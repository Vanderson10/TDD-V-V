package domain;

import enumeration.StatusFatura;
import enumeration.TipoPagamento;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProcessadorBoletos {
    private Map<Integer, Fatura> faturasProcessadas;

    public ProcessadorBoletos() {
        this.faturasProcessadas = new HashMap<>();
    }

    public Map<Integer, Fatura> getFaturasProcessadas() {
        return faturasProcessadas;
    }

    public void setFaturasProcessadas(Map<Integer, Fatura> faturasProcessadas) {
        this.faturasProcessadas = faturasProcessadas;
    }

    public double processaFatura(Fatura fatura, List<Boleto> boletos) {
        double totalPago = 0;

        if (this.faturasProcessadas.containsKey(fatura.getId())) {
            totalPago = this.somaPagamentosAssociados(totalPago, fatura);
        }

        for (Boleto boleto : boletos) {
            totalPago += boleto.getValorPago();
            Pagamento pagamento = new Pagamento(boleto.getValorPago(), LocalDate.now(), TipoPagamento.BOLETO, boleto, fatura);
            fatura.getPagamentosAssociados().add(pagamento);
        }

        this.faturasProcessadas.put(fatura.getId(), fatura);

        if (totalPago >= fatura.getValorTotal()) {
            fatura.setStatusFatura(StatusFatura.PAGA);
        }
        return totalPago;
    }

    private double somaPagamentosAssociados(double total, Fatura fatura) {
        for (Pagamento pagamento : fatura.getPagamentosAssociados()) {
            total += pagamento.getValorPago();
        }
        return total;
    }

}
