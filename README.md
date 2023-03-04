<p align="center">
<img src="images/intelligent_java_header.png" width="500em">
</p>

<p align="center">

<a href="https://central.sonatype.com/artifact/io.github.barqawiz/intellijava.core/0.8.0" alt="maven build">
    <img src="https://img.shields.io/maven-central/v/io.github.barqawiz/intellijava.core?style=flat-square" />
</a>

<a href="https://github.com/Barqawiz/IntelliJava/releases" alt="github releases">
    <img src="https://img.shields.io/github/v/release/Barqawiz/IntelliJava?style=flat-square" />
</a>

<a href="https://opensource.org/licenses/Apache-2.0" alt="licenses tag">
    <img src="https://img.shields.io/github/license/Barqawiz/IntelliJava?style=flat-square" />
</a>

</p>

# Intelligent Java
Intelligent java (IntelliJava) is the ultimate tool to integrate with the latest language models and deep learning frameworks using java. The library provides an intuitive functions for sending input to models like ChatGPT and DALL·E, and receiving generated text, speech or images. With just a few lines of code, you can easily access the power of cutting-edge AI models to enhance your projects.

The supported models:
- **OpenAI**: Access ChatGPT, GPT3 to generate text and DALL·E to generate images. OpenAI is preferred for quality results without tuning.
- **Cohere.ai**: Generate text; Cohere allows you to custom your language model to suit your specific needs.
- **Google AI**: Generate audio from text; Access DeepMind’s speech models.

# How to use

1. Add the maven dependency or import the core jar file (check the Integration section).
2. Add Gson dependency if using the jar file; otherwise, it's handled by maven or Gradle.
3. Call the ``RemoteLanguageModel`` for the language models, ``RemoteImageModel`` for image generation and ``RemoteSpeechModel`` for text to speech models, ``Chatbot`` for ChatGPT.

## Integration
The package released to Maven Central Repository:

Maven:
```xml
<dependency>
    <groupId>io.github.barqawiz</groupId>
    <artifactId>intellijava.core</artifactId>
    <version>0.8.0</version>
</dependency>
```

Gradle:

```
implementation 'io.github.barqawiz:intellijava.core:0.8.0'
```

Gradle(Kotlin):
```
implementation("io.github.barqawiz:intellijava.core:0.8.0")
```

Jar download:
[intellijava.jar](https://repo1.maven.org/maven2/io/github/barqawiz/intellijava.core/0.8.0/intellijava.core-0.8.0.jar).

## Code Example
**Language model code** (2 steps):
```java
// 1- initiate the remote language model
String apiKey = "<add-openai-api-key>";
RemoteLanguageModel langModel = new RemoteLanguageModel(apiKey, "openai");

// 2- call generateText with any command !
LanguageModelInput langInput = new LanguageModelInput.Builder("Summarize the plot of the 'Inception' movie in two sentences")
                .setModel("text-davinci-003").setTemperature(0.7f).setMaxTokens(50).build();
String resValue = langModel.generateText(langInput);
```
Output:```Inception follows Dom Cobb, a professional thief, who is offered a chance at redemption in exchange for planting an idea in a target's mind. He must navigate a dangerous landscape of dream-sharing technology and battle his inner demons in order to complete the mission and find his way back to reality.```

<br>

**Image generation code** (2 steps):
```java
// 1- initiate the remote image model
RemoteImageModel imageModel = new RemoteImageModel(apiKey, "openai");

// 2- call generateImages with any command !
ImageModelInput imageInput = new ImageModelInput.Builder("teddy writing a blog in times square")
                .setNumberOfImages(2).setImageSize("1024x1024").build();
List<String> images = imageModel.generateImages(imageInput);
```
Output:<br>
<img src="images/response_image.png" height="220px">

<br>

**Text to speech code** (2 steps):
```java
// 1- initiate the remote speech model
RemoteSpeechModel model = new RemoteSpeechModel(apiKey, SpeechModels.google);

// 2- call generateEnglishText with any text
Text2SpeechInput input = new Text2SpeechInput.Builder("Hi, I am Intelligent Java.").build();
byte[] decodedAudio = model.generateEnglishText(input);
```
Output:<br>
```Java
// save temporary audio file for testing
AudioHelper.saveTempAudio(decodedAudio);
```

<br>

**ChatGPT code**:
```java
// 1- initiate the chat model.
Chatbot bot = new Chatbot(apiKey, SupportedChatModels.openai);

// 2- prepare the chat history by calling addMessage.
String mode = "You are a helpful astronomy assistant.";
ChatModelInput input = new ChatGPTInput.Builder(mode)
                       .addUserMessage("what is the space between moon and earth").build();

// 3- call chat!
List<String> resValues =  bot.chat(input);
```
Output:``` The average distance between the Moon and the Earth is about 238,855 miles (384,400 kilometers). ```

<br>

For full examples and ready integration try [the sample_code](https://github.com/Barqawiz/IntelliJava/tree/main/sample_code).

## Third-party dependencies
The only dependencies is **GSON**.
*Required to add manually when using IntelliJava jar. However, if you imported this repo through Maven, it will handle the dependencies.*

For jar download:
[gson download repo](https://search.maven.org/artifact/com.google.code.gson/gson/2.10.1/jar)

## Documentation
- [Go to Java docs](https://barqawiz.github.io/IntelliJava/javadocs/)
- [Tutorial article](https://albarqawi.medium.com/intelligent-java-a-gateway-to-the-latest-ai-models-c08c09513672)

# Roadmap
Call for contributors:
- [x] Add support to OpenAI Completion.
- [x] Add support to OpenAI DALL·E 2.
- [ ] Add support to other OpenAI functions.
- [x] Add support to cohere generate API.
- [ ] Add support to Google language models.
- [x] Add support to Google speech models.
- [ ] Add support to Amazon language models.
- [ ] Add support to Azure nlp models.
- [ ] Add support to Midjourney image generation.
- [ ] Add support to WuDao 2.0 model.

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
