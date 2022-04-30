import exception.*;
import models.Deducao;
import models.Dependente;
import models.Rendimento;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class Irpf {

    private double FAIXA1_LIMIT = 1903.98; // 0%
    private double FAIXA2_LIMIT = 922.67; // 7.5%
    private double FAIXA3_LIMIT = 924.40; // 15.5%
    private double FAIXA4_LIMIT = 913.63; // 22.5%
    private double FAIXA5_LIMIT = FAIXA4_LIMIT + FAIXA3_LIMIT + FAIXA2_LIMIT + FAIXA1_LIMIT; // 27.5%

    ArrayList<Rendimento> rendimentos;
    ArrayList<Deducao> deducoes;
    private ArrayList<Dependente> dependentes;

    public Irpf() {
        rendimentos = new ArrayList<>();
        deducoes = new ArrayList<>();
        dependentes = new ArrayList<>();
    }

    public ArrayList<Rendimento> getRendimentos(){
        return this.rendimentos;
    }

    public ArrayList<Deducao> getDeducao(){
        return this.deducoes;
    }


    public double getRendimentoTotal () {
        double finalValue = new CalculateRendimento(this).computarValorTotal();
        return this.truncateValue(finalValue);
    }

    public double getDeducaoTotal() {
        double dependenteFinalValue = dependentes.size() * Dependente.pensionValue;
        double finalValue = new CalculateDeducao(this).computarValorTotal(dependenteFinalValue);
        return this.truncateValue(finalValue);
    }

    public double getAliquota() {
        double percentage = 100 * this.getTax()/this.getRendimentoTotal();
        return this.truncateValue(percentage);
    };

    public double getTax() {
        double totalValue = this.getRendimentoTotal() - this.getDeducaoTotal();
        double faixaValue;
        double taxValue = 0;

        if(totalValue > FAIXA5_LIMIT) {
            faixaValue = totalValue - FAIXA5_LIMIT;
            taxValue += faixaValue * 0.275;
        }  if (totalValue > FAIXA3_LIMIT + FAIXA2_LIMIT + FAIXA1_LIMIT){
            faixaValue = Math.min(totalValue - (FAIXA3_LIMIT + FAIXA2_LIMIT + FAIXA1_LIMIT), FAIXA4_LIMIT);
            taxValue += faixaValue * 0.225;
        }  if (totalValue > FAIXA2_LIMIT+ FAIXA1_LIMIT){
            faixaValue = Math.min(totalValue - (FAIXA2_LIMIT + FAIXA1_LIMIT), FAIXA3_LIMIT);
            taxValue += faixaValue * 0.15;
        }  if (totalValue > FAIXA1_LIMIT) {
            faixaValue = Math.min(totalValue - FAIXA1_LIMIT, FAIXA2_LIMIT);
            taxValue += faixaValue * 0.075;
        }

        return truncateValue(taxValue);
    };

    private double truncateValue(double value) {
        return BigDecimal.valueOf(value)
                .setScale(2, RoundingMode.HALF_EVEN)
                .doubleValue();
    }

    void addRendimento(Rendimento rendimento) throws DescricaoEmBrancoException, ValorRendimentoInvalidoException {
        new CalculateRendimento(this).computar(rendimento);
    }

    public void addDeducao(Deducao deducao) throws ValorDeducaoInvalidoException, DescricaoEmBrancoException {
        new CalculateDeducao(this).computar(deducao);
    }

    public ArrayList<Dependente> getDependentes(){
        return this.dependentes;
    }

    public void setDependenteDeducao(Dependente dependente) throws NoSuchMethodException {
        if(dependente.getName() == "") throw new NoSuchMethodException("Descrição não pode ser vazia");
        this.dependentes.add(dependente);
    }

    public void handleDeducoesException(Deducao deducao) throws DescricaoEmBrancoException, ValorDeducaoInvalidoException {
        if(deducao.getDeducaoDescription() == "")
            throw new DescricaoEmBrancoException("Descrição não pode ser vazia");
        if(deducao.getValue() <= 0)
            throw new ValorDeducaoInvalidoException("Valor deve ser positivo");
    }

    public void handleRendimentosException(Rendimento rendimento) throws DescricaoEmBrancoException, ValorRendimentoInvalidoException {
        if(rendimento.getDescription() == "")
            throw new DescricaoEmBrancoException("Descrção não pode ser vazia");
        if(rendimento.getValue() <= 0)
            throw new ValorRendimentoInvalidoException("Valor deve ser positivo");
    }

}