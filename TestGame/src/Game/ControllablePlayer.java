package Game;

import Engine.*;

import java.awt.*;

public class ControllablePlayer extends BasePlayer {
    private static final float MOVEMENT_SPEED = 250;
    private static final int START_BULLETS = 30;
    private static final float FIRE_RATE = 0.2f;
    private static final float MULTISHOOT_FIRERATE = 5f;

    protected KeyConfig keys;
    private float nextMultiShootTime;
    public float fireRate;
    private float nextShootTime;
    private BasePlayer killer;

    public ControllablePlayer(String name, Vector2 position, KeyConfig keys, Color skinColor) {
        super(name, position, skinColor);
        this.keys = keys;
        this.ammo = START_BULLETS;
        this.fireRate = FIRE_RATE;
        setNextMultiShootTime();
        setFireTime();

        this.killer = null;
    }

    private void setFireTime() {
        this.nextShootTime = Application.time + fireRate;
    }

    private void setNextMultiShootTime() {
        nextMultiShootTime = Application.time + MULTISHOOT_FIRERATE;
    }

    private void respawn() {
        // Reset values
        this.health = 100;
        this.ammo = START_BULLETS;
        this.streaks.resetStreak();
        this.boosterTime = 0;
        this.hasBooster = false;
        this.killer = null;
        setPosition(Util.randomPositionInsideZone());

        Application.log("Player " + name + " respawned");

        World.onRespawn();

        setNextMultiShootTime();
    }

    private void handleMovement(Input i, float deltaTime) {
        float speed = MOVEMENT_SPEED * getSpeedMultiplier() * deltaTime;

        Vector2 targetDirection = new Vector2(0, 0);

        // Handle horizontal movement
        if (i.getKey(keys.moveLeft)) {
            targetDirection.x = -1;
        }
        if (i.getKey(keys.moveRight)) {
            targetDirection.x = 1;
        }

        // Handle vertical movement
        if (i.getKey(keys.moveUp)) {
            targetDirection.y = -1;
        }
        if (i.getKey(keys.moveDown)) {
            targetDirection.y = 1;
        }

        targetDirection.normalize();

        this.position.x += targetDirection.x * speed;
        this.position.y += targetDirection.y * speed;

        Camera.focusOn(getChest());

        Vector2 p = Application.getCursorPosition();
        shootDirection = Vector2.difference(Camera.offsetPosition(getChest()), p).getNormalized();
    }

    private void handleShotFired() {
        ammo--;

        Vector2 currentShootDirection = new Vector2(shootDirection.x, shootDirection.y);
        if (currentShootDirection.isZero()) {
            currentShootDirection = Vector2.left();
        }

        Vector2 shootPosition = new Vector2(position.x + chest.x, position.y + chest.y);

        // Spawn projectile
        Projectile p = new Projectile(this, shootPosition, currentShootDirection, 1f);

        // Add projectile to entity list
        EntityManager.addEntity(p);
    }

    private void handleMultiShoot() {
        int toFire = Math.min(ammo, START_BULLETS);

        ammo -= toFire;

        Vector2 shootPosition = new Vector2(position.x + chest.x, position.y + chest.y);

        for (int i = 0; i < toFire; i++) {
            double angle = Util.randomBetween(0f, 360f);

            Vector2 shootDirection = new Vector2((float)Math.cos(angle), (float)Math.sin(angle));

            // Spawn projectile
            Projectile p = new Projectile(this, shootPosition, shootDirection, 1f);

            // Add projectile to entity list
            EntityManager.addEntity(p);
        }
    }

    @Override
    public void onDied(BasePlayer attacker) {
        super.onDied(attacker);

        if (attacker != null && !attacker.isDead()) {
            killer = attacker;
        }
    }

    private String getBoosterString() {
        if (!hasBooster)
            return "none";

        float timeLeft = boosterTime - Application.time;
        return "" + timeLeft + "s";
    }

    @Override
    public void render(Drawing d) {
        super.render(d);

        d.setPositionRelative(false);
        d.setCameraRelative(false);
        d.drawText(new Vector2(25, Application.getScreenSize().y - 150), "Health: " + getHealth());
        d.drawText(new Vector2(25, Application.getScreenSize().y - 125), "Booster: " + getBoosterString());
        d.drawText(new Vector2(25, Application.getScreenSize().y - 100), "Streak: " + streaks.currentStreak);
        d.drawText(new Vector2(25, Application.getScreenSize().y - 75), "Ammo: " + ammo);
        d.setCameraRelative(true);
        d.setPositionRelative(true);
    }

    @Override
    public void update(Input i, float deltaTime) {
        super.update(i, deltaTime);

        // Don't run game logic if player is dead
        if (isDead()) {
            if (i.getKeyDown(keys.respawn)) {
                respawn();
            } else {
                if (killer != null && !killer.isDead()) {
                    Camera.focusOn(killer.getChest());
                }
                return;
            }
        }

        // Handle player movement
        handleMovement(i, deltaTime);

        // Handle shooting
        if (i.getKeyDown(keys.shoot) && !isOutOfAmmo()) {
            handleShotFired();
        } else if (i.getKey(keys.shoot) && !isOutOfAmmo() && Application.time >= nextShootTime) {
            handleShotFired();
            setFireTime();
        }

        if (i.getKeyDown(keys.multiShoot) && !isOutOfAmmo() && Application.time >= nextMultiShootTime) {
            handleMultiShoot();
            setNextMultiShootTime();
        }
    }
}
