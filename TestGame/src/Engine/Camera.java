package Engine;

public class Camera {
    private static Vector2 cameraPosition = new Vector2(0, 0);
    public static Vector2 offsetPosition(Vector2 p) {
        return new Vector2(p.x - cameraPosition.x, p.y - cameraPosition.y);
    }
    public static void focusOn(Vector2 p) {
        Vector2 screenSize = Application.getScreenSize();
        cameraPosition.x = p.x - (screenSize.x / 2);
        cameraPosition.y = p.y - (screenSize.y / 2);
    }
}
