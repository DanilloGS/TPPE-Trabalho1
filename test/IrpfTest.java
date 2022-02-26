import exception.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class IrpfTest {

    Irpf irpf;

    @BeforeEach
    public void setUp() {
        irpf = new Irpf();
    }

    @ParameterizedTest
    @CsvSource({
            "Salario, 5000",
            "Aluguel, 6000",
            "Comercio, 8000"
    })
    void getRendimentos(String description, int value) throws DescricaoEmBrancoException, ValorRendimentoInvalidoException {
        irpf.addRendimento(description, value);
        assertEquals(description, irpf.getRendimentos().get(0).getDescription());
        assertEquals(value, irpf.getRendimentos().get(0).getValue());
    }

    @ParameterizedTest
    @CsvSource({
            "1000, 2000, 321, 3321",
            "6000, 5000, 3000, 14000",
            "8000, 2681, 3920, 14601"
    })
    void getRendimentoTotal(int value1, int value2, int value3, int finalValue) throws DescricaoEmBrancoException, ValorRendimentoInvalidoException {
        irpf.addRendimento("Salario", value1);
        irpf.addRendimento("Salario", value2);
        irpf.addRendimento("Salario", value3);
        assertEquals(finalValue, irpf.getRendimentoTotal());
    }

    @Test
    void addRendimentoVoidValueException(){
        assertThrows(VoidValueException.class, () -> irpf.addRendimento("",1000));
    }

    @Test
    void addRendimentoNonPositiveValueExceptionWhen0(){
        assertThrows(NonPositiveValueException.class, () -> irpf.addRendimento("Salario",0));
    }

    @Test
    void addRendimentoNonPositiveValueExceptionWhenNegative(){
        assertThrows(NonPositiveValueException.class, () -> irpf.addRendimento("Salario",-1000));
    }

    @Test
    void addDeducaoDescricaoEmBranco(){
        assertThrows(DescricaoEmBrancoException.class,
                () -> irpf.addDeducao(
                        8482.23, "PP", ""
                ));
    }

    @Test
    void addDeducaoValorDeducaoInvalidoVazio(){
        assertThrows(ValorDeducaoInvalidoException.class,
                () -> irpf.addDeducao(
                        0, "PP", "Previdencia Privada"
                ));
    }

    @Test
    void addDeducaoValorDeducaoInvalidoNegativo(){
        assertThrows(ValorDeducaoInvalidoException.class,
                () -> irpf.addDeducao(
                        -2500.00, "PP", "Previdencia Privada"
                ));
    }

    @ParameterizedTest
    @CsvSource({
            "1000.00, PO, Contribuicao Previdencia Oficial",
            "2000.00, PP, Minha Previdencia Privada",
            "3000.00, PA, Pensao Alimenticia"
    })
    void getDeducao(double deducao, String tipoDeducao, String descricao) throws DescricaoEmBrancoException, ValorDeducaoInvalidoException {
        irpf.addDeducao(deducao, tipoDeducao, descricao);
        assertEquals(deducao, irpf.getDeducao().get(0).getValue());
        assertEquals(tipoDeducao, irpf.getDeducao().get(0).getDeducaoType());
        assertEquals(descricao, irpf.getDeducao().get(0).getDeducaoDescription());
    }

    @ParameterizedTest
    @CsvSource({
            "Ada Lovelace, 25/03/1820",
            "Lord Byron, 13/02/1780",
            "Steve Wozniak, 12/03/1990"
    })
    void getDependentes(String nome, String dtNascimento) throws NoSuchMethodException {
        irpf.setDependenteDeducao(nome, dtNascimento);
        assertEquals(nome, irpf.getDependentes().get(0).getNome());
        assertEquals(dtNascimento, irpf.getDependentes().get(0).getDtNascimento());
    }

    @Test
    void getDeducaoTotal() throws DescricaoEmBrancoException, ValorDeducaoInvalidoException {
        irpf.addDeducao(5000.00, "PA", "Pensao Alimenticia");
        irpf.addDeducao(10000.00, "PP", "Previdencia Privada");
        irpf.addDeducao(12000.00, "PP", "Previdencia Privada");
        assertEquals(27000.00, irpf.getDeducaoTotal());
    }
}