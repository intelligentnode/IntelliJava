package com.intellijava.core.wrappers;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import com.intellijava.core.model.BaseRemoteModel;
import com.intellijava.core.model.CohereLanguageResponse;
import com.intellijava.core.model.OpenaiLanguageResponse;
import com.intellijava.core.utils.Config2;
import com.intellijava.core.utils.ConnHelper;

/**
 * 
 * Wrapper for the Cohere API models. 
 * 
 * @author github.com/Barqawiz
 *
 */
public class CohereAIWrapper implements LanguageModelInterface{

	private final String API_BASE_URL = Config2.getInstance().getProperty("url.cohere.base");
	private final String COHERE_VERSION = Config2.getInstance().getProperty("url.cohere.version");
    private String API_KEY;

    /**
     * CohereAIWrapper constructor with the API key
     * 
     * @param apiKey cohere API key, generate if from your account.
     */
    public CohereAIWrapper(String apiKey) {
    	this.API_KEY = apiKey;
    }

    /**
	 * 
	 * Generate text from remote large language model based on the received prompt.
	 * 
	 * @param params key and value for the API parameters
	 * 			model the model name, either medium or xlarge.
	 * 			prompt text of the required action or the question.
	 * 			temperature higher values means more risks and creativity.
	 * 			max_tokens maximum size of the model input and output.
	 * @return BaseRemoteModel for model response
	 * @throws IOException if there is an error when connecting to the OpenAI API.
	 */
	@Override
	public BaseRemoteModel generateText(Map<String, Object> params) throws IOException {
		
		String url = API_BASE_URL + Config2.getInstance().getProperty("url.cohere.completions");

        String json = ConnHelper.convertMaptToJson(params);

        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Authorization", "Bearer " + API_KEY);
        connection.setRequestProperty("Cohere-Version", COHERE_VERSION);
        connection.setDoOutput(true);

        try (OutputStream outputStream = connection.getOutputStream()) {
            outputStream.write(json.getBytes(StandardCharsets.UTF_8));
        }

        if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
        	String errorMessage = ConnHelper.getErrorMessage(connection);
            throw new IOException(errorMessage);
        }

        // get the response and convert to model 
        CohereLanguageResponse resModel = ConnHelper.convertSteamToModel(connection.getInputStream(), CohereLanguageResponse.class);
        return resModel;
	}
    
}
