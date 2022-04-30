import exception.DescricaoEmBrancoException;
import exception.ValorDeducaoInvalidoException;
import models.Deducao;

import java.util.ArrayList;

public class CalculateDeducao extends Calculate {
    public CalculateDeducao(Irpf source) {
        super(source);
    }

    void computar (Deducao deducao) throws ValorDeducaoInvalidoException, DescricaoEmBrancoException {
        this.source.handleDeducoesException(deducao);
        this.source.deducoes.add(deducao);
    }

    double computarValorTotal(double initialValue){
        ArrayList<Double> finalValue = new ArrayList<>();
        finalValue.add(initialValue);

        this.source.deducoes.forEach(deducao ->
                finalValue.add(deducao.getValue())
        );

        return this.sum(finalValue);
    }
}
