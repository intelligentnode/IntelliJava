package com.intellijava.com.intellijava.core;


import static org.junit.Assert.fail;

import java.io.IOException;
import org.junit.Test;
import com.intellijava.com.intellijava.core.controller.RemoteLanguageModel;
import com.intellijava.com.intellijava.core.utils.Config2;

/**
 * Unit test for Remote Language Model
 */
public class RemoteLanguageModelTest {
	
	/**
	 * openaiKey - change the openaiKey 
	 */
	private final String openaiKey = Config2.getInstance().getProperty("url.openai.testkey");
	
	/**
	 * basic API call test :-)
	 */
	@Test
	public void testOpenaiCompletion() {
		
		try {

			RemoteLanguageModel wrapper = new RemoteLanguageModel(openaiKey, "openai");

			String resValue = wrapper.generateText("text-davinci-002", "What is chatgpt ? include the word ChatGPT", 0.5F, 100);

			System.out.print(resValue);
			
			assert resValue.length() > 0;
			assert resValue.toLowerCase().contains("chatgpt");

		} catch (IOException e) {
			if (openaiKey.isBlank()) {
				System.out.print("testOpenaiCompletion: set the API key to run the test case.");
			} else {
				fail("Test case failed with exception: " + e.getMessage());
			}
			
		}
	}
}
