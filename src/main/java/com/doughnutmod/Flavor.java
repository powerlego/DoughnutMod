package com.doughnutmod;

import net.minecraft.potion.Potion;

public class Flavor {

    private String name;
    private Potion effect;
    private int duration;
    private int amplifier;

    public Flavor(String name, Potion effect, int duration, int amplifier){
        this.name = name;
        this.effect = effect;
        this.duration = duration;
        this.amplifier = amplifier;
    }

    public int getAmplifier() {
        return amplifier;
    }

    public Potion getEffect() {
        return effect;
    }

    public int getDuation() {
        return duration;
    }

    public String getName() {
        return name;
    }
}
