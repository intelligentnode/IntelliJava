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
package com.intellijava.com.intellijava.core.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.intellijava.com.intellijava.core.model.OpenaiImageResponse;
import com.intellijava.com.intellijava.core.model.OpenaiImageResponse.Data;
import com.intellijava.com.intellijava.core.wrappers.OpenAIWrapper;

/**
 * 
 * The RemoteImageModel class is used to generate images from text descriptions using an API key.
 * It currently supports OpenAI only. 
 * The class uses the OpenAIWrapper to generate the images and returns a list of URLs for the generated images.
 *
 * @author github.com/Barqawiz
 */
public class RemoateImageModel {
	
	private String keyType;
	private OpenAIWrapper openaiWrapper;
	
	/**
	 * 
	 * Constructor for creating a new RemoteImageModel object.
	 * 
	 * Creates an instance of the class and sets up the API key and the key type.
	 * 
	 * @param keyValue the API key.
	 * @param keyType support openai only.
	 * 
	 * @throws IllegalArgumentException if the keyType passed is not "openai".
	 * 
	 */
	public RemoateImageModel(String keyValue, String keyType) {
		
		if (keyType == "" || keyType == "openai") {
			this.keyType = "openai";
			openaiWrapper = new OpenAIWrapper(keyValue);
		} else {
			throw new IllegalArgumentException("This version support openai keyType only");
		}
	}

	
	/**
	 * 
	 * Generates images from a given text description.
	 * 
	 * @param prompt text of the required action or the question.
	 * @param numberOfImages number of the generated images.
	 * @param imageSize size of the generated images, options are: 256x256, 512x512, or 1024x1024.
	 * @return list of URLs of the generated images
	 * @throws IOException if there is a problem with the API connection
	 * 
	 */
	public List<String> generateImages(String prompt, int numberOfImages, String imageSize) throws IOException { 
		
		if (this.keyType == "openai") {
			return this.generateOpenaiImage(prompt, numberOfImages, imageSize);
		} else {
			throw new IllegalArgumentException("This version support openai keyType only");
		}
		
	}
	
	/**
	 * 
	 * Generates images from a given text description using OpenAI service.
	 * 
	 * @param prompt text of the required action or the question.
	 * @param numberOfImages number of the generated images.
	 * @param imageSize size of the generated images, options are: 256x256, 512x512, or 1024x1024.
	 * @return list of URLs of the generated images
	 * @throws IOException if there is a problem with the API connection
	 */
	private List<String>  generateOpenaiImage(String prompt, int numberOfImages, String imageSize) throws IOException { 
		
		List<String> images = new ArrayList<>();
		
		Map<String, Object> params = new HashMap<>();
		params.put("prompt", prompt);
        params.put("n", numberOfImages);
        params.put("size", imageSize);
        
        OpenaiImageResponse resModel = (OpenaiImageResponse) openaiWrapper.generateImages(params);
		
		List<Data> responseImages = resModel.getData();
    	for (Data data: responseImages) {
    		images.add(data.getUrl().toString());
    	}
    	
		return images;
		
	}
}
