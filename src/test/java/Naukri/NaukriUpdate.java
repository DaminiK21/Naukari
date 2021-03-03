package Naukri;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.w3c.dom.Text;

public class NaukriUpdate extends ConfigReader {

	// public static void main(String[] args) throws InterruptedException,
	// IOException {

	WebDriver driver;

	@BeforeMethod
	public void doSetup() throws IOException {
		System.setProperty("webdriver.chrome.driver", "G:\\eclipse-java-2019-09-R-win32-x86_64\\eclipse\\Chrome 88\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(500, TimeUnit.SECONDS);
		String url = new ConfigReader().getProperty("url");

		driver.get(url);
	}

	@Test
	public void test() throws InterruptedException, IOException {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		Thread.sleep(10000);
		Set<String> window = driver.getWindowHandles();

		for (String string : window) {
			driver.switchTo().window(string);
			if (driver.getTitle().contains("Jobs - Recruitment")) {
				driver.switchTo().window(string);
				System.out.println("done=" + driver.getTitle());
				break;
			}
		}

		driver.findElement(By.id("login_Layer")).click();
		WebElement email = driver
				.findElement(By.xpath("//input[@placeholder='Enter your active Email ID / Username']"));

		String a = driver.findElement(By.xpath("//a[text()='Register for free']")).getText();
		email.sendKeys(new ConfigReader().getProperty("email"));

		driver.findElement(By.xpath("//input[@type='password']")).sendKeys(new ConfigReader().getProperty("password"));

		driver.findElement(By.xpath("//input[@type='password']//following::button[text()='Login']")).click();
		Thread.sleep(5000);
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//*[text()='My Naukri']"))).build().perform();
		driver.findElement(By.xpath("//a[text()='Edit Profile']")).click();

		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		
		js.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//*[text()='Attach Resume']")));
		Thread.sleep(5000);
		
		WebElement attachment= driver.findElement(By.xpath("//input[@id='attachCV']"));
		//String title=driver.findElement(By.xpath("//b[@class='truncate exten']")).getText().replace(".", "@").split("@")[1].trim();
		WebElement title=driver.findElement(By.xpath("//div[@class='cvPreview']//b"));
		System.out.println(title.getText());
		//System.out.println(title.contains("docx"));
		if(title.getText().contains("docx"))
		{
			
			//Runtime.getRuntime().exec("G:\\eclipse-java-2019-09-R-win32-x86_64\\NaukriProfileUpdate\\Naukari\\Resume\\pdfFile.exe");
			//attachment.sendKeys(System.getProperty("user.dir")+"\\src\\test\\DaminiKadam_QA_3years.pdf");
			attachment.sendKeys("G:\\eclipse-java-2019-09-R-win32-x86_64\\NaukriProfileUpdate\\Naukari\\Resume\\DaminiKadam_QA_3years.pdf");
			System.out.println("Pdf uploaded");
			//System.out.println(driver.findElement(By.linkText("Resume has been successfully uploaded.")).getText());
			
		}
		
		else {
		
			//Runtime.getRuntime().exec("G:\\eclipse-java-2019-09-R-win32-x86_64\\NaukriProfileUpdate\\Naukari\\Resume\\pdfFile.exe");		
			//attachment.sendKeys(System.getProperty("user.dir")+"\\Downloads\\DaminiKadam_QA_3years.pdf");
			attachment.sendKeys("G:\\eclipse-java-2019-09-R-win32-x86_64\\NaukriProfileUpdate\\Naukari\\Resume\\DaminiKadam_QA_3years.docx");
			System.out.println("docx uploaded");
			//System.out.println(driver.findElement(By.linkText("Resume has been successfully uploaded.")).getText());
		}
		
		js.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//*[text()='Attach Resume']")));
		Thread.sleep(5000);
		
		

		WebElement editResumeHeadline = driver.findElement(
				By.xpath("//div[@class='cardPad']//span[text()='Resume Headline']/following-sibling::span"));
		wait.until(ExpectedConditions.elementToBeClickable(editResumeHeadline));
		editResumeHeadline.click();

		WebElement txtBox = driver.findElement(By.id("resumeHeadlineTxt"));
		if (txtBox.getText().endsWith(".")) {
			txtBox.sendKeys(Keys.BACK_SPACE);
			
			

		}
		
		else {
			txtBox.sendKeys(".");
			
		}

		driver.findElement(By.xpath("//form[@name='resumeHeadlineForm']//button[text()='Save']")).click();
		System.out.println("execution completed.");
		
		Thread.sleep(5000);
	
		
	}
	
	
	
	@AfterTest
	public void closeAllBrowsers()
	{
		driver.quit();
	}
}
