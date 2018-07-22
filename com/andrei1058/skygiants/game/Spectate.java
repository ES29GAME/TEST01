package com.andrei1058.skygiants.game;

import com.andrei1058.skygiants.Main;
import com.andrei1058.skygiants.configuration.Messages;
import com.andrei1058.skygiants.configuration.Settings;
import java.util.ArrayList;
import java.util.Iterator;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Spectate {
   public static void setSpectator(final Player var0) {
      if (Settings.getCfg().getBoolean("gamemode-spectator")) {
         var0.getInventory().clear();
         var0.getInventory().setArmorContents((ItemStack[])null);
         var0.setGameMode(GameMode.SPECTATOR);
         Main.spectators.add(var0);
         Main.players.remove(var0);
         Main.magentaPlayers.remove(var0);
         Main.goldPlayers.contains(var0);
         Main.bluePlayers.remove(var0);
         Main.greenPlayers.remove(var0);
      } else {
         var0.setLevel(0);
         var0.setGameMode(GameMode.ADVENTURE);
         var0.setAllowFlight(true);
         var0.setFlying(true);
         var0.getInventory().setArmorContents((ItemStack[])null);
         var0.getInventory().clear();
         var0.setHealthScale(20.0D);
         Iterator var1 = Main.players.iterator();

         while(var1.hasNext()) {
            Player var2 = (Player)var1.next();
            var2.hidePlayer(var0);
         }

         Main.spectators.add(var0);
         Main.players.remove(var0);
         Main.magentaPlayers.remove(var0);
         Main.goldPlayers.contains(var0);
         Main.bluePlayers.remove(var0);
         Main.greenPlayers.remove(var0);
         Main.nmsH.transparent(var0);
         Bukkit.getScheduler().runTaskLater(Main.plugin, new Runnable() {
            public void run() {
               var0.teleport(com.andrei1058.skygiants.locations.Spectate.getSpect(Main.choosenMap));
               Spectate.spectateItems(var0);
            }
         }, 2L);
      }

      var0.setScoreboard(Main.sb);
   }

   private static void spectateItems(Player var0) {
      ItemStack var1 = new ItemStack(Material.SLIME_BALL);
      ItemMeta var2 = var1.getItemMeta();
      var2.setDisplayName(Messages.getMsg().getString("back-to-hub.name").replace('&', '\u00a7'));
      ArrayList var3 = new ArrayList();
      Iterator var4 = Messages.getMsg().getStringList("back-to-hub.lore").iterator();

      while(var4.hasNext()) {
         String var5 = (String)var4.next();
         var3.add(var5.replace('&', '\u00a7'));
      }

      var2.setLore(var3);
      var1.setItemMeta(var2);
      var0.getInventory().setItem(8, var1);
      ItemStack var9 = new ItemStack(Material.COMPASS);
      ItemMeta var10 = var9.getItemMeta();
      var10.setDisplayName(Messages.getMsg().getString("spectator-selector.name").replace('&', '\u00a7'));
      ArrayList var6 = new ArrayList();
      Iterator var7 = Messages.getMsg().getStringList("spectator-selector.lore").iterator();

      while(var7.hasNext()) {
         String var8 = (String)var7.next();
         var6.add(var8.replace('&', '\u00a7'));
      }

      var10.setLore(var6);
      var9.setItemMeta(var10);
      var0.getInventory().setItem(0, var9);
   }
}
