package Killstreaks;

import Engine.Application;
import Engine.Util;
import Engine.Vector2;
import Game.BaseKillstreak;
import Game.BasePlayer;
import Game.Explosion;

public class CarpetGunner extends BaseKillstreak {
    public CarpetGunner(BasePlayer player) {
        super(250, player);
    }

    @Override
    protected void onStreakAcquired() {
        System.out.println("CarpetGunner! " + player.name);

        for (int i = 0; i < Util.randomBetween(4, 8); i++) {
            BulletRain.invokeRain(player, Util.randomPositionInsideZone(), 50);
        }
    }
}
