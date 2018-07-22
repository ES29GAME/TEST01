package com.andrei1058.skygiants.runnables;

import com.andrei1058.skygiants.Main;
import com.andrei1058.skygiants.configuration.Messages;
import com.andrei1058.skygiants.game.Scoreboard;
import com.andrei1058.skygiants.game.Winner;
import com.andrei1058.skygiants.locations.Region;
import java.util.Iterator;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Game extends BukkitRunnable {
   public void run() {
      if (Main.GameCountdown != 0) {
         --Main.GameCountdown;
      }

      if (Main.GameCountdown == 1320) {
         Bukkit.broadcastMessage(Main.PREFIX + Messages.getMsg().getString("double-damage").replace("{time}", "2").replace('&', '\u00a7'));
      }

      if (Main.GameCountdown == 1260) {
         Bukkit.broadcastMessage(Main.PREFIX + Messages.getMsg().getString("double-damage").replace("{time}", "1").replace('&', '\u00a7'));
      }

      if (Main.GameCountdown == 1200) {
         Bukkit.broadcastMessage(Main.PREFIX + Messages.getMsg().getString("double-giant-damage").replace('&', '\u00a7'));
         Main.doubleDamage = true;
      }

      Scoreboard.updateScoreboard();
      Iterator var1 = Main.players.iterator();

      while(var1.hasNext()) {
         Player var2 = (Player)var1.next();
         Main.money.put(var2, ((Integer)Main.money.get(var2)).intValue() + 2);
         Main.nmsH.actionMsg(var2, Messages.getMsg().getString("action-bar").replace("{gold}", String.valueOf(Main.money.get(var2))).replace("{region}", Region.getRegion(var2)).replace("{kills}", String.valueOf(Main.kills.get(var2))).replace('&', '\u00a7'));
      }

      if (Main.GameCountdown == 0) {
         this.cancel();
         Winner.getWinner();
         (new Restarting()).runTaskTimer(Main.plugin, 20L, 20L);
      }

   }
}
