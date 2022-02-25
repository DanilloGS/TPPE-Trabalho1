import exception.ValorRendimentoInvalidoException;
import exception.DescricaoEmBrancoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.text.DecimalFormat;

import static org.junit.jupiter.api.Assertions.*;

class IrpfTest {

    Irpf irpf;

    @BeforeEach
    public void setUp() {
        irpf = new Irpf();
    }

    @ParameterizedTest
    @CsvSource({
            "Salario, 5000.0",
            "Aluguel, 6000.0",
            "Comercio, 8000.0"
    })
    void getRendimentos(String description, double value) throws DescricaoEmBrancoException, ValorRendimentoInvalidoException {
        irpf.addRendimento(description, value);
        assertEquals(description, irpf.getRendimentos().get(0).getDescription());
        assertEquals(value, irpf.getRendimentos().get(0).getValue());
    }

    @ParameterizedTest
    @CsvSource({
            "1000.10, 2000.30, 321.0, 3321.40",
            "6000.0, 5000.0, 3000.0, 14000.0",
            "8000.0, 2681.0, 3920.01, 14601.01"
    })
    void getRendimentoTotal(double value1, double value2, double value3, double finalValue) throws DescricaoEmBrancoException, ValorRendimentoInvalidoException {
        irpf.addRendimento("Salario", value1);
        irpf.addRendimento("Salario", value2);
        irpf.addRendimento("Salario", value3);
        assertEquals(finalValue, irpf.getRendimentoTotal());
    }

    @Test
    void addRendimentoVoidValueException(){
        assertThrows(DescricaoEmBrancoException.class, () -> irpf.addRendimento("",1000));
    }

    @Test
    void addRendimentoNonPositiveValueExceptionWhen0(){
        assertThrows(ValorRendimentoInvalidoException.class, () -> irpf.addRendimento("Salario",0));
    }

    @Test
    void addRendimentoNonPositiveValueExceptionWhenNegative(){
        assertThrows(ValorRendimentoInvalidoException.class, () -> irpf.addRendimento("Salario",-1000));
    }

    @Test
    void calculateTax() throws ValorRendimentoInvalidoException, DescricaoEmBrancoException {
        irpf.addRendimento("Salario", 15000);
        assertEquals(3255.64, irpf.calculateTax());
    }

    @Test
    void calculateTax1() throws ValorRendimentoInvalidoException, DescricaoEmBrancoException {
        irpf.addRendimento("Salario", 10000);
        assertEquals(1880.64, irpf.calculateTax());
    }

    @Test
    void calculateTax2() throws ValorRendimentoInvalidoException, DescricaoEmBrancoException {
        irpf.addRendimento("Salario", 500);
        assertEquals(0, irpf.calculateTax());
    }

    @Test
    void calculateTax3() throws ValorRendimentoInvalidoException, DescricaoEmBrancoException {
        irpf.addRendimento("Salario", 1903.98);
        assertEquals(0, irpf.calculateTax());
    }

    @Test
    void calculateTax4() throws ValorRendimentoInvalidoException, DescricaoEmBrancoException {
        irpf.addRendimento("Salario", 2826.65);
        assertEquals(69.20, irpf.calculateTax());
    }

}