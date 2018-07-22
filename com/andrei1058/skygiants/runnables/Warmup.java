package com.andrei1058.skygiants.runnables;

import com.andrei1058.skygiants.Main;
import com.andrei1058.skygiants.configuration.Messages;
import com.andrei1058.skygiants.game.Scoreboard;
import com.andrei1058.skygiants.locations.Region;
import com.andrei1058.skygiants.protocolLib.Borders;
import com.andrei1058.skygiants.utils.Titles;
import java.util.Iterator;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Warmup extends BukkitRunnable {
   public void run() {
      if (Main.WarmupCountdown != 0) {
         --Main.WarmupCountdown;
      }

      Scoreboard.updateScoreboard();
      Iterator var1 = Main.players.iterator();

      Player var2;
      while(var1.hasNext()) {
         var2 = (Player)var1.next();
         if (!Main.money.containsKey(var2)) {
            Main.money.put(var2, Integer.valueOf(2));
         }

         Main.money.replace(var2, ((Integer)Main.money.get(var2)).intValue() + 2);
         Main.nmsH.actionMsg(var2, Messages.getMsg().getString("action-bar").replace("{gold}", String.valueOf(Main.money.get(var2))).replace("{region}", Region.getRegion(var2)).replace("{kills}", String.valueOf(Main.kills.get(var2))).replace('&', '\u00a7'));
      }

      if (Main.WarmupCountdown == 0) {
         Borders.removeBorder();
         Main.warmup = false;
         (new Game()).runTaskTimer(Main.plugin, 20L, 20L);
         this.cancel();
         Main.GameMilis = 1800000L;
         var1 = Bukkit.getOnlinePlayers().iterator();

         while(var1.hasNext()) {
            var2 = (Player)var1.next();
            Titles.fightTitle(var2);
         }
      }

   }
}
