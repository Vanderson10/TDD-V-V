import domain.Boleto;
import domain.Fatura;
import domain.ProcessadorBoletos;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    @Test
    public void processarFaturaTest() {
        ProcessadorBoletos processadorBoletos = new ProcessadorBoletos();
        Fatura fatura = new Fatura(LocalDate.now(), 250, "Fulano");
        List<Boleto> boletoList = new ArrayList<Boleto>();

        boletoList.add(new Boleto("1", LocalDate.now(), 50));
        boletoList.add(new Boleto("2", LocalDate.now(), 50));
        boletoList.add(new Boleto("3", LocalDate.now(), 80));
        boletoList.add(new Boleto("4", LocalDate.now(), 70));

        assertEquals(250, processadorBoletos.processaFatura(fatura, boletoList));
    }
}
