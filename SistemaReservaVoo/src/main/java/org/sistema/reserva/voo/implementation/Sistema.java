package org.sistema.reserva.voo.implementation;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Sistema {
    private List<Voo> voos;

    public Sistema() {
        this.voos = new ArrayList<>();
    }

    public void adicionarVoo(Voo voo) {
        this.voos.add(voo);
    }

    public Voo buscarVooPorCodigo(String codigo) {
        for (Voo voo : voos) {
            if (voo.getCodigo().equals(codigo)) {
                return voo;
            }
        }
        return null;
    }

}

