package de.unistuttgart.iste.meitrex.scrumgame.util;

import de.unistuttgart.iste.meitrex.generated.dto.TShirtSizeEstimation;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class TShirtSizeEstimationStoryPointsConverter {

    public static int getStoryPoints(TShirtSizeEstimation estimation) {
        return switch (estimation) {
            case XS -> 1;
            case S -> 2;
            case M -> 3;
            case L -> 5;
            case XL -> 8;
        };
    }

    public static TShirtSizeEstimation getTShirtSizeEstimation(int storyPoints) {
        return switch (storyPoints) {
            case 1 -> TShirtSizeEstimation.XS;
            case 2 -> TShirtSizeEstimation.S;
            case 3 -> TShirtSizeEstimation.M;
            case 5 -> TShirtSizeEstimation.L;
            case 8 -> TShirtSizeEstimation.XL;
            default -> throw new IllegalArgumentException("Invalid story points value: " + storyPoints);
        };
    }

}
