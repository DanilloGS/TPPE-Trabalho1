import java.util.ArrayList;

public class Calculate {
    protected Irpf fonte;

    // Construtor do objeto-metodo
    public Calculate(Irpf fonte){
        this.fonte = fonte;
    }

    protected double sum(ArrayList<Double> values) {
        double result = 0;
        for (Double value:values) result += value;
        return result;
    }

}
