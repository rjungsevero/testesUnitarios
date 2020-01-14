package dominio;

import builder.CriadorDeLeilao;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class TesteLeilao {

    private Usuario steve;
    private Usuario bill;
    private Leilao leilao;

    @Before
    public void setUp() {

        this.steve = new Usuario("Steve Jobs");
        this.bill = new Usuario("Bill Gates");
        this.leilao = new Leilao("Macbook Pro 15");

    }

    @Test
    public void testRealizaApenasUmLance() {

        leilao = new CriadorDeLeilao()
                .para("Macbook Pro 15")
                .lance(steve, 2000.0)
                .constroi();

        assertThat(leilao.getLances(), hasItem(new Lance(steve, 2000.0)));

    }

    @Test
    public void testRealizaVariosLances() {

        leilao = new CriadorDeLeilao()
                .para("Macbook Pro 15")
                .lance(steve, 2000.0)
                .lance(bill, 3000.0)
                .constroi();

        assertThat(leilao.getLances().size(), equalTo(2));
        assertThat(leilao.getLances(), hasItems(new Lance(steve, 2000.0), new Lance(bill, 3000.0)));

    }

    @Test
    public void testNaoAceitaDoisLancesSeguidosDoMesmoUsuario() {

        leilao = new CriadorDeLeilao()
                .para("Macbook Pro 15")
                .lance(steve, 2000.0)
                .lance(steve, 3000.0)
                .constroi();

        assertThat(leilao.getLances().size(), equalTo(1));
        assertThat(leilao.getLances(), hasItem(new Lance(steve, 2000.0)));

    }

    @Test
    public void testNaoAceitaMaisQueCincoLancesPorUsuario() {

        leilao = new CriadorDeLeilao()
                .para("MacBook Pro 15")
                .lance(steve, 2000.0)
                .lance(bill, 3000.0)
                .lance(steve, 4000.0)
                .lance(bill, 5000.0)
                .lance(steve, 6000.0)
                .lance(bill, 7000.0)
                .lance(steve, 8000.0)
                .lance(bill, 9000.0)
                .lance(steve, 10000.0)
                .lance(bill, 11000.0)
                .lance(steve, 12000.0)
                .constroi();

        assertThat(leilao.getLances().size(), equalTo(10));
        assertThat(leilao.getLances().get(9).getValor(), equalTo(11000.0));

    }

    @Test
    public void testDobraLanceDeUmUsuario() {

        leilao = new CriadorDeLeilao()
                .para("MacBook Pro 15")
                .lance(steve, 3000.0)
                .lance(bill, 4000.0)
                .constroi();
        leilao.dobraLance(steve);

        assertThat(leilao.getLances().get(2).getValor(), equalTo(6000.0));

    }

    @Test
    public void testNaoDobrarLanceCasoNaoHajaLanceAnterior() {

        leilao.dobraLance(steve);

        assertThat(leilao.getLances().size(), equalTo(0));

    }
}
