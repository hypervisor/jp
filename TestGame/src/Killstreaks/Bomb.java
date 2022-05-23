package Killstreaks;

import Engine.Application;
import Engine.Vector2;
import Game.BaseKillstreak;
import Game.BasePlayer;
import Game.Explosion;

public class Bomb extends BaseKillstreak {
    public Bomb(BasePlayer player) {
        super(8, player);
    }

    @Override
    protected void onStreakAcquired() {
        System.out.println("Bomb!");
        Application.log("!!!");
        Application.log("PLAYER BOMBED AREA");

        Vector2 explosionPosition = new Vector2(player.position.x, player.position.y);
        Explosion.triggerExplosion(explosionPosition, 500, 250, null, player);

        Application.log("!!!");
    }
}
