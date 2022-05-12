public class BoxCollider implements ICollider {
    public Vector2 position;
    public Vector2 size;

    public BoxCollider(Vector2 position, Vector2 size) {
        this.position = position;
        this.size = size;
    }

    public Vector2 getEndPosition() {
        return new Vector2(position.x + size.x, position.y + size.y);
    }

    @Override
    public boolean isInsideCollider(Vector2 p) {
        Vector2 end = getEndPosition();

        if (p.x < position.x)
            return false;

        if (p.x >= end.x)
            return false;

        if (p.y < position.y)
            return false;

        if (p.y >= end.y)
            return false;

        return true;
    }
}
