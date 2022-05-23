package Killstreaks;

import Engine.Application;
import Engine.EntityManager;
import Engine.Vector2;
import Game.BaseKillstreak;
import Game.BasePlayer;
import Game.Explosion;
import Game.Projectile;

public class SuperSneakers extends BaseKillstreak {
    public SuperSneakers(BasePlayer player) {
        super(16, player);
    }

    @Override
    protected void onStreakAcquired() {

    }
}
