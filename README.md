# IntelliJava-OpenaiAPI
*IntelliJava V0.1 beta*

IntelliJava allows java developers to easily integrate with the latest language models and deep learning frameworks like GPT-3 using few lines of code.
The first version supports only Openai APIs. It provides a simple and intuitive API with convenient methods for sending text input to GPT-3 and receiving generated text in return.

# How to use
1. Import the core jar file to your project [intellijava.jar](https://insta-answer-public.s3.amazonaws.com/opensource/IntelliJava/version0.1/com.intellijava.core-0.1-SNAPSHOT.jar).
2. Add gson dependency using maven or the jar file (check the dependencies section).
3. Call the RemoteLanguageModel as described in the code example.

# Code Example
```
// 1- initiate the remote language model 
String apiKey = "<todo-add-api-key>";
RemoteLanguageModel wrapper = new RemoteLanguageModel(apiKey, "openai");

// 2- call generateText with any command !
String command = "write a java code that says hello wrold";
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

MIT License

Copyright (c) 2023 Github.com/Barqawiz/IntelliJava

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
