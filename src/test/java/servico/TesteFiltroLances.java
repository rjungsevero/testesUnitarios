package servico;

import dominio.Lance;
import dominio.Usuario;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TesteFiltroLances {

    @Test
    public void testFiltroLance() {
        Usuario joao = new Usuario("Joao");

        FiltroLances filtro = new FiltroLances();
        List<Lance> resultado = filtro.filtra(Arrays.asList(
                new Lance(joao, 2000),
                new Lance(joao, 1000),
                new Lance(joao, 5000),
                new Lance(joao, 800)));

        assertEquals(1, resultado.size());
        assertEquals(2000, resultado.get(0).getValor(), 0.00001);

    }

    @Test
    public void testSelecionaLanceMaior5000() {
        Usuario joao = new Usuario("Joao");

        FiltroLances filtro = new FiltroLances();
        List<Lance> resultado = filtro.filtra(Arrays.asList(
                new Lance(joao, 900),
                new Lance(joao, 1000),
                new Lance(joao, 6000),
                new Lance(joao, 800)));

        assertEquals(1, resultado.size());
        assertEquals(6000, resultado.get(0).getValor(), 0.00001);

    }

    @Test
    public void testEliminaLanceMenor500() {
        Usuario joao = new Usuario("Joao");

        FiltroLances filtro = new FiltroLances();
        List<Lance> resultado = filtro.filtra(Arrays.asList(
                new Lance(joao, 300),
                new Lance(joao, 200),
                new Lance(joao, 50),
                new Lance(joao, 275)));

        assertEquals(0, resultado.size());

    }

    @Test
    public void testEliminaLanceEntre700E1000() {
        Usuario joao = new Usuario("Joao");

        FiltroLances filtro = new FiltroLances();
        List<Lance> resultado = filtro.filtra(Arrays.asList(
                new Lance(joao, 800),
                new Lance(joao, 900),
                new Lance(joao, 750),
                new Lance(joao, 995)));

        assertEquals(0, resultado.size());

    }

    @Test
    public void testEliminaLanceEntre3000E5000() {
        Usuario joao = new Usuario("Joao");

        FiltroLances filtro = new FiltroLances();
        List<Lance> resultado = filtro.filtra(Arrays.asList(
                new Lance(joao, 4000),
                new Lance(joao, 3500),
                new Lance(joao, 4550),
                new Lance(joao, 3995)));

        assertEquals(0, resultado.size());

    }

}
