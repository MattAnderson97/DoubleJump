package space.wolv.doublejump.events;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleFlightEvent;

import net.md_5.bungee.api.ChatColor;
import space.wolv.doublejump.DoubleJump;
import space.wolv.doublejump.ActionBar.ActionBarAPI;

public class PlayerFlightListener implements Listener
{
	DoubleJump plugin;
	ActionBarAPI actionBar;
	
	public PlayerFlightListener(DoubleJump plugin){
		this.plugin = plugin;
		actionBar = new ActionBarAPI(plugin);
	}
	
	@EventHandler
	public void onFlight(PlayerToggleFlightEvent event)
	{
		Player player = event.getPlayer();
		
		if (player.hasPermission("doublejump.jump"))
		{
			if (DoubleJump.doublejumpToggled.containsKey(player.getUniqueId().toString()))
			{
				if (DoubleJump.doublejumpToggled.get(player.getUniqueId().toString()))
				{
					if (!(player.getGameMode() == GameMode.SURVIVAL) || !(player.getWorld() == Bukkit.getServer().getWorld("world") || player.getWorld() == Bukkit.getWorld("world_nether") || player.getWorld() == Bukkit.getWorld("world_the_end")))
					{
						return;
					}
					
					event.setCancelled(true);
					event.getPlayer().setAllowFlight(false);
					event.getPlayer().setVelocity(event.getPlayer().getLocation().getDirection().multiply(1.6d).setY(1.0d));
					
					actionBar.sendActionBar(player, ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("doublejump.message")));
				}
				
			}
		}
	}
}