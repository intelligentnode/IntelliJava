package com.intelliJava.test;


import java.io.IOException;
import com.intellijava.core.controller.RemoteSpeechModel;
import com.intellijava.core.model.SpeechModels;
import com.intellijava.core.model.input.Text2SpeechInput;
import com.intellijava.core.model.input.Text2SpeechInput.Gender;
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
			
			System.out.print("Check the temp forlder for the generated audio.");
			
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

		
		RemoteSpeechModel model = new RemoteSpeechModel(apiKey, SpeechModels.google);
		
		Text2SpeechInput input = new Text2SpeechInput.Builder("Hi, I am Intelligent Java.").build();
		
		// get the audio bytes
		// you can play it using libraries like javafx
		byte[] decodedAudio = model.generateEnglishText(input);
		
		// save temporary audio file
		AudioHelper.saveTempAudio(decodedAudio);

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
	private static void tryGermanSpeechModel(String apiKey) throws IOException {

		
		RemoteSpeechModel model = new RemoteSpeechModel(apiKey, SpeechModels.google);
		
		Text2SpeechInput input = new Text2SpeechInput.Builder("Hallo, ich bin Intelligent Java.").build();
		
		// get the audio bytes
		// you can play it using libraries like javafx
		byte[] decodedAudio = model.generateGermanText(input);
		
		// save temporary audio file
		AudioHelper.saveTempAudio(decodedAudio);

	}

}
