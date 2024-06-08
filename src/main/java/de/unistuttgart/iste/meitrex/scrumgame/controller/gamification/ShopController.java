package de.unistuttgart.iste.meitrex.scrumgame.controller.gamification;

import de.unistuttgart.iste.meitrex.generated.dto.PlaceAssetInput;
import de.unistuttgart.iste.meitrex.generated.dto.PlacedAsset;
import de.unistuttgart.iste.meitrex.generated.dto.ProjectMutation;
import de.unistuttgart.iste.meitrex.generated.dto.ShopItem;
import de.unistuttgart.iste.meitrex.scrumgame.service.gamification.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.*;

@Controller
@RequiredArgsConstructor
public class ShopController {

    private final ShopService shopService;

    @QueryMapping
    public List<ShopItem> shopItems() {
        return shopService.getShopItems();
    }

    @SchemaMapping
    public PlacedAsset buyAndPlace(ProjectMutation projectMutation, @Argument PlaceAssetInput input) {
        return shopService.buyAndPlace(projectMutation, input);
    }
}
