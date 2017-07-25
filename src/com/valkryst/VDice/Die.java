package com.valkryst.VDice;

import lombok.Getter;

import java.util.Random;

public class Die {
    /** The number of sides on the die. */
    @Getter private final int sides;

    /** Whether or not the result of the die is negative. */
    @Getter private final boolean isNegative;

    /**
     * Constructs a new Die.
     *
     * @param sides
     *        The number of sides on the die.
     *
     * @param isNegative
     *        Whether or not the result of the die is negative.
     */
    public Die(final int sides, final boolean isNegative) {
        this.sides = sides;
        this.isNegative = isNegative;

        if (sides < 1) {
            throw new IllegalArgumentException("A die requires at-least one side.");
        }
    }

    /**
     * Rolls the die.
     *
     * @param random
     *        The random instance to roll with.
     *
     * @return
     *        The result.
     */
    public int roll(final Random random) {
        int result = random.nextInt(sides) + 1;
        return (isNegative ? -result : result);
    }
}
