import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.sun.javafx.PlatformUtil;

public class HotelBookingTest {

	WebDriver driver = null;

	@FindBy(linkText = "Hotels")
	private WebElement hotelLink;

	@FindBy(id = "Tags")
	private WebElement localityTextBox;

	@FindBy(id = "SearchHotelsButton")
	private WebElement searchButton;

	@FindBy(id = "travellersOnhome")
	private WebElement travellerSelection;

	// Added to hide Date options
	@FindBy(id = "CheckInDate")
	private WebElement checkInDate;

	@FindBy(id = "CheckOutDate")
	private WebElement checkOutDate;

	@Test
	public void shouldBeAbleToSearchForHotels() {

		// Needs a public method to access the private method
		// setDriverPath();
		setDriverPath();
		driver = new ChromeDriver();

		// This initElements method will create all WebElements
		PageFactory.initElements(driver, this);

		driver.get("https://www.cleartrip.com/");
		waitFor(3000);

		hotelLink.click();

		waitFor(1000);

		localityTextBox.sendKeys("Indiranagar, Bangalore");
		waitFor(2000);

		// Select the 1st option
		localityTextBox.sendKeys(Keys.ENTER);

		// Hide CheckInDate field
		checkInDate.sendKeys(Keys.ESCAPE);

		// Hide CheckOutDate field
		checkOutDate.sendKeys(Keys.ESCAPE);

		new Select(travellerSelection).selectByVisibleText("1 room, 2 adults");
		searchButton.click();

		waitFor(5000);

		driver.quit();

	}

	private void setDriverPath() {
		if (PlatformUtil.isMac()) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/chromedriver");
		}
		if (PlatformUtil.isWindows()) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\chromedriver.exe");
		}
		if (PlatformUtil.isLinux()) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/chromedriver_linux");
		}
	}

	// Added to put wait for slow network speed
	private void waitFor(int durationInMilliSeconds) {
		try {
			Thread.sleep(durationInMilliSeconds);
		} catch (InterruptedException e) {
			e.printStackTrace(); // To change body of catch statement use File |
									// Settings | File Templates.
		}
	}

}
