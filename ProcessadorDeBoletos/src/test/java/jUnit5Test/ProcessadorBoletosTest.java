package jUnit5Test;

import domain.Boleto;
import domain.Fatura;
import domain.ProcessadorBoletos;
import enumeration.StatusFatura;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProcessadorBoletosTest {

    private static ProcessadorBoletos processadorBoletos;
    private static Fatura fatura;

    @BeforeAll
    public static void criarProcessadorEFatura() {
        processadorBoletos = new ProcessadorBoletos();
        fatura = new Fatura(1, LocalDate.now(), 250, "Fulano");
    }

    @AfterEach
    public void reset(){
        processadorBoletos.getFaturasProcessadas().clear();
        assertTrue(processadorBoletos.getFaturasProcessadas().size() == 0);

        fatura.getPagamentosAssociados().clear();
        assertTrue(fatura.getPagamentosAssociados().size() == 0);

        fatura.setStatusFatura(StatusFatura.PENDENTE);
        assertTrue(fatura.getStatusFatura() == StatusFatura.PENDENTE);
    }

    @DisplayName("Teste de criação de Processador de Boletos")
    @jUnit5Test.interfaces.ProcessadorBoletosTest
    public void criarProcessadorBoleto() {
        ProcessadorBoletos processadorBoleto = new ProcessadorBoletos();
        assertNotNull(processadorBoleto);
    }

    @DisplayName("Teste de lista de faturas processadas vazia ao criar Processador")
    @jUnit5Test.interfaces.ProcessadorBoletosTest
    public void emptyListFaturas() {
        ProcessadorBoletos processadorBoleto = new ProcessadorBoletos();
        assertNotNull(processadorBoleto.getFaturasProcessadas());
        assertEquals(0, processadorBoleto.getFaturasProcessadas().size());
    }

    @DisplayName("Teste de processamento de fatura com total pago")
    @jUnit5Test.interfaces.ProcessadorBoletosTest
    public void processarFaturaPagaTest() {
        List<Boleto> boletoList = new ArrayList<Boleto>();

        boletoList.add(new Boleto("1", LocalDate.now(), 50));
        boletoList.add(new Boleto("2", LocalDate.now(), 50));
        boletoList.add(new Boleto("3", LocalDate.now(), 80));
        boletoList.add(new Boleto("4", LocalDate.now(), 70));

        assertEquals(250, this.processadorBoletos.processaFatura(fatura, boletoList));
    }

    @DisplayName("Teste de processamento de fatura paga parcialmente")
    @jUnit5Test.interfaces.ProcessadorBoletosTest
    public void processarFaturaParcialTest() {
        List<Boleto> boletoList = new ArrayList<Boleto>();

        boletoList.add(new Boleto("10", LocalDate.now(), 50));
        boletoList.add(new Boleto("20", LocalDate.now(), 100));

        double totalPago = this.processadorBoletos.processaFatura(fatura, boletoList);
        assertTrue(totalPago < fatura.getValorTotal());
        assertEquals(150, totalPago);
    }

    @DisplayName("Teste de novos processamentos para faturas já processadas")
    @jUnit5Test.interfaces.ProcessadorBoletosTest
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

    @DisplayName("Teste de processamento de fatura com valor pago abaixo do mínimo")
    @jUnit5Test.interfaces.ProcessadorBoletosTest
    public void processarFaturaBoletoMinMenos1() {
        List<Boleto> boletoList = new ArrayList<Boleto>();
        boletoList.add(new Boleto("10", LocalDate.now(), 0));

        assertNotNull(new Boleto("11", LocalDate.now(), 0));
        assertEquals(0, this.processadorBoletos.processaFatura(fatura, boletoList));
    }

    @DisplayName("Teste de processamento de fatura com valor mínimo pago")
    @jUnit5Test.interfaces.ProcessadorBoletosTest
    public void processarFaturaBoletoMin() {
        List<Boleto> boletoList = new ArrayList<Boleto>();
        boletoList.add(new Boleto("10", LocalDate.now(), 1));

        double valorPago = this.processadorBoletos.processaFatura(fatura, boletoList);
        assertEquals(1, valorPago);
        assertTrue(valorPago < this.fatura.getValorTotal());
    }

    @DisplayName("Teste de processamento de fatura com valor mínimo mais 1 pago")
    @jUnit5Test.interfaces.ProcessadorBoletosTest
    public void processarFaturaBoletoMinMais1() {
        List<Boleto> boletoList = new ArrayList<Boleto>();
        boletoList.add(new Boleto("10", LocalDate.now(), 2));

        double valorPago = this.processadorBoletos.processaFatura(fatura, boletoList);
        assertEquals(2, valorPago);
        assertTrue(valorPago < this.fatura.getValorTotal());
        assertEquals(StatusFatura.PENDENTE, this.fatura.getStatusFatura());
    }

    @DisplayName("Teste de processamento de fatura com valor qualquer pago")
    @jUnit5Test.interfaces.ProcessadorBoletosTest
    public void processarFaturaBoletoQualquer() {
        List<Boleto> boletoList = new ArrayList<Boleto>();
        boletoList.add(new Boleto("10", LocalDate.now(), 85));

        double valorPago = this.processadorBoletos.processaFatura(fatura, boletoList);
        assertEquals(85, valorPago);
        assertTrue(valorPago < this.fatura.getValorTotal());
        assertEquals(StatusFatura.PENDENTE, this.fatura.getStatusFatura());
    }

    @DisplayName("Teste de processamento de fatura com valor máximo menos 1 pago")
    @jUnit5Test.interfaces.ProcessadorBoletosTest
    public void processarFaturaBoletoMaxMenos1() {
        List<Boleto> boletoList = new ArrayList<Boleto>();
        boletoList.add(new Boleto("10", LocalDate.now(), 249));

        double valorPago = this.processadorBoletos.processaFatura(fatura, boletoList);
        assertEquals(249, valorPago);
        assertTrue((this.fatura.getValorTotal() - valorPago) == 1);
        assertEquals(StatusFatura.PENDENTE, this.fatura.getStatusFatura());
    }

    @DisplayName("Teste de processamento de fatura com valor máximo pago")
    @jUnit5Test.interfaces.ProcessadorBoletosTest
    public void processarFaturaBoletoMax() {
        List<Boleto> boletoList = new ArrayList<Boleto>();
        boletoList.add(new Boleto("10", LocalDate.now(), 250));

        double valorPago = this.processadorBoletos.processaFatura(fatura, boletoList);
        assertEquals(250, valorPago);
        assertTrue((this.fatura.getValorTotal() - valorPago) == 0);
        assertEquals(StatusFatura.PAGA, this.fatura.getStatusFatura());
    }

    @DisplayName("Teste de processamento de fatura com valor máximo mais 1 pago")
    @jUnit5Test.interfaces.ProcessadorBoletosTest
    public void processarFaturaBoletoMaxMais1() {
        List<Boleto> boletoList = new ArrayList<Boleto>();
        boletoList.add(new Boleto("10", LocalDate.now(), 251));

        double valorPago = this.processadorBoletos.processaFatura(fatura, boletoList);
        assertEquals(251, valorPago);
        assertTrue((this.fatura.getValorTotal() - valorPago) == -1);
        assertEquals(StatusFatura.PAGA, this.fatura.getStatusFatura());
    }
}
