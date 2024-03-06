package domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ProcessadorBoletos processadorBoletos = new ProcessadorBoletos();
        List<Boleto> boletosPagos = new ArrayList<Boleto>();
        Fatura fatura = new Fatura(1, LocalDate.now(), 300, "Andreza");
        boletosPagos.add(new Boleto("1a", LocalDate.now(), 50)) ;
        boletosPagos.add(new Boleto("1b", LocalDate.now(), 120));

        double valorPagoF1 = processadorBoletos.processaFatura(fatura,boletosPagos);
        boletosPagos.clear();
        System.out.println(fatura.toString());

        List<Boleto> boletosPagos2 = new ArrayList<Boleto>();
        Fatura fatura2 = new Fatura(2, LocalDate.now(), 150, "Manu");
        boletosPagos2.add(new Boleto("2a", LocalDate.now(), 150)) ;
        double valorPagoF2 = processadorBoletos.processaFatura(fatura2,boletosPagos2);

        System.out.println(fatura2.toString());

        boletosPagos.add(new Boleto("1c", LocalDate.now(), 130)) ;
        valorPagoF1 = processadorBoletos.processaFatura(fatura, boletosPagos);
        System.out.println(fatura.toString());
    }
}