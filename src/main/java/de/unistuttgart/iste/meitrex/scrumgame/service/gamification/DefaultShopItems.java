package de.unistuttgart.iste.meitrex.scrumgame.service.gamification;

import de.unistuttgart.iste.meitrex.generated.dto.KnownAsset;
import de.unistuttgart.iste.meitrex.generated.dto.ShopItem;
import lombok.NoArgsConstructor;

import java.util.*;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class DefaultShopItems {

    public static final List<ShopItem> SHOP_ITEMS = List.of(
            ShopItem.builder()
                    .setImage(KnownAsset.BUSHES_1)
                    .setName("Fern")
                    .setPrice(50)
                    .setId(UUID.randomUUID())
                    .build(),
            ShopItem.builder()
                    .setImage(KnownAsset.BUSHES_2)
                    .setName("Fern")
                    .setPrice(50)
                    .setId(UUID.randomUUID())
                    .build(),
//            ShopItem.builder()
//                    .setImage(Asset.builder()
//                            .setKnownAsset(KnownAsset.BUSHES_3)
//                            .build())
//                    .setName("Bushes")
//                    .setPrice(100)
//                    .setId(UUID.randomUUID())
//                    .build(),
//            ShopItem.builder()
//                    .setImage(Asset.builder()
//                            .setKnownAsset(KnownAsset.ROCK_1)
//                            .build())
//                    .setName("Rock")
//                    .setPrice(100)
//                    .setId(UUID.randomUUID())
//                    .build(),
//            ShopItem.builder()
//                    .setImage(Asset.builder()
//                            .setKnownAsset(KnownAsset.ROCK_2)
//                            .build())
//                    .setName("Rock")
//                    .setPrice(100)
//                    .setId(UUID.randomUUID())
//                    .build(),
            ShopItem.builder()
                    .setImage(KnownAsset.FLOWERS)
                    .setName("Flowers")
                    .setPrice(100)
                    .setId(UUID.randomUUID())
                    .build(),
            ShopItem.builder()
                    .setImage(KnownAsset.TREES)
                    .setName("Trees")
                    .setPrice(150)
                    .setId(UUID.randomUUID())
                    .build(),
            ShopItem.builder()
                    .setImage(KnownAsset.WATER_PUDDLE)
                    .setName("Small Pond")
                    .setPrice(150)
                    .setId(UUID.randomUUID())
                    .build(),
            ShopItem.builder()
                    .setImage(KnownAsset.TREE_WITH_ROCKS)
                    .setName("Tree with Rocks")
                    .setPrice(300)
                    .setId(UUID.randomUUID())
                    .build(),
            ShopItem.builder()
                    .setImage(KnownAsset.FEEDING_TROUGH)
                    .setName("Feeding Trough")
                    .setPrice(150)
                    .setId(UUID.randomUUID())
                    .build(),
            ShopItem.builder()
                    .setImage(KnownAsset.CAVE)
                    .setName("Cave")
                    .setPrice(350)
                    .setId(UUID.randomUUID())
                    .build());
}
