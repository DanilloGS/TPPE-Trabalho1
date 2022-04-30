package TaxCalculator;

import exception.DescricaoEmBrancoException;
import exception.ValorDeducaoInvalidoException;
import models.Deducao;
import models.Monetary;

import java.util.ArrayList;

public class CalculateDeducao extends Calculate {
    public CalculateDeducao(Irpf source) {
        super(source);
    }

    void computar (Deducao deducao) throws ValorDeducaoInvalidoException, DescricaoEmBrancoException {
        this.source.handleDeducoesException(deducao);
        this.source.deducoes.add(deducao);
    }
}
