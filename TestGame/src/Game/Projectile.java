package Game;

import Engine.*;
import Game.*;

import java.awt.*;

public class Projectile extends BaseEntity {
    private static final float PROJECTILE_SPEED = 300;

    public Vector2 direction;
    private Color color;
    private BasePlayer attacker;

    public Projectile(BasePlayer attacker, Vector2 position, Vector2 direction) {
        this.attacker = attacker;
        this.position = position;
        this.direction = direction;
        this.color = Util.randomColor();
    }

    private void onHit(BasePlayer player) {
        // We shouldn't be able to hit ourselves!
        if (player.equals(attacker))
            return;

        int damage = Util.randomBetween(5, 25);

        // Game.Projectile hit a player!
        player.takeDamage(damage);
        System.out.println("Hit player " + player.name + " for " + damage + " (" + player.getHealth() + ")");

        // Destroy projectile
        EntityManager.removeEntity(this);

        // Check if this hit killed the player
        if (player.isDead()) {
            attacker.addKill();

            System.out.println("Player " + player.name + " was killed by " + attacker.name);
        }
    }

    @Override
    public void update(Input i, float deltaTime) {
        Vector2 movement = new Vector2(
                direction.x * PROJECTILE_SPEED * deltaTime,
                direction.y * PROJECTILE_SPEED * deltaTime);

        position = new Vector2(position.x + movement.x, position.y + movement.y);

        // Check for collision against players
        for (BasePlayer player : EntityManager.getPlayerList()) {
            if (player.isDead())
                continue;

            if (player.getCollider().isInsideCollider(this.position)) {
                onHit(player);
            }
        }
    }

    @Override
    public void render(Drawing d) {
        d.drawCircle(Vector2.zero(), 10, color);
    }
}
