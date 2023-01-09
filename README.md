# IntelliJava-OpenaiAPI
*IntelliJava V0.2 beta*

IntelliJava allows java developers to easily integrate with the latest language models and deep learning frameworks like GPT-3 using few lines of code.
The first version supports only Openai APIs. It provides a simple and intuitive API with convenient methods for sending text input to GPT-3 and receiving generated text in return.


# How to use
1. Import the core jar file to your project [intellijava.jar](https://insta-answer-public.s3.amazonaws.com/opensource/IntelliJava/version0.2/com.intellijava.core-0.2.jar).
2. Add gson dependency using maven or the jar file (check the dependencies section).
3. Call the RemoteLanguageModel as described in the code example.

# Code Example
```
// 1- initiate the remote language model 
String apiKey = "<todo-add-api-key>";
RemoteLanguageModel wrapper = new RemoteLanguageModel(apiKey, "openai");

// 2- call generateText with any command !
String command = "return a java code that says hello wrold";
String resValue = wrapper.generateText("text-davinci-002", command, 0.5F, 100);
```
For full example check the samples projects inside sample_code folder.

# Dependencies 
The only dependencies is **GSON**.

For Maven:
```
<dependency>
    <groupId>com.google.code.gson</groupId>
    <artifactId>gson</artifactId>
    <version>2.8.9</version>
</dependency>
```

For Gradle:
```
dependencies {
  implementation 'com.google.code.gson:gson:2.8.9'
}
```

For jar download:
[gson download repo](https://search.maven.org/artifact/com.google.code.gson/gson/2.8.9/jar)


# Roadmap
Call for contributors:
- [x] Add support to OpenAI Completion API.
- [ ] Add support to OpenAI DALL·E 2 (under development).
- [ ] Add support to other OpenAI functions.
- [ ] Add support to Google language models.
- [ ] Add support to Amazon language models.


# License
Apache License

Copyright 2023 Github.com/Barqawiz/IntelliJava

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

<img src="images/IntelliJava_logo.png" height="300px">
