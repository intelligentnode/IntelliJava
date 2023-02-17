package com.intellijava.core.model;

import com.google.gson.annotations.SerializedName;

public class AudioResponse extends BaseRemoteModel {

	@SerializedName("audioContent")
    private String audioContent;

	public String getAudioContent() {
		return audioContent;
	}

	public void setAudioContent(String audioContent) {
		this.audioContent = audioContent;
	}
	
}
