package Engine;

import java.awt.*;
import javax.swing.JFrame;

public class Application extends JFrame {
    public static final String GAME_NAME = "Minigrounds";

    private static boolean ENABLE_LOGS = false;
    public static final int SCREEN_W = 1200;
    public static final int SCREEN_H = 800;

    public static Application instance;

    public static Vector2 getScreenSize() {
        Dimension size = instance.getSize();
        return new Vector2(size.width, size.height);
    }
    public static Vector2 getCursorPosition() {
        Point p = MouseInfo.getPointerInfo().getLocation();
        Point s = instance.getLocation();
        return new Vector2(p.x - s.x, p.y - s.y);
    }

    public static float time;

    public Application() {
        instance = this;
        setSize(SCREEN_W, SCREEN_H);
        add(new Board());
        setTitle(GAME_NAME);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);
        time = 0f;
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            Application ex = new Application();
            ex.setVisible(true);
        });
    }

    public static void log(String x) {
        if (ENABLE_LOGS) {
            System.out.println(x);
        }
    }
}
