package Game;

public abstract class BaseKillstreak {
    private int killsRequired;
    private int currentStreak;
    private BasePlayer player;

    public BaseKillstreak(int killsRequired, BasePlayer player) {
        this.killsRequired = killsRequired;
        this.player = player;
        this.currentStreak = 0;
    }

    protected abstract void onStreakAcquired();

    public void incrementStreak() {
        // Legg til et kill i streaken
        currentStreak += 1;
        if(currentStreak == killsRequired) {
            onStreakAcquired();
        }
    }

    public void resetStreak() {
        // Spilleren døde, reset streaken
        currentStreak = 0;
    }

    public boolean hasKillStreak() {
        // Om spilleren har denne killstreaken
        return currentStreak >= killsRequired;
    }
}
