package space.wolv.doublejump;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import space.wolv.doublejump.events.PlayerFlightListener;
import space.wolv.doublejump.events.PlayerJoinListener;
import space.wolv.doublejump.events.PlayerMoveListener;

public class DoubleJump extends JavaPlugin
{	
	public static String nmsver;
	
	@Override
	public void onEnable()
    {
		nmsver = Bukkit.getServer().getClass().getPackage().getName();
		nmsver = nmsver.substring(nmsver.lastIndexOf(".") + 1);
		
		Bukkit.getPluginManager().registerEvents(new PlayerFlightListener(this), this);
		Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerMoveListener(), this);
		
		this.getCommand("doublejump").setExecutor(new MainCmd(this));
		
		checkConfig();
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
