import exception.DescricaoEmBrancoException;
import exception.ValorDeducaoInvalidoException;
import models.Deducao;

import java.util.ArrayList;

public class CalculateDeducao extends Calculate {
    public CalculateDeducao(Irpf fonte) {
        super(fonte);
    }

    void computar (Deducao deducao) throws ValorDeducaoInvalidoException, DescricaoEmBrancoException {
        this.fonte.handleDeducoesException(deducao);
        this.fonte.deducoes.add(deducao);
    }

    double computarValorTotal(double initialValue){
        ArrayList<Double> finalValue = new ArrayList<>();
        finalValue.add(initialValue);

        this.fonte.deducoes.forEach(deducao ->
                finalValue.add(deducao.getValue())
        );

        return this.sum(finalValue);
    }
}
