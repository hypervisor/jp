import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Board extends JPanel implements ActionListener {
    private static final int FRAMES_PER_SECOND = 60;
    private static final int TIMER_DELAY = 1000 / FRAMES_PER_SECOND;
    private static final float DELTA_TIME = 1f / FRAMES_PER_SECOND;

    private Timer updateTimer;
    private Input input;

    public Board() {
        // Required for KeyListener to work
        setFocusable(true);
        requestFocus();
        requestFocusInWindow();

        EntityManager.addEntity(new SafeZone());

        EntityManager.spawnPlayer(new WasdPlayer("Adrian", Util.randomPositionInsideZone()));
        EntityManager.spawnPlayer(new ArrowPlayer("William", Util.randomPositionInsideZone()));

        EntityManager.addEntity(new Scoreboard());

        World.spawnBandages();
        World.spawnAmmo();
        World.spawnPoison();

        updateTimer = new Timer(TIMER_DELAY, this);
        updateTimer.start();

        input = new Input();
        addKeyListener(input);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        render(g);
    }

    private void render(Graphics g) {
        for (BaseEntity entity : EntityManager.getEntities()) {
            Drawing d = new Drawing(g, entity.position);
            entity.render(d);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Add queued entities and clear queue
        EntityManager.updateEntityList();

        // Call update() on every entity.
        for (BaseEntity entity : EntityManager.getEntities()) {
            entity.update(input, DELTA_TIME);
        }

        // Re-render.
        repaint();

        // Update keyboard
        input.newFrame();
    }
}
