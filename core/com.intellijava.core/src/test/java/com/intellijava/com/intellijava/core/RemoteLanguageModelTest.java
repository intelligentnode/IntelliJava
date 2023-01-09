package com.intellijava.com.intellijava.core;


import java.io.IOException;
import org.junit.Test;
import com.intellijava.com.intellijava.core.controller.RemoteLanguageModel;

/**
 * Unit test for Remote Language Model
 */
public class RemoteLanguageModelTest {
	
	/**
	 * basic API call test :-)
	 */
	@Test
	public void testOpenaiCompletion() {
		try {

			RemoteLanguageModel wrapper = new RemoteLanguageModel("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",
					"openai");

			String resValue = wrapper.generateText("text-davinci-002", "write a java code that says hello wrold", 0.5F,
					100);

			System.out.print(resValue);
			
			assert resValue.length() > 0;
			assert resValue.toLowerCase().contains("hello");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
