package br.com.caelum.leilao.servico;

import br.com.caelum.leilao.builder.CriadorDeLeilao;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.infra.dao.LeilaoDao;
import br.com.caelum.leilao.infra.email.Carteiro;
import org.junit.Test;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

/**
 * Created by vinids86 on 01/08/15.
 */

public class EncerradorDeLeilaoTest {

    @Test
    public void deveEncerrarLeiloesQueComecaramUmaSemanaAntes() {
        Calendar antiga = Calendar.getInstance();
        antiga.set(1999, 1, 20);

        Leilao leilao1 = new CriadorDeLeilao().para("TV de plasma").naData(antiga).constroi();
        Leilao leilao2 = new CriadorDeLeilao().para("Geladeira").naData(antiga).constroi();
        List<Leilao> leiloesAntigos = Arrays.asList(leilao1, leilao2);

        LeilaoDao daoFalso = mock(LeilaoDao.class);

        when(daoFalso.correntes()).thenReturn(leiloesAntigos);

        Carteiro carteiroFalso = mock(Carteiro.class);

        EncerradorDeLeilao encerrador = new EncerradorDeLeilao(daoFalso, carteiroFalso);
        encerrador.encerra();

        assertEquals(2, encerrador.getTotalEncerrados());
        assertTrue(leilao1.isEncerrado());
        assertTrue(leilao2.isEncerrado());
    }

    @Test
    public void deveAtualizarLeiloesEncerrdos() {
        Calendar antiga = Calendar.getInstance();
        antiga.set(1999, 1, 20);

        Leilao leilao = new CriadorDeLeilao().para("TV de Plasma").naData(antiga).constroi();

        LeilaoDao daoFalso = mock(LeilaoDao.class);
        when(daoFalso.correntes()).thenReturn(Arrays.asList(leilao));

        Carteiro carteiroFalso = mock(Carteiro.class);

        EncerradorDeLeilao encerrador = new EncerradorDeLeilao(daoFalso, carteiroFalso);
        encerrador.encerra();

        verify(daoFalso).atualiza(leilao);
    }

    @Test
    public void deveContinuarAExecucaoMesmoQuandoDaoFalha() {
        Calendar antiga = Calendar.getInstance();
        antiga.set(1999, 1, 20);

        Leilao leilao1 = new CriadorDeLeilao().para("TV de Plasma").naData(antiga).constroi();
        Leilao leilao2 = new CriadorDeLeilao().para("Geladeira").naData(antiga).constroi();

        LeilaoDao daoFalso = mock(LeilaoDao.class);
        Carteiro carteiroFalso = mock(Carteiro.class);

        when(daoFalso.correntes()).thenReturn(Arrays.asList(leilao1, leilao2));
         doThrow(new RuntimeException()).when(daoFalso).atualiza(leilao1);

        EncerradorDeLeilao encerrador = new EncerradorDeLeilao(daoFalso, carteiroFalso);
        encerrador.encerra();

        verify(daoFalso).atualiza(leilao2);
        verify(carteiroFalso).envia(leilao2);

        verify(carteiroFalso, times(0)).envia(leilao1);
    }
}