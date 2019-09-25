package com.doughnutmod.items;

import com.doughnutmod.DoughnutMod;
import com.doughnutmod.FlavorsEnum;
import com.doughnutmod.items.coatings.CoatingType;
import com.doughnutmod.items.doughnuts.LongJohn;
import com.doughnutmod.items.doughnuts.RingDoughnut;
import com.doughnutmod.items.toppings.Toppings;
import com.doughnutmod.items.toppings.sprinkles.SprinklesEnum;
import com.doughnutmod.registry.ModRegistry;
import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

public class ModItems {

    private static CoatingType.Type type;
    private static FlavorsEnum.Flavors flavor;
    private static SprinklesEnum.Sprinkles sprinkles;

    public static void init(){
        final ModRegistry registry = DoughnutMod.REGISTRY;

        for (CoatingType.Type type: CoatingType.Type.values()){
            registry.register(type.getCoating(), type.getName());
        }
        int i = 0;
        for(FlavorsEnum.Flavors flavors : FlavorsEnum.Flavors.values()){
            for (CoatingType.Type type : CoatingType.Type.values()){
                if(type.getName() == "glaze"||type.getName()=="powderedCinnamon"||type.getName()=="powderedSugar"||type.getName()=="none"){
                    registry.register(new RingDoughnut("doughnut_"+i,flavors.getFlavor(),type.getCoating(), Toppings.none), "doughnut_"+i);
                    i++;
                    continue;
                }
                for (SprinklesEnum.Sprinkles sprinkles: SprinklesEnum.Sprinkles.values()){
                    registry.register(new RingDoughnut("doughnut_"+i,flavors.getFlavor(),type.getCoating(),sprinkles.getSprinkles()),"doughnut_"+i);
                    i++;
                }
            }
        }
        System.out.println(i);
        for (CoatingType.Type type : CoatingType.Type.values()){

            if(type.getName() == "powderedCinnamon"||type.getName()=="powderedSugar"){
                continue;
            }

            if (type.getName() == "glaze" || type.getName() == "none") {
                registry.register(new LongJohn("doughnut_"+i,type.getCoating(), Toppings.none), "doughnut_"+i);
                i++;
                continue;
            }
            for (SprinklesEnum.Sprinkles sprinkles: SprinklesEnum.Sprinkles.values()){
                registry.register(new LongJohn("doughnut_"+i, type.getCoating(),sprinkles.getSprinkles()),"doughnut_"+i);
                i++;
            }
        }

    }
}
