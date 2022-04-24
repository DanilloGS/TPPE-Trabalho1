import exception.DescricaoEmBrancoException;
import exception.ValorDeducaoInvalidoException;
import exception.ValorRendimentoInvalidoException;
import models.Rendimento;

public class CalculateRendimento {
    // Referencia para objeto original
    private Irpf fonte;
    private Rendimento rendimento;

    // Construtor do objeto-metodo
    public CalculateRendimento(Irpf fonte, Rendimento rendimento){
        this.fonte = fonte;
        this.rendimento = rendimento;
    }

    void computarRendimento () throws DescricaoEmBrancoException, ValorRendimentoInvalidoException {
        fonte.handleRendimentosException(this.rendimento);
        fonte.rendimentos.add(this.rendimento);
    }
}
