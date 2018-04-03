package com.valkryst.VDice;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class DiceRoller {
    /** The random instance to use when rolling. */
    private final static Random RANDOM = new SecureRandom();
    /** The lock that controls access to the rolls counter. */
    private final static ReentrantReadWriteLock RESEED_LOCK = new ReentrantReadWriteLock();
    /** The number of die rolls since the last reseed of the random instance. */
    private static AtomicInteger ROLLS_SINCE_RESEED = new AtomicInteger(0);

    /** The dice. */
    private final List<Die> dice = new ArrayList<>();

    /**
     * Rolls each of the dice and sums the result.
     *
     * @return
     *        The summed result of all die rolls.
     */
    public int roll() {
        if (dice.size() == 0) {
            return 0;
        }

        RESEED_LOCK.readLock().lock();
        if (ROLLS_SINCE_RESEED.get() >= 100_000) {
            RESEED_LOCK.readLock().unlock();

            RANDOM.setSeed(System.currentTimeMillis());


            RESEED_LOCK.writeLock().lock();
            ROLLS_SINCE_RESEED.set(0);
            RESEED_LOCK.writeLock().unlock();
        } else {
            RESEED_LOCK.readLock().unlock();
        }

        int total = 0;

        for (final Die die : dice) {
            total += die.roll(RANDOM);

            RESEED_LOCK.writeLock().lock();
            ROLLS_SINCE_RESEED.getAndIncrement();
            RESEED_LOCK.writeLock().unlock();
        }

        return total;
    }

    /** Removes all dice from the roller. */
    public void reset() {
        dice.clear();
    }

    /**
     * Adds a die to the roller.
     *
     * @param sides
     *        The number of sides on the die to add.
     */
    public void addDie(final int sides) {
        dice.add(new Die(sides));
    }

    /**
     * Adds a die to the roller.
     *
     * @param die
     *        The die.
     */
    public void addDie(final Die die) {
        if (die != null) {
            dice.add(die);
        }
    }

    /**
     * Adds one or more dice to the roller.
     *
     * @param sides
     *        The number of sides on the dice to add.
     *
     * @param totalDice
     *        The number of dice to add.
     */
    public void addDice(final int sides, int totalDice) {
        while (totalDice > 0) {
            addDie(sides);
            totalDice--;
        }
    }

    /**
     * Removes the first occurrence of a die from the roller.
     *
     * @param die
     *        The die.
     */
    public void removeDie(final Die die) {
        if (die != null) {
            dice.remove(die);
        }
    }

    /**
     * Determines the minimum possible roll.
     *
     * @return
     *        The minimum possible roll.
     */
    public int getMinimumRoll() {
        int total = 0;

        for (final Die die : dice) {
            if (die.isNegative()) {
                total--;
            } else {
                total++;
            }
        }

        return total;
    }

    /**
     * Determines the maximum possible roll.
     *
     * @return
     *        The maximum possible roll.
     */
    public int getMaximumRoll() {
        int total = 0;

        for (final Die die : dice) {
            if (die.isNegative()) {
                total -= die.getSides();
            } else {
                total += die.getSides();
            }
        }

        return total;
    }
}
