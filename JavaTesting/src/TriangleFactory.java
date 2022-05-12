public class TriangleFactory {
    public static Triangle withoutHypotenuse(double adjacent, double opposite) {
        return new SSSTriangle(adjacent, opposite, SSSTriangle.findHypotenuse(adjacent, opposite));
    }

    public static Triangle withoutOpposite(double adjacent, double hypotenuse) {
        return new SSSTriangle(adjacent, SSSTriangle.findSide(adjacent, hypotenuse), hypotenuse);
    }

    public static Triangle withoutAdjacent(double opposite, double hypotenuse) {
        return new SSSTriangle(SSSTriangle.findSide(opposite, hypotenuse), opposite, hypotenuse);
    }
}
