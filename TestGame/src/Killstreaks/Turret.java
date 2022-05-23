package Killstreaks;

import Engine.EntityManager;
import Game.AutoTurret;
import Game.BaseKillstreak;
import Game.BasePlayer;

public class Turret extends BaseKillstreak {
    public Turret(BasePlayer player) {
        super(14, player);
    }

    @Override
    protected void onStreakAcquired() {
        AutoTurret t = new AutoTurret(player);
        player.turrets.add(t);
        EntityManager.addEntity(t);
    }
}
