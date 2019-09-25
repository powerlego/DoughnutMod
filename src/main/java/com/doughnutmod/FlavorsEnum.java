package com.doughnutmod;

import net.minecraft.init.MobEffects;
import net.minecraft.potion.Potion;
import net.minecraft.util.IStringSerializable;

public class FlavorsEnum {

    public static enum Flavors implements IStringSerializable{

        CHOCOLATE("chocolate",MobEffects.STRENGTH, 6000),
        REDVELVET("redVelvet",MobEffects.HEALTH_BOOST, 6000, 2),
        PLAIN("plain",MobEffects.GLOWING, 0),
        ;

        private final String name;
        private final Potion effect;
        private final int duration;
        private final int amplifier;
        private Flavor flavor;

        Flavors(String name, Potion effect, int duration){
            this.name = name;
            this.effect = effect;
            this.duration = duration;
            this.amplifier = 1;
        }

        Flavors(String name, Potion effect, int duration, int amplifier){
            this.name = name;
            this.effect = effect;
            this.duration = duration;
            this.amplifier = amplifier;
        }

        @Override
        public String getName(){
            return name;
        }

        public Potion getEffect() {
            return effect;
        }

        public int getDuration() {
            return duration;
        }

        public int getAmplifier() {
            return amplifier;
        }

        public Flavor getFlavor(){
            return flavor;
        }

        public void declare(){
            this.flavor = new Flavor(getName(),getEffect(), getDuration(), getAmplifier());
        }
    }

}
