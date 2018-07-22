package com.andrei1058.skygiants.game;

import com.andrei1058.skygiants.Main;
import com.andrei1058.skygiants.configuration.Messages;
import com.andrei1058.skygiants.runnables.Restarting;
import com.andrei1058.skygiants.utils.Titles;
import java.util.Iterator;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Winner {
   public static void getWinner() {
      if (Main.STATUS != GameState.RESTARTING) {
         Iterator var0;
         Player var1;
         if (!Main.aliveTeams.contains("BLUE") && !Main.aliveTeams.contains("MAGENTA") && !Main.aliveTeams.contains("GREEN") && !Main.WINNER.booleanValue()) {
            Main.plugin.getServer().getScheduler().cancelAllTasks();
            (new Restarting()).runTaskTimer(Main.plugin, 20L, 20L);
            var0 = Bukkit.getOnlinePlayers().iterator();

            while(var0.hasNext()) {
               var1 = (Player)var0.next();
               Titles.sendTitle(var1, Integer.valueOf(0), Integer.valueOf(40), Integer.valueOf(0), Messages.getMsg().getString("gold-team").replace('&', '\u00a7'), Messages.getMsg().getString("champions").replace('&', '\u00a7'));
            }

            Main.WINNER = true;
         } else if (!Main.aliveTeams.contains("GOLD") && !Main.aliveTeams.contains("MAGENTA") && !Main.aliveTeams.contains("BLUE") && !Main.WINNER.booleanValue()) {
            Main.plugin.getServer().getScheduler().cancelAllTasks();
            var0 = Bukkit.getOnlinePlayers().iterator();

            while(var0.hasNext()) {
               var1 = (Player)var0.next();
               Titles.sendTitle(var1, Integer.valueOf(0), Integer.valueOf(40), Integer.valueOf(0), Messages.getMsg().getString("green-team").replace('&', '\u00a7'), Messages.getMsg().getString("champions").replace('&', '\u00a7'));
            }

            (new Restarting()).runTaskTimer(Main.plugin, 20L, 20L);
            Main.WINNER = true;
         } else if (!Main.aliveTeams.contains("GOLD") && !Main.aliveTeams.contains("BLUE") && !Main.aliveTeams.contains("GREEN") && !Main.WINNER.booleanValue()) {
            Main.plugin.getServer().getScheduler().cancelAllTasks();
            (new Restarting()).runTaskTimer(Main.plugin, 20L, 20L);
            var0 = Bukkit.getOnlinePlayers().iterator();

            while(var0.hasNext()) {
               var1 = (Player)var0.next();
               Titles.sendTitle(var1, Integer.valueOf(0), Integer.valueOf(40), Integer.valueOf(0), Messages.getMsg().getString("magenta-team").replace('&', '\u00a7'), Messages.getMsg().getString("champions").replace('&', '\u00a7'));
            }

            Main.WINNER = true;
         } else if (!Main.aliveTeams.contains("GOLD") && !Main.aliveTeams.contains("MAGENTA") && !Main.aliveTeams.contains("GREEN") && !Main.WINNER.booleanValue()) {
            Main.plugin.getServer().getScheduler().cancelAllTasks();
            (new Restarting()).runTaskTimer(Main.plugin, 20L, 20L);
            var0 = Bukkit.getOnlinePlayers().iterator();

            while(var0.hasNext()) {
               var1 = (Player)var0.next();
               Titles.sendTitle(var1, Integer.valueOf(0), Integer.valueOf(40), Integer.valueOf(0), Messages.getMsg().getString("blue-team").replace('&', '\u00a7'), Messages.getMsg().getString("champions").replace('&', '\u00a7'));
            }

            Main.WINNER = true;
         } else if (Main.players.isEmpty()) {
            Main.plugin.getServer().getScheduler().cancelAllTasks();
            (new Restarting()).runTaskTimer(Main.plugin, 20L, 20L);
         } else {
            if (Main.GameCountdown == 0) {
               if (Main.nmsH.getGiantHealthInt("Gold") > Main.nmsH.getGiantHealthInt("Magenta") && Main.nmsH.getGiantHealthInt("Gold") > Main.nmsH.getGiantHealthInt("Green") && Main.nmsH.getGiantHealthInt("Gold") > Main.nmsH.getGiantHealthInt("Blue")) {
                  Main.plugin.getServer().getScheduler().cancelAllTasks();
                  (new Restarting()).runTaskTimer(Main.plugin, 20L, 20L);
                  var0 = Bukkit.getOnlinePlayers().iterator();

                  while(var0.hasNext()) {
                     var1 = (Player)var0.next();
                     Titles.sendTitle(var1, Integer.valueOf(0), Integer.valueOf(40), Integer.valueOf(0), Messages.getMsg().getString("gold-team").replace('&', '\u00a7'), Messages.getMsg().getString("champion").replace('&', '\u00a7'));
                  }

                  Main.WINNER = true;
               } else if (Main.nmsH.getGiantHealthInt("Magenta").intValue() > Main.nmsH.getGiantHealthInt("Gold").intValue() && Main.nmsH.getGiantHealthInt("Magenta").intValue() > Main.nmsH.getGiantHealthInt("Green").intValue() && Main.nmsH.getGiantHealthInt("Magenta").intValue() > Main.nmsH.getGiantHealthInt("Blue").intValue()) {
                  Main.plugin.getServer().getScheduler().cancelAllTasks();
                  (new Restarting()).runTaskTimer(Main.plugin, 20L, 20L);
                  var0 = Bukkit.getOnlinePlayers().iterator();

                  while(var0.hasNext()) {
                     var1 = (Player)var0.next();
                     Titles.sendTitle(var1, Integer.valueOf(0), Integer.valueOf(40), Integer.valueOf(0), Messages.getMsg().getString("magenta-team").replace('&', '\u00a7'), Messages.getMsg().getString("champion").replace('&', '\u00a7'));
                  }

                  Main.WINNER = true;
               } else if (Main.nmsH.getGiantHealthInt("Green") > Main.nmsH.getGiantHealthInt("Gold") && Main.nmsH.getGiantHealthInt("Green") > Main.nmsH.getGiantHealthInt("Magenta") && Main.nmsH.getGiantHealthInt("Green") > Main.nmsH.getGiantHealthInt("Blue")) {
                  Main.plugin.getServer().getScheduler().cancelAllTasks();
                  (new Restarting()).runTaskTimer(Main.plugin, 20L, 20L);
                  var0 = Bukkit.getOnlinePlayers().iterator();

                  while(var0.hasNext()) {
                     var1 = (Player)var0.next();
                     Titles.sendTitle(var1, Integer.valueOf(0), Integer.valueOf(40), Integer.valueOf(0), Messages.getMsg().getString("green-team").replace('&', '\u00a7'), Messages.getMsg().getString("champion").replace('&', '\u00a7'));
                  }

                  Main.WINNER = true;
               } else if (Main.nmsH.getGiantHealthInt("Blue") > Main.nmsH.getGiantHealthInt("Gold") && Main.nmsH.getGiantHealthInt("Blue") > Main.nmsH.getGiantHealthInt("Magenta") && Main.nmsH.getGiantHealthInt("Blue") > Main.nmsH.getGiantHealthInt("Green")) {
                  Main.plugin.getServer().getScheduler().cancelAllTasks();
                  (new Restarting()).runTaskTimer(Main.plugin, 20L, 20L);
                  var0 = Bukkit.getOnlinePlayers().iterator();

                  while(var0.hasNext()) {
                     var1 = (Player)var0.next();
                     Titles.sendTitle(var1, Integer.valueOf(0), Integer.valueOf(40), Integer.valueOf(0), Messages.getMsg().getString("blue-team").replace('&', '\u00a7'), Messages.getMsg().getString("champion").replace('&', '\u00a7'));
                  }

                  Main.WINNER = true;
               } else {
                  Main.plugin.getServer().getScheduler().cancelAllTasks();
                  (new Restarting()).runTaskTimer(Main.plugin, 20L, 20L);
               }
            }

         }
      }
   }
}
