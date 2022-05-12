package Game;

import Engine.*;
import Game.*;

import java.awt.*;
import java.util.Collections;

public class Scoreboard extends BaseEntity {
    private static final Color SCOREBOARD_BG_COLOR = new Color(255, 255, 255);
    public static Scoreboard instance;

    public Scoreboard() {
        instance = this;
        this.position = new Vector2(25, 25);
    }

    @Override
    public void update(Input i, float deltaTime) {

    }

    @Override
    public void render(Drawing d) {
        var players = EntityManager.getPlayerList();
        Collections.sort(players);

        //d.fillRect(new Vector2(-25, -25), new Vector2(300, 50 + players.size() * 25), SCOREBOARD_BG_COLOR);
        d.drawRect(new Vector2(-25, -25), new Vector2(300, 50 + players.size() * 25));

        int offset = 0;
        for (BasePlayer player : players) {
            d.drawText(new Vector2(0, offset), player.scoreboardText());
            offset += 25;
        }

        d.drawText(new Vector2(0, offset), "Time: " + Application.time);
    }
}
