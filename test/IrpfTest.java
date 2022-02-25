import exception.*;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.Assert.assertEquals;
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
        Assertions.assertEquals(description, irpf.getRendimentos().get(0).getDescription());
        Assertions.assertEquals(value, irpf.getRendimentos().get(0).getValue());
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
        Assertions.assertEquals(finalValue, irpf.getRendimentoTotal());
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
        Assertions.assertEquals(1000.00, irpf.getDeducao().get(0).getValue());
        Assertions.assertEquals("Contribuicao Previdencia Oficial", irpf.getDeducao().get(0).getDeducaoDescription());
    }

    @Test
    void getDependentes() throws NoSuchMethodException {
        irpf.setDependenteDeducao("Carlos", "25/03/1990");
        Assertions.assertEquals("Carlos", irpf.getDependentes().get(0).getNome());
        Assertions.assertEquals("25/03/1990", irpf.getDependentes().get(0).getDtNascimento());
    }

    @Test
    void getDeducaoTotal() throws DescricaoEmBrancoException, ValorDeducaoInvalidoException {
        irpf.addDeducao(5000.00, "PA", "PA");
        Assertions.assertEquals(5000.00, irpf.getDeducaoTotal());
    }
}