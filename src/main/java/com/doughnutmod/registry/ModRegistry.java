package com.doughnutmod.registry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.doughnutmod.helper.IModelHelper;
import com.doughnutmod.helper.RecipeHelper;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.oredict.OreDictionary;

public class ModRegistry {
    private String modid;
    public List<RegistryObject<Block>> blocks = new ArrayList<>();
    public List<RegistryObject<Item>> items = new ArrayList<>();
    public List<RegistryObject<IRecipe>> recipes = new ArrayList<>();
    public Map<Class, String> tiles = new HashMap<>();

    public ModRegistry(String modid) {
        this.modid = modid;
    }

    public static ModRegistry create(String modid) {
        ModRegistry registry = new ModRegistry(modid);
        MinecraftForge.EVENT_BUS.register(registry);
        return registry;
    }

    public <T extends Block> T register(T block, String name) {
        return register(block, name, true);
    }


    public <T extends Block> T register(T block, String name, boolean itemBlock) {
        blocks.add(new RegistryObject<Block>(block, name));
        if (itemBlock) {
            items.add(new RegistryObject<Item>(new ItemBlock(block), name));
        }
        return block;
    }


    public <T extends Block> T register(T block, String name, ItemBlock itemBlock) {
        blocks.add(new RegistryObject<Block>(block, name));
        items.add(new RegistryObject<Item>(itemBlock, name));
        return block;
    }

    public <T extends Item> T register(T item, String name) {
        items.add(new RegistryObject<Item>(item, name));
        return item;
    }


    public <T extends Item> T register(T item, String name, ItemStack stack) {
        register(item, name);
        return item;
    }


    public <T extends IRecipe> T register(T recipe) {
        if (recipe.getRecipeOutput().isEmpty()) {
            return recipe;
        }
        if (recipe.getIngredients().isEmpty()) {
            return recipe;
        }
        recipes.add(new RegistryObject<IRecipe>(recipe,
                RecipeHelper.getRecipeLocation(recipe.getRecipeOutput()).toString()));
        return recipe;
    }

    public <T extends IRecipe> T register(T recipe, String name) {
        if (recipe.getRecipeOutput().isEmpty()) {
            return recipe;
        }
        if (recipe.getIngredients().isEmpty()) {
            return recipe;
        }
        recipes.add(new RegistryObject<IRecipe>(recipe, modid + ":" + name));
        return recipe;
    }

    public void register(Class clazz, String name) {
        tiles.put(clazz, name);
    }


    @SubscribeEvent
    public void registerBlocks(RegistryEvent.Register<Block> event) {
        for (RegistryObject<Block> block : blocks) {
            if (block.get().getRegistryName() == null) {
                block.get().setRegistryName(block.getName());
            }
            event.getRegistry().register(block.get());
        }
    }

    @SubscribeEvent
    public void registerItems(RegistryEvent.Register<Item> event) {
        for (RegistryObject<Item> item : items) {
            if (item.get().getRegistryName() == null) {
                item.get().setRegistryName(item.getName());
            }
            event.getRegistry().register(item.get());
        }
    }

    @SubscribeEvent
    public void registerRecipes(RegistryEvent.Register<IRecipe> event) {
        for (RegistryObject<IRecipe> recipe : recipes) {
            if (recipe.get().getRegistryName() == null) {
                recipe.get().setRegistryName(new ResourceLocation(recipe.getName()));
            }
            event.getRegistry().register(recipe.get());
        }
    }

    @SubscribeEvent
    public void registerModels(ModelRegistryEvent event) {
        for (RegistryObject<Item> item : items) {
            if (item.get() instanceof IModelHelper) {
                ((IModelHelper) item.get()).initModels();
            } else if (item.get() instanceof ItemBlock) {
                if (((ItemBlock) item.get()).getBlock() instanceof IModelHelper) {
                    ((IModelHelper) ((ItemBlock) item.get()).getBlock()).initModels();
                } else {
                    ModelLoader.setCustomModelResourceLocation(item.get(), 0,
                            new ModelResourceLocation(modid + ":" + item.getName(), "inventory"));
                }
            } else {
                ModelLoader.setCustomModelResourceLocation(item.get(), 0,
                        new ModelResourceLocation(modid + ":" + item.getName(), "inventory"));
            }
        }
    }



}
