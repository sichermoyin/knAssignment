package job.assignment.knab.ui;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class SetUp {

	final static Logger logger = LogManager.getLogger(SetUp.class);
	String currentDriver = null;
	WebDriver driver = null;

	public WebDriver getCurrentDriver(int browser, boolean headless) {

		switch (browser) {

		case 1:// google chrome
			ChromeOptions options = new ChromeOptions();
			String currentOs = System.getProperty("os.name");
			if (currentOs.toLowerCase().contains("windows") == true) {
				logger.debug("Curent OS -> " + currentOs);
				currentDriver = GLOBAL.CHROME_DRIVER_WINDOW;
			} else {
				logger.debug("Curent OS -> " + currentOs);
				currentDriver = GLOBAL.CHROME_DRIVER_LINUX;
				options.setExperimentalOption("useAutomationExtension", false);
				options.addArguments("--no-sandbox");
				options.addArguments("--disable-extensions");
				options.addArguments("--ignore-certificate-errors");
				options.addArguments("--ignore-ssl-errors");

			}
			System.setProperty("webdriver.chrome.driver", currentDriver);

			// headless means browser does not show but execution is going on in a virtual
			// display
			if (headless == true) {
				options.addArguments("--headless");
			}

			//options.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
			options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);

			driver = new ChromeDriver(options);
			driver.manage().deleteAllCookies();
		

			if (driver == null) {
				logger.debug("Unable to access driver!");
				System.exit(-1);
			}

			break;

		case 2: // explorer
			currentDriver = GLOBAL.EXPLORER_DRIVER;
			System.setProperty("webdriver.ie.driver", currentDriver);
			DesiredCapabilities dcp = new DesiredCapabilities();
			//dcp = DesiredCapabilities.internetExplorer();
			// dcp.setCapability("pageLoadStrategy", "eager");
			dcp.setCapability("unexpectedAlertBehaviour", "accept");
			// dcp.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
			// true);
			driver = new InternetExplorerDriver();

			break;

		case 3: // microsoft edge
			currentDriver = GLOBAL.EDGE_DRIVER;
			System.setProperty("webdriver.edge.driver", currentDriver);
			EdgeOptions edgeOptions = new EdgeOptions();
			edgeOptions.setCapability("unexpectedAlertBehaviour", "accept");
			driver = new EdgeDriver(edgeOptions);

			break;

		case 4: // firefox
			currentDriver = GLOBAL.FIREFOX_DRIVER;
			System.setProperty("webdriver.firefox.driver", currentDriver);
			driver = new FirefoxDriver();

			break;

		default:
			logger.error("Environment not setup");

		}
		driver.manage().window().maximize();
		return driver;

	}

	public void shutDownDriver() {

		if (driver != null) {
			driver.close();
			driver.quit();
		}
	}

}
