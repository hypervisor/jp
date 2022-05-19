package Game;

import Engine.*;

import java.awt.*;

public class Projectile extends BaseEntity {
    private static final float PROJECTILE_SPEED = 750;
    private static final float PROJECTILE_LIFETIME = 3.5f;

    public Vector2 direction;
    private Color color;
    public BasePlayer attacker;
    private float dieTime;

    public Projectile(BasePlayer attacker, Vector2 position, Vector2 direction) {
        this.attacker = attacker;
        this.position = position;
        this.direction = direction;
        this.color = Util.randomColor();
        this.dieTime = Application.time + PROJECTILE_LIFETIME;
    }

    @Override
    public void update(Input i, float deltaTime) {
        // Check if it's time to die
        if (Application.time >= this.dieTime) {
            // Destroy projectile
            EntityManager.removeEntity(this);

            return;
        }

        Vector2 movement = new Vector2(
                direction.x * PROJECTILE_SPEED * deltaTime,
                direction.y * PROJECTILE_SPEED * deltaTime);

        position = new Vector2(position.x + movement.x, position.y + movement.y);

        // Check for collision
        for (BaseEntity entity : EntityManager.getEntities()) {
            if (entity.isDead())
                continue;

            if (entity instanceof Projectile)
                continue;

            if (entity.checkCollision(this.position)) {
                if (entity.onProjectileHit(this)) {
                    // Destroy projectile
                    EntityManager.removeEntity(this);
                }
            }
        }
    }

    @Override
    public void render(Drawing d) {
        d.drawCircle(Vector2.zero(), 10, color);
    }
}
