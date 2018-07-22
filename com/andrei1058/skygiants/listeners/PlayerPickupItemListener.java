package com.andrei1058.skygiants.listeners;

import com.andrei1058.skygiants.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class PlayerPickupItemListener implements Listener {
   @EventHandler
   public void pickup(PlayerPickupItemEvent var1) {
      if (Main.spectators.contains(var1.getPlayer())) {
         var1.setCancelled(true);
      }

   }
}
