package Items;

import java.awt.*;

import Engine.*;
import Game.*;

public class Poison extends BaseItem {
    public static final int MIN_AMOUNT = 2;
    public static final int MAX_AMOUNT = 5;

    public Poison(Vector2 position) {
        this.displayText = "Poison";
        this.displayColor = Color.GREEN;
        this.position = position;
    }

    @Override
    public boolean onPickup(BasePlayer player) {
        player.takeDamage(Util.randomBetween(20,40));
        return true;
    }
}
