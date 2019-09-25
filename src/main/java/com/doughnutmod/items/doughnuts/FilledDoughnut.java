package com.doughnutmod.items.doughnuts;

import com.doughnutmod.items.coatings.Coatings;
import com.doughnutmod.items.coatings.Glaze;
import com.doughnutmod.items.toppings.Toppings;
import com.doughnutmod.items.coatings.icing.ChocolateIcing;
import com.doughnutmod.items.coatings.icing.StrawberryIcing;
import com.doughnutmod.items.coatings.icing.VanillaIcing;
import com.doughnutmod.items.coatings.powder.PowderedCinnamon;
import com.doughnutmod.items.coatings.powder.PowderedSugar;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemFood;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.MathHelper;

public class FilledDoughnut extends ItemFood {


    private String coatingIn;
    private String sprinklesIn;
    private Coatings coating;
    private Toppings sprinkles;
    private Toppings filling;
    private String fillingIn;
    private static final Float factor = 1.0F;
    private String name;
    private PotionEffect flavor;
    private static final PotionEffect chocolate = new PotionEffect(MobEffects.STRENGTH, MathHelper.floor(factor*(float)6000));
    private static final PotionEffect redVelvet = new PotionEffect(MobEffects.HEALTH_BOOST, MathHelper.floor(factor*(float)6000), 2);
    private static final PotionEffect plain = new PotionEffect(MobEffects.GLOWING, 0);

    public FilledDoughnut(String name, String flavorIn, String coatingIn, String sprinklesIn, String fillingIn){
        super(3, 0.1F, false);
        this.name = name;
        this.coatingIn = coatingIn;
        this.sprinklesIn = sprinklesIn;
        this.fillingIn = fillingIn;
        setRegistryName(name);
        setUnlocalizedName(name);
        this.setAlwaysEdible();
        flavor = plain;
        switch (coatingIn){
            case "glaze":
                coating = new Glaze();
                break;
            case "powderedSugar":
                coating = new PowderedSugar();
                break;
            case "powderedCinnamon":
                coating = new PowderedCinnamon();
                break;
            case "chocolateIcing":
                coating = new ChocolateIcing();
                break;
            case "vanillaIcing":
                coating = new VanillaIcing();
                break;
            case "strawberryIcing":
                coating = new StrawberryIcing();
                break;
            default:
                coating = Coatings.none;
        }
        /*
        switch (fillingIn){
            case "apple":
                filling = new Apple();
                break;
            case "kreme":
                filling = new Kreme();
                break;
            case "custard":
                filling = new Custard();
                break;
            case "raspberry":
                filling = new Raspberry();
                break;
            case "lemon":
                filling = new Lemon();
                break;
            case "chocolate":
                filling = new Chocolate();
                break;
            case "caramel":
                filling = new Caramel();
                break;
            case "blueberry":
                filling = new Blueberry();
                break;
            case "strawberry":
                filling = new Strawberry();
                break;
            case "lemonKreme":
                filling = new LemonKreme();
                break;
            case "strawberryKreme":
                filling = new StrawberyKreme();
                break;
            default:
                filling = Toppings.none;
                break;
        }*/
        /*
        if (coatingIn == "chocolateIcing"||coatingIn=="vanillaIcing"||coatingIn=="strawberryIcing"){
            switch (sprinklesIn){
                case "rainbowSprinkles":
                    sprinkles = new RainbowSprinkles();
                    break;
                case "chocolateSprinkles":
                    sprinkles = new ChocolateSprinkles();
                    break;
                case "vanillaSprinkles":
                    sprinkles = new VanillaSprinkles();
                    break;
                default:
                    sprinkles = Toppings.none;
            }
        }
        else{
            sprinkles = Toppings.none;
        }*/
    }

/*
    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving)
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
            player.addPotionEffect(flavor);
            //coating
            if(coatingIn == "powderedSugar"||coatingIn=="powderedCinnamon"){
                player.addPotionEffect(new PotionEffect(coating.getEffect()[0], MathHelper.floor(factor * (float) coating.getEffectDuration()),3));
            }
            else {
                player.addPotionEffect(new PotionEffect(coating.getEffect()[0], MathHelper.floor(factor * (float) coating.getEffectDuration())));
            }
            if (sprinkles.getEffect().length > 1) {
                for (Potion effect : sprinkles.getEffect()) {
                    player.addPotionEffect(new PotionEffect(effect, MathHelper.floor(factor * (float) sprinkles.getEffectDuration())));
                }
            } else {
                player.addPotionEffect(new PotionEffect(sprinkles.getEffect()[0], MathHelper.floor(factor * (float) sprinkles.getEffectDuration())));
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn){
        String flavorS = "Plain";
        tooltip.add(TextFormatting.WHITE + "Flavor:");
        tooltip.add(TextFormatting.GOLD + "    "+flavorS);

        tooltip.add(TextFormatting.WHITE + "Coating:");
        String coatingS = "None";
        if(coating != Toppings.none){
            PotionEffect coatingP= new PotionEffect(coating.getEffect()[0], MathHelper.floor(factor * (float) coating.getEffectDuration()));
            coatingS = I18n.translateToLocal(coatingP.getEffectName()).trim();
            coatingS = coatingS + " (" + Potion.getPotionDurationString(coatingP, 1.0F)+ ")";
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
            tooltip.add(TextFormatting.GOLD+"    "+header+": "+TextFormatting.DARK_GREEN+coatingS);
        }
        else{
            tooltip.add(TextFormatting.GOLD + "    "+coatingS);
        }
        tooltip.add(TextFormatting.WHITE+"Sprinkles:");
        String sprinklesS = "None";
        if(sprinkles != Toppings.none){
            if (sprinkles instanceof RainbowSprinkles){
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


    public void registerItemModel(){
        DoughnutMod.proxy.registerItemRenderer(this, 0 , name);
    }

*/
}
