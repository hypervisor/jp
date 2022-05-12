package Items;

import Engine.Vector2;
import Game.BaseItem;
import Game.BasePlayer;
import Game.Explosion;

import java.awt.*;

public class LandMine extends BaseItem {

    public LandMine(Vector2 position) {
        super(position, "Landmine", Color.BLACK);
    }
    @Override
    public boolean onPickup(BasePlayer player) {
        System.out.println("Player " + player.name + " stepped on a landmine");

        Explosion.triggerExplosion(position, 100, 10);
        return true;
    }
}
