package speedboats;

import java.util.EnumSet;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.client.registry.KeyBindingRegistry.KeyHandler;
import cpw.mods.fml.common.TickType;

public class BoatKeyHandler extends KeyHandler 
{
	
	public BoatKeyHandler(int KEY_SPEED, int JUMP_KEY)
    {
		super(new KeyBinding[]{new KeyBinding("GoFaster", KEY_SPEED), new KeyBinding("Jumping", JUMP_KEY)}, new boolean[]{false,false});
    }
    
    @Override
    public void keyDown(EnumSet<TickType> es, KeyBinding kb, boolean bln, boolean bln1) 
	{
		Minecraft client = Minecraft.getMinecraft();
		if (client != null && client.thePlayer != null)
		{
		Entity ent=client.thePlayer.ridingEntity;
		if (ent!=null && ent instanceof EntitySpeedBoat)
		{
		}
		
		else if (kb.keyDescription=="GoFaster" && ((EntitySpeedBoat)ent).isGoingFast)
		{	
			SpeedBoats.instance.proxy.sendPacket(1,ent.riddenByEntity);	
		}
		else if (kb.keyDescription=="Jumping")
		{
			SpeedBoats.instance.proxy.sendPacket(2,ent.riddenByEntity);
		}
		}
	}
    @Override
    public void keyUp(EnumSet<TickType> es, KeyBinding kb, boolean bln) 
	{
		Minecraft client = Minecraft.getMinecraft();
		if (client != null && client.thePlayer != null)
		{
		Entity ent=client.thePlayer.ridingEntity;
		if (ent!=null && ent instanceof EntitySpeedBoat)
		{
		if (kb.keyDescription=="GoFaster")
		{	
			SpeedBoats.instance.proxy.sendPacket(3,ent.riddenByEntity);	
		}
		else if (kb.keyDescription=="Jumping")
		{
			SpeedBoats.instance.proxy.sendPacket(4,ent.riddenByEntity);
		}
		}
		}
    }
    @Override
    public EnumSet<TickType> ticks() 
	{
		return EnumSet.of(TickType.CLIENT);
    }
    @Override
    public String getLabel() 
	{
		return "Boat KeyHandler";
    }  
}