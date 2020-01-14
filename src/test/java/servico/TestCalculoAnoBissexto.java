package servico;

import dominio.AnoBissexto;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestCalculoAnoBissexto {

    private static AnoBissexto ano;
    private static CalculoAnoBissexto calculoAnoBissexto;

    @BeforeClass
    public static void setUp() {
        ano = new AnoBissexto();
        calculoAnoBissexto = new CalculoAnoBissexto();
    }

    @Test
    public void testVerificarAnoBissextoTrue() {

        ano.setAno(2020);
        Boolean atual = calculoAnoBissexto.ehBissexto(ano);
        assertEquals(true, atual);

    }

    @Test
    public void testVerificarAnoBissextoFalse() {

        ano.setAno(2019);
        CalculoAnoBissexto calculoAnoBissexto = new CalculoAnoBissexto();
        Boolean atual = calculoAnoBissexto.ehBissexto(ano);
        assertEquals(false, atual);

    }
}
