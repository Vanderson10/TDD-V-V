package domain;

import java.time.LocalDate;
import java.util.Date;

public class Fatura {

    private LocalDate data;
    private double valorTotal;
    private String nomeCliente;

    public Fatura(LocalDate data, double valorTotal, String nomeCliente){
        this.data = data;
        this.valorTotal = valorTotal;
        this.nomeCliente = nomeCliente;
    }
}
