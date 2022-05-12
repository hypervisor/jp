package Game;

import Engine.*;

import java.awt.*;

public class SafeZone extends BaseEntity {
    public static final int ZONE_RADIUS = 350;
    public static final int ZONE_DIAMETER = ZONE_RADIUS * 2;
    public static final Color ZONE_COLOR = new Color(200, 200, 255);

    public static SafeZone instance;

    public SafeZone() {
        instance = this;
        Vector2 screenSize = Application.getScreenSize();
        this.position = new Vector2((screenSize.x - ZONE_DIAMETER) / 2, (screenSize.y - ZONE_DIAMETER) / 2);
        //this.position = new Engine.Vector2(150, 150);
    }

    public Vector2 zonePosition() {
        Vector2 screenSize = Application.getScreenSize();
        return new Vector2(screenSize.x / 2, screenSize.y / 2);
    }

    private boolean inZone(Vector2 p) {
        return Vector2.distance(zonePosition(), p) < ZONE_RADIUS;
    }

    @Override
    public void update(Input i, float deltaTime) {
        for (BasePlayer player : EntityManager.getPlayerList()) {
            if (inZone(player.topLeft()) ||
                    inZone(player.topRight()) ||
                    inZone(player.bottomLeft()) ||
                    inZone(player.bottomRight())) {
                // One or more of the player hitbox corners are inside zone, player is good
                continue;
            }

            // If player has the gas mask killstreak he is immune to gas damage.
            if (player.gasMask.hasKillStreak())
                continue;

            // Player is outside the zone, fuck him up
            float distance = Vector2.distance(zonePosition(), player.middle());
            float damage = distance / 100000;
            player.takeDamage(damage / deltaTime);
        }
    }

    @Override
    public void render(Drawing d) {
        d.fillCircle(Vector2.zero(), ZONE_DIAMETER, ZONE_COLOR);
        d.drawCircle(Vector2.zero(), ZONE_DIAMETER, Color.BLUE);
    }
}
