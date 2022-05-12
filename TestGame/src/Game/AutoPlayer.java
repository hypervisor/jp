package Game;

import Engine.EntityManager;
import Engine.Input;
import Engine.Util;
import Engine.Vector2;

import java.awt.*;

public class AutoPlayer extends BasePlayer {
    private BasePlayer target;
    private float movementSpeed;

    public AutoPlayer(String name, Vector2 position) {
        super(name, position, Color.GREEN);
        setRandomTarget();
        movementSpeed = Util.randomBetween(5, 7);
    }

    public void setRandomTarget() {
        var players = EntityManager.getPlayerList();
        target = players.get(Util.randomBetween(0, players.size()));
    }

    @Override
    public void update(Input i, float deltaTime) {
        super.update(i, deltaTime);

        if (isDead())
            return;

        position = Vector2.moveTowards(position, target.position, movementSpeed * deltaTime);
    }
}
