package com.intelliJava.test;

import java.io.IOException;
import java.util.List;

import com.intellijava.core.function.Chatbot;
import com.intellijava.core.model.SupportedChatModels;
import com.intellijava.core.model.input.ChatGPTInput;
import com.intellijava.core.model.input.ChatGPTMessage;
import com.intellijava.core.model.input.ChatModelInput;
import com.intellijava.core.model.input.ChatGPTMessage.Role;

public class ChatGPTApp {

	public static void main(String[] args) { 
		
		System.out.println("Start calling the chat!");
		
		// get the api key from https://openai.com/api/
		// TODO: replace <openai-api-key> with your API key.
		String apiKey = "<openai-api-key>";
		
		
		try {
			
			System.out.println("Try ChatGPT with one message:");
			simpleChat(apiKey);
			
			System.out.println("\n");
			
			System.out.println("Try ChatGPT with history chat and multi outputs:");
			historyChat(apiKey);
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
					
	}
	
	private static void simpleChat(String apiKey) throws IOException {
		
		Chatbot bot = new Chatbot(apiKey, SupportedChatModels.openai);
		String mode = "You are a helpful astronomy assistant.";
		
		ChatModelInput input = new ChatGPTInput.Builder(mode)
								.addUserMessage("what is the space between moon and earth")
								.build();
		
		List<String> resValues =  bot.chat(input);
		
		for (String result : resValues)
			System.out.println("- " + result);
		
	}
	
	private static void historyChat(String apiKey)  throws IOException {
		
		Chatbot bot = new Chatbot(apiKey, SupportedChatModels.openai);
		String mode = "You are a helpful assistant.";
		ChatModelInput input = new ChatGPTInput.Builder(mode)
				.addMessage(new ChatGPTMessage("Who won the world series in 2020?", Role.user))
				.addMessage(new ChatGPTMessage("The Los Angeles Dodgers won the World Series in 2020.", Role.assistant))
				.addMessage(new ChatGPTMessage("Where was it played?", Role.user))
				.setNumberOfOutputs(2)
				.build();
		
		List<String> resValues =  bot.chat(input);
		
		for (String result : resValues)
			System.out.println("- " + result);
	}
}
