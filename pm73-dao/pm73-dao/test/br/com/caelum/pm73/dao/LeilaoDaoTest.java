package br.com.caelum.pm73.dao;

import br.com.caelum.pm73.dominio.Leilao;
import br.com.caelum.pm73.dominio.Usuario;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by vinids86 on 02/08/15.
 */
public class LeilaoDaoTest {

    private Session session;
    private UsuarioDao usuarioDao;
    private LeilaoDao leilaoDao;

    @Before
    public void antes() {
        session = new CriadorDeSessao().getSession();
        usuarioDao = new UsuarioDao(session);
        leilaoDao = new LeilaoDao(session);

        session.beginTransaction();
    }

    @After
    public void depois() {
        session.getTransaction().rollback();
        session.close();
    }

    @Test
    public void deveContarLeiloesNaoEncerrados() {

        Usuario mauricio = new Usuario("Mauricio", "mauricio@mauricio.com.br");

        Leilao ativo = new Leilao("Geladeira", 1500.0, mauricio, false);
        Leilao encerrado = new Leilao("XBox", 700.0, mauricio, false);
        encerrado.encerra();

        usuarioDao.salvar(mauricio);
        leilaoDao.salvar(ativo);
        leilaoDao.salvar(encerrado);

        long total = leilaoDao.total();

        assertEquals(1L, total);
    }

    @Test
    public void deveTrazerLeiloesNaoEncerradosNoPeriodo() {
        Calendar comecoDoIntervalo = Calendar.getInstance();
        comecoDoIntervalo.add(Calendar.DAY_OF_MONTH, -10);

        Calendar finalDoIntervalo = Calendar.getInstance();

        Usuario mauricio = new Usuario("Mauricio", "mauricio@mauricio.com.br");

        Leilao leilao1 = new Leilao("XBox", 700.0, mauricio, false);
        Calendar dataDoLeilao1 = Calendar.getInstance();
        dataDoLeilao1.add(Calendar.DAY_OF_MONTH, -2);
        leilao1.setDataAbertura(dataDoLeilao1);

        Leilao leilao2 = new Leilao("Geladeira", 1700.0, mauricio, false);
        Calendar dataDoLeilao2 = Calendar.getInstance();
        dataDoLeilao2.add(Calendar.DAY_OF_MONTH, -20);
        leilao2.setDataAbertura(dataDoLeilao2);

        usuarioDao.salvar(mauricio);
        leilaoDao.salvar(leilao1);
        leilaoDao.salvar(leilao2);

        List<Leilao> leiloes = leilaoDao.porPeriodo(comecoDoIntervalo, finalDoIntervalo);

        assertEquals(1, leiloes.size());
        assertEquals("XBox", leiloes.get(0).getNome());
    }

    @Test
    public void naoDeveTrazerLeiloesEncerradosNoPeriodo() {
        Calendar comecoDoIntervalo = Calendar.getInstance();
        comecoDoIntervalo.add(Calendar.DAY_OF_MONTH, -10);

        Calendar finalDoIntervalo = Calendar.getInstance();

        Usuario mauricio = new Usuario("Mauricio", "mauricio@mauricio.com.br");

        Calendar dataLeilao1 = Calendar.getInstance();
        dataLeilao1.add(Calendar.DAY_OF_MONTH, -2);

        Leilao leilao1 = new Leilao("XBox", 700.0, mauricio, false);
        leilao1.setDataAbertura(dataLeilao1);
        leilao1.encerra();

        usuarioDao.salvar(mauricio);
        leilaoDao.salvar(leilao1);

        List<Leilao> leiloes = leilaoDao.porPeriodo(comecoDoIntervalo, finalDoIntervalo);

        assertEquals(0, leiloes.size());
    }

    @Test
    public void deveDeletarUsuario() {
        Usuario usuario = new Usuario("Mauricio", "mauricio@mauricio.com.br");

        usuarioDao.salvar(usuario);
        usuarioDao.deletar(usuario);

        session.flush();
        session.clear();

        Usuario deletado = usuarioDao.porNomeEEmail("Mauricio", "mauricio@mauricio.com.br");

        assertNull(deletado);
    }
}