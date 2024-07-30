package de.unistuttgart.iste.meitrex.scrumgame.controller.gamification;

import de.unistuttgart.iste.meitrex.generated.dto.*;
import de.unistuttgart.iste.meitrex.scrumgame.service.gamification.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.*;

@Controller
@RequiredArgsConstructor
public class ShopController {

    private final ShopService shopService;

    @SchemaMapping
    public List<ShopItem> shopItems(Project project) {
        return shopService.getShopItems(project);
    }

    @SchemaMapping
    public PlacedAsset buyAndPlace(ProjectMutation projectMutation, @Argument PlaceAssetInput input) {
        return shopService.buyAndPlace(projectMutation, input);
    }
}
