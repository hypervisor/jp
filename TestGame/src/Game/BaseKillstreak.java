package Game;

public abstract class BaseKillstreak {
    private int killsRequired;
    protected BasePlayer player;

    public BaseKillstreak(int killsRequired, BasePlayer player) {
        this.killsRequired = killsRequired;
        this.player = player;
    }

    // Kallet når man får streaken
    protected abstract void onStreakAcquired();

    public void incrementStreak() {
        // Sjekk om vi har fått streaken, og kall onStreakAcquired med en gang vi har.
        if(player.streaks.currentStreak == killsRequired) {
            onStreakAcquired();
        }
    }
    public boolean hasKillStreak() {
        // Om spilleren har denne killstreaken
        return player.streaks.currentStreak >= killsRequired;
    }
}
