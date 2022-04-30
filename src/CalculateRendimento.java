import exception.DescricaoEmBrancoException;
import exception.ValorRendimentoInvalidoException;
import models.Rendimento;

import java.util.ArrayList;

public class CalculateRendimento extends Calculate {

    public CalculateRendimento(Irpf source) {
        super(source);
    }

    void computar (Rendimento rendimento) throws DescricaoEmBrancoException, ValorRendimentoInvalidoException {
        source.handleRendimentosException(rendimento);
        source.rendimentos.add(rendimento);
    }

    double computarValorTotal() {
        ArrayList<Double> finalValue = new ArrayList<>();

        this.source.rendimentos.forEach(rendimento ->
                finalValue.add(rendimento.getValue())
        );

        return this.sum(finalValue);
    }

}
