package speedboats;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.packet.Packet250CustomPayload;

public class CommonProxy 
{

	public void registerRenderThings() 
	{
		
	}
	
	public void sendPacket(int i,Entity playerEntity) 
	{
		ByteArrayOutputStream bos = new ByteArrayOutputStream(2);
		DataOutputStream outputStream = new DataOutputStream(bos);
		try 
		{
			outputStream.writeShort(i);
		} 
		catch (Exception ex) 
		{
			ex.printStackTrace();
		}		
		Packet250CustomPayload packet = new Packet250CustomPayload();
		packet.channel = "SpeedBoat";
		packet.data = bos.toByteArray();
		packet.length = bos.size();
		Side side = FMLCommonHandler.instance().getEffectiveSide();
        if (side == Side.SERVER) 
		{
                EntityPlayerMP player = (EntityPlayerMP) playerEntity;
        } 
		else if (side == Side.CLIENT) 
		{
            EntityClientPlayerMP player = (EntityClientPlayerMP) playerEntity;
            player.sendQueue.addToSendQueue(packet);
        }
	}
	
}