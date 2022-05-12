package Engine;

import Engine.Application;
import Game.SafeZone;

import java.awt.*;
import java.util.Random;

public class Util {
    public static Color randomColor() {
        Random rand = new Random();
        int r = rand.nextInt(255);
        int g = rand.nextInt(255);
        int b = rand.nextInt(255);
        return new Color(r, g, b);
    }

    public static int randomBetween(int min, int max) {
        Random r = new Random();
        return min + r.nextInt(max - min);
    }

    public static boolean randomChance(float chance) {
        return (float)randomBetween(0, 100) < chance;
    }

    public static Vector2 randomPosition() {
        Random r = new Random();
        Vector2 screenSize = Application.getScreenSize();

        // Add bounds of 25 so items can't spawn at the edge of your screen
        return new Vector2((float)randomBetween(25, screenSize.getX() - 25), (float)randomBetween(25, screenSize.getY() - 25));
    }

    public static Vector2 randomPositionInsideZone() {
        Random r = new Random();
        Vector2 zonePos = SafeZone.instance.getZonePosition();
        return new Vector2(zonePos.x + randomBetween(-150, 150), zonePos.x + randomBetween(-150, 150));
    }
}
