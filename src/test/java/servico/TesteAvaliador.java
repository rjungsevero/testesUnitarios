package servico;

import dominio.Lance;
import dominio.Leilao;
import dominio.Usuario;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class TesteAvaliador {

    @Test
    public void testAvaliaMaiorEMenorValor() {
        Usuario raul = new Usuario("Raul");
        Usuario joao = new Usuario("Joao");
        Usuario maria = new Usuario("Maria");

        Leilao leilaoPS3 = new Leilao("PS3");

        leilaoPS3.propoe(new Lance(raul, 800.0));
        leilaoPS3.propoe(new Lance(joao, 700.0));
        leilaoPS3.propoe(new Lance(maria, 600.0));

        Avaliador leiloeiro = new Avaliador();
        leiloeiro.avalia(leilaoPS3);

        assertEquals(800.0, leiloeiro.getMaiorValor(), 0.00001);
        assertEquals(600.0, leiloeiro.getMenorValor(), 0.00001);

    }

    @Test
    public void testMediaLancesPorLeilao() {
        Usuario raul = new Usuario("Raul");
        Usuario joao = new Usuario("Joao");
        Usuario maria = new Usuario("Maria");

        Leilao leilaoPS4 = new Leilao("PS4");

        leilaoPS4.propoe(new Lance(raul, 700.0));
        leilaoPS4.propoe(new Lance(joao, 600.0));
        leilaoPS4.propoe(new Lance(maria, 800.0));

        Avaliador leiloeiro = new Avaliador();

        assertEquals(700.0, leiloeiro.valorMedio(leilaoPS4), 0.00001);
    }

    @Test
    public void testTresMaioresLances() {
        Usuario raul = new Usuario("Raul");
        Usuario joao = new Usuario("Joao");
        Usuario maria = new Usuario("Maria");

        Leilao leilao = new Leilao("PS5");

        leilao.propoe(new Lance(raul, 700.0));
        leilao.propoe(new Lance(joao, 600.0));
        leilao.propoe(new Lance(maria, 800.0));
        leilao.propoe(new Lance(joao, 900.0));

        Avaliador leiloeiro = new Avaliador();
        List<Lance> maiores = leiloeiro.tresMaioresLances(leilao);

        assertEquals(3, maiores.size());
        assertEquals(leilao.getLances().get(3).getValor(), maiores.get(0).getValor(), 0.00001);
        assertEquals(leilao.getLances().get(2).getValor(), maiores.get(1).getValor(), 0.00001);
        assertEquals(leilao.getLances().get(0).getValor(), maiores.get(2).getValor(), 0.00001);
    }

}
