package space.wolv.doublejump;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class ToggleCmd implements CommandExecutor
{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) 
	{
		if (!(sender instanceof Player))
		{
			return false;
		}
		Player player = (Player) sender;
		
		if (DoubleJump.doublejumpToggled.containsKey(player.getUniqueId().toString()))
		{
			DoubleJump.doublejumpToggled.put(player.getUniqueId().toString(), !DoubleJump.doublejumpToggled.get(player.getUniqueId().toString()));
		}
		else
		{DoubleJump.doublejumpToggled.put(player.getUniqueId().toString(), false);
		}
		
		if (DoubleJump.doublejumpToggled.get(player.getUniqueId().toString()))
		{
			player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3>> &fDoublejump enabled"));
		}
		else
		{
			player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3>> &fDoublejump disabled"));
		}
		return true;
	}

}
