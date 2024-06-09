package de.unistuttgart.iste.meitrex.scrumgame.service.gamification;

import de.unistuttgart.iste.meitrex.generated.dto.*;
import de.unistuttgart.iste.meitrex.scrumgame.service.auth.AuthService;
import de.unistuttgart.iste.meitrex.scrumgame.service.sprint.SprintService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class ShopService {

    private final SprintService    sprintService;
    private final UserStatsService userStatsService;
    private final AuthService      authService;

    public List<ShopItem> getShopItems() {
        return DefaultShopItems.SHOP_ITEMS.stream()
                .sorted(Comparator.comparing(ShopItem::getPrice))
                .toList();
    }

    @Transactional
    public PlacedAsset buyAndPlace(ProjectMutation projectMutation, PlaceAssetInput input) {
        ShopItem shopItem = getShopItemByKnownAsset(input.getAsset());

        userStatsService.updateUserStats(
                authService.getCurrentUserId(),
                projectMutation.getProject().getId(), userStatsEntity -> {
                    if (userStatsEntity.getVirtualCurrency() < shopItem.getPrice()) {
                        throw new IllegalArgumentException("Not enough virtual currency to buy this item");
                    }
                    userStatsEntity.setVirtualCurrency(userStatsEntity.getVirtualCurrency() - shopItem.getPrice());
                });

        return sprintService.placeAsset(projectMutation.getProject(), input);
    }

    public ShopItem getShopItemByKnownAsset(KnownAsset knownAsset) {
        return DefaultShopItems.SHOP_ITEMS.stream()
                .filter(shopItem -> shopItem.getImage() == knownAsset)
                .findFirst()
                .orElseThrow();
    }
}
