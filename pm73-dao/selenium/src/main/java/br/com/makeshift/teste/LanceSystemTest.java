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
public class LanceSystemTest {

    private WebDriver driver;
    private LeiloesPage leiloes;

    @Before
    public void inicializa() {
        driver = new FirefoxDriver();
        leiloes = new LeiloesPage(driver);

        driver.get("http://localhost:8080/apenas-teste/limpa");

        UsuariosPage usuarios = new UsuariosPage(driver);
        usuarios.visita();
        usuarios.novo().cadastra("Vinicius Dias Saraiva", "viniciusdsaraiva@gmail.com");
        usuarios.novo().cadastra("Sofia Rodrigues Saraiva", "sofiarsaraiva@gmail.com");

        LeiloesPage leiloes = new LeiloesPage(driver);
        leiloes.visita();
        leiloes.novo().preenche("Geladeira", 100, "Vinicius Dias Saraiva", false);
    }

    @Test
    public void deveFazerUmLance() {
        DetalhesDoLeilaoPage lances = leiloes.detalhes(1);

        lances.lance("Vinicius Dias Saraiva", 150);

        assertTrue(lances.existeLance("Vinicius Dias Saraiva", 150));
    }

    @After
    public void finaliza() {
        driver.close();
    }
}
