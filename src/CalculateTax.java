import exception.DescricaoEmBrancoException;
import exception.ValorDeducaoInvalidoException;
import models.Deducao;

public class CalculateTax {
    // Referencia para objeto original
    private Irpf fonte;
    private Deducao deducao;

    // Construtor do objeto-metodo
    public CalculateTax(Irpf fonte, Deducao deducao){
        this.fonte = fonte;
        this.deducao = deducao;
    }

    void computar () throws ValorDeducaoInvalidoException, DescricaoEmBrancoException {
        fonte.handleException(this.deducao);
        fonte.deducoes.add(this.deducao);
    }
}
