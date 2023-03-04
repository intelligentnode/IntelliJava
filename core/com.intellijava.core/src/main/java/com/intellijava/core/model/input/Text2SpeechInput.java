package com.intellijava.core.model.input;

/**
 * SpeechTextInput class represents the speech input with the provided text and gender.
 * 
 * It also provides a Builder to create an instance with optional fields.
 * 
 * @author github.com/Barqawiz
 * 
 */
public class Text2SpeechInput {

    /**
     * The text of the speech input.
     */
    private String text;

    /**
     * The gender of the speech input.
     */
    private Gender gender;

    /**
     * Constructor to create a new SpeechInput object with provided text and gender.
     * 
     * @param text the text of the speech input.
     * @param gender the gender of the speech input.
     */
    public Text2SpeechInput(String text, Gender gender) {
    	this.text = text;
    	this.gender = gender;
    }

    /**
     * Constructor that creates a new SpeechInput object with a Builder.
     * 
     * @param builder a Builder to create an instance of SpeechInput with optional fields.
     */
    private Text2SpeechInput(Builder builder) {
        this.text = builder.text;
        this.gender = builder.gender;
    }
    
    /**
     * Builder class to create an instance of SpeechInput with optional fields.
     */
    public static class Builder {

        /**
         * The text of the speech input.
         */
        private String text;

        /**
         * The gender of the speech input. 
         * Default is FEMALE.
         */
        private Gender gender = Gender.FEMALE;

        /**
         * Constructor that creates a new Builder object with the provided text.
         * 
         * @param text the text of the speech input.
         */
        public Builder(String text) {
            this.text = text;
        }

        /**
         * Setter for speech input text.
         * 
         * @param text the text of the speech input.
         * @return the current instance of the Builder.
         */
        public Builder setText(String text) {
            this.text = text;
            return this;
        }

        /**
         * Setter for the speech input gender.
         * @param gender the gender of the speech input.
         * @return the current instance of the Builder.
         */
        public Builder setGender(Gender gender) {
            this.gender = gender;
            return this;
        }

        /**
         * Build a new instance of SpeechInput with the values set in the Builder.
         * @return a new instance of SpeechInput.
         */
        public Text2SpeechInput build() {
            return new Text2SpeechInput(this);
        }
    }

    /**
     * Getter for speech text.
     * @return the text of the speech input.
     */
    public String getText() {
        return text;
    }

    /**
     * Getter for the speech gender.
     * @return the gender of the speech input.
     */
    public Gender getGender() {
        return gender;
    }

    /**
     * Setter for the speech text.
     * @param text the text of the speech input.
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Setter for the speech gender.
     * @param gender the gender of the speech input.
     */
    public void setGender(Gender gender) {
        this.gender = gender;
    }

    /**
     * Enum for the speech input gender.
     */
    public enum Gender {
        /** female voice */FEMALE, /** male voice */MALE;
    }
}
