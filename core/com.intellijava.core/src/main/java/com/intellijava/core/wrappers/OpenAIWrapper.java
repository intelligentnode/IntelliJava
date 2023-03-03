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
package com.intellijava.core.wrappers;


import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import com.intellijava.core.model.BaseRemoteModel;
import com.intellijava.core.model.OpenaiChatResponse;
import com.intellijava.core.model.OpenaiImageResponse;
import com.intellijava.core.model.OpenaiLanguageResponse;
import com.intellijava.core.utils.Config2;
import com.intellijava.core.utils.ConnHelper;

/**
 * 
 * OpenAIWrapper is a wrapper for the OpenAI API, providing a simplified interface for interacting with the API.
 *
 * @author github.com/Barqawiz
 * 
 */
public class OpenAIWrapper implements LanguageModelInterface, ImageModelInterface {
	
    private final String API_BASE_URL = Config2.getInstance().getProperty("url.openai.base");
    private String API_KEY;

    /**
     * OpenAIWrapper constructor with the API key
     * 
     * @param apiKey openai API key, generate if from your account.
     */
    public OpenAIWrapper(String apiKey) {
    	this.API_KEY = apiKey;
    }
    
    /**
	 * 
	 * Generate text from remote large language model based on the received prompt.
	 * 
	 * @param params key and value for the API parameters
	 * 			model the model name, example: text-davinci-002.
	 * 			prompt text of the required action or the question.
	 * 			temperature higher values means more risks and creativity.
	 * 			maxTokens maximum size of the model input and output.
	 * 
	 * For more GPT3 models: https://beta.openai.com/docs/models/gpt-3
	 * 
	 * @return BaseRemoteModel for model response
	 * @throws IOException if there is an error when connecting to the OpenAI API.
	 */
    public BaseRemoteModel generateText(Map<String, Object> params) throws IOException {
    	
        String url = API_BASE_URL + Config2.getInstance().getProperty("url.openai.completions");

        String json = ConnHelper.convertMaptToJson(params);
        
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Authorization", "Bearer " + API_KEY);
        connection.setDoOutput(true);

        try (OutputStream outputStream = connection.getOutputStream()) {
            outputStream.write(json.getBytes(StandardCharsets.UTF_8));
        }

        if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
        	String errorMessage = ConnHelper.getErrorMessage(connection);
            throw new IOException(errorMessage);
        }

        // get the response and convert to model 
        OpenaiLanguageResponse resModel = ConnHelper.convertSteamToModel(connection.getInputStream(), OpenaiLanguageResponse.class);
        return resModel;
    }
    
    /**
	 * 
	 * Generate text from remote large language model based on the chat history.
	 * 
	 * @param params key and value for the API parameters
	 * 			model the model name, example: gpt-3.5-turbo. 
	 * 			messages a dictionary of role and prompt.
	 * 			temperature higher values means more risks and creativity.
	 * 			maxTokens maximum size of the model input and output.
	 * 
	 * For more GPT3 models: https://beta.openai.com/docs/models/gpt-3
	 * 
	 * @return BaseRemoteModel for model response
	 * @throws IOException if there is an error when connecting to the OpenAI API.
	 */
    public BaseRemoteModel generateChatText(Map<String, Object> params) throws IOException {
    	
        String url = API_BASE_URL + Config2.getInstance().getProperty("url.openai.chatgpt");

        String json = ConnHelper.convertMaptToJson(params);
        
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Authorization", "Bearer " + API_KEY);
        connection.setDoOutput(true);

        try (OutputStream outputStream = connection.getOutputStream()) {
            outputStream.write(json.getBytes(StandardCharsets.UTF_8));
        }

        if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
        	String errorMessage = ConnHelper.getErrorMessage(connection);
            throw new IOException(errorMessage);
        }

        // get the response and convert to model 
        OpenaiChatResponse resModel = ConnHelper.convertSteamToModel(connection.getInputStream(), OpenaiChatResponse.class);
        return resModel;
    }
    
    /**
     * 
     * Generate image from openai image model.
     * 
     * @param params should include prompt, n, size
     * 			prompt: text of the required action or the question.
     * 			n: number of the generated images.
     * 			size: 256x256, 512x512, or 1024x1024.
     * @return BaseRemoteModel
     * @throws IOException if there is an error when connecting to the OpenAI API.
     */
    public BaseRemoteModel generateImages(Map<String, Object> params) throws IOException {
    	
        String url = API_BASE_URL + Config2.getInstance().getProperty("url.openai.imagegenerate");

        String json = ConnHelper.convertMaptToJson(params);

        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Authorization", "Bearer " + API_KEY);
        connection.setDoOutput(true);

        try (OutputStream outputStream = connection.getOutputStream()) {
            outputStream.write(json.getBytes(StandardCharsets.UTF_8));
        }

        if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
        	String errorMessage = ConnHelper.getErrorMessage(connection);
            throw new IOException(errorMessage);
        }

        // get the response and convert to model
        OpenaiImageResponse resModel = ConnHelper.convertSteamToModel(connection.getInputStream(), OpenaiImageResponse.class);
        return resModel;
    }
}