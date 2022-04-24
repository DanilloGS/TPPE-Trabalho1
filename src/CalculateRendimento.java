import exception.DescricaoEmBrancoException;
import exception.ValorDeducaoInvalidoException;
import exception.ValorRendimentoInvalidoException;
import models.Rendimento;

import java.util.ArrayList;

public class CalculateRendimento extends Calculate{

    public CalculateRendimento(Irpf fonte) {
        super(fonte);
    }

    void computar (Rendimento rendimento) throws DescricaoEmBrancoException, ValorRendimentoInvalidoException {
        fonte.handleRendimentosException(rendimento);
        fonte.rendimentos.add(rendimento);
    }

    double computarValorTotal() {
        ArrayList<Double> finalValue = new ArrayList<>();

        this.fonte.rendimentos.forEach(rendimento ->
                finalValue.add(rendimento.getValue())
        );

        return this.sum(finalValue);
    }

}
