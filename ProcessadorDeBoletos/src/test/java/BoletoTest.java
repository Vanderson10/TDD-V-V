import domain.Boleto;

import java.time.LocalDate;

public class BoletoTest {

    public void criarBoletoTest(){
        Boleto boleto = new Boleto("1", LocalDate.now(), 250);
    }
}
