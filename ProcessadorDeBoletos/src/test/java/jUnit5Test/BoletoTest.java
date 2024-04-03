package jUnit5Test;

import domain.Boleto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BoletoTest {


    @DisplayName("Teste de criação de boletos.")
    @ParameterizedTest
    @ValueSource(strings = {"1a", "2b", "3c"})
    public void criarBoletoTest(String codigos) {
        List<Boleto> boletos = new ArrayList<>();
        boletos.add(new Boleto(codigos, LocalDate.now(), 100));

        for(Boleto b : boletos){
            System.out.println(b.toString());
        }
    }


}
