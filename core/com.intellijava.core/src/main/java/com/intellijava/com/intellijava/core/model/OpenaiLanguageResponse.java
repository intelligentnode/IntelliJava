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
package com.intellijava.com.intellijava.core.model;

import java.util.List;

/**
 * 
 * OpenaiLanguageResponse is a model class used to parse the response from the OpenAI language API.
 * choices and usage are nested classes.
 * 
 * @author github.com/Barqawiz
 */
public class OpenaiLanguageResponse extends BaseRemoteModel {
    
    private String object;
    private long created;
    private String model;
    private List<Choice> choices;
    private Usage usage;

    /**
     * A nested class that represents an choice object returned in the API response.
     */
    public static class Choice {
    	/**
    	 * text is the generated response from the model. 
    	 */
        private String text;
        private int index;
        private Object logprobs;
        private String finish_reason;
        
        /**
         * Get the text property of the choice object
         * 
         * @return the text of the choice object
         */
		public String getText() {
			return text;
		}
		/**
         * Sets the text property of the choice object
         * 
         * @param text the text of the choice object
         */
		public void setText(String text) {
			this.text = text;
		}
		/**
         * Get the index property of the choice object
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
		/**
         * Get the logprobs property of the choice object
         * 
         * @return the logprobs of the choice object
         */
		public Object getLogprobs() {
			return logprobs;
		}
		/**
         * Sets the logprobs property of the choice object
         * 
         * @param logprobs the logprobs of the choice object
         */
		public void setLogprobs(Object logprobs) {
			this.logprobs = logprobs;
		}
		/**
         * Get the finish_reason property of the choice object
         * 
         * @return the finish_reason of the choice object
         */
		public String getFinish_reason() {
			return finish_reason;
		}
		/**
         * Sets the finish_reason property of the choice object
         * 
         * @param finish_reason the finish_reason of the choice object
         */
		public void setFinish_reason(String finish_reason) {
			this.finish_reason = finish_reason;
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
         * Get the prompt_tokens property
         * 
         * @return the value of prompt_tokens property
         */
		public int getPrompt_tokens() {
			return prompt_tokens;
		}
		/**
         * Set the prompt_tokens property
         * 
         * @param prompt_tokens the new value of the prompt_tokens property
         */
		public void setPrompt_tokens(int prompt_tokens) {
			this.prompt_tokens = prompt_tokens;
		}
		/**
         * Get the completion_tokens property
         * 
         * @return the value of completion_tokens property
         */
		public int getCompletion_tokens() {
			return completion_tokens;
		}
		/**
         * Set the completion_tokens property
         * 
         * @param completion_tokens the new value of the completion_tokens property
         */
		public void setCompletion_tokens(int completion_tokens) {
			this.completion_tokens = completion_tokens;
		}
		/**
         * Get the total_tokens property
         * 
         * @return the value of total_tokens property
         */
		public int getTotal_tokens() {
			return total_tokens;
		}
		/**
         * Set the total_tokens property
         * 
         * @param total_tokens the new value of the total_tokens property
         */
		public void setTotal_tokens(int total_tokens) {
			this.total_tokens = total_tokens;
		}

        
    }
    
    /**
     * Get the object property
     * 
     * @return the value of object property
     */
	public String getObject() {
		return object;
	}

	/**
     * Set the object property
     * 
     * @param object the new value of the object property
     */
	public void setObject(String object) {
		this.object = object;
	}
	/**
     * Get the created property
     * 
     * @return the value of created property
     */
	public long getCreated() {
		return created;
	}

	/**
     * Set the created property
     * 
     * @param created the timestamp when the API request was created.
     */
	public void setCreated(long created) {
		this.created = created;
	}

	/**
     * Get the model name property
     * 
     * @return the model id or name used to generate the API response
     */
	public String getModel() {
		return model;
	}

	/**
     * Set the model name property
     * 
     * @param model the model id or name used to generate the API response
     */
	public void setModel(String model) {
		this.model = model;
	}

	/**
     * Get the choices property
     * 
     * @return list of Choice objects that contain the generated completions and additional information 
     */
	public List<Choice> getChoices() {
		return choices;
	}

	/**
     * Set the choices property
     * 
     * @param choices list of Choice objects that contain the generated completions and additional information 
     */
	public void setChoices(List<Choice> choices) {
		this.choices = choices;
	}

	/**
     * Get the usage property
     * 
     * @return the usage object that contains usage statistics of the API request 
     */
	public Usage getUsage() {
		return usage;
	}

	/**
     * Set the usage property
     * 
     * @param usage the usage object that contains usage statistics of the API request 
     */
	public void setUsage(Usage usage) {
		this.usage = usage;
	}

    
}
