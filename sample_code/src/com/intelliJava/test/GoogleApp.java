package com.intelliJava.test;


import java.io.IOException;
import com.intellijava.core.controller.RemoteSpeechModel;
import com.intellijava.core.model.SpeechModels;
import com.intellijava.core.model.input.SpeechInput;
import com.intellijava.core.model.input.SpeechInput.Gender;
import com.intellijava.core.utils.AudioHelper;

public class GoogleApp {

	public static void main(String[] args) {

		System.out.println("Start calling the API!");

		// get the api key from https://console.cloud.google.com/
		// TODO: replace <google-api-key> with your API key.
		String apiKey = "<google-api-key>";

		/********************************/
		/** 1- Call the language model **/
		/********************************/
		try {
			
			tryGoogleSpeechModel(apiKey);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Generate speech from text using google API.
	 * 
	 * To use this model:
	 * 1- Go to console.cloud.google.com.
	 * 2- Enable "Cloud Text-to-Speech API" from APIs Services.
	 * 3- Generate API key from APIs and services Credentials page.
	 * 
	 * @param apiKey
	 * @throws IOException
	 */
	private static void tryGoogleSpeechModel(String apiKey) throws IOException {

		SpeechInput input = new SpeechInput("Hi, I am Intelligent Java.", Gender.FEMALE);
		
		RemoteSpeechModel model = new RemoteSpeechModel(apiKey, SpeechModels.google);
		
		// get the audio bytes
		// you can play it using libraries like javafx
		byte[] decodedAudio = model.generateEnglishText(input);
		
		// save temporary audio file
		AudioHelper.saveTempAudio(decodedAudio);

	}

}
