package Engine;

import Game.BaseEntity;
import Game.BasePlayer;

import java.util.ArrayList;

public class EntityManager {
    private static ArrayList<BasePlayer> playerList = new ArrayList<>();
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
        playerList.add(player);
        addEntity(player);
    }

    public static void addEntity(BaseEntity entity) {
        entityAddQueue.add(entity);
    }

    public static void removeEntity(BaseEntity entity) {
        entityRemoveQueue.add(entity);
    }

    private static void addPendingEntities() {
        // Add pending entities
        for (BaseEntity entity : entityAddQueue) {
            entities.add(entity);
        }

        // Clear pending
        entityAddQueue.clear();
    }

    private static void removePendingEntities() {
        // Remove pending entities
        for (BaseEntity entity : entityRemoveQueue) {
            entities.remove(entity);
        }

        // Clear pending
        entityRemoveQueue.clear();
    }

    public static void updateEntityList() {
        addPendingEntities();
        removePendingEntities();
    }
}
