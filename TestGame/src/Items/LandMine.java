package Items;

import Engine.Vector2;
import Game.BaseItem;
import Game.BasePlayer;

import java.awt.*;

public class LandMine extends BaseItem {

    public LandMine(Vector2 position) {
        super(position, "Landmine", Color.BLACK);
    }
    @Override
    public boolean onPickup(BasePlayer player) {
        player.takeDamage(100);
        System.out.println("Player " + player.name + " stepped on a landmine");
        return true;
    }
}
