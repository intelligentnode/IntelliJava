package com.intelliJava.test;
import java.io.IOException;

import com.intellijava.com.intellijava.core.controller.RemoteLanguageModel;

public class App {

	public static void main(String[] args) {
		
		System.out.println( "Start calling the API!" );
        
        try {
        	
        	// get the api key from https://openai.com/api/
        	String apiKey = "<todo-add-api-key>";
        	assert apiKey.equals("<todo-add-api-key>"): "change the API key to use the function, go to https://openai.com/api/";
        	
        	// initiate the remote language model wrapper with openai details
        	RemoteLanguageModel wrapper = new RemoteLanguageModel(apiKey, "openai");
        	
        	
        	// change the command to any text you want like write a funny short story.
        	String command = "write a java code that says hello wrold";
        	String resValue = wrapper.generateText("text-davinci-002", command, 0.5F, 100);
			
			System.out.print(resValue);
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
