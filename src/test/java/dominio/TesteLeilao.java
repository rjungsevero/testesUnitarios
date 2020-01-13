package dominio;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TesteLeilao {

    @Test
    public void testRealizaApenasUmLance() {

        Leilao leilao = new Leilao("Macbook Pro 15");

        assertEquals(0, leilao.getLances().size());

        Usuario steve = new Usuario("Steve Jobs");
        leilao.propoe(new Lance(steve, 2000.0));

        assertEquals(2000.0, leilao.getLances().get(0).getValor(), 0.00001);

    }

    @Test
    public void testRealizaVariosLances() {

        Leilao leilao = new Leilao("Macbook Pro 15");
        leilao.propoe(new Lance(new Usuario("Steve Jobs"), 2000.0));
        leilao.propoe(new Lance(new Usuario("Steve Wozniak"), 3000.0));

        assertEquals(2, leilao.getLances().size());
        assertEquals(2000.0, leilao.getLances().get(0).getValor(), 0.00001);
        assertEquals(3000.0, leilao.getLances().get(1).getValor(), 0.00001);

    }

    @Test
    public void testNaoAceitaDoisLancesSeguidosDoMesmoUsuario() {

        Leilao leilao = new Leilao("Macbook Pro 15");
        Usuario steve = new Usuario("Steve Jobs");

        leilao.propoe(new Lance(steve, 2000.0));
        leilao.propoe(new Lance(steve, 3000.0));

        assertEquals(1, leilao.getLances().size());
        assertEquals(2000.0, leilao.getLances().get(0).getValor(), 0.00001);
    }

    @Test
    public void testNaoAceitaMaisQueCincoLancesPorUsuario() {
        Leilao leilao = new Leilao("Macbook Pro 15");
        Usuario steve = new Usuario("Steve Jobs");
        Usuario bill = new Usuario("Bill Gates");

        leilao.propoe(new Lance(steve, 2000.0));
        leilao.propoe(new Lance(bill, 3000.0));

        leilao.propoe(new Lance(steve, 4000.0));
        leilao.propoe(new Lance(bill, 5000.0));

        leilao.propoe(new Lance(steve, 6000.0));
        leilao.propoe(new Lance(bill, 7000.0));

        leilao.propoe(new Lance(steve, 8000.0));
        leilao.propoe(new Lance(bill, 9000.0));

        leilao.propoe(new Lance(steve, 10000.0));
        leilao.propoe(new Lance(bill, 11000.0));

        leilao.propoe(new Lance(steve, 12000.0));

        assertEquals(10, leilao.getLances().size());
        assertEquals(11000.0, leilao.getLances().get(9).getValor(), 0.00001);
    }

    @Test
    public void testDobraLanceDeUmUsuario() {
        Leilao leilao = new Leilao("Macbook Pro 15");
        Usuario steve = new Usuario("Steve Jobs");
        Usuario bill = new Usuario("Bill Gastes");

        leilao.propoe(new Lance(steve, 3000.0));
        leilao.propoe(new Lance(bill, 4000.0));
        leilao.dobraLance(steve);

        assertEquals(6000.0, leilao.getLances().get(2).getValor(), 0.00001);

    }

    @Test
    public void testNaoDobrarLanceCasoNaoHajaLanceAnterior() {
        Leilao leilao = new Leilao("Macbook Pro 15");
        Usuario steve = new Usuario("Steve Jobs");

        leilao.dobraLance(steve);
        assertEquals(0, leilao.getLances().size());
    }
}
