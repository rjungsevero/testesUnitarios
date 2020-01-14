package servico;

import dominio.AnoBissexto;

public class CalculoAnoBissexto {

    public boolean ehBissexto(AnoBissexto ano) {
        return (ano.getAno() % 400 == 0) || (ano.getAno() % 4 == 0) || (ano.getAno() % 100 == 0);
    }

}
