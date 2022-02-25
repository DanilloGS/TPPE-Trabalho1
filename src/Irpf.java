import exception.ValorRendimentoInvalidoException;
import exception.DescricaoEmBrancoException;
import models.Rendimento;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class Irpf {

    private double FAIXA1_LIMIT = 1903.98; // 0%
    private double FAIXA2_LIMIT = 922.67; // 7.5%
    private double FAIXA3_LIMIT = 924.40; // 15.5%
    private double FAIXA4_LIMIT = 913.63; // 22.5%
//    private double FAIXA5_LIMIT = ANY_VALUE; 27.5%

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

    public double calculateTax() {
        return 3255.64;
    };
}
