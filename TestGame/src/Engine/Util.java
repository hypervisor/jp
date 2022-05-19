package Engine;

import Engine.Application;
import Game.SafeZone;

import java.awt.*;
import java.util.Random;

public class Util {
    private static Random rand = new Random();

    public static Color randomColor() {
        int r = rand.nextInt(255);
        int g = rand.nextInt(255);
        int b = rand.nextInt(255);
        return new Color(r, g, b);
    }

    public static int randomBetween(int min, int max) {
        return min + rand.nextInt(max - min);
    }

    public static float randomBetween(float min, float max) {
        return min + (rand.nextFloat() * (max - min));
    }

    public static boolean randomChance(float chance) {
        return randomBetween(0f, 100f) < chance;
    }

    public static Vector2 randomPosition() {
        Vector2 screenSize = Application.getScreenSize();

        // Add bounds of 25 so items can't spawn at the edge of your screen
        return new Vector2((float)randomBetween(25, screenSize.getX() - 25), (float)randomBetween(25, screenSize.getY() - 25));
    }

    public static String pluralizeWord(String word, int count) {
        if (count == 0 || count > 1)
            return word + "s";
        return word;
    }

    public static Vector2 randomPositionInsideZone() {
        Vector2 screenSize = Application.getScreenSize();
        Vector2 zonePos = SafeZone.instance.getZonePosition();
        int radius = (int)SafeZone.instance.getZoneRadius();

        int min = -radius;
        int max = radius;

        int rx = zonePos.getX() + randomBetween(min, max);
        int ry = zonePos.getY() + randomBetween(min, max);

        // Make sure position doesn't go outside screen

        /*
        rx = Math.min(rx, screenSize.getX() - 50);
        ry = Math.min(ry, screenSize.getY() - 100);

        rx = Math.max(rx, 25);
        ry = Math.max(ry, 100);
         */

        return new Vector2(rx, ry);
    }
}
