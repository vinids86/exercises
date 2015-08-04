package br.com.makeshift.teste;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.Assert.assertTrue;

/**
 * Created by vinids86 on 02/08/15.
 */
public class LeiloesSystemTest {

    private WebDriver driver;
    private LeiloesPage leiloes;

    @Before
    public void inicializa() {
        this.driver = new FirefoxDriver();
        this.leiloes = new LeiloesPage(driver);

        UsuariosPage usuarios = new UsuariosPage(driver);
        usuarios.visita();
        usuarios.novo().cadastra("Vinicius Dias Saraiva", "viniciusdsaraiva@gmail.com");
    }

    @Test
    public void deveCadastrarUmLeilao() {
        leiloes.visita();
        NovoLeilaoPage novoLeilao = leiloes.novo();
        novoLeilao.preenche("Geladeira", 123, "Vinicius Dias Saraiva", true);

        assertTrue(leiloes.existe("Geladeira", 123, "Vinicius Dias Saraiva", true));
    }

    @After
    public void finaliza() {
        driver.close();
    }
}
