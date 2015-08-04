package br.com.caelum.leilao.servico;

import br.com.caelum.leilao.builder.CriadorDeLeilao;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Pagamento;
import br.com.caelum.leilao.dominio.Usuario;
import br.com.caelum.leilao.infra.dao.LeilaoDao;
import br.com.caelum.leilao.infra.dao.RepositorioDePagamentos;
import br.com.caelum.leilao.infra.relogio.Relogio;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.Arrays;
import java.util.Calendar;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by vinids86 on 01/08/15.
 */
public class GeradorDePagamentoTest {

    @Test
    public void deveGerarPagamentoParaUmLeilaoEncerrado() {
        LeilaoDao leiloes = mock(LeilaoDao.class);
        RepositorioDePagamentos pagamentos = mock(RepositorioDePagamentos.class);
        Avaliador avaliador = mock(Avaliador.class);

        Leilao leilao = new CriadorDeLeilao().para("Playstation")
                .lance(new Usuario("José da Silva"), 2000.0)
                .lance(new Usuario("Maria Pereira"), 2500.0)
                .constroi();

        when(leiloes.encerrados()).thenReturn(Arrays.asList(leilao));
        when(avaliador.getMaiorLance()).thenReturn(2500.0);

        GeradorDePagamento gerador = new GeradorDePagamento(leiloes, pagamentos, avaliador);
        gerador.gera();

        ArgumentCaptor<Pagamento> argumento = ArgumentCaptor.forClass(Pagamento.class);
        verify(pagamentos).salva(argumento.capture());

        Pagamento pagamentoGerado = argumento.getValue();

        assertEquals(2500.0, pagamentoGerado.getValor(), 0.00001);
    }

    @Test
    public void deveEmpurrarParaOProximoDiaUtil() {
        LeilaoDao leiloes = mock(LeilaoDao.class);
        RepositorioDePagamentos pagamentos = mock(RepositorioDePagamentos.class);
        Relogio relogio = mock(Relogio.class);

        Leilao leilao = new CriadorDeLeilao().para("Playstation")
                .lance(new Usuario("José da Silva"), 2000)
                .lance(new Usuario("Maria Pereira"), 2500)
                .constroi();

        when(leiloes.encerrados()).thenReturn(Arrays.asList(leilao));

        Calendar sabado = Calendar.getInstance();
        sabado.set(2012, Calendar.APRIL, 7);

        when(relogio.hoje()).thenReturn(sabado);

        GeradorDePagamento gerado = new GeradorDePagamento(leiloes, pagamentos, new Avaliador(), relogio);
        gerado.gera();

        ArgumentCaptor<Pagamento> argumento = ArgumentCaptor.forClass(Pagamento.class);
        verify(pagamentos).salva(argumento.capture());

        Pagamento pagamentoGerado = argumento.getValue();

        assertEquals(Calendar.MONDAY, pagamentoGerado.getData().get(Calendar.DAY_OF_WEEK));
        assertEquals(9, pagamentoGerado.getData().get(Calendar.DAY_OF_MONTH));
    }
}