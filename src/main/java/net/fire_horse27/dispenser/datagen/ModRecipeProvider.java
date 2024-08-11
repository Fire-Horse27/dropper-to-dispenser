package net.fire_horse27.dispenser.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {

    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        ShapelessRecipeJsonBuilder.create(RecipeCategory.REDSTONE, Items.DISPENSER)
                .input(Items.DROPPER)
                .input(Items.BOW)
                .group("dispenser")
                .criterion(hasItem(Items.DROPPER), conditionsFromItem(Items.DROPPER))
                .criterion(hasItem(Items.BOW), conditionsFromItem(Items.BOW))
                .offerTo(exporter, "dispenser:dispenser_dropper");
        ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, Items.DISPENSER)
                .pattern(" WS")
                .pattern("WDS")
                .pattern(" WS")
                .input('S', Items.STRING)
                .input('W', Items.STICK)
                .input('D', Items.DROPPER)
                .criterion(hasItem(Items.DROPPER), conditionsFromItem(Items.DROPPER))
                .group("dispenser")
                .offerTo(exporter, "dispenser:dispenser_stackable");
        ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, Items.DISPENSER)
                .pattern("SSS")
                .pattern("SBS")
                .pattern("SRS")
                .input('S', Items.COBBLESTONE)
                .input('B', Items.BOW)
                .input('R', Items.REDSTONE)
                .criterion(hasItem(Items.DROPPER), conditionsFromItem(Items.DROPPER))
                .group("dispenser")
                .offerTo(exporter);

        /*offerSmelting(exporter, Blocks.RAW_COPPER_BLOCK, RecipeCategory.BUILDING_BLOCKS, Blocks.COPPER_BLOCK,
                        0.7f, 200, "iron_block");*/
    }
}
