package com.andrei1058.skygiants.listeners;

import com.andrei1058.skygiants.Main;
import com.andrei1058.skygiants.game.GameState;
import org.bukkit.entity.Giant;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

public class EntityDamageListener implements Listener {
   @EventHandler
   public void damage(EntityDamageEvent var1) {
      if (!Main.MAINTENANCE.booleanValue()) {
         if (Main.STATUS != GameState.PLAYING) {
            var1.setCancelled(true);
         }

         if (Main.spectators.contains(var1.getEntity())) {
            var1.setCancelled(true);
         }

         if (var1.getEntity() instanceof Giant && var1.getCause() != DamageCause.PROJECTILE && var1.getCause() != DamageCause.ENTITY_ATTACK) {
            var1.setCancelled(true);
         }

      }
   }
}
