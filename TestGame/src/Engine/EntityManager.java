package Engine;

import Game.BaseEntity;
import Game.BasePlayer;

import java.util.ArrayList;

public class EntityManager {
    private static ArrayList<BasePlayer> playerList = new ArrayList<>();
    private static ArrayList<BasePlayer> playerAddQueue = new ArrayList<>();
    private static ArrayList<BasePlayer> playerRemoveQueue = new ArrayList<>();

    private static ArrayList<BaseEntity> entities = new ArrayList<>();
    private static ArrayList<BaseEntity> entityAddQueue = new ArrayList<>();
    private static ArrayList<BaseEntity> entityRemoveQueue = new ArrayList<>();

    public static ArrayList<BasePlayer> getPlayerList() {
        return playerList;
    }

    public static ArrayList<BaseEntity> getEntities() {
        return entities;
    }

    public static void spawnPlayer(BasePlayer player) {
        playerAddQueue.add(player);
        addEntity(player);
    }

    public static void removePlayer(BasePlayer player) {
        removeEntity(player);
        playerRemoveQueue.add(player);
    }

    public static void addEntity(BaseEntity entity) {
        entityAddQueue.add(entity);
    }

    public static void removeEntity(BaseEntity entity) {
        entityRemoveQueue.add(entity);
    }

    private static void addPending() {
        // Add pending entities
        for (BaseEntity entity : entityAddQueue) {
            entities.add(entity);
        }
        for (BasePlayer player : playerAddQueue) {
            playerList.add(player);
        }

        // Clear pending
        entityAddQueue.clear();
        playerAddQueue.clear();
    }

    private static void removePending() {
        // Remove pending entities
        for (BaseEntity entity : entityRemoveQueue) {
            entities.remove(entity);
        }
        for (BasePlayer player : playerRemoveQueue) {
            playerList.remove(player);
        }

        // Clear pending
        entityRemoveQueue.clear();
        playerRemoveQueue.clear();
    }

    public static void updateEntityList() {
        addPending();
        removePending();
    }
}
