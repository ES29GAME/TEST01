package com.andrei1058.skygiants.locations;

import com.andrei1058.skygiants.Main;
import com.andrei1058.skygiants.configuration.ArenaCfg;
import java.io.File;
import java.io.IOException;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class Spawns {
   public static void setSpawn(String var0, Player var1) {
      File var2 = new File("plugins/SkyGiants1058/Arenas/" + var1.getWorld().getName() + ".yml");
      YamlConfiguration var3 = YamlConfiguration.loadConfiguration(var2);
      if (var0.equalsIgnoreCase("gold")) {
         var3.set("Spawn.Gold.X", var1.getLocation().getX());
         var3.set("Spawn.Gold.Y", var1.getLocation().getY());
         var3.set("Spawn.Gold.Z", var1.getLocation().getZ());
         var3.set("Spawn.Gold.Yaw", var1.getLocation().getYaw());
         var3.set("Spawn.Gold.Pitch", var1.getLocation().getPitch());

         try {
            var3.save(var2);
         } catch (IOException var8) {
            var8.printStackTrace();
         }

         var1.sendMessage(Main.PREFIX + "\u00a76Gold \u00a7bspawn set!");
      } else if (var0.equalsIgnoreCase("magenta")) {
         var3.set("Spawn.Magenta.X", var1.getLocation().getX());
         var3.set("Spawn.Magenta.Y", var1.getLocation().getY());
         var3.set("Spawn.Magenta.Z", var1.getLocation().getZ());
         var3.set("Spawn.Magenta.Yaw", var1.getLocation().getYaw());
         var3.set("Spawn.Magenta.Pitch", var1.getLocation().getPitch());

         try {
            var3.save(var2);
         } catch (IOException var7) {
            var7.printStackTrace();
         }

         var1.sendMessage(Main.PREFIX + "\u00a75Magenta \u00a7bspawn set!");
      } else if (var0.equalsIgnoreCase("green")) {
         var3.set("Spawn.Green.X", var1.getLocation().getX());
         var3.set("Spawn.Green.Y", var1.getLocation().getY());
         var3.set("Spawn.Green.Z", var1.getLocation().getZ());
         var3.set("Spawn.Green.Yaw", var1.getLocation().getYaw());
         var3.set("Spawn.Green.Pitch", var1.getLocation().getPitch());

         try {
            var3.save(var2);
         } catch (IOException var6) {
            var6.printStackTrace();
         }

         var1.sendMessage(Main.PREFIX + "\u00a7aGreen \u00a7bspawn set!");
      } else if (var0.equalsIgnoreCase("blue")) {
         var3.set("Spawn.Blue.X", var1.getLocation().getX());
         var3.set("Spawn.Blue.Y", var1.getLocation().getY());
         var3.set("Spawn.Blue.Z", var1.getLocation().getZ());
         var3.set("Spawn.Blue.Yaw", var1.getLocation().getYaw());
         var3.set("Spawn.Blue.Pitch", var1.getLocation().getPitch());

         try {
            var3.save(var2);
         } catch (IOException var5) {
            var5.printStackTrace();
         }

         var1.sendMessage(Main.PREFIX + "\u00a79Blue \u00a7bspawn set!");
      } else {
         var1.sendMessage(Main.PREFIX + "\u00a7bChoices: &6Gold&b, &5Magenta&b, &aGreen&b, &9Blue&b.");
      }

   }

   public static void checkSpawns(Player var0) {
      String var1 = var0.getLocation().getWorld().getName();
      if (ArenaCfg.getArena(var1).get("Spawn.Gold.X") == null && ArenaCfg.getArena(var1).get("Spawn.Magenta.X") == null && ArenaCfg.getArena(var1).get("Spawn.Green.X") != null && ArenaCfg.getArena(var1).get("Spawn.Blue.X") != null) {
         var0.sendMessage(Main.PREFIX + "\u00a7b/sgs setSpawn \u00a76\u2588 \u00a75\u2588");
      } else if (ArenaCfg.getArena(var1).get("Spawn.Gold.X") != null && ArenaCfg.getArena(var1).get("Spawn.Magenta.X") == null && ArenaCfg.getArena(var1).get("Spawn.Green.X") == null && ArenaCfg.getArena(var1).get("Spawn.Blue.X") != null) {
         var0.sendMessage(Main.PREFIX + "\u00a7b/sgs setSpawn \u00a75\u2588 \u00a7a\u2588");
      } else if (ArenaCfg.getArena(var1).get("Spawn.Gold.X") != null && ArenaCfg.getArena(var1).get("Spawn.Magenta.X") != null && ArenaCfg.getArena(var1).get("Spawn.Green.X") == null && ArenaCfg.getArena(var1).get("Spawn.Blue.X") == null) {
         var0.sendMessage(Main.PREFIX + "\u00a7b/sgs setSpawn \u00a7a\u2588 \u00a79\u2588");
      } else if (ArenaCfg.getArena(var1).get("Spawn.Gold.X") == null && ArenaCfg.getArena(var1).get("Spawn.Magenta.X") != null && ArenaCfg.getArena(var1).get("Spawn.Green.X") != null && ArenaCfg.getArena(var1).get("Spawn.Blue.X") == null) {
         var0.sendMessage(Main.PREFIX + "\u00a7b/sgs setSpawn \u00a76\u2588 \u00a79\u2588");
      } else if (ArenaCfg.getArena(var1).get("Spawn.Gold.X") != null && ArenaCfg.getArena(var1).get("Spawn.Magenta.X") == null && ArenaCfg.getArena(var1).get("Spawn.Green.X") == null && ArenaCfg.getArena(var1).get("Spawn.Blue.X") != null) {
         var0.sendMessage(Main.PREFIX + "\u00a7b/sgs setSpawn \u00a75\u2588 \u00a7a\u2588");
      } else if (ArenaCfg.getArena(var1).get("Spawn.Gold.X") == null && ArenaCfg.getArena(var1).get("Spawn.Magenta.X") != null && ArenaCfg.getArena(var1).get("Spawn.Green.X") == null && ArenaCfg.getArena(var1).get("Spawn.Blue.X") != null) {
         var0.sendMessage(Main.PREFIX + "\u00a7b/sgs setSpawn \u00a76\u2588 \u00a7a\u2588");
      } else if (ArenaCfg.getArena(var1).get("Spawn.Gold.X") != null && ArenaCfg.getArena(var1).get("Spawn.Magenta.X") == null && ArenaCfg.getArena(var1).get("Spawn.Green.X") != null && ArenaCfg.getArena(var1).get("Spawn.Blue.X") == null) {
         var0.sendMessage(Main.PREFIX + "\u00a7b/sgs setSpawn \u00a75\u2588 \u00a79\u2588");
      } else if (ArenaCfg.getArena(var1).get("Spawn.Gold.X") == null && ArenaCfg.getArena(var1).get("Spawn.Magenta.X") == null && ArenaCfg.getArena(var1).get("Spawn.Green.X") == null && ArenaCfg.getArena(var1).get("Spawn.Blue.X") != null) {
         var0.sendMessage(Main.PREFIX + "\u00a7b/sgs setSpawn \u00a76\u2588 \u00a75\u2588 \u00a7a\u2588");
      } else if (ArenaCfg.getArena(var1).get("Spawn.Gold.X") != null && ArenaCfg.getArena(var1).get("Spawn.Magenta.X") == null && ArenaCfg.getArena(var1).get("Spawn.Green.X") == null && ArenaCfg.getArena(var1).get("Spawn.Blue.X") == null) {
         var0.sendMessage(Main.PREFIX + "\u00a7b/sgs setSpawn \u00a75\u2588 \u00a7a\u2588 \u00a79\u2588");
      } else if (ArenaCfg.getArena(var1).get("Spawn.Gold.X") == null && ArenaCfg.getArena(var1).get("Spawn.Magenta.X") != null && ArenaCfg.getArena(var1).get("Spawn.Green.X") == null && ArenaCfg.getArena(var1).get("Spawn.Blue.X") == null) {
         var0.sendMessage(Main.PREFIX + "\u00a7b/sgs setSpawn \u00a76\u2588 \u00a7a\u2588 \u00a79\u2588");
      } else if (ArenaCfg.getArena(var1).get("Spawn.Gold.X") == null && ArenaCfg.getArena(var1).get("Spawn.Magenta.X") == null && ArenaCfg.getArena(var1).get("Spawn.Green.X") == null && ArenaCfg.getArena(var1).get("Spawn.Blue.X") != null) {
         var0.sendMessage(Main.PREFIX + "\u00a7b/sgs setSpawn \u00a76\u2588 \u00a7a\u2588 \u00a75\u2588");
      } else if (ArenaCfg.getArena(var1).get("Spawn.Gold.X") == null && ArenaCfg.getArena(var1).get("Spawn.Magenta.X") != null && ArenaCfg.getArena(var1).get("Spawn.Green.X") != null && ArenaCfg.getArena(var1).get("Spawn.Blue.X") != null) {
         var0.sendMessage(Main.PREFIX + "\u00a7b/sgs setSpawn \u00a76\u2588");
      } else if (ArenaCfg.getArena(var1).get("Spawn.Gold.X") != null && ArenaCfg.getArena(var1).get("Spawn.Magenta.X") == null && ArenaCfg.getArena(var1).get("Spawn.Green.X") != null && ArenaCfg.getArena(var1).get("Spawn.Blue.X") != null) {
         var0.sendMessage(Main.PREFIX + "\u00a7b/sgs setSpawn \u00a75\u2588");
      } else if (ArenaCfg.getArena(var1).get("Spawn.Gold.X") != null && ArenaCfg.getArena(var1).get("Spawn.Magenta.X") != null && ArenaCfg.getArena(var1).get("Spawn.Green.X") == null && ArenaCfg.getArena(var1).get("Spawn.Blue.X") != null) {
         var0.sendMessage(Main.PREFIX + "\u00a7b/sgs setSpawn \u00a7a\u2588");
      } else if (ArenaCfg.getArena(var1).get("Spawn.Gold.X") != null && ArenaCfg.getArena(var1).get("Spawn.Magenta.X") != null && ArenaCfg.getArena(var1).get("Spawn.Green.X") != null && ArenaCfg.getArena(var1).get("Spawn.Blue.X") == null) {
         var0.sendMessage(Main.PREFIX + "\u00a7b/sgs setSpawn \u00a79\u2588");
      } else if (ArenaCfg.getArena(var1).get("Spawn.Gold.X") == null && ArenaCfg.getArena(var1).get("Spawn.Magenta.X") == null && ArenaCfg.getArena(var1).get("Spawn.Green.X") == null && ArenaCfg.getArena(var1).get("Spawn.Blue.X") == null) {
         var0.sendMessage(Main.PREFIX + "\u00a7b/sgs setSpawn \u00a76\u2588 \u00a75\u2588 \u00a7a\u2588 \u00a79\u2588");
      }

   }

   public static Location getSpawn(String var0) {
      return new Location(Bukkit.getWorld(Main.choosenMap), ArenaCfg.getArena(Main.choosenMap).getDouble("Spawn." + var0 + ".X"), ArenaCfg.getArena(Main.choosenMap).getDouble("Spawn." + var0 + ".Y"), ArenaCfg.getArena(Main.choosenMap).getDouble("Spawn." + var0 + ".Z"), (float)ArenaCfg.getArena(Main.choosenMap).getDouble("Spawn." + var0 + ".Yaw"), (float)ArenaCfg.getArena(Main.choosenMap).getDouble("Spawn." + var0 + ".Pitch"));
   }
}
