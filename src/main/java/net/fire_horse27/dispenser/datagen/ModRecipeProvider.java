package net.fire_horse27.dispenser.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {

    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup registries, RecipeExporter exporter) {
        return new RecipeGenerator(registries, exporter) {
            @Override
            public void generate() {
                createShapeless(RecipeCategory.REDSTONE, Items.DISPENSER)
                        .input(Items.DROPPER)
                        .input(Items.BOW)
                        .group("dispenser")
                        .criterion(hasItem(Items.DROPPER), conditionsFromItem(Items.DROPPER))
                        .criterion(hasItem(Items.BOW), conditionsFromItem(Items.BOW))
                        .offerTo(exporter, "dispenser:dispenser_dropper");
                createShaped(RecipeCategory.REDSTONE, Items.DISPENSER)
                        .pattern(" WS")
                        .pattern("WDS")
                        .pattern(" WS")
                        .input('S', Items.STRING)
                        .input('W', Items.STICK)
                        .input('D', Items.DROPPER)
                        .criterion(hasItem(Items.DROPPER), conditionsFromItem(Items.DROPPER))
                        .group("dispenser")
                        .offerTo(exporter, "dispenser:dispenser_stackable");
                createShaped(RecipeCategory.REDSTONE, Items.DISPENSER)
                        .pattern("SSS")
                        .pattern("SBS")
                        .pattern("SRS")
                        .input('S', Items.COBBLESTONE)
                        .input('B', Items.BOW)
                        .input('R', Items.REDSTONE)
                        .criterion(hasItem(Items.DROPPER), conditionsFromItem(Items.DROPPER))
                        .group("dispenser")
                        .offerTo(exporter);
            }
        };
    }

    @Override
    public String getName() {
        return "ModRecipeProvider";
    }
}
