package Engine;

import Game.Projectile;

public abstract class BaseEntity implements GameObject {
    public Vector2 position;
    protected float health;
    protected ICollider collider;

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position.x = position.x;
        this.position.y = position.y;
    }

    public boolean checkCollision(Vector2 p) {
        if (collider == null)
            return false;

        return collider.isInsideCollider(p);
    }

    public boolean onProjectileHit(Projectile p) {
        // Do nothing by default, return false to indicate that we don't care about the hit.
        return false;
    }

    public float getHealth() {
        return health;
    }

    public void setHealth(float health) {
        this.health = health;
    }

    public ICollider getCollider() {
        return collider;
    }

    public void setCollider(ICollider collider) {
        this.collider = collider;
    }

    public boolean hasCollider() {
        return collider != null;
    }

    public void takeDamage(float d) {
        health -= d;
    }
    public void giveHealth(float h) {
        // TODO: Make sure health doesn't go above 100!
        health += h;
    }

    public boolean isDead() {
        return health < 1;
    }
}
