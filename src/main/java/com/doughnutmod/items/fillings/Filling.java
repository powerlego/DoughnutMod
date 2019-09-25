package com.doughnutmod.items.fillings;

import com.doughnutmod.items.toppings.Toppings;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;

public class Filling extends Item {

    private String name;
    private Potion effect;
    private int duration;

    public Filling(String name, int duration, Potion effect){
        this.name = name;
        setUnlocalizedName(name);
        setRegistryName(name);
        this.effect = effect;
        this.duration = duration;
    }


    public int getDuration() {
        return duration;
    }

    public Potion getEffect() {
        return effect;
    }

    public String getName(){
        return name;
    }



}
