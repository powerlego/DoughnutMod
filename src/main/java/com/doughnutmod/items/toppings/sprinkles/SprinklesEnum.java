package com.doughnutmod.items.toppings.sprinkles;

import com.doughnutmod.items.toppings.Toppings;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.Potion;
import net.minecraft.util.IStringSerializable;

public class SprinklesEnum {

    public static enum Sprinkles implements IStringSerializable{


        CHOCOLATESPRINKLES("chocolateSprinkles",2500, MobEffects.STRENGTH),
        VANILLASPRINKLES("vanillaSprinkles",2500, MobEffects.REGENERATION),
        RAINBOWSPRINKLES("rainbowSprinkles", 2500,  MobEffects.ABSORPTION, MobEffects.FIRE_RESISTANCE, MobEffects.NIGHT_VISION, MobEffects.RESISTANCE, MobEffects.HASTE),
        NONE("none", 0, MobEffects.GLOWING),
        ;

        private final String name;
        private final int duration;
        private final Potion[] effects;
        private Toppings sprinkles;

        Sprinkles(String name, int duration, Potion...effects){
            this.name = name;
            this.duration = duration;
            this.effects = effects;
        }

        @Override
        public String getName(){
            return name;
        }

        public int getDuration() {
            return duration;
        }

        public Potion[] getEffects() {
            return effects;
        }

        public Toppings getSprinkles() {
            return sprinkles;
        }

        public void declare(){
            this.sprinkles = new Toppings(getName(),getDuration(),getEffects());
        }
    }


}
