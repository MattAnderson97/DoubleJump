package space.wolv.doublejump.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import space.wolv.doublejump.DoubleJump;

public class PlayerJoinListener implements Listener
{
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event)
	{
		Player player = event.getPlayer();
		
		if (player.hasPermission("doublejump.jump"))
		{
            if (!DoubleJump.checkBlocks(player) || player.getAllowFlight())
            {
                return;
            }
            
            player.setAllowFlight(true);
		}
	}
}
