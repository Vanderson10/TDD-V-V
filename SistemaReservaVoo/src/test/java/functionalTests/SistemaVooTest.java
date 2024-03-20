package functionalTests;

import org.junit.Before;
import org.junit.Test;
import org.sistema.reserva.voo.implementation.Cliente;
import org.sistema.reserva.voo.implementation.Sistema;
import org.sistema.reserva.voo.implementation.Voo;


import static org.junit.Assert.*;

public class SistemaVooTest {

    private Voo voo;

    private Cliente clienteTest;

    private Sistema sistema;

    @Before
    public void CadastroVoo() {
        voo = new Voo("01/01/2024", "V001", 100.0, "Origem", "Destino", 300, "17h");
        sistema = new Sistema();
        sistema.adicionarVoo(voo);
        clienteTest = new Cliente("Everton", 5, "123456789");
    }

    @Test
    public void testReservaVooZeroPassageiros() {
        Cliente cliente = new Cliente("Vanderson", 0, "123456789");
        cliente.reservarVoo(voo);
        assertTrue(cliente.getVoosReservados().size() == 0);
    }

    @Test
    public void testReservaVooNegativoPassageiros() {
        Cliente cliente = new Cliente("Vanderson", -1, "123456789");
        cliente.reservarVoo(voo);
        assertTrue(cliente.getVoosReservados().size() == 0);
    }

    @Test
    public void testReservaVooValidoPassageiros() {
        Cliente cliente = new Cliente("Vanderson", 2, "123456789");
        cliente.reservarVoo(voo);
        assertTrue(cliente.getVoosReservados().size() == 1);
    }

    @Test
    public void testReservaVoo250Passageiros() {
        Cliente cliente = new Cliente("Vanderson", 250, "123456789");
        cliente.reservarVoo(voo);
        assertTrue(cliente.getVoosReservados().size() == 1);
    }

    @Test
    public void testReservaVoo299Passageiros() {
        Cliente cliente = new Cliente("Vanderson", 299, "123456789");
        cliente.reservarVoo(voo);
        assertTrue(cliente.getVoosReservados().size() == 1);
    }

    @Test
    public void testReservaVoo300Passageiros() {
        Cliente cliente = new Cliente("Vanderson", 300, "123456789");
        cliente.reservarVoo(voo);
        assertTrue(cliente.getVoosReservados().size() == 1);
    }

    @Test
    public void testReservaVoo301Passageiros() {
        Cliente cliente = new Cliente("Vanderson", 301, "123456789");
        cliente.reservarVoo(voo);
        assertTrue(cliente.getVoosReservados().size() == 0);
    }

    @Test
    public void testCancelamentoCodigoInvalido() {
        clienteTest.reservarVoo(voo);
        clienteTest.cancelarReserva("CodigoErrado");
        assertTrue(clienteTest.getVoosReservados().size() == 1);
    }

    @Test
    public void testCancelamentoCodigoValido() {
        clienteTest.reservarVoo(voo);
        clienteTest.cancelarReserva(voo.getCodigo());
        assertTrue(clienteTest.getVoosReservados().size() == 0);
    }

    @Test
    public void testBuscaVooExistente() {
        assertTrue(clienteTest.pesquisarVoos(sistema, "Origem", "Destino", "01/01/2024", 67));
    }

    @Test
    public void testBuscaVooInexistente() {
        assertFalse(clienteTest.pesquisarVoos(sistema, "nada", "___", "756745", 6789));
    }

}
