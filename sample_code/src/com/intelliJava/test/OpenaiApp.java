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
import com.intellijava.core.controller.RemoateImageModel;
import com.intellijava.core.controller.RemoteLanguageModel;
import com.intellijava.core.model.input.ImageModelInput;
import com.intellijava.core.model.input.LanguageModelInput;

/**
 * 
 * @author github.com/Barqawiz
 * 
 *
 */
public class OpenaiApp {

	public static void main(String[] args) {

		System.out.println("Start calling the API!");

		try {

			// get the api key from https://openai.com/api/
			// TODO: replace <openai-api-key> with your API key.
			String apiKey = "<openai-api-key>";

			/********************************/
			/** 1- Call the language model **/
			/********************************/
			tryTheLanguageModel(apiKey);

			/******************************/
			/** 2- Call image generation **/
			/******************************/
			tryTheImageModel(apiKey);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static void tryTheLanguageModel(String apiKey) throws IOException {
		// initiate the remote language model wrapper with openai details
		RemoteLanguageModel langModel = new RemoteLanguageModel(apiKey, "openai");

		// prepare the input parameters - change the prompt to any text 
		LanguageModelInput langInput = new LanguageModelInput.Builder("Summarize the plot of the 'Inception' movie in two sentences")
				.setModel("text-davinci-003").setTemperature(0.7f).setMaxTokens(80).build();
		

		String resValue = langModel.generateText(langInput);

		// print language model output
		System.out.println("Language model output:\n" + resValue);
	}
	
	private static void tryTheLanguageModelMultiText(String apiKey) throws IOException {
		// initiate the remote language model wrapper with openai details
		RemoteLanguageModel langModel = new RemoteLanguageModel(apiKey, "openai");

		// prepare the input parameters - change the prompt to any text 
		LanguageModelInput langInput = new LanguageModelInput.Builder("Summarize the plot of the 'Inception' movie in two sentences")
				.setModel("text-davinci-003").setTemperature(0.7f)
				.setMaxTokens(80).setNumberOfOutputs(2).build();
		
		
		List<String> resValues = langModel.generateMultiText(langInput);
		
		// print language model output
		for (String result : resValues)
			System.out.print("- " + result);
	}

	private static void tryTheImageModel(String apiKey) throws IOException {
		// initiate the remote image model wrapper
		RemoateImageModel imageModel = new RemoateImageModel(apiKey, "openai");

		// prepare the input parameters
		ImageModelInput imageInput = new ImageModelInput.Builder("teddy writing a blog in times square")
				.setNumberOfImages(2).setImageSize("1024x1024").build();

		// call the model
		List<String> images = imageModel.generateImages(imageInput);

		// print images links
		System.out.println("Images links:");
		for (String image : images) {
			System.out.println(image);
		}
	}

}
