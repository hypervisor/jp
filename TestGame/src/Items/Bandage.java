package Items;

import Engine.*;
import Game.*;

import java.awt.*;
import java.util.Random;

public class Bandage extends BaseItem {
    public static final int MIN_AMOUNT = 4;
    public static final int MAX_AMOUNT = 8;

    public Bandage(Vector2 position) {
        super(position, "Bandage", Color.RED);
    }

    @Override
    public boolean onPickup(BasePlayer player) {
        // Can't pick up bandage if player is not hurt
        if (!player.canHeal())
            return false;

        player.giveHealth(Util.randomBetween(10, 50));
        return true;
    }
}
