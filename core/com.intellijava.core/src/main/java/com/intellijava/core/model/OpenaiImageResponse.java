package com.intellijava.core.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

/**
 * OpenaiImageResponse is a model class used to parse the response from the OpenAI image API.
 * 
 * The class contains a nested call Data
 * 
 * @author github.com/Barqawiz
 */
public class OpenaiImageResponse extends BaseRemoteModel {


	private long created;


	private List<Data> data;
	
	/**
	 * OpenaiImageResponse default constructor.
	 */
	public OpenaiImageResponse() {
		
	}

	/**
     * A nested class that represents an image object returned in the API response.
     */
	public static class Data {
		private String url;
		
		/**
		 * Data default constructor.
		 */
		public Data() {
			
		}

		/**
         * Gets the URL of the image
         * 
         * @return the URL of the image
         */
		public String getUrl() {
			return url;
		}
	}

	
	/**
     * Get the timestamp when the response was created
     * 
     * @return the timestamp when the response was created
     */
	public long getCreated() {
		return created;
	}

	/**
     * Get the list of data objects contained in the API response
     * 
     * @return the list of data objects contained in the API response
     */
	public List<Data> getData() {
		return data;
	}

}
