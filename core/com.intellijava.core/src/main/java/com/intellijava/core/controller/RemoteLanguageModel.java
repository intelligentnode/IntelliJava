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
import com.intellijava.core.model.CohereLanguageResponse;
import com.intellijava.core.model.OpenaiLanguageResponse;
import com.intellijava.core.model.SupportedLangModels;
import com.intellijava.core.model.input.LanguageModelInput;
import com.intellijava.core.wrappers.CohereAIWrapper;
import com.intellijava.core.wrappers.OpenAIWrapper;

/**
 * RemoteLanguageModel class to call the most sophisticated remote language
 * models.
 * 
 * This class support: - Openai: - url: openai.com - description: provides an
 * API for interacting with OpenAI's GPT-3 language model. - model names :
 * text-davinci-003, text-curie-001, text-babbage-001, more.
 * 
 * - cohere: - url: cohere.ai - description: provides an API for interacting
 * with generate language model. - it is recommended to fine tune your model or
 * add example of the response in the prompt when calling cohere models. - model
 * names : medium or xlarge
 *
 * @author github.com/Barqawiz
 * 
 */
public class RemoteLanguageModel {

	private SupportedLangModels keyType;
	private OpenAIWrapper openaiWrapper;
	private CohereAIWrapper cohereWrapper;

	/**
	 * Constructor for the RemoteLanguageModel class.
	 *
	 * Creates an instance of the class and sets up the key and the API type.
	 *
	 * @param keyValue      the API key.
	 * @param keyTypeString either openai (default) or cohere or send empty string
	 *                      for default value.
	 *
	 * @throws IllegalArgumentException if the keyType passed is not "openai".
	 * 
	 */
	public RemoteLanguageModel(String keyValue, String keyTypeString) {

		if (keyTypeString.isEmpty()) {
			keyTypeString = SupportedLangModels.openai.toString();
		}

		List<String> supportedModels = this.getSupportedModels();

		if (supportedModels.contains(keyTypeString)) {
			this.initiate(keyValue, SupportedLangModels.valueOf(keyTypeString));
		} else {
			String models = String.join(" - ", supportedModels);
			throw new IllegalArgumentException("The received keyValue not supported. Send any model from: " + models);
		}
	}

	/**
	 * Constructor for the RemoteLanguageModel class.
	 *
	 * Creates an instance of the class and sets up the API key and the enum key
	 * type.
	 *
	 * @param keyValue the API key.
	 * @param keyType  enum version from the key type (SupportedModels).
	 *
	 * @throws IllegalArgumentException if the keyType passed is not "openai".
	 * 
	 */
	public RemoteLanguageModel(String keyValue, SupportedLangModels keyType) {
		this.initiate(keyValue, keyType);
	}

	/**
	 * Get the supported models names as array of string
	 * 
	 * @return supportedModels
	 */
	public List<String> getSupportedModels() {
		SupportedLangModels[] values = SupportedLangModels.values();
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
	private void initiate(String keyValue, SupportedLangModels keyType) {
		// set the model type
		this.keyType = keyType;

		// generate the related model
		if (keyType.equals(SupportedLangModels.openai)) {
			this.openaiWrapper = new OpenAIWrapper(keyValue);
		} else if (keyType.equals(SupportedLangModels.cohere)) {
			this.cohereWrapper = new CohereAIWrapper(keyValue);
		}
	}

	/**
	 * 
	 * Call a remote large model to generate any text based on the received prompt.
	 * 
	 * @param langInput flexible builder for language model parameters.
	 * 
	 * @return string for the model response.
	 * @throws IOException              if there is an error when connecting to the
	 *                                  OpenAI API.
	 * @throws IllegalArgumentException if the keyType passed in the constructor is
	 *                                  not "openai".
	 * 
	 */
	public String generateText(LanguageModelInput langInput) throws IOException {

		if (this.keyType.equals(SupportedLangModels.openai)) {
			return this.generateOpenaiText(langInput.getModel(), langInput.getPrompt(), langInput.getTemperature(),
					langInput.getMaxTokens());
		} else if (this.keyType.equals(SupportedLangModels.cohere)) {
			return this.generateCohereText(langInput.getModel(), langInput.getPrompt(), langInput.getTemperature(),
					langInput.getMaxTokens());
		} else {
			throw new IllegalArgumentException("This version support openai keyType only");
		}

	}

	/**
	 * Private helper method for generating text from OpenAI GPT-3 model.
	 *
	 * @param model       the model name, example: text-davinci-003. For more
	 *                    details about GPT-3 models, see:
	 *                    https://beta.openai.com/docs/models/gpt-3
	 * @param prompt      text of the required action or the question.
	 * @param temperature higher values means more risks and creativity.
	 * @param maxTokens   maximum size of the model input and output.
	 * @return string model response.
	 * @throws IOException if there is an error when connecting to the OpenAI API.
	 * 
	 */
	private String generateOpenaiText(String model, String prompt, float temperature, int maxTokens)
			throws IOException {

		if (model.equals(""))
			model = "text-davinci-003";

		Map<String, Object> params = new HashMap<>();
		params.put("model", model);
		params.put("prompt", prompt);
		params.put("temperature", temperature);
		params.put("max_tokens", maxTokens);

		OpenaiLanguageResponse resModel = (OpenaiLanguageResponse) openaiWrapper.generateText(params);

		return resModel.getChoices().get(0).getText();

	}

	/**
	 * Private helper method for generating text from Cohere model.
	 *
	 * @param model       the model name, either medium or xlarge.
	 * @param prompt      text of the required action or the question.
	 * @param temperature higher values means more risks and creativity.
	 * @param maxTokens   maximum size of the model input and output.
	 * @return string model response.
	 * @throws IOException if there is an error when connecting to the API.
	 * 
	 */
	private String generateCohereText(String model, String prompt, float temperature, int maxTokens)
			throws IOException {

		if (model.equals(""))
			model = "xlarge";

		Map<String, Object> params = new HashMap<>();
		params.put("model", model);
		params.put("prompt", prompt);
		params.put("temperature", temperature);
		params.put("max_tokens", maxTokens);

		CohereLanguageResponse resModel = (CohereLanguageResponse) cohereWrapper.generateText(params);

		return resModel.getGenerations().get(0).getText();

	}
}
