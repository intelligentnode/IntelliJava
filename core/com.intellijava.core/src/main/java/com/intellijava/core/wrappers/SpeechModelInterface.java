package com.intellijava.core.wrappers;

import java.io.IOException;
import java.util.Map;

import com.intellijava.core.model.BaseRemoteModel;

public interface SpeechModelInterface {

	
	public BaseRemoteModel generateSpeech(Map<String, Object> params) throws IOException;
}
