package Game;

import Engine.*;
import Items.Booster;
import Killstreaks.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public abstract class BasePlayer extends BaseEntity implements Comparable<BasePlayer> {
    private static final boolean DEBUG_MODE = false;

    public String name;
    protected float playerSize;
    protected int ammo;
    protected Color skinColor;
    protected Vector2 shootDirection;
    protected int kills;
    protected int deaths;
    public int currentStreak;
    private float boosterTime;
    private boolean hasBooster;

    public GasMask gasMask;
    public FMJAmmo fmjAmmo;
    public Bomb bomb;
    public WeakNuke nuke;
    public Turret turret;

    public ArrayList<AutoTurret> turrets;

    public float headSize;
    public Vector2 neck;
    public Vector2 chest;
    public Vector2 leftArm;
    public Vector2 rightArm;
    public Vector2 cock;
    public Vector2 leftFoot;
    public Vector2 rightFoot;

    public BasePlayer(String name, Vector2 position, Color skinColor) {
        this.name = name;
        this.health = 100f;
        this.playerSize = 0.5f;
        this.ammo = 24;
        this.skinColor = skinColor;
        this.shootDirection = new Vector2(0, 0);
        this.position = position;
        this.collider = new BoxCollider(position, new Vector2(50 * playerSize, 165 * playerSize));
        this.gasMask = new GasMask(this);
        this.fmjAmmo = new FMJAmmo(this);
        this.bomb = new Bomb(this);
        this.nuke = new WeakNuke(this);
        this.turret = new Turret(this);
        this.kills = 0;
        this.deaths = 0;
        this.currentStreak = 0;
        this.turrets = new ArrayList<>();

        headSize = 50 * playerSize;
        neck = new Vector2(headSize / 2, headSize);
        chest = new Vector2(neck.x, neck.y + (20 * playerSize));
        leftArm = new Vector2(chest.x - (25 * playerSize), chest.y + (5 * playerSize));
        rightArm = new Vector2(chest.x + (25 * playerSize), chest.y + (5 * playerSize));
        cock = new Vector2(neck.x, neck.y + (75 * playerSize));
        leftFoot = new Vector2(cock.x - (20 * playerSize), cock.y + (40 * playerSize));
        rightFoot = new Vector2(cock.x + (20 * playerSize), cock.y + (40 * playerSize));
    }

    public void onDied() {
        for (AutoTurret t : turrets) {
            EntityManager.removeEntity(t);
        }
        turrets.clear();

        addDeath();
    }

    public void giveBooster() {
        boosterTime = Application.time + Booster.BOOSTER_DURATION;
        hasBooster = true;
    }

    public Vector2 getChest() {
        return new Vector2(position.x + chest.x, position.y + chest.y);
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

    public boolean isOutOfAmmo() {
        return ammo == 0;
    }

    public boolean canHeal() {
        return health < 100f;
    }

    @Override
    public boolean onProjectileHit(Projectile p) {
        // We shouldn't be able to hit ourselves!
        if (this.equals(p.attacker))
            return false;

        float damage = (float)Util.randomBetween(5, 25);

        // If attacker has FMJ killstream, increase damage by 100%
        if (p.attacker.fmjAmmo.hasKillStreak())
            damage *= 2;

        // Projectile hit a player!
        this.takeDamage(damage);
        //Application.log("Hit player " + this.name + " for " + damage + " (" + this.getHealth() + ")");

        // Check if this hit killed the player
        if (this.isDead()) {
            p.attacker.addKill();
            onDied();

            Application.log("Player " + this.name + " was killed by " + p.attacker.name);
        }
        return true;
    }

    public void addDeath() {
        currentStreak = 0;
        deaths++;
    }

    public void addKill() {
        currentStreak++;
        gasMask.incrementStreak();
        fmjAmmo.incrementStreak();
        bomb.incrementStreak();
        nuke.incrementStreak();
        turret.incrementStreak();
        kills++;
    }

    public float killDeathRatio() {
        if (deaths == 0)
            return kills;

        return (float)kills / (float)deaths;
    }

    public String scoreboardText() {
        return name + ": " + kills + " " + Util.pluralizeWord("kill", kills) + ", " + deaths + " " + Util.pluralizeWord("death", deaths) + " (" + killDeathRatio() + " K/D)";
    }

    public void giveAmmo(int ammo) {
        this.ammo += ammo;
    }

    @Override
    public void update(Input i, float deltaTime) {
        // Run logic here

        if (hasBooster) {
            if (Application.time >= boosterTime) {
                hasBooster = false;
            } else {
                giveHealth((100 / Booster.BOOSTER_DURATION) * deltaTime);
            }
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

    @Override
    public int compareTo(BasePlayer o) {
        if (killDeathRatio() == o.killDeathRatio())
            return 0;
        return killDeathRatio() > o.killDeathRatio() ? -1 : 1;
    }
}
