package job.assignment.knab.ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TestRunner {
	
	final static Logger logger = LogManager.getLogger(TestRunner.class);
	
	protected WebDriver driver = null;
	protected SetUp setUp = null;
	protected WebElement webEl = null;
	protected List <WebElement> webEls = null;
	protected Properties data = new Properties();
	protected String run_id = GLOBAL.RUNID;
	
	public TestRunner() {
		loadTestData();
		initializeDriver(true);
	}
	
	
	public TestRunner(boolean headless) {
		loadTestData();
		initializeDriver(headless);
	}
	
	
	public TestRunner(WebDriver webDriver) {
		loadTestData();
		driver = webDriver;
	}
	
	public void initializeDriver(boolean headless) {
		GLOBAL.SCREENSHOT_DIRECTORY = System.getProperty("user.dir")+"\\Sceenshots\\"+LocalDate.now().toString();
		setUp = new SetUp();
		driver = setUp.getCurrentDriver(1, headless);
	}
	
	public String getDefaultTestDataFileName(String className) {

		String[] theNameComps = className.split("\\.");
		return (theNameComps[theNameComps.length - 1] + ".data");
	}

	private void loadTestData() {

		FileReader reader = null;

		String testDataFileName = getDefaultTestDataFileName(getClass().getName());
		logger.info("Test data fileName: " + testDataFileName);
		try {
			reader = new FileReader(testDataFileName);

			data.load(reader);

		} catch (FileNotFoundException e) {
			logger.info("loading from the stream");
			try {
				InputStream in = getClass().getResourceAsStream("/" + testDataFileName);
				data.load(in);

			} catch (IOException e1) {
				e1.printStackTrace();
			}

		} catch (IOException e) {
			logger.error("Error Message: " + e.getMessage());
		}

	}
	
	public void takeAScreenshot(WebDriver driver,String timeline) throws IOException {
		// Take a screenshot before moving to the next screen

		String screenshotFileName = run_id+"_"+timeline+"_"+String.valueOf(GLOBAL.SCREENSHOT_COUNTER+".png");
		logger.info("Screenshot fileName: "+ screenshotFileName);
		driver.manage().window().maximize();
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

		// Now you can do whatever you need to do with it, for example copy somewhere
		FileUtils.copyFile(scrFile, new File(GLOBAL.SCREENSHOT_DIRECTORY+"\\"+screenshotFileName));
		GLOBAL.SCREENSHOT_COUNTER++;
		}
		
	public void shutDownTest() {
		if(setUp != null) {
			setUp.shutDownDriver();
		}
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	public SetUp getSetUp() {
		return setUp;
	}

	public void setSetUp(SetUp setUp) {
		this.setUp = setUp;
	}

	public Properties getData() {
		return data;
	}

	public void setData(Properties data) {
		this.data = data;
	}

	public String getRun_id() {
		return run_id;
	}

	public void setRun_id(String run_id) {
		this.run_id = run_id;
	}
	
}
