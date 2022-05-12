package Game;

import Engine.*;
import Game.*;
import Items.*;

public class World {
    public static void spawnBandages() {
        for (int i = 0; i < Util.randomBetween(Bandage.MIN_AMOUNT, Bandage.MAX_AMOUNT); i++) {
            EntityManager.addEntity(new Bandage(Util.randomPosition()));
        }
    }

    public static void spawnAmmo() {
        for (int i = 0; i < Util.randomBetween(Bandage.MIN_AMOUNT, Bandage.MAX_AMOUNT); i++) {
            EntityManager.addEntity(new Ammo(Util.randomPosition()));
        }
    }

    public static void spawnPoison() {
        for (int i = 0; i < Util.randomBetween(Poison.MIN_AMOUNT, Poison.MAX_AMOUNT); i++) {
            EntityManager.addEntity(new Poison(Util.randomPositionInsideZone()));
        }
    }

    public static void spawnSuperBandage(){
        EntityManager.addEntity(new SuperBandage(Util.randomPosition()));
    }
}
