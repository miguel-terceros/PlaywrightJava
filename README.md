# Playwright Java

Java + JUnit + Playwright automation examples for browser testing.

This repository contains practical demos for:
- browser automation
- locators and element interactions
- assertions
- authentication and storage state
- network interception and API-style testing
- browser configuration and advanced scenarios

## Prerequisites

- Java 17+
- Gradle 8.x
- Git

> The project includes the Gradle wrapper, so you typically do not need to install Gradle globally.

## Clone and install

```bash
git clone https://github.com/miguel-terceros/PlaywrightJava.git
cd PlaywrightJava
./gradlew build
```

## Run tests

```bash
./gradlew test
```

Run a specific test:

```bash
./gradlew test --tests "ProjectSetupTest"
./gradlew test --tests "Advanced.Authentication"
```

## Local web app

The project includes a static app under `web/` for local testing:

- `http://localhost:8000/index.html`
- `http://localhost:8000/savings.html`
- `http://localhost:8000/loans.html`

Start the server:

```bash
cd web
jwebserver
```

## Project structure

```text
PlaywrightJava/
├── src/
│   ├── main/java/org/example/     # shared support classes
│   └── test/java/                # test examples by topic
│       ├── Actions/
│       ├── Advanced/
│       ├── Locators/
│       ├── Networking/
│       ├── PwConfig/
│       ├── Refactor/
│       ├── Tools/
│       ├── BrowserSupport.java
│       ├── FirstPwScript.java
│       └── ProjectSetupTest.java
├── web/                          # sample static pages
├── build.gradle.kts
├── settings.gradle.kts
├── gradlew
├── gradlew.bat
├── state.json
├── .gitignore
└── README.md
```

## Tech stack

- Java
- Gradle
- JUnit 5
- Playwright Java

This project is mainly educational and demo-oriented. It focuses on practical Playwright usage rather than a formal Page Object Model structure.
