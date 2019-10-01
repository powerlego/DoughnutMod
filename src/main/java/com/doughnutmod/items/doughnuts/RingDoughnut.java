package com.doughnutmod.items.doughnuts;

import com.doughnutmod.Flavor;
import com.doughnutmod.items.coatings.Coatings;
import com.doughnutmod.items.toppings.Toppings;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class RingDoughnut extends ItemFood {

    private Coatings coating;
    private Toppings sprinkles;
    private static final Float factor = 0.8F;
    private String name;
    private Flavor flavor;

    public RingDoughnut(String name, Flavor flavor, Coatings coating, Toppings sprinkles){
        super(2, 0.1F, false);
        this.name = name;
        this.coating = coating;

        this.flavor = flavor;
        setRegistryName(name);
        setUnlocalizedName(name);
        this.setAlwaysEdible();

        if (coating.getName() == "chocolateIcing"||coating.getName()=="vanillaIcing"||coating.getName()=="strawberryIcing"){
            this.sprinkles = sprinkles;
        }
        else{
            this.sprinkles = Toppings.none;
        }
    }


    @Override
    public ItemStack onItemUseFinish( ItemStack stack, World worldIn, EntityLivingBase entityLiving)
    {
        if (entityLiving instanceof EntityPlayer)
        {
            EntityPlayer entityplayer = (EntityPlayer)entityLiving;
            worldIn.playSound((EntityPlayer)null, entityplayer.posX, entityplayer.posY, entityplayer.posZ, SoundEvents.ENTITY_PLAYER_BURP, SoundCategory.PLAYERS, 0.5F, worldIn.rand.nextFloat() * 0.1F + 0.9F);
            this.onFoodEaten(stack, worldIn, entityplayer);

            if (entityplayer instanceof EntityPlayerMP)
            {
                CriteriaTriggers.CONSUME_ITEM.trigger((EntityPlayerMP)entityplayer, stack);
            }
        }

        stack.shrink(1);
        return stack;
    }

    @Override
    protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {

        if (!worldIn.isRemote) {
            //flavor
            player.addPotionEffect(new PotionEffect(flavor.getEffect(), MathHelper.floor(factor * (float) flavor.getDuation()), flavor.getAmplifier()));
            //coating
            if (coating.getName() == "powderedSugar" || coating.getName() == "powderedCinnamon") {
                for (Potion effect : coating.getEffect()) {
                    player.addPotionEffect(new PotionEffect(effect, MathHelper.floor(factor * (float) coating.getDuration()), 3));
                }
            } else {
                for (Potion effect : coating.getEffect())
                    player.addPotionEffect(new PotionEffect(effect, MathHelper.floor(factor * (float) coating.getDuration())));
            }
        }
        for (Potion effect : sprinkles.getEffect()) {
            player.addPotionEffect(new PotionEffect(effect, MathHelper.floor(factor * (float) sprinkles.getEffectDuration())));
        }
    }
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn){
        String flavorS = "Plain";
        tooltip.add(TextFormatting.WHITE + "Flavor:");
        PotionEffect flavorP = new PotionEffect(flavor.getEffect(),MathHelper.floor(factor*(float)flavor.getDuation()),flavor.getAmplifier());
        if(flavor.getName() == "chocolate"||flavor.getName()=="redVelvet"){
            flavorS = I18n.translateToLocal(flavorP.getEffectName()).trim();
            flavorS = flavorS + " (" + Potion.getPotionDurationString(flavorP,1.0F)+")";
            String header = (flavor.getName() == "chocolate" ? "Chocolate":"Red Velvet");
            tooltip.add(TextFormatting.GOLD + "    "+header+": "+TextFormatting.DARK_GREEN+flavorS);
        }
        else{
            tooltip.add(TextFormatting.GOLD + "    "+flavorS);
        }

        tooltip.add(TextFormatting.WHITE + "Coating:");
        String coatingS = "None";
        if(coating.getName() != "none"){
            String header = "";
            switch (coating.getName()){
                case "glaze":
                    header = "Glaze";
                    break;
                case "powderedSugar":
                    header = "Powdered Sugar";
                    break;
                case "powderedCinnamon":
                    header = "Powdered Cinnamon";
                    break;
                case "chocolateIcing":
                    header = "Chocolate Icing";
                    break;
                case "vanillaIcing":
                    header = "Vanilla Icing";
                    break;
                case "strawberryIcing":
                    header = "Strawberry Icing";
                    break;
            }
            tooltip.add(TextFormatting.GOLD+"    "+header+":");
            for (Potion effect : coating.getEffect()) {
                PotionEffect coatingP = new PotionEffect(effect, MathHelper.floor(factor * (float) coating.getDuration()));
                coatingS = I18n.translateToLocal(coatingP.getEffectName()).trim();
                coatingS = coatingS + " (" + Potion.getPotionDurationString(coatingP, 1.0F) + ")";
                tooltip.add(TextFormatting.DARK_GREEN + "        " + coatingS);
            }
        }
        else{
            tooltip.add(TextFormatting.GOLD + "    "+coatingS);
        }
        tooltip.add(TextFormatting.WHITE+"Sprinkles:");
        String sprinklesS = "None";
        if(sprinkles.getName() != "none"){
            if (sprinkles.getName() == "rainbowSprinkles"){
                tooltip.add(TextFormatting.GOLD+"    "+"Rainbow Sprinkles"+":");
                for (Potion effect: sprinkles.getEffect()){
                    PotionEffect sprinklesP= new PotionEffect(effect, MathHelper.floor(factor * (float) sprinkles.getEffectDuration()));
                    sprinklesS = I18n.translateToLocal(sprinklesP.getEffectName()).trim();
                    sprinklesS = sprinklesS + " (" + Potion.getPotionDurationString(sprinklesP, 1.0F)+")";
                    tooltip.add(TextFormatting.DARK_GREEN+"        "+sprinklesS);
                }
            }
            else{
                PotionEffect sprinklesP= new PotionEffect(sprinkles.getEffect()[0], MathHelper.floor(factor * (float) sprinkles.getEffectDuration()));
                sprinklesS = I18n.translateToLocal(sprinklesP.getEffectName()).trim();
                sprinklesS = sprinklesS + " (" + Potion.getPotionDurationString(sprinklesP, 1.0F)+")";
                String header = "";
                switch(sprinkles.getName()){
                    case "chocolateSprinkles":
                        header = "Chocolate Sprinkles";
                        break;
                    case "vanillaSprinkles":
                        header = "Vanilla Sprinkles";
                        break;
                }
                tooltip.add(TextFormatting.GOLD+"    "+header+": "+TextFormatting.DARK_GREEN+sprinklesS);
            }
        }
        else{
            tooltip.add(TextFormatting.GOLD + "    "+sprinklesS);

        }

    }
}
