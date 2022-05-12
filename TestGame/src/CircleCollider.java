public class CircleCollider implements ICollider {
    public Vector2 position;
    public float radius;

    public CircleCollider(Vector2 position, float radius) {
        this.position = position;
        this.radius = radius;
    }

    @Override
    public boolean isInsideCollider(Vector2 p) {
        return Vector2.distance(position, p) <= radius;
    }
}
