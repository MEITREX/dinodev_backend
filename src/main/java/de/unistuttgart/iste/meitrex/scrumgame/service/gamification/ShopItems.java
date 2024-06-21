package de.unistuttgart.iste.meitrex.scrumgame.service.gamification;

import de.unistuttgart.iste.meitrex.common.util.MeitrexCollectionUtils;
import de.unistuttgart.iste.meitrex.generated.dto.KnownAsset;
import de.unistuttgart.iste.meitrex.generated.dto.ShopItem;
import lombok.NoArgsConstructor;

import java.util.*;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class ShopItems {

    public static final List<ShopItem> DEFAULT_SHOP_ITEMS = List.of(
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

    public static final List<ShopItem> ALL_SHOP_ITEMS = MeitrexCollectionUtils.concat(
            DEFAULT_SHOP_ITEMS,
            ShopItem.builder()
                    .setImage(KnownAsset.ROCK_1)
                    .setName("Rock")
                    .setPrice(100)
                    .setId(UUID.nameUUIDFromBytes("ROCK_1".getBytes()))
                    .build(),
            ShopItem.builder()
                    .setImage(KnownAsset.CAVE_2)
                    .setName("Cave")
                    .setPrice(400)
                    .setId(UUID.nameUUIDFromBytes("CAVE_2".getBytes()))
                    .build(),
            ShopItem.builder()
                    .setImage(KnownAsset.FOUNTAIN)
                    .setName("Fountain")
                    .setPrice(300)
                    .setId(UUID.nameUUIDFromBytes("FOUNTAIN".getBytes()))
                    .build()

    );
}
