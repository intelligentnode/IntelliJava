package com.intellijava.core.wrappers;

import java.io.IOException;
import java.util.Map;

import com.intellijava.core.model.BaseRemoteModel;

/**
 * 
 * ImageModelInterface represent the standard methods for any model that generate images.
 * 
 * @author github.com/Barqawiz
 *
 */
public interface ImageModelInterface {
	
	/**
	 * 
	 * Generate image from remote model.
	 * 
	 * @param params map of input keys and values.
	 * @return BaseRemoteModel or any sub class.
	 * @throws IOException if there is an error when connecting to the server.
	 */
	public BaseRemoteModel generateImages(Map<String, Object> params) throws IOException;
}
