package servico;

import dominio.Lance;
import dominio.Leilao;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Avaliador {


    private double maiorValor = Double.NEGATIVE_INFINITY;
    private double menorValor = Double.POSITIVE_INFINITY;
    private double media;
    private List<Lance> lances = new ArrayList<>();

    public double getMaiorValor() {
        return maiorValor;
    }

    public double getMenorValor() {
        return menorValor;
    }

    public List<Lance> getLances() {
        return lances;
    }

    public void avalia(Leilao leilao) {

        for (Lance lance : leilao.getLances()) {
            if (lance.getValor() > maiorValor) maiorValor = lance.getValor();
            if (lance.getValor() < menorValor) menorValor = lance.getValor();

        }
    }

    public double valorMedio(Leilao leilao) {

        for (Lance lance : leilao.getLances())
            media = media + lance.getValor();
        return media / leilao.getLances().size();

    }

    public List<Lance> tresMaioresLances(Leilao leilao) {

        lances = leilao.getLances()
                .stream()
                .sorted(Comparator.comparingDouble(Lance::getValor).reversed())
                .collect(Collectors.toList());

        return lances.subList(0, lances.size() > 3 ? 3 : lances.size());
    }
}
