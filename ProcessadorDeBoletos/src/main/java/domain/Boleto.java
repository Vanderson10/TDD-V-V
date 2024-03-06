package domain;

import java.time.LocalDate;

public class Boleto {

    private String codigo;
    private LocalDate data;
    private double valorPago;

    public Boleto(String codigo, LocalDate data, double valor) {
        this.codigo = codigo;
        this.data = data;
        this.valorPago = valor;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public double getValorPago() {
        return valorPago;
    }

    public void setValorPago(double valorPago) {
        this.valorPago = valorPago;
    }
}
