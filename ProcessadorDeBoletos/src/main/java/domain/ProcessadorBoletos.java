package domain;

import enumeration.StatusFatura;
import enumeration.TipoPagamento;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProcessadorBoletos {
    private List<Fatura> faturasProcessadas;

    public ProcessadorBoletos() {
        this.faturasProcessadas = new ArrayList<>();
    }

    public List<Fatura> getFaturasProcessadas() {
        return faturasProcessadas;
    }

    public void setFaturasProcessadas(List<Fatura> faturasProcessadas) {
        this.faturasProcessadas = faturasProcessadas;
    }

    public double processaFatura(Fatura fatura, List<Boleto> boletos) {
        float totalPago = 0;

        for (Boleto boleto : boletos) {
            totalPago += boleto.getValorPago();
            Pagamento pagamento = new Pagamento(boleto.getValorPago(), LocalDate.now(), TipoPagamento.BOLETO, boleto, fatura);
        }

        this.faturasProcessadas.add(fatura);

        if (totalPago >= fatura.getValorTotal()) {
            fatura.setStatusFatura(StatusFatura.PAGA);
        }
        return totalPago;
    }

}
