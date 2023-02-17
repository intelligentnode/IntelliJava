package com.intellijava.core.wrappers;

import java.io.IOException;
import java.util.Map;

import com.intellijava.core.model.BaseRemoteModel;

/**
 * SpeechModelInterface represent the standard methods for any speech model.
 * 
 * @author  github.com/Barqawiz
 *
 */
public interface SpeechModelInterface {

	/**
	 * Generate speech from text.
	 * 
	 * @param params dictionary of speech model inputs.
	 * @return BaseRemoteModel
	 * @throws IOException in case of error.
	 */
	public BaseRemoteModel generateSpeech(Map<String, Object> params) throws IOException;
}
