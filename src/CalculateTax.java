import exception.DescricaoEmBrancoException;
import exception.ValorDeducaoInvalidoException;
import models.Deducao;

public class CalculateTax {
    // Referencia para objeto original
    Irpf _fonte;

    // Atributo para cada parametro
    double deducaoValue;
    String deducaoType;
    String deducaoDescription;

    // Atributo para cada variavel temporaria
    Deducao deducao;

    // Construtor do objeto-metodo
    public CalculateTax(Irpf _fonte, double deducaoValue, String deducaoType, String deducaoDescription){
        this._fonte = _fonte;
        this.deducaoValue = deducaoValue;
        this.deducaoType = deducaoType;
        this.deducaoDescription = deducaoDescription;
    }

    void computar () throws ValorDeducaoInvalidoException, DescricaoEmBrancoException {
        _fonte.handleException(deducaoValue, deducaoDescription);

        deducao = new Deducao();
        deducao.setValue(deducaoValue);
        deducao.setDeducaoType(deducaoType);
        deducao.setDeducaoDescription(deducaoDescription);

        _fonte.deducoes.add(deducao);
    }
}
