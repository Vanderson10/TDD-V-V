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
}
