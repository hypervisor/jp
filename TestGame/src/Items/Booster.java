package Items;

import Engine.Vector2;
import Game.BaseItem;
import Game.BasePlayer;

import java.awt.*;

public class Booster extends BaseItem {
    public static float BOOSTER_DURATION = 10f;
    public static final int MIN_AMOUNT = 2;
    public static final int MAX_AMOUNT = 4;

    public Booster(Vector2 position) {
        super(position, "Booster", Color.CYAN);
    }

    @Override
    public boolean onPickup(BasePlayer player) {
        player.giveBooster();
        return true;
    }
}
