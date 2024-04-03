import domain.Boleto;
import domain.Fatura;
import domain.ProcessadorBoletos;
import enumeration.StatusFatura;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProcessadorBoletosTestUnit {

    ProcessadorBoletos processadorBoletos;
    Fatura fatura;

    @BeforeEach
    public void criarProcessadorEFatura() {
        this.processadorBoletos = new ProcessadorBoletos();
        this.fatura = new Fatura(1, LocalDate.now(), 250, "Fulano");
    }

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
    public void processarFaturaPagaTest() {
        List<Boleto> boletoList = new ArrayList<Boleto>();

        boletoList.add(new Boleto("1", LocalDate.now(), 50));
        boletoList.add(new Boleto("2", LocalDate.now(), 50));
        boletoList.add(new Boleto("3", LocalDate.now(), 80));
        boletoList.add(new Boleto("4", LocalDate.now(), 70));

        assertEquals(250, this.processadorBoletos.processaFatura(fatura, boletoList));
    }

    @Test
    public void processarFaturaParcialTest() {
        List<Boleto> boletoList = new ArrayList<Boleto>();

        boletoList.add(new Boleto("10", LocalDate.now(), 50));
        boletoList.add(new Boleto("20", LocalDate.now(), 100));

        double totalPago = this.processadorBoletos.processaFatura(fatura, boletoList);
        assertTrue(totalPago < fatura.getValorTotal());
        assertEquals(150, totalPago);
    }

    @Test
    public void processarFaturaJaProcessadaTest() {
        List<Boleto> boletoList = new ArrayList<Boleto>();
        boletoList.add(new Boleto("10", LocalDate.now(), 50));
        boletoList.add(new Boleto("20", LocalDate.now(), 100));
        this.processadorBoletos.processaFatura(fatura, boletoList);

        List<Boleto> boletoList2 = new ArrayList<Boleto>();

        boletoList2.add(new Boleto("5", LocalDate.now(), 30));
        boletoList2.add(new Boleto("6", LocalDate.now(), 60));

        assertEquals(240, this.processadorBoletos.processaFatura(fatura, boletoList2));
    }

    @Test
    public void processarFaturaBoletoMinMenos1() {
        List<Boleto> boletoList = new ArrayList<Boleto>();
        boletoList.add(new Boleto("10", LocalDate.now(), 0));

        assertNotNull(new Boleto("11", LocalDate.now(), 0));
        assertEquals(0, this.processadorBoletos.processaFatura(fatura, boletoList));
    }

    @Test
    public void processarFaturaBoletoMin() {
        List<Boleto> boletoList = new ArrayList<Boleto>();
        boletoList.add(new Boleto("10", LocalDate.now(), 1));

        double valorPago = this.processadorBoletos.processaFatura(fatura, boletoList);
        assertEquals(1, valorPago);
        assertTrue(valorPago < this.fatura.getValorTotal());
    }

    @Test
    public void processarFaturaBoletoMinMais1() {
        List<Boleto> boletoList = new ArrayList<Boleto>();
        boletoList.add(new Boleto("10", LocalDate.now(), 2));

        double valorPago = this.processadorBoletos.processaFatura(fatura, boletoList);
        assertEquals(2, valorPago);
        assertTrue(valorPago < this.fatura.getValorTotal());
        assertEquals(StatusFatura.PENDENTE, this.fatura.getStatusFatura());
    }

    @Test
    public void processarFaturaBoletoQualquer() {
        List<Boleto> boletoList = new ArrayList<Boleto>();
        boletoList.add(new Boleto("10", LocalDate.now(), 85));

        double valorPago = this.processadorBoletos.processaFatura(fatura, boletoList);
        assertEquals(85, valorPago);
        assertTrue(valorPago < this.fatura.getValorTotal());
        assertEquals(StatusFatura.PENDENTE, this.fatura.getStatusFatura());
    }

    @Test
    public void processarFaturaBoletoMaxMenos1() {
        List<Boleto> boletoList = new ArrayList<Boleto>();
        boletoList.add(new Boleto("10", LocalDate.now(), 249));

        double valorPago = this.processadorBoletos.processaFatura(fatura, boletoList);
        assertEquals(249, valorPago);
        assertTrue((this.fatura.getValorTotal() - valorPago) == 1);
        assertEquals(StatusFatura.PENDENTE, this.fatura.getStatusFatura());
    }
    @Test
    public void processarFaturaBoletoMax() {
        List<Boleto> boletoList = new ArrayList<Boleto>();
        boletoList.add(new Boleto("10", LocalDate.now(), 250));

        double valorPago = this.processadorBoletos.processaFatura(fatura, boletoList);
        assertEquals(250, valorPago);
        assertTrue((this.fatura.getValorTotal() - valorPago) == 0);
        assertEquals(StatusFatura.PAGA, this.fatura.getStatusFatura());
    }

    @Test
    public void processarFaturaBoletoMaxMais1() {
        List<Boleto> boletoList = new ArrayList<Boleto>();
        boletoList.add(new Boleto("10", LocalDate.now(), 251));

        double valorPago = this.processadorBoletos.processaFatura(fatura, boletoList);
        assertEquals(251, valorPago);
        assertTrue((this.fatura.getValorTotal() - valorPago) == -1);
        assertEquals(StatusFatura.PAGA, this.fatura.getStatusFatura());
    }

}
