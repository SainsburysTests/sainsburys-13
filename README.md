# Technical Test

## How to run

1. Build: mvn clean compile assembly:single
2. java -jar target/sainsburys-0.0.1-SNAPSHOT-jar-with-dependencies.jar

## How to Test

mvn test

## Dependencies

The system requires Firefox to be installed as it is used by Selenium.

* [Selenium](http://www.seleniumhq.org/) used to render page by automating the browser
* [GSon](https://github.com/google/gson) used to render the output as JSON

