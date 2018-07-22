package com.andrei1058.skygiants.locations;

import com.andrei1058.skygiants.Main;
import com.andrei1058.skygiants.configuration.Messages;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class Region {
   private Location loc1;
   private Location loc2;
   private static boolean tick = false;
   private static boolean creeper = false;

   public Region(Location var1, Location var2) {
      this.loc1 = var1;
      this.loc2 = var2;
   }

   public boolean isInRegion(Location var1) {
      Location var2 = new Location(this.loc1.getWorld(), this.loc1.getX() > this.loc2.getX() ? this.loc2.getX() : this.loc1.getX(), this.loc1.getY() > this.loc2.getY() ? this.loc2.getY() : this.loc1.getY(), this.loc1.getZ() > this.loc2.getZ() ? this.loc2.getZ() : this.loc1.getZ());
      Location var3 = new Location(this.loc1.getWorld(), this.loc1.getX() > this.loc2.getX() ? this.loc1.getX() : this.loc2.getX(), this.loc1.getY() > this.loc2.getY() ? this.loc1.getY() : this.loc2.getY(), this.loc1.getZ() > this.loc2.getZ() ? this.loc1.getZ() : this.loc2.getZ());
      return var1.getX() <= var3.getX() && var1.getX() >= var2.getX() && var1.getY() <= var3.getY() && var1.getY() >= var2.getY() && var1.getZ() <= var3.getZ() && var1.getZ() >= var2.getZ();
   }

   public static String getRegion(Player var0) {
      String var1 = Messages.getMsg().getString("protected-land").replace('&', '\u00a7');
      String var2 = Messages.getMsg().getString("scared-land").replace('&', '\u00a7');
      String var3 = Messages.getMsg().getString("distructible-land").replace('&', '\u00a7');
      if (Main.BeastRegion.isInRegion(var0.getLocation())) {
         if (Main.beastSpawned.booleanValue()) {
            if (tick) {
               var0.damage(1.0D);
               tick = false;
            } else {
               tick = true;
            }

            if (Main.beastType.equalsIgnoreCase("CENTAUR") && !creeper) {
               creeper = true;
               Creeper var4 = (Creeper)var0.getWorld().spawnEntity(var0.getLocation().add(1.0D, 0.0D, 0.0D), EntityType.CREEPER);
               var4.setPowered(true);
               Bukkit.getScheduler().runTaskLater(Main.plugin, new Runnable() {
                  public void run() {
                     Region.creeper = false;
                  }
               }, 100L);
            }
         }

         return var2;
      } else if (Main.MiddleRegion.isInRegion(var0.getLocation())) {
         return var1;
      } else if (Main.GoldVillagerRegion.isInRegion(var0.getLocation())) {
         return var1;
      } else if (Main.MagentaVillagerRegion.isInRegion(var0.getLocation())) {
         return var1;
      } else if (Main.GreenVillagerRegion.isInRegion(var0.getLocation())) {
         return var1;
      } else if (Main.BlueVillagerRegion.isInRegion(var0.getLocation())) {
         return var1;
      } else if (Main.GoldGiantRegion.isInRegion(var0.getLocation())) {
         return var1;
      } else if (Main.MagentaGiantRegion.isInRegion(var0.getLocation())) {
         return var1;
      } else if (Main.GreenGiantRegion.isInRegion(var0.getLocation())) {
         return var1;
      } else {
         return Main.BlueGiantRegion.isInRegion(var0.getLocation()) ? var1 : var3;
      }
   }
}
