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
package com.intellijava.core.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import com.google.gson.Gson;
import com.intellijava.core.model.OpenaiLanguageResponse;

/**
 * ConnHelper is a class that contains common helper functions to call remote services.
 * 
 * @author github.com/Barqawiz
 */
public class ConnHelper {

	private static Gson gson = new Gson();
	
	/**
	 * Convert a map of string key and object value to Json string.
	 * 
	 * @param params a map of key-value pairs to be converted to json.
	 * @return a json string representation of the provided map.
	 */
	public static String convertMaptToJson(Map<String, Object> params) {
		
		return gson.toJson(params);
	}
	
	/**
	 * Convert API input stream to the relevant class model.
	 * 
	 * @param <T> the type of the response model.
	 * @param stream input stream from the called API.
	 * @param classOfT the class type of the response model.
	 * @return an instance of the provided class type, T, that represents the API response.
	 */
	public static <T> T convertSteamToModel(InputStream stream, Class<T> classOfT) {
		
		Reader streamReader = new InputStreamReader(stream, StandardCharsets.UTF_8);
        T resModel = gson.fromJson(streamReader, classOfT);
        
		return resModel;
	}
	
	
	/**
	 * Get the error message from an HttpURLConnection.
	 * 
	 * @param connection the HttpURLConnection from which to get the error message.
	 * @return the error message as a string.
	 * @throws IOException if there is an issue reading the error stream.
	 */
	public static String getErrorMessage(HttpURLConnection connection) throws IOException {
		
		String apiErrorMessage = readStream(connection.getErrorStream());
		
		String errorMessage = "Unexpected HTTP response: " + connection.getResponseCode();
		
		// add extra error details - if any
		if (apiErrorMessage != null) {
			errorMessage += " Error details:"+apiErrorMessage;
		}
		
		return errorMessage;
	}
	
	/**
	 * Read an input stream and return its contents as a string.
	 * 
	 * @param stream the input stream to read.
	 * @return the contents of the input stream as a string.
	 * @throws IOException if there is an issue reading the input stream.
	 */
	public static String readStream(InputStream stream) throws IOException {
		  StringBuilder result = new StringBuilder();
		  try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream))) {
		    String line;
		    while ((line = reader.readLine()) != null) {
		      result.append(line);
		    }
		  }
		  return result.toString();
		}
}
