package cl.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import com.relevantcodes.extentreports.ExtentTest;
import cl.qa.helpers.Helper;
import cl.qa.helpers.PageWeb;

/**
 * Clase que engloba los elementos de la Pruebas Funcionales en
 * https://clmconsultores.cl/ según el patrón Page Object Model
 * 
 * @author Noreysi Escalona
 */
public class FunctionalTestsPage extends PageWeb {
	// Atributos
	private By buttonContactUs;
	private By titleFunctionalTest;

	/**
	 * Construye las elementos de la pagina que posteriormente seran llamados
	 * 
	 * @param driver, driver del navegador a utilizar
	 * @param test,   test necesario para la toma de evidencia del reporte
	 * @param TAKE_SS ,boolean que indica si se debe adjuntar la evidencia.
	 * @param SECONDS , necesario para la espera de la toma de evidencia
	 */
	public FunctionalTestsPage(WebDriver driver, ExtentTest test, Boolean TAKE_SS, int seconds) {
		super(driver, test, TAKE_SS, seconds);

		this.buttonContactUs = By.xpath("//*[@id=\"post-12162\"]/div/div[2]/div/div/div/div[2]/a/span");
		this.titleFunctionalTest = By.xpath("//*[@id=\"post-12162\"]/div/div[2]/div/div/div/div[1]/h1/p");

	}

	/**
	 * Construye las elementos de la pagina que posteriormente seran llamados
	 * @param subDir, Subdirectorio en el cual se guardara la imagen.
	 */
	public void clickButtonContactUs(String subDir) {

		// Baja el Scroll hasta el boton Contactanos
		JavascriptExecutor js = (JavascriptExecutor) driver; // declaración del java scrips
		WebElement element = driver.findElement(titleFunctionalTest); // titulo donde bajara el scroll (Variable)
		js.executeScript("arguments[0].scrollIntoView();", element); // declaración
		Helper.addEvidence(TAKE_SS, driver, test, "Se baja el Scroll hasta boton Contactanos", subDir, "funtional_01");

		// Hacer clic en el boton Contactanos
		driver.findElement(buttonContactUs).click();
		Helper.waitSeconds(3);
		Helper.addEvidence(TAKE_SS, driver, test, "Ingreso al formulario de acceso", subDir, "funtional_01");

	}

}
