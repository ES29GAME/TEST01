package com.andrei1058.skygiants.locations;

import com.andrei1058.skygiants.Main;
import com.andrei1058.skygiants.configuration.ArenaCfg;
import java.io.File;
import java.io.IOException;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class Borders {
   public static void setBorder(String var0, Player var1, Integer var2) {
      File var3 = new File("plugins/SkyGiants1058/Arenas/" + var1.getWorld().getName() + ".yml");
      YamlConfiguration var4 = YamlConfiguration.loadConfiguration(var3);
      if (var0.equalsIgnoreCase("gold")) {
         var4.set("Border.Gold.X", var1.getLocation().getX());
         var4.set("Border.Gold.Z", var1.getLocation().getZ());
         var4.set("Border.Gold.Size", var2);

         try {
            var4.save(var3);
         } catch (IOException var9) {
            var9.printStackTrace();
         }

         var1.sendMessage(Main.PREFIX + "\u00a76Gold \u00a7bborder set!");
      } else if (var0.equalsIgnoreCase("magenta")) {
         var4.set("Border.Magenta.X", var1.getLocation().getX());
         var4.set("Border.Magenta.Z", var1.getLocation().getZ());
         var4.set("Border.Magenta.Size", var2);

         try {
            var4.save(var3);
         } catch (IOException var8) {
            var8.printStackTrace();
         }

         var1.sendMessage(Main.PREFIX + "\u00a75Magenta \u00a7bborder set!");
      } else if (var0.equalsIgnoreCase("green")) {
         var4.set("Border.Green.X", var1.getLocation().getX());
         var4.set("Border.Green.Z", var1.getLocation().getZ());
         var4.set("Border.Green.Size", var2);

         try {
            var4.save(var3);
         } catch (IOException var7) {
            var7.printStackTrace();
         }

         var1.sendMessage(Main.PREFIX + "\u00a7aGreen \u00a7bborder set!");
      } else if (var0.equalsIgnoreCase("blue")) {
         var4.set("Border.Blue.X", var1.getLocation().getX());
         var4.set("Border.Blue.Z", var1.getLocation().getZ());
         var4.set("Border.Blue.Size", var2);

         try {
            var4.save(var3);
         } catch (IOException var6) {
            var6.printStackTrace();
         }

         var1.sendMessage(Main.PREFIX + "\u00a79Blue \u00a7bborder set!");
      } else {
         var1.sendMessage(Main.PREFIX + "\u00a7bChoices: &6Gold&b, &5Magenta&b, &aGreen&b, &9Blue&b.");
      }

   }

   public static void checkBorders(Player var0) {
      String var1 = var0.getLocation().getWorld().getName();
      if (ArenaCfg.getArena(var1).get("Border.Gold.X") == null && ArenaCfg.getArena(var1).get("Border.Magenta.X") == null && ArenaCfg.getArena(var1).get("Border.Green.X") != null && ArenaCfg.getArena(var1).get("Border.Blue.X") != null) {
         var0.sendMessage(Main.PREFIX + "\u00a7b/sgs setBorder \u00a76\u2588 \u00a75\u2588 \u00a7b<radius>");
      } else if (ArenaCfg.getArena(var1).get("Border.Gold.X") != null && ArenaCfg.getArena(var1).get("Border.Magenta.X") == null && ArenaCfg.getArena(var1).get("Border.Green.X") == null && ArenaCfg.getArena(var1).get("Border.Blue.X") != null) {
         var0.sendMessage(Main.PREFIX + "\u00a7b/sgs setBorder \u00a75\u2588 \u00a7a\u2588 \u00a7b<radius>");
      } else if (ArenaCfg.getArena(var1).get("Border.Gold.X") != null && ArenaCfg.getArena(var1).get("Border.Magenta.X") != null && ArenaCfg.getArena(var1).get("Border.Green.X") == null && ArenaCfg.getArena(var1).get("Border.Blue.X") == null) {
         var0.sendMessage(Main.PREFIX + "\u00a7b/sgs setBorder \u00a7a\u2588 \u00a79\u2588 \u00a7b<radius>");
      } else if (ArenaCfg.getArena(var1).get("Border.Gold.X") == null && ArenaCfg.getArena(var1).get("Border.Magenta.X") != null && ArenaCfg.getArena(var1).get("Border.Green.X") != null && ArenaCfg.getArena(var1).get("Border.Blue.X") == null) {
         var0.sendMessage(Main.PREFIX + "\u00a7b/sgs setBorder \u00a76\u2588 \u00a79\u2588 \u00a7b<radius>");
      } else if (ArenaCfg.getArena(var1).get("Border.Gold.X") != null && ArenaCfg.getArena(var1).get("Border.Magenta.X") == null && ArenaCfg.getArena(var1).get("Border.Green.X") == null && ArenaCfg.getArena(var1).get("Border.Blue.X") != null) {
         var0.sendMessage(Main.PREFIX + "\u00a7b/sgs setBorder \u00a75\u2588 \u00a7a\u2588 \u00a7b<radius>");
      } else if (ArenaCfg.getArena(var1).get("Border.Gold.X") == null && ArenaCfg.getArena(var1).get("Border.Magenta.X") != null && ArenaCfg.getArena(var1).get("Border.Green.X") == null && ArenaCfg.getArena(var1).get("Border.Blue.X") != null) {
         var0.sendMessage(Main.PREFIX + "\u00a7b/sgs setBorder \u00a76\u2588 \u00a7a\u2588 \u00a7b<radius>");
      } else if (ArenaCfg.getArena(var1).get("Border.Gold.X") != null && ArenaCfg.getArena(var1).get("Border.Magenta.X") == null && ArenaCfg.getArena(var1).get("Border.Green.X") != null && ArenaCfg.getArena(var1).get("Border.Blue.X") == null) {
         var0.sendMessage(Main.PREFIX + "\u00a7b/sgs setBorder \u00a75\u2588 \u00a79\u2588 \u00a7b<radius>");
      } else if (ArenaCfg.getArena(var1).get("Border.Gold.X") == null && ArenaCfg.getArena(var1).get("Border.Magenta.X") == null && ArenaCfg.getArena(var1).get("Border.Green.X") == null && ArenaCfg.getArena(var1).get("Border.Blue.X") != null) {
         var0.sendMessage(Main.PREFIX + "\u00a7b/sgs setBorder \u00a76\u2588 \u00a75\u2588 \u00a7a\u2588 \u00a7b<radius>");
      } else if (ArenaCfg.getArena(var1).get("Border.Gold.X") != null && ArenaCfg.getArena(var1).get("Border.Magenta.X") == null && ArenaCfg.getArena(var1).get("Border.Green.X") == null && ArenaCfg.getArena(var1).get("Border.Blue.X") == null) {
         var0.sendMessage(Main.PREFIX + "\u00a7b/sgs setBorder \u00a75\u2588 \u00a7a\u2588 \u00a79\u2588 \u00a7b<radius>");
      } else if (ArenaCfg.getArena(var1).get("Border.Gold.X") == null && ArenaCfg.getArena(var1).get("Border.Magenta.X") != null && ArenaCfg.getArena(var1).get("Border.Green.X") == null && ArenaCfg.getArena(var1).get("Border.Blue.X") == null) {
         var0.sendMessage(Main.PREFIX + "\u00a7b/sgs setBorder \u00a76\u2588 \u00a7a\u2588 \u00a79\u2588 \u00a7b<radius>");
      } else if (ArenaCfg.getArena(var1).get("Border.Gold.X") == null && ArenaCfg.getArena(var1).get("Border.Magenta.X") == null && ArenaCfg.getArena(var1).get("Border.Green.X") == null && ArenaCfg.getArena(var1).get("Border.Blue.X") != null) {
         var0.sendMessage(Main.PREFIX + "\u00a7b/sgs setBorder \u00a76\u2588 \u00a7a\u2588 \u00a75\u2588 \u00a7b<radius>");
      } else if (ArenaCfg.getArena(var1).get("Border.Gold.X") == null && ArenaCfg.getArena(var1).get("Border.Magenta.X") != null && ArenaCfg.getArena(var1).get("Border.Green.X") != null && ArenaCfg.getArena(var1).get("Border.Blue.X") != null) {
         var0.sendMessage(Main.PREFIX + "\u00a7b/sgs setBorder \u00a76\u2588 \u00a7b<radius>");
      } else if (ArenaCfg.getArena(var1).get("Border.Gold.X") != null && ArenaCfg.getArena(var1).get("Border.Magenta.X") == null && ArenaCfg.getArena(var1).get("Border.Green.X") != null && ArenaCfg.getArena(var1).get("Border.Blue.X") != null) {
         var0.sendMessage(Main.PREFIX + "\u00a7b/sgs setBorder \u00a75\u2588 \u00a7b<radius>");
      } else if (ArenaCfg.getArena(var1).get("Border.Gold.X") != null && ArenaCfg.getArena(var1).get("Border.Magenta.X") != null && ArenaCfg.getArena(var1).get("Border.Green.X") == null && ArenaCfg.getArena(var1).get("Border.Blue.X") != null) {
         var0.sendMessage(Main.PREFIX + "\u00a7b/sgs setBorder \u00a7a\u2588 \u00a7b<radius>");
      } else if (ArenaCfg.getArena(var1).get("Border.Gold.X") != null && ArenaCfg.getArena(var1).get("Border.Magenta.X") != null && ArenaCfg.getArena(var1).get("Border.Green.X") != null && ArenaCfg.getArena(var1).get("Border.Blue.X") == null) {
         var0.sendMessage(Main.PREFIX + "\u00a7b/sgs setBorder \u00a79\u2588 \u00a7b<radius>");
      } else if (ArenaCfg.getArena(var1).get("Border.Gold.X") == null && ArenaCfg.getArena(var1).get("Border.Magenta.X") == null && ArenaCfg.getArena(var1).get("Border.Green.X") == null && ArenaCfg.getArena(var1).get("Border.Blue.X") == null) {
         var0.sendMessage(Main.PREFIX + "\u00a7b/sgs setBorder \u00a76\u2588 \u00a75\u2588 \u00a7a\u2588 \u00a79\u2588 \u00a7b<radius>");
      }

   }

   public static Location getBorder1(String var0) {
      return new Location(Bukkit.getWorld(Main.choosenMap), ArenaCfg.getArena(Main.choosenMap).getDouble("Border." + var0 + ".X"), ArenaCfg.getArena(Main.choosenMap).getDouble("Border." + var0 + ".Y"), ArenaCfg.getArena(Main.choosenMap).getDouble("Border." + var0 + ".Z"), ((Float)ArenaCfg.getArena(Main.choosenMap).get("Border." + var0 + ".Yaw")).floatValue(), ((Float)ArenaCfg.getArena(Main.choosenMap).get("Border." + var0 + ".Pitch")).floatValue());
   }
}
