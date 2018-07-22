package com.andrei1058.skygiants.listeners;

import com.andrei1058.skygiants.Main;
import com.andrei1058.skygiants.game.GameState;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class FoodLevelChangeListener implements Listener {
   @EventHandler
   public void food(FoodLevelChangeEvent var1) {
      if (Main.STATUS != GameState.PLAYING) {
         var1.setCancelled(true);
      }

      if (Main.spectators.contains(var1.getEntity())) {
         var1.setCancelled(true);
      }

   }
}
