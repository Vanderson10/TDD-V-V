import static org.junit.Assert.*;
import org.junit.*;

public class VooTest {

    @Test
    public void testIsDisponivel() {
        Voo voo = new Voo("01/01/2024", "V001", 100.0, "Origem", "Destino", 100);
        assertTrue(voo.isDisponivel());

        // Reservando o voo
        Cliente cliente = new Cliente("Vanderson");
        voo.setCliente(cliente);
        assertFalse(voo.isDisponivel());
    }

    @Test
    public void testGetPrecoTotal() {
        Voo voo = new Voo("01/01/2024", "V001", 100.0, "Origem", "Destino", 5);
        assertEquals(500.0, voo.getPrecoTotal(), 0.001);

        // Modificando o preço do voo
        voo.setPreco(120.0);
        assertEquals(600.0, voo.getPrecoTotal(), 0.001);
    }

    @Test
    public void testImprimirDetalhes() {
        Voo voo = new Voo("01/01/2024", "V001", 100.0, "Origem", "Destino", 100);
        Cliente cliente = new Cliente("João");
        voo.setCliente(cliente);

        // Verifica se a impressão não lança exceções
        voo.imprimirDetalhes();
    }
}
