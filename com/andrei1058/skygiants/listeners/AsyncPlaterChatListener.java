package com.andrei1058.skygiants.listeners;

import com.andrei1058.skygiants.Main;
import com.andrei1058.skygiants.configuration.Messages;
import com.andrei1058.skygiants.game.GameState;
import java.util.Iterator;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class AsyncPlaterChatListener implements Listener {
   @EventHandler
   public void chat(AsyncPlayerChatEvent var1) {
      if (!Main.MAINTENANCE.booleanValue()) {
         var1.setCancelled(true);
         if (Main.STATUS == GameState.PLAYING) {
            Iterator var2;
            Player var3;
            if (Main.players.contains(var1.getPlayer())) {
               if (var1.getMessage().startsWith("!")) {
                  Bukkit.broadcastMessage(Messages.getMsg().getString("chat.shout").replace("{player}", var1.getPlayer().getName()).replace("{message}", var1.getMessage()).replaceFirst("!", "").replace('&', '\u00a7'));
               } else if (Main.goldPlayers.contains(var1.getPlayer())) {
                  var2 = Main.goldPlayers.iterator();

                  while(var2.hasNext()) {
                     var3 = (Player)var2.next();
                     var3.sendMessage(Messages.getMsg().getString("chat.gold").replace("{player}", var1.getPlayer().getName()).replace("{message}", var1.getMessage()).replace('&', '\u00a7').replace("{prefix}", getPrefix(var1.getPlayer())).replace("{suffix}", getSuffix(var1.getPlayer())));
                  }

                  Bukkit.getLogger().info("GOLD | " + var1.getPlayer().getName() + " > " + var1.getMessage());
               } else if (Main.magentaPlayers.contains(var1.getPlayer())) {
                  var2 = Main.magentaPlayers.iterator();

                  while(var2.hasNext()) {
                     var3 = (Player)var2.next();
                     var3.sendMessage(Messages.getMsg().getString("chat.magenta").replace("{player}", var1.getPlayer().getName()).replace("{message}", var1.getMessage()).replace('&', '\u00a7').replace("{prefix}", getPrefix(var1.getPlayer())).replace("{suffix}", getSuffix(var1.getPlayer())));
                  }

                  Bukkit.getLogger().info("MAGENTA | " + var1.getPlayer().getName() + " > " + var1.getMessage());
               } else if (Main.greenPlayers.contains(var1.getPlayer())) {
                  var2 = Main.greenPlayers.iterator();

                  while(var2.hasNext()) {
                     var3 = (Player)var2.next();
                     var3.sendMessage(Messages.getMsg().getString("chat.green").replace("{player}", var1.getPlayer().getName()).replace("{message}", var1.getMessage()).replace('&', '\u00a7').replace("{prefix}", getPrefix(var1.getPlayer())).replace("{suffix}", getSuffix(var1.getPlayer())));
                  }

                  Bukkit.getLogger().info("GREEN | " + var1.getPlayer().getName() + " > " + var1.getMessage());
               } else if (Main.bluePlayers.contains(var1.getPlayer())) {
                  var2 = Main.bluePlayers.iterator();

                  while(var2.hasNext()) {
                     var3 = (Player)var2.next();
                     var3.sendMessage(Messages.getMsg().getString("chat.blue").replace("{player}", var1.getPlayer().getName()).replace("{message}", var1.getMessage()).replace('&', '\u00a7').replace("{prefix}", getPrefix(var1.getPlayer())).replace("{suffix}", getSuffix(var1.getPlayer())));
                  }

                  Bukkit.getLogger().info("BLUE | " + var1.getPlayer().getName() + " > " + var1.getMessage());
               }
            } else {
               var2 = Main.spectators.iterator();

               while(var2.hasNext()) {
                  var3 = (Player)var2.next();
                  var3.sendMessage(Messages.getMsg().getString("chat.spectators").replace("{player}", var1.getPlayer().getName()).replace("{message}", var1.getMessage()).replace('&', '\u00a7').replace("{prefix}", getPrefix(var1.getPlayer())).replace("{suffix}", getSuffix(var1.getPlayer())));
               }

               Bukkit.getLogger().info("DEAD | " + var1.getPlayer().getName() + " > " + var1.getMessage());
            }
         }

         if (Main.STATUS == GameState.LOBBY || Main.STATUS == GameState.STARTING || Main.STATUS == GameState.RESTARTING) {
            Bukkit.broadcastMessage(Messages.getMsg().getString("chat.lobby").replace("{player}", var1.getPlayer().getName()).replace("{message}", var1.getMessage()).replace('&', '\u00a7').replace("{prefix}", getPrefix(var1.getPlayer())).replace("{suffix}", getSuffix(var1.getPlayer())));
         }

      }
   }

   private static String getPrefix(Player var0) {
      return Main.vaultHook.booleanValue() ? Main.chat.getPlayerPrefix(var0).replace('&', '\u00a7') : "";
   }

   private static String getSuffix(Player var0) {
      return Main.vaultHook.booleanValue() ? Main.chat.getPlayerSuffix(var0).replace('&', '\u00a7') : "";
   }
}
