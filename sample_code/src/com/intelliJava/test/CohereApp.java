package com.intelliJava.test;

import java.io.IOException;

import com.intellijava.core.controller.RemoteLanguageModel;
import com.intellijava.core.model.input.LanguageModelInput;

public class CohereApp {

	public static void main(String[] args) {

		System.out.println("Start calling the API!");

		try {

			// get the api key from https://dashboard.cohere.ai/
			// TODO: replace <cohere-api-key> with your API key.
			String apiKey = "<cohere-api-key>";

			/********************************/
			/** 1- Call the language model **/
			/********************************/
			tryTheLanguageModel(apiKey);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static void tryTheLanguageModel(String apiKey) throws IOException {
		// initiate the remote language model wrapper with cohere details
		RemoteLanguageModel langModel = new RemoteLanguageModel(apiKey, "cohere");

		// prepare the input parameters - change the prompt to any text like "write a
		// funny short story"
		LanguageModelInput langInput = new LanguageModelInput.Builder("Once upon a time in a magical land called")
				.setModel("xlarge").setTemperature(0.7f).setMaxTokens(50).build();

		String resValue = langModel.generateText(langInput);

		// print language model output
		System.out.println("Language model output:\n" + resValue);
	}
	
}
