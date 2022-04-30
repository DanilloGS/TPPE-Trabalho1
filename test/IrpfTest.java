import TaxCalculator.Irpf;
import exception.*;
import models.Deducao;
import models.Dependente;
import models.Rendimento;
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
        Rendimento rendimento = new Rendimento(value, description);
        irpf.addRendimento(rendimento);
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
        Rendimento rendimento1 = new Rendimento(value1, "Salario");
        Rendimento rendimento2 = new Rendimento(value2, "Salario");
        Rendimento rendimento3 = new Rendimento(value3, "Salario");
        irpf.addRendimento(rendimento1);
        irpf.addRendimento(rendimento2);
        irpf.addRendimento(rendimento3);
        assertEquals(finalValue, irpf.getRendimentoTotal());
    }

    @Test
    void addRendimentoVoidValueException(){
        Rendimento rendimento = new Rendimento(1000, "");
        assertThrows(DescricaoEmBrancoException.class, () -> irpf.addRendimento(rendimento));
    }

    @Test
    void addRendimentoNonPositiveValueExceptionWhen0(){
        Rendimento rendimento = new Rendimento(0, "Salario");
        assertThrows(ValorRendimentoInvalidoException.class, () -> irpf.addRendimento(rendimento));
    }

    @Test
    void addRendimentoNonPositiveValueExceptionWhenNegative(){
        Rendimento rendimento = new Rendimento(-1000, "Salario");
        assertThrows(ValorRendimentoInvalidoException.class, () -> irpf.addRendimento(rendimento));
    }

    @Test
    void addDeducaoDescricaoEmBranco(){
        Deducao deducao = new Deducao(8482.23, "PP", "");
        assertThrows(DescricaoEmBrancoException.class,
                () -> irpf.addDeducao(deducao));
    }

    @Test
    void addDeducaoValorDeducaoInvalidoVazio(){
        Deducao deducao = new Deducao(0, "PP", "Previdencia Privada");
        assertThrows(ValorDeducaoInvalidoException.class,
                () -> irpf.addDeducao(deducao));
    }

    @Test
    void addDeducaoValorDeducaoInvalidoNegativo(){
        Deducao deducao = new Deducao(-2500.00, "PP", "Previdencia Privada");
        assertThrows(ValorDeducaoInvalidoException.class,
                () -> irpf.addDeducao(deducao));
    }

    @ParameterizedTest
    @CsvSource({
            "1000.00, PO, Contribuicao Previdencia Oficial",
            "2000.00, PP, Minha Previdencia Privada",
            "3000.00, PA, Pensao Alimenticia"
    })
    void getDeducao(double value, String tipoDeducao, String descricao) throws DescricaoEmBrancoException, ValorDeducaoInvalidoException {
        Deducao deducao = new Deducao(value, tipoDeducao, descricao);
        irpf.addDeducao(deducao);
        assertEquals(value, irpf.getDeducao().get(0).getValue());
        assertEquals(tipoDeducao, irpf.getDeducao().get(0).getDeducaoType());
        assertEquals(descricao, irpf.getDeducao().get(0).getDescription());
    }

    @ParameterizedTest
    @CsvSource({
            "Ada Lovelace, 25/03/1820",
            "Lord Byron, 13/02/1780",
            "Steve Wozniak, 12/03/1990"
    })
    void getDependentes(String name, String birthDay) throws NoSuchMethodException {
        Dependente dependente = new Dependente(name, birthDay);
        irpf.setDependenteDeducao(dependente);
        assertEquals(name, irpf.getDependentes().get(0).getName());
        assertEquals(birthDay, irpf.getDependentes().get(0).getBirthDay());
    }

    @Test
    void getDeducaoTotal() throws DescricaoEmBrancoException, ValorDeducaoInvalidoException {
        Deducao deducao1 = new Deducao(5000.00, "PA", "Pensao Alimenticia");
        Deducao deducao2 = new Deducao(10000.00, "PP", "Previdencia Privada");
        Deducao deducao3 = new Deducao(12000.00, "PP", "Previdencia Privada");
        irpf.addDeducao(deducao1);
        irpf.addDeducao(deducao2);
        irpf.addDeducao(deducao3);
        assertEquals(27000.00, irpf.getDeducaoTotal());
    }
    @ParameterizedTest
    @CsvSource({
            "15000, 21.70",
            "10000, 18.81",
            "500, 0",
            "5000, 10.11",
            "3000, 3.17",
            "1903.98, 0",
            "2826.65, 2.45",
            "3751.05, 5.54",
    })
    void getAliquota(double value, double percentageExpected) throws ValorRendimentoInvalidoException, DescricaoEmBrancoException {
        Rendimento rendimento = new Rendimento(value, "Salario");
        irpf.addRendimento(rendimento);

        assertEquals(percentageExpected, irpf.getAliquota());
        }
    }
