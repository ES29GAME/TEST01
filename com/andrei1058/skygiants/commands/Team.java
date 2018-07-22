package com.andrei1058.skygiants.commands;

import com.andrei1058.skygiants.Main;
import com.andrei1058.skygiants.configuration.Messages;
import com.andrei1058.skygiants.game.GameState;
import java.util.HashMap;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Team implements CommandExecutor {
   public static HashMap isInvited = new HashMap();
   public static HashMap hasInvited = new HashMap();

   public boolean onCommand(CommandSender var1, Command var2, String var3, String[] var4) {
      if (var2.getName().equalsIgnoreCase("team")) {
         if (Main.STATUS != GameState.STARTING && Main.STATUS != GameState.LOBBY) {
            return true;
         }

         if (!Main.miniSG.booleanValue()) {
            return true;
         }

         Player var5 = (Player)var1;
         if (var4.length == 1) {
            if (var4[0].equalsIgnoreCase("leave")) {
               hasLeft(var5);
            }

            if (Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(var4[0]))) {
               if (Main.hasTeam.contains(var5)) {
                  var5.sendMessage(Main.PREFIX + Messages.getMsg().getString("already-in-team").replace('&', '\u00a7'));
                  return true;
               }

               if (Main.hasTeam.contains(Bukkit.getPlayer(var4[0]))) {
                  var5.sendMessage(Main.PREFIX + Messages.getMsg().getString("has-team").replace('&', '\u00a7'));
                  return true;
               }

               Player var6 = Bukkit.getPlayer(var4[0]);
               if (!isInvited.containsKey(var5)) {
                  var5.sendMessage(Main.PREFIX + Messages.getMsg().getString("no-invite").replace('&', '\u00a7'));
                  return true;
               }

               if (!hasInvited.containsKey(var6)) {
                  var6.sendMessage(Main.PREFIX + Messages.getMsg().getString("no-invite").replace('&', '\u00a7'));
                  return true;
               }

               if (var6.getUniqueId().toString().equalsIgnoreCase(((Player)isInvited.get(var5)).getUniqueId().toString())) {
                  Main.hasTeam.add(var6);
                  Main.hasTeam.add(var5);
                  var6.sendMessage(Main.PREFIX + Messages.getMsg().getString("teamed-up").replace("{name}", var5.getName()).replace('&', '\u00a7'));
                  var5.sendMessage(Main.PREFIX + Messages.getMsg().getString("teamed-up").replace("{name}", var6.getName()).replace('&', '\u00a7'));
                  if (Main.goldPlayers.isEmpty()) {
                     Main.goldPlayers.add(var5);
                     Main.goldPlayers.add(var6);
                  } else if (Main.magentaPlayers.isEmpty()) {
                     Main.magentaPlayers.add(var5);
                     Main.magentaPlayers.add(var6);
                  } else if (Main.greenPlayers.isEmpty()) {
                     Main.greenPlayers.add(var5);
                     Main.greenPlayers.add(var6);
                  } else if (Main.bluePlayers.isEmpty()) {
                     Main.bluePlayers.add(var5);
                     Main.bluePlayers.add(var6);
                  }
               } else {
                  var5.sendMessage(Main.PREFIX + Messages.getMsg().getString("no-invite").replace('&', '\u00a7'));
               }
            }
         }
      }

      return false;
   }

   public static void hasLeft(Player var0) {
      if (Main.miniSG.booleanValue()) {
         if (Main.hasTeam.contains(var0)) {
            Player var2 = null;
            if (isInvited.containsKey(var0)) {
               var2 = (Player)isInvited.get(var0);
            }

            if (hasInvited.containsKey(var0)) {
               var2 = (Player)hasInvited.get(var0);
            }

            var2.sendMessage(Main.PREFIX + Messages.getMsg().getString("partner-left").replace('&', '\u00a7'));
            Main.hasTeam.remove(var0);
            Main.hasTeam.remove(var2);
            Main.goldPlayers.remove(var0);
            Main.goldPlayers.remove(var2);
            Main.magentaPlayers.remove(var0);
            Main.magentaPlayers.remove(var2);
            Main.greenPlayers.remove(var0);
            Main.greenPlayers.remove(var2);
            Main.bluePlayers.remove(var0);
            Main.bluePlayers.remove(var2);
            isInvited.remove(var0);
            isInvited.remove(var2);
            hasInvited.remove(var0);
            hasInvited.remove(var2);
         }

      }
   }
}
