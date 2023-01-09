package com.intellijava.com.intellijava.core.utils;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import com.google.gson.Gson;
import com.intellijava.com.intellijava.core.model.OpenaiResponseModel;

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
}
