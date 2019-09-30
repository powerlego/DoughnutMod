package com.doughnutmod.proxy;

import com.doughnutmod.DoughnutMod;
import com.doughnutmod.FlavorsEnum;
import com.doughnutmod.blocks.ModBlocks;
import com.doughnutmod.items.ModItems;
import com.doughnutmod.items.coatings.CoatingType;
import com.doughnutmod.items.toppings.sprinkles.SprinklesEnum;
import com.doughnutmod.world.VillageHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {

    public void preInit(FMLPreInitializationEvent e){
        MinecraftForge.EVENT_BUS.register(this);

        for (CoatingType.Type type : CoatingType.Type.values()){
            type.declare();
        }
        for(SprinklesEnum.Sprinkles sprinkles : SprinklesEnum.Sprinkles.values()){
            sprinkles.declare();
        }
        for (FlavorsEnum.Flavors flavors : FlavorsEnum.Flavors.values()){
            flavors.declare();
        }

        ModItems.init();
        ModBlocks.init();

        MinecraftForge.EVENT_BUS.register(DoughnutMod.REGISTRY);
    }

    public void init(FMLInitializationEvent e){
        VillageHandler.initBakery();
    }

    public void postInit(FMLPostInitializationEvent e){

    }

}
