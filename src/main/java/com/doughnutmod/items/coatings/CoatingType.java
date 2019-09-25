package com.doughnutmod.items.coatings;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.Potion;
import net.minecraft.util.IStringSerializable;

public class CoatingType {

    public static enum Type implements IStringSerializable {


        CHOCOLATEICING("chocolateIcing",3000, MobEffects.STRENGTH),

        STRAWBERRYICING("strawberryIcing",3000, MobEffects.FIRE_RESISTANCE),
        VANILLAICING("vanillaIcing",3000, MobEffects.REGENERATION),

        POWDEREDCINNAMON("powderedCinnamon",3000, MobEffects.HASTE),
        POWDEREDSUGAR("powderedSugar",3000, MobEffects.SPEED, MobEffects.JUMP_BOOST),
        GLAZE("glaze",3000, MobEffects.LUCK),
        NONE("none", 0, MobEffects.GLOWING),
        ;

        private final String name;
        private final int duration;
        private final Potion[] effects;
        private Coatings coating;

        Type(String name, int duration, Potion...effects){
            this.name = name;
            this.duration = duration;
            this.effects = effects;
        }

        @Override
        public String getName(){
            return this.name;
        }

        public int getDuration() {
            return duration;
        }

        public Potion[] getEffects() {
            return effects;
        }

        public Coatings getCoating() {
            return coating;
        }

        public void declare(){
            this.coating = new Coatings(getName(), getDuration(), getEffects());
            coating.setCreativeTab(CreativeTabs.MATERIALS);
        }

    }


}
