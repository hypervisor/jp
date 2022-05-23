package Killstreaks;

import Engine.Application;
import Engine.Util;
import Engine.Vector2;
import Game.BaseKillstreak;
import Game.BasePlayer;
import Game.Explosion;

public class CarpetBomb extends BaseKillstreak {
    public CarpetBomb(BasePlayer player) {
        super(100, player);
    }

    @Override
    protected void onStreakAcquired() {
        System.out.println("CarpetBomb! " + player.name);
        Application.log("!!!");
        Application.log("PLAYER BOMBED AREA");

        for (int i = 0; i < Util.randomBetween(4, 8); i++) {
            Explosion.triggerExplosion(Util.randomPositionInsideZone(), 500, 250, null, player);
        }

        Application.log("!!!");
    }
}
