public class Sigmoid implements ActivationFunction {
    @Override
    public double activation(double x) {
        return 1.0 / (1.0 + Math.exp(-x));
    }
}
