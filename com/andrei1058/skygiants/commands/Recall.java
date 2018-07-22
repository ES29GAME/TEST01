package com.andrei1058.skygiants.commands;

import com.andrei1058.skygiants.Main;
import com.andrei1058.skygiants.game.GameState;
import com.andrei1058.skygiants.locations.Spawns;
import org.bukkit.Effect;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Recall implements CommandExecutor {
   public boolean onCommand(CommandSender var1, Command var2, String var3, String[] var4) {
      if (!(var1 instanceof Player)) {
         return true;
      } else {
         final Player var5 = (Player)var1;
         if (var2.getName().equalsIgnoreCase("recall")) {
            if (Main.STATUS != GameState.PLAYING) {
               return true;
            }

            if (Main.recall.contains(var5)) {
               return true;
            }

            if (Main.respawning.contains(var5)) {
               return true;
            }

            if (Main.spectators.contains(var5)) {
               return true;
            }

            Main.recall.add(var5);
            (new BukkitRunnable() {
               int i = 40;

               public void run() {
                  if (this.i != 0) {
                     --this.i;
                  }

                  if (Main.recall.contains(var5)) {
                     var5.getWorld().playEffect(var5.getLocation(), Effect.CLOUD, 1, 0);
                     var5.getWorld().playEffect(var5.getLocation(), Effect.CLOUD, 1, 0);
                     var5.getWorld().playEffect(var5.getLocation(), Effect.CLOUD, 1, 0);
                     var5.getWorld().playEffect(var5.getLocation().add(0.0D, 1.0D, 0.0D), Effect.CLOUD, 1, 0);
                     var5.getWorld().playEffect(var5.getLocation().add(0.0D, 1.0D, 0.0D), Effect.CLOUD, 1, 0);
                     var5.getWorld().playEffect(var5.getLocation().add(0.0D, 1.0D, 0.0D), Effect.CLOUD, 1, 0);
                     var5.getWorld().playEffect(var5.getLocation().add(0.0D, 1.8D, 0.0D), Effect.CLOUD, 1, 0);
                     var5.getWorld().playEffect(var5.getLocation().add(0.0D, 1.8D, 0.0D), Effect.CLOUD, 1, 0);
                     var5.getWorld().playEffect(var5.getLocation().add(0.0D, 1.8D, 0.0D), Effect.CLOUD, 1, 0);
                     var5.getWorld().playEffect(var5.getLocation().add(0.0D, 1.8D, 0.0D), Effect.CLOUD, 1, 0);
                  } else {
                     this.cancel();
                  }

                  if (this.i == 0) {
                     this.cancel();
                     if (Main.magentaPlayers.contains(var5)) {
                        var5.teleport(Spawns.getSpawn("Magenta"));
                     } else if (Main.goldPlayers.contains(var5)) {
                        var5.teleport(Spawns.getSpawn("Gold"));
                     } else if (Main.greenPlayers.contains(var5)) {
                        var5.teleport(Spawns.getSpawn("Green"));
                     } else if (Main.bluePlayers.contains(var5)) {
                        var5.teleport(Spawns.getSpawn("Blue"));
                     }

                     Main.recall.remove(var5);
                  }

               }
            }).runTaskTimer(Main.plugin, 0L, 5L);
         }

         return false;
      }
   }
}
