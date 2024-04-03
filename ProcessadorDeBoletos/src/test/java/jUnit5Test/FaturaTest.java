package jUnit5Test;

import domain.Boleto;
import domain.Fatura;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FaturaTest {

    @DisplayName("Teste de criação de faturas.")
    @ParameterizedTest
    @ValueSource(ints={1, 3, 4})
    public void criarBoletoTest(int ids) {
        List<Fatura> faturas = new ArrayList<>();
        faturas.add(new Fatura(ids, LocalDate.now(), 300, "Cliente" + ids));

        for(Fatura f : faturas){
            System.out.println(f.toString());
        }
    }

    @DisplayName("Teste de criação de fatura com valor mínimo.")
    @jUnit5Test.interfaces.FaturaTest
    public void criarFaturaMin() {
        Fatura fatura = new Fatura(2, LocalDate.now(), 1, "Ciclano");
    }

    @DisplayName("Teste de criação de fatura com valor mínimo mais 1.")
    @jUnit5Test.interfaces.FaturaTest
    public void criarFaturaMinMais1() {
        Fatura fatura = new Fatura(3, LocalDate.now(), 2, "Beltrano");
    }

    @DisplayName("Teste de criação de fatura com valor qualquer dentro do intervalo válido.")
    @jUnit5Test.interfaces.FaturaTest
    public void criarFaturaQualquer() {
        Fatura fatura = new Fatura(4, LocalDate.now(), 576, "Andreza");
    }

    @DisplayName("Teste de criação de fatura com valor máximo menos 1")
    @jUnit5Test.interfaces.FaturaTest
    public void criarFaturaMaxMmenos1() {
        Fatura fatura = new Fatura(5, LocalDate.now(), 2999, "Evelyn");
    }

    @DisplayName("Teste de criação de fatura com valor máximo")
    @jUnit5Test.interfaces.FaturaTest
    public void criarFaturaMax() {
        Fatura fatura = new Fatura(6, LocalDate.now(), 3000, "Julio");
    }

    @DisplayName("Teste de criação de fatura com valor máximo mais 1")
    @jUnit5Test.interfaces.FaturaTest
    public void criarFaturaMaxMais1() {
        Fatura fatura = new Fatura(7, LocalDate.now(), 3001, "Miguel");
    }

}
