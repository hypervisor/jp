package Game;

import Engine.*;
import Game.*;
import Items.*;

public class World {
    private static final int SPAWN_DENSITY = 2;
    private static int botCount = 0;

    public static void setup() {
        EntityManager.addEntity(new SafeZone());

        EntityManager.spawnPlayer(new WasdPlayer("Adrian", Util.randomPositionInsideZone()));
        //EntityManager.spawnPlayer(new ArrowPlayer("William", Util.randomPositionInsideZone()));
        spawnBots(10);

        World.spawnBarrels();
        World.spawnBandages();
        World.spawnAmmo();
        World.spawnPoison();
        World.spawnSuperBandage();
        World.spawnLandMine();
        World.spawnBoosters();

        EntityManager.addEntity(new Scoreboard());
    }

    public static void spawnBots(int count, AutoPlayer mutationSource) {
        for (int i = 0; i < count; i++) {
            EntityManager.spawnPlayer(new AutoPlayer("Bot " + (botCount + i), Util.randomPositionInsideZone(), mutationSource));
        }

        botCount += count;
    }

    public static void spawnBots(int count) {
        spawnBots(count, null);
    }

    public static void spawnBarrels() {
        for (int i = 0; i < Util.randomBetween(Barrel.MIN_AMOUNT, Barrel.MAX_AMOUNT); i++) {
            EntityManager.addEntity(new Barrel(Util.randomPosition()));
        }
    }

    public static void spawnBoosters() {
        for (int i = 0; i < Util.randomBetween(Booster.MIN_AMOUNT, Booster.MAX_AMOUNT); i++) {
            EntityManager.addEntity(new Booster(Util.randomPosition()));
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

        World.spawnBots(Util.randomBetween(1, 3));

        for (int i = 0; i < SPAWN_DENSITY; i++) {
            if (Util.randomChance(70)) {
                EntityManager.addEntity(new Ammo(Util.randomPosition()));
                EntityManager.addEntity(new Bandage(Util.randomPosition()));
            }

            if (Util.randomChance(60)) {
                EntityManager.addEntity(new Ammo(Util.randomPositionInsideZone()));
                EntityManager.addEntity(new Bandage(Util.randomPositionInsideZone()));
            }

            if (Util.randomChance(15)) {
                EntityManager.addEntity(new Barrel(Util.randomPositionInsideZone()));
            }

            if (Util.randomChance(30)) {
                EntityManager.addEntity(new LandMine(Util.randomPositionInsideZone()));
            }

            if (Util.randomChance(60)) {
                EntityManager.addEntity(new Poison(Util.randomPositionInsideZone()));
            }

            if (Util.randomChance(10)) {
                EntityManager.addEntity(new Booster(Util.randomPosition()));
            }

            if (Util.randomChance(5)) {
                EntityManager.addEntity(new SuperBandage(Util.randomPosition()));
            }
        }
    }
}
