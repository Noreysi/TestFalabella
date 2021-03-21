
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
 * Clase que engloba los elementos de la Página Principal de https://clmconsultores.cl/ 
 * según el patrón Page Object Model
 * @author Noreysi Escalona
 */
public class PrincipalPage extends PageWeb {

	// Atributos
	private By serviceLink;
	private By FunctionalTestsLink;
	private By titleFunctionalTest;
	
	/**
	 * Construye las elementos de la pagina que posteriormente seran llamados
	 * @param driver, driver del navegador a utilizar
	 * @param test, test necesario para la toma de evidencia del reporte
	 * @param TAKE_SS  ,boolean que indica si se debe adjuntar la evidencia.
	 * @param SECONDS , necesario  para la espera de la toma de evidencia
	 */
	public PrincipalPage(WebDriver driver, ExtentTest test, Boolean TAKE_SS, int seconds) {
		super(driver, test, TAKE_SS, seconds);

		this.serviceLink = By.xpath("//*[@id=\"menu-item-12181\"]/a");
		this.FunctionalTestsLink = By.xpath("//*[@id=\"menu-item-12182\"]/a");
		this.titleFunctionalTest = By.xpath("//*[@id=\"post-12162\"]/div/div[1]/div/div[1]/div/h1");

	}
	
	/**
	 * Construye las elementos de la pagina que posteriormente seran llamados
	 * @param subDir, Subdirectorio en el cual se guardara la imagen.
	 */
	public void clickOnServiceLink( String subDir) {
		
		//Espera mientras aparece el nombre Servicio en el HOME y hace clic
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(serviceLink))).click();
		Helper.addEvidence(TAKE_SS, driver, test, "Clic en Servicios", subDir, "principal_01");
		

	}
	
	/**
	 * Construye las elementos de la pagina que posteriormente seran llamados
	 * @param subDir, Subdirectorio en el cual se guardara la imagen.
	 */
	public void clickFunctionalTestsLink( String subDir) {
		
		//Espera mientras aparece el nombre Pruebas Funcionales y hacer clic
		
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(FunctionalTestsLink))).click();
		// Baja el Scroll hasta el boton Contactanos
		JavascriptExecutor js = (JavascriptExecutor) driver; //declaración del java scrips
		WebElement element = driver.findElement(titleFunctionalTest); //titulo donde bajara el scroll (Variable)
		js.executeScript("arguments[0].scrollIntoView();", element); //declaración
		Helper.waitSeconds(5);
		Helper.addEvidence(TAKE_SS, driver, test, "Clic en Pruebas Funcionales", subDir, "principal_02");
		
	}
	
}
