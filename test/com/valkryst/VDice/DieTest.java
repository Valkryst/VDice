package com.valkryst.VDice;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.ThreadLocalRandom;

public class DieTest {
    @Test
    public void testConstructor_withIsNegativeSetToTrue() {
        final Die die = new Die(1, true);
        Assert.assertEquals(1, die.getSides());
        Assert.assertTrue(die.isNegative());
    }

    @Test
    public void testConstructor_withIsNegativeSetToFalse() {
        final Die die = new Die(1, false);
        Assert.assertEquals(1, die.getSides());
        Assert.assertFalse(die.isNegative());
    }

    @Test
    public void testConstructor_withPositiveSides() {
        final int sides = ThreadLocalRandom.current().nextInt(Integer.MAX_VALUE);
        final Die die = new Die(sides, false);

        Assert.assertEquals(sides, die.getSides());
        Assert.assertFalse(die.isNegative());
    }

    @Test(expected=IllegalArgumentException.class)
    public void testConstructor_withZeroSides() {
        new Die(0, false);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testConstructor_withNegativeSides() {
        int sides = ThreadLocalRandom.current().nextInt(Integer.MAX_VALUE);
        sides = -sides;

        new Die(sides, false);
    }

    @Test
    public void testRoll_withPositiveDie() {
        final Die die = new Die(6, false);

        for (int i = 0 ; i < 1000 ; i++) {
            final int result = die.roll(ThreadLocalRandom.current());

            Assert.assertTrue(result >= 1);
            Assert.assertTrue(result <= 6);
        }
    }

    @Test
    public void testRoll_withNegativeDie() {
        final Die die = new Die(6, true);

        for (int i = 0 ; i < 1000 ; i++) {
            final int result = die.roll(ThreadLocalRandom.current());

            Assert.assertTrue(result <= -1);
            Assert.assertTrue(result >= -6);
        }
    }
}
