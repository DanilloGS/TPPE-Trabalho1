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
    void getDeducao() throws DescricaoEmBrancoException, ValorDeducaoInvalidoException {
        irpf.addDeducao(1000.00, "PO", "Contribuicao Previdencia Oficial");
        assertEquals(1000.00, irpf.getDeducao().get(0).getValue());
        assertEquals("Contribuicao Previdencia Oficial", irpf.getDeducao().get(0).getDeducaoDescription());
    }

    @Test
    void getDuasDeducoes() throws DescricaoEmBrancoException, ValorDeducaoInvalidoException {
        irpf.addDeducao(1000.00, "PO", "Primeira Previdencia Oficial");
        irpf.addDeducao(3000.00, "PO", "Segunda Previdencia Oficial");
        assertEquals(4000.00, irpf.getDeducaoTotal());
        assertEquals("Primeira Previdencia Oficial", irpf.getDeducao().get(0).getDeducaoDescription());
        assertEquals("Segunda Previdencia Oficial", irpf.getDeducao().get(1).getDeducaoDescription());
    }

    @Test
    void getDependentes() throws NoSuchMethodException {
        irpf.setDependenteDeducao("Carlos", "25/03/1990");
        assertEquals("Carlos", irpf.getDependentes().get(0).getNome());
        assertEquals("25/03/1990", irpf.getDependentes().get(0).getDtNascimento());
    }

    @Test
    void getDoisDependentes() throws NoSuchMethodException {
        irpf.setDependenteDeducao("Carlos Prestes", "25/03/1750");
        irpf.setDependenteDeducao("Steve Wozniak", "02/01/1990");
        assertEquals("Carlos Prestes", irpf.getDependentes().get(0).getNome());
        assertEquals("Steve Wozniak", irpf.getDependentes().get(1).getNome());
        assertEquals("25/03/1750", irpf.getDependentes().get(0).getDtNascimento());
        assertEquals("02/01/1990", irpf.getDependentes().get(1).getDtNascimento());
    }

    @Test
    void getDeducaoTotal() throws DescricaoEmBrancoException, ValorDeducaoInvalidoException {
        irpf.addDeducao(5000.00, "PA", "PA");
        irpf.addDeducao(10000.00, "PA", "PA");
        assertEquals(15000.00, irpf.getDeducaoTotal());
    }
}