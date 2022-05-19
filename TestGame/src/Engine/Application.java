package Engine;

import java.awt.*;
import javax.swing.JFrame;

public class Application extends JFrame {
    private static boolean ENABLE_LOGS = false;
    public static final int SCREEN_W = 1600;
    public static final int SCREEN_H = 1200;

    private static Application instance;

    public static Vector2 getScreenSize() {
        return new Vector2(SCREEN_W, SCREEN_H);
    }
    public static Vector2 getCursorPosition() {
        Point p = MouseInfo.getPointerInfo().getLocation();
        Point s = instance.getLocation();
        return new Vector2(p.x - s.x, p.y - s.y);
    }

    public static float time;

    public Application() {
        instance = this;
        add(new Board());
        setSize(SCREEN_W, SCREEN_H);
        setTitle("Call of Duty: Black Ops V");
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
