package Engine;

public class Vector2 {
    public float x;
    public float y;

    public Vector2() {
        this.x = 0;
        this.y = 0;
    }

    public Vector2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vector2 add(Vector2 b) {
        return new Vector2(x + b.x, y + b.y);
    }

    public Vector2 scale(float s) {
        return new Vector2(x * s, y * s);
    }

    public Vector2 divide(float s) {
        return new Vector2(x / s, y / s);
    }

    public int getX() {
        return (int)x;
    }

    public int getY() {
        return (int)y;
    }

    public float magnitude() {
        return (float)Math.sqrt((x * x) + (y * y));
    }

    public static Vector2 difference(Vector2 a, Vector2 b) {
        return new Vector2(b.x - a.x, b.y - a.y);
    }

    public static float distance(Vector2 a, Vector2 b) {
        Vector2 diff = difference(a, b);
        return (float)Math.sqrt((diff.x * diff.x) + (diff.y * diff.y));
    }

    public static Vector2 lerp(Vector2 a, Vector2 b, float t) {
        return new Vector2(a.x + ((b.x - a.x) * t), a.y + ((b.y - a.y) * t));
    }

    public static Vector2 moveTowards(Vector2 current, Vector2 target, float maxDistanceDelta) {
        Vector2 diff = difference(current, target);
        float magnitude = diff.magnitude();
        if (magnitude <= maxDistanceDelta || magnitude == 0f) {
            return target;
        }

        //return current + diff / magnitude * maxDistanceDelta;
        return current.add(diff.getNormalized().scale(maxDistanceDelta));
    }

    public Vector2 getNormalized() {
        //float length = x * x + y * y;
        float length = magnitude();
        if (length == 0f)
            return zero();

        return new Vector2(x / length, y / length);
    }

    public void normalize() {
        float length = x * x + y * y;
        if (length == 0f)
            return;

        x /= length;
        y /= length;
    }

    public boolean isZero() {
        return x == 0f && y == 0f;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    @Override
    public boolean equals(Object b) {
        if (b == this)
            return true;

        if (!(b instanceof Vector2)) {
            return false;
        }

        Vector2 bv = (Vector2)b;
        return bv.x == x && bv.y == y;
    }

    public static Vector2 zero() { return new Vector2(0, 0); }

    // Directions
    public static Vector2 left() { return new Vector2(-1, 0); }
    public static Vector2 right() { return new Vector2(1, 0); }
    public static Vector2 up() { return new Vector2(0, -1); }
    public static Vector2 down() { return new Vector2(0, 1); }
}
