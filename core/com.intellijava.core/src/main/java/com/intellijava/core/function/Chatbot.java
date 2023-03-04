package com.intellijava.core.function;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.intellijava.core.model.OpenaiChatResponse;
import com.intellijava.core.model.OpenaiChatResponse.Choice;
import com.intellijava.core.model.SupportedChatModels;
import com.intellijava.core.model.input.ChatGPTInput;
import com.intellijava.core.model.input.ChatModelInput;
import com.intellijava.core.wrappers.OpenAIWrapper;

/**
 * 
 * 	Chatbot controller for most sophisticated AI chatbots.
 * 
 *  @author github.com/Barqawiz
 *
 */
public class Chatbot {
	
	private SupportedChatModels keyType;
	private OpenAIWrapper openaiWrapper;

	/**
	 * 
	 * Constructor for the Chatbot class.
	 *
	 * Creates class instance and set the API type and key.
	 *
	 * @param keyValue      the API key.
	 * @param keyTypeString either openai (default) or cohere or send empty string
	 *                      for default value.
	 *
	 * @throws IllegalArgumentException if the keyType passed is not "openai".
	 * 
	 */
	public Chatbot(String keyValue, String keyTypeString) {

		if (keyTypeString.isEmpty()) {
			keyTypeString = SupportedChatModels.openai.toString();
		}

		List<String> supportedModels = this.getSupportedModels();

		if (supportedModels.contains(keyTypeString)) {
			this.initiate(keyValue, SupportedChatModels.valueOf(keyTypeString));
		} else {
			String models = String.join(" - ", supportedModels);
			throw new IllegalArgumentException("The received keyValue not supported. Send any model from: " + models);
		}
	}

	/**
	 * Constructor for the Chatbot class.
	 *
	 * Creates class instance and set the API enum type and key.
	 *
	 * @param keyValue the API key.
	 * @param keyType  enum version from the key type (SupportedModels).
	 *
	 * @throws IllegalArgumentException if the keyType passed is not "openai".
	 * 
	 */
	public Chatbot(String keyValue, SupportedChatModels keyType) {
		this.initiate(keyValue, keyType);
	}
	
	/**
	 * Get the supported models names as array of string
	 * 
	 * @return supportedModels
	 */
	public List<String> getSupportedModels() {
		SupportedChatModels[] values = SupportedChatModels.values();
		List<String> enumValues = new ArrayList<>();

		for (int i = 0; i < values.length; i++) {
			enumValues.add(values[i].name());
		}

		return enumValues;
	}
	
	/**
	 * Common function to initiate the class from any constructor.
	 * 
	 * @param keyValue the API key.
	 * @param keyType enum version from the key type (SupportedModels).
	 */
	private void initiate(String keyValue, SupportedChatModels keyType) {
		// set the model type
		this.keyType = keyType;

		// generate the related model
		if (keyType.equals(SupportedChatModels.openai)) {
			this.openaiWrapper = new OpenAIWrapper(keyValue);
		}
	}
	
	/**
	 * 
	 * Call a chat model to generate response based on the received messages history.
	 * 
	 * To support multiple response call the variation function generateMultiText.
	 * 
	 * @param modelInput language model parameters.
	 * 					
	 * @return the model response.
	 * 
	 * @throws IOException if there is an error when connecting to the chat model.
	 * 
	 */
	public List<String> chat(ChatModelInput modelInput) throws IOException {

		if (this.keyType.equals(SupportedChatModels.openai)) {
			return this.chatGPT((ChatGPTInput) modelInput);
		} else {
			throw new IllegalArgumentException("the keyType not supported");
		}
	}
	
	
	/**
	 * 
	 * Call a chatGPT to generate response based on the received messages history.
	 * 
	 * @param modelInput for chatGPT.
	 * 
	 * @return string for the model response.
	 * 
	 * @throws IOException if there is an error when connecting to the chatGPT.
	 */
	private List<String> chatGPT(ChatGPTInput modelInput) throws IOException {
		
		Map<String, Object> params = new HashMap<>();
		params.put("model", modelInput.getModel());
		params.put("messages", modelInput.getMessages());
		if (modelInput.getNumberOfOutputs() >= 0) { 
			params.put("n", modelInput.getNumberOfOutputs());
		}
		
		if (modelInput.getTemperature() >= 0) {
			params.put("temperature", modelInput.getTemperature());
		}
		
		if (modelInput.getMaxTokens() >= 1) {
			params.put("max_tokens", modelInput.getMaxTokens());
		}
		
		OpenaiChatResponse resModel = (OpenaiChatResponse) openaiWrapper.generateChatText(params);
		
		List<String> outputs = new ArrayList<>();
		for (Choice item : resModel.getChoices()) {
			outputs.add(item.getMessage().getContent());
		}
		
		return outputs;
	}
	

}
