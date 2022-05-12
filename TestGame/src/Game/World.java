package Game;

import Engine.*;
import Game.*;
import Items.*;

public class World {
    public static void setup() {
        EntityManager.addEntity(new SafeZone());

        EntityManager.spawnPlayer(new WasdPlayer("Adrian", Util.randomPositionInsideZone()));
        EntityManager.spawnPlayer(new ArrowPlayer("William", Util.randomPositionInsideZone()));
        spawnBots(1000);

        World.spawnBarrels();
        World.spawnBandages();
        World.spawnAmmo();
        World.spawnPoison();
        World.spawnSuperBandage();
        World.spawnLandMine();

        EntityManager.addEntity(new Scoreboard());
    }

    public static void spawnBots(int count) {
        for (int i = 0; i < count; i++) {
            EntityManager.spawnPlayer(new AutoPlayer("Bot " + i, Util.randomPositionInsideZone()));
        }
    }

    public static void spawnBarrels() {
        for (int i = 0; i < Util.randomBetween(Barrel.MIN_AMOUNT, Barrel.MAX_AMOUNT); i++) {
            EntityManager.addEntity(new Barrel(Util.randomPosition()));
        }
    }

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
        EntityManager.addEntity(new LandMine(Util.randomPositionInsideZone()));
        EntityManager.addEntity(new LandMine(Util.randomPositionInsideZone()));
        EntityManager.addEntity(new LandMine(Util.randomPositionInsideZone()));
        EntityManager.addEntity(new LandMine(Util.randomPositionInsideZone()));

    }

    public static void spawnSuperBandage(){
        EntityManager.addEntity(new SuperBandage(Util.randomPosition()));
    }

    public static void onRespawn() {
        SafeZone.instance.onRespawn();

        if (Util.randomChance(15)) {
            EntityManager.addEntity(new Barrel(Util.randomPositionInsideZone()));
        }

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
