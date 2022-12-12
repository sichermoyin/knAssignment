package job.assignment.knab.ui;

import static org.junit.jupiter.api.Assertions.fail;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Utils {

	private static final String TASKLIST = "tasklist";
	private static final String B_KILL = "wmic process where name=\"";
	private static final String A_KILL = "\" call terminate";
	final static Logger logger = LogManager.getLogger(Utils.class);

	public static void killProcess(String serviceName) throws Exception {

		Runtime.getRuntime().exec(B_KILL + serviceName + A_KILL);
	}

	public static boolean isProcessRunning(String serviceName) throws Exception {

		String currentOs = System.getProperty("os.name");

		if (currentOs.toLowerCase().contains("windows") == true) {
			Process p = Runtime.getRuntime().exec(TASKLIST);
			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line;
			while ((line = reader.readLine()) != null) {
				if (line.length() > 0) {
					if (line.contains(serviceName)) {
						System.out.println("Process: " + line);

						return true;
					}
				}
			}
		}
		return false;

	}

	public static void terminateChromeAndChromeDriverProcesses() {

		boolean driverResponse;
		try {
			driverResponse = Utils.isProcessRunning("chromeDriver.exe");
			if (driverResponse == true) {
				Utils.killProcess("chromeDriver.exe");
			}
			boolean response = Utils.isProcessRunning("chrome.exe");
			if (response == true) {
				Utils.killProcess("chrome.exe");

			}
			System.out.println("is chrome running: " + response);
			System.out.println("is chromedriver running: " + driverResponse);

		} catch (Exception e) {
			e.printStackTrace();
			fail("unable to terminate chrome related processes");
		}

	}


}
