package br.com.makeshift.teste;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by vinids86 on 02/08/15.
 */
public class LeiloesPage {

    private final WebDriver driver;

    public LeiloesPage(WebDriver driver) {
        this.driver = driver;
    }

    public void visita() {
        driver.get("http://localhost:8080/leiloes");
    }

    public NovoLeilaoPage novo() {
        driver.findElement(By.linkText("Novo Leilão")).click();

        return new NovoLeilaoPage(driver);
    }

    public boolean existe(String produto, double valor, String usuario, boolean usado) {
        return driver.getPageSource().contains(produto) &&
                driver.getPageSource().contains(String.valueOf(valor)) &&
                driver.getPageSource().contains(usado ? "Sim" : "Não");
    }

    public DetalhesDoLeilaoPage detalhes(int position) {
        List<WebElement> elementos = driver.findElements(By.linkText("exibir"));
        elementos.get(position -1).click();

        return new DetalhesDoLeilaoPage(driver);
    }
}
