package job.assignment.knab.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Order;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Tag;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import job.assignment.knab.ui.CreateBoard;

@DisplayName("Trello Test")
@TestMethodOrder(OrderAnnotation.class)
public class AssignmentTest {
	
	final static Logger logger = LogManager.getLogger(AssignmentTest.class);
	
	/**
	 * This method create a user board through the API
	 */
	@Test
	@Tag("createBoardApi")
	public void createBoardApi() {

		HttpResponse<String> jResponse;
		try {
			jResponse = Unirest.post("https://api.trello.com/1/boards/")
					  .queryString("name", "assignment")
					  .queryString("key", "7b0b31761afab2d23e5173d9dfd779be")
					  .queryString("token", "afd67c62a408a7086476c9101323c8734a6568b407747f5e14a14727a581e66d")
					  .asString();
			
			logger.info(jResponse.getBody());
			assertNotNull(jResponse.getBody());
			assertEquals(200, jResponse.getStatus());
		} catch (UnirestException e) {
			e.printStackTrace();
			fail("Exception: " + e.getMessage());
		}
	}
	
	
	/**
	 *  This method create a user board through the UI
	 * @throws IOException 
	 */
	@Test
	@Tag("createBoardUI")
	public void createBoardUI() throws IOException {
		
			CreateBoard createBoard = new CreateBoard(false);
			assertTrue(createBoard.logIn());
			assertTrue(createBoard.createBoard());
				
	}

}
