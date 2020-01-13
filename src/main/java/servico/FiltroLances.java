package servico;

import dominio.Lance;

import java.util.ArrayList;
import java.util.List;

public class FiltroLances {

    public List<Lance> filtra(List<Lance> lances) {

        List<Lance> resultado = new ArrayList<>();

        lances.forEach(lance -> {
            if (lance.getValor() > 1000 && lance.getValor() < 3000)
                resultado.add(lance);
            else if (lance.getValor() > 500 && lance.getValor() < 700)
                resultado.add(lance);
            else if (lance.getValor() > 5000)
                resultado.add(lance);
        });

        return resultado;
    }
}
