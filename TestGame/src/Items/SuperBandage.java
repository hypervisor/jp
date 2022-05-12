package Items;

import java.awt.*;

import Engine.*;
import Game.*;

public class SuperBandage extends BaseItem {

    public SuperBandage(Vector2 position) {
        super(position, "Super Bandage", Color.MAGENTA);
    }

    @Override
    public boolean onPickup(BasePlayer player) {
        // Can't pick up bandage if player is not hurt
        if (!player.canHeal())
            return false;

        player.giveHealth(100);
        return true;
    }
}
