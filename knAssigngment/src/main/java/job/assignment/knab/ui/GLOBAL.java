package job.assignment.knab.ui;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GLOBAL {

	final static Logger logger 	= LogManager.getLogger(GLOBAL.class);
	//this is the time selenium will wait for a webelement to be visible
	public static Duration CONTROL_WAIT_TIMEOUT = Duration.ofMillis(1000);

	//public static long CONTROL_WAIT_TIMEOUT = 10;
	// the time selenium will wait for a screen to be visible
	public static long PAGE_WAIT_TIMEOUT;
	
	//screenshot directory
	public static String SCREENSHOT_DIRECTORY= "";
	public static int SCREENSHOT_COUNTER     = 0;
	//the time a particular test is executed, it's part of the name of each screenshot 
	public static String RUNID = String.valueOf(System.currentTimeMillis());

	
	//Browsers
	public static  int CHROME 		= 1;
	public static  int IEXPLORER 	= 2;
	public static int EDGE			= 3;
	public static int FIREFOX		= 4;
	
	//firefox
	public static String FIREFOX_DRIVER 			= null;
	public static String FIREFOX_EXE 				= null;
	
	
	
	//chrome
	// provide the path to chrome executable if it is not in its standard location
	public static String CHROME_EXE 			    = null;
	// provide the path to chrome driver if it is not in its standard location
	public static String CHROME_DRIVER_WINDOW 		= "chromedriver.exe";
	public static String CHROME_DRIVER_LINUX 	    = "chromedriver";
	
	public static String EXPLORER_DRIVER            = null;
	public static String EDGE_DRIVER                = null;
}
