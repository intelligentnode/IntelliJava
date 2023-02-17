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
package com.intellijava.core.model;

import com.google.gson.annotations.SerializedName;

/**
 * 
 * AudioResponse represents the response from the speech API that contains the audio content.
 * 
 * @author github.com/Barqawiz
 *
 */
public class AudioResponse extends BaseRemoteModel {

	/**
	 * Default AudioResponse constructor.
	 */
	public AudioResponse() {}
	
	/**
	 * The audio content generated from a text.
	 */
	@SerializedName("audioContent")
    private String audioContent;

	/**
	 * Gets the audio content generated from a text.
	 * @return audio content as a base64 string.
	 */
	public String getAudioContent() {
		return audioContent;
	}

	/**
	 * Sets the audio content generated from a text.
	 * 
	 * @param audioContent audio content as a base64 string.
	 */
	public void setAudioContent(String audioContent) {
		this.audioContent = audioContent;
	}
	
}
