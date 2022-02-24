import exception.ValorRendimentoInvalidoException;
import exception.DescricaoEmBrancoException;
import models.Rendimento;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class Irpf {
    private ArrayList<Rendimento> rendimentos = new ArrayList<>();

    void addRendimento(String description, double value) throws DescricaoEmBrancoException, ValorRendimentoInvalidoException {
        if(description == "")
            throw new DescricaoEmBrancoException("Descrção não pode ser vazia");
        if(value <= 0)
            throw new ValorRendimentoInvalidoException("Valor deve ser positivo");
        Rendimento _rendimento = new Rendimento();
        _rendimento.setValue(value);
        _rendimento.setDescription(description);
        this.rendimentos.add(_rendimento);
    }

    public ArrayList<Rendimento> getRendimentos(){
        return this.rendimentos;
    }

    public double getRendimentoTotal () {
        final double[] totalValue = {0};
        this.rendimentos.forEach(rendimento ->
                totalValue[0] += rendimento.getValue()
        );
        return totalValue[0];
    }
}
