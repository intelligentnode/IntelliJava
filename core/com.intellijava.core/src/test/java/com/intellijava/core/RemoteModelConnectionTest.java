/**
 * Copyright 2023 Github.com/Barqawiz/IntelliJava
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.intellijava.core;


import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import com.intellijava.core.controller.RemoateImageModel;
import com.intellijava.core.controller.RemoteLanguageModel;
import com.intellijava.core.model.OpenaiImageResponse;
import com.intellijava.core.model.OpenaiImageResponse.Data;
import com.intellijava.core.model.input.ImageModelInput;
import com.intellijava.core.model.input.LanguageModelInput;
import com.intellijava.core.utils.Config2;
import com.intellijava.core.wrappers.OpenAIWrapper;


/**
 * Unit test for Remote Language Model
 */
public class RemoteModelConnectionTest {
	
	/**
	 * openaiKey - change the openaiKey 
	 */
	private final String openaiKey = Config2.getInstance().getProperty("url.openai.testkey");
	
	/**
	 * basic API call test :-)
	 */
	@Test
	public void testOpenaiCompletionRemoteModel() {
		
		try {
			
			RemoteLanguageModel wrapper = new RemoteLanguageModel(openaiKey, "openai");
			
			LanguageModelInput input = new LanguageModelInput.Builder("return a java code that print hello world")
	                .setModel("text-davinci-002").setTemperature(0.7f).setMaxTokens(50).build();
			
			if (openaiKey.isBlank()) return;
			
			String resValue = wrapper.generateText(input);

			System.out.print(resValue);
			
			assert resValue.length() > 0;
			assert resValue.toLowerCase().contains("world");

		} catch (IOException e) {
			if (openaiKey.isBlank()) {
				System.out.print("testOpenaiCompletion: set the API key to run the test case.");
			} else {
				fail("Test case failed with exception: " + e.getMessage());
			}
			
		}
	}
	
	@Test
	public void testImageWrapper() {
		
		// prepare the object
		OpenAIWrapper openaiWrapper = new OpenAIWrapper(openaiKey);
		
		// prepare the input parameters
		String prompt = "teddy writing a blog in times square";
		int n = 2;
		String size = "1024x1024";
		
		Map<String, Object> params = new HashMap<>();
        params.put("prompt", prompt);
        params.put("n", n);
        params.put("size", size);
        
        // call the API
        try {
        	if (openaiKey.isBlank()) return;
        	
        	OpenaiImageResponse resModel = (OpenaiImageResponse) openaiWrapper.generateImages(params);
        	
        	List<Data> responseImages = resModel.getData();
        	for (Data data: responseImages) {
        		System.out.println(data.getUrl().toString());
        	}
        	
        	
		} catch (IOException e) {
			if (openaiKey.isBlank()) {
				System.out.print("testOpenaiCompletion: set the API key to run the test case.");
			} else {
				fail("Test case failed with exception: " + e.getMessage());
			}
		}
        
		
	}
	
	@Test
	public void testOpenaiImageRemoteModel() { 
		
		// prepare the input parameters
		String prompt = "teddy writing a blog in times square";
		
		try {

			RemoateImageModel wrapper = new RemoateImageModel(openaiKey, "openai");
			ImageModelInput input = new ImageModelInput.Builder(prompt) 
					.setNumberOfImages(2).setImageSize("1024x1024").build();
			
			if (openaiKey.isBlank()) return;
			
			List<String> images = wrapper.generateImages(input);
			
			for (String image:images) {
				System.out.print(image);
			}
			
			assert images.size() > 0;
			
		} catch (IOException e) {
			if (openaiKey.isBlank()) {
				System.out.print("testOpenaiCompletion: set the API key to run the test case.");
			} else {
				fail("Test case failed with exception: " + e.getMessage());
			}
			
		}
				
	}
}
