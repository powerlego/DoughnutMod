package com.doughnutmod.world;

import com.doughnutmod.DoughnutMod;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraftforge.fml.common.registry.VillagerRegistry;

public class VillageHandler {


    private static final VillagerRegistry VILLAGER_REGISTRY = VillagerRegistry.instance();


    public static void initBakery(){


        VILLAGER_REGISTRY.registerVillageCreationHandler(new VillageBakery.VillageManager());
        MapGenStructureIO.registerStructureComponent(VillageBakery.class, DoughnutMod.MODID+":Bakery");


    }


}
