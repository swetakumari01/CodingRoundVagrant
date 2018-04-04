import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.sun.javafx.PlatformUtil;

public class SignInTest {

	@Test
	public void shouldThrowAnErrorIfSignInDetailsAreMissing() {

		setDriverPath();

		// launching the chrome driver
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\chromedriver.exe");

		WebDriver driver = new ChromeDriver();

		driver.get("https://www.cleartrip.com/");

		waitFor(4000);

		// Clicking on the "your Trips"
		driver.findElement(By.linkText("Your trips")).click();

		waitFor(2000);

		// CLicking on the Sign in button.
		driver.findElement(By.id("SignIn")).click();

		waitFor(3000);

		// Switch to the frame.
		// driver.switchTo().frame("ContentFrame");
		driver.switchTo().frame("modal_window"); // signin iframe id/name was
													// wrong

		waitFor(3000);

		// clicking on the signin button
		driver.findElement(By.id("signInButton")).click();

		waitFor(5000);

		String errors1 = driver.findElement(By.id("errors1")).getText();
		Assert.assertTrue(errors1.contains("There were errors in your submission"));
		driver.quit();
	}

	private void waitFor(int durationInMilliSeconds) {
		try {
			Thread.sleep(durationInMilliSeconds);
		} catch (InterruptedException e) {
			e.printStackTrace(); // To change body of catch statement use File |
									// Settings | File Templates.
		}
	}

	private void setDriverPath() {
		if (PlatformUtil.isMac()) {
			System.setProperty("webdriver.chrome.driver", "chromedriver");
		}
		if (PlatformUtil.isWindows()) {
			System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		}
		if (PlatformUtil.isLinux()) {
			System.setProperty("webdriver.chrome.driver", "chromedriver_linux");
		}
	}

}
