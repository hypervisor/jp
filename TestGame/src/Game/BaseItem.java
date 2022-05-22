package Game;

import Engine.*;

import java.awt.*;

public abstract class BaseItem extends BaseEntity {
    public static final boolean DEBUG_COLLIDERS = false;

    public String displayText;
    public Color displayColor;
    public float dieTime;

    public BaseItem(Vector2 position, String displayText, Color displayColor) {
        this.position = position;
        this.displayText = displayText;
        this.displayColor = displayColor;
        this.dieTime = Application.time + Util.randomBetween(20f, 30f);
    }

    // Returns true if item was picked up
    public abstract boolean onPickup(BasePlayer player);

    @Override
    public void update(Input i, float deltaTime) {
        // Check for collision against players
        for (BasePlayer player : EntityManager.getPlayerList()) {
            if (player.isDead())
                continue;

            if (player.getCollider().isInsideCollider(this.position)) {
                if (onPickup(player)) {
                    // If item was picked up, delete it.
                    EntityManager.removeEntity(this);
                    break;
                }
            }
        }

        if (Application.time > dieTime) {
            EntityManager.removeEntity(this);
        }
    }

    @Override
    public void render(Drawing d) {
        d.fillCircle(Vector2.zero(), 25, displayColor);
        d.drawText(Vector2.zero(), displayText);
        if (DEBUG_COLLIDERS && this.collider != null) {
            d.drawRect(Vector2.zero(), ((BoxCollider)this.collider).size);
        }
    }
}
