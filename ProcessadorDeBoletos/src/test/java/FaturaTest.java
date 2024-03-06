import domain.Fatura;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class FaturaTest {

    @Test
    public void criarFaturaTest() {
        Fatura fatura = new Fatura(1, LocalDate.now(), 250, "Fulano");
    }


}
