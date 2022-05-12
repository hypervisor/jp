import java.awt.*;

public abstract class BaseItem extends BaseEntity {
    public String displayText;
    public Color displayColor;

    // Returns true if item was picked up
    public abstract boolean onPickup(BasePlayer player);

    @Override
    public void update(Input i, float deltaTime) {
        // Check for collision against players
        for (BasePlayer player : EntityManager.getPlayerList()) {
            if (player.isDead())
                continue;

            if (player.collider.isInsideCollider(this.position)) {
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
    }
}
