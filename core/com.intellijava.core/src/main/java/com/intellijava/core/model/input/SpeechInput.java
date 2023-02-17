package com.intellijava.core.model.input;

public class SpeechInput {

    private String text;
    private Gender gender;

    private SpeechInput(Builder builder) {
        this.text = builder.text;
        this.gender = builder.gender;
    }

    public static class Builder {

        private String text;
        private Gender gender = Gender.FEMALE;

        public Builder(String text) {
            this.text = text;
        }

        public Builder setText(String text) {
            this.text = text;
            return this;
        }

        public Builder setGender(Gender gender) {
            this.gender = gender;
            return this;
        }

        public SpeechInput build() {
            return new SpeechInput(this);
        }
    }

    public String getText() {
        return text;
    }

    public Gender getGender() {
        return gender;
    }

    public enum Gender {
        MALE, FEMALE;
    }
}
