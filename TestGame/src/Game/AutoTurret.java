package Game;

import Engine.*;

import java.awt.*;

public class AutoTurret extends BaseEntity {
    private static final float TURRET_MAX_DISTANCE = 600;
    private static final float TURRET_FIRE_RATE = 0.25f;
    private static final float TURRET_LIFETIME = 20f;

    public BasePlayer owner;
    public BasePlayer target;
    private Vector2 shootDirection;
    private float nextShotTime;
    private float dieTime;

    public AutoTurret(BasePlayer player) {
        this.owner = player;
        this.position = new Vector2(player.position.x, player.position.y);
        this.health = 100;

        nextShotTime = Application.time + TURRET_FIRE_RATE;
        dieTime = Application.time + TURRET_LIFETIME;
    }

    private void updateTarget() {
        BasePlayer closest = null;
        float minDist = TURRET_MAX_DISTANCE;
        for (BasePlayer player : EntityManager.getPlayerList()) {
            if (player == owner)
                continue;

            if (player.isDead())
                continue;

            float d = Vector2.distance(position, player.position);
            if (d < minDist) {
                closest = player;
                minDist = d;
            }
        }

        target = closest;
    }

    @Override
    public void update(Input i, float deltaTime) {
        if (target == null || target.isDead()) {
            updateTarget();
        } else {
            shootDirection = Vector2.difference(position, target.getChest()).getNormalized();
        }

        if (target != null && !target.isDead()) {
            if (Application.time >= nextShotTime && shootDirection != null) {
                // Spawn projectile
                Projectile p = new Projectile(owner, position, shootDirection, 1f);

                // Add projectile to entity list
                EntityManager.addEntity(p);

                nextShotTime = Application.time + TURRET_FIRE_RATE;
            }
        }

        if (Application.time > dieTime) {
            owner.turrets.remove(this);
            EntityManager.removeEntity(this);
        }
    }

    @Override
    public void render(Drawing d) {
        d.fillCircle(new Vector2(-40, -40), 80, Color.black);
        d.fillCircle(new Vector2(-20, -20), 40, Color.darkGray);
        d.drawText(Vector2.zero(), "Owner: " + owner + " (" + owner.getHealth() + ")", Color.RED);
        if (target != null) {
            d.drawText(new Vector2(0, 25), "Target: " + target + " (" + target.getHealth() + ")", Color.RED);
        }

        if (shootDirection != null)
            d.drawLine(Vector2.zero(), new Vector2(shootDirection.x * 50, shootDirection.y * 50), Color.RED);
    }
}
