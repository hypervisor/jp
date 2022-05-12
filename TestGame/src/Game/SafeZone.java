package Game;

import Engine.*;

import java.awt.*;

public class SafeZone extends BaseEntity {
    public static boolean ZONE_DEBUG = false;
    public static final float ZONE_MOVE_SPEED = 10;
    public static final float ZONE_SCALE_SPEED = 5;
    public static final float ZONE_START_RADIUS = 500;
    public static final float ZONE_FINAL_RADIUS = 100;
    public static final Color ZONE_COLOR = new Color(200, 200, 255);

    public static SafeZone instance;

    private Vector2 zoneTarget;
    private Vector2 zonePosition;
    private Vector2 initialZonePosition;
    private float zoneRadius;

    public SafeZone() {
        position = Vector2.zero();
        zoneTarget = Util.randomPosition();
        zoneRadius = ZONE_START_RADIUS;
        instance = this;

        Vector2 screenSize = Application.getScreenSize();
        zonePosition = new Vector2(screenSize.x / 2, screenSize.y / 2);
        initialZonePosition = zonePosition;
    }

    public Vector2 getZonePosition() {
        return zonePosition;
    }
    public float getZoneRadius() {
        return zoneRadius;
    }

    private Vector2 getZoneDrawPosition() {
        return new Vector2(zonePosition.x - zoneRadius, zonePosition.y - zoneRadius);
    }

    private float getZoneDiameter() {
        return zoneRadius * 2;
    }

    private boolean inZone(Vector2 p) {
        return Vector2.distance(zonePosition, p) < zoneRadius;
    }

    public void onRespawn() {
        if (zoneRadius <= ZONE_FINAL_RADIUS)
            zoneRadius = (float)Util.randomBetween((int)ZONE_FINAL_RADIUS, (int)ZONE_START_RADIUS);
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
            zoneRadius -= ZONE_SCALE_SPEED * deltaTime;

        // Move towards zone target
        zonePosition = Vector2.moveTowards(zonePosition, zoneTarget, ZONE_MOVE_SPEED * deltaTime);

        // If we reached zone target, move to new location
        if (zonePosition.equals(zoneTarget)) {
            zoneTarget = Util.randomPosition();
        }
    }

    @Override
    public void render(Drawing d) {
        Vector2 drawPos = getZoneDrawPosition();
        d.fillCircle(drawPos, getZoneDiameter(), ZONE_COLOR);
        d.drawCircle(drawPos, getZoneDiameter(), Color.BLUE);

        if (ZONE_DEBUG) {
            d.drawCircle(zonePosition, 25, Color.RED);
            d.drawLine(Vector2.zero(), zonePosition, Color.RED);
            d.drawText(zonePosition, "Actual SafeZone position");

            d.drawCircle(drawPos, 25, Color.BLUE);
            d.drawLine(Vector2.zero(), getZoneDrawPosition(), Color.BLUE);
            d.drawText(drawPos, "SafeZone draw position");

            d.drawCircle(zoneTarget, 25, Color.YELLOW);
            d.drawLine(Vector2.zero(), zoneTarget, Color.YELLOW);
            d.drawText(zoneTarget, "Target SafeZone position");
        }
    }
}
