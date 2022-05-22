package Items;

import Engine.*;
import Game.*;

import java.awt.*;

public class SuperAmmo extends BaseItem {
    public static final int MIN_AMOUNT = 4;
    public static final int MAX_AMOUNT = 8;

    private int containingAmmo;

    public SuperAmmo(Vector2 position) {
        super(position, "Large Ammo Box", Color.darkGray);

        containingAmmo = Util.randomBetween(40, 60);
    }

    @Override
    public boolean onPickup(BasePlayer player) {
        player.giveAmmo(containingAmmo);
        return true;
    }

    @Override
    public void render(Drawing d) {
        d.fillCircle(Vector2.zero(), 25, displayColor);
        d.drawText(Vector2.zero(), displayText + " (" + containingAmmo + ")");
    }
}
