package Killstreaks;

import Game.BaseKillstreak;
import Game.BasePlayer;

public class FMJAmmo extends BaseKillstreak {
    public FMJAmmo(BasePlayer player) {
        super(4, player);
    }

    @Override
    protected void onStreakAcquired() {

    }
}
