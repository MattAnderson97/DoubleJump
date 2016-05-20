package space.wolv.doublejump.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import space.wolv.doublejump.DoubleJump;

public class PlayerMoveListener implements Listener
{
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event)
    {
        Player player = event.getPlayer();
    	if (player.hasPermission("doublejump.jump")){
            if (!DoubleJump.checkBlocks(player))
            {
                return;
            }
            
            if (!DoubleJump.doublejumpToggled.get(player.getUniqueId().toString()))
            {
            	return;
            }
            
            player.setAllowFlight(true);
    	}
    }
}