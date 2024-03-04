package domain;

import enumeration.StatusFatura;

import java.time.LocalDate;
import java.util.Date;

public class Fatura {

    private LocalDate data;
    private double valorTotal;
    private String nomeCliente;
    private StatusFatura statusFatura;

    public Fatura(LocalDate data, double valorTotal, String nomeCliente){
        this.data = data;
        this.valorTotal = valorTotal;
        this.nomeCliente = nomeCliente;
    }
}
