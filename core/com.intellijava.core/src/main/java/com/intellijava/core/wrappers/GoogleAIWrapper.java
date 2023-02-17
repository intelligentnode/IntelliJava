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
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Scanner;
import com.intellijava.core.model.AudioResponse;
import com.intellijava.core.model.BaseRemoteModel;
import com.intellijava.core.utils.Config2;
import com.intellijava.core.utils.ConnHelper;
import java.nio.charset.StandardCharsets;

/**
 * 
 * Wrapper for Google speech services.
 * 
 * To use this wrapper:
 * 1- Go to console.cloud.google.com.
 * 2- Enable "Cloud Text-to-Speech API" from APIs Services.
 * 3- Generate API key from APIs and services Credentials page.
 * 
 * @author github.com/Barqawiz
 *
 */
public class GoogleAIWrapper implements SpeechModelInterface {

	private final String API_SPEECH_URL;
	private String API_KEY;
	
	/**
	 * Constructs a new GoogleAIWrapper object with the API key.
	 * 
	 * @param apiKey the key generated from google console Credentials page
	 */
	public GoogleAIWrapper(String apiKey) {
		this.API_KEY = apiKey;
		this.API_SPEECH_URL = Config2.getInstance().getProperty("url.google.base").
				toString().replace("{1}", 
						Config2.getInstance().getProperty("url.google.speech.prefix"));
	}

	/**
	 * Generates speech from text using the Google speech service.
	 * 
	 * @param params speech model input parameters.
	 * @return BaseRemoteModel
	 * @throws IOException in case of communication errors.
	 */
	@Override
	public BaseRemoteModel generateSpeech(Map<String, Object> params) throws IOException {
		
		String url = API_SPEECH_URL + Config2.getInstance().getProperty("url.google.synthesize.postfix");
		String json = getSynthesizeInput(params);
		
		HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
        connection.setRequestProperty("X-Goog-Api-Key", API_KEY);
        connection.setDoOutput(true);
		
        try (OutputStream outputStream = connection.getOutputStream()) {
            outputStream.write(json.getBytes(StandardCharsets.UTF_8));
        }

        if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
        	String errorMessage = ConnHelper.getErrorMessage(connection);
            throw new IOException(errorMessage);
        }
        
        // get the response and convert to model
        AudioResponse resModel = ConnHelper.convertSteamToModel(connection.getInputStream(), AudioResponse.class);
        
		return resModel;
	}
	
	/**
	 * 
	 * Prepare the synthesize service input.
	 * 
	 * @param params
	 * @return String
	 * @throws IOException
	 */
	private String getSynthesizeInput(Map<String, Object> params) throws IOException {
		String modelInput = "";
		
		// read model input template
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream("google-synthesize-input.txt");
		Scanner scanner = new Scanner(inputStream).useDelimiter("\\A");
		modelInput = scanner.hasNext() ? scanner.next() : "";
		
		// fill the details
		String text = (String) params.get("text");
        String languageCode = (String) params.get("languageCode");
        String name = (String) params.get("name");
        String ssmlGender = (String) params.get("ssmlGender");
        
        modelInput =  String.format(modelInput, text, languageCode, name, ssmlGender);
		
		return modelInput;
	}
}
