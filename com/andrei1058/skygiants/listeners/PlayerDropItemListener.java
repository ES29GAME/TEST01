package com.andrei1058.skygiants.listeners;

import com.andrei1058.skygiants.Main;
import com.andrei1058.skygiants.game.GameState;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class PlayerDropItemListener implements Listener {
   @EventHandler
   public void drop(PlayerDropItemEvent var1) {
      if (Main.spectators.contains(var1.getPlayer())) {
         var1.setCancelled(true);
      }

      if (Main.STATUS == GameState.STARTING || Main.STATUS == GameState.LOBBY) {
         var1.setCancelled(true);
      }

   }
}
