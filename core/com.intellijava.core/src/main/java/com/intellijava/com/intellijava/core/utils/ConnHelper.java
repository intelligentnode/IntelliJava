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
package com.intellijava.com.intellijava.core.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import com.google.gson.Gson;
import com.intellijava.com.intellijava.core.model.OpenaiLanguageResponse;

/**
 * 
 * @author  github.com/Barqawiz
 * 
 * Common helper functions to call remote services.
 *
 */
public class ConnHelper {

	private static Gson gson = new Gson();
	
	/**
	 * Convert a map of string key and object value to Json string.
	 * @param params any key and value.
	 * @return
	 */
	public static String convertMaptToJson(Map<String, Object> params) {
		
		return gson.toJson(params);
	}
	
	/**
	 * Convert API input stream to the relevant class model.
	 * 
	 * @param <T> the type of the response model.
	 * @param stream input stream from the called API.
	 * @param classOfT the type of the response model.
	 * @return parsed response model T
	 */
	public static <T> T convertSteamToModel(InputStream stream, Class<T> classOfT) {
		
		Reader streamReader = new InputStreamReader(stream, StandardCharsets.UTF_8);
        T resModel = gson.fromJson(streamReader, classOfT);
        
		return resModel;
	}
	
	
	public static String getErrorMessage(HttpURLConnection connection) throws IOException {
		
		String apiErrorMessage = readStream(connection.getErrorStream());
		
		String errorMessage = "Unexpected HTTP response: " + connection.getResponseCode();
		
		// add extra error details - if any
		if (apiErrorMessage != null) {
			errorMessage += " Error details:"+apiErrorMessage;
		}
		
		return errorMessage;
	}
	
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
