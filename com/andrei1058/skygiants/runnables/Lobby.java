package com.andrei1058.skygiants.runnables;

import com.andrei1058.skygiants.Main;
import com.andrei1058.skygiants.configuration.Messages;
import com.andrei1058.skygiants.game.GameState;
import com.andrei1058.skygiants.game.RandomTeam;
import com.andrei1058.skygiants.game.Scoreboard;
import com.andrei1058.skygiants.locations.Beasts;
import com.andrei1058.skygiants.locations.Giants;
import com.andrei1058.skygiants.locations.Middle;
import com.andrei1058.skygiants.locations.Spawns;
import com.andrei1058.skygiants.locations.Villagers;
import com.andrei1058.skygiants.protocolLib.Borders;
import com.andrei1058.skygiants.utils.Titles;
import java.util.Iterator;
import java.util.Map.Entry;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.WorldCreator;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class Lobby extends BukkitRunnable {
   private static Boolean voteAnnounced = false;

   public void run() {
      if (Main.LobbyCountdown != 0) {
         --Main.LobbyCountdown;
      }

      Iterator var1 = Bukkit.getOnlinePlayers().iterator();

      Player var2;
      while(var1.hasNext()) {
         var2 = (Player)var1.next();
         var2.setLevel(Main.LobbyCountdown);
      }

      if (Main.LobbyCountdown == 10) {
         Entry var8 = null;
         Iterator var9 = Main.mapsVoting.entrySet().iterator();

         label77:
         while(true) {
            Entry var3;
            do {
               if (!var9.hasNext()) {
                  Bukkit.getServer().createWorld(new WorldCreator(Main.choosenMap));
                  Bukkit.getWorld(Main.choosenMap).setAutoSave(false);
                  Bukkit.getWorld(Main.choosenMap).setGameRuleValue("keepInventory", "true");
                  Bukkit.broadcastMessage(Main.PREFIX + Messages.getMsg().getString("voting-ended").replace("{choosenMap}", Main.choosenMap).replace('&', '\u00a7'));
                  var9 = Bukkit.getWorld(Main.choosenMap).getEntities().iterator();

                  while(var9.hasNext()) {
                     Entity var10 = (Entity)var9.next();
                     if (var10.getType() != EntityType.PLAYER) {
                        var10.remove();
                     }
                  }
                  break label77;
               }

               var3 = (Entry)var9.next();
            } while(var8 != null && ((Integer)var3.getValue()).intValue() <= ((Integer)var8.getValue()).intValue());

            var8 = var3;
            Main.choosenMap = (String)var3.getKey();
         }
      }

      if (Main.LobbyCountdown == 0) {
         this.cancel();
         var1 = Bukkit.getOnlinePlayers().iterator();

         while(var1.hasNext()) {
            var2 = (Player)var1.next();
            RandomTeam.randomTeam(var2);
            if (Main.spectators.contains(var2)) {
               return;
            }

            if (Main.magentaPlayers.contains(var2)) {
               var2.teleport(Spawns.getSpawn("Magenta"));
               var2.sendMessage(Main.PREFIX + Messages.getMsg().getString("play-magenta").replace('&', '\u00a7'));
               var2.setDisplayName("\u00a7d" + var2.getName());
               Titles.sendTitle(var2, Integer.valueOf(0), Integer.valueOf(20), Integer.valueOf(0), " ", Messages.getMsg().getString("title-play-magenta").replace('&', '\u00a7'));
               Borders.magentaBorders(var2);
            } else if (Main.goldPlayers.contains(var2)) {
               var2.teleport(Spawns.getSpawn("Gold"));
               var2.sendMessage(Main.PREFIX + Messages.getMsg().getString("play-gold").replace('&', '\u00a7'));
               var2.setDisplayName("\u00a76" + var2.getName());
               Titles.sendTitle(var2, Integer.valueOf(0), Integer.valueOf(20), Integer.valueOf(0), " ", Messages.getMsg().getString("title-play-gold").replace('&', '\u00a7'));
               Borders.goldBorders(var2);
            } else if (Main.greenPlayers.contains(var2)) {
               var2.teleport(Spawns.getSpawn("Green"));
               var2.sendMessage(Main.PREFIX + Messages.getMsg().getString("play-green").replace('&', '\u00a7'));
               var2.setDisplayName("\u00a7a" + var2.getName());
               Titles.sendTitle(var2, Integer.valueOf(0), Integer.valueOf(20), Integer.valueOf(0), " ", Messages.getMsg().getString("title-play-green").replace('&', '\u00a7'));
               Borders.greenBorders(var2);
            } else if (Main.bluePlayers.contains(var2)) {
               var2.teleport(Spawns.getSpawn("Blue"));
               var2.sendMessage(Main.PREFIX + Messages.getMsg().getString("play-blue").replace('&', '\u00a7'));
               var2.setDisplayName("\u00a79" + var2.getName());
               Titles.sendTitle(var2, Integer.valueOf(0), Integer.valueOf(20), Integer.valueOf(0), " ", Messages.getMsg().getString("title-play-blue").replace('&', '\u00a7'));
               Borders.blueBorders(var2);
            }

            var2.setGameMode(GameMode.SURVIVAL);
            var2.getInventory().clear();
            var2.getInventory().setHelmet(new ItemStack(Material.CHAINMAIL_HELMET));
            var2.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
            var2.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
            var2.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));
            var2.getInventory().addItem(new ItemStack[]{new ItemStack(Material.STONE_SWORD)});
            ItemStack var11 = new ItemStack(Material.IRON_PICKAXE);
            var11.addEnchantment(Enchantment.DIG_SPEED, 1);
            var2.getInventory().addItem(new ItemStack[]{var11});
            ItemStack var4 = new ItemStack(Material.STONE_AXE);
            var4.addEnchantment(Enchantment.DIG_SPEED, 1);
            var2.getInventory().addItem(new ItemStack[]{var4});
            ItemStack var5 = new ItemStack(Material.STONE_SPADE);
            var5.addEnchantment(Enchantment.DIG_SPEED, 1);
            var2.getInventory().addItem(new ItemStack[]{var5});
            var2.setHealth(20.0D);
            var2.setFoodLevel(20);
            Iterator var6 = Messages.getMsg().getStringList("your-objective").iterator();

            while(var6.hasNext()) {
               String var7 = (String)var6.next();
               var2.sendMessage(Main.PREFIX + var7.replace('&', '\u00a7'));
            }
         }

         Middle.loadMidRegion();
         Beasts.loadBeastRegion();
         Giants.loadGiantsRegions();
         Villagers.loadVillagersRegions();
         Main.nmsH.spawnGiants();
         Main.nmsH.spawnVillagers();
         Scoreboard.sendScoreboard();
         (new Warmup()).runTaskTimer(Main.plugin, 20L, 20L);
         Main.STATUS = GameState.PLAYING;
      }

   }
}
