package Game;

import Engine.*;

import java.awt.*;
import java.util.ArrayList;

public class AutoPlayer extends BasePlayer {
    private static final boolean SHOW_DEBUG_INFO = false;

    private BasePlayer target;
    private float movementSpeed;
    private float nextShotTime;
    private float fireRate;
    private float nextBirthTime;
    private float birthRate;

    public AutoPlayer(String name, Vector2 position) {
        super(name, position, Color.GREEN);
        setRandomTarget();
        movementSpeed = Util.randomBetween(25, 100);
        fireRate = Util.randomBetween(0.2f, 2.5f);
        nextShotTime = Application.time + fireRate;
        birthRate = Util.randomBetween(8f, 20f);
        nextBirthTime = Application.time + birthRate;
    }

    public void setRandomTarget() {
        var alivePlayers = new ArrayList<BasePlayer>();
        var players = EntityManager.getPlayerList();
        for (var player : players) {
            if (player.isDead())
                continue;
            alivePlayers.add(player);
        }

        target = alivePlayers.get(Util.randomBetween(0, alivePlayers.size()));
    }

    @Override
    public void update(Input i, float deltaTime) {
        super.update(i, deltaTime);

        if (isDead())
            return;

        if (target.isDead())
            setRandomTarget();

        if (Application.time >= nextBirthTime) {
            // Every few seconds a bot can have 1 to 3 children.
            int children = Util.randomBetween(1, 3);
            World.spawnBots(children);
            nextBirthTime = Application.time + birthRate;

            System.out.println(name + " gave birth to " + children + " children");
        }

        setPosition(Vector2.moveTowards(position, target.position, movementSpeed * deltaTime));

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

    @Override
    public void onDied() {
        super.onDied();

        // Let there be a 75% chance for the bot to respawn
        if (Util.randomChance(75)) {
            // Reset values
            this.health = 100;
            this.ammo = 24;
            //movementSpeed = Util.randomBetween(25, 60);
            //fireRate = Util.randomBetween(0.2f, 2.5f);
            setPosition(Util.randomPositionInsideZone());

            System.out.println("Player " + name + " respawned");

            World.onRespawn();
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
