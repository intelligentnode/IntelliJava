package com.intellijava.core.model;

import java.util.List;

/**
 * 
 * CohereTextResponse is a model class used to parse the response from the Cohere text API.
 * 
 * @author github.com/Barqawiz
 *
 */
public class CohereTextResponse {

	/** A unique identifier for the response.*/
	private String id;
    private List<Generation> generations;
    private String prompt;
    
    public static class Generation {
        private String id;
        private String text;

        /**
         * Get the unique identifier for the generation.
         *
         * @return the unique identifier for the generation.
         */
        public String getId() {
            return id;
        }

        /**
         * Sets the unique identifier for the generation.
         *
         * @param id the unique identifier for the generation.
         */
        public void setId(String id) {
            this.id = id;
        }

        /**
         * Get the model generated text.
         *
         * @return the generated text.
         */
        public String getText() {
            return text;
        }

        /**
         * Sets the model generated text.
         *
         * @param text the generated text.
         */
        public void setText(String text) {
            this.text = text;
        }
    }

    /**
     * Get the unique identifier for the response.
     *
     * @return the unique identifier for the response.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the unique identifier for the response.
     *
     * @param id the unique identifier for the response.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Get the list of generated texts.
     *
     * @return the list of generated texts.
     */
    public List<Generation> getGenerations() {
        return generations;
    }

    /**
     * Sets the list of generated texts.
     *
     * @param generations the list of generated texts.
     */
    public void setGenerations(List<Generation> generations) {
        this.generations = generations;
    }

    /**
     * 
     * Get the user prompt.
     * 
     * @return prompt the user input.
     */
    public String getPrompt() {
        return prompt;
    }

    /**
     * Sets the user prompt.
     *
     * @param prompt the user input.
     */
    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }
}
