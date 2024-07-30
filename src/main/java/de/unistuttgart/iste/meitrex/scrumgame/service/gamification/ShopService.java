package de.unistuttgart.iste.meitrex.scrumgame.service.gamification;

import de.unistuttgart.iste.meitrex.generated.dto.*;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.project.ProjectEntity;
import de.unistuttgart.iste.meitrex.scrumgame.service.auth.AuthService;
import de.unistuttgart.iste.meitrex.scrumgame.service.project.ProjectService;
import de.unistuttgart.iste.meitrex.scrumgame.service.sprint.SprintService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class ShopService {

    private final SprintService    sprintService;
    private final UserStatsService userStatsService;
    private final AuthService      authService;
    private final ProjectService projectService;

    public List<ShopItem> getShopItems(Project project) {
        ProjectEntity projectEntity = projectService.getProjectEntity(project.getId());

        var unlockedShopItems = projectEntity.getAdditionalUnlockedAssets()
                .stream()
                .map(ShopItems::getShopItemByKnownAsset);
        var defaultShopItems = ShopItems.DEFAULT_SHOP_ITEMS.stream();

        return Stream.concat(unlockedShopItems, defaultShopItems)
                .sorted(Comparator.comparing(ShopItem::getPrice))
                .collect(Collectors.toList());
    }

    @Transactional
    public PlacedAsset buyAndPlace(ProjectMutation projectMutation, PlaceAssetInput input) {
        KnownAsset knownAsset = input.getAsset();
        ShopItem shopItem = ShopItems.getShopItemByKnownAsset(knownAsset);

        userStatsService.updateUserStats(
                authService.getCurrentUserId(),
                projectMutation.getProject().getId(), userStatsEntity -> {
                    if (userStatsEntity.getVirtualCurrency() < shopItem.getPrice()) {
                        throw new IllegalArgumentException("Not enough virtual currency to buy this item");
                    }
                    userStatsEntity.setVirtualCurrency(userStatsEntity.getVirtualCurrency() - shopItem.getPrice());
                });

        return sprintService.placeAsset(projectMutation.getProject(), input, authService.getCurrentUserId());
    }

}
