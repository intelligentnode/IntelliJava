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
package com.intellijava.com.intellijava.core.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Config2 is a class that reads the configuration properties from a "config.properties" file.
 * 
 * @author github.com/Barqawiz
 */
public class Config2 {
    private static Config2 instance;
    
    private Properties prop;

    /**
     * private constructor to prevent multiple instances from being created.
     */
    private Config2() {
        prop = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            prop.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Get the singleton instance of the Config2 class.
     * If the instance does not exist, it will be created.
     * 
     * @return the instance of Config2
     */
    public static Config2 getInstance() {
        if (instance == null) {
            instance = new Config2();
        }
        return instance;
    }

    /**
     * Get a property value by key.
     * 
     * @param key the key of the property to be retrieved.
     * @return the value of the property, or null if the key is not found.
     */
    public String getProperty(String key) {
        return prop.getProperty(key);
    }
}
