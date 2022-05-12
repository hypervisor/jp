package Game;

import Engine.*;
import Game.*;

import java.awt.*;

public abstract class BaseItem extends BaseEntity {
    public String displayText;
    public Color displayColor;

    public BaseItem(Vector2 position, String displayText, Color displayColor) {
        this.position = position;
        this.displayText = displayText;
        this.displayColor = displayColor;
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
    }

    @Override
    public void render(Drawing d) {
        d.fillCircle(Vector2.zero(), 25, displayColor);
        d.drawText(Vector2.zero(), displayText);
        if (this.collider != null) {
            d.drawRect(Vector2.zero(), ((BoxCollider)this.collider).size);
        }
    }
}
