package com.andrei1058.skygiants.listeners;

import com.andrei1058.skygiants.Main;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;

public class PrepareItemCraftListener implements Listener {
   @EventHandler
   public void craft(PrepareItemCraftEvent var1) {
      Main var10000 = Main.plugin;
      if (Main.nmsH.getboats().booleanValue()) {
         if (var1.getRecipe().getResult().getType() == Material.BOAT || var1.getRecipe().getResult().getType() == Material.BOAT_ACACIA || var1.getRecipe().getResult().getType() == Material.BOAT_BIRCH || var1.getRecipe().getResult().getType() == Material.BOAT_DARK_OAK || var1.getRecipe().getResult().getType() == Material.BOAT_JUNGLE || var1.getRecipe().getResult().getType() == Material.BOAT_SPRUCE) {
            var1.getInventory().setResult(new ItemStack(Material.AIR));
         }
      } else if (var1.getRecipe().getResult().getType() == Material.BOAT) {
         var1.getInventory().setResult(new ItemStack(Material.AIR));
      }

   }
}
