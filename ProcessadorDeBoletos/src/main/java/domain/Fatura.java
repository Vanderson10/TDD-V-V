package domain;

import enumeration.StatusFatura;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class Fatura {

    private LocalDate data;
    private double valorTotal;
    private String nomeCliente;
    private StatusFatura statusFatura;

    private List<Pagamento> pagamentosAssociados;

    public List<Pagamento> getPagamentosAssociados() {
        return pagamentosAssociados;
    }

    public void setPagamentosAssociados(List<Pagamento> pagamentosAssociados) {
        this.pagamentosAssociados = pagamentosAssociados;
    }

    public Fatura(LocalDate data, double valorTotal, String nomeCliente) {
        this.data = data;
        this.valorTotal = valorTotal;
        this.nomeCliente = nomeCliente;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public StatusFatura getStatusFatura() {
        return statusFatura;
    }

    public void setStatusFatura(StatusFatura statusFatura) {
        this.statusFatura = statusFatura;
    }
}
