package Killstreaks;

import Engine.Application;
import Engine.EntityManager;
import Engine.Vector2;
import Game.BaseKillstreak;
import Game.BasePlayer;
import Game.Explosion;
import Game.Projectile;

public class Martyrdom extends BaseKillstreak {
    public Martyrdom(BasePlayer player) {
        super(6, player);
    }

    @Override
    protected void onStreakAcquired() {

    }
}
