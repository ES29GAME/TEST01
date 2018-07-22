package com.andrei1058.skygiants.listeners;

import com.andrei1058.skygiants.Main;
import com.andrei1058.skygiants.configuration.Messages;
import com.andrei1058.skygiants.game.GameState;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlaceListener implements Listener {
   @EventHandler
   public void place(BlockPlaceEvent var1) {
      if (!Main.MAINTENANCE.booleanValue()) {
         if (Main.STATUS != GameState.PLAYING && !var1.getPlayer().isOp()) {
            var1.setCancelled(true);
         }

         if (Main.respawning.contains(var1.getPlayer())) {
            var1.setCancelled(true);
         } else {
            if (var1.getItemInHand().getType() == Material.SKULL_ITEM) {
               if (var1.getItemInHand().getItemMeta().getDisplayName() == null) {
                  return;
               }

               if (var1.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(Messages.getMsg().getString("shop.respawn-beacon"))) {
                  if (Main.respBeac.containsValue(var1.getPlayer())) {
                     var1.setCancelled(true);
                  } else {
                     Main.respBeac.put(var1.getBlock().getLocation(), var1.getPlayer());
                     Main.respOwn.put(var1.getPlayer(), var1.getBlock().getLocation());
                  }
               }
            }

            if (Main.GoldGiantRegion.isInRegion(var1.getBlock().getLocation()) || Main.MagentaGiantRegion.isInRegion(var1.getBlock().getLocation()) || Main.GreenGiantRegion.isInRegion(var1.getBlock().getLocation()) || Main.BlueGiantRegion.isInRegion(var1.getBlock().getLocation()) || Main.GoldVillagerRegion.isInRegion(var1.getBlock().getLocation()) || Main.MagentaVillagerRegion.isInRegion(var1.getBlock().getLocation()) || Main.GreenVillagerRegion.isInRegion(var1.getBlock().getLocation()) || Main.BlueVillagerRegion.isInRegion(var1.getBlock().getLocation()) || Main.MiddleRegion.isInRegion(var1.getBlock().getLocation())) {
               Main.PlacedBlocks.add(var1.getBlock().getLocation());
            }

            if (Main.BeastRegion.isInRegion(var1.getBlock().getLocation())) {
               var1.setCancelled(true);
               var1.getPlayer().sendMessage(Main.PREFIX + Messages.getMsg().getString("scared-region").replace('&', '\u00a7'));
            }

            if (var1.getBlock().getType() == Material.TNT) {
               var1.getBlock().getWorld().spawnEntity(var1.getBlock().getLocation(), EntityType.PRIMED_TNT);
               var1.getBlock().setType(Material.AIR);
            }

         }
      }
   }
}
