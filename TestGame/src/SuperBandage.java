import java.awt.*;

public class SuperBandage extends BaseItem{

    public SuperBandage(Vector2 position) {
        this.displayText = "Super Bandage";
        this.displayColor = Color.MAGENTA;
        this.position = position;
    }

    @Override
    public boolean onPickup(BasePlayer player) {
        // Can't pick up bandage if player is not hurt
        if (player.health > 99)
            return false;

        player.giveHealth(100);
        return true;
    }
}
