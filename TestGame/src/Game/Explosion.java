package Game;

import Engine.*;
import Items.Barrel;
import Items.LandMine;

import java.awt.*;

public class Explosion extends BaseEntity {
    private static final float EXPLOSION_TIME = 1.5f;
    private static final float EXPLOSION_CIRCLE_SPEED = 400f;

    private Vector2 explosionPosition;
    private float size;
    private float explosionRadius;
    private float dieTime;

    private Explosion(Vector2 position, float size) {
        this.explosionPosition = position;
        this.position = Vector2.zero();
        this.size = size;
        this.explosionRadius = size;
        this.dieTime = Application.time + EXPLOSION_TIME;
    }

    private static boolean isExplodable(BaseEntity entity) {
        return entity instanceof Barrel || entity instanceof LandMine;
    }

    public static void triggerExplosion(Vector2 position, float size, float damageMultiplier, BaseEntity source, BasePlayer toIgnore) {
        // Explode - just do damage to everyone around us within a certain distance
        for (BasePlayer player : EntityManager.getPlayerList()) {
            if (player == toIgnore)
                continue;

            if (player.isDead())
                continue;

            float distance = Vector2.distance(position, player.getPosition());
            if (distance > size)
                continue;

            // Inverse of distance in range [0, distance] converted to a percentage
            float damage = ((size - distance) / size) * 100 * damageMultiplier;

            player.takeDamage(damage);
            //Application.log("Explosion caused " + damage + " damage to " + player.name);
        }

        // Trigger explosions to barrels and landmines
        for (BaseEntity entity : EntityManager.getEntities()) {
            if (entity == source)
                continue;

            if (!isExplodable(entity))
                continue;

            if (Vector2.distance(position, entity.position) > size)
                continue;

            /*
            if (entity instanceof Barrel) {
                ((Barrel)entity).explodeBarrel();
            } else if (entity instanceof LandMine) {
                ((LandMine)entity).explodeLandMine();
            }

            EntityManager.removeEntity(entity);
             */
        }

        // Create Explosion entity for visuals
        EntityManager.addEntity(new Explosion(position, size));
    }

    public static void triggerExplosion(Vector2 position, float size, float damageMultiplier, BaseEntity source) {
        triggerExplosion(position, size, damageMultiplier, source, null);
    }

    private Vector2 getDrawPosition() {
        return new Vector2(explosionPosition.x - explosionRadius, explosionPosition.y - explosionRadius);
    }

    @Override
    public void update(Input i, float deltaTime) {
        explosionRadius -= (EXPLOSION_CIRCLE_SPEED / EXPLOSION_TIME) * deltaTime;

        if (Application.time >= dieTime) {
            EntityManager.removeEntity(this);
        }
    }

    @Override
    public void render(Drawing d) {
        d.drawCircle(getDrawPosition(), explosionRadius * 2, Color.RED);
        d.fillCircle(getDrawPosition(), explosionRadius * 2, Color.ORANGE);
    }
}
