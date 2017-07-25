package com.valkryst.VDice;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ThreadLocalRandom;

public class DiceRollerTest {
    private final DiceRoller roller = new DiceRoller();

    @Before
    public void initializeDiceRoller() {
        roller.reset();
    }

    @Test
    public void testRoll_forceReseed() {
        roller.addDie(1);

        for (int i = 0 ; i < 200_000 ; i++) {
            roller.roll();
        }
    }

    @Test
    public void testRoll_withNoDice() {
        Assert.assertEquals(0, roller.roll());
    }

    @Test
    public void testRoll_withOneDie() {
        roller.addDie(6);

        for (int i = 0 ; i < 1000 ; i++) {
            final int result = roller.roll();
            Assert.assertTrue(result >= 1);
            Assert.assertTrue(result <= 6);
        }
    }

    @Test
    public void testRoll_withTwoDie() {
        roller.addDie(3);
        roller.addDie(3);

        for (int i = 0 ; i < 1000 ; i++) {
            final int result = roller.roll();
            Assert.assertTrue(result >= 1);
            Assert.assertTrue(result <= 6);
        }
    }

    @Test
    public void testAddDie_intParam() {
        final int sides = ThreadLocalRandom.current().nextInt(Integer.MAX_VALUE);
        roller.addDie(sides);
        Assert.assertEquals(roller.getMinimumRoll(), 1);
    }

    @Test
    public void testAddDie_objectParam_withValidInput() {
        final Die die = new Die(6);
        roller.addDie(die);
        Assert.assertEquals(roller.getMinimumRoll(), 1);
    }

    @Test(expected=NullPointerException.class)
    public void testAddDie_objectParam_withNullDie() {
        roller.addDie(null);
    }

    @Test
    public void testAddDie_twoParams_withIsNegativeSetToTrue() {
        final int sides = ThreadLocalRandom.current().nextInt(Integer.MAX_VALUE);
        roller.addDie(-sides);
        Assert.assertEquals(roller.getMinimumRoll(), -1);
    }

    @Test
    public void testAddDie_twoParams_withIsNegativeSetToFalse() {
        final int sides = ThreadLocalRandom.current().nextInt(Integer.MAX_VALUE);
        roller.addDie(sides);
        Assert.assertEquals(roller.getMinimumRoll(), 1);
    }

    @Test
    public void testAddDice_twoParams() {
        final int sides = ThreadLocalRandom.current().nextInt(Integer.MAX_VALUE);
        roller.addDice(sides, 1000);
        Assert.assertEquals(roller.getMinimumRoll(), 1000);
    }

    @Test
    public void testRemoveDie_withValidInput_withOneInstanceInList() {
        final Die die = new Die(6);

        roller.addDie(die);
        Assert.assertEquals(roller.getMinimumRoll(), 1);

        roller.removeDie(die);
        Assert.assertEquals(roller.getMinimumRoll(), 0);
    }

    @Test
    public void testRemoveDie_withValidInput_withTwoInstancesInList() {
        final Die die = new Die(6);

        roller.addDie(die);
        roller.addDie(die);
        Assert.assertEquals(roller.getMinimumRoll(), 2);

        roller.removeDie(die);
        Assert.assertEquals(roller.getMinimumRoll(), 1);

        roller.removeDie(die);
        Assert.assertEquals(roller.getMinimumRoll(), 0);
    }

    @Test(expected=NullPointerException.class)
    public void testRemoveDie_withNullDie() {
        roller.removeDie(null);
    }

    @Test
    public void testGetMinimumRoll_withOnlyPositiveDice() {
        roller.addDice(1, 1000);
        Assert.assertEquals(roller.getMinimumRoll(), 1000);
    }

    @Test
    public void testGetMinimumRoll_withOnlyNegativeDice() {
        roller.addDice(-1, 1000);
        Assert.assertEquals(roller.getMinimumRoll(), -1000);
    }

    @Test
    public void testGetMinimumRoll_withMixedDice() {
        roller.addDice(-1, 1000);
        roller.addDice(1, 500);
        Assert.assertEquals(roller.getMinimumRoll(), -500);
    }

    @Test
    public void testGetMaximumRoll_withOnlyPositiveDice() {
        roller.addDice(2, 1000);
        Assert.assertEquals(roller.getMaximumRoll(), 2000);
    }

    @Test
    public void testGetMaximumRoll_withOnlyNegativeDice() {
        roller.addDice(-2, 1000);
        Assert.assertEquals(roller.getMaximumRoll(), -2000);
    }

    @Test
    public void testGetMaximumRoll_withMixedDice() {
        roller.addDice(-2, 1000);
        roller.addDice(1, 500);
        Assert.assertEquals(roller.getMaximumRoll(), -1500);
    }
}
