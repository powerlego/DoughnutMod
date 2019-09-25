package com.doughnutmod.items.toppings;

import com.doughnutmod.DoughnutMod;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionUtils;

import java.util.ArrayList;
import java.util.Collection;

public class Toppings extends Item {

    private String name;
    private Potion [] effect;
    private int duration;

    public Toppings(String name, int duration, Potion...effect){
        this.name = name;
        setUnlocalizedName(name);
        setRegistryName(name);
        this.effect = effect;
        this.duration = duration;

    }

    public static final Toppings none = new Toppings("none", 0, MobEffects.GLOWING);

    public int getEffectDuration() {
        return duration;
    }

    public Potion [] getEffect() {
        return effect;
    }

    public String getName(){
        return name;
    }


}
