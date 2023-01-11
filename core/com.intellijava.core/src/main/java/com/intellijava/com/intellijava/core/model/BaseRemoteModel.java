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
package com.intellijava.com.intellijava.core.model;


/**
* BaseRemoteModel is an abstract class that represents a common model with a basic parameter, id.
* 
* @author github.com/Barqawiz
*/
public abstract class BaseRemoteModel {
	private String id;
	
	/**
     * Get the id of the model
     * 
     * @return the id
     */
	public String getId() {
		return id;
	}

	
	/**
     * Sets the id of the model
     * 
     * @param id the id
     */
	public void setId(String id) {
		this.id = id;
	}
}
