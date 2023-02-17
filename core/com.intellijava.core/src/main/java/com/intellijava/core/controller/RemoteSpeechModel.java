package com.intellijava.core.controller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.intellijava.core.model.AudioResponse;
import com.intellijava.core.model.SpeechModels;
import com.intellijava.core.model.input.SpeechInput;
import com.intellijava.core.model.input.SpeechInput.Gender;
import com.intellijava.core.utils.AudioHelper;
import com.intellijava.core.wrappers.GoogleAIWrapper;

public class RemoteSpeechModel {

	private SpeechModels keyType;
	private GoogleAIWrapper wrapper;
	
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
	
	public RemoteSpeechModel(String keyValue, SpeechModels keyType) { 
		this.initiate(keyValue, keyType);
	}
	
	private void initiate(String keyValue, SpeechModels keyType) {
		
		this.keyType = keyType;
		wrapper = new GoogleAIWrapper(keyValue);
	}
	
	public List<String> getSupportedModels() {
		SpeechModels[] values = SpeechModels.values();
		List<String> enumValues = new ArrayList<>();

		for (int i = 0; i < values.length; i++) {
			enumValues.add(values[i].name());
		}

		return enumValues;
	}
	
	public byte[] generateEnglishText(SpeechInput input) throws IOException {
		
		if (this.keyType == SpeechModels.google) {
			return this.generateGoogleText(input.getText(), input.getGender(), "en-gb");
		} else {
			throw new IllegalArgumentException("the keyType not supported");
		}
	}
	
	private byte[] generateGoogleText(String text, Gender geneder, String language) throws IOException {
		byte[] decodedAudio = null;
		
		Map<String, Object> params = new HashMap<>();
		params.put("text", text);
		params.put("languageCode", language);
		
		if (geneder == Gender.FEMALE) {
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
