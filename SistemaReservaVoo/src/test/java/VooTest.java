import static org.junit.Assert.*;
import org.junit.*;
import org.sistema.reserva.voo.implementation.Cliente;
import org.sistema.reserva.voo.implementation.Voo;

public class VooTest {

    @Test
    public void testIsDisponivel() {
        Voo voo = new Voo("01/01/2024", "V001", 100.0, "Origem", "Destino", 100, "16h");
        assertTrue(voo.isDisponivel());

        // Reservando o voo
        Cliente cliente = new Cliente("Vanderson", 2, "565675745");
        voo.setCliente(cliente);
        assertTrue(voo.isDisponivel());
    }

    @Test
    public void testGetPrecoTotal() {
        Voo voo = new Voo("01/01/2024", "V001", 100.0, "Origem", "Destino", 5, "17h");
        assertEquals(500.0, voo.getPrecoTotal(), 0.001);

        // Modificando o preço do voo
        voo.setPreco(120.0);
        assertEquals(600.0, voo.getPrecoTotal(), 0.001);
    }

    @Test
    public void testImprimirDetalhes() {
        Voo voo = new Voo("01/01/2024", "V001", 100.0, "Origem", "Destino", 100, "20h");
        Cliente cliente = new Cliente("João", 1, "6476347843");
        voo.setCliente(cliente);

        // Verifica se a impressão não lança exceções
        voo.imprimirDetalhes();
    }
}
