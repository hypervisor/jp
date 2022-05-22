package Items;

import java.awt.*;

import Engine.*;
import Game.*;
import Killstreaks.BulletRain;

public class Poison extends BaseItem {
    public static final int MIN_AMOUNT = 2;
    public static final int MAX_AMOUNT = 5;

    public Poison(Vector2 position) {
        super(position, "Poison", Color.GREEN);
    }

    @Override
    public boolean onPickup(BasePlayer player) {
        player.takeDamage(Util.randomBetween(25, 75));

        // 30% chance for poison to invoke bullet rain
        if (Util.randomChance(30)) {
            BulletRain.invokeRain(player, Util.randomPositionInsideZone(), Util.randomBetween(20, 60));
        }
        Application.log("Player " + player.name + " consumed poison");

        return true;
    }
}
