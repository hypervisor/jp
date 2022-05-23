package Game;

import Engine.CircleCollider;
import Engine.Vector2;

public class Subrock {
    public Vector2 position;
    public float size;

    public Subrock(Vector2 position, float size) {
        this.position = position;
        this.size = size;
    }

    public Vector2 globalPosition(Vector2 center) {
        return new Vector2(center.x + position.x + size, center.y + position.y + size);
    }
}
