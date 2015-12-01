package uk.co.aps.sainsburys.service.impl;

import static org.junit.Assert.assertNotNull;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import uk.co.aps.sainsburys.App;


public class AppTest {

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	
	@Before
	public void setUpStreams() {
	    System.setOut(new PrintStream(outContent));
	}

	@After
	public void cleanUpStreams() {
	    System.setOut(null);
	}
	
	@Test
	public void testCalculatePriceForNoProducts() throws InterruptedException{
		// given - running the integration test with the sainsburys url
		
		// when - we calculate the total
		App.main(new String[]{});
		
		//then - expect the output not to be null
		assertNotNull(outContent.toString());
	}
	
	
}
