package Killstreaks;

import Engine.Application;
import Engine.Vector2;
import Game.BaseKillstreak;
import Game.BasePlayer;
import Game.Explosion;

public class WeakNuke extends BaseKillstreak {
    public WeakNuke(BasePlayer player) {
        super(5, player);
    }

    @Override
    protected void onStreakAcquired() {
        Application.log("!!!");
        Application.log("PLAYER NUKED AREA");

        Vector2 explosionPosition = new Vector2(player.position.x, player.position.y);
        Explosion.triggerExplosion(explosionPosition, 600, 2.0f, null, player);

        Application.log("!!!");
    }
}
