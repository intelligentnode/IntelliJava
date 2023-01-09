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
package com.intellijava.com.intellijava.core;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.intellijava.com.intellijava.core.utils.Config2;

/**
 * Unit test to run before generating the jar files
 *
 */
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
