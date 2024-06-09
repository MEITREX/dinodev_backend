package de.unistuttgart.iste.meitrex.scrumgame.service.gamification.rules;

import lombok.NoArgsConstructor;

import java.util.*;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class VirtualCurrencyCalculator {

    @SuppressWarnings("java:S2245") // random is not used for security purposes
    private static final Random RANDOM = new Random();

    private static final int RANDOM_VIRTUAL_CURRENCY    = 10;
    private static final int BASE_VIRTUAL_CURRENCY      = 100;
    private static final int VIRTUAL_CURRENCY_PER_LEVEL = 10;

    public static int getVirtualCurrencyForLevelUp(int newLevel) {
        return BASE_VIRTUAL_CURRENCY
               + (newLevel - 1) * VIRTUAL_CURRENCY_PER_LEVEL
               + RANDOM.nextInt(RANDOM_VIRTUAL_CURRENCY);
    }
}
