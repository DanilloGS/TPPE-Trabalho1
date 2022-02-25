import exception.ValorRendimentoInvalidoException;
import exception.DescricaoEmBrancoException;
import models.Rendimento;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

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
        double totalValue = getRendimentoTotal();
        double taxValue = 0;
        if(totalValue > FAIXA4_LIMIT + FAIXA3_LIMIT + FAIXA2_LIMIT + FAIXA1_LIMIT) {
            totalValue -= (FAIXA4_LIMIT + FAIXA3_LIMIT + FAIXA2_LIMIT + FAIXA1_LIMIT);
            taxValue += totalValue * 0.275;
        }  if (totalValue > FAIXA3_LIMIT + FAIXA2_LIMIT + FAIXA1_LIMIT){
            taxValue += FAIXA4_LIMIT * 0.225;
        }  if (totalValue > FAIXA2_LIMIT+ FAIXA1_LIMIT){
            taxValue += FAIXA3_LIMIT * 0.15;
        }  if (totalValue > FAIXA1_LIMIT) {
            taxValue += FAIXA2_LIMIT * 0.075;
        }

        double truncatedTax = BigDecimal.valueOf(taxValue)
                .setScale(2, RoundingMode.DOWN)
                .doubleValue();

        return truncatedTax;
    };
}
