package org.sistema.reserva.voo;

import org.sistema.reserva.voo.entidades.Cliente;
import org.sistema.reserva.voo.entidades.Sistema;
import org.sistema.reserva.voo.entidades.Voo;

import java.util.Scanner;

public class Main {

    public static void main(String args[]) {
        Sistema sistema = new Sistema();
        cadastrarVoosFicticios(sistema);

        Scanner scanner = new Scanner(System.in);

        System.out.println("Cadastro de Cliente");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Número de passageiros a embarcar: ");
        int numeroPassageirosEmbarcar = Integer.parseInt(scanner.nextLine());
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();

        Cliente cliente = new Cliente(nome, numeroPassageirosEmbarcar, telefone);

        exibirMenuOperacoes(scanner, sistema, cliente);

        scanner.close();
    }

    private static void exibirMenuOperacoes(Scanner scanner, Sistema sistema, Cliente cliente) {
        int opcao;
        do {
            System.out.println("\nEscolha uma operação:");
            System.out.println("1. Reservar voo");
            System.out.println("2. Cancelar reserva");
            System.out.println("3. Pesquisar voos");
            System.out.println("0. Sair");
            System.out.print("Opção: ");
            opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1:
                    System.out.print("Digite o código do voo para reserva: ");
                    String codigoReserva = scanner.nextLine();
                    Voo vooReserva = sistema.buscarVooPorCodigo(codigoReserva);
                    if (vooReserva != null) {
                        cliente.reservarVoo(vooReserva);
                    } else {
                        System.out.println("Voo não encontrado.");
                    }
                    break;
                case 2:
                    System.out.print("Digite o código do voo para cancelamento de reserva: ");
                    String codigoCancelamento = scanner.nextLine();
                    cliente.cancelarReserva(codigoCancelamento);
                    break;
                case 3:
                    System.out.print("Origem: ");
                    String origem = scanner.nextLine();
                    System.out.print("Destino: ");
                    String destino = scanner.nextLine();
                    System.out.print("Data: ");
                    String data = scanner.nextLine();
                    System.out.print("Número de passageiros: ");
                    int numeroPassageiros = Integer.parseInt(scanner.nextLine());
                    cliente.pesquisarVoos(sistema, origem, destino, data, numeroPassageiros);
                    break;
                case 0:
                    System.out.println("Encerrando...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }

    private static void cadastrarVoosFicticios(Sistema sistema) {
        sistema.adicionarVoo(new Voo("01/03/2024", "V001", 100.0, "São Paulo", "Rio de Janeiro", 10, "10h"));
        sistema.adicionarVoo(new Voo("02/03/2024", "V002", 150.0, "França", "Brasil", 15, "17h"));
        sistema.adicionarVoo(new Voo("03/03/2024", "V003", 200.0, "Esperança", "Campina", 20,"22h"));
    }
}
