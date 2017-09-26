package com.valkryst.VDice;

import lombok.Getter;
import lombok.NonNull;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Die {
    /** The number of sides on the die. This value is always positive. */
    @Getter private final int sides;

    /** Whether or not the result of the die is negative. */
    @Getter private final boolean isNegative;

    /**
     * Constructs a new Die.
     *
     * @param sides
     *        The number of sides on the die.
     */
    public Die(final int sides) {
        isNegative = sides < 0;
        this.sides = isNegative ? -sides : sides;
    }

    /**
     * Rolls the die.
     *
     * @return
     *        The result.
     */
    public int roll() {
        return roll(ThreadLocalRandom.current());
    }

    /**
     * Rolls the die.
     *
     * @param random
     *        The random instance to roll with.
     *
     * @return
     *        The result.
     *
     * @throws NullPointerException
     *         If random is null.
     */
    public int roll(final @NonNull Random random) {
        int result = random.nextInt(sides) + 1;
        return (isNegative ? -result : result);
    }
}
