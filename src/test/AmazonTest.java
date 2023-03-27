package test;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AmazonTest {

	public static void main(String[] args) throws IOException {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");

		WebDriver driver = new ChromeDriver();

		driver.get("https://www.amazon.in");

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);

		WebElement searchInput = driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"));
		searchInput.sendKeys("samsung mobile");
		WebElement searchBtn = driver.findElement(By.xpath("//input[@id='nav-search-submit-button']"));
		searchBtn.click();

		List<WebElement> products = driver.findElements(By.xpath("//div[@class='a-section']//h2//span"));

		List<WebElement> productPrice = driver.findElements(By.xpath(
				"//div[@data-component-type='s-search-result']//div[contains(@class,'price')]//span[@class='a-price-whole']"));
		List<WebElement> productCurrency = driver
				.findElements(By.xpath("//div[@class='sg-row']//span[@class='a-price-symbol']"));

		System.out.println("The no of products list: " + products.size());
		for (int i = 0; i < products.size(); i++) {
			System.out.println(products.get(i).getText());
			System.out.println(productCurrency.get(i).getText() + "" + productPrice.get(i).getText());

		}
		
		TakesScreenshot ss =  (TakesScreenshot)driver;
		File ssFile=ss.getScreenshotAs(OutputType.FILE);
		File fileObj = new File("Amazon.png");
		FileUtils.copyFile(ssFile, fileObj);
		
		driver.close();

	}

}
