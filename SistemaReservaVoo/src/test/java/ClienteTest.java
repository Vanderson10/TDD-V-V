import static org.junit.Assert.*;
import org.junit.*;

public class ClienteTest {

    @Test
    public void testReservarVoo() {
        Voo voo = new Voo("01/01/2024", "V001", 100.0, "Origem", "Destino", 100);
        Cliente cliente = new Cliente("João", 2, "123456789");

        // Verifica se a reserva é realizada com sucesso
        cliente.reservarVoo(voo);
        assertEquals(1, cliente.getVoosReservados().size());

        // Verifica se a reserva não é realizada quando não há lugares suficientes
        Cliente cliente2 = new Cliente("Maria", 5, "987654321");
        cliente2.reservarVoo(voo);
        assertEquals(1, cliente2.getVoosReservados().size());
    }

    @Test
    public void testCancelarReserva() {
        Voo voo = new Voo("01/01/2024", "V001", 100.0, "Origem", "Destino", 100);
        Cliente cliente = new Cliente("João", 2, "123456789");
        cliente.reservarVoo(voo);

        // Verifica se a reserva é cancelada com sucesso
        cliente.cancelarReserva("V001");
        assertEquals(0, cliente.getVoosReservados().size());

        // Verifica se não é possível cancelar uma reserva inexistente
        cliente.cancelarReserva("V002");
        assertEquals(0, cliente.getVoosReservados().size());
    }

    @Test
    public void testPesquisarVoos() {
        Sistema sistema = new Sistema();
        sistema.adicionarVoo(new Voo("01/01/2024", "V001", 100.0, "Origem1", "Destino1", 5));
        sistema.adicionarVoo(new Voo("01/01/2024", "V002", 150.0, "Origem2", "Destino2", 10));
        Cliente cliente = new Cliente("João", 2, "123456789");

        // Verifica se encontra os voos de acordo com os critérios de pesquisa
        cliente.pesquisarVoos(sistema, "Origem1", "Destino1", "01/01/2024", 3);
        cliente.pesquisarVoos(sistema, "Origem2", "Destino2", "01/01/2024", 3);

        // Verifica se não encontra voos quando não há correspondência com os critérios de pesquisa
        cliente.pesquisarVoos(sistema, "Origem3", "Destino3", "01/01/2024", 3);
    }
}
