#### License

Use this code however you wish. Modifications and improvements are welcome.

Please refer to the LICENSE file for additional information.

---

[![MIT License](https://img.shields.io/badge/license-MIT_License-green.svg)](https://github.com/Valkryst/VDice/blob/master/LICENSE.md) ![](https://travis-ci.org/Valkryst/VDice.svg?branch=master) [![codecov](https://codecov.io/gh/Valkryst/VDice/branch/master/graph/badge.svg)](https://codecov.io/gh/Valkryst/VDice) [![Release](https://jitpack.io/v/Valkryst/VDice.svg)](https://jitpack.io/#Valkryst/VDice)

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/935164ad165841f9871665cde7975eaf)](https://www.codacy.com/app/Valkryst/VDice?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=Valkryst/VDice&amp;utm_campaign=Badge_Grade)

# VDice

A library to generate pseudorandom dice rolls.

Supports any combination of dice as well as both negative and positive roll results.

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