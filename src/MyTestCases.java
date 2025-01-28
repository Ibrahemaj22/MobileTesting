import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class MyTestCases {

	DesiredCapabilities caps = new DesiredCapabilities();
	AndroidDriver driver;
	Random rand = new Random();

	@BeforeTest

	public void MySetup() {

		caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		caps.setCapability(MobileCapabilityType.DEVICE_NAME, "abc");

		File myapplication = new File("src\\myApplication\\calculator.apk");

		caps.setCapability(MobileCapabilityType.APP, myapplication.getAbsolutePath());
	}

	@BeforeMethod
	public void myBeforeMethod() throws MalformedURLException {

		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps);

	}

	@Test(priority = 1, enabled = false)

	public void MultiplyTwoNumbers() throws MalformedURLException {

		driver.findElement(By.id("com.google.android.calculator:id/digit_7")).click();

		driver.findElement(By.id("com.google.android.calculator:id/op_mul")).click();

		driver.findElement(By.id("com.google.android.calculator:id/digit_9")).click();

		String ExpectedResult = "63";

		WebElement Results = driver.findElement(By.id("com.google.android.calculator:id/result_preview"));

		String ActualResult = Results.getText();

		Assert.assertEquals(ActualResult, ExpectedResult);

	}

	@Test(priority = 2, enabled = false)
	public void ClickOnAllItems() throws MalformedURLException, InterruptedException {

		List<WebElement> AllButtons = driver.findElements(By.className("android.widget.ImageButton"));
		Thread.sleep(3000);
		for (int i = 0; i < AllButtons.size(); i++) {
			AllButtons.get(i).click();
		}

	}

	@Test(priority = 3, enabled = false)
	public void ClickOnlyOnNumbers() throws MalformedURLException {

		List<WebElement> AllButtons = driver.findElements(By.className("android.widget.ImageButton"));

		for (int i = 0; i < AllButtons.size(); i++) {

			if (AllButtons.get(i).getDomAttribute("resource-id").contains("digit")) {
				AllButtons.get(i).click();
			}

		}

	}

	@Test(priority = 4, enabled = false)
	public void ClickOnlyOnEvenNumbers() throws MalformedURLException {

		List<WebElement> AllButtons = driver.findElements(By.className("android.widget.ImageButton"));

		for (int i = 0; i < AllButtons.size(); i++) {

			if (AllButtons.get(i).getDomAttribute("resource-id").contains("digit")) {

				int EvenNumbers = Integer.parseInt(AllButtons.get(i).getDomAttribute("content-desc"));

				if (EvenNumbers % 2 == 1) {
					AllButtons.get(i).click();
				}

			}

		}

	}

	@Test(priority = 4, enabled = true)
	public void MultiplyTwoRandomNumbers() throws MalformedURLException {

		String FirstNumber = Integer.toString(rand.nextInt(10));
		String SecondNumber = Integer.toString(rand.nextInt(10));

		List<WebElement> AllButtons = driver.findElements(By.className("android.widget.ImageButton"));

		for (int i = 0; i < AllButtons.size(); i++) {

			if (AllButtons.get(i).getDomAttribute("resource-id").contains("digit_" + FirstNumber)) {
				AllButtons.get(i).click();
				driver.findElement(By.id("com.google.android.calculator:id/op_mul")).click();
			}

			if (AllButtons.get(i).getDomAttribute("resource-id").contains("digit_" + SecondNumber)) {
				AllButtons.get(i).click();

			}

		}

	}

}
