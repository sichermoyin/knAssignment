package job.assignment.knab.ui;

import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * 
 * This class is used for recognition of web elements
 *
 */
public class UIControl {

	final static Logger logger = LogManager.getLogger(UIControl.class);

	
	/**
	 * 
	 * @param driver WebDriver
	 * @param controlRecognitionType RecognitionType values for example -  RecognitionType.NAME
	 * @param recString  String that identifies the Webelement
	 * @return
	 */
	public WebElement retrieveElement(WebDriver driver, RecognitionType controlRecognitionType, String recString) {

		WebElement currentElement = null;
		try {
			switch (controlRecognitionType) {

			case NAME:
				currentElement = (new WebDriverWait(driver, GLOBAL.CONTROL_WAIT_TIMEOUT))
						.until(ExpectedConditions.visibilityOfElementLocated(By.name(recString)));

				break;
			case ID:
				currentElement = (new WebDriverWait(driver, GLOBAL.CONTROL_WAIT_TIMEOUT))
						.until(ExpectedConditions.visibilityOfElementLocated(By.id(recString)));
				break;

			case XPATH:
				currentElement = (new WebDriverWait(driver, GLOBAL.CONTROL_WAIT_TIMEOUT))
						.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(recString)));
				break;
			case CSSSELECT:
				currentElement = (new WebDriverWait(driver, GLOBAL.CONTROL_WAIT_TIMEOUT))
						.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(recString)));
				break;

			case CLASSNAME:
				currentElement = (new WebDriverWait(driver, GLOBAL.CONTROL_WAIT_TIMEOUT))
						.until(ExpectedConditions.visibilityOfElementLocated(By.className(recString)));
				break;

			case LINK:
				currentElement = (new WebDriverWait(driver, GLOBAL.CONTROL_WAIT_TIMEOUT))
						.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(recString)));
				break;

			case SUBLINK:
				currentElement = (new WebDriverWait(driver, GLOBAL.CONTROL_WAIT_TIMEOUT))
						.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(recString)));
				break;

			case TAGNAME:
				currentElement = (new WebDriverWait(driver, GLOBAL.CONTROL_WAIT_TIMEOUT))
						.until(ExpectedConditions.visibilityOfElementLocated(By.tagName(recString)));
				break;

			default:
				logger.error("Having problem with recognizing control: " + recString);
				break;
			}
		} catch (StaleElementReferenceException e) {
			fail(e.getMessage());
		} catch (TimeoutException toe) {
			logger.error(toe.getMessage());
			 haltTestExecution(driver);
		}

		return (currentElement);

	}

    
	/**
	 * 
	 * @param driver - shuts down the WebDriver
	 */
	public void haltTestExecution(WebDriver driver) {
		if (driver != null) {
			logger.info("Halting test execution .....");
			String screenshotFileName = "Halt.png";
			logger.info("*********Screenshot fileName: " + screenshotFileName);
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			try {
				// Now you can do whatever you need to do with it, for example copy somewhere
				FileUtils.copyFile(scrFile, new File(GLOBAL.SCREENSHOT_DIRECTORY + "\\" + screenshotFileName));
			} catch (IOException e) {
				logger.error(e.getMessage());
			}
			GLOBAL.SCREENSHOT_COUNTER++;
			driver.close();
			driver.quit();
			
		}
	}

}
