package funcionalTest;

import domain.Fatura;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class FaturaFuncTest {

    @Test
    public void criarFaturaMinMenos1() {
        Fatura fatura = new Fatura(1, LocalDate.now(), 0, "Fulano");
    }

    @Test
    public void criarFaturaMin() {
        Fatura fatura = new Fatura(2, LocalDate.now(), 1, "Ciclano");
    }

    @Test
    public void criarFaturaMinMais1() {
        Fatura fatura = new Fatura(3, LocalDate.now(), 2, "Beltrano");
    }

    @Test
    public void criarFaturaQualquer() {
        Fatura fatura = new Fatura(4, LocalDate.now(), 576, "Andreza");
    }

    @Test
    public void criarFaturaMaxMmenos1() {
        Fatura fatura = new Fatura(5, LocalDate.now(), 2999, "Evelyn");
    }

    @Test
    public void criarFaturaMax() {
        Fatura fatura = new Fatura(6, LocalDate.now(), 3000, "Julio");
    }

    @Test
    public void criarFaturaMaxMais1() {
        Fatura fatura = new Fatura(7, LocalDate.now(), 3001, "Miguel");
    }

}
