package com.intellijava.core.model.input;

import java.util.ArrayList;
import java.util.List;

import com.intellijava.core.model.input.ChatGPTMessage.Role;

/**
 * ChatGPTInput contains a list of messages to continue the conversation with
 * the chatbot.
 * 
 * @author github.com/Barqawiz
 *
 */
public class ChatGPTInput extends ChatModelInput {

	private List<ChatGPTMessage> messages = new ArrayList<>();
	private String model = "gpt-3.5-turbo";
	private float temperature = 1;
	private int numberOfOutputs = 1;
	private int maxTokens;

	/**
	 * Create ChatGPT input object with the system instruction.
	 * 
	 * Example of general system instruction: You are a helpful assistant.
	 * 
	 * @param systemMessage the system instruction to define the chat mode/theme.
	 */
	public ChatGPTInput(ChatGPTMessage systemMessage) {
		if (systemMessage.isSystemRol()) {
			messages.add(systemMessage);
		} else {
			throw new IllegalArgumentException(
					"The input type should be system to define the chatbot theme or instructions.");
		}
	}
	
	
	/**
	 * Create ChatGPT input object with the system instruction.
	 * 
	 * Example of general system instruction: You are a helpful assistant.
	 * 
	 * @param systemPrompt string input to define the chat mode/theme.
	 */
	public ChatGPTInput(String systemPrompt) {
		messages.add(new ChatGPTMessage(systemPrompt, Role.system));
		
	}

	/**
	 * Create ChatGPT input with the builder.
	 * 
	 * 
	 * @param system message
	 */
	private ChatGPTInput(Builder builder) {
		this.messages = builder.messages;
		this.temperature = builder.temperature;
		this.numberOfOutputs = builder.numberOfOutputs;
		this.maxTokens = builder.maxTokens;
		this.model = builder.model;
	}

	/**
	 * 
	 * Builder class for ChatGPTInput.
	 * 
	 */
	public static class Builder {

		private List<ChatGPTMessage> messages = new ArrayList<>();
		private String model = "gpt-3.5-turbo";
		private float temperature = 1;
		private int numberOfOutputs = 1;
		private int maxTokens;

		/**
		 * Builder for ChatGPTInput with the system or chatbot instructions.
		 * 
		 * Example of general system instruction: You are a helpful assistant.
		 * 
		 * @param systemMessage the system instruction to define the chat mode/theme.
		 */
		public Builder(ChatGPTMessage systemMessage) {
			if (systemMessage.isSystemRol()) {
				messages.add(systemMessage);
			} else {
				throw new IllegalArgumentException(
						"The input type should be system to define the chatbot theme or instructions.");
			}
		}
		
		/**
		 * Builder for ChatGPTInput with the system or chatbot instructions.
		 * 
		 * Example of general system instruction: You are a helpful assistant.
		 * 
		 * @param systemPrompt string input to define the chat mode/theme.
		 */
		public Builder(String systemPrompt) {
			messages.add(new ChatGPTMessage(systemPrompt, Role.system));
		}

		/**
		 * Add the chat message.
		 * 
		 * @param message (content and role)
		 * 
		 * @return instance of Builder.
		 */
		public Builder addMessage(ChatGPTMessage message) {
			messages.add(message);
			return this;
		}

		/**
		 * Sets the temperature.
		 * 
		 * @param temperature higher values means more risks and creativity.
		 * @return instance of Builder.
		 */
		public Builder setTemperature(float temperature) {
			this.temperature = temperature;
			return this;
		}

		/**
		 * Sets the numberOfOutputs
		 * 
		 * @param numberOfOutputs number of model outputs, default value is 1.
		 * 
		 * @return instance of Builder.
		 */
		public Builder setNumberOfOutputs(int numberOfOutputs) {
			this.numberOfOutputs = numberOfOutputs;
			return this;
		}

		/**
		 * Sets the maxTokens.
		 * 
		 * @param maxTokens maximum size of the model input and output.
		 * @return instance of Builder.
		 */
		public Builder setMaxTokens(int maxTokens) {
			this.maxTokens = maxTokens;
			return this;
		}
		
		/**
		 * Add input message for your prompt.
		 * 
		 * @param prompt model input with user role.
		 * 
		 * @return instance of Builder.
		 */
		public Builder addUserMessage(String prompt) {
			messages.add(new ChatGPTMessage(prompt, Role.user));
			return this;
		}

		/**
		 * Sets chatGPT model name, default is gpt-3.5-turbo.
		 * 
		 * @param model the model name.
		 * @return instance of Builder.
		 */
		public Builder setModel(String model) {
			this.model = model;
			return this;
		}

		/**
		 * Build the final ChatGPTInput object.
		 * 
		 * @return final LanguageModelInput object.
		 */
		public ChatGPTInput build() {
			return new ChatGPTInput(this);
		}
	}

	/**
	 * Add input message.
	 * 
	 * Example of chat flow: system: You are a helpful assistant. user: Who won the
	 * world series in 2020? assistant: The Los Angeles Dodgers won the World Series
	 * in 2020. user: Where was it played?
	 * 
	 * @param message chat input element with defined role.
	 */
	public void addMessage(ChatGPTMessage message) {
		messages.add(message);
	}
	
	/**
	 * Add input message for your prompt.
	 * @param prompt users input (query).
	 */
	public void addUserMessage(String prompt) {
		messages.add(new ChatGPTMessage(prompt, Role.user));
	}

	/**
	 * Remove all messages except the system message. To start new system, create a
	 * new object.
	 */
	public void cleanMessages() {
		if (messages.size() > 1) {
			ChatGPTMessage firstMessage = messages.get(0);
			messages.clear();
			messages.add(firstMessage);
		}
	}

	/**
	 * Delete last added message with the same message.getContent() and
	 * message.getRole().
	 * 
	 * @param message The message to delete
	 * @return true if a message was deleted, false otherwise
	 */
	public boolean deleteLastMessage(ChatGPTMessage message) {

		for (int i = messages.size() - 1; i >= 0; i--) {
			ChatGPTMessage currentMessage = messages.get(i);
			if (currentMessage.getContent().equals(message.getContent())
					&& currentMessage.getRole() == message.getRole()) {
				messages.remove(i);
				return true;
			}
		}

		return false;
	}

	/**
	 * Gets all messages.
	 * 
	 * @return list of messages
	 */
	public List<ChatGPTMessage> getMessages() {
		return messages;
	}

	/**
	 * Gets the temperature of the ChatGPTInput object.
	 * 
	 * @return temperature.
	 */
	public float getTemperature() {
		return temperature;
	}

	/**
	 * Sets the temperature.
	 * 
	 * @param temperature higher values means more risks and creativity.
	 */
	public void setTemperature(float temperature) {
		this.temperature = temperature;
	}

	/**
	 * Gets the number of model outputs.
	 * 
	 * @return numberOfOutputs
	 */
	public int getNumberOfOutputs() {
		return numberOfOutputs;
	}

	/**
	 * Sets the numberOfOutputs.
	 * 
	 * @param numberOfOutputs number of model outputs, default value is 1.
	 */
	public void setNumberOfOutputs(int numberOfOutputs) {
		this.numberOfOutputs = numberOfOutputs;
	}

	/**
	 * Gets the maxTokens.
	 * 
	 * @return maxTokens
	 */
	public int getMaxTokens() {
		return maxTokens;
	}

	/**
	 * Sets the maxTokens.
	 * 
	 * @param maxTokens maximum size of the model input and output.
	 */
	public void setMaxTokens(int maxTokens) {
		this.maxTokens = maxTokens;
	}

	/**
	 * Gets the model.
	 * 
	 * @return model
	 */
	public String getModel() {
		return model;
	}

	/**
	 * Sets the model.
	 * 
	 * @param model chatGPT model name, default is gpt-3.5-turbo.
	 */
	public void setModel(String model) {
		this.model = model;
	}

}
