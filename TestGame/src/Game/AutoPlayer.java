package Game;

import Engine.*;
import Items.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class AutoPlayer extends BasePlayer {
    private static final boolean SHOW_DEBUG_INFO = false;

    private BasePlayer target;
    private float movementSpeed;
    private float nextShotTime;
    private float fireRate;
    private float nextBirthTime;
    private float birthRate;
    private float suicidalChance;
    private boolean isCrazy;
    private boolean isRacist;
    private boolean isSuicidal;

    public AutoPlayer(String name, Vector2 position, AutoPlayer mutationSource) {
        super(name, position, Color.GREEN);
        setRandomTarget();

        if (mutationSource != null) {
            movementSpeed = mutationSource.movementSpeed * Util.randomBetween(0.9f, 1.1f);
            fireRate = mutationSource.fireRate * Util.randomBetween(0.9f, 1.1f);
            birthRate = mutationSource.birthRate * Util.randomBetween(0.9f, 1.1f);
            suicidalChance = mutationSource.suicidalChance * Util.randomBetween(0.5f, 1.5f);
            isCrazy = Util.randomChance(mutationSource.isCrazy ? 90 : 10);
            isRacist = Util.randomChance(mutationSource.isRacist ? 90 : 10);
        } else {
            movementSpeed = Util.randomBetween(10, 200);
            fireRate = Util.randomBetween(0.1f, 6f);
            birthRate = Util.randomBetween(12f, 75f);
            suicidalChance = Util.randomBetween(0.5f, 50.f);
            isCrazy = Util.randomChance(10);
            isRacist = Util.randomChance(isCrazy ? 90 : 60);

            if (isCrazy) {
                // Crazy bots are on steroids. Move faster, shoot faster, more racist, etc
                movementSpeed *= 1.5f;
                fireRate *= 1.5f;
                birthRate *= 0.85f;
            }
        }
        isSuicidal = Util.randomChance(suicidalChance);

        skinColor = isCrazy ? Color.RED : Color.GREEN;

        nextShotTime = Application.time + fireRate;
        nextBirthTime = Application.time + birthRate;
    }

    public void setRandomTarget() {
        var alivePlayers = new ArrayList<BasePlayer>();
        var players = EntityManager.getPlayerList();
        for (var player : players) {
            if (player.isDead())
                continue;

            if (player.position == null)
                continue;

            if (isRacist && player.skinColor == this.skinColor)
                continue;

            // Only crazy bots go outside the zone
            if (!isCrazy && !SafeZone.instance.inZone(player.position))
                continue;

            alivePlayers.add(player);
        }

        if (alivePlayers.size() == 0)
            target = null;
        else
            target = alivePlayers.get(Util.randomBetween(0, alivePlayers.size()));
    }

    private boolean shouldAttack() {
        return health > 25f && !isOutOfAmmo();
    }

    private boolean isHealable(BaseEntity ent) {
        return ent instanceof Bandage || ent instanceof SuperBandage || ent instanceof Booster;
    }

    private BaseEntity getItemTarget() {
        BaseEntity closest = null;
        float minDist = Float.MAX_VALUE;
        for (BaseEntity entity : EntityManager.getEntities()) {
            if (!(entity instanceof BaseItem))
                continue;

            if (isOutOfAmmo() && !(entity instanceof Ammo))
                continue;

            if (health <= 25f && !isHealable(entity))
                continue;

            // Don't walk outside zone unless you have gas mask
            if (!gasMask.hasKillStreak() && !SafeZone.instance.inZone(entity.position))
                continue;

            float d = Vector2.distance(position, entity.position);
            if (d < minDist) {
                closest = entity;
                minDist = d;
            }
        }

        return closest;
    }

    private BaseEntity getClosestLandMine() {
        BaseEntity closest = null;
        float minDist = Float.MAX_VALUE;
        for (BaseEntity entity : EntityManager.getEntities()) {
            if (!(entity instanceof LandMine))
                continue;

            float d = Vector2.distance(position, entity.position);
            if (d < minDist) {
                closest = entity;
                minDist = d;
            }
        }

        return closest;
    }

    private void moveTo(Vector2 targetPosition, float deltaTime) {
        setPosition(Vector2.moveTowards(position, targetPosition, movementSpeed * deltaTime));
    }

    @Override
    public void update(Input i, float deltaTime) {
        super.update(i, deltaTime);

        if (isDead())
            return;

        if (isSuicidal) {
            BaseEntity closestLandMine = getClosestLandMine();
            if (closestLandMine != null) {
                moveTo(closestLandMine.position, deltaTime);
                return;
            }
        }

        if (shouldAttack()) {
            if (target == null || target.isDead())
                setRandomTarget();

            if (target != null) {
                if (Vector2.distance(position, target.position) > 100) {
                    // Only move towards target if we're more than one meter away
                    moveTo(target.position, deltaTime);
                }

                if (!isOutOfAmmo() && Application.time >= nextShotTime) {
                    ammo--;

                    Vector2 shootPosition = new Vector2(position.x + chest.x, position.y + chest.y);
                    Vector2 shootDirection = Vector2.difference(shootPosition, target.getChest()).getNormalized();

                    // Spawn projectile
                    Projectile p = new Projectile(this, shootPosition, shootDirection);

                    // Add projectile to entity list
                    EntityManager.addEntity(p);

                    nextShotTime = Application.time + fireRate;
                }
            }
        } else {
            BaseEntity itemTarget = getItemTarget();
            if (itemTarget != null) {
                moveTo(itemTarget.position, deltaTime);
            }
        }

        if (Application.time >= nextBirthTime) {
            // Every few seconds a bot can have 1 to 4 children.
            int children = Util.randomBetween(1, 4);

            // Crazy bots have 2x more children, so killing them is smart.
            if (isCrazy)
                children *= 2;

            World.spawnBots(children, this);
            nextBirthTime = Application.time + birthRate;

            Application.log(name + " gave birth to " + children + " children");
        }
    }

    @Override
    public void onDied() {
        super.onDied();

        // Let there be a 60% chance for the bot to respawn
        if (Util.randomChance(60)) {
            // Reset values
            this.health = 100;
            this.ammo = 24;

            setPosition(Util.randomPositionInsideZone());

            //Application.log("Player " + name + " respawned");

            World.onRespawn();
        } else {
            Application.log("Player " + name + " died permanently!");

            EntityManager.removeEntity(this);
        }
    }

    @Override
    public void render(Drawing d) {
        if (isDead())
            return;

        super.render(d);

        if (SHOW_DEBUG_INFO)
            d.drawText(new Vector2(0, 25), "Target: " + target.name);
    }
}
