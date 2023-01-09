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
 * @author  github.com/Barqawiz
 * 
 * A model class to parse openai completion API response.
 *
 */
public class OpenaiLanguageResponse extends BaseRemoteModel {
    
    private String object;
    private long created;
    private String model;
    private List<Choice> choices;
    private Usage usage;

    public static class Choice {
    	/**
    	 * text is the generated response from the model. 
    	 */
        private String text;
        private int index;
        private Object logprobs;
        private String finish_reason;
		public String getText() {
			return text;
		}
		public void setText(String text) {
			this.text = text;
		}
		public int getIndex() {
			return index;
		}
		public void setIndex(int index) {
			this.index = index;
		}
		public Object getLogprobs() {
			return logprobs;
		}
		public void setLogprobs(Object logprobs) {
			this.logprobs = logprobs;
		}
		public String getFinish_reason() {
			return finish_reason;
		}
		public void setFinish_reason(String finish_reason) {
			this.finish_reason = finish_reason;
		}

        
    }

    public static class Usage {
        private int prompt_tokens;
        private int completion_tokens;
        private int total_tokens;
		public int getPrompt_tokens() {
			return prompt_tokens;
		}
		public void setPrompt_tokens(int prompt_tokens) {
			this.prompt_tokens = prompt_tokens;
		}
		public int getCompletion_tokens() {
			return completion_tokens;
		}
		public void setCompletion_tokens(int completion_tokens) {
			this.completion_tokens = completion_tokens;
		}
		public int getTotal_tokens() {
			return total_tokens;
		}
		public void setTotal_tokens(int total_tokens) {
			this.total_tokens = total_tokens;
		}

        
    }

	public String getObject() {
		return object;
	}

	public void setObject(String object) {
		this.object = object;
	}

	public long getCreated() {
		return created;
	}

	public void setCreated(long created) {
		this.created = created;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public List<Choice> getChoices() {
		return choices;
	}

	public void setChoices(List<Choice> choices) {
		this.choices = choices;
	}

	public Usage getUsage() {
		return usage;
	}

	public void setUsage(Usage usage) {
		this.usage = usage;
	}

    
}
