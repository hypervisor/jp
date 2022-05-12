import java.awt.EventQueue;
import javax.swing.JFrame;

public class Application extends JFrame {
    public static final int SCREEN_W = 800;
    public static final int SCREEN_H = 800;

    public static Vector2 getScreenSize() {
        return new Vector2(SCREEN_W, SCREEN_H);
    }

    public Application() {
        add(new Board());
        setSize(SCREEN_W, SCREEN_H);
        setTitle("Call of Duty: Black Ops V");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            Application ex = new Application();
            ex.setVisible(true);
        });
    }
}
