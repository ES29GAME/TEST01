package com.andrei1058.skygiants.commands;

import com.andrei1058.skygiants.Main;
import com.andrei1058.skygiants.configuration.Settings;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Leave implements CommandExecutor {
   public boolean onCommand(CommandSender var1, Command var2, String var3, String[] var4) {
      if (!(var1 instanceof Player)) {
         return true;
      } else {
         Player var5 = (Player)var1;
         if (var2.getName().equalsIgnoreCase("leave")) {
            ByteArrayDataOutput var6 = ByteStreams.newDataOutput();
            var6.writeUTF("Connect");
            var6.writeUTF(Settings.getCfg().getString("lobby-server"));
            var5.sendPluginMessage(Main.plugin, "BungeeCord", var6.toByteArray());
            return true;
         } else {
            return false;
         }
      }
   }
}
