package speedboats;

import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy
{
	@Override
	public void registerRenderThings()
	{
		RenderingRegistry.registerEntityRenderingHandler(EntitySpeedBoat.class, new RenderSpeedBoat());
		KeyBindingRegistry.registerKeyBinding(new BoatKeyHandler(SpeedBoats.instance.KEY_SPEED, SpeedBoats.instance.KEY_JUMP));
	}
	
}