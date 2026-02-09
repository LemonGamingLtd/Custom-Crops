repositories {
    mavenCentral()
    maven("https://repo.lemongaming.ltd/repository/maven-public/") {
        credentials {
            username = providers.gradleProperty("lgNexusUser").get()
            password = providers.gradleProperty("lgNexusPass").get()
        }
    }
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    compileOnly(project(":api"))
    compileOnly("io.papermc.paper:paper-api:1.20.4-R0.1-SNAPSHOT")
    compileOnly("ltd.lemongaming:LGFurniture:1.0.0-SNAPSHOT")
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
    options.release.set(21)
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}