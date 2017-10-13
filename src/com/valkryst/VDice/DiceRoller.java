package com.valkryst.VDice;

import lombok.NonNull;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class DiceRoller {
    private final static Random RANDOM = new SecureRandom();
    private final static ReentrantReadWriteLock RANDOM_LOCK = new ReentrantReadWriteLock();
    /** The number of die rolls since the last reseed of the random instance. */
    private static int ROLLS_SINCE_RESEED = 0;

    /** The dice. */
    private final List<Die> dice = new ArrayList<>();

    /**
     * Rolls each of the dice and sums the result.
     *
     * @return
     *        The summed result of all die rolls.
     */
    public int roll() {
        RANDOM_LOCK.readLock().lock();
        if (ROLLS_SINCE_RESEED >= 100_000) {
            RANDOM_LOCK.readLock().unlock();

            RANDOM.setSeed(System.currentTimeMillis());


            RANDOM_LOCK.writeLock().lock();
            ROLLS_SINCE_RESEED = 0;
            RANDOM_LOCK.writeLock().unlock();
        } else {
            RANDOM_LOCK.readLock().unlock();
        }

        int total = 0;

        for (final Die die : dice) {
            total += die.roll(RANDOM);

            RANDOM_LOCK.writeLock().lock();
            ROLLS_SINCE_RESEED++;
            RANDOM_LOCK.writeLock().unlock();
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
     *
     * @throws NullPointerException
     *        If the die is null.
     */
    public void addDie(final @NonNull Die die) {
        dice.add(die);
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
     *
     * @throws NullPointerException
     *        If the die is null.
     */
    public void removeDie(final @NonNull Die die) {
        dice.remove(die);
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
