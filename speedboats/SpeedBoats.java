package speedboats;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.settings.KeyBinding;
import net.minecraft.item.Item;
import net.minecraftforge.common.Configuration;
import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

@Mod(modid = "SpeedBoats", name = "SpeedBoats", version = "0.1.0")
@NetworkMod(clientSideRequired = true, serverSideRequired = false, channels = "SpeedBoat", packetHandler = SpeedBoatPacketHandler.class)

public class SpeedBoats 
{
	@Instance("SpeedBoats")
	public static SpeedBoats instance;
	
	public static Item speedBoat;
	public static int KEY_SPEED = Keyboard.KEY_F;
	public static int KEY_JUMP = Keyboard.KEY_G;
	public static int speedBoatID;
	
	@SidedProxy(clientSide = "speedboats.ClientProxy", serverSide = "speedboats.CommonProxy")
	public static CommonProxy proxy;

	@cpw.mods.fml.common.Mod.PreInit
	public void PreInit(FMLPreInitializationEvent event)
	{
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();
		
		speedBoatID =  config.getItem("SpeedBoat ID", 10000, (String)null).getInt();
		
		config.save();
		
	}
	
	@Init
	public void load(FMLInitializationEvent event) 
	{
		
		speedBoat = new ItemSpeedBoat(speedBoatID).setUnlocalizedName("Speed Boat");
		
		LanguageRegistry.addName(speedBoat, "Speed Boat");
		
		EntityRegistry.registerModEntity(EntitySpeedBoat.class, "Lava Boat", speedBoatID, this, 8, 1, true);
		
		proxy.registerRenderThings();
	}

	@cpw.mods.fml.common.Mod.PostInit
	public void PostInit(FMLPostInitializationEvent event)
	{
		
	}
	
}