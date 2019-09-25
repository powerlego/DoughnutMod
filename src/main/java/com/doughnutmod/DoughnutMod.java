package com.doughnutmod;

import com.doughnutmod.proxy.CommonProxy;
import com.doughnutmod.registry.ModRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = DoughnutMod.MODID, name = DoughnutMod.NAME, version = DoughnutMod.VERSION)
public class DoughnutMod {
    public static final String MODID = "doughnutmod";
    public static final String NAME = "Doughnut Mod";
    public static final String VERSION = "1.0";
    public static final ModRegistry REGISTRY = ModRegistry.create(MODID);
    private static Logger logger;

    @Mod.Instance(MODID)
    public static DoughnutMod instance;

    @SidedProxy(serverSide = "com.doughnutmod.proxy.CommonProxy", clientSide = "com.doughnutmod.proxy.ClientProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        // some example code
        proxy.init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event){
        proxy.postInit(event);

    }
}
