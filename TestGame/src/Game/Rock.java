package Game;

import Engine.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Rock extends BaseEntity implements ICollider {
    public static final int MIN_AMOUNT = 7;
    public static final int MAX_AMOUNT = 17;

    private static final float SUBROCK_MIN_SIZE = 10f;
    private static final float SUBROCK_MAX_SIZE = 75f;
    private static final Color ROCK_COLOR = new Color(128, 120, 120);
    private static final Color ROCK_OUTLINE_COLOR = new Color(108, 100, 100);
    private static final float OUTLINE_SIZE = 1f;

    private List<Subrock> subrocks;

    public Rock(Vector2 position) {
        this.position = position;
        this.collider = this;
        this.health = 100;
        subrocks = new ArrayList<>();

        for (int i = 0; i < Util.randomBetween(2, 5); i++) {
            Vector2 offsetPosition = new Vector2(Util.randomBetween(SUBROCK_MIN_SIZE, SUBROCK_MAX_SIZE), Util.randomBetween(SUBROCK_MIN_SIZE, SUBROCK_MAX_SIZE));
            Subrock subrock = new Subrock(offsetPosition, Util.randomBetween(SUBROCK_MIN_SIZE, SUBROCK_MAX_SIZE));

            subrocks.add(subrock);
        }
    }

    @Override
    public boolean onProjectileHit(Projectile p) {
        return true;
    }

    @Override
    public void update(Input i, float deltaTime) {

    }

    @Override
    public void render(Drawing d) {
        for (Subrock r : subrocks) {
            d.fillCircle(new Vector2(r.position.x - OUTLINE_SIZE, r.position.y - OUTLINE_SIZE), OUTLINE_SIZE * 2 + r.size * 2, ROCK_OUTLINE_COLOR);
            d.fillCircle(r.position, r.size * 2, ROCK_COLOR);
        }
    }

    public boolean isInsideCollider(Vector2 p) {
        for (Subrock r : subrocks) {
            if (Vector2.distance(r.globalPosition(position), p) <= r.size) {
                return true;
            }
        }

        return false;
    }
}
