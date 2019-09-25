package com.doughnutmod.items.coatings;

import com.doughnutmod.DoughnutMod;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;

public class Coatings extends Item {

    private String name;
    private Potion [] effect;
    private int duration;

    public Coatings(String name, int duration, Potion...effect){
        this.name = name;
        setUnlocalizedName(name);
        setRegistryName(name);
        this.effect = effect;
        this.duration = duration;

    }

    public static final Coatings none = new Coatings("none", 0, MobEffects.GLOWING);

    public String getName() {
        return name;
    }

    public Potion[] getEffect() {
        return effect;
    }

    public int getDuration() {
        return duration;
    }
}
