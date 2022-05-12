package Game;

import Engine.*;

import java.awt.*;
import java.util.ArrayList;

public class AutoPlayer extends BasePlayer {
    private static final boolean SHOW_DEBUG_INFO = false;

    private BasePlayer target;
    private float movementSpeed;
    private float nextShotTime;

    public AutoPlayer(String name, Vector2 position) {
        super(name, position, Color.GREEN);
        setRandomTarget();
        movementSpeed = Util.randomBetween(25, 60);
        nextShotTime = Application.time + 1f;
    }

    public void setRandomTarget() {
        var alivePlayers = new ArrayList<BasePlayer>();
        var players = EntityManager.getPlayerList();
        for (var player : players) {
            if (player.isDead())
                continue;
            alivePlayers.add(player);
        }

        target = alivePlayers.get(Util.randomBetween(0, alivePlayers.size()));
    }

    @Override
    public void update(Input i, float deltaTime) {
        super.update(i, deltaTime);

        if (isDead())
            return;

        if (target.isDead())
            setRandomTarget();

        setPosition(Vector2.moveTowards(position, target.position, movementSpeed * deltaTime));

        if (!isOutOfAmmo() && Application.time >= nextShotTime) {
            ammo--;

            Vector2 shootPosition = new Vector2(position.x + chest.x, position.y + chest.y);
            Vector2 shootDirection = Vector2.difference(shootPosition, target.position).getNormalized();

            // Spawn projectile
            Projectile p = new Projectile(this, shootPosition, shootDirection);

            // Add projectile to entity list
            EntityManager.addEntity(p);

            nextShotTime = Application.time + Util.randomBetween(0.2f, 1.5f);
        }
    }

    @Override
    public void render(Drawing d) {
        if (isDead())
            return;

        super.render(d);



        if (SHOW_DEBUG_INFO)
            d.drawText(new Vector2(0, 25), "Target: " + target.name);
    }
}
