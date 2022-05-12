package Items;

import java.awt.*;

import Engine.*;
import Game.*;

public class Poison extends BaseItem {
    public static final int MIN_AMOUNT = 2;
    public static final int MAX_AMOUNT = 5;

    public Poison(Vector2 position) {
        super(position, "Poison", Color.GREEN);
    }

    @Override
    public boolean onPickup(BasePlayer player) {
        player.takeDamage(Util.randomBetween(20,40));
        System.out.println("Player " + player.name + " consumed poison");

        return true;
    }
}
