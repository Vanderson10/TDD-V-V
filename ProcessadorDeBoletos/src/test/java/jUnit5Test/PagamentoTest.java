package jUnit5Test;

import domain.Boleto;
import domain.Fatura;
import domain.Pagamento;
import enumeration.TipoPagamento;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

public class PagamentoTest {

    Fatura fatura;
    Boleto boleto;

    @DisplayName("Teste de criação de fatura e boleto para associação com pagamento.")
    @BeforeEach
    public void criaFaturaEBoleto() {
        this.fatura = new Fatura(1, LocalDate.now(), 250, "Fulano");
        this.boleto = new Boleto("1", LocalDate.now(), 250);
    }

    @DisplayName("Teste de criação de pagamento.")
    @jUnit5Test.interfaces.PagamentoTest
    public void criarPagamentoTest() {
        int valor = 250;
        assertTrue(valor >= 1);
        Pagamento pagamento = new Pagamento(valor, LocalDate.now(), TipoPagamento.BOLETO, this.boleto, this.fatura);
        assertNotNull(pagamento);
    }
}
