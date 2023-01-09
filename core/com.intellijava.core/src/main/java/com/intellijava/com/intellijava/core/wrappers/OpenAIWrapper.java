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
import com.intellijava.com.intellijava.core.model.OpenaiResponseModel;
import com.intellijava.com.intellijava.core.utils.ConnHelper;

/**
 * 
 * @author @author  github.com/Barqawiz
 * 
 * A wrapper to hide the complexity of openai API.
 *
 */
public class OpenAIWrapper {
	
    private final String API_BASE_URL = "https://api.openai.com";
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
    public OpenaiResponseModel generateText(String model, String prompt, float temperature, int maxTokens) throws IOException {
        String url = API_BASE_URL + "/v1/completions";

        Map<String, Object> params = new HashMap<>();
        params.put("model", model);
        params.put("prompt", prompt);
        params.put("temperature", temperature);
        params.put("max_tokens", maxTokens);

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
            throw new IOException("Unexpected HTTP response: " + connection.getResponseCode());
        }

        // get the response and convert to model 
        OpenaiResponseModel resModel = ConnHelper.convertSteamToModel(connection.getInputStream(), OpenaiResponseModel.class);
        return resModel;
    }
}