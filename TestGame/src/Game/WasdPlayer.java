package Game;

import Engine.*;
import Game.BasePlayer;
import Game.KeyConfig;

import java.awt.*;
import java.awt.event.KeyEvent;

public class WasdPlayer extends BasePlayer {
    private static final KeyConfig WASD_CONFIG = getConfig();
    private static KeyConfig getConfig() {
        KeyConfig cfg = new KeyConfig();
        cfg.moveLeft = KeyEvent.VK_A;
        cfg.moveRight = KeyEvent.VK_D;
        cfg.moveUp = KeyEvent.VK_W;
        cfg.moveDown = KeyEvent.VK_S;
        cfg.shoot = KeyEvent.VK_E;

        return cfg;
    }

    public WasdPlayer(String name, Vector2 position) {
        super(name, position, WASD_CONFIG, Color.BLUE);
    }
}
