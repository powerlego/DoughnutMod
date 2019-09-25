package com.doughnutmod.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class OvenWall extends Block {


    public OvenWall(){
        super(Material.ROCK);
        this.setHardness(2.0F);
        this.setResistance(10.0F);
        this.setSoundType(SoundType.STONE);
        this.setUnlocalizedName("ovenWall");
        this.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
    }

}
