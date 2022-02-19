import exception.NonPositiveValueException;
import exception.VoidValueException;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;

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
    void getRendimentos(String description, int value) throws VoidValueException, NonPositiveValueException {
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
    void getRendimentoTotal(int value1, int value2, int value3, int finalValue) throws VoidValueException, NonPositiveValueException {
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
}