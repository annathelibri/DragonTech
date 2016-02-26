package hyghlander.mods.DragonScales.client;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.relauncher.Side;
import hyghlander.mods.DragonScales.client.models.ModelDragonChestplate;
import hyghlander.mods.DragonScales.client.models.ModelModDragon;
import hyghlander.mods.DragonScales.client.models.RenderModDragon;
import hyghlander.mods.DragonScales.client.renderers.TileCrystalRenderer;
import hyghlander.mods.DragonScales.client.renderers.BlockModCauldronRenderer;
import hyghlander.mods.DragonScales.client.renderers.TileCauldronConstructRenderer;
import hyghlander.mods.DragonScales.client.renderers.TileCombinerRenderer;
import hyghlander.mods.DragonScales.common.CommonProxy;
import hyghlander.mods.DragonScales.common.DragonScalesHandler;
import hyghlander.mods.DragonScales.common.blocks.tile.TileCauldronConstruct;
import hyghlander.mods.DragonScales.common.blocks.tile.TileCombiner;
import hyghlander.mods.DragonScales.common.blocks.tile.TileCrystal;
import hyghlander.mods.DragonScales.common.events.KeyBindings;
import hyghlander.mods.DragonScales.common.events.PlayerTickHandler;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.item.ItemStack;

public class ClientProxy extends CommonProxy {
	private static final ModelBiped dragonChestplate = new ModelDragonChestplate(1.0f);
	private static final ModelBiped dragonLeggings = new ModelBiped(0.45f);
	private static final ModelBiped dragonBoots = new ModelBiped(0.9f);
	
	private static int cauldronRenderType = -1;
	
	public void preInit(){
		super.preInit();
		
		//Tweak to Remove the Cauldron from NEI
		if (Loader.isModLoaded("NotEnoughItems"));
		codechicken.nei.api.API.hideItem(new ItemStack(DragonScalesHandler.modCauldron));
	}
	
	public void registerRenderThings(){
		//Register TileEntity Renderers
		ClientRegistry.bindTileEntitySpecialRenderer(TileCrystal.class, new TileCrystalRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileCombiner.class, new TileCombinerRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileCauldronConstruct.class, new TileCauldronConstructRenderer());
		
		
		//Register Cauldron Renderer
		cauldronRenderType = RenderingRegistry.getNextAvailableRenderId();
		ISimpleBlockRenderingHandler cauldronRenderer = new BlockModCauldronRenderer();
		RenderingRegistry.registerBlockHandler(cauldronRenderType, cauldronRenderer);
		
		//Register Dragon Renderer
		//RenderingRegistry.registerEntityRenderingHandler(EntityModDragon.class, new RenderModDragon(new ModelModDragon(), 0.5F));
	}
	
	@Override
	public ModelBiped getArmorModel(int id){
		switch (id) {
		case 0:
			return dragonBoots;
		case 1:
			return dragonChestplate;
		case 2:
			return dragonLeggings;
		case 3:
			return dragonBoots;
		}
		return dragonLeggings;
	}
	
	@Override
	public int getRenderType(String name) {
		if (name.equals("modCauldron"))
			return cauldronRenderType;
		
		return 0;
	}
}
