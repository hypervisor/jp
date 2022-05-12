package Game;

public abstract class BaseKillstreak {
    private int killsRequired;
    private int currentStreak;

    public BaseKillstreak(int killsRequired) {
        this.killsRequired = killsRequired;
        this.currentStreak = 0;
    }

    protected abstract void onStreakAcquired(BasePlayer player);

    public void incrementStreak() {
        // Legg til et kill i streaken
    }

    public void resetStreak() {
        // Spilleren døde, reset streaken
    }

    public boolean hasKillStreak() {
        // Om spilleren har denne killstreaken
        return false; // TODO!
    }
}
