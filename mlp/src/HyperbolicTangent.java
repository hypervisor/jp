public class HyperbolicTangent implements ActivationFunction {
    @Override
    public double activation(double x) {
        return Math.tanh(x);
    }
}
