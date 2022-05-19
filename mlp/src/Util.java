import java.util.Random;

public class Util {
    private static Random rand = new Random();

    public static double randomBetween(double min, double max) {
        return min + (rand.nextDouble() * (max - min));
    }

    public static boolean randomChance(double chance) {
        return randomBetween(0, 100) < chance;
    }
}
