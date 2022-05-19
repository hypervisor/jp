public class Main {
    public static void main(String[] args) {
        ActivationFunction sigmoid = new Sigmoid();
        ActivationFunction tanh = new HyperbolicTangent();

        Neuron input0 = new Neuron(sigmoid);
        Neuron input1 = new Neuron(sigmoid);

        Neuron output = new Neuron(sigmoid);
        input0.connect(output, Util.randomBetween(0f, 1f));
        input1.connect(output, Util.randomBetween(0f, 1f));

        // Bias in input neurons = input value
        input0.setBias(1);
        input1.setBias(0);

        System.out.println(output.get());
    }
}
