import java.awt.*;

public class Drawing {
    private Graphics g;
    private Vector2 base;

    public Drawing(Graphics g, Vector2 base) {
        this.g = g;
        this.base = base;
    }

    private Vector2 makeRelative(Vector2 p) {
        return new Vector2(base.x + p.x, base.y + p.y);
    }

    public void drawLine(Vector2 a, Vector2 b, Color c) {
        a = makeRelative(a);
        b = makeRelative(b);

        g.setColor(c);
        g.drawLine(a.getX(), a.getY(), b.getX(), b.getY());
    }

    public void drawLine(Vector2 a, Vector2 b) {
        drawLine(a, b, Color.BLACK);
    }

    public void drawCircle(Vector2 p, float d, Color c) {
        p = makeRelative(p);

        g.setColor(c);
        g.drawOval(p.getX(), p.getY(), (int)d, (int)d);
    }

    public void drawCircle(Vector2 p, float d) {
        drawCircle(p, d, Color.BLACK);
    }

    public void fillCircle(Vector2 p, float d, Color c) {
        p = makeRelative(p);

        g.setColor(c);
        g.fillOval(p.getX(), p.getY(), (int)d, (int)d);
    }

    public void fillCircle(Vector2 p, float d) {
        fillCircle(p, d, Color.BLACK);
    }

    public void drawText(Vector2 p, String t, Color c) {
        p = makeRelative(p);

        g.setColor(c);
        g.drawString(t, p.getX(), p.getY());
    }

    public void drawText(Vector2 p, String t) {
        drawText(p, t, Color.BLACK);
    }

    public void drawRect(Vector2 p, Vector2 s, Color c) {
        p = makeRelative(p);

        g.setColor(c);
        g.drawRect(p.getX(), p.getY(), s.getX(), s.getY());
    }

    public void drawRect(Vector2 p, Vector2 s) {
        drawRect(p, s, Color.BLACK);
    }

    public void fillRect(Vector2 p, Vector2 s, Color c) {
        p = makeRelative(p);

        g.setColor(c);
        g.fillRect(p.getX(), p.getY(), s.getX(), s.getY());
    }

    public void fillRect(Vector2 p, Vector2 s) {
        fillRect(p, s, Color.BLACK);
    }
}
