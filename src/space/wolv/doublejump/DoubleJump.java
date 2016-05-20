package space.wolv.doublejump;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import space.wolv.doublejump.events.*;

public class DoubleJump extends JavaPlugin
{	
	public static String nmsver;
	public static HashMap<String, Boolean> doublejumpToggled;
	
	@Override
	public void onEnable()
    {
		nmsver = Bukkit.getServer().getClass().getPackage().getName();
		nmsver = nmsver.substring(nmsver.lastIndexOf(".") + 1);
		
		doublejumpToggled = new HashMap<String, Boolean>();
		
		Bukkit.getPluginManager().registerEvents(new PlayerFlightListener(this), this);
		Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(this), this);
		Bukkit.getPluginManager().registerEvents(new PlayerQuitListener(this), this);
		Bukkit.getPluginManager().registerEvents(new PlayerMoveListener(), this);
		
		this.getCommand("doublejump").setExecutor(new MainCmd(this));
		this.getCommand("toggledoublejump").setExecutor(new ToggleCmd());
		
		checkConfig();
		checkPlayerData();

		
		for (Player player: Bukkit.getServer().getOnlinePlayers())
		{
			getPlayerData(player);
		}
    }
	
	public void onDisable()
	{
		for (Player player: Bukkit.getServer().getOnlinePlayers())
		{
			savePlayerData(player);
		}
	}
        
    private void checkPlayerData() {
		File playerStorage = new File(getDataFolder(), "playerData.yml");
		if (!playerStorage.exists())
		{
			playerStorage.getParentFile().mkdirs();
			try 
			{
				playerStorage.createNewFile();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
	}

	public void getPlayerData(Player player)
    {		
		player.sendMessage("loading data");
		FileConfiguration data = YamlConfiguration.loadConfiguration(new File(getDataFolder(), "playerData.yml"));
		Boolean isToggled;
		
		if (data.contains(player.getUniqueId().toString() + ".toggled"))
		{
			isToggled = data.getBoolean(player.getUniqueId().toString() + ".toggled");
		}
		else
		{
			isToggled = true;
		}

		doublejumpToggled.put(player.getUniqueId().toString(), isToggled);
		if (!isToggled)
		{
			player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3>> &fDouble jump is currently disabled"));
		}
		
	}
	
	public void savePlayerData(Player player)
	{
		FileConfiguration data = YamlConfiguration.loadConfiguration(new File(getDataFolder(), "playerData.yml"));
		
		if (doublejumpToggled.containsKey(player.getUniqueId().toString()))
		{
			Boolean isToggled = doublejumpToggled.get(player.getUniqueId().toString());
			data.set(player.getUniqueId().toString() + ".toggled", isToggled);
			try {
				data.save(new File(getDataFolder(), "playerData.yml"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			doublejumpToggled.remove(player.getUniqueId().toString());
		}
	}

	private void checkConfig() 
    {
    	File dataFile = new File(this.getDataFolder() + File.separator + "config.yml");
    	if (!dataFile.exists())
    	{
    		this.saveDefaultConfig();
    	}
    	
    	this.getConfig();
	}

	public static boolean checkBlocks(Player player)
    {
        Location playerLoc = player.getLocation();
        playerLoc = playerLoc.subtract(0,1,0);
        
        Block block = playerLoc.getBlock();
        
        if (!block.getType().isSolid())
        {
            return false;
        }
        
        return true;
    }
}
