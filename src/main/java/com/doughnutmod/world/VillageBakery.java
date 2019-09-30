package com.doughnutmod.world;


import net.minecraft.block.*;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureVillagePieces.Start;
import net.minecraft.world.gen.structure.StructureVillagePieces.Village;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureVillagePieces.PieceWeight;
import net.minecraftforge.fml.common.registry.VillagerRegistry.*;

import javax.annotation.Nullable;

import java.util.List;
import java.util.Random;

public class VillageBakery extends Village {



    private boolean hasMadeChest;
    private final Random random;


    public VillageBakery(){
        random = new Random();
    }

    public VillageBakery(Start villagePiece, int componentType, Random random, StructureBoundingBox boundingBox, EnumFacing facing){
        super(villagePiece, componentType);
        this.setCoordBaseMode(facing);
        this.random = random;
        this.boundingBox = boundingBox;

    }


    private int groundLevel = -1;

    private IBlockState brickSlab = Blocks.STONE_SLAB.getDefaultState().withProperty(BlockStoneSlab.VARIANT, BlockStoneSlab.EnumType.BRICK);
    private IBlockState brickSlabInverted = brickSlab.withProperty(BlockSlab.HALF, BlockSlab.EnumBlockHalf.TOP);

    @Override
    public boolean addComponentParts(World world, Random rand, StructureBoundingBox box){
        if (groundLevel < 0) {
            this.groundLevel = getAverageGroundLevel(world, box);

            if (groundLevel < 0)
                return true;

            boundingBox.offset(0, groundLevel - boundingBox.maxY + 7, 0);
        }

        IBlockState torch = Blocks.TORCH.getDefaultState();
        IBlockState glassPane = Blocks.GLASS_PANE.getDefaultState();
        IBlockState stoneBrick = Blocks.STONEBRICK.getDefaultState();
        IBlockState oakLog = Blocks.LOG.getDefaultState();
        IBlockState oakLogX = Blocks.LOG.getDefaultState().withProperty(BlockLog.LOG_AXIS, BlockLog.EnumAxis.X);
        IBlockState oakLogZ = Blocks.LOG.getDefaultState().withProperty(BlockLog.LOG_AXIS, BlockLog.EnumAxis.Z);
        IBlockState spruceLog = Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.SPRUCE);
        IBlockState spruceLogX = Blocks.LOG.getDefaultState().withProperty(BlockLog.LOG_AXIS, BlockLog.EnumAxis.X).withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.SPRUCE);
        IBlockState spruceLogZ = Blocks.LOG.getDefaultState().withProperty(BlockLog.LOG_AXIS, BlockLog.EnumAxis.Z).withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.SPRUCE);
        IBlockState whiteWool = Blocks.WOOL.getDefaultState().withProperty(BlockColored.COLOR, EnumDyeColor.WHITE);

        IBlockState oakSlab = Blocks.WOODEN_SLAB.getDefaultState().withProperty(BlockSlab.HALF, BlockSlab.EnumBlockHalf.TOP);

        //clear area
        fillWithAir(world,box,0,1,0,14,9,13);


        //set layer 0

        fillWithBlocks(world,box,0,0,0,14,0,12,stoneBrick,stoneBrick,false);


        fillWithBlocks(world,box,2,1,2, 8, 5, 2,whiteWool,whiteWool,false);
        fillWithBlocks(world,box,2,1,11, 11, 5, 11,whiteWool,whiteWool,false);
        fillWithBlocks(world,box,1,1,3, 1, 5, 10,whiteWool,whiteWool,false);
        fillWithBlocks(world,box,1,0,2,1,6,2,oakLog,oakLog,false);
        fillWithBlocks(world,box,1,0,5,1,5,5,oakLog,oakLog,false);
        fillWithBlocks(world,box,1,0,8,1,5,8,oakLog,oakLog,false);
        fillWithBlocks(world,box,1,0,11,1,6,11,oakLog,oakLog,false);

        fillWithBlocks(world,box,4,0,2,4,5,2,oakLog,oakLog,false);
        fillWithBlocks(world,box,4,0,11,4,5,11,oakLog,oakLog,false);

        fillWithBlocks(world,box,9,0,2,9,5,2,oakLog,oakLog,false);
        fillWithBlocks(world,box,9,0,11,9,5,11,oakLog,oakLog,false);

        fillWithBlocks(world,box,12,0,2,12,6,2,oakLog,oakLog,false);
        fillWithBlocks(world,box,12,0,11,12,6,11,oakLog,oakLog,false);

        fillWithBlocks(world,box,12,0,4,12,3,4,oakLog,oakLog,false);
        fillWithBlocks(world,box,12,0,9,12,6,9,oakLog,oakLog,false);




        fillWithBlocks(world,box,1,7,4, 1, 7, 9,whiteWool,whiteWool,false);
        fillWithBlocks(world,box,12,7,4, 12, 7, 9,whiteWool,whiteWool,false);
        fillWithBlocks(world,box,1,7,6,1,8,7,oakLog,oakLog,false);
        fillWithBlocks(world,box,12,7,6,12,8,7,oakLog,oakLog,false);

        fillWithBlocks(world,box,12,5,3, 12, 5, 10,whiteWool,whiteWool,false);
        setBlockState(world,oakLog,12,5,5,box);
        setBlockState(world,oakLog,12,5,8,box);

        fillWithBlocks(world,box,2,6,3,11,6,10,oakSlab,oakSlab,false);

        fillWithBlocks(world,box,2,3,2,11,3,2,oakLogX,oakLogX,false);
        fillWithBlocks(world,box,2,3,11,11,3,11,oakLogX,oakLogX,false);

        fillWithBlocks(world,box,2,6,2,11,6,2,oakLogX,oakLogX,false);
        fillWithBlocks(world,box,2,6,11,11,6,11,oakLogX,oakLogX,false);

        fillWithBlocks(world,box,2,6,5,11,6,5,oakLogX,oakLogX,false);

        fillWithBlocks(world,box,12,1,5,12,1,8,oakLogZ,oakLogZ,false);


        fillWithBlocks(world,box,1,3,3,1,3,10,oakLogZ,oakLogZ,false);
        setBlockState(world,oakLogZ,12,3,10,box);

        fillWithBlocks(world,box,12,4,3,12,4,10,oakLogZ,oakLogZ,false);

        fillWithBlocks(world,box,1,6,3,1,6,10,oakLogZ,oakLogZ,false);
        fillWithBlocks(world,box,4,6,3,4,6,4,oakLogZ,oakLogZ,false);
        fillWithBlocks(world,box,9,6,3,9,6,10,oakLogZ,oakLogZ,false);
        fillWithBlocks(world,box,12,6,3,12,6,10,oakLogZ,oakLogZ,false);

        fillWithBlocks(world,box,10,4,2, 11, 5, 2,whiteWool,whiteWool,false);
        fillWithBlocks(world,box,12,1,3, 12, 3, 3,whiteWool,whiteWool,false);
        fillWithBlocks(world,box,12,1,10, 12, 2, 10,whiteWool,whiteWool,false);
        buildRoof(world,box);


        fillWithBlocks(world,box,12,2,5,12,3,8,glassPane,glassPane,false);





        return true;
    }


    public void buildRoof(World world, StructureBoundingBox box){
        for (int i = 0;i<3;i++){
            fillWithBlocks(world,box,0,6+(i),1+2*i,13,6+i,1+2*i,brickSlabInverted,brickSlabInverted,false);
            fillWithBlocks(world,box,0,7+i,2+2*i,13,7+i,2+2*i,brickSlab,brickSlab,false);
            fillWithBlocks(world,box,0,6+(i),12-2*i,13,6+i,12-2*i,brickSlabInverted,brickSlabInverted,false);
            fillWithBlocks(world,box,0,7+i,11-2*i,13,7+i,11-2*i,brickSlab,brickSlab,false);
        }
    }


    public static class VillageManager implements IVillageCreationHandler
    {
        @Override
        public Village buildComponent(PieceWeight villagePiece, Start startPiece, List<StructureComponent> pieces, Random random, int p1, int p2, int p3, EnumFacing facing, int p5)
        {
            StructureBoundingBox box = StructureBoundingBox.getComponentToAddBoundingBox(p1, p2, p3, 0, 0, 0, 14, 9, 13, facing);
            return (!canVillageGoDeeper(box))||(StructureComponent.findIntersecting(pieces, box)!=null)?null: new VillageBakery(startPiece, p5, random, box, facing);
        }

        @Override
        public PieceWeight getVillagePieceWeight(Random random, int i)
        {
            return new PieceWeight(VillageBakery.class, 15, MathHelper.getInt(random, 0+i, 1+i));
        }

        @Override
        public Class<?> getComponentClass()
        {
            return VillageBakery.class;
        }
    }


}
