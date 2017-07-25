package com.valkryst.VDice;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.ThreadLocalRandom;

public class DieTest {
    @Test
    public void testConstructor_withPositiveSides() {
        final int sides = ThreadLocalRandom.current().nextInt(Integer.MAX_VALUE);
        final Die die = new Die(sides);

        Assert.assertEquals(sides, die.getSides());
        Assert.assertFalse(die.isNegative());
    }

    @Test
    public void testConstructor_withZeroSides() {
        new Die(0);
    }

    @Test
    public void testConstructor_withNegativeSides() {
        int sides = ThreadLocalRandom.current().nextInt(Integer.MAX_VALUE);
        sides = -sides;

        final Die die = new Die(sides);

        Assert.assertEquals(-sides, die.getSides());
        Assert.assertFalse(die.isNegative());
    }

    @Test
    public void testRoll_withPositiveDie() {
        final Die die = new Die(6);

        for (int i = 0 ; i < 1000 ; i++) {
            final int result = die.roll(ThreadLocalRandom.current());

            Assert.assertTrue(result >= 1);
            Assert.assertTrue(result <= 6);
        }
    }

    @Test
    public void testRoll_withNegativeDie() {
        final Die die = new Die(-6);

        for (int i = 0 ; i < 1000 ; i++) {
            final int result = die.roll(ThreadLocalRandom.current());

            Assert.assertTrue(result <= -1);
            Assert.assertTrue(result >= -6);
        }
    }
}
