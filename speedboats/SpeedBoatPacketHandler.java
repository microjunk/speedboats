package speedboats;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class SpeedBoatPacketHandler implements IPacketHandler
{

	@Override
    public void onPacketData(INetworkManager manager, Packet250CustomPayload payload, Player player)
    {
		if (payload.channel.equals("SpeedBoat")) 
		{
            this.handle(payload,player);
		}
    }
	private void handle(Packet250CustomPayload payload, Player player) 
	{
		DataInputStream inStream = new DataInputStream(new ByteArrayInputStream(payload.data));
		short data;
		try 
		{
			data = inStream.readShort();
		} 
		catch (IOException e) 
		{
            e.printStackTrace();
            return;
		}
		Entity ent = ((EntityPlayer)player).ridingEntity;
		if (ent!=null && ent instanceof EntitySpeedBoat)
		{	
			switch(data)
			{
				case 1: ((EntitySpeedBoat)ent).isGoingFast = true;break;
				case 2: ((EntitySpeedBoat)ent).isJumping = true;break;	
				case 3: ((EntitySpeedBoat)ent).isGoingFast = false;break;
				case 4: ((EntitySpeedBoat)ent).isJumping = false;break;
			}	
		}
	}

}
