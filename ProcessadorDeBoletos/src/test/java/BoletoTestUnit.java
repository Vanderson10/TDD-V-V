import domain.Boleto;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class BoletoTestUnit {

    @Test
    public void criarBoletoTest() {
        Boleto boleto = new Boleto("1", LocalDate.now(), 250);
    }
}
