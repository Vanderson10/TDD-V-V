import domain.Boleto;
import domain.Fatura;
import domain.Pagamento;
import enumeration.TipoPagamento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class PagamentoTestUnit {
    Fatura fatura;
    Boleto boleto;

    @BeforeEach
    public void criaFaturaEBoleto() {
        this.fatura = new Fatura(1, LocalDate.now(), 250, "Fulano");
        this.boleto = new Boleto("1", LocalDate.now(), 250);
    }

    @Test
    public void criarPagamentoTest() {
        Pagamento pagamento = new Pagamento(250, LocalDate.now(), TipoPagamento.BOLETO, this.boleto, this.fatura);
    }
}
