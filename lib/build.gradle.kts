plugins {
    `java-gradle-plugin`
    `maven-publish`
    kotlin("jvm") version "1.9.0"
}

group = "org.edwardian"
version = "1.0"

gradlePlugin {
    plugins {
        create("kotlinTeamTaskPlugin") {
            id = "org.edwardian.kotlinteamtaskplugin"
            implementationClass = "KotlinTeamTaskPlugin"
        }
    }
}

kotlin {
    jvmToolchain(17) 
}

publishing {

    publications {
        create<MavenPublication>("pluginKotlin") {
            from(components["kotlin"])
            artifactId = "kotlinteamtaskplugin"
        }
    }

    repositories {
        mavenLocal()
    }
}

repositories {
    mavenCentral()
    mavenLocal()
}
dependencies {
    testImplementation(kotlin("test"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.8.1")
}

tasks.test {
    useJUnitPlatform()  // Ensure that this is added
}


