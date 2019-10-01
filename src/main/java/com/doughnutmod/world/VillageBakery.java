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



    @Override
    public boolean addComponentParts(World world, Random rand, StructureBoundingBox box){
        if (groundLevel < 0) {
            this.groundLevel = getAverageGroundLevel(world, box);

            if (groundLevel < 0)
                return true;

            boundingBox.offset(0, groundLevel - boundingBox.maxY + 7, 0);
        }


        IBlockState glassPane = Blocks.GLASS_PANE.getDefaultState();
        IBlockState stoneBrick = Blocks.STONEBRICK.getDefaultState();
        IBlockState oakLog = Blocks.LOG.getDefaultState();
        IBlockState oakLogX = Blocks.LOG.getDefaultState().withProperty(BlockLog.LOG_AXIS, BlockLog.EnumAxis.X);
        IBlockState oakLogZ = Blocks.LOG.getDefaultState().withProperty(BlockLog.LOG_AXIS, BlockLog.EnumAxis.Z);
        IBlockState spruceLog = Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.SPRUCE);
        IBlockState spruceLogX = Blocks.LOG.getDefaultState().withProperty(BlockLog.LOG_AXIS, BlockLog.EnumAxis.X).withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.SPRUCE);
        IBlockState spruceLogZ = Blocks.LOG.getDefaultState().withProperty(BlockLog.LOG_AXIS, BlockLog.EnumAxis.Z).withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.SPRUCE);
        IBlockState whiteWool = Blocks.WOOL.getDefaultState().withProperty(BlockColored.COLOR, EnumDyeColor.WHITE);
        IBlockState oakStairs = Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST).withProperty(BlockStairs.HALF, BlockStairs.EnumHalf.TOP);
        IBlockState flowerPot = Blocks.FLOWER_POT.getDefaultState();
        IBlockState craftingTable = Blocks.CRAFTING_TABLE.getDefaultState();
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

        setBlockState(world,Blocks.PLANKS.getDefaultState(),3,0,4,box);
        setBlockState(world,Blocks.CAULDRON.getDefaultState(),6,1,3,box);


        fillWithBlocks(world,box,2,1,3,2,1,5,oakStairs,oakStairs,false);
        oakStairs = oakStairs.withProperty(BlockStairs.FACING,EnumFacing.WEST);
        setBlockState(world,oakStairs,4,1,3,box);
        setBlockState(world,oakStairs,4,1,5,box);
        oakStairs = oakStairs.withProperty(BlockStairs.HALF, BlockStairs.EnumHalf.BOTTOM);
        setBlockState(world,oakStairs,4,1,4,box);
        oakStairs = oakStairs.withProperty(BlockStairs.FACING,EnumFacing.NORTH);
        setBlockState(world,oakStairs,3,1,3,box);
        oakStairs = oakStairs.withProperty(BlockStairs.FACING,EnumFacing.SOUTH);
        setBlockState(world,oakStairs,3,1,5,box);
        oakStairs = oakStairs.withProperty(BlockStairs.HALF, BlockStairs.EnumHalf.TOP);
        fillWithBlocks(world, box, 10,1,6,11,1,6,oakStairs,oakStairs,false);
        oakStairs = oakStairs.withProperty(BlockStairs.FACING,EnumFacing.NORTH);
        fillWithBlocks(world, box, 10,1,10,11,1,10,oakStairs,oakStairs,false);
        setBlockState(world, oakStairs,11,3,10,box);
        setBlockState(world,oakStairs,9,3,10,box);


        IBlockState quartzSlab = Blocks.STONE_SLAB.getDefaultState().withProperty(BlockSlab.HALF, BlockSlab.EnumBlockHalf.TOP).withProperty(BlockStoneSlab.VARIANT, BlockStoneSlab.EnumType.QUARTZ);

        fillWithBlocks(world,box,10,1,7,11,1,9,quartzSlab,quartzSlab,false);

        fillWithBlocks(world,box,3,1,7,3,2,9,stoneBrick,stoneBrick,false);

        fillWithBlocks(world,box,4,-1,8,5,-1,8,Blocks.NETHERRACK.getDefaultState(),Blocks.NETHERRACK.getDefaultState(),false);
        fillWithBlocks(world,box,4,0,8,5,0,8,Blocks.FIRE.getDefaultState(),Blocks.FIRE.getDefaultState(),false);

        IBlockState stoneBrickStairs = Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING,EnumFacing.NORTH);
        fillWithBlocks(world,box,3,1,6,6,1,6,stoneBrickStairs,stoneBrickStairs,false);
        fillWithBlocks(world,box,4,1,9,5,1,9,stoneBrickStairs,stoneBrickStairs,false);
        fillWithBlocks(world,box,3,3,7,6,3,7,stoneBrickStairs,stoneBrickStairs,false);
        stoneBrickStairs = stoneBrickStairs.withProperty(BlockStairs.HALF, BlockStairs.EnumHalf.TOP);
        fillWithBlocks(world,box,4,2,9,5,2,9,stoneBrickStairs,stoneBrickStairs,false);
        stoneBrickStairs = stoneBrickStairs.withProperty(BlockStairs.FACING, EnumFacing.SOUTH);
        fillWithBlocks(world,box,4,2,7,5,2,7,stoneBrickStairs,stoneBrickStairs,false);
        stoneBrickStairs = stoneBrickStairs.withProperty(BlockStairs.HALF, BlockStairs.EnumHalf.BOTTOM);
        fillWithBlocks(world,box,4,1,7,5,1,7,stoneBrickStairs,stoneBrickStairs,false);
        fillWithBlocks(world, box,3,1,10,6,1,10,stoneBrickStairs,stoneBrickStairs,false);
        fillWithBlocks(world, box,3,3,9,6,3,9,stoneBrickStairs,stoneBrickStairs,false);
        stoneBrickStairs = stoneBrickStairs.withProperty(BlockStairs.FACING, EnumFacing.EAST);
        fillWithBlocks(world,box,2,1,6,2,1,10,stoneBrickStairs,stoneBrickStairs,false);


        fillWithBlocks(world,box,6,1,9,6,2,9,stoneBrick,stoneBrick,false);
        fillWithBlocks(world,box,6,1,7,6,2,7,stoneBrick,stoneBrick,false);

        setBlockState(world,stoneBrick,3,3,8,box);
        setBlockState(world,stoneBrick,6,3,8,box);

        fillWithBlocks(world,box,4,4,8,4,10,8,stoneBrick,stoneBrick,false);
        setBlockState(world,Blocks.AIR.getDefaultState(),5,6,8,box);

        IBlockState stoneBrickSlab = Blocks.STONE_SLAB.getDefaultState().withProperty(BlockStoneSlab.VARIANT, BlockStoneSlab.EnumType.SMOOTHBRICK);
        setBlockState(world,stoneBrickSlab,3,4,8,box);
        setBlockState(world,stoneBrickSlab,5,4,8,box);
        setBlockState(world,stoneBrickSlab,6,4,8,box);

        placeTorches(world,box);

        IBlockState darkOakSlab = Blocks.WOODEN_SLAB.getDefaultState().withProperty(BlockWoodSlab.VARIANT, BlockPlanks.EnumType.DARK_OAK).withProperty(BlockSlab.HALF, BlockSlab.EnumBlockHalf.TOP);
        fillWithBlocks(world,box,2,2,3,2,2,5,darkOakSlab,darkOakSlab,false);


        fillWithBlocks(world,box,2,3,3,2,3,4,flowerPot,flowerPot,false);
        setBlockState(world,craftingTable,10,3,10,box);

        fillWithBlocks(world,box,2,4,3,2,4,6,oakSlab,oakSlab,false);

        fillWithBlocks(world,box,2,5,4,2,5,5,flowerPot,flowerPot,false);

        setBlockState(world,spruceLog,4,1,1,box);
        setBlockState(world,spruceLog,6,1,1,box);

        setBlockState(world,spruceLogZ,5,1,0,box);
        setBlockState(world,spruceLogZ,7,1,1,box);

        setBlockState(world,spruceLogX,13,1,1,box);
        setBlockState(world,oakLogX,13,1,2,box);
        setBlockState(world,oakLogX,13,1,3,box);
        setBlockState(world,spruceLogX,13,1,4,box);

        setBlockState(world,spruceLogX,14,1,2,box);
        setBlockState(world,spruceLogX,14,1,3,box);

        setBlockState(world,spruceLogX,13,2,2,box);
        setBlockState(world,oakLogX,13,2,3,box);

        IBlockState trapDoor = Blocks.TRAPDOOR.getDefaultState().withProperty(BlockTrapDoor.FACING,EnumFacing.SOUTH).withProperty(BlockTrapDoor.HALF, BlockTrapDoor.DoorHalf.BOTTOM).withProperty(BlockTrapDoor.OPEN,true);

        setBlockState(world,trapDoor,5,1,-1,box);
        setBlockState(world,trapDoor,7,1,0,box);

        trapDoor = trapDoor.withProperty(BlockTrapDoor.FACING, EnumFacing.NORTH);

        setBlockState(world,trapDoor,5,1,1,box);

        trapDoor = trapDoor.withProperty(BlockTrapDoor.FACING, EnumFacing.SOUTH).withProperty(BlockTrapDoor.OPEN,false);

        setBlockState(world,trapDoor,4,2,1,box);
        setBlockState(world,trapDoor,6,2,1,box);

        trapDoor = trapDoor.withProperty(BlockTrapDoor.FACING,EnumFacing.EAST);

        fillWithBlocks(world,box,13,4,5,13,4,8,trapDoor,trapDoor,false);




        placeDoor(world,box,10,1,2,EnumFacing.NORTH, BlockDoor.EnumHingePosition.LEFT);
        placeDoor(world,box,11,1,2,EnumFacing.NORTH, BlockDoor.EnumHingePosition.RIGHT);



        return true;
    }



    private void buildRoof(World world, StructureBoundingBox box){
        IBlockState brickSlab = Blocks.STONE_SLAB.getDefaultState().withProperty(BlockStoneSlab.VARIANT, BlockStoneSlab.EnumType.BRICK);
        IBlockState brickSlabInverted = brickSlab.withProperty(BlockSlab.HALF, BlockSlab.EnumBlockHalf.TOP);
        for (int i = 0;i<3;i++){
            fillWithBlocks(world,box,0,6+(i),1+2*i,13,6+i,1+2*i,brickSlabInverted,brickSlabInverted,false);
            fillWithBlocks(world,box,0,7+i,2+2*i,13,7+i,2+2*i,brickSlab,brickSlab,false);
            fillWithBlocks(world,box,0,6+(i),12-2*i,13,6+i,12-2*i,brickSlabInverted,brickSlabInverted,false);
            fillWithBlocks(world,box,0,7+i,11-2*i,13,7+i,11-2*i,brickSlab,brickSlab,false);
        }
    }

    public void placeTorches(World world, StructureBoundingBox box){
        IBlockState torch = Blocks.TORCH.getDefaultState();

        setBlockState(world,torch,1,7,1,box);
        setBlockState(world,torch,1,7,12,box);
        setBlockState(world,torch,7,7,1,box);
        setBlockState(world,torch,7,7,12,box);
        setBlockState(world,torch,13,7,1,box);
        setBlockState(world,torch,13,7,12,box);

        setBlockState(world,torch,3,7,4,box);
        setBlockState(world,torch,3,7,9,box);
        setBlockState(world,torch,9,7,4,box);
        setBlockState(world,torch,9,7,9,box);


        setBlockState(world,torch,1,9,5,box);
        setBlockState(world,torch,1,9,8,box);
        setBlockState(world,torch,7,9,5,box);
        setBlockState(world,torch,7,9,8,box);
        setBlockState(world,torch,13,9,5,box);
        setBlockState(world,torch,13,9,8,box);

        torch = torch.withProperty(BlockTorch.FACING,EnumFacing.NORTH);
        setBlockState(world,torch,4,4,3, box);
        setBlockState(world,torch,4,3,12,box);
        setBlockState(world,torch,9,3,12,box);


        torch = torch.withProperty(BlockTorch.FACING,EnumFacing.WEST);

        setBlockState(world,torch,11,4,4,box);
        setBlockState(world,torch,0,3,5,box);
        setBlockState(world,torch,0,3,8,box);

        torch = torch.withProperty(BlockTorch.FACING,EnumFacing.EAST);

        setBlockState(world,torch,7,3,8,box);
        setBlockState(world,torch,13,3,4,box);
        setBlockState(world,torch,13,3,9,box);


        torch = torch.withProperty(BlockTorch.FACING,EnumFacing.SOUTH);
        setBlockState(world,torch,4,4,10,box);
        setBlockState(world,torch,4,3,1,box);
        setBlockState(world,torch,1,3,1,box);
        setBlockState(world,torch,9,2,1,box);
        setBlockState(world,torch,12,2,1,box);
    }

    protected void placeDoor(World worldIn, StructureBoundingBox boundingBoxIn, int x, int y, int z, EnumFacing facing, BlockDoor.EnumHingePosition hinge)
    {
        this.setBlockState(worldIn, Blocks.OAK_DOOR.getDefaultState().withProperty(BlockDoor.FACING, facing).withProperty(BlockDoor.HINGE, hinge), x, y, z, boundingBoxIn);
        this.setBlockState(worldIn, Blocks.OAK_DOOR.getDefaultState().withProperty(BlockDoor.FACING, facing).withProperty(BlockDoor.HALF, BlockDoor.EnumDoorHalf.UPPER).withProperty(BlockDoor.HINGE, hinge), x, y+1, z, boundingBoxIn);
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
