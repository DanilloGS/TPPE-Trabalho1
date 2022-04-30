import java.util.ArrayList;

public class Calculate {
    protected Irpf source;

    public Calculate(Irpf source){
        this.source = source;
    }

    protected double sum(ArrayList<Double> values) {
        double result = 0;
        for (Double value:values) result += value;
        return result;
    }

}
