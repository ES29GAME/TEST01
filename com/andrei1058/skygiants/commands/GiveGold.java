package com.andrei1058.skygiants.commands;

import com.andrei1058.skygiants.Main;
import com.andrei1058.skygiants.configuration.Messages;
import com.andrei1058.skygiants.game.GameState;
import com.andrei1058.skygiants.utils.Misc;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GiveGold implements CommandExecutor {
   public boolean onCommand(CommandSender var1, Command var2, String var3, String[] var4) {
      if (!(var1 instanceof Player)) {
         return true;
      } else if (Main.STATUS != GameState.PLAYING) {
         return true;
      } else if (!Main.players.contains(var1)) {
         return true;
      } else {
         if (var2.getName().equalsIgnoreCase("givegold")) {
            if (var4.length == 2) {
               if (Bukkit.getPlayer(var4[0]) == null) {
                  var1.sendMessage(Main.PREFIX + Messages.getMsg().getString("gold-usage").replace('&', '\u00a7'));
                  return true;
               }

               if (!Misc.isInt(var4[1])) {
                  var1.sendMessage(Main.PREFIX + Messages.getMsg().getString("gold-usage").replace('&', '\u00a7'));
                  return true;
               }

               Player var5 = (Player)var1;
               Player var6 = Bukkit.getPlayer(var4[0]);
               if (var6.getName() == var5.getName()) {
                  var1.sendMessage(Main.PREFIX + Messages.getMsg().getString("gold-usage").replace('&', '\u00a7'));
                  return true;
               }

               if (!Main.players.contains(var6)) {
                  return true;
               }

               if (((Integer)Main.money.get(var5)).intValue() >= Integer.valueOf(var4[1]).intValue()) {
                  Main.money.replace(var6, ((Integer)Main.money.get(var5)).intValue() + Integer.valueOf(var4[1]).intValue());
                  Main.money.remove(var5, ((Integer)Main.money.get(var5)).intValue() - Integer.valueOf(var4[1]).intValue());
                  var5.sendMessage(Main.PREFIX + Messages.getMsg().getString("gold-sent").replace("{amount}", var4[1]).replace("{target}", var4[0]).replace('&', '\u00a7'));
                  var6.sendMessage(Main.PREFIX + Messages.getMsg().getString("gold-received").replace("{amount}", var4[1]).replace("{player}", var5.getName()));
               } else {
                  var5.sendMessage(Main.PREFIX + Messages.getMsg().getString("gold-to-send").replace('&', '\u00a7'));
               }
            } else {
               var1.sendMessage(Main.PREFIX + Messages.getMsg().getString("gold-usage").replace('&', '\u00a7'));
            }
         }

         return false;
      }
   }
}
