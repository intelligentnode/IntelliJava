package com.intellijava.core.model.input;

/**
 * 
 * LanguageModelInput handle the input parameters for the majority of the remote language models.
 * 
 * Language models documentations:
 * - Openai : https://beta.openai.com/docs/api-reference/completions.
 * - Cohere : https://docs.cohere.ai/reference/generate
 * 
 * @author github.com/Barqawiz
 *
 */
public class LanguageModelInput {
    //Fields
	private String model; 
	private String prompt; 
	private float temperature; 
	private int maxTokens;
	private int numberOfOutputs = 1;
	
    /**
     * Private Constructor for the Builder.
     * @param builder instance of Builder
     */
    private LanguageModelInput(Builder builder) {
        this.model = builder.model;
        this.prompt = builder.prompt;
        this.temperature = builder.temperature;
        this.maxTokens = builder.maxTokens;
        this.maxTokens = builder.numberOfOutputs;
    }
    /**
     * 
     * Builder class for LanguageModelInput.
     * 
     */
    public static class Builder {
        private String model;
        private String prompt;
        private float temperature;
        private int maxTokens;
        private int numberOfOutputs = 1;

        /**
         * Language input Constructor.
         * @param prompt text of the required action or the question.
         */
        public Builder(String prompt) {
        	this.prompt = prompt;
        }

        /**
         *  Setter for model.
         * @param model the model name. 
         * 
         * The largest OpenAI model is text-davinci-003.
         * The largest cohere model is xlarge.
         * 
         * @return instance of Builder
         */
        public Builder setModel(String model) {
            this.model = model;
            return this;
        }

        /**
         *  Setter for prompt.
         * @param prompt text of the required action or the question.
         * @return instance of Builder.
         */
        public Builder setPrompt(String prompt) {
            this.prompt = prompt;
            return this;
        }

        /**
         *  Setter for temperature.
         * @param temperature higher values means more risks and creativity. 
         * @return instance of Builder.
         */
        public Builder setTemperature(float temperature) {
            this.temperature = temperature;
            return this;
        }
        
        /**
         *  Setter for maxTokens
         * @param maxTokens maximum size of the model input and output. 
         * @return instance of Builder
         */
        public Builder setMaxTokens(int maxTokens) {
            this.maxTokens = maxTokens;
            return this;
        }
        
        /**
        *  Setter for numberOfOutputs
        * @param numberOfOutputs number of model outputs, default value is 1. 
        * 
        * Cohere maximum value is five.
        * 
        * @return instance of Builder
        */
        public Builder setNumberOfOutputs(int numberOfOutputs) {
        	if (this.numberOfOutputs < 0)
        		this.numberOfOutputs = 0;
        	
    		this.numberOfOutputs = numberOfOutputs;
    		return this;
    	}

        /**
         * Build the final LanguageModelInput object.
         * @return final LanguageModelInput object
         */
        public LanguageModelInput build() {
            return new LanguageModelInput(this);
        }
    }
    /**
     * Getter for model.
     * @return model
     */
    public String getModel() {
        return model;
    }

    /**
     * Getter for prompt.
     * @return prompt
     */
    public String getPrompt() {
        return prompt;
    }
    
    /**
     * Getter for temperature.
     * @return temperature
     */
    public float getTemperature() {
        return temperature;
    }
    
    /**
     * Getter for maxTokens.
     * @return maxTokens
     */
    public int getMaxTokens() {
        return maxTokens;
    }

    /**
     * Getter for number of model outputs.
     * @return numberOfOutputs
     */
	public int getNumberOfOutputs() {
		return numberOfOutputs;
	}
    
    
}

