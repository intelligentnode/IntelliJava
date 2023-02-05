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
package com.intellijava.core.wrappers;

import java.io.IOException;
import java.util.Map;

import com.intellijava.core.model.BaseRemoteModel;

/**
 * 
 * ImageModelInterface represent the standard methods for any model that generate text.
 * 
 * @author github.com/Barqawiz
 *
 */
public interface LanguageModelInterface {

	/**
	 * 
	 * Generate text from remote large language model based on the received prompt.
	 * 
	 * @param params key and value for the API parameters.
	 * @return BaseRemoteModel or any sub class.
	 * @throws IOException if there is an error when connecting to the server.
	 */
	public BaseRemoteModel generateText(Map<String, Object> params) throws IOException;
}
