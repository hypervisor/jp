import java.util.ArrayList;
import java.util.List;

public class Neuron {
    public double bias;
    public List<Connection> connections;
    public ActivationFunction activation;

    public Neuron(ActivationFunction activation) {
        bias = 0.0;
        connections = new ArrayList<>();
        this.activation = activation;
    }

    public void setBias(double b) {
        bias = b;
    }

    public void connect(Neuron n, double weight) {
        n.connections.add(new Connection(this, weight));
    }

    public double get() {
        double value = bias;
        for (Connection c : connections) {
            value += c.source.get() * c.weight;
        }
        return value;
    }
}