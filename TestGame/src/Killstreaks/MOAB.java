package Killstreaks;

import Engine.Application;
import Engine.Vector2;
import Game.BaseKillstreak;
import Game.BasePlayer;
import Game.Explosion;
import Game.SafeZone;

public class MOAB extends BaseKillstreak {
    public MOAB(BasePlayer player) {
        super(400, player);
    }

    @Override
    protected void onStreakAcquired() {
        System.out.println("MOAB! " + player.name);

        Explosion.triggerExplosion(SafeZone.instance.getZonePosition(), 750, 250, null, player);

        Application.log("!!!");
    }
}
