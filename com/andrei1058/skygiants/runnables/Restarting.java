package com.andrei1058.skygiants.runnables;

import com.andrei1058.skygiants.Main;
import com.andrei1058.skygiants.configuration.Messages;
import com.andrei1058.skygiants.configuration.Settings;
import com.andrei1058.skygiants.game.GameState;
import com.andrei1058.skygiants.game.Scoreboard;
import com.andrei1058.skygiants.utils.Database;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import java.util.Iterator;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Restarting extends BukkitRunnable {
   public void run() {
      if (Main.RestartCountdown != 0) {
         --Main.RestartCountdown;
      }

      if (Main.RestartCountdown == 12 || Main.RestartCountdown == 11 || Main.RestartCountdown == 10) {
         Scoreboard.restartSb();
      }

      if (Main.RestartCountdown == 10) {
         Main.STATUS = GameState.RESTARTING;
         Bukkit.broadcastMessage(Main.PREFIX + Messages.getMsg().getString("game-over").replace('&', '\u00a7'));
         Iterator var1 = Bukkit.getOnlinePlayers().iterator();

         Player var2;
         while(var1.hasNext()) {
            var2 = (Player)var1.next();
            Database.saveStats(var2);
         }

         if (Settings.getCfg().getBoolean("reward-command.use")) {
            var1 = Main.players.iterator();

            while(var1.hasNext()) {
               var2 = (Player)var1.next();
               Bukkit.dispatchCommand(Bukkit.getConsoleSender(), Settings.getCfg().getString("reward-command.cmd").replace("%player%", var2.getName()));
            }
         }
      }

      if (Main.RestartCountdown == 2) {
         ByteArrayDataOutput var4 = ByteStreams.newDataOutput();
         var4.writeUTF("Connect");
         var4.writeUTF(Settings.getCfg().getString("lobby-server"));
         Iterator var5 = Bukkit.getOnlinePlayers().iterator();

         while(var5.hasNext()) {
            Player var3 = (Player)var5.next();
            var3.sendPluginMessage(Main.plugin, "BungeeCord", var4.toByteArray());
         }

         Bukkit.unloadWorld(Main.choosenMap, false);
      }

      if (Main.RestartCountdown == 0) {
         Bukkit.dispatchCommand(Bukkit.getConsoleSender(), Settings.getCfg().getString("restart-cmd"));
         this.cancel();
      }

   }
}
