plugins {
	id("org.springframework.boot") version "3.0.0"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	id("io.freefair.lombok") version "6.6"
	java
}

val postgresqlVersion: String by project
val flywayVersion: String by project

group = "com.powem.co"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
}

dependencies {
	//spring
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-security")

	// db
	runtimeOnly("org.postgresql:postgresql:$postgresqlVersion")

	// flyway
	implementation("org.flywaydb:flyway-core:$flywayVersion")

	// hibernate
	implementation("com.vladmihalcea:hibernate-types-60:2.21.1")

	//lombok
	//    providedRuntime("org.projectlombok:lombok")
	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")

	// JWT
	implementation("io.jsonwebtoken:jjwt-api:0.11.2")
	runtimeOnly("io.jsonwebtoken:jjwt-impl:0.11.2")
	runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.11.2")

	//dev tools
	runtimeOnly("org.springframework.boot:spring-boot-devtools")

	//test
	testImplementation("org.springframework.boot:spring-boot-starter-test")
//    testImplementation("org.springframework.security:spring-security-test") //not yet in use
}

tasks.test {
	useJUnitPlatform()
}

tasks {
	processResources {
		filesMatching("**/application.yml") {
			filter {
				it.replace("#project.version#", version as String)
			}
			filter {
				it.replace("#project.name#", rootProject.name)
			}
		}
	}

	bootJar {
		enabled = true
	}

	jar {
		enabled = false
	}
}
