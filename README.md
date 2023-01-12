# IntelliJava-OpenaiAPI
*IntelliJava V0.5.5*

IntelliJava allows java developers to easily integrate with the latest language models and deep learning frameworks using few lines of java code.
The first version supports only Openai APIs. It provides a simple and intuitive API with convenient methods for sending text input to models like (GPT-3 and DALL·E) and receiving generated text or images in return.


# How to use
1. Import the core jar file to your project or add the maven package (check Integration section).
2. Add gson dependency using maven or the jar file (check dependencies section).
3. Call the ``RemoteLanguageModel`` for the language model and ``RemoateImageModel`` for image generation.

## Integration
The package released to [Maven Central Repository](https://central.sonatype.dev/artifact/io.github.barqawiz/intellijava.core/0.5.5).

Maven:
```xml
<dependency>
    <groupId>io.github.barqawiz</groupId>
    <artifactId>intellijava.core</artifactId>
    <version>0.5.5</version>
</dependency>
```

Gradle:

```
implementation group: 'io.github.barqawiz', name: 'intellijava.core', version: '0.5.5'
```

Gradle(Kotlin):
```
implementation("io.github.barqawiz:intellijava.core:0.5.5")
```

Jar download:
[intellijava.jar](https://insta-answer-public.s3.amazonaws.com/opensource/IntelliJava/version0.5.5/intellijava.core-0.5.5.jar).

For ready integration: try the sample_code.

## Code Example
**Language model code** (2 steps):
```java
// 1- initiate the remote language model 
String apiKey = "<add-openai-api-key>";
RemoteLanguageModel langModel = new RemoteLanguageModel(apiKey, "openai");

// 2- call generateText with any command !
LanguageModelInput langInput = new LanguageModelInput.Builder("return a java code that says hello world")
                .setModel("text-davinci-002").setTemperature(0.7f).setMaxTokens(50).build();
String resValue = langModel.generateText(langInput);
```
Output:
``` System.out.println("Hello, World!");```<br><br>
**Image generation code** (2 steps):
```java
// 1- initiate the remote image model 
RemoateImageModel imageModel = new RemoateImageModel(apiKey, "openai");

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
- [x] Add support to OpenAI DALL·E 2.
- [ ] Add support to other OpenAI functions.
- [ ] Add support to Google language models.
- [ ] Add support to Amazon language models.
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

<img src="images/IntelliJava_logo.png" height="300px">
