package com.andrei1058.skygiants.listeners;

import com.andrei1058.skygiants.Main;
import com.andrei1058.skygiants.configuration.Messages;
import com.andrei1058.skygiants.game.Shop;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class PlayerInteractEntityListener implements Listener {
   @EventHandler
   public void shop(PlayerInteractEntityEvent var1) {
      if (var1.getRightClicked().getType() == EntityType.VILLAGER) {
         Villager var2 = (Villager)var1.getRightClicked();
         if (var2.getCustomName().equalsIgnoreCase(Messages.getMsg().getString("merchant").replace('&', '\u00a7'))) {
            if (Main.spectators.contains(var1.getPlayer())) {
               var1.setCancelled(true);
               return;
            }

            var1.setCancelled(true);
            var1.getPlayer().openInventory(Shop.mainShop());
         }
      }

   }
}
