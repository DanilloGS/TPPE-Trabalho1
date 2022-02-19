import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IrpfTest {

    Irpf irpf;

    @BeforeEach
    void setUp() {
        irpf = new Irpf();
    }

    @Test
    void getRendimento() {
        irpf.addRendimento("Salario", 5000);
        irpf.addRendimento("Aluguel", 6000);
        irpf.addRendimento("Comercio", 8000);

        assertEquals("Salario", irpf.getRendimento().get(0).getDescription());
        assertEquals(5000, irpf.getRendimento().get(0).getValue());

        assertEquals("Aluguel", irpf.getRendimento().get(1).getDescription());
        assertEquals(6000, irpf.getRendimento().get(1).getValue());

        assertEquals("Comercio", irpf.getRendimento().get(2).getDescription());
        assertEquals(8000, irpf.getRendimento().get(2).getValue());
    }
}