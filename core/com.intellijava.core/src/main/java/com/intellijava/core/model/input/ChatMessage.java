package com.intellijava.core.model.input;

/**
 * 
 * ChatInput represents an input element for chatbots.
 * 
 * @author github.com/Barqawiz
 *
 */
public class ChatMessage {

	private String content;
	
	/**
	 * ChatMessage default constructor.
	 */
	public ChatMessage() {
		
	}

	/**
	 * Gets the text content.
	 * 
	 * @return content string message.
	 */
	public String getContent() {
		return content;
	}

	/**
	 * Sets the text content.
	 * 
	 * @param content string message.
	 */
	public void setContent(String content) {
		this.content = content;
	}
	
	
}
