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
package com.intellijava.com.intellijava.core.wrappers;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.intellijava.com.intellijava.core.model.BaseRemoteModel;
import com.intellijava.com.intellijava.core.model.OpenaiResponseModel;
import com.intellijava.com.intellijava.core.utils.Config2;
import com.intellijava.com.intellijava.core.utils.ConnHelper;

/**
 * 
 * @author @author  github.com/Barqawiz
 * 
 * A wrapper to hide the complexity of openai API.
 *
 */
public class OpenAIWrapper implements LanguageModelInterface {
	
    private final String API_BASE_URL = Config2.getInstance().getProperty("url.openai.base");
    private String API_KEY;

    /**
     * 
     * @param apiKey openai API key, generate if from your account.
     */
    public OpenAIWrapper(String apiKey) {
    	this.API_KEY = apiKey;
    }
    
    /**
     * 
     * @param model the model name, example: text-davinci-002. For more details about GPT3 models: https://beta.openai.com/docs/models/gpt-3
     * @param prompt text of the required action or the question.
     * @param temperature higher values means more risks and creativity.
     * @param maxTokens maximum size of the model input and output.
     * @return the model response.
     * @throws IOException
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
        OpenaiResponseModel resModel = ConnHelper.convertSteamToModel(connection.getInputStream(), OpenaiResponseModel.class);
        return resModel;
    }
}