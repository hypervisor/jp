package Killstreaks;

import Game.BaseKillstreak;
import Game.BasePlayer;

public class GasMask extends BaseKillstreak {
    public GasMask(BasePlayer player) {
        super(3, player);
    }
    @Override
    protected void onStreakAcquired() {

    }
}