package com.intellijava.com.intellijava.core.wrappers;

import java.io.IOException;
import java.util.Map;

import com.intellijava.com.intellijava.core.model.BaseRemoteModel;

public interface ImageModelInterface {

	/**
	 * 
	 * @param params map of input parameters keys and values
	 * @return
	 * @throws IOException
	 */
	public BaseRemoteModel generateImages(Map<String, Object> params) throws IOException;
}
