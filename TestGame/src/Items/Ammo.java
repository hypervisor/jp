package Items;

import Engine.*;
import Game.*;

import java.awt.*;

public class Ammo extends BaseItem {
    public static final int MIN_AMOUNT = 4;
    public static final int MAX_AMOUNT = 8;

    public Ammo(Vector2 position) {
        super(position, "Ammo", Color.darkGray);
    }

    @Override
    public boolean onPickup(BasePlayer player) {
        player.giveAmmo(Util.randomBetween(10, 25));
        return true;
    }
}
