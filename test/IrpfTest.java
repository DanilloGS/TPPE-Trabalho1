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
        System.out.println(irpf.getRendimento());
    }
}