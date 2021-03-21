package cl.qa.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import com.github.javafaker.Faker;
import com.relevantcodes.extentreports.ExtentTest;
import cl.qa.helpers.Helper;
import cl.qa.helpers.PageWeb;

/**
 * Clase que engloba los elementos del Formulario de Contacto en
 * https://clmconsultores.cl/ según el patrón Page Object Model
 * 
 * @author Noreysi Escalona
 */
public class ContactFormPage extends PageWeb {

	// Atributos
	private By name;
	private By email;
	private By phone;
	private By company;
	private By position;
	private By service;
	private By cuestionDescription;
	private By buttonSend;
	private Faker faker;

	/**
	 * Construye las elementos de la pagina que posteriormente seran llamados
	 * 
	 * @param driver, driver del navegador a utilizar
	 * @param test,   test necesario para la toma de evidencia del reporte
	 * @param TAKE_SS ,boolean que indica si se debe adjuntar la evidencia.
	 * @param SECONDS , necesario para la espera de la toma de evidencia
	 */
	public ContactFormPage(WebDriver driver, ExtentTest test, Boolean TAKE_SS, int seconds) {
		super(driver, test, TAKE_SS, seconds);

		this.name = By.name("nombrecompleto");
		this.email = By.name("email");
		this.phone = By.name("telefono");
		this.company = By.name("empresa");
		this.position = By.name("cargo");
		this.service = By.name("servicios");
		this.cuestionDescription = By.name("consulta");
		this.buttonSend = By.xpath("//*[@id=\"wpcf7-f12419-p86-o1\"]/form/p/input");
	    this.faker = new Faker();

	}
	
	/**
	 * Construye las elementos de la pagina que posteriormente seran llamados
	 * @param subDir, Subdirectorio en el cual se guardara la imagen.
	 */
	public void fillAllFields(String subDir) {


		String fakeName = faker.name().fullName();
		String fakeEmail = faker.internet().emailAddress();
		String fakePhone = faker.phoneNumber().cellPhone();
		String fakeCompany = faker.company().name();
		String fakePosition = faker.job().title();
		String fakeCuestionDescription = faker.lorem().paragraph();
		
		
		// Espera mientras aparece el formulario e ingresa el nombre
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(name))).sendKeys(fakeName);
		Helper.waitSeconds(1);
		Helper.addEvidence(TAKE_SS, driver, test, "Ingresar el nombre en el formulario", subDir, "funtionalform_01");

		// Ingresar el Email en el formulario
		driver.findElement(email).sendKeys(fakeEmail);
		Helper.waitSeconds(2);
		
		// Ingresar el telefono en el formulario
		driver.findElement(phone).sendKeys(fakePhone);
		Helper.waitSeconds(2);

		// Ingresar la empresa
		driver.findElement(company).sendKeys(fakeCompany);
		Helper.waitSeconds(2);

		// Ingresar la empresa
		driver.findElement(position).sendKeys(fakePosition);

		JavascriptExecutor js = (JavascriptExecutor) driver; // declaración del java scrips
		WebElement element = driver.findElement(name); // titulo donde bajara el scroll (Variable)
		js.executeScript("arguments[0].scrollIntoView();", element);

		// Ingresar el servicio
		Select servicio = new Select(driver.findElement(service));
		servicio.selectByVisibleText("Pruebas Funcionales");

		// Ingresar la Consulta
		driver.findElement(cuestionDescription).sendKeys(fakeCuestionDescription);
		Helper.waitSeconds(1);
		Helper.addEvidence(TAKE_SS, driver, test, "Ingresar todos los campos en el formulario", subDir, "funtional_02");

	}
	
	/**
	 * Construye las elementos de la pagina que posteriormente seran llamados
	 * @param subDir, Subdirectorio en el cual se guardara la imagen.
	 */
	public void fillWrongFormatFiels(String subDir) {

		// Ingresar el Email en el formulario
		driver.findElement(email).sendKeys("prueba");

		// Ingresar el telefono en el formulario
		driver.findElement(phone).sendKeys("prueba");

		Helper.waitSeconds(5);
		Helper.addEvidence(TAKE_SS, driver, test, "Ingresar los campos incorrectos en el formulario", subDir,
				"funtional_02");

	}
	
	/**
	 * Construye las elementos de la pagina que posteriormente seran llamados
	 * @param subDir, Subdirectorio en el cual se guardara la imagen.
	 */
	public void clickSendButton(String subDir) {

		// Presionar Enviar
		JavascriptExecutor js = (JavascriptExecutor) driver; // declaración del java scrips
		WebElement element = driver.findElement(buttonSend); // titulo donde bajara el scroll (Variable)
		js.executeScript("arguments[0].scrollIntoViewIfNeeded(true);", element); // declaración
		Helper.waitSeconds(2);
		element.click();

		Helper.addEvidence(TAKE_SS, driver, test, " Envio de Informacion con Exito", subDir, "funtional_03");
		Helper.waitSeconds(2);
	}

}
