# Intelligent Java
*IntelliJava V0.6.2*

Intelligent java (IntelliJava) is the ultimate tool for Java developers looking to integrate with the latest language models and deep learning frameworks. The library provides a simple and intuitive API with convenient methods for sending text input to models like GPT-3 and DALL·E, and receiving generated text or images in return. With just a few lines of code, you can easily access the power of cutting-edge AI models to enhance your projects.

The supported models:
- **OpenAI**: Access GPT-3 to generate text and DALL·E to generate images. OpenAI is preferred when you want quality results without tuning.
- **Cohere.ai**: Generate text; Cohere allows you to generate your language model to suit your specific needs.

# How to use

1. Import the core jar file OR maven dependency (check the Integration section).
2. Add Gson dependency if using the jar file; otherwise, it's handled by maven or Gradle.
3. Call the ``RemoteLanguageModel`` for the language models and ``RemoteImageModel`` for image generation.

## Integration
The package released to [Maven Central Repository](https://central.sonatype.com/artifact/io.github.barqawiz/intellijava.core/0.6.2).

Maven:
```xml
<dependency>
    <groupId>io.github.barqawiz</groupId>
    <artifactId>intellijava.core</artifactId>
    <version>0.6.2</version>
</dependency>
```

Gradle:

```
implementation 'io.github.barqawiz:intellijava.core:0.6.2'
```

Gradle(Kotlin):
```
implementation("io.github.barqawiz:intellijava.core:0.6.2")
```

Jar download:
[intellijava.jar](https://repo1.maven.org/maven2/io/github/barqawiz/intellijava.core/0.6.2/intellijava.core-0.6.2.jar).

For ready integration: try the [sample_code](https://github.com/Barqawiz/IntelliJava/tree/main/sample_code).

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
<br><br>
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

For full example check the code inside sample_code project.

## Third-party dependencies
The only dependencies is **GSON**.
*Required to add manually when using IntelliJava jar. However, if you imported this repo through Maven, it will handle the dependencies.*

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

## Documentation
[Go to Java docs](https://barqawiz.github.io/IntelliJava/javadocs/)

# Roadmap
Call for contributors:
- [x] Add support to OpenAI Completion API.
- [x] Add support to OpenAI DALL·E 2.
- [ ] Add support to other OpenAI functions.
- [x] Add support to cohere generate API.
- [ ] Add support to Google language models.
- [ ] Add support to Amazon language models.
- [ ] Add support to Azure models.
- [ ] Add support to Midjourney image generation.


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

<img src="images/intelligent_java_header_footer.png">
