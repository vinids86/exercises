package br.com.caelum.leilao.dominio;

import static org.junit.Assert.assertEquals;

import br.com.caelum.leilao.br.com.caelum.leilao.teste.CriadorDeLeilao;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by vinids86 on 01/08/15.
 */
public class LeilaoTest {

    private Usuario steveJobs;
    private Usuario billGates;

    @Before
    public void criaUsuarios() {
        this.steveJobs = new Usuario("Steve Jobs");
        this.billGates = new Usuario("Bill Gates");
    }

    @Test
    public void deveReceberUmLance() {
        Leilao leilao = new Leilao("Mackbook Pro 15");
        assertEquals(0, leilao.getLances().size());

        leilao.propoe(new Lance(steveJobs, 2000));
        assertEquals(1, leilao.getLances().size());
        assertEquals(2000.0, leilao.getLances().get(0).getValor(), 0.00001);
    }

    @Test
    public void deveReceberVariosLances() {
        Leilao leilao = new Leilao("Mackbook Pro 15");
        leilao.propoe(new Lance(steveJobs, 2000));
        leilao.propoe(new Lance(billGates, 3000));

        assertEquals(2, leilao.getLances().size());
        assertEquals(2000.0, leilao.getLances().get(0).getValor(), 0.00001);
        assertEquals(3000.0, leilao.getLances().get(1).getValor(), 0.00001);
    }

    @Test
    public void naoDeveAceitarDoisLancesSeguidosDoMesmoUsuario() {
        Leilao leilao = new Leilao("Mackbook Pro 15");

        leilao.propoe(new Lance(steveJobs, 2000));
        leilao.propoe(new Lance(steveJobs, 3000));

        assertEquals(1, leilao.getLances().size());
        assertEquals(2000.0, leilao.getLances().get(0).getValor(), 0.00001);
    }

    @Test
    public void naoDeveAceitarMaisDoQue5LancesDeUmMesmoUsuario() {
        Leilao leilao = new CriadorDeLeilao().para("Mackbook Pro 15")
                .lance(steveJobs, 2000)
                .lance(billGates, 3000)
                .lance(steveJobs, 4000)
                .lance(billGates, 5000)
                .lance(steveJobs, 6000)
                .lance(billGates, 7000)
                .lance(steveJobs, 8000)
                .lance(billGates, 9000)
                .lance(steveJobs, 10000)
                .lance(billGates, 11000)
                .lance(steveJobs, 12000)
                .constroi();

        assertEquals(10, leilao.getLances().size());
        assertEquals(11000.0, leilao.getLances().get(leilao.getLances().size() - 1).getValor(), 0.00001);
    }
}