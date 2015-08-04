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
public class UsuarioSystemTest {

    private WebDriver driver;
    private UsuariosPage usuarios;

    @Before
    public void inicializa() {
        this.driver = new FirefoxDriver();
        this.usuarios = new UsuariosPage(driver);
    }

    @Test
    public void deveAdicionarUmUsuario() {
        usuarios.visita();
        usuarios.novo().cadastra("Vinicius Dias Saraiva", "viniciusdsaraiva@gmail.com");

        assertTrue(usuarios.existeNaListagem("Vinicius Dias Saraiva", "viniciusdsaraiva@gmail.com"));
    }

    @After
    public void finaliza() {
        driver.close();
    }
}
