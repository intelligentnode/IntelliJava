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
package com.intellijava.core.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.intellijava.core.model.AudioResponse;
import com.intellijava.core.model.SpeechModels;
import com.intellijava.core.model.input.Text2SpeechInput;
import com.intellijava.core.model.input.Text2SpeechInput.Gender;
import com.intellijava.core.utils.AudioHelper;
import com.intellijava.core.wrappers.GoogleAIWrapper;

/**
 * RemoteSpeechModel class provides a remote speech model implementation. 
 * It generates speech from text using the Wrapper classes. 
 * 
 * This version support google speech models only.
 * 
 * To use Google speech services:
 * 1- Go to console.cloud.google.com.
 * 2- Enable "Cloud Text-to-Speech API".
 * 3- Generate API key from "Credentials" page. 
 * 
 *  @author github.com/Barqawiz
 */
public class RemoteSpeechModel {

	private SpeechModels keyType;
	private GoogleAIWrapper wrapper;
	
	/**
	 * 
	 * Constructs a new RemoteSpeechModel object with the specified key value and key type string.
	 * If keyTypeString is empty, it is set to "google" by default.
	 * 
	 * @param keyValue the API key value to use.
	 * @param keyTypeString the string representation of the key type.
	 */
	public RemoteSpeechModel(String keyValue, String keyTypeString) {
		
		if (keyTypeString.isEmpty()) {
			keyTypeString = SpeechModels.google.toString();
		}
		
		List<String> supportedModels = this.getSupportedModels();
		
		
		if (supportedModels.contains(keyTypeString)) {
			this.initiate(keyValue, SpeechModels.valueOf(keyTypeString));
		} else {
			String models = String.join(" - ", supportedModels);
			throw new IllegalArgumentException("The received keyValue not supported. Send any model from: " + models);
		}
	}
	
	/**
	 * 
	 * Constructs a new RemoteSpeechModel object with the specified key value and key type.
	 * 
	 * @param keyValue The API key value to use.
	 * @param keyType The SpeechModels enum value representing the key type.
	 */
	public RemoteSpeechModel(String keyValue, SpeechModels keyType) { 
		this.initiate(keyValue, keyType);
	}
	
	/**
	 * Initiate the object with the specified key value and key type.
	 * 
	 * @param keyValue the API key value to use.
	 * @param keyType the SpeechModels enum value representing the key type.
	 */
	private void initiate(String keyValue, SpeechModels keyType) {
		
		this.keyType = keyType;
		wrapper = new GoogleAIWrapper(keyValue);
	}
	
	/**
	 * Get a list of supported key type models.
	 * 
	 * @return list of the supported SpeechModels enum values.
	 */
	public List<String> getSupportedModels() {
		SpeechModels[] values = SpeechModels.values();
		List<String> enumValues = new ArrayList<>();

		for (int i = 0; i < values.length; i++) {
			enumValues.add(values[i].name());
		}

		return enumValues;
	}
	
	/**
	 * Generates speech from text using the support models.
	 * 
	 * You can save the returned byte to audio file using FileOutputStream("path/audio.mp3"). 
	 * 
	 * @param input SpeechInput object containing the text and gender to use.
	 * @return byte array of the decoded audio content.
	 * @throws IOException in case of communication error.
	 */
	public byte[] generateEnglishText(Text2SpeechInput input) throws IOException {
		
		if (this.keyType == SpeechModels.google) {
			return this.generateGoogleText(input.getText(), input.getGender(), "en-gb");
		} else {
			throw new IllegalArgumentException("the keyType not supported");
		}
	}
	
	/**
	 * Generates speech from text using the Google Speech service API.
	 * 
	 * @param text text to generate the speech.
	 * @param gender gender to use (male or female).
	 * @param language en-gb.
	 * @return
	 * @throws IOException in case of communication error.
	 */
	private byte[] generateGoogleText(String text, Gender gender, String language) throws IOException {
		byte[] decodedAudio = null;
		
		Map<String, Object> params = new HashMap<>();
		params.put("text", text);
		params.put("languageCode", language);
		
		if (gender == Gender.FEMALE) {
			params.put("name", "en-GB-Standard-A");
			params.put("ssmlGender", "FEMALE");
		} else {
			params.put("name", "en-GB-Standard-B");
			params.put("ssmlGender", "MALE");
		}
		
		AudioResponse resModel = (AudioResponse) wrapper.generateSpeech(params);
		decodedAudio = AudioHelper.decode(resModel.getAudioContent());
		
		return decodedAudio;
	}
}
