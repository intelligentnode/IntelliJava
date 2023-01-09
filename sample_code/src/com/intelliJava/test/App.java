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
package com.intelliJava.test;
import java.io.IOException;
import java.util.List;

import com.intellijava.com.intellijava.core.controller.RemoateImageModel;
import com.intellijava.com.intellijava.core.controller.RemoteLanguageModel;

/**
 * 
 * @author github.com/Barqawiz
 * 
 *
 */
public class App {

	public static void main(String[] args) {
		
		System.out.println( "Start calling the API!" );
        
        try {
        	
        	// get the api key from https://openai.com/api/
        	// TODO: replace <openai-api-key> with your API key.
        	String apiKey = "<openai-api-key>";
        	
        	/********************************/
        	/** 1- Call the language model **/
        	/********************************/
        	
        	// initiate the remote language model wrapper with openai details
        	RemoteLanguageModel langModel = new RemoteLanguageModel(apiKey, "openai");
        	
        	// change the command to any text you want like write a funny short story
        	String command = "return a java code that says hello world";
        	String resValue = langModel.generateText("text-davinci-002", command, 0.5F, 100);
			
        	//print language model output
			System.out.println("Language model output:\n"+resValue);
			
			
			/******************************/
			/** 2- Call image generation **/
			/******************************/
			
			// initiate the remote image model wrapper
			RemoateImageModel imageModel = new RemoateImageModel(apiKey, "openai");
			
			// prepare the input parameters
			String prompt = "teddy writing a blog in times square";
			int n = 2;
			String size = "1024x1024";
			
			// call the model
			List<String> images = imageModel.generateImages(prompt, n/*number of images*/, size);
			
			// print images links
			System.out.println("Images links:");
			for (String image:images) {
				System.out.println(image);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
