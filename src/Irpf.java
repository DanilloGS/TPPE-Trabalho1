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
    private double taxValue = 0;

    ArrayList<Rendimento> rendimentos;
    ArrayList<Deducao> deducoes;
    private ArrayList<Dependente> dependentes;

    public Irpf() {
        rendimentos = new ArrayList<>();
        deducoes = new ArrayList<>();
        dependentes = new ArrayList<>();
    }

    void addRendimento(Rendimento rendimento) throws DescricaoEmBrancoException, ValorRendimentoInvalidoException {
        new CalculateRendimento(this, rendimento).computarRendimento();
    }

    public ArrayList<Rendimento> getRendimentos(){
        return this.rendimentos;
    }

    public ArrayList<Deducao> getDeducao(){
        return this.deducoes;
    }

    public ArrayList<Dependente> getDependentes(){
        return this.dependentes;
    }

    public double getRendimentoTotal () {
        final double[] totalValue = {0};
        this.rendimentos.forEach(rendimento ->
                totalValue[0] += rendimento.getValue()
        );
        return this.truncateValue(totalValue[0]);
    }

    public double calculateTax() {
        double totalValue = this.getRendimentoTotal() - this.getDeducaoTotal();
        double faixaValue;
        this.taxValue = 0;

        if(totalValue > FAIXA5_LIMIT) {
            faixaValue = totalValue - FAIXA5_LIMIT;
            this.taxValue += faixaValue * 0.275;
        }  if (totalValue > FAIXA3_LIMIT + FAIXA2_LIMIT + FAIXA1_LIMIT){
            faixaValue = Math.min(totalValue - (FAIXA3_LIMIT + FAIXA2_LIMIT + FAIXA1_LIMIT), FAIXA4_LIMIT);
            this.taxValue += faixaValue * 0.225;
        }  if (totalValue > FAIXA2_LIMIT+ FAIXA1_LIMIT){
            faixaValue = Math.min(totalValue - (FAIXA2_LIMIT + FAIXA1_LIMIT), FAIXA3_LIMIT);
            this.taxValue += faixaValue * 0.15;
        }  if (totalValue > FAIXA1_LIMIT) {
            faixaValue = Math.min(totalValue - FAIXA1_LIMIT, FAIXA2_LIMIT);
            this.taxValue += faixaValue * 0.075;
        }

        return truncateValue(this.taxValue);
    };

    public double getAliquota() {
        double percentage = 100 * this.calculateTax()/this.getRendimentoTotal();
        return this.truncateValue(percentage);
    };

    public void addDeducao(Deducao deducao) throws ValorDeducaoInvalidoException, DescricaoEmBrancoException {
        new CalculateDeducao(this, deducao).computarDeducoes();
    }

    public void setDependenteDeducao(String name, String dtNascimento) throws NoSuchMethodException {
        if(name == "")
            throw new NoSuchMethodException("Descrição não pode ser vazia");
        Dependente dependente = new Dependente(name, dtNascimento);
        this.dependentes.add(dependente);
    }

    public double getDeducaoTotal() {
        int totalDependentes = dependentes.size();

        final double[] totalValue = {totalDependentes * Dependente.pensao};

        this.deducoes.forEach(deducao ->
                totalValue[0] += deducao.getValue()
        );

        return this.truncateValue(totalValue[0]);
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

    private double truncateValue(double value) {
        return BigDecimal.valueOf(value)
                .setScale(2, RoundingMode.HALF_EVEN)
                .doubleValue();
    }
}