package domain;

import java.time.LocalDate;

public class Boleto {

    private String codigo;
    private LocalDate data;
    private double valorPago;

    public Boleto(String codigo, LocalDate data, double valor){
        this.codigo = codigo;
        this.data = data;
        this.valorPago = valor;
    }
}
