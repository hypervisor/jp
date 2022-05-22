package Game;

import Engine.*;
import Game.BasePlayer;
import Game.KeyConfig;

import java.awt.*;
import java.awt.event.KeyEvent;

public class WasdPlayer extends ControllablePlayer {
    private static final KeyConfig WASD_CONFIG = getConfig();
    private static KeyConfig getConfig() {
        KeyConfig cfg = new KeyConfig();
        cfg.moveLeft = KeyEvent.VK_A;
        cfg.moveRight = KeyEvent.VK_D;
        cfg.moveUp = KeyEvent.VK_W;
        cfg.moveDown = KeyEvent.VK_S;
        cfg.shoot = KeyEvent.VK_E;
        cfg.multiShoot = KeyEvent.VK_Q;
        cfg.respawn = KeyEvent.VK_R;

        return cfg;
    }

    public WasdPlayer(String name, Vector2 position) {
        super(name, position, WASD_CONFIG, Color.BLUE);
    }
}
