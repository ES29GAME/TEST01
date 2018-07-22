package com.andrei1058.skygiants.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

public class CreatureSpawnListener implements Listener {
   @EventHandler
   public void spawn(CreatureSpawnEvent var1) {
      if (var1.getSpawnReason() != SpawnReason.CUSTOM) {
         var1.setCancelled(true);
      }

   }
}
