import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;

public class BasePlayer extends BaseEntity {
    private static final float MOVEMENT_SPEED = 200;
    private static final float AIM_SPEED = 10;
    private static final boolean DEBUG_MODE = false;

    public String name;
    private float playerSize;
    private KeyConfig keys;
    private int ammo;
    private Color skinColor;
    private Vector2 shootDirection;
    private int kills;
    private int deaths;

    private float headSize;
    private Vector2 neck;
    private Vector2 chest;
    private Vector2 leftArm;
    private Vector2 rightArm;
    private Vector2 cock;
    private Vector2 leftFoot;
    private Vector2 rightFoot;

    public BasePlayer(String name, Vector2 position, KeyConfig keys, Color skinColor) {
        this.name = name;
        this.health = 100f;
        this.playerSize = 0.5f;
        this.keys = keys;
        this.ammo = 20;
        this.skinColor = skinColor;
        this.shootDirection = new Vector2(0, 0);
        this.position = position;
        this.collider = new BoxCollider(position, new Vector2(50 * playerSize, 165 * playerSize));

        headSize = 50 * playerSize;
        neck = new Vector2(headSize / 2, headSize);
        chest = new Vector2(neck.x, neck.y + (20 * playerSize));
        leftArm = new Vector2(chest.x - (25 * playerSize), chest.y + (5 * playerSize));
        rightArm = new Vector2(chest.x + (25 * playerSize), chest.y + (5 * playerSize));
        cock = new Vector2(neck.x, neck.y + (75 * playerSize));
        leftFoot = new Vector2(cock.x - (20 * playerSize), cock.y + (40 * playerSize));
        rightFoot = new Vector2(cock.x + (20 * playerSize), cock.y + (40 * playerSize));
    }

    public Vector2 topLeft() {
        return this.position;
    }

    public Vector2 topRight() {
        return new Vector2(position.x + (50 * playerSize), position.y);
    }

    public Vector2 bottomLeft() {
        return new Vector2(position.x, position.y + (165 * playerSize));
    }

    public Vector2 bottomRight() {
        return new Vector2(position.x + (50 * playerSize), position.y + (165 * playerSize));
    }

    public Vector2 middle() {
        return new Vector2(position.x + ((50 * playerSize) / 2), position.y + ((165 * playerSize) / 2));
    }

    private boolean isOutOfAmmo() {
        return ammo == 0;
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

        shootDirection = Vector2.lerp(shootDirection, targetDirection, AIM_SPEED * deltaTime).getNormalized();
    }

    public void addDeath() {
        deaths++;
    }

    public void addKill() {
        kills++;
    }

    public float killDeathRatio() {
        if (deaths == 0)
            return kills;

        return (float)kills / (float)deaths;
    }

    public String scoreboardText() {
        return name + ": " + kills + " kills, " + deaths + " deaths (" + killDeathRatio() + " K/D)";
    }

    public void giveAmmo(int ammo) {
        this.ammo += ammo;
    }

    private void respawn() {
        // Reset values
        this.health = 100;
        this.ammo = 30;
        setPosition(Util.randomPositionInsideZone());
        addDeath(); // TODO: Make a onDeath() method called from takeDamage if isDead() and addDeath() there instead.

        // NOTE: Respawning breaks colliders or some shit
        System.out.println("Player " + name + " respawned");
    }

    @Override
    public void update(Input i, float deltaTime) {
        // Don't run game logic if player is dead
        if (isDead()) {
            if (i.getKeyDown(KeyEvent.VK_R)) {
                respawn();
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

    @Override
    public void render(Drawing d) {
        if (isDead()) {
            d.drawText(Vector2.zero(), name + " is DEAD!", Color.RED);
        } else {
            d.drawText(Vector2.zero(), name + " (" + (int)health + "HP, " + ammo + " bullets)");
            d.drawCircle(Vector2.zero(), headSize, skinColor);

            d.drawLine(neck, cock, skinColor);
            d.drawLine(chest, leftArm, skinColor);
            d.drawLine(chest, rightArm, skinColor);
            d.drawLine(cock, leftFoot, skinColor);
            d.drawLine(cock, rightFoot, skinColor);
            d.drawLine(chest, new Vector2(chest.x + shootDirection.x * 20, chest.y + shootDirection.y * 20), Color.BLACK);

            if (DEBUG_MODE) {
                d.drawRect(Vector2.zero(), ((BoxCollider)this.collider).size, Color.RED);
            }
        }
    }
}
