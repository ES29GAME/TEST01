package com.andrei1058.skygiants.listeners;

import com.andrei1058.skygiants.Main;
import com.andrei1058.skygiants.locations.Spectate;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class PlayerRespawnListener implements Listener {
   @EventHandler
   public void respawn(PlayerRespawnEvent var1) {
      if (!Main.MAINTENANCE.booleanValue()) {
         if (Main.spectators.contains(var1.getPlayer())) {
            var1.getPlayer().teleport(Spectate.getSpect(Main.choosenMap));
         } else {
            var1.setRespawnLocation(Spectate.getSpect(Main.choosenMap));
            var1.getPlayer().setAllowFlight(true);
            var1.getPlayer().setFlying(true);
         }
      }
   }
}
