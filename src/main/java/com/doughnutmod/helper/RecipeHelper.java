package com.doughnutmod.helper;


import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class RecipeHelper {

    public static final ResourceLocation EMPTY_GROUP = new ResourceLocation("", "");

    public static void addShapedRecipe(ItemStack output, Object... input) {
        addShapedRecipe(EMPTY_GROUP, output, input);
    }

    public static void addShapelessRecipe(ItemStack output, Object... input) {
        addShapelessRecipe(EMPTY_GROUP, output, input);
    }


    public static void addShapedRecipe(ResourceLocation group, ItemStack output, Object... input) {
        for (Object obj : input) {
            if (obj == null) {
                return;
            }
        }
        ForgeRegistries.RECIPES.register(new ShapedOreRecipe(group, output, input).setRegistryName(getRecipeLocation(output)));
    }

    public static void addShapelessRecipe(ResourceLocation group, ItemStack output, Object... input) {
        for (Object obj : input) {
            if (obj == null) {
                return;
            }
        }
        ForgeRegistries.RECIPES.register(new ShapelessOreRecipe(group, output, input).setRegistryName(getRecipeLocation(output)));
    }

    public static ResourceLocation getRecipeLocation(ItemStack output) {
        String namespace = Loader.instance().activeModContainer().getModId();
        ResourceLocation baseLoc = new ResourceLocation(namespace, output.getItem().getRegistryName().getResourcePath());
        ResourceLocation recipeLoc = baseLoc;
        int index = 0;

        while (CraftingManager.REGISTRY.containsKey(recipeLoc)) {
            index++;
            recipeLoc = new ResourceLocation(namespace, baseLoc.getResourcePath() + "_" + index);
        }

        return recipeLoc;
    }

    public static ResourceLocation getRecipeLocation(String name) {
        String namespace = Loader.instance().activeModContainer().getModId();
        ResourceLocation baseLoc = new ResourceLocation(namespace, name);
        ResourceLocation recipeLoc = baseLoc;
        int index = 0;

        while (CraftingManager.REGISTRY.containsKey(recipeLoc)) {
            index++;
            recipeLoc = new ResourceLocation(namespace, baseLoc.getResourcePath() + "_" + index);
        }

        return recipeLoc;
    }
}
