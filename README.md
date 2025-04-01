# 🛠️ KotlinTeamTaskPlugin  

![Gradle](https://img.shields.io/badge/Gradle-Plugin-green) ![Kotlin](https://img.shields.io/badge/Kotlin-1.9.0-blue) ![JetBrains](https://img.shields.io/badge/JetBrains-Kotlin%20Team-purple)

A **custom Gradle plugin** designed to support Kotlin in the **Build Server Protocol** as part of a project for an internship at the **Kotlin team, JetBrains Munich**. This plugin helps analyze Kotlin source files in a Gradle project and provides structured output.

> **Project Mentor**: Yahor Berdnikau  

## 📌 Features  
✅ Scans `src/` directory for Kotlin files (`.kt` and `.kts`)  
✅ Generates a markdown summary (`kotlin_sources.md`) listing Kotlin files  
✅ Ensures project compatibility with the **Build Server Protocol (BSP)**  
✅ Skips execution if Kotlin plugin is not applied  

## 🎥 Video Guide  

Watch the step-by-step guide:  

[![Watch the tutorial](https://img.youtube.com/vi/eojEGlzfLz0/maxresdefault.jpg)](https://www.youtube.com/watch?v=eojEGlzfLz0)  

👉 **Click the image to watch on YouTube!**

## 🚀 Installation & Usage  

### 1️⃣ Apply the Plugin  
Ensure your Gradle project uses **Kotlin DSL (`build.gradle.kts`)** and apply the plugin:

```kotlin
plugins {
    id("org.edwardian.kotlinteamtaskplugin") version "1.0"
}
```

### 2️⃣ Configure Repositories

Make sure your project can access Maven Local (if using a local build):
```
pluginManagement {
    repositories {
        mavenLocal()
        mavenCentral()
    }
}
```

### 3️⃣ Run the Task
Use Gradle to execute the task:
```
./gradlew outputKotlinFiles
```

This will generate a file at:
`build/kotlin_sources.md`
containing a list of all `.kt` and `.kts` files in your project.

### 🔬 Example Output
example output in blank android project with `First.kt` file added
```md
# .KT files:
### app/src/androidTest/java/com/example/myapplication/ExampleInstrumentedTest.kt
### app/src/test/java/com/example/myapplication/ExampleUnitTest.kt
### app/src/First.kt
### app/src/main/java/com/example/myapplication/ui/theme/Color.kt
### app/src/main/java/com/example/myapplication/ui/theme/Theme.kt
### app/src/main/java/com/example/myapplication/ui/theme/Type.kt
### app/src/main/java/com/example/myapplication/MainActivity.kt

# .KTS files:
### There are no .kts files.
### Repeat output
(since our gradle task running in build folder, we're going to have an error. That s why we should call it from root)
``` bash
cd .. 
gradle outputKotlinFiles
```/
```



