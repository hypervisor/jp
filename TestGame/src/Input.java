import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Input extends KeyAdapter {
    private static final int MAX_KEYS = 1024;

    private boolean[] previousState;
    private boolean[] currentState;

    public Input() {
        previousState = new boolean[MAX_KEYS];
        currentState = new boolean[MAX_KEYS];
    }

    public void newFrame() {
        // Copy current state to previous state
        for (int i = 0; i < MAX_KEYS; i++) {
            previousState[i] = currentState[i];
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() >= MAX_KEYS)
            return;

        currentState[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() >= MAX_KEYS)
            return;

        currentState[e.getKeyCode()] = false;
    }

    public boolean getKey(int k) {
        return currentState[k];
    }

    public boolean getKeyDown(int k) {
        return currentState[k] && !previousState[k];
    }

    public boolean getKeyUp(int k) {
        return previousState[k] && !currentState[k];
    }
}
