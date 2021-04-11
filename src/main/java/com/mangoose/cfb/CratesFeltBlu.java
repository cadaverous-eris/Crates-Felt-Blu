package com.mangoose.cfb;

import blusunrize.immersiveengineering.common.blocks.wooden.CrateBlock;
import blusunrize.immersiveengineering.common.blocks.wooden.WoodenCrateTileEntity;
import blusunrize.immersiveengineering.common.util.inventory.IIEInventory;
import net.minecraft.block.BlockState;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nullable;

@Mod("cratesfeltblu")
public final class CratesFeltBlu {
	public CratesFeltBlu() {
		MinecraftForge.EVENT_BUS.<BlockEvent.BreakEvent>addListener(event -> {
			BlockState state = event.getState();
			if (state.getBlock() instanceof CrateBlock) {
				IWorld world = event.getWorld();
				if (!world.isRemote()) {
					BlockPos pos = event.getPos();
					@Nullable TileEntity tile = world.getTileEntity(pos);
					if (tile instanceof WoodenCrateTileEntity) {
						@Nullable NonNullList<ItemStack> inventory = ((IIEInventory) tile).getInventory();
						if (inventory != null) {
							InventoryHelper.dropItems((World) world, pos, inventory);
						}
					}
				}
			}
		});
	}
}
