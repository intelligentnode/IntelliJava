package com.intellijava.core.model;

import java.util.List;

import com.intellijava.core.model.OpenaiLanguageResponse.Choice;
import com.intellijava.core.model.OpenaiLanguageResponse.Usage;

/**
 * 
 * OpenaiChatResponse is a model class used to parse the response from the OpenAI ChatGPT API.
 * choices, Usage and Message are nested classes.
 * 
 * @author github.com/Barqawiz
 */
public class OpenaiChatResponse extends BaseRemoteModel {
	
    private String object;
    private long created;
    private String model;
    private Usage usage;
    private List<Choice> choices;

    /**
     * OpenaiChatResponse default constructor.
     */
    public OpenaiChatResponse() {
    	
    }
    
    /**
     * A nested class for the API response's choice represents the model output.
     */
    public static class Choice {
        private Message message;
        private String finish_reason;
        private int index;
        
        /**
         * Choice default constructor.
         */
        public Choice() {
        	
        }

        /**
         * Gets the model message.
         * 
         * @return message
         */
        public Message getMessage() {
            return message;
        }

        /**
         * Sets the model message
         * 
         * @param the message object of the model response.
         */
        public void setMessage(Message message) {
            this.message = message;
        }

        /**
         * Gets the reason to end the message for validating missing response reasons.
         *  
         * @return reason string
         */
        public String getFinish_reason() {
            return finish_reason;
        }

        /**
         * 
         * Sets the reason to end the message text
         * 
         * @param finish_reason
         */
        public void setFinish_reason(String finish_reason) {
            this.finish_reason = finish_reason;
        }

        /**
         * Gets the index property of the choice object
         * 
         * @return the index of the choice object
         */
        public int getIndex() {
            return index;
        }

        /**
         * Sets the index property of the choice object
         * 
         * @param index the index of the choice object
         */
        public void setIndex(int index) {
            this.index = index;
        }
    }
    
    /**
     * Usage is a nested class that represents a Usage object returned in the API response.
     */
    public static class Usage {
        private int prompt_tokens;
        private int completion_tokens;
        private int total_tokens;
        
        /**
         * Usage default constructor.
         */
        public Usage() {
        	
        }
        
        /**
         * Gets the prompt_tokens property
         * 
         * @return the value of prompt_tokens property
         */
		public int getPrompt_tokens() {
			return prompt_tokens;
		}
		/**
         * Sets the prompt_tokens property
         * 
         * @param prompt_tokens the new value of the prompt_tokens property
         */
		public void setPrompt_tokens(int prompt_tokens) {
			this.prompt_tokens = prompt_tokens;
		}
		/**
         * Gets the completion_tokens property
         * 
         * @return the value of completion_tokens property
         */
		public int getCompletion_tokens() {
			return completion_tokens;
		}
		/**
         * Sets the completion_tokens property
         * 
         * @param completion_tokens the new value of the completion_tokens property
         */
		public void setCompletion_tokens(int completion_tokens) {
			this.completion_tokens = completion_tokens;
		}
		/**
         * Gets the total_tokens property
         * 
         * @return the value of total_tokens property
         */
		public int getTotal_tokens() {
			return total_tokens;
		}
		/**
         * Sets the total_tokens property
         * 
         * @param total_tokens the new value of the total_tokens property
         */
		public void setTotal_tokens(int total_tokens) {
			this.total_tokens = total_tokens;
		}
 
    }

    /**
     * Message represents a message exchanged in a chat session.
    */
    public static class Message {
        private String role;
        private String content;
        
        public Message() {}

        /**
         * Gets the role of the sender of the message.
         * 
         * @return the role of the sender of the message.
         */
        public String getRole() {
            return role;
        }

        /**
         * Sets the role of the sender of the message.
         * 
         * @param role the role of the sender of the message.
         */
        public void setRole(String role) {
            this.role = role;
        }
        
        /**
         * Gets the content of the message.
         * 
         * @return the content of the message.
         */
        public String getContent() {
            return content;
        }

        /**
         * Sets the content of the message.
         * 
         * @param content the content of the message.
         */
        public void setContent(String content) {
            this.content = content;
        }
    }
    
    /**
     * Gets the object type of the API response.
     * 
     * @return the value of object property
     */
    public String getObject() {
        return object;
    }

    /**
     * Sets the object type of the API response.
     * 
     * @param object the new value of the object property
     */
    public void setObject(String object) {
        this.object = object;
    }

    /**
     * Gets the timestamp for the API response.
     * 
     * @return the value of created property
     */
    public long getCreated() {
        return created;
    }

    
    /**
     * Set the timestamp for the API response.
     * 
     * @param created the timestamp when the API request was created.
     */
    public void setCreated(long created) {
        this.created = created;
    }

    /**
     * Gets the name of the language model
     * 
     * @return the model id or name used to generate the API response
     */
	public String getModel() {
		return model;
	}

	/**
     * Setsthe name of the language model
     * 
     * @param model the model id or name used to generate the API response
     */
	public void setModel(String model) {
		this.model = model;
	}

	/**
     * Gets the usage statistics for generating the API response.
     * 
     * @return the usage object that contains usage statistics of the API request 
     */
	public Usage getUsage() {
		return usage;
	}

	/**
     * Set the usage statistics for generating the API response.
     * 
     * @param usage the usage object that contains usage statistics of the API request 
     */
	public void setUsage(Usage usage) {
		this.usage = usage;
	}

	/**
     * Gets the choices property
     * 
     * @return list of Choice objects that contain the generated completions and additional information 
     */
	public List<Choice> getChoices() {
		return choices;
	}

	/**
     * Sets the choices property
     * 
     * @param choices list of Choice objects that contain the generated completions and additional information 
     */
	public void setChoices(List<Choice> choices) {
		this.choices = choices;
	}
}
