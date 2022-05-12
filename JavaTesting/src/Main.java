public class Main {
    public static void main(String[] args) {
        Triangle t = TriangleFactory.withoutAdjacent(3, 5);
        System.out.println("sine: " + t.sine());
        System.out.println("cosine: " + t.cosine());
        System.out.println("tangent: " + t.tangent());
        System.out.println("area: " + t.area());
    }
}
