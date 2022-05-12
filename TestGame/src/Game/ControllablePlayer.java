package Game;

import Engine.EntityManager;
import Engine.Input;
import Engine.Util;
import Engine.Vector2;

import java.awt.*;
import java.awt.event.KeyEvent;

public class ControllablePlayer extends BasePlayer {
    private static final float MOVEMENT_SPEED = 200;
    private static final float AIM_SPEED = 10;

    protected KeyConfig keys;

    public ControllablePlayer(String name, Vector2 position, KeyConfig keys, Color skinColor) {
        super(name, position, skinColor);
        this.keys = keys;
    }

    private void respawn() {
        // Reset values
        this.health = 100;
        this.ammo = 30;
        setPosition(Util.randomPositionInsideZone());

        System.out.println("Player " + name + " respawned");

        World.onRespawn();
    }

    private void handleMovement(Input i, float deltaTime) {
        float speed = MOVEMENT_SPEED * deltaTime;

        Vector2 targetDirection = new Vector2(shootDirection.x, shootDirection.y);

        // Handle horizontal movement
        if (i.getKey(keys.moveLeft)) {
            //this.shootDirection.x = -1;
            targetDirection.x = -1;
            this.position.x -= speed;
        }
        if (i.getKey(keys.moveRight)) {
            //this.shootDirection.x = 1;
            targetDirection.x = 1;
            this.position.x += speed;
        }

        // Handle vertical movement
        if (i.getKey(keys.moveUp)) {
            //this.shootDirection.y = -1;
            targetDirection.y = -1;
            this.position.y -= speed;
        }
        if (i.getKey(keys.moveDown)) {
            //this.shootDirection.y = 1;
            targetDirection.y = 1;
            this.position.y += speed;
        }

        shootDirection = Vector2.moveTowards(shootDirection, targetDirection, AIM_SPEED * deltaTime).getNormalized();
    }

    private void handleShotFired() {
        ammo--;

        Vector2 currentShootDirection = new Vector2(shootDirection.x, shootDirection.y);
        if (currentShootDirection.isZero()) {
            currentShootDirection = Vector2.left();
        }

        Vector2 shootPosition = new Vector2(position.x + chest.x, position.y + chest.y);

        // Spawn projectile
        Projectile p = new Projectile(this, shootPosition, currentShootDirection);

        // Add projectile to entity list
        EntityManager.addEntity(p);
    }

    @Override
    public void update(Input i, float deltaTime) {
        super.update(i, deltaTime);

        // Don't run game logic if player is dead
        if (isDead()) {
            if (i.getKeyDown(KeyEvent.VK_R)) {
                respawn();
                World.spawnBots(5);
            } else {
                return;
            }
        }

        // Handle player movement
        handleMovement(i, deltaTime);

        // Handle shooting
        if (i.getKeyDown(keys.shoot) && !isOutOfAmmo()) {
            handleShotFired();
        }
    }
}
