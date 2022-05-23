package Killstreaks;

import Engine.Application;
import Engine.EntityManager;
import Engine.Vector2;
import Game.BaseKillstreak;
import Game.BasePlayer;
import Game.Explosion;
import Game.Projectile;

public class Sneakers extends BaseKillstreak {
    public Sneakers(BasePlayer player) {
        super(10, player);
    }

    @Override
    protected void onStreakAcquired() {

    }
}
