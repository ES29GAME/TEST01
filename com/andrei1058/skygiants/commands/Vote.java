package com.andrei1058.skygiants.commands;

import com.andrei1058.skygiants.Main;
import com.andrei1058.skygiants.configuration.ArenaCfg;
import com.andrei1058.skygiants.configuration.Messages;
import com.andrei1058.skygiants.configuration.Settings;
import com.andrei1058.skygiants.game.GameState;
import com.andrei1058.skygiants.utils.Misc;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Vote implements CommandExecutor {
   public boolean onCommand(CommandSender var1, Command var2, String var3, String[] var4) {
      if (var2.getName().equalsIgnoreCase("vote")) {
         if (Main.MAINTENANCE.booleanValue()) {
            var1.sendMessage(Main.PREFIX + Messages.getMsg().getString("voting-disabled").replace('&', '\u00a7'));
            return true;
         }

         if (Main.STATUS == GameState.PLAYING) {
            var1.sendMessage(Main.PREFIX + Messages.getMsg().getString("voting-disabled").replace('&', '\u00a7'));
            return true;
         }

         if (Main.voted.contains((Player)var1)) {
            var1.sendMessage(Main.PREFIX + Messages.getMsg().getString("already-voted").replace('&', '\u00a7'));
            return true;
         }

         if (var4.length == 1) {
            if (!Misc.isInt(var4[0])) {
               ArenaCfg.listArenaVotes((Player)var1);
               return true;
            }

            if (Settings.getCfg().getStringList("Arenas").size() < Integer.valueOf(var4[0]).intValue()) {
               var1.sendMessage(Main.PREFIX + Messages.getMsg().getString("invalid-option").replace('&', '\u00a7'));
               return true;
            }

            Main.mapsVoting.replace(Settings.getCfg().getStringList("Arenas").get(Integer.valueOf(var4[0]).intValue() - 1), ((Integer)Main.mapsVoting.get(Settings.getCfg().getStringList("Arenas").get(Integer.valueOf(var4[0]).intValue() - 1))).intValue() + 1);
            Main.voted.add((Player)var1);
            ArenaCfg.listArenaVotes((Player)var1);
         } else {
            ArenaCfg.listArenaVotes((Player)var1);
         }
      }

      return false;
   }
}
