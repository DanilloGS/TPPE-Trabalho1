import exception.DescricaoEmBrancoException;
import exception.ValorDeducaoInvalidoException;
import models.Deducao;

import java.util.ArrayList;

public class CalculateDeducao {
    // Referencia para objeto original
    private Irpf fonte;
    private Deducao deducao;

    ArrayList<Deducao> deducoes;

    // Construtor do objeto-metodo
    public CalculateDeducao(Irpf fonte, Deducao deducao){
        this.fonte = fonte;
        this.deducao = deducao;
    }

    void computarDeducoes () throws ValorDeducaoInvalidoException, DescricaoEmBrancoException {
        fonte.handleDeducoesException(this.deducao);
        fonte.deducoes.add(this.deducao);
    }


}
