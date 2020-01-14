package servico;

import builder.CriadorDeLeilao;
import dominio.Lance;
import dominio.Leilao;
import dominio.Usuario;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static builder.LeilaoMatcher.temUmLance;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertThat;

public class TesteFiltroLances {

    private static Usuario joao;
    private static FiltroLances filtro;
    private static List<Lance> resultado;

    @BeforeClass
    public static void setUp() {
        joao = new Usuario("Joao");
        filtro = new FiltroLances();
        resultado = new ArrayList<>();
    }

    @Test
    public void testFiltroLance() {

        resultado = filtro.filtra(Arrays.asList(
                new Lance(joao, 2000),
                new Lance(joao, 1000),
                new Lance(joao, 5000),
                new Lance(joao, 800)));

        assertThat(resultado.size(), equalTo(1));
        assertThat(resultado, hasItem(new Lance(joao, 2000)));

    }

    @Test
    public void testSelecionaLanceMaior5000() {

        resultado = filtro.filtra(Arrays.asList(
                new Lance(joao, 900),
                new Lance(joao, 1000),
                new Lance(joao, 6000),
                new Lance(joao, 800)));

        assertThat(resultado.size(), equalTo(1));
        assertThat(resultado, hasItem(new Lance(joao, 6000)));

    }

    @Test
    public void testEliminaLanceMenor500() {

        resultado = filtro.filtra(Arrays.asList(
                new Lance(joao, 300),
                new Lance(joao, 200),
                new Lance(joao, 50),
                new Lance(joao, 275)));

        assertThat(resultado.size(), equalTo(0));

    }

    @Test
    public void testEliminaLanceEntre700E1000() {

        resultado = filtro.filtra(Arrays.asList(
                new Lance(joao, 800),
                new Lance(joao, 900),
                new Lance(joao, 750),
                new Lance(joao, 995)));

        assertThat(resultado.size(), equalTo(0));

    }

    @Test
    public void testEliminaLanceEntre3000E5000() {

        resultado = filtro.filtra(Arrays.asList(
                new Lance(joao, 4000),
                new Lance(joao, 3500),
                new Lance(joao, 4550),
                new Lance(joao, 3995)));

        assertThat(resultado.size(), equalTo(0));

    }

    @Test(expected = IllegalArgumentException.class)
    public void testLanceComValorNegativoOuIgualAZero() {

        resultado = filtro.filtra(Arrays.asList(
                new Lance(joao, 1000),
                new Lance(joao, -50),
                new Lance(joao, 4550),
                new Lance(joao, 3995)));

    }

    @Test
    public void deveReceberUmLance() {
        Leilao leilao = new CriadorDeLeilao().para("Macbook Pro 15").constroi();
        assertThat(leilao.getLances().size(), equalTo(0));

        Lance lance = new Lance(new Usuario("Steve Jobs"), 2000);
        leilao.propoe(lance);

        assertThat(leilao.getLances().size(), equalTo(1));
        assertThat(leilao, temUmLance(lance));
    }

}
