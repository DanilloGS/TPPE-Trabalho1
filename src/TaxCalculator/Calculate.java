package TaxCalculator;

import models.Monetary;
import models.Rendimento;

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

    public <T extends Monetary> double computarValorTotal(ArrayList<T> monetaryList, double initialValue) {
        ArrayList<Double> finalValue = new ArrayList<>();
        finalValue.add(initialValue);

        monetaryList.forEach(monetary -> finalValue.add(monetary.getValue()));

        return this.sum(finalValue);
    }

}
