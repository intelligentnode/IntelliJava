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
import com.intellijava.core.model.OpenaiImageResponse;
import com.intellijava.core.utils.Config2;
import com.intellijava.core.utils.ConnHelper;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;


public class GoogleAIWrapper implements SpeechModelInterface {

	private final String API_SPEECH_URL;
	private String API_KEY;
	
	public GoogleAIWrapper(String apiKey) {
		this.API_KEY = apiKey;
		this.API_SPEECH_URL = Config2.getInstance().getProperty("url.google.base").
				toString().replace("{1}", 
						Config2.getInstance().getProperty("url.google.speech.prefix"));
	}

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
