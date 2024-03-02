package org.sistema.reserva.voo.entidades;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Voo {

    private String data;
    private String codigo;
    private double preco;
    private Cliente cliente;
    private String localOrigem;
    private String localDestino;
    private int numeroPassageiros;
    private String horario;

    public Voo(String data, String codigo, double preco, String localOrigem, String localDestino, int numeroPassageiros, String horario) {
        this.data = data;
        this.codigo = codigo;
        this.preco = preco;
        this.localOrigem = localOrigem;
        this.localDestino = localDestino;
        this.numeroPassageiros = numeroPassageiros;
        this.cliente = null;
        this.horario = horario;
    }

    public boolean isDisponivel() {
        return this.numeroPassageiros>0;
    }

    public double getPrecoTotal() {
        return this.preco * this.numeroPassageiros;
    }

    public void imprimirDetalhes() {
        System.out.println("Voo " + this.codigo + ":");
        System.out.println("Data: " + this.data);
        System.out.println("Origem: " + this.localOrigem);
        System.out.println("Destino: " + this.localDestino);
        System.out.println("Horário: " + this.horario);
        System.out.println("Preço: R$ " + this.preco);
        System.out.println("Lugares disponíveis: " + (this.numeroPassageiros - (this.cliente == null ? 0 : this.cliente.getNumeroPassageirosEmbarcar())));
        if (this.cliente != null) {
            System.out.println("Cliente reservado: " + this.cliente.getNome());
        }
        System.out.println();
    }
}

