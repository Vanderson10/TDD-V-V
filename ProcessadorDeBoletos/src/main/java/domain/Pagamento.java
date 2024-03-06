package domain;

import enumeration.TipoPagamento;

import java.awt.print.Pageable;
import java.time.LocalDate;

public class Pagamento {

    private double valorPago;
    private LocalDate data;
    private TipoPagamento tipoPagamento;
    private Boleto boleto;
    private Fatura fatura;

    public Pagamento(double valorPago, LocalDate data, TipoPagamento tipoPagamento, Boleto boleto, Fatura fatura){
        this.valorPago= valorPago;
        this.data = data;
        this.tipoPagamento = tipoPagamento;
        this.boleto = boleto;
        this.fatura = fatura;
    }

    public double getValorPago() {
        return valorPago;
    }

    public void setValorPago(double valorPago) {
        this.valorPago = valorPago;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public TipoPagamento getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(TipoPagamento tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public Boleto getBoleto() {
        return boleto;
    }

    public void setBoleto(Boleto boleto) {
        this.boleto = boleto;
    }

    public Fatura getFatura() {
        return fatura;
    }

    public void setFatura(Fatura fatura) {
        this.fatura = fatura;
    }
}
