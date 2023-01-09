package com.intellijava.com.intellijava.core;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.intellijava.com.intellijava.core.utils.Config2;

public class DeploymentTestCase {

	/**
	 * openaiKey - make sure the api key is empty 
	 */
	private final String openaiKey = Config2.getInstance().getProperty("url.openai.testkey");
	
	
	@Test
	public void testKeyIsEmpty() { 
		assertTrue(openaiKey.isBlank());
	}
	
}
