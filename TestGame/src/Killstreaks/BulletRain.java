package Killstreaks;

import Engine.Application;
import Engine.EntityManager;
import Engine.Util;
import Engine.Vector2;
import Game.BaseKillstreak;
import Game.BasePlayer;
import Game.Explosion;
import Game.Projectile;

public class BulletRain extends BaseKillstreak {
    private static final int BULLETS_FIRED = 150;

    public BulletRain(BasePlayer player) {
        super(15, player);
    }

    public static void invokeRain(BasePlayer fromPlayer, Vector2 shootPosition, int bullets) {
        for (int i = 0; i < bullets; i++) {
            double angle = i * (360f / bullets);

            Vector2 shootDirection = new Vector2((float)Math.cos(angle), (float)Math.sin(angle));

            // Spawn projectile
            Projectile p = new Projectile(fromPlayer, shootPosition, shootDirection);

            // Add projectile to entity list
            EntityManager.addEntity(p);
        }
    }

    @Override
    protected void onStreakAcquired() {
        invokeRain(player, player.getPosition(), BULLETS_FIRED);
    }
}
