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
import java.util.HashMap;
import java.util.Map;

import com.intellijava.core.model.OpenaiLanguageResponse;
import com.intellijava.core.model.input.LanguageModelInput;
import com.intellijava.core.wrappers.OpenAIWrapper;

/**
 * A class to call the most sophisticated remote language models.
 *
 * This class provides an API for interacting with OpenAI's GPT-3 language model. It is designed to be easily extensible
 * to support other models in the future.
 *
 * @author github.com/Barqawiz
 * 
 */
public class RemoteLanguageModel {
	
	private String keyType;
	private OpenAIWrapper openaiWrapper;
	
	/**
     * Constructor for the RemoteLanguageModel class.
     *
     * Creates an instance of the class and sets up the API key and the key type. 
     * Currently, only the "openai" key type is supported.
     *
     * @param keyValue the API key.
     * @param keyType support openai only.
     *
     * @throws IllegalArgumentException if the keyType passed is not "openai".
     * 
     */
	public RemoteLanguageModel(String keyValue, String keyType) {
		
		if (keyType.isEmpty() || keyType.equals("openai")) {
			this.keyType = "openai";
			openaiWrapper = new OpenAIWrapper(keyValue);
		} else {
			throw new IllegalArgumentException("This version support openai keyType only");
		}
	}
	
	
	/**
	 * 
	 * Call a remote large model to generate any text based on the received prompt.
	 * 
	 * @param langInput flexible builder for language model parameters.
	 * @return string for the model response.
	 * @throws IOException if there is an error when connecting to the OpenAI API.
	 * @throws IllegalArgumentException if the keyType passed in the constructor is not "openai".
	 * 
	 */
	public String generateText(LanguageModelInput langInput) throws IOException { 
		
		if (this.keyType.equals("openai")) {
			return this.generateOpenaiText(langInput.getModel(), langInput.getPrompt(), 
					langInput.getTemperature(), langInput.getMaxTokens());
		} else {
			throw new IllegalArgumentException("This version support openai keyType only");
		}
		
	}

	/**
     * Private helper method for generating text from OpenAI GPT-3 model.
     *
     * @param model the model name, example: text-davinci-002. For more details about GPT-3 models, see: https://beta.openai.com/docs/models/gpt-3
     * @param prompt text of the required action or the question.
     * @param temperature higher values means more risks and creativity.
     * @param maxTokens maximum size of the model input and output.
     * @return string model response.
     * @throws IOException if there is an error when connecting to the OpenAI API.
     * 
     */
	private String generateOpenaiText(String model, String prompt, float temperature, int maxTokens) throws IOException { 
		
		Map<String, Object> params = new HashMap<>();
        params.put("model", model);
        params.put("prompt", prompt);
        params.put("temperature", temperature);
        params.put("max_tokens", maxTokens);
        
		OpenaiLanguageResponse resModel = (OpenaiLanguageResponse) openaiWrapper.generateText(params);
		
		return resModel.getChoices().get(0).getText();
		
	}
}
