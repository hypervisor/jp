package Game;

import Engine.*;

import java.awt.*;

public class SafeZone extends BaseEntity {
    public static boolean ZONE_DEBUG = true;
    public static final float ZONE_SPEED = 10;
    public static final float ZONE_FINAL_RADIUS = 50;
    public static final Color ZONE_COLOR = new Color(200, 200, 255);

    public static SafeZone instance;

    private Vector2 zoneTarget;
    private Vector2 zonePosition;
    private Vector2 initialZonePosition;
    private float zoneRadius;

    public SafeZone() {
        position = Vector2.zero();
        zoneTarget = Util.randomPosition();
        zoneRadius = 300;
        instance = this;

        Vector2 screenSize = Application.getScreenSize();
        zonePosition = new Vector2(screenSize.x / 2, screenSize.y / 2);
        initialZonePosition = zonePosition;
    }

    public Vector2 getZonePosition() {
        return zonePosition;
    }

    private Vector2 getZoneDrawPosition() {
        //Vector2 screenSize = Application.getScreenSize();
        //return new Vector2((screenSize.x - getZoneDiameter()) / 2, (screenSize.y - getZoneDiameter()) / 2);

        return new Vector2(zonePosition.x - zoneRadius, zonePosition.y - zoneRadius);
    }

    private float getZoneDiameter() {
        return zoneRadius * 2;
    }

    private boolean inZone(Vector2 p) {
        return Vector2.distance(zonePosition, p) < zoneRadius;
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

            // Player is outside the zone, fuck him up
            float distance = Vector2.distance(zonePosition, player.middle());
            float damage = distance / 100000;

            // Gas mask reduces gas damage to 10%
            if (player.gasMask.hasKillStreak())
                damage /= 10;

            player.takeDamage(damage / deltaTime);
        }

        if (zoneRadius > ZONE_FINAL_RADIUS)
            zoneRadius -= ZONE_SPEED * deltaTime;

        zonePosition = Vector2.lerp(initialZonePosition, zoneTarget, ZONE_SPEED * deltaTime);
    }

    @Override
    public void render(Drawing d) {
        d.fillCircle(getZoneDrawPosition(), getZoneDiameter(), ZONE_COLOR);
        d.drawCircle(getZoneDrawPosition(), getZoneDiameter(), Color.BLUE);

        if (ZONE_DEBUG) {
            d.fillRect(zonePosition, new Vector2(50, 50), Color.RED);
            d.drawLine(Vector2.zero(), zonePosition, Color.RED);

            d.fillRect(getZoneDrawPosition(), new Vector2(50, 50), Color.BLUE);
            d.drawLine(Vector2.zero(), getZoneDrawPosition(), Color.BLUE);

            d.fillRect(zoneTarget, new Vector2(50, 50), Color.YELLOW);
            d.drawLine(Vector2.zero(), zoneTarget, Color.YELLOW);
        }
    }
}
