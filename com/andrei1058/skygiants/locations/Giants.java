package com.andrei1058.skygiants.locations;

import com.andrei1058.skygiants.Main;
import com.andrei1058.skygiants.configuration.ArenaCfg;
import java.io.File;
import java.io.IOException;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class Giants {
   public static void setGiant(String var0, Player var1) {
      File var2 = new File("plugins/SkyGiants1058/Arenas/" + var1.getWorld().getName() + ".yml");
      YamlConfiguration var3 = YamlConfiguration.loadConfiguration(var2);
      if (var0.equalsIgnoreCase("gold")) {
         var3.set("Giant.Gold.X", var1.getLocation().getX());
         var3.set("Giant.Gold.Y", var1.getLocation().getY());
         var3.set("Giant.Gold.Z", var1.getLocation().getZ());
         var3.set("Giant.Gold.Yaw", var1.getLocation().getYaw());
         var3.set("Giant.Gold.Pitch", var1.getLocation().getPitch());
         var3.set("Region.Gold.Giant.Pos1.X", var1.getLocation().getX() - 4.0D);
         var3.set("Region.Gold.Giant.Pos1.Y", var1.getLocation().getY() - 4.0D);
         var3.set("Region.Gold.Giant.Pos1.Z", var1.getLocation().getZ() - 4.0D);
         var3.set("Region.Gold.Giant.Pos2.X", var1.getLocation().getX() + 4.0D);
         var3.set("Region.Gold.Giant.Pos2.Y", var1.getLocation().getY() + 4.0D);
         var3.set("Region.Gold.Giant.Pos2.Z", var1.getLocation().getZ() + 4.0D);

         try {
            var3.save(var2);
         } catch (IOException var8) {
            var8.printStackTrace();
         }

         var1.sendMessage(Main.PREFIX + "\u00a76Gold \u00a7bGiant set!");
      } else if (var0.equalsIgnoreCase("magenta")) {
         var3.set("Giant.Magenta.X", var1.getLocation().getX());
         var3.set("Giant.Magenta.Y", var1.getLocation().getY());
         var3.set("Giant.Magenta.Z", var1.getLocation().getZ());
         var3.set("Giant.Magenta.Yaw", var1.getLocation().getYaw());
         var3.set("Giant.Magenta.Pitch", var1.getLocation().getPitch());
         var3.set("Region.Magenta.Giant.Pos1.X", var1.getLocation().getX() - 4.0D);
         var3.set("Region.Magenta.Giant.Pos1.Y", var1.getLocation().getY() - 4.0D);
         var3.set("Region.Magenta.Giant.Pos1.Z", var1.getLocation().getZ() - 4.0D);
         var3.set("Region.Magenta.Giant.Pos2.X", var1.getLocation().getX() + 4.0D);
         var3.set("Region.Magenta.Giant.Pos2.Y", var1.getLocation().getY() + 4.0D);
         var3.set("Region.Magenta.Giant.Pos2.Z", var1.getLocation().getZ() + 4.0D);

         try {
            var3.save(var2);
         } catch (IOException var7) {
            var7.printStackTrace();
         }

         var1.sendMessage(Main.PREFIX + "\u00a75Magenta \u00a7bGiant set!");
      } else if (var0.equalsIgnoreCase("green")) {
         var3.set("Giant.Green.X", var1.getLocation().getX());
         var3.set("Giant.Green.Y", var1.getLocation().getY());
         var3.set("Giant.Green.Z", var1.getLocation().getZ());
         var3.set("Giant.Green.Yaw", var1.getLocation().getYaw());
         var3.set("Giant.Green.Pitch", var1.getLocation().getPitch());
         var3.set("Region.Green.Giant.Pos1.X", var1.getLocation().getX() - 4.0D);
         var3.set("Region.Green.Giant.Pos1.Y", var1.getLocation().getY() - 4.0D);
         var3.set("Region.Green.Giant.Pos1.Z", var1.getLocation().getZ() - 4.0D);
         var3.set("Region.Green.Giant.Pos2.X", var1.getLocation().getX() + 4.0D);
         var3.set("Region.Green.Giant.Pos2.Y", var1.getLocation().getY() + 4.0D);
         var3.set("Region.Green.Giant.Pos2.Z", var1.getLocation().getZ() + 4.0D);

         try {
            var3.save(var2);
         } catch (IOException var6) {
            var6.printStackTrace();
         }

         var1.sendMessage(Main.PREFIX + "\u00a7aGreen \u00a7bGiant set!");
      } else if (var0.equalsIgnoreCase("blue")) {
         var3.set("Giant.Blue.X", var1.getLocation().getX());
         var3.set("Giant.Blue.Y", var1.getLocation().getY());
         var3.set("Giant.Blue.Z", var1.getLocation().getZ());
         var3.set("Giant.Blue.Yaw", var1.getLocation().getYaw());
         var3.set("Giant.Blue.Pitch", var1.getLocation().getPitch());
         var3.set("Region.Blue.Giant.Pos1.X", var1.getLocation().getX() - 4.0D);
         var3.set("Region.Blue.Giant.Pos1.Y", var1.getLocation().getY() - 4.0D);
         var3.set("Region.Blue.Giant.Pos1.Z", var1.getLocation().getZ() - 4.0D);
         var3.set("Region.Blue.Giant.Pos2.X", var1.getLocation().getX() + 4.0D);
         var3.set("Region.Blue.Giant.Pos2.Y", var1.getLocation().getY() + 4.0D);
         var3.set("Region.Blue.Giant.Pos2.Z", var1.getLocation().getZ() + 4.0D);

         try {
            var3.save(var2);
         } catch (IOException var5) {
            var5.printStackTrace();
         }

         var1.sendMessage(Main.PREFIX + "\u00a79Blue \u00a7bGiant set!");
      } else {
         var1.sendMessage(Main.PREFIX + "\u00a7bChoices: &6Gold&b, &5Magenta&b, &aGreen&b, &9Blue&b.");
      }

   }

   public static void checkGiants(Player var0) {
      String var1 = var0.getLocation().getWorld().getName();
      if (ArenaCfg.getArena(var1).get("Giant.Gold.X") == null && ArenaCfg.getArena(var1).get("Giant.Magenta.X") == null && ArenaCfg.getArena(var1).get("Giant.Green.X") != null && ArenaCfg.getArena(var1).get("Giant.Blue.X") != null) {
         var0.sendMessage(Main.PREFIX + "\u00a7b/sgs setGiant \u00a76\u2588 \u00a75\u2588");
      } else if (ArenaCfg.getArena(var1).get("Giant.Gold.X") != null && ArenaCfg.getArena(var1).get("Giant.Magenta.X") == null && ArenaCfg.getArena(var1).get("Giant.Green.X") == null && ArenaCfg.getArena(var1).get("Giant.Blue.X") != null) {
         var0.sendMessage(Main.PREFIX + "\u00a7b/sgs setGiant \u00a75\u2588 \u00a7a\u2588");
      } else if (ArenaCfg.getArena(var1).get("Giant.Gold.X") != null && ArenaCfg.getArena(var1).get("Giant.Magenta.X") != null && ArenaCfg.getArena(var1).get("Giant.Green.X") == null && ArenaCfg.getArena(var1).get("Giant.Blue.X") == null) {
         var0.sendMessage(Main.PREFIX + "\u00a7b/sgs setGiant \u00a7a\u2588 \u00a79\u2588");
      } else if (ArenaCfg.getArena(var1).get("Giant.Gold.X") == null && ArenaCfg.getArena(var1).get("Giant.Magenta.X") != null && ArenaCfg.getArena(var1).get("Giant.Green.X") != null && ArenaCfg.getArena(var1).get("Giant.Blue.X") == null) {
         var0.sendMessage(Main.PREFIX + "\u00a7b/sgs setGiant \u00a76\u2588 \u00a79\u2588");
      } else if (ArenaCfg.getArena(var1).get("Giant.Gold.X") != null && ArenaCfg.getArena(var1).get("Giant.Magenta.X") == null && ArenaCfg.getArena(var1).get("Giant.Green.X") == null && ArenaCfg.getArena(var1).get("Giant.Blue.X") != null) {
         var0.sendMessage(Main.PREFIX + "\u00a7b/sgs setGiant \u00a75\u2588 \u00a7a\u2588");
      } else if (ArenaCfg.getArena(var1).get("Giant.Gold.X") == null && ArenaCfg.getArena(var1).get("Giant.Magenta.X") != null && ArenaCfg.getArena(var1).get("Giant.Green.X") == null && ArenaCfg.getArena(var1).get("Giant.Blue.X") != null) {
         var0.sendMessage(Main.PREFIX + "\u00a7b/sgs setGiant \u00a76\u2588 \u00a7a\u2588");
      } else if (ArenaCfg.getArena(var1).get("Giant.Gold.X") != null && ArenaCfg.getArena(var1).get("Giant.Magenta.X") == null && ArenaCfg.getArena(var1).get("Giant.Green.X") != null && ArenaCfg.getArena(var1).get("Giant.Blue.X") == null) {
         var0.sendMessage(Main.PREFIX + "\u00a7b/sgs setGiant \u00a75\u2588 \u00a79\u2588");
      } else if (ArenaCfg.getArena(var1).get("Giant.Gold.X") == null && ArenaCfg.getArena(var1).get("Giant.Magenta.X") == null && ArenaCfg.getArena(var1).get("Giant.Green.X") == null && ArenaCfg.getArena(var1).get("Giant.Blue.X") != null) {
         var0.sendMessage(Main.PREFIX + "\u00a7b/sgs setGiant \u00a76\u2588 \u00a75\u2588 \u00a7a\u2588");
      } else if (ArenaCfg.getArena(var1).get("Giant.Gold.X") != null && ArenaCfg.getArena(var1).get("Giant.Magenta.X") == null && ArenaCfg.getArena(var1).get("Giant.Green.X") == null && ArenaCfg.getArena(var1).get("Giant.Blue.X") == null) {
         var0.sendMessage(Main.PREFIX + "\u00a7b/sgs setGiant \u00a75\u2588 \u00a7a\u2588 \u00a79\u2588");
      } else if (ArenaCfg.getArena(var1).get("Giant.Gold.X") == null && ArenaCfg.getArena(var1).get("Giant.Magenta.X") != null && ArenaCfg.getArena(var1).get("Giant.Green.X") == null && ArenaCfg.getArena(var1).get("Giant.Blue.X") == null) {
         var0.sendMessage(Main.PREFIX + "\u00a7b/sgs setGiant \u00a76\u2588 \u00a7a\u2588 \u00a79\u2588");
      } else if (ArenaCfg.getArena(var1).get("Giant.Gold.X") == null && ArenaCfg.getArena(var1).get("Giant.Magenta.X") == null && ArenaCfg.getArena(var1).get("Giant.Green.X") == null && ArenaCfg.getArena(var1).get("Giant.Blue.X") != null) {
         var0.sendMessage(Main.PREFIX + "\u00a7b/sgs setGiant \u00a76\u2588 \u00a7a\u2588 \u00a75\u2588");
      } else if (ArenaCfg.getArena(var1).get("Giant.Gold.X") == null && ArenaCfg.getArena(var1).get("Giant.Magenta.X") != null && ArenaCfg.getArena(var1).get("Giant.Green.X") != null && ArenaCfg.getArena(var1).get("Giant.Blue.X") != null) {
         var0.sendMessage(Main.PREFIX + "\u00a7b/sgs setGiant \u00a76\u2588");
      } else if (ArenaCfg.getArena(var1).get("Giant.Gold.X") != null && ArenaCfg.getArena(var1).get("Giant.Magenta.X") == null && ArenaCfg.getArena(var1).get("Giant.Green.X") != null && ArenaCfg.getArena(var1).get("Giant.Blue.X") != null) {
         var0.sendMessage(Main.PREFIX + "\u00a7b/sgs setGiant \u00a75\u2588");
      } else if (ArenaCfg.getArena(var1).get("Giant.Gold.X") != null && ArenaCfg.getArena(var1).get("Giant.Magenta.X") != null && ArenaCfg.getArena(var1).get("Giant.Green.X") == null && ArenaCfg.getArena(var1).get("Giant.Blue.X") != null) {
         var0.sendMessage(Main.PREFIX + "\u00a7b/sgs setGiant \u00a7a\u2588");
      } else if (ArenaCfg.getArena(var1).get("Giant.Gold.X") != null && ArenaCfg.getArena(var1).get("Giant.Magenta.X") != null && ArenaCfg.getArena(var1).get("Giant.Green.X") != null && ArenaCfg.getArena(var1).get("Giant.Blue.X") == null) {
         var0.sendMessage(Main.PREFIX + "\u00a7b/sgs setGiant \u00a79\u2588");
      } else if (ArenaCfg.getArena(var1).get("Giant.Gold.X") == null && ArenaCfg.getArena(var1).get("Giant.Magenta.X") == null && ArenaCfg.getArena(var1).get("Giant.Green.X") == null && ArenaCfg.getArena(var1).get("Giant.Blue.X") == null) {
         var0.sendMessage(Main.PREFIX + "\u00a7b/sgs setGiant \u00a76\u2588 \u00a75\u2588 \u00a7a\u2588 \u00a79\u2588");
      }

   }

   public static Location getGiant(String var0) {
      return new Location(Bukkit.getWorld(Main.choosenMap), ArenaCfg.getArena(Main.choosenMap).getDouble("Giant." + var0 + ".X"), ArenaCfg.getArena(Main.choosenMap).getDouble("Giant." + var0 + ".Y"), ArenaCfg.getArena(Main.choosenMap).getDouble("Giant." + var0 + ".Z"), (float)ArenaCfg.getArena(Main.choosenMap).getDouble("Giant." + var0 + ".Yaw"), (float)ArenaCfg.getArena(Main.choosenMap).getDouble("Giant." + var0 + ".Pitch"));
   }

   private static Location getGiantPos1(String var0) {
      return new Location(Bukkit.getWorld(Main.choosenMap), ArenaCfg.getArena(Main.choosenMap).getDouble("Region." + var0 + ".Giant.Pos1.X"), ArenaCfg.getArena(Main.choosenMap).getDouble("Region." + var0 + ".Giant.Pos1.Y"), ArenaCfg.getArena(Main.choosenMap).getDouble("Region." + var0 + ".Giant.Pos1.Z"));
   }

   private static Location getGiantPos2(String var0) {
      return new Location(Bukkit.getWorld(Main.choosenMap), ArenaCfg.getArena(Main.choosenMap).getDouble("Region." + var0 + ".Giant.Pos2.X"), ArenaCfg.getArena(Main.choosenMap).getDouble("Region." + var0 + ".Giant.Pos2.Y"), ArenaCfg.getArena(Main.choosenMap).getDouble("Region." + var0 + ".Giant.Pos2.Z"));
   }

   public static void loadGiantsRegions() {
      if (ArenaCfg.getArena(Main.choosenMap).get("Region.Gold.Giant.Pos1.X") != null || ArenaCfg.getArena(Main.choosenMap).get("Region.Gold.Giant.Pos2.X") != null) {
         Main.GoldGiantRegion = new Region(getGiantPos1("Gold"), getGiantPos2("Gold"));
      }

      if (ArenaCfg.getArena(Main.choosenMap).get("Region.Magenta.Giant.Pos1.X") != null || ArenaCfg.getArena(Main.choosenMap).get("Region.Magenta.Giant.Pos2.X") != null) {
         Main.MagentaGiantRegion = new Region(getGiantPos1("Magenta"), getGiantPos2("Magenta"));
      }

      if (ArenaCfg.getArena(Main.choosenMap).get("Region.Green.Giant.Pos1.X") != null || ArenaCfg.getArena(Main.choosenMap).get("Region.Green.Giant.Pos2.X") != null) {
         Main.GreenGiantRegion = new Region(getGiantPos1("Green"), getGiantPos2("Green"));
      }

      if (ArenaCfg.getArena(Main.choosenMap).get("Region.Blue.Giant.Pos1.X") != null || ArenaCfg.getArena(Main.choosenMap).get("Region.Blue.Giant.Pos2.X") != null) {
         Main.BlueGiantRegion = new Region(getGiantPos1("Blue"), getGiantPos2("Blue"));
      }

   }
}
