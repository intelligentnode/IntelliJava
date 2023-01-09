package com.intellijava.com.intellijava.core.controller;

import java.io.IOException;

import com.intellijava.com.intellijava.core.model.OpenaiResponseModel;
import com.intellijava.com.intellijava.core.wrappers.OpenAIWrapper;

/**
 * 
 * @author github.com/Barqawiz
 * 
 * A class to call the most sophisticated remote language models.
 * 
 * This version support Openai GPT model only, with a plan to add more models in the future.
 *
 */
public class RemoteLanguageModel {
	
	private String keyType;
	private OpenAIWrapper openaiWrapper;
	
	/**
	 * 
	 * @param keyValue the API key.
	 * @param keyType support openai only.
	 */
	public RemoteLanguageModel(String keyValue, String keyType) {
		
		if (keyType == "" || keyType == "openai") {
			this.keyType = "openai";
			openaiWrapper = new OpenAIWrapper(keyValue);
		} else {
			throw new IllegalArgumentException("This version support openai keyType only");
		}
	}
	
	
	/**
	 * Call a remote large model to generate any text based on the received prompt.
	 * 
	 * @param model the model name. the largest openai model is text-davinci-002 
	 * @param prompt text of the required action or the question.
	 * @param temperature higher values means more risks and creativity.
	 * @param maxTokens maximum size of the model input and output.
	 * @return string model response
	 * @throws IOException
	 */
	public String generateText(String model, String prompt, float temperature, int maxTokens) throws IOException { 
		
		if (this.keyType == "openai") {
			return this.generateOpensiText(model, prompt, temperature, maxTokens);
		} else {
			throw new IllegalArgumentException("This version support openai keyType only");
		}
		
	}

	/**
	 * 
	 * @param model the model name, example: text-davinci-002. For more details about GPT3 models: https://beta.openai.com/docs/models/gpt-3
	 * @param prompt text of the required action or the question.
	 * @param temperature higher values means more risks and creativity.
	 * @param maxTokens maximum size of the model input and output.
	 * @return string model response
	 * @throws IOException
	 */
	private String generateOpensiText(String model, String prompt, float temperature, int maxTokens) throws IOException { 
		
		OpenaiResponseModel resModel = openaiWrapper.generateText(model, prompt, temperature, maxTokens);
		
		return resModel.getChoices().get(0).getText();
		
	}
}
