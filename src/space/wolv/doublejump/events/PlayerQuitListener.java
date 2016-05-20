package space.wolv.doublejump.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import space.wolv.doublejump.DoubleJump;

public class PlayerQuitListener implements Listener
{
	DoubleJump plugin;
	
	public PlayerQuitListener(DoubleJump plugin)
	{
		this.plugin = plugin;
	}
	
	@EventHandler
	public void playerQuitEvent(PlayerQuitEvent event)
	{
		Player player = event.getPlayer();
		
		plugin.savePlayerData(player);
	}
}
