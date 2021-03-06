![](https://codebuild.us-east-1.amazonaws.com/badges?uuid=eyJlbmNyeXB0ZWREYXRhIjoiK0RwTG80S3pER1Y1d0J0T25aV2txeEM5djdVbUl1UmdtU3JWN0c0QUYvTHlFUW5yL2FSQXo2bVVvQnpVbjNoSlZDU2h1c1NkWnpVNHVQQkZjaFNiUHFRPSIsIml2UGFyYW1ldGVyU3BlYyI6ImY2WVhDeStnT2UyNlJLTmwiLCJtYXRlcmlhbFNldFNlcmlhbCI6MX0%3D&branch=master) [![codecov](https://codecov.io/gh/Valkryst/VDice/branch/master/graph/badge.svg)](https://codecov.io/gh/Valkryst/VDice) [![Release](https://jitpack.io/v/Valkryst/VDice.svg)](https://jitpack.io/#Valkryst/VDice)
[![Total alerts](https://img.shields.io/lgtm/alerts/g/Valkryst/VDice.svg?logo=lgtm&logoWidth=18)](https://lgtm.com/projects/g/Valkryst/VDice/alerts/) [![Language grade: Java](https://img.shields.io/lgtm/grade/java/g/Valkryst/VDice.svg?logo=lgtm&logoWidth=18)](https://lgtm.com/projects/g/Valkryst/VDice/context:java)

# VDice

A library to generate pseudorandom dice rolls.

Supports any combination of dice as well as both negative and positive roll results.

# Jar Files & Maven

The Maven dependency is hosted off of JitPack, so you will need to add JitPack as a repository before you add VTerminal as a dependency.

### Maven

JitPack ([Example](https://github.com/Valkryst/VTerminal/blob/master/pom.xml)):

    <repositories>
        <repository>
            <id>jitpack.io</id>
            <url>https://jitpack.io</url>
        </repository>
    </repositories>

Dependency:

    <dependency>
        <groupId>com.github.Valkryst</groupId>
        <artifactId>VDice</artifactId>
        <version>1.1.2</version>
    </dependency>

### Jar

Jar files can be found on the [releases](https://github.com/Valkryst/VDice/releases) page.

## Examples
Add a 1d6 and a 1d20 to a roller, then roll the dice and output the result.

```java
final DiceRoller roller = new DiceRoller();
roller.add(6);
roller.add(20);

System.out.println(roller.roll());
```

Add 2d6 to a roller, then roll the dice and output the result.

```java
final DiceRoller roller = new DiceRoller();
roller.add(6, 2);

System.out.println(roller.roll());
```

Add a negative 1d6 and a positive 1d20 to a roller, then roll the dice and output the result.

```java
final DiceRoller roller = new DiceRoller();
roller.add(-6);
roller.add(20);

System.out.println(roller.roll());
```

## JavaDoc Documentation

Whenever a Travis CI build passes, the JavaDocs are auto-generated and made available at the following link.

https://valkryst.github.io/VDice/
