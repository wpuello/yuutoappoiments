plugins {
	java
    id("org.springframework.boot") version "3.3.2"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "com.yuutoap"
version = "0.0.1-SNAPSHOT"
description = "Yuuto Doctors Appoiments"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
    implementation ("org.springframework.boot:spring-boot-starter-data-jpa")
    runtimeOnly ("org.postgresql:postgresql")
    implementation ("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-security")
	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")
    implementation ("org.springframework.boot:spring-boot-starter-web")
    testImplementation ("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
