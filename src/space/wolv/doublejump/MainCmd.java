package space.wolv.doublejump;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class MainCmd implements CommandExecutor
{
	DoubleJump plugin;
	
	public MainCmd(DoubleJump plugin){
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		plugin.reloadConfig();
		
		if (!(sender instanceof Player))
		{   
			Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&fIgnis &3>&b> &7Double jump - Config reloaded"));
		}
		
		sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&fIgnis &3>&b> &7Double jump - config reloaded"));
		
		return true;
	}
}