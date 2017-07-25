#### License

Use this code however you wish. Modifications and improvements are welcome.

Please refer to the LICENSE file for additional information.

---

[![MIT License](https://img.shields.io/badge/license-MIT_License-green.svg)](https://github.com/Valkryst/VDice/blob/master/LICENSE.md) ![](https://travis-ci.org/Valkryst/VDice.svg?branch=master) [![codecov](https://codecov.io/gh/Valkryst/VDice/branch/master/graph/badge.svg)](https://codecov.io/gh/Valkryst/VDice) [![Release](https://jitpack.io/v/Valkryst/VDice.svg)](https://jitpack.io/#Valkryst/VDice)

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/935164ad165841f9871665cde7975eaf)](https://www.codacy.com/app/Valkryst/VDice?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=Valkryst/VDice&amp;utm_campaign=Badge_Grade)

## Examples
```java
// Add a 1d6 to the roller:
final DiceRoller roller = new DiceRoller();
roller.add(6);

// Output roll:
System.out.println(roller.roll());
```

```java
// Add a 1d6 and a 1d(-6) to the roller:
// Add a 1d6 to the roller:
final DiceRoller roller = new DiceRoller();
roller.add(6);
roller.add(-6);

// Output roll:
System.out.println(roller.roll());
```

## JavaDoc Documentation

Whenever a Travis CI build passes, the JavaDocs are auto-generated and made available at the following link.

https://valkryst.github.io/VDice/