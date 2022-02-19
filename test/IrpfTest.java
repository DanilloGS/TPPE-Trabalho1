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
    void getRendimento1() {
        irpf.addRendimento("Salario", 5000);
        assertEquals("Salario", irpf.getRendimento().getDescription());
        assertEquals(5000, irpf.getRendimento().getValue());
    }
    @Test
    void getRendimento2() {
        irpf.addRendimento("Aluguel", 6000);
        assertEquals("Aluguel", irpf.getRendimento().getDescription());
        assertEquals(6000, irpf.getRendimento().getValue());
    }
    @Test
    void getRendimento3() {
        irpf.addRendimento("Comercio", 8000);
        assertEquals("Comercio", irpf.getRendimento().getDescription());
        assertEquals(8000, irpf.getRendimento().getValue());
    }
}