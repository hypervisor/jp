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

    public static void spawnLandMine() {
        EntityManager.addEntity(new LandMine(Util.randomPositionInsideZone()));
    }

    public static void spawnSuperBandage(){
        EntityManager.addEntity(new SuperBandage(Util.randomPosition()));
    }

    public static void onRespawn() {
        if (Util.randomChance(40)) {
            EntityManager.addEntity(new Bandage(Util.randomPosition()));
        }

        if (Util.randomChance(40)) {
            EntityManager.addEntity(new Ammo(Util.randomPosition()));
        }

        if (Util.randomChance(40)) {
            EntityManager.addEntity(new LandMine(Util.randomPositionInsideZone()));
        }

        if (Util.randomChance(60)) {
            EntityManager.addEntity(new Poison(Util.randomPositionInsideZone()));
        }

        if (Util.randomChance(15)) {
            EntityManager.addEntity(new SuperBandage(Util.randomPosition()));
        }
    }
}
