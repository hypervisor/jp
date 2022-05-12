public class SSSTriangle implements Triangle{
    private double adjacent;
    private double opposite;
    private double hypotenuse;

    public SSSTriangle(double adjacent, double opposite, double hypotenuse) {
        this.adjacent = adjacent;
        this.opposite = opposite;
        this.hypotenuse = hypotenuse;
    }

    public double getAdjacent() {
        return adjacent;
    }

    public double getOpposite() {
        return opposite;
    }

    public double getHypotenuse() {
        return hypotenuse;
    }

    public static double findSide(double otherSide, double hypotenuse) {
        return Math.sqrt((hypotenuse * hypotenuse) - (otherSide * otherSide));
    }

    public static double findHypotenuse(double a, double b) {
        return Math.sqrt((a * a) + (b * b));
    }

    @Override
    public double sine() {
        return opposite / hypotenuse;
    }

    @Override
    public double cosine() {
        return adjacent / hypotenuse;
    }

    @Override
    public double tangent() {
        return opposite / adjacent;
    }

    @Override
    public double perimeter() {
        return adjacent + opposite + hypotenuse;
    }

    @Override
    public double area() {
        double s = perimeter() / 2;
        // Heron's formula
        return Math.sqrt(s * (s - adjacent) * (s - opposite) * (s - hypotenuse));
    }
}
