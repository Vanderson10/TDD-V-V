import domain.ProcessadorBoletos;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProcessadorBoletosTest {

    @Test
    public void criarProcessadorBoleto() {
        ProcessadorBoletos processadorBoleto = new ProcessadorBoletos();
        assertNotNull(processadorBoleto);
    }

    @Test
    public void emptyListFaturas() {
        ProcessadorBoletos processadorBoleto = new ProcessadorBoletos();
        assertNotNull(processadorBoleto.getFaturasProcessadas());
        assertEquals(0, processadorBoleto.getFaturasProcessadas().size());
    }
}
