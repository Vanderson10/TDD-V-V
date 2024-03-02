package org.sistema.reserva.voo.implementation;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
public class Cliente {

    private String nome;
    private int numeroPassageirosEmbarcar;
    private String telefone;
    private ArrayList<Voo> voosReservados;

    public Cliente(String nome, int numeroPassageirosEmbarcar, String telefone) {
        this.nome = nome;
        this.numeroPassageirosEmbarcar = numeroPassageirosEmbarcar;
        this.telefone = telefone;
        this.voosReservados = new ArrayList<>();
    }

    public void reservarVoo(Voo voo) {
        if (voo.isDisponivel()) {
            if (this.numeroPassageirosEmbarcar <= voo.getNumeroPassageiros()) {
                voo.setCliente(this);
                this.voosReservados.add(voo);
                voo.setNumeroPassageiros(voo.getNumeroPassageiros()-this.numeroPassageirosEmbarcar);
                System.out.println("Reserva realizada com sucesso!");
                System.out.println("Código da reserva: " + voo.getCodigo());
                System.out.println("Preço total: R$ " + voo.getPrecoTotal());
                System.out.println("Informações do passageiro:");
                System.out.println("Nome: " + this.nome);
                System.out.println("Telefone: " + this.telefone);
                System.out.println("Número de passageiros: " + this.numeroPassageirosEmbarcar);
                System.out.println();
            } else {
                System.out.println("Não há lugares suficientes para o número de passageiros solicitado.");
            }
        } else {
            System.out.println("O voo não está disponível.");
        }
    }

    public void cancelarReserva(String codigo) {
        boolean encontrouCodigoVoo = false;
        for (Voo voo : this.voosReservados) {
            if (voo.getCodigo().equals(codigo)) {
                encontrouCodigoVoo = true;
                voo.setCliente(null);
                this.voosReservados.remove(voo);
                System.out.println("Reserva cancelada com sucesso!");
                System.out.println("Código da reserva: " + voo.getCodigo());
                System.out.println("Preço total: R$ " + voo.getPrecoTotal());
                System.out.println("Informações do passageiro:");
                System.out.println("Nome: " + this.nome);
                System.out.println("Telefone: " + this.telefone);
                System.out.println("Número de passageiros: " + this.numeroPassageirosEmbarcar);
                System.out.println();
                break;
            }
        }
        if (!encontrouCodigoVoo) {
            System.out.println("Não há reserva com o código informado.");
        }
    }

    public void pesquisarVoos(Sistema sistema, String origem, String destino, String data, int numeroPassageiros) {
        boolean encontrou = false;
        System.out.println("Voos encontrados:");
        for (Voo voo : sistema.getVoos()) {
            if (voo.getLocalOrigem().equals(origem) || voo.getLocalDestino().equals(destino) || voo.getData().equals(data) || voo.getNumeroPassageiros() >= numeroPassageiros) {
                encontrou = true;
                voo.imprimirDetalhes();
            }
        }
        if (!encontrou) {
            System.out.println("Não há voos disponíveis para os critérios informados.");
        }
    }
}
