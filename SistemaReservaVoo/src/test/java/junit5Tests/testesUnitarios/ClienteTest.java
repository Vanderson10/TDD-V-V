package junit5Tests.testesUnitarios;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.sistema.reserva.voo.implementation.Cliente;
import org.sistema.reserva.voo.implementation.Sistema;
import org.sistema.reserva.voo.implementation.Voo;

@DisplayName("Testes operações do Cliente")
public class ClienteTest {

    private static Voo voo;
    private static Cliente cliente;

    private static Sistema sistema;

    @BeforeAll
    public static void Initialize(){
        voo = new Voo("01/01/2024", "V001", 100.0, "Origem", "Destino", 100, "10h");
        cliente = new Cliente("João", 2, "123456789");
        sistema = new Sistema();
        sistema.adicionarVoo(new Voo("01/01/2024", "V001", 100.0, "Origem1", "Destino1", 5, "10h"));
        sistema.adicionarVoo(new Voo("01/01/2024", "V002", 150.0, "Origem2", "Destino2", 10, "15h"));
    }

    @Test
    public void testReservarVoo() {
        // Verifica se a reserva é realizada com sucesso
        cliente.reservarVoo(voo);
        Assertions.assertEquals(1, cliente.getVoosReservados().size());
        // Verifica se a reserva não é realizada quando não há lugares suficientes
        Cliente cliente2 = new Cliente("Maria", 5, "987654321");
        cliente2.reservarVoo(voo);
        Assertions.assertEquals(1, cliente2.getVoosReservados().size());
    }

    @Test
    public void testCancelarReserva() {
        cliente.reservarVoo(voo);
        // Verifica se a reserva é cancelada com sucesso
        cliente.cancelarReserva("V001");
        Assertions.assertEquals(0, cliente.getVoosReservados().size());

        // Verifica se não é possível cancelar uma reserva inexistente
        cliente.cancelarReserva("V002");
        Assertions.assertEquals(0, cliente.getVoosReservados().size());
    }

    @Test
    public void testPesquisarVoos() {
        // Verifica se encontra os voos de acordo com os critérios de pesquisa
        cliente.pesquisarVoos(sistema, "Origem1", "Destino1", "01/01/2024", 3);
        cliente.pesquisarVoos(sistema, "Origem2", "Destino2", "01/01/2024", 3);

        // Verifica se não encontra voos quando não há correspondência com os critérios de pesquisa
        cliente.pesquisarVoos(sistema, "Origem3", "Destino3", "01/01/2024", 3);
    }
}