package servico;

import builder.CriadorDeLeilao;
import dominio.Lance;
import dominio.Leilao;
import dominio.Usuario;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class TesteAvaliador {

    private static Avaliador leiloeiro;
    private static Usuario raul;
    private static Usuario claudia;
    private static Usuario maria;

    @BeforeClass
    public static void setUp() {
        leiloeiro = new Avaliador();
        raul = new Usuario("Raul");
        claudia = new Usuario("Claudia");
        maria = new Usuario("Maria");
    }

    @Test
    public void testAvaliaMaiorEMenorValor() {

        Leilao leilao = new CriadorDeLeilao().para("PlayStation 3")
                .lance(raul, 800.0)
                .lance(claudia, 700.0)
                .lance(maria, 600.0)
                .constroi();

        leiloeiro.avalia(leilao);

        assertThat(leiloeiro.getMaiorValor(), equalTo(800.0));
        assertThat(leiloeiro.getMenorValor(), equalTo(600.0));

    }

    @Test
    public void testMediaLancesPorLeilao() {

        Leilao leilao = new CriadorDeLeilao().para("PlayStation 3")
                .lance(raul, 700.0)
                .lance(claudia, 600.0)
                .lance(maria, 800.0)
                .constroi();

        assertEquals(700.0, leiloeiro.valorMedio(leilao), 0.00001);
    }

    @Test
    public void testTresMaioresLances() {

        Leilao leilao = new CriadorDeLeilao().para("PlayStation 3")
                .lance(raul, 700.0)
                .lance(claudia, 600.0)
                .lance(maria, 800.0)
                .lance(claudia, 900.0)
                .constroi();

        List<Lance> maiores = leiloeiro.tresMaioresLances(leilao);

        assertEquals(3, maiores.size());
        assertThat(maiores, hasItems(
                new Lance(claudia, 900.0),
                new Lance(maria, 800.0),
                new Lance(raul, 700.0)));
    }

    @Test(expected = RuntimeException.class)
    public void testLeilaoSemlances() {
        Leilao leilao = new CriadorDeLeilao()
                .para("PlayStation 3")
                .constroi();
        leiloeiro.avalia(leilao);
    }

}
