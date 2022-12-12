package job.assignment.knab.ui;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CreateBoard extends TestRunner {

	final static Logger logger = LogManager.getLogger(CreateBoard.class);
	String currentUrl;

	public CreateBoard() throws IOException {
		super();
	}


	public CreateBoard(boolean headless) throws IOException {
		super(headless);
	}


	/**
	 *
	 * @throws IOException
	 */

	public boolean logIn() throws IOException {

		boolean retVal = false;

		driver.navigate().to(data.getProperty("url"));
		takeAScreenshot(driver, "TrelloHomePage");

		UIControl uiControl = new UIControl();

		webEl =  uiControl.retrieveElement(driver, RecognitionType.XPATH, data.getProperty("applicationLink"));
		webEl.click();

		webEl =  uiControl.retrieveElement(driver, RecognitionType.NAME,data.getProperty("userEmailRecog"));
		webEl.sendKeys(data.getProperty("userEmail"));

		webEl =  uiControl.retrieveElement(driver, RecognitionType.ID, data.getProperty("userEmailButton"));
		webEl.click();

		webEl =  uiControl.retrieveElement(driver, RecognitionType.ID, data.getProperty("userPasswordRecog"));
		webEl.sendKeys(data.getProperty("userPassword"));

		takeAScreenshot(driver, "CredentialsFilled");

		webEl =  uiControl.retrieveElement(driver, RecognitionType.ID, "login-submit");
		webEl.click();
		takeAScreenshot(driver, "UserDashboard");

		retVal = true;

		return(retVal);

	}



	/**
	 *
	 * for creating boards
	 * @throws IOException
	 */
	public boolean createBoard() throws IOException {

		boolean retVal = false;

		UIControl uiControl = new UIControl();

		webEl =  uiControl.retrieveElement(driver, RecognitionType.XPATH,data.getProperty("createMenuRecog"));
		webEl.click();

		webEl =  uiControl.retrieveElement(driver, RecognitionType.XPATH, data.getProperty("createBoardSubMenuRecog"));
		webEl.click();

		webEl =  uiControl.retrieveElement(driver, RecognitionType.XPATH, data.getProperty("boardNameRecog"));
		webEl.sendKeys(data.getProperty("boardName"));

		takeAScreenshot(driver, "BoardInfo");

		webEl =  uiControl.retrieveElement(driver, RecognitionType.XPATH,data.getProperty("createBoardButton") );
		webEl.click();

		takeAScreenshot(driver, "BoardCreated");
		retVal=true;

		return(retVal);

	}

	public void endTest() {
		setUp.shutDownDriver();
	}

}
