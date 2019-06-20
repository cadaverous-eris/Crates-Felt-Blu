package cfb;

import java.util.ArrayList;
import java.util.List;

import blusunrize.immersiveengineering.ImmersiveEngineering;
import blusunrize.immersiveengineering.common.blocks.wooden.BlockWoodenDevice0;
import blusunrize.immersiveengineering.common.util.Utils;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod(modid = CratesFeltBlu.MODID, name = CratesFeltBlu.NAME, version = CratesFeltBlu.VERSION, dependencies = CratesFeltBlu.DEPENDENCIES)
public class CratesFeltBlu {
	
	public static final String MODID = "cfb";
	public static final String NAME = "Crates Felt Blu";
	public static final String VERSION = "1.0";
	public static final String DEPENDENCIES = "required-after:immersiveengineering";
	
	@Mod.Instance
	public static CratesFeltBlu instance;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		
	}
	
	@SubscribeEvent
	public void crateHarvest(BlockEvent.HarvestDropsEvent event) {
		IBlockState state = event.getState();
		if (state.getBlock() instanceof BlockWoodenDevice0) {
			List<ItemStack> drops = event.getDrops();
			List<ItemStack> inventory = new ArrayList<ItemStack>();
			
			for (ItemStack stack : event.getDrops()) {
				if (stack.getItem() instanceof ItemBlock && ((ItemBlock) stack.getItem()).getBlock() instanceof BlockWoodenDevice0 && stack.hasTagCompound() && stack.getTagCompound().hasKey("inventory", 9)) {	
					NBTTagList invTagList = stack.getTagCompound().getTagList("inventory", 10);
					inventory.addAll(Utils.readInventory(invTagList, 27));
					stack.getTagCompound().removeTag("inventory");
					if (stack.getTagCompound().hasNoTags()) stack.setTagCompound(null);
				}
			}
			
			drops.addAll(inventory);
		}
	}
	
}
