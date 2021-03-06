package Game;

import java.awt.*;
import java.awt.event.KeyEvent;

import Engine.*;
import Game.*;

public class ArrowPlayer extends ControllablePlayer {
    private static final KeyConfig ARROW_CONFIG = getConfig();
    private static KeyConfig getConfig() {
        KeyConfig cfg = new KeyConfig();
        cfg.moveLeft = KeyEvent.VK_LEFT;
        cfg.moveRight = KeyEvent.VK_RIGHT;
        cfg.moveUp = KeyEvent.VK_UP;
        cfg.moveDown = KeyEvent.VK_DOWN;
        cfg.shoot = KeyEvent.VK_P;

        return cfg;
    }

    public ArrowPlayer(String name, Vector2 position) {
        super(name, position, ARROW_CONFIG, Color.RED);
    }
}
