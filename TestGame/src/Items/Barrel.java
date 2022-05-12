package Items;

import Engine.*;
import Game.BaseItem;
import Game.BasePlayer;
import Game.Projectile;

import javax.swing.*;
import java.awt.*;

public class Barrel extends BaseItem {
    public static final int MIN_AMOUNT = 2;
    public static final int MAX_AMOUNT = 4;

    private static final Color BARREL_COLOR = new Color(183, 65, 14);
    private static final float EXPLOSION_DISTANCE = 100;
    private static final float EXPLOSION_DAMAGE_MULTIPLIER = 1.5f;

    public Barrel(Vector2 position) {
        super(position, "Barrel", BARREL_COLOR);
        this.collider = new BoxCollider(position, new Vector2(25, 25));
        this.health = 100f;
    }

    @Override
    public boolean onProjectileHit(Projectile p) {
        // Remove the entity
        EntityManager.removeEntity(this);

        // Explode - just do damage to everyone around us within a certain distance
        for (BasePlayer player : EntityManager.getPlayerList()) {
            if (player.isDead())
                continue;

            float distance = Vector2.distance(position, player.getPosition());
            if (distance > EXPLOSION_DISTANCE)
                continue;

            // Inverse of distance in range [0, distance] converted to a percentage
            float damage = ((EXPLOSION_DISTANCE - distance) / EXPLOSION_DISTANCE) * 100 * EXPLOSION_DAMAGE_MULTIPLIER;

            player.takeDamage(damage);
            System.out.println("Barrel explosion caused " + damage + " damage to " + player.name);
        }

        // Register the hit
        return true;
    }

    @Override
    public boolean onPickup(BasePlayer player) {
        return false;
    }
}
