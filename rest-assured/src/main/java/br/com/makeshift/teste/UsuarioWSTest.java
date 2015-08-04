package br.com.makeshift.teste;

import br.com.makeshift.modelo.Usuario;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.path.xml.XmlPath;
import org.junit.Before;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.get;
import static com.jayway.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

/**
 * Created by vinids86 on 02/08/15.
 */

public class UsuarioWSTest {

    private Usuario mauricio;
    private Usuario guilherme;

    @Before
    public void setUp() {
        mauricio = new Usuario(1l, "Mauricio Aniche", "mauricio.aniche@caelum.com.br");
        guilherme = new Usuario(2l, "Guilherme Silveira", "guilherme.silveira@caelum.com.br");
    }

    @Test
    public void deveRetornarListaDeUsuarios() {

        XmlPath path = given()
                .header("Accept", "application/xml")
                .get("/usuarios")
                .andReturn()
                .xmlPath();

        Usuario usuario1 = path.getObject("list.usuario[0]", Usuario.class);
        Usuario usuario2 = path.getObject("list.usuario[1]", Usuario.class);

        assertEquals(mauricio, usuario1);
        assertEquals(guilherme, usuario2);
    }

    @Test
    public void deveRetornarUsuarioPeloId() {
        JsonPath path = given()
                .header("Accept", "application/json")
                .parameter("usuario.id", 1)
                .get("/usuarios/show")
                .andReturn()
                .jsonPath();

        Usuario usuario = path.getObject("usuario", Usuario.class);

        assertEquals(mauricio, usuario);
    }

    @Test
    public void deveAdicionarUmUsuario() {
        Usuario vinicius = new Usuario("Vinicius Dias Saraiva", "viniciusdsaraiva@gmail.com");

        XmlPath path = given()
                .header("Accept", "application/xml")
                .contentType("application/xml")
                .body(vinicius)
                .expect()
                .statusCode(200)
                .when()
                .post("/usuarios")
                .andReturn()
                .xmlPath();

        Usuario resposta = path.getObject("usuario", Usuario.class);


        assertEquals("Vinicius Dias Saraiva", resposta.getNome());
        assertEquals("viniciusdsaraiva@gmail.com", resposta.getEmail());
    }
}
