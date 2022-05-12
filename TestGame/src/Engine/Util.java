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
        return new Vector2((float)r.nextInt(screenSize.getX()), (float) r.nextInt(screenSize.getY()));
    }

    public static Vector2 randomPositionInsideZone() {
        Random r = new Random();
        Vector2 zonePos = SafeZone.instance.zonePosition();
        return new Vector2(zonePos.x + randomBetween(-150, 150), zonePos.x + randomBetween(-150, 150));
    }
}
