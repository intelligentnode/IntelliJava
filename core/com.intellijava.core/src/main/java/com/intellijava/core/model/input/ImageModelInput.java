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
package com.intellijava.core.model.input;

/**
 * 
 * ImageModelInput handle the input parameters for the majority of the remote image models.
 * 
 * @author github.com/Barqawiz
 *
 */
public class ImageModelInput {
    //Fields
	private String prompt;
    private int numberOfImages;
    private String imageSize;
    
    /**
     * Private Constructor for the Builder.
     * @param builder instance of Builder
     */
    private ImageModelInput(Builder builder) { 
    	this.prompt = builder.prompt;
        this.numberOfImages = builder.numberOfImages;
        this.imageSize = builder.imageSize;
    }
    
    
    /**
     * ImageModelInput default constructor.
     * 
     * @param prompt
     * @param numberOfImages
     * @param imageSize
     */
    public ImageModelInput(String prompt, int numberOfImages, String imageSize) {
		super();
		this.prompt = prompt;
		this.numberOfImages = numberOfImages;
		this.imageSize = imageSize;
	}



	/**
     * 
     * Builder class for ImageModelInput
     */
    public static class Builder {
        private String prompt;
        private int numberOfImages;
        private String imageSize;
        /**
         * Image input Constructor.
         * @param prompt : the text of the required action or the question.
         */
        public Builder(String prompt) {
        	this.prompt = prompt;
        }

        /**
         *  Setter for prompt
         * @param prompt : the text of the required action or the question.
         * @return instance of Builder
         */
        public Builder setPrompt(String prompt) {
            this.prompt = prompt;
            return this;
        }

        /**
         *  Setter for numberOfImages
         * @param numberOfImages : the number of the generated images.
         * @return instance of Builder
         */
        public Builder setNumberOfImages(int numberOfImages) {
            this.numberOfImages = numberOfImages;
            return this;
        }

        /**
         *  Setter for imageSize
         * @param imageSize : the size of the generated images, options are: 256x256, 512x512, or 1024x1024.
         * @return instance of Builder
         */
        public Builder setImageSize(String imageSize) {
            this.imageSize = imageSize;
            return this;
        }

        /**
         * Build the final ImageModelInput object.
         * @return final ImageModelInput object
         */
        public ImageModelInput build() {
            return new ImageModelInput(this);
        }
    }
    /**
     * Getter for prompt.
     * @return prompt
     */
    public String getPrompt() {
        return prompt;
    }

    /**
     * Getter for numberOfImages.
     * @return numberOfImages
     */
    public int getNumberOfImages() {
        return numberOfImages;
    }

    /**
     * Getter for imageSize.
     * @return imageSize
     */
    public String getImageSize() {
        return imageSize;
    }


    /**
     * Setter for prompt.
     * 
     * @param prompt
     */
	public void setPrompt(String prompt) {
		this.prompt = prompt;
	}


	/**
	 * Setter for numberOfImages.
	 * @param numberOfImages
	 */
	public void setNumberOfImages(int numberOfImages) {
		this.numberOfImages = numberOfImages;
	}


	/**
	 * Setter for imageSize.
	 * 
	 * @param imageSize
	 */
	public void setImageSize(String imageSize) {
		this.imageSize = imageSize;
	}
    
}

