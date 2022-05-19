package Items;

import Engine.Application;
import Engine.Vector2;
import Game.BaseItem;
import Game.BasePlayer;
import Game.Explosion;

import java.awt.*;

public class LandMine extends BaseItem {

    public LandMine(Vector2 position) {
        super(position, "Landmine", Color.BLACK);
    }

    public void explodeLandMine() {
        Explosion.triggerExplosion(position, 100, 10, this);
    }

    @Override
    public boolean onPickup(BasePlayer player) {
        Application.log("Player " + player.name + " stepped on a landmine");
        explodeLandMine();

        return true;
    }
}
