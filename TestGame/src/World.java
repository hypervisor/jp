import java.util.Random;

public class World {
    public static void spawnBandages() {
        Random r = new Random();
        for (int i = 0; i < Util.randomBetween(Bandage.MIN_AMOUNT, Bandage.MAX_AMOUNT); i++) {
            EntityManager.addEntity(new Bandage(Util.randomPosition()));
        }
    }

    public static void spawnAmmo() {
        Random r = new Random();
        for (int i = 0; i < Util.randomBetween(Bandage.MIN_AMOUNT, Bandage.MAX_AMOUNT); i++) {
            EntityManager.addEntity(new Ammo(Util.randomPosition()));
        }
    }

    public static void spawnPoison() {
        Random r = new Random();
        for (int i = 0; i < Util.randomBetween(Poison.MIN_AMOUNT, Poison.MAX_AMOUNT); i++) {
            EntityManager.addEntity(new Poison(Util.randomPositionInsideZone()));
        }
    }
}
