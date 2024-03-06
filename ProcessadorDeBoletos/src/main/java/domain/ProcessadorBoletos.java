package domain;

import enumeration.StatusFatura;

import java.util.ArrayList;
import java.util.List;

public class ProcessadorBoletos {
    private List<Fatura> faturasProcessadas;

    public ProcessadorBoletos() {
        this.faturasProcessadas = new ArrayList<>();
    }

    public List<Fatura> getFaturasProcessadas() {
        return faturasProcessadas;
    }

    public void setFaturasProcessadas(List<Fatura> faturasProcessadas) {
        this.faturasProcessadas = faturasProcessadas;
    }


}
