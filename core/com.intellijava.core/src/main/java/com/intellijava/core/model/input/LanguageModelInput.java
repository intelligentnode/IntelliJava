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
        this.numberOfOutputs = builder.numberOfOutputs;
    }
    
    
    /**
     * LanguageModelInput default constructor.
     * 
     * @param model
     * @param prompt
     * @param temperature
     * @param maxTokens
     * @param numberOfOutputs
     */
    public LanguageModelInput(String model, String prompt, float temperature, int maxTokens, int numberOfOutputs) {
		super();
		this.model = model;
		this.prompt = prompt;
		this.temperature = temperature;
		this.maxTokens = maxTokens;
		this.numberOfOutputs = numberOfOutputs;
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
         *  Sets the model.
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
         *  Sets the prompt.
         * @param prompt text of the required action or the question.
         * @return instance of Builder.
         */
        public Builder setPrompt(String prompt) {
            this.prompt = prompt;
            return this;
        }

        /**
         *  Sets the temperature.
         * @param temperature higher values means more risks and creativity. 
         * @return instance of Builder.
         */
        public Builder setTemperature(float temperature) {
            this.temperature = temperature;
            return this;
        }
        
        /**
         *  Sets the maxTokens.
         * @param maxTokens maximum size of the model input and output. 
         * @return instance of Builder
         */
        public Builder setMaxTokens(int maxTokens) {
            this.maxTokens = maxTokens;
            return this;
        }
        
        /**
        *  Sets the numberOfOutputs
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
     * Gets the model.
     * @return model
     */
    public String getModel() {
        return model;
    }

    /**
     * Gets the prompt.
     * @return prompt
     */
    public String getPrompt() {
        return prompt;
    }
    
    /**
     * Gets the temperature.
     * @return temperature
     */
    public float getTemperature() {
        return temperature;
    }
    
    /**
     * Gets the maxTokens.
     * @return maxTokens
     */
    public int getMaxTokens() {
        return maxTokens;
    }

    /**
     * Gets the number of model outputs.
     * @return numberOfOutputs
     */
	public int getNumberOfOutputs() {
		return numberOfOutputs;
	}

	/**
	 * Sets the model.
	 * 
	 * @param model
	 */
	public void setModel(String model) {
		this.model = model;
	}

	
	/**
	 * Sets the prompt.
	 * 
	 * @param prompt
	 */
	public void setPrompt(String prompt) {
		this.prompt = prompt;
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
	 * Sets the maxTokens.
	 * 
	 * @param maxTokens maximum size of the model input and output.
	 */
	public void setMaxTokens(int maxTokens) {
		this.maxTokens = maxTokens;
	}

	/**
	 * Sets the numberOfOutputs.
	 * 
	 * @param numberOfOutputs number of model outputs, default value is 1.
	 */
	public void setNumberOfOutputs(int numberOfOutputs) {
		this.numberOfOutputs = numberOfOutputs;
	}
    
}

