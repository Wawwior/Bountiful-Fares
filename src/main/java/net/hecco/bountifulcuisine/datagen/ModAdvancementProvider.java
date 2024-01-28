package net.hecco.bountifulcuisine.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.hecco.bountifulcuisine.BountifulCuisine;
import net.hecco.bountifulcuisine.block.ModBlocks;
import net.hecco.bountifulcuisine.effect.ModEffects;
import net.hecco.bountifulcuisine.entity.ModEntities;
import net.hecco.bountifulcuisine.item.ModItems;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementDisplay;
import net.minecraft.advancement.AdvancementFrame;
import net.minecraft.advancement.criterion.*;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.condition.LootConditionConsumingBuilder;
import net.minecraft.loot.condition.LootConditionType;
import net.minecraft.loot.condition.LootConditionTypes;
import net.minecraft.predicate.BlockPredicate;
import net.minecraft.predicate.entity.*;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.stat.Stat;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.function.Consumer;

public class ModAdvancementProvider extends FabricAdvancementProvider {
    public ModAdvancementProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateAdvancement(Consumer<Advancement> consumer) {
        Advancement root_advancement = Advancement.Builder.create()
                .display(new AdvancementDisplay(new ItemStack(ModItems.PASSION_FRUIT),
                        Text.translatable("advancement.bountifulcuisine.bountiful_cuisine"),
                        Text.translatable("advancement.bountifulcuisine.bountiful_cuisine.description"), new Identifier("minecraft:textures/block/farmland_moist.png"), AdvancementFrame.TASK,
                        false,
                        false,
                        false)).criterion("consume_item", ConsumeItemCriterion.Conditions.any())
                .build(consumer, BountifulCuisine.MOD_ID + ":bountiful_cuisine");
        Advancement place_gristmill = Advancement.Builder.create()
                .display(new AdvancementDisplay(new ItemStack(ModBlocks.GRISTMILL),
                        Text.translatable("advancement.bountifulcuisine.place_gristmill"),
                        Text.translatable("advancement.bountifulcuisine.place_gristmill.description"), new Identifier("minecraft:textures/block/farmland_moist.png"), AdvancementFrame.TASK,
                        true,
                        true,
                        false))
                .parent(root_advancement)
                .criterion("place_gristmill", ItemCriterion.Conditions.createPlacedBlock(ModBlocks.GRISTMILL))
                .build(consumer, BountifulCuisine.MOD_ID + ":place_gristmill");

        Advancement obtain_feldspar = Advancement.Builder.create()
                .display(new AdvancementDisplay(new ItemStack(ModItems.FELDSPAR),
                        Text.translatable("advancement.bountifulcuisine.obtain_feldspar"),
                        Text.translatable("advancement.bountifulcuisine.obtain_feldspar.description"), new Identifier("minecraft:textures/block/farmland_moist.png"), AdvancementFrame.TASK,
                        true,
                        true,
                        false))
                .parent(place_gristmill)
                .criterion("obtain_feldspar", InventoryChangedCriterion.Conditions.items(ModItems.FELDSPAR))
                .build(consumer, BountifulCuisine.MOD_ID + ":obtain_feldspar");

        Advancement obtain_ceramic_tiles = Advancement.Builder.create()
                .display(new AdvancementDisplay(new ItemStack(ModBlocks.CERAMIC_TILES),
                        Text.translatable("advancement.bountifulcuisine.obtain_ceramic_tiles"),
                        Text.translatable("advancement.bountifulcuisine.obtain_ceramic_tiles.description"), new Identifier("minecraft:textures/block/farmland_moist.png"), AdvancementFrame.TASK,
                        true,
                        true,
                        false))
                .parent(obtain_feldspar)
                .criterion("obtain_ceramic_tiles", InventoryChangedCriterion.Conditions.items(ModBlocks.CERAMIC_TILES))
                .build(consumer, BountifulCuisine.MOD_ID + ":obtain_ceramic_tiles");
        Advancement obtain_fermentation_vessel = Advancement.Builder.create()
                .display(new AdvancementDisplay(new ItemStack(ModBlocks.OLD_FERMENTATION_VESSEL),
                        Text.translatable("advancement.bountifulcuisine.obtain_fermentation_vessel"),
                        Text.translatable("advancement.bountifulcuisine.obtain_fermentation_vessel.description"), new Identifier("minecraft:textures/block/farmland_moist.png"), AdvancementFrame.TASK,
                        true,
                        true,
                        false))
                .parent(obtain_feldspar)
                .criterion("obtain_fermentation_vessel", InventoryChangedCriterion.Conditions.items(ModBlocks.OLD_FERMENTATION_VESSEL))
                .build(consumer, BountifulCuisine.MOD_ID + ":obtain_fermentation_vessel");
        Advancement eat_ancient_fruit = Advancement.Builder.create()
                .display(new AdvancementDisplay(new ItemStack(ModItems.HOARY_APPLE),
                        Text.translatable("advancement.bountifulcuisine.eat_ancient_fruit"),
                        Text.translatable("advancement.bountifulcuisine.eat_ancient_fruit.description"), new Identifier("minecraft:textures/block/farmland_moist.png"), AdvancementFrame.TASK,
                        true,
                        true,
                        false))
                .parent(root_advancement)
                .criterion("eat_ancient_fruit", ConsumeItemCriterion.Conditions.predicate(ItemPredicate.Builder.create().items(ModItems.HOARY_APPLE, ModItems.LAPISBERRIES).build()))
                .build(consumer, BountifulCuisine.MOD_ID + ":eat_ancient_fruit");
        Advancement place_all_baked_goods = Advancement.Builder.create()
                .display(new AdvancementDisplay(new ItemStack(ModBlocks.ARTISAN_BREAD),
                        Text.translatable("advancement.bountifulcuisine.place_all_baked_goods"),
                        Text.translatable("advancement.bountifulcuisine.place_all_baked_goods.description"), new Identifier("minecraft:textures/block/farmland_moist.png"), AdvancementFrame.CHALLENGE,
                        true,
                        true,
                        false))
                .parent(root_advancement)
                .criterion("cake", ItemCriterion.Conditions.createPlacedBlock(Blocks.CAKE))
                .criterion("cocoa_cake", ItemCriterion.Conditions.createPlacedBlock(ModBlocks.COCOA_CAKE))
                .criterion("artisan_bread", ItemCriterion.Conditions.createPlacedBlock(ModBlocks.ARTISAN_BREAD))
                .criterion("artisan_cookie", ItemCriterion.Conditions.createPlacedBlock(ModBlocks.ARTISAN_COOKIES))
                .criterion("apple_pie", ItemCriterion.Conditions.createPlacedBlock(ModBlocks.APPLE_PIE))
                .criterion("orange_pie", ItemCriterion.Conditions.createPlacedBlock(ModBlocks.ORANGE_PIE))
                .criterion("lemon_pie", ItemCriterion.Conditions.createPlacedBlock(ModBlocks.LEMON_PIE))
                .criterion("plum_pie", ItemCriterion.Conditions.createPlacedBlock(ModBlocks.PLUM_PIE))
                .criterion("hoary_pie", ItemCriterion.Conditions.createPlacedBlock(ModBlocks.HOARY_PIE))
                .criterion("passion_fruit_tart", ItemCriterion.Conditions.createPlacedBlock(ModBlocks.PASSION_FRUIT_TART))
                .criterion("elderberry_tart", ItemCriterion.Conditions.createPlacedBlock(ModBlocks.ELDERBERRY_TART))
                .criterion("glow_berry_tart", ItemCriterion.Conditions.createPlacedBlock(ModBlocks.GLOW_BERRY_TART))
                .criterion("sweet_berry_tart", ItemCriterion.Conditions.createPlacedBlock(ModBlocks.SWEET_BERRY_TART))
                .criterion("lapisberry_tart", ItemCriterion.Conditions.createPlacedBlock(ModBlocks.LAPISBERRY_TART))
                .build(consumer, BountifulCuisine.MOD_ID + ":place_all_baked_goods");
        Advancement eat_citric_acid = Advancement.Builder.create()
                .display(new AdvancementDisplay(new ItemStack(ModItems.CITRIC_ACID),
                        Text.translatable("advancement.bountifulcuisine.eat_citric_acid"),
                        Text.translatable("advancement.bountifulcuisine.eat_citric_acid.description"), new Identifier("minecraft:textures/block/farmland_moist.png"), AdvancementFrame.TASK,
                        true,
                        true,
                        false
                ))
                .parent(obtain_fermentation_vessel)
                .criterion("eat_citric_acid", ConsumeItemCriterion.Conditions.item(ModItems.CITRIC_ACID))
                .build(consumer, BountifulCuisine.MOD_ID + ":eat_citric_acid");
//        Advancement throw_flour = Advancement.Builder.create()
//                .display(new AdvancementDisplay(new ItemStack(ModItems.FLOUR),
//                        Text.translatable("advancement.bountifulcuisine.throw_flour"),
//                        Text.translatable("advancement.bountifulcuisine.throw_flour.description"), new Identifier("minecraft:textures/block/farmland_moist.png"), AdvancementFrame.TASK,
//                        true,
//                        true,
//                        false))
//                .parent(place_gristmill)
////                .criterion("throw_flour", ItemCriterion.Conditions.createItemUsedOnBlock(LocationPredicate.Builder.create().block(BlockPredicate.ANY), ItemPredicate.Builder.create().items(ModItems.FLOUR)))
////                .criterion("throw_flour", SummonedEntityCriterion.Conditions.create(EntityPredicate.Builder.create().type(ModEntities.THROWN_FLOUR_PROJECTILE)))
//                .build(consumer, BountifulCuisine.MOD_ID + ":throw_flour");
        Advancement obtain_sun_hat = Advancement.Builder.create()
                .display(new AdvancementDisplay(new ItemStack(ModItems.SUN_HAT),
                        Text.translatable("advancement.bountifulcuisine.obtain_sun_hat"),
                        Text.translatable("advancement.bountifulcuisine.obtain_sun_hat.description"), new Identifier("minecraft:textures/block/farmland_moist.png"), AdvancementFrame.TASK,
                        true,
                        true,
                        false))
                .parent(root_advancement)
                .criterion("obtain_sun_hat", InventoryChangedCriterion.Conditions.items(ModItems.SUN_HAT))
                .build(consumer, BountifulCuisine.MOD_ID + ":obtain_sun_hat");
        Advancement eat_all_candy = Advancement.Builder.create()
                .display(new AdvancementDisplay(new ItemStack(ModItems.CANDY),
                        Text.translatable("advancement.bountifulcuisine.eat_all_candy"),
                        Text.translatable("advancement.bountifulcuisine.eat_all_candy.description"), new Identifier("minecraft:textures/block/farmland_moist.png"), AdvancementFrame.TASK,
                        true,
                        true,
                        false))
                .parent(root_advancement)
                .criterion("candy", ConsumeItemCriterion.Conditions.item(ModItems.CANDY))
                .criterion("piquant", ConsumeItemCriterion.Conditions.item(ModItems.PIQUANT_CANDY))
                .criterion("sour", ConsumeItemCriterion.Conditions.item(ModItems.SOUR_CANDY))
                .criterion("bitter", ConsumeItemCriterion.Conditions.item(ModItems.BITTER_CANDY))
                .build(consumer, BountifulCuisine.MOD_ID + ":eat_all_candy");
        Advancement gorge = Advancement.Builder.create()
                .display(new AdvancementDisplay(new ItemStack(ModItems.SOUR_CANDY),
                        Text.translatable("advancement.bountifulcuisine.gorge"),
                        Text.translatable("advancement.bountifulcuisine.gorge.description"), new Identifier("minecraft:textures/block/farmland_moist.png"), AdvancementFrame.TASK,
                        true,
                        true,
                        false))
                .parent(eat_all_candy)
                .criterion("gorge", EffectsChangedCriterion.Conditions.create(EntityEffectPredicate.create().withEffect(ModEffects.GORGING)))
                .build(consumer, BountifulCuisine.MOD_ID + ":gorge");
        Advancement obtain_tea_blends = Advancement.Builder.create()
                .display(new AdvancementDisplay(new ItemStack(ModItems.TEA_LEAVES),
                        Text.translatable("advancement.bountifulcuisine.obtain_tea_blends"),
                        Text.translatable("advancement.bountifulcuisine.obtain_tea_blends.description"), new Identifier("minecraft:textures/block/farmland_moist.png"), AdvancementFrame.CHALLENGE,
                        true,
                        true,
                        false))
                .parent(place_gristmill)
                .criterion("green", InventoryChangedCriterion.Conditions.items(ModItems.GREEN_TEA_BLEND))
                .criterion("black", InventoryChangedCriterion.Conditions.items(ModItems.BLACK_TEA_BLEND))
                .criterion("chamomile", InventoryChangedCriterion.Conditions.items(ModItems.CHAMOMILE_TEA_BLEND))
                .criterion("honeysuckle", InventoryChangedCriterion.Conditions.items(ModItems.HONEYSUCKLE_TEA_BLEND))
                .criterion("bellflower", InventoryChangedCriterion.Conditions.items(ModItems.BELLFLOWER_TEA_BLEND))
                .criterion("torchflower", InventoryChangedCriterion.Conditions.items(ModItems.TORCHFLOWER_TEA_BLEND))
                .build(consumer, BountifulCuisine.MOD_ID + ":obtain_tea_blends");
        Advancement place_all_tea_candles = Advancement.Builder.create()
                .display(new AdvancementDisplay(new ItemStack(ModBlocks.GREEN_TEA_CANDLE),
                        Text.translatable("advancement.bountifulcuisine.place_all_tea_candles"),
                        Text.translatable("advancement.bountifulcuisine.place_all_tea_candles.description"), new Identifier("minecraft:textures/block/farmland_moist.png"), AdvancementFrame.CHALLENGE,
                        true,
                        true,
                        false))
                .parent(obtain_tea_blends)
                .criterion("green", ItemCriterion.Conditions.createPlacedBlock(ModBlocks.GREEN_TEA_CANDLE))
                .criterion("black", ItemCriterion.Conditions.createPlacedBlock(ModBlocks.BLACK_TEA_CANDLE))
                .criterion("chamomile", ItemCriterion.Conditions.createPlacedBlock(ModBlocks.CHAMOMILE_CANDLE))
                .criterion("honeysuckle", ItemCriterion.Conditions.createPlacedBlock(ModBlocks.HONEYSUCKLE_CANDLE))
                .criterion("bellflower", ItemCriterion.Conditions.createPlacedBlock(ModBlocks.BELLFLOWER_CANDLE))
                .criterion("torchflower", ItemCriterion.Conditions.createPlacedBlock(ModBlocks.TORCHFLOWER_CANDLE))
                .criterion("walnut", ItemCriterion.Conditions.createPlacedBlock(ModBlocks.WALNUT_CANDLE))
                .build(consumer, BountifulCuisine.MOD_ID + ":place_all_tea_candles");
        Advancement obtain_walnut = Advancement.Builder.create()
                .display(new AdvancementDisplay(new ItemStack(ModItems.WALNUT),
                        Text.translatable("advancement.bountifulcuisine.obtain_walnut"),
                        Text.translatable("advancement.bountifulcuisine.obtain_walnut.description"), new Identifier("minecraft:textures/block/farmland_moist.png"), AdvancementFrame.TASK,
                        true,
                        true,
                        false))
                .parent(root_advancement)
                .criterion("obtain_walnut", InventoryChangedCriterion.Conditions.items(ModItems.WALNUT))
                .build(consumer, BountifulCuisine.MOD_ID + ":obtain_walnut");
        Advancement obtain_spongekin_seeds = Advancement.Builder.create()
                .display(new AdvancementDisplay(new ItemStack(ModItems.SPONGEKIN_SEEDS),
                        Text.translatable("advancement.bountifulcuisine.obtain_spongekin_seeds"),
                        Text.translatable("advancement.bountifulcuisine.obtain_spongekin_seeds.description"), new Identifier("minecraft:textures/block/farmland_moist.png"), AdvancementFrame.TASK,
                        true,
                        true,
                        false))
                .parent(root_advancement)
                .criterion("obtain_spongekin_seeds", InventoryChangedCriterion.Conditions.items(ModItems.SPONGEKIN_SEEDS))
                .build(consumer, BountifulCuisine.MOD_ID + ":obtain_spongekin_seeds");
        Advancement obtain_spongekin = Advancement.Builder.create()
                .display(new AdvancementDisplay(new ItemStack(ModBlocks.SPONGEKIN),
                        Text.translatable("advancement.bountifulcuisine.obtain_spongekin"),
                        Text.translatable("advancement.bountifulcuisine.obtain_spongekin.description"), new Identifier("minecraft:textures/block/farmland_moist.png"), AdvancementFrame.TASK,
                        true,
                        true,
                        false))
                .parent(obtain_spongekin_seeds)
                .criterion("obtain_spongekin", InventoryChangedCriterion.Conditions.items(ModBlocks.SPONGEKIN, ModItems.SPONGEKIN_SLICE))
                .build(consumer, BountifulCuisine.MOD_ID + ":obtain_spongekin");
//        Advancement breedWolvesWithMulch = Advancement.Builder.create()
//                .display(new AdvancementDisplay(new ItemStack(ModBlocks.WALNUT_MULCH),
//                        Text.translatable("advancement.bountifulcuisine.breed_wolves_with_mulch"),
//                        Text.translatable("advancement.bountifulcuisine.breed_wolves_with_mulch.description"), new Identifier("minecraft:textures/block/farmland_moist.png"), AdvancementFrame.CHALLENGE,
//                        true,
//                        true,
//                        false))
//                .parent(obtain_feldspar)
//                .criterion("breed_wolves_with_mulch", PlayerInteractedWithEntityCriterion.Conditions.create(ItemPredicate.Builder.create().items(ModBlocks.WALNUT_MULCH), LootContextPredicate.create()))
//                .build(consumer, BountifulCuisine.MOD_ID + ":breed_wolves_with_mulch");
    }
}
//EntityPredicate.Builder.create().type(EntityType.WOLF