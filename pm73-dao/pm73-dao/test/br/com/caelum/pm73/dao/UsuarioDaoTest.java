package br.com.caelum.pm73.dao;

import br.com.caelum.pm73.dominio.Usuario;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by vinids86 on 02/08/15.
 */
public class UsuarioDaoTest {

    private Session session;
    private UsuarioDao usuarioDao;

    @Before
    public void antes() {
        session = new CriadorDeSessao().getSession();
        usuarioDao = new UsuarioDao(session);

        session.beginTransaction();
    }

    @After
    public void depois() {
        session.getTransaction().rollback();
        session.close();
    }

    @Test
    public void deveEncontrarPeloNomeEEmail() {
        Usuario novoUsuario = new Usuario("Joao da Silva", "joao@dasilva.com.br");
        usuarioDao.salvar(novoUsuario);

        Usuario usuario = usuarioDao.porNomeEEmail("Joao da Silva", "joao@dasilva.com.br");

        assertEquals("Joao da Silva", usuario.getNome());
        assertEquals("joao@dasilva.com.br", usuario.getEmail());
    }

    @Test
    public void deveRetornarNuloSeNaoEncontrarUsuario() {
        Usuario usuario = usuarioDao.porNomeEEmail("Joao Joaquim", "joao@joaquim.com.br");

        assertNull(usuario);
    }
}