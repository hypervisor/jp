package Game;

import Engine.*;
import Items.LandMine;

import java.awt.*;

public class AutoTurret extends BaseEntity {
    private static final float TURRET_MAX_DISTANCE = 600;
    private static final float TURRET_FIRE_RATE = 0.25f;

    public BasePlayer owner;
    public BasePlayer target;
    private Vector2 shootDirection;
    private float nextShotTime;

    public AutoTurret(BasePlayer player) {
        this.owner = player;
        this.position = new Vector2(player.position.x, player.position.y);
        this.health = 100;

        nextShotTime = Application.time + TURRET_FIRE_RATE;
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

        if (Application.time >= nextShotTime && shootDirection != null) {
            // Spawn projectile
            Projectile p = new Projectile(owner, position, shootDirection);

            // Add projectile to entity list
            EntityManager.addEntity(p);

            nextShotTime = Application.time + TURRET_FIRE_RATE;
        }
    }

    @Override
    public void render(Drawing d) {
        d.fillCircle(new Vector2(-40, -40), 80, Color.black);
        d.fillCircle(new Vector2(-20, -20), 40, Color.darkGray);

        if (shootDirection != null)
            d.drawLine(Vector2.zero(), new Vector2(shootDirection.x * 50, shootDirection.y * 50), Color.RED);
    }
}
