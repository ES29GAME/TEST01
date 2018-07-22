package com.andrei1058.skygiants.listeners;

import com.andrei1058.skygiants.Main;
import com.andrei1058.skygiants.commands.Team;
import com.andrei1058.skygiants.configuration.Messages;
import com.andrei1058.skygiants.game.GameState;
import com.andrei1058.skygiants.game.Winner;
import com.andrei1058.skygiants.utils.Database;
import java.util.Iterator;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {
   @EventHandler
   public void quit(PlayerQuitEvent var1) {
      var1.setQuitMessage((String)null);
      Player var2 = var1.getPlayer();
      if (Main.players.contains(var2)) {
         Main.players.remove(var2);
      }

      if (Main.STATUS == GameState.RESTARTING || Main.STATUS == GameState.PLAYING) {
         if (Main.spectators.contains(var2)) {
            Main.spectators.remove(var2);
         }

         Iterator var3;
         Player var4;
         if (Main.magentaPlayers.contains(var2)) {
            Main.magentaPlayers.remove(var2);
            if (Main.magentaPlayers.isEmpty()) {
               Main.aliveTeams.remove("MAGENTA");
               Main.nmsH.killGiant("Magenta");
            } else {
               var3 = Main.magentaPlayers.iterator();

               while(var3.hasNext()) {
                  var4 = (Player)var3.next();
                  var4.sendMessage(Main.PREFIX + Messages.getMsg().getString("teammate-quit").replace("{name}", var2.getName()).replace('&', '\u00a7'));
               }
            }
         }

         if (Main.goldPlayers.contains(var2)) {
            Main.goldPlayers.remove(var2);
            if (Main.goldPlayers.isEmpty()) {
               Main.aliveTeams.remove("GOLD");
               Main.nmsH.killGiant("Gold");
            } else {
               var3 = Main.goldPlayers.iterator();

               while(var3.hasNext()) {
                  var4 = (Player)var3.next();
                  var4.sendMessage(Main.PREFIX + Messages.getMsg().getString("teammate-quit").replace("{name}", var2.getName()).replace('&', '\u00a7'));
               }
            }
         }

         if (Main.greenPlayers.contains(var2)) {
            Main.greenPlayers.remove(var2);
            if (Main.greenPlayers.isEmpty()) {
               Main.aliveTeams.remove("GREEN");
               Main.nmsH.killGiant("Green");
            } else {
               var3 = Main.greenPlayers.iterator();

               while(var3.hasNext()) {
                  var4 = (Player)var3.next();
                  var4.sendMessage(Main.PREFIX + Messages.getMsg().getString("teammate-quit").replace("{name}", var2.getName()).replace('&', '\u00a7'));
               }
            }
         }

         if (Main.bluePlayers.contains(var2)) {
            Main.bluePlayers.remove(var2);
            if (Main.bluePlayers.isEmpty()) {
               Main.aliveTeams.remove("BLUE");
               Main.nmsH.killGiant("Blue");
            } else {
               var3 = Main.bluePlayers.iterator();

               while(var3.hasNext()) {
                  var4 = (Player)var3.next();
                  var4.sendMessage(Main.PREFIX + Messages.getMsg().getString("teammate-quit").replace("{name}", var2.getName()).replace('&', '\u00a7'));
               }
            }
         }
      }

      if (Main.STATUS == GameState.STARTING) {
         Bukkit.getScheduler().runTaskLater(Main.plugin, new Runnable() {
            public void run() {
               if (Main.miniSG.booleanValue()) {
                  if (Bukkit.getOnlinePlayers().size() < Main.MaxInTeam * 2) {
                     Main.plugin.getServer().getScheduler().cancelTasks(Main.plugin);
                     Main.LobbyCountdown = 300;
                     Main.STATUS = GameState.LOBBY;
                  }
               } else if (Bukkit.getOnlinePlayers().size() < Main.MaxInTeam) {
                  Main.plugin.getServer().getScheduler().cancelTasks(Main.plugin);
                  Main.LobbyCountdown = 300;
                  Main.STATUS = GameState.LOBBY;
               }

            }
         }, 5L);
      }

      if (Main.STATUS == GameState.PLAYING) {
         Bukkit.getScheduler().runTaskLater(Main.plugin, new Runnable() {
            public void run() {
               Winner.getWinner();
            }
         }, 10L);
         Main.gamesplayed.add(var2);
         Database.saveStats(var2);
      }

      if (Main.money.containsKey(var2)) {
         Main.money.remove(var2);
      }

      if (Main.kills.containsKey(var2)) {
         Main.kills.remove(var2);
      }

      if (Main.beastsSlain.containsKey(var2)) {
         Main.beastsSlain.remove(var2);
      }

      if (Main.deaths.containsKey(var2)) {
         Main.deaths.remove(var2);
      }

      if (Main.goldearnt.containsKey(var2)) {
         Main.goldearnt.remove(var2);
      }

      if (Main.gamesplayed.contains(var2)) {
         Main.gamesplayed.remove(var2);
      }

      if (Main.victories.contains(var2)) {
         Main.victories.remove(var2);
      }

      if (Main.lookingAtShop.contains(var2)) {
         Main.lookingAtShop.remove(var2);
      }

      if (Main.recall.contains(var2)) {
         Main.recall.remove(var2);
      }

      if (Main.STATUS == GameState.STARTING || Main.STATUS == GameState.LOBBY) {
         Team.hasLeft(var2);
         if (Main.goldPlayers.contains(var2)) {
            Main.goldPlayers.remove(var2);
         }

         if (Main.magentaPlayers.contains(var2)) {
            Main.magentaPlayers.remove(var2);
         }

         if (Main.greenPlayers.contains(var2)) {
            Main.greenPlayers.remove(var2);
         }

         if (Main.bluePlayers.contains(var2)) {
            Main.bluePlayers.remove(var2);
         }
      }

   }
}
