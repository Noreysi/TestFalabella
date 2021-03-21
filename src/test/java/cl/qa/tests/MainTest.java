package cl.qa.tests;


import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import cl.qa.helpers.Helper;
import cl.qa.pages.ContactFormPage;
import cl.qa.pages.FunctionalTestsPage;
import cl.qa.pages.PrincipalPage;




public class MainTest {
	private WebDriver driver;
	private ExtentReports extent;
	private ExtentTest test;
	private static String SUBDIR = "AmbienteBase\\";
	private static Boolean TAKE_SS = true;
	private static int WAITING = 10;


	@BeforeSuite
	public void configExtentReports() {
		// ExtentReports config
		this.extent = new ExtentReports("ExtentReports/ReporteTestFalabella.html", true);
		this.extent.addSystemInfo("Host Name", "Entrevista Falabella");
		this.extent.addSystemInfo("Enviroment", "Automation Testing");
		this.extent.addSystemInfo("User Name", "Noreysi Escalona");
        //llamada a objeto de configuracion de Extent report
		File conf = new File("src/test/resources/extentReports/" + "extent-config.xml");
		this.extent.loadConfig(conf);
	}

	@BeforeMethod
	@Parameters({ "URL_PRINCIPAL" })
	public void configSelenium(String URL_PRINCIPAL) {
		// Selenium config
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("Empresa", "Empresa");
		System.setProperty("webdriver.chrome.driver", "DRIVERS/chromedriver.exe");
		Helper.robotMoveMouse(2000, 2000);
		driver = new ChromeDriver();
		//Implicit Waits No usar si se estan usando explicit wait
		driver.manage().timeouts().implicitlyWait(WAITING, TimeUnit.SECONDS);
		//Maximizar Ventana
		driver.manage().window().maximize();
		//Navegar a url principal
		driver.navigate().to(URL_PRINCIPAL);
	}
	@Test
	public void servicesAvailable () {
		String subDir = SUBDIR + Thread.currentThread().getStackTrace()[1].getMethodName();
		test = extent.startTest("Visualizacion de servicios disponibles en CLM,", "Prueba para validar la visualizacion de servicios disponibles en CLM");
		test.log(LogStatus.INFO, "Prueba para validar la visualizacion de servicios disponibles en CLM");
		PrincipalPage principal = new PrincipalPage(driver, test, TAKE_SS, 20);
		principal.clickOnServiceLink(subDir);
		Assert.assertEquals((driver.findElement(By.xpath("//*[@id=\"menu-item-12182\"]/a"))).getText(), "Pruebas Funcionales");
		Assert.assertEquals((driver.findElement(By.xpath("//*[@id=\"menu-item-12269\"]/a"))).getText(), "Pruebas de Rendimiento");
		Assert.assertEquals((driver.findElement(By.xpath("//*[@id=\"menu-item-12284\"]/a"))).getText(), "Pruebas Mobile");
		
	}
	

	@Test
	public void goToTestFuntional() {
		String subDir = SUBDIR + Thread.currentThread().getStackTrace()[1].getMethodName();
		test = extent.startTest("Prueba de ingreso a Pruebas Funcionales Exitosa", "Prueba para validar el ingreso a Pruebas Funcionales Exitosa");
		test.log(LogStatus.INFO, "Prueba para validar el ingreso a Pruebas Funcionales Exitosa");
		PrincipalPage principal = new PrincipalPage(driver, test, TAKE_SS, 20);
		principal.clickOnServiceLink(subDir);
		principal.clickFunctionalTestsLink(subDir);
		Assert.assertEquals((driver.findElement(By.xpath("//*[@id=\"post-12162\"]/div/div[2]/div/div/div/div[1]/h1"))).getText(), "¿Qué contempla el servicio de Pruebas Funcionales?");
	}
	
	@Test
	public void accessFormContactUs() {
		String subDir = SUBDIR + Thread.currentThread().getStackTrace()[1].getMethodName();
		test = extent.startTest("Prueba de acceso al formulario de Contactanos de forma Exitosa", "Prueba para validar el acceso al formulario de Contactanos de forma Exitosa");
		test.log(LogStatus.INFO, "Prueba para validar el acceso al formulario de Contactanos de forma Exitosa");
		PrincipalPage principal = new PrincipalPage(driver, test, TAKE_SS, 20);
		FunctionalTestsPage functional = new FunctionalTestsPage (driver, test, TAKE_SS, 20);
		principal.clickOnServiceLink(subDir);
		principal.clickFunctionalTestsLink(subDir);
		functional.clickButtonContactUs(subDir);
		Assert.assertEquals((driver.findElement(By.xpath("//*[@id=\"sliders-container\"]/div/div/ul[1]/li/div[1]/div/div[1]/div/div/h2"))).getText(), "Contáctanos");
	}
   
	@Test
	public void failedRequestRequiredFields() {
		String subDir = SUBDIR + Thread.currentThread().getStackTrace()[1].getMethodName();
		test = extent.startTest("Prueba de  validación de los mensajes de error de los campos requeridos", "Prueba para validar los mensajes de error de los campos requeridos");
		test.log(LogStatus.INFO, "Prueba para validar los mensajes de error de los campos requeridos");
		PrincipalPage principal = new PrincipalPage(driver, test, TAKE_SS, 20);
		FunctionalTestsPage functional = new FunctionalTestsPage (driver, test, TAKE_SS, 20);
		ContactFormPage form = new ContactFormPage (driver, test, TAKE_SS, 20);
		principal.clickOnServiceLink(subDir);
		principal.clickFunctionalTestsLink(subDir);
		functional.clickButtonContactUs(subDir);
		form.clickSendButton(subDir);
		String text = driver.findElement(By.xpath("//*[@id=\"wpcf7-f12419-p86-o1\"]/form/div[2]")).getText();
		Assert.assertTrue(text.contains("UNO O MÁS CAMPOS TIENEN UN ERROR. POR FAVOR REVISA E INTÉNTALO DE NUEVO."));
		
	}
	
	@Test
	public void failedRequestWrongFormatFields() {
		String subDir = SUBDIR + Thread.currentThread().getStackTrace()[1].getMethodName();
		test = extent.startTest("Prueba de  validación de los mensajes de error de los campos con formato incorrecto", "Prueba para validar los mensajes de error  de los campos con formato incorrecto");
		test.log(LogStatus.INFO, "Prueba para validar los mensajes de error  de los campos con formato incorrecto");
		PrincipalPage principal = new PrincipalPage(driver, test, TAKE_SS, 20);
		FunctionalTestsPage functional = new FunctionalTestsPage (driver, test, TAKE_SS, 20);
		ContactFormPage form = new ContactFormPage (driver, test, TAKE_SS, 20);
		principal.clickOnServiceLink(subDir);
		principal.clickFunctionalTestsLink(subDir);
		functional.clickButtonContactUs(subDir);
        form.fillWrongFormatFiels(subDir);
		form.clickSendButton(subDir);
		String text = driver.findElement(By.xpath("//*[@id=\"wpcf7-f12419-p86-o1\"]/form/div[2]")).getText();
		Assert.assertTrue(text.contains("UNO O MÁS CAMPOS TIENEN UN ERROR. POR FAVOR REVISA E INTÉNTALO DE NUEVO."));
		
	}


	@Test
	public void successfullyRequest() {
		String subDir = SUBDIR + Thread.currentThread().getStackTrace()[1].getMethodName();
		test = extent.startTest("Prueba de envio de Solicitud de servicio Exitosa", "Prueba de envio de Solicitud de Pruebas Funcionales Exitosa");
		test.log(LogStatus.INFO, "Prueba de envio de Solicitud de Pruebas Funcionales Exitosa en CLM");
		PrincipalPage principal = new PrincipalPage(driver, test, TAKE_SS, 20);
		FunctionalTestsPage functional = new FunctionalTestsPage (driver, test, TAKE_SS, 20);
		ContactFormPage form = new ContactFormPage (driver, test, TAKE_SS, 20);
		principal.clickOnServiceLink(subDir);
		principal.clickFunctionalTestsLink(subDir);
		functional.clickButtonContactUs(subDir);
		form.fillAllFields(subDir);
		form.clickSendButton(subDir);
		Assert.assertEquals((driver.findElement(By.xpath("//*[@id=\"wpcf7-f12419-p86-o1\"]/div"))).getText(), "Gracias por tu mensaje. Ha sido enviado.");
	}
	
	


	

	@AfterMethod
	public void afterMethod(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			test.log(LogStatus.FAIL, "Test failed.- <br>" + result.getThrowable());
		} else if (result.getStatus() == ITestResult.SKIP) {
			test.log(LogStatus.SKIP, "Test skipped.- <br>" + result.getThrowable());
		} else {
			test.log(LogStatus.PASS, "Test passed.-");
		}
		driver.close();
		extent.endTest(test);
	}

	@AfterSuite
	public void closeExtentReports() {
		// Escribimos los datos al reporte.
		extent.flush();
		//driver.close();
	}
}
