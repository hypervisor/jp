package Game;

import Killstreaks.*;

public class StreakManager {
    public Bomb bomb;
    public BulletRain bulletRain;
    public FMJAmmo fmjAmmo;
    public GasMask gasMask;
    public Turret turret;
    public WeakNuke weakNuke;
    public Martyrdom martyrdom;
    public Sneakers sneakers;
    public SuperSneakers superSneakers;
    public CarpetBomb carpetBomb;
    public CarpetGunner carpetGunner;
    public MOAB moab;

    public int currentStreak;

    public StreakManager(BasePlayer player) {
        bomb = new Bomb(player);
        bulletRain = new BulletRain(player);
        fmjAmmo = new FMJAmmo(player);
        gasMask = new GasMask(player);
        weakNuke = new WeakNuke(player);
        turret = new Turret(player);
        martyrdom = new Martyrdom(player);
        sneakers = new Sneakers(player);
        superSneakers = new SuperSneakers(player);
        carpetBomb = new CarpetBomb(player);
        carpetGunner = new CarpetGunner(player);
        moab = new MOAB(player);

        resetStreak();
    }

    public void resetStreak() {
        currentStreak = 0;
    }

    public void incrementStreak() {
        currentStreak++;

        gasMask.incrementStreak();
        bulletRain.incrementStreak();
        fmjAmmo.incrementStreak();
        bomb.incrementStreak();
        weakNuke.incrementStreak();
        turret.incrementStreak();
        martyrdom.incrementStreak();
        sneakers.incrementStreak();
        superSneakers.incrementStreak();
        carpetBomb.incrementStreak();
        carpetGunner.incrementStreak();
        moab.incrementStreak();
    }
}
