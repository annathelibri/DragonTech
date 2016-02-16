package hyghlander.mods.DragonScales.client;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import hyghlander.mods.DragonScales.client.models.ModelDragonChestplate;
import hyghlander.mods.DragonScales.client.models.ModelModDragon;
import hyghlander.mods.DragonScales.client.models.RenderModDragon;
import hyghlander.mods.DragonScales.common.CommonProxy;
import hyghlander.mods.DragonScales.common.blocks.tile.TileEntityDragonCrystal;
import hyghlander.mods.DragonScales.common.blocks.tile.TileEntityDragonCrystalRenderer;
import hyghlander.mods.DragonScales.common.events.KeyBindings;
import hyghlander.mods.DragonScales.common.events.PlayerTickHandler;
import net.minecraft.client.model.ModelBiped;

public class ClientProxy extends CommonProxy {
	private static final ModelBiped dragonChestplate = new ModelDragonChestplate(1.0f);
	private static final ModelBiped dragonLeggings = new ModelBiped(0.45f);
	private static final ModelBiped dragonBoots = new ModelBiped(0.9f);
	
	public void registerRenderThings(){
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDragonCrystal.class, new TileEntityDragonCrystalRenderer());
		//RenderingRegistry.registerEntityRenderingHandler(EntityModDragon.class, new RenderModDragon(new ModelModDragon(), 0.5F));
	}
	
	public void registerHandlers()
	{
		super.registerHandlers();
		//KeyBindings.init();
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
}