package com.intellijava.core.model.input;

/**
 * 
 * ChatGPTMessage represent a message element for the chat.
 * 
 * @author github.com/Barqawiz
 *
 */
public class ChatGPTMessage extends ChatMessage {

	private Role role;
	
	/**
	 * ChatGPTMessage default constructor.
	 */
	public ChatGPTMessage() {}
	
	/**
	 * Initiate ChatGPTMessage with the mandatory parameters. 
	 * 
	 * @param content text input
	 * @param role system or user or assistant to define who sent the message.
	 * 
	 */
	public ChatGPTMessage(String content, Role role) {
		setContent(content);
		setRole(role);
	}

	/**
	 * get the input role.
	 * system : represents the instruction message.
	 * user : represents the end user message.
	 * assistant : represents the model message.
	 * 
	 * @return system | user | assistant
	 */
	public Role getRole() {
		return role;
	}
	
	/**
	 * set the input role.
	 * system : represents the instruction message.
	 * user : represents the end user message.
	 * assistant : represents the model message.
	 * 
	 * @param role system | user | assistant
	 */
	public void setRole(Role role) {
		this.role = role;
	}
	
	/**
	 * Sets the role for chatGPT instructions.
	 */
	public void setSystemRol() {
		this.role = Role.system;
	}
	
	/**
	 * 
	 * Validate the current message role is system.
	 * 
	 * @return true if system role.
	 */
	public boolean isSystemRol() {
		return this.role == Role.system;
	}
	
	/**
	 * Sets the role for the user message.
	 */
	public void setUserRol() {
		this.role = Role.user;
	}
	
	/**
	 * Sets the role for the model message.
	 */
	public void setAssistantRol() {
		this.role = Role.assistant;
	}
	
	/**
	 *
	 * Chat input roles.
	 * 
	 * @author github.com/Barqawiz
	 *
	 */
	public enum Role { 
		/** system role defines the chat mode or theme.*/system, 
		/** user role defines the end user message or query.*/user, 
		/** assistant role defines the chatbot response.*/assistant
	}
	
}
