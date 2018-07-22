package com.andrei1058.skygiants.listeners;

import com.andrei1058.skygiants.Main;
import java.util.Iterator;
import java.util.List;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

public class EntityExplodeListener implements Listener {
   @EventHandler
   public void explode(EntityExplodeEvent var1) {
      List var2 = var1.blockList();
      Iterator var3 = var2.iterator();

      while(var3.hasNext()) {
         Block var4 = (Block)var3.next();
         if (Main.GoldGiantRegion.isInRegion(var4.getLocation())) {
            var1.setCancelled(true);
         } else if (Main.MagentaGiantRegion.isInRegion(var4.getLocation())) {
            var1.setCancelled(true);
         } else if (Main.GreenGiantRegion.isInRegion(var4.getLocation())) {
            var1.setCancelled(true);
         } else if (Main.BlueGiantRegion.isInRegion(var4.getLocation())) {
            var1.setCancelled(true);
         } else if (Main.GoldVillagerRegion.isInRegion(var4.getLocation())) {
            var1.setCancelled(true);
         } else if (Main.MagentaVillagerRegion.isInRegion(var4.getLocation())) {
            var1.setCancelled(true);
         } else if (Main.GreenVillagerRegion.isInRegion(var4.getLocation())) {
            var1.setCancelled(true);
         } else if (Main.BlueVillagerRegion.isInRegion(var4.getLocation())) {
            var1.setCancelled(true);
         } else if (Main.BeastRegion.isInRegion(var4.getLocation())) {
            var1.setCancelled(true);
         } else if (Main.MiddleRegion.isInRegion(var4.getLocation())) {
            var1.setCancelled(true);
         }
      }

   }
}
