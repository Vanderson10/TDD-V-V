package junit5Tests.functionalTests;

import junit5Tests.interfaces.CancelaVooTest;
import junit5Tests.interfaces.PesquisaVooTest;
import junit5Tests.interfaces.ReservaVooTest;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.sistema.reserva.voo.implementation.Cliente;
import org.sistema.reserva.voo.implementation.Sistema;
import org.sistema.reserva.voo.implementation.Voo;

import static org.junit.Assert.assertTrue;

@DisplayName("Testes Funcionais para reserva, cancelamento e busca de voos")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SistemaVooTest {

    private Voo voo;

    private Cliente clienteTest;

    private Sistema sistema;

    @BeforeEach
    @DisplayName("Instância Objetos")
    public void CadastroVoo() {
        voo = new Voo("01/01/2024", "V001", 100.0, "Origem", "Destino", 300, "17h");
        sistema = new Sistema();
        sistema.adicionarVoo(voo);
        clienteTest = new Cliente("Everton", 5, "123456789");
    }

    @ReservaVooTest
    @DisplayName("Reserva com 0 passageiros")
    public void testReservaVooZeroPassageiros() {
        Cliente cliente = new Cliente("Vanderson", 0, "123456789");
        cliente.reservarVoo(voo);
        assertTrue(cliente.getVoosReservados().size() != 0);
    }

    @ReservaVooTest
    @DisplayName("Reserva com -1 passageiros")
    public void testReservaVooNegativoPassageiros() {
        Cliente cliente = new Cliente("Vanderson", -1, "123456789");
        cliente.reservarVoo(voo);
        assertTrue(cliente.getVoosReservados().size() != 0);
    }

    @ParameterizedTest
    @ValueSource(ints = { 2, 250, 299, 300})
    @DisplayName("Reserva com 2 passageiros")
    public void testReservaVooValidoPassageiros(int passageiros) {
        Cliente cliente = new Cliente("Vanderson", passageiros, "123456789");
        cliente.reservarVoo(voo);
        assertTrue(cliente.getVoosReservados().size() == 1);
    }

    @ReservaVooTest
    @DisplayName("Reserva com 301 passageiros")
    public void testReservaVoo301Passageiros() {
        Cliente cliente = new Cliente("Vanderson", 301, "123456789");
        cliente.reservarVoo(voo);
        assertTrue(cliente.getVoosReservados().size() == 0);
    }

    @CancelaVooTest
    @DisplayName("Cancelamento com Código Errado")
    public void testCancelamentoCodigoInvalido() {
        clienteTest.reservarVoo(voo);
        clienteTest.cancelarReserva("CodigoErrado");
        assertTrue(clienteTest.getVoosReservados().size() == 1);
    }

    @CancelaVooTest
    @DisplayName("Cancelamento com Código Correto")
    public void testCancelamentoCodigoValido() {
        clienteTest.reservarVoo(voo);
        clienteTest.cancelarReserva(voo.getCodigo());
        assertTrue(clienteTest.getVoosReservados().size() == 0);
    }

    @PesquisaVooTest
    @Order(1)
    @DisplayName("Busca existente no sistema")
    public void testBuscaVooExistente() {
        assertTrue(clienteTest.pesquisarVoos(sistema, "Origem", "Destino", "01/01/2024", 67));
    }

    @ParameterizedTest
    @Order(2)
    @ValueSource(strings = { "Origem"})
    @DisplayName("Busca inexistente no sistema")
    public void testBuscaVoos(String origem) {
        assertTrue(clienteTest.pesquisarVoos(sistema, origem, "___", "756745", 6789));
    }

}
