package Game;

import Engine.*;

import java.awt.*;
import java.util.Collections;

public class Scoreboard extends BaseEntity {
    private static final Color SCOREBOARD_BG_COLOR = new Color(255, 255, 255);
    private static final int SCOREBOARD_SIZE = 10;
    public static Scoreboard instance;
    private BasePlayer[] topPlayers;

    public Scoreboard() {
        instance = this;
        this.position = new Vector2(25, 25);
        topPlayers = new BasePlayer[SCOREBOARD_SIZE];
    }

    @Override
    public void update(Input i, float deltaTime) {
        var players = EntityManager.getPlayerList();
        Collections.sort(players);

        for (int j = 0; j < SCOREBOARD_SIZE; j++) {
            topPlayers[j] = players.get(j);
        }
    }

    @Override
    public void render(Drawing d) {
        d.setCameraRelative(false);

        //d.fillRect(new Vector2(-25, -25), new Vector2(300, 50 + players.size() * 25), SCOREBOARD_BG_COLOR);
        //d.drawRect(new Vector2(-25, -25), new Vector2(300, 50 + players.size() * 25));

        int offset = 0;
        for (BasePlayer player : topPlayers) {
            if (player == null)
                continue;

            if (player.isDead())
                continue;

            d.drawText(new Vector2(0, offset), player.scoreboardText());
            offset += 25;
        }

        d.setCameraRelative(true);
    }
}
