package com.andrei1058.skygiants.locations;

import com.andrei1058.skygiants.Main;
import com.andrei1058.skygiants.configuration.ArenaCfg;
import java.io.File;
import java.io.IOException;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class Villagers {
   public static void setVillager(String var0, Player var1) {
      File var2 = new File("plugins/SkyGiants1058/Arenas/" + var1.getWorld().getName() + ".yml");
      YamlConfiguration var3 = YamlConfiguration.loadConfiguration(var2);
      if (var0.equalsIgnoreCase("gold")) {
         var3.set("Villager.Gold.X", var1.getLocation().getX());
         var3.set("Villager.Gold.Y", var1.getLocation().getY());
         var3.set("Villager.Gold.Z", var1.getLocation().getZ());
         var3.set("Villager.Gold.Yaw", var1.getLocation().getYaw());
         var3.set("Villager.Gold.Pitch", var1.getLocation().getPitch());
         var3.set("Region.Gold.Villager.Pos1.X", var1.getLocation().getX() - 2.0D);
         var3.set("Region.Gold.Villager.Pos1.Y", var1.getLocation().getY() - 2.0D);
         var3.set("Region.Gold.Villager.Pos1.Z", var1.getLocation().getZ() - 2.0D);
         var3.set("Region.Gold.Villager.Pos2.X", var1.getLocation().getX() + 2.0D);
         var3.set("Region.Gold.Villager.Pos2.Y", var1.getLocation().getY() + 2.0D);
         var3.set("Region.Gold.Villager.Pos2.Z", var1.getLocation().getZ() + 2.0D);

         try {
            var3.save(var2);
         } catch (IOException var8) {
            var8.printStackTrace();
         }

         var1.sendMessage(Main.PREFIX + "\u00a76Gold \u00a7bVillager set!");
      } else if (var0.equalsIgnoreCase("magenta")) {
         var3.set("Villager.Magenta.X", var1.getLocation().getX());
         var3.set("Villager.Magenta.Y", var1.getLocation().getY());
         var3.set("Villager.Magenta.Z", var1.getLocation().getZ());
         var3.set("Villager.Magenta.Yaw", var1.getLocation().getYaw());
         var3.set("Villager.Magenta.Pitch", var1.getLocation().getPitch());
         var3.set("Region.Magenta.Villager.Pos1.X", var1.getLocation().getX() - 2.0D);
         var3.set("Region.Magenta.Villager.Pos1.Y", var1.getLocation().getY() - 2.0D);
         var3.set("Region.Magenta.Villager.Pos1.Z", var1.getLocation().getZ() - 2.0D);
         var3.set("Region.Magenta.Villager.Pos2.X", var1.getLocation().getX() + 2.0D);
         var3.set("Region.Magenta.Villager.Pos2.Y", var1.getLocation().getY() + 2.0D);
         var3.set("Region.Magenta.Villager.Pos2.Z", var1.getLocation().getZ() + 2.0D);

         try {
            var3.save(var2);
         } catch (IOException var7) {
            var7.printStackTrace();
         }

         var1.sendMessage(Main.PREFIX + "\u00a75Magenta \u00a7bVillager set!");
      } else if (var0.equalsIgnoreCase("green")) {
         var3.set("Villager.Green.X", var1.getLocation().getX());
         var3.set("Villager.Green.Y", var1.getLocation().getY());
         var3.set("Villager.Green.Z", var1.getLocation().getZ());
         var3.set("Villager.Green.Yaw", var1.getLocation().getYaw());
         var3.set("Villager.Green.Pitch", var1.getLocation().getPitch());
         var3.set("Region.Green.Villager.Pos1.X", var1.getLocation().getX() - 2.0D);
         var3.set("Region.Green.Villager.Pos1.Y", var1.getLocation().getY() - 2.0D);
         var3.set("Region.Green.Villager.Pos1.Z", var1.getLocation().getZ() - 2.0D);
         var3.set("Region.Green.Villager.Pos2.X", var1.getLocation().getX() + 2.0D);
         var3.set("Region.Green.Villager.Pos2.Y", var1.getLocation().getY() + 2.0D);
         var3.set("Region.Green.Villager.Pos2.Z", var1.getLocation().getZ() + 2.0D);

         try {
            var3.save(var2);
         } catch (IOException var6) {
            var6.printStackTrace();
         }

         var1.sendMessage(Main.PREFIX + "\u00a7aGreen \u00a7bVillager set!");
      } else if (var0.equalsIgnoreCase("blue")) {
         var3.set("Villager.Blue.X", var1.getLocation().getX());
         var3.set("Villager.Blue.Y", var1.getLocation().getY());
         var3.set("Villager.Blue.Z", var1.getLocation().getZ());
         var3.set("Villager.Blue.Yaw", var1.getLocation().getYaw());
         var3.set("Villager.Blue.Pitch", var1.getLocation().getPitch());
         var3.set("Region.Blue.Villager.Pos1.X", var1.getLocation().getX() - 2.0D);
         var3.set("Region.Blue.Villager.Pos1.Y", var1.getLocation().getY() - 2.0D);
         var3.set("Region.Blue.Villager.Pos1.Z", var1.getLocation().getZ() - 2.0D);
         var3.set("Region.Blue.Villager.Pos2.X", var1.getLocation().getX() + 2.0D);
         var3.set("Region.Blue.Villager.Pos2.Y", var1.getLocation().getY() + 2.0D);
         var3.set("Region.Blue.Villager.Pos2.Z", var1.getLocation().getZ() + 2.0D);

         try {
            var3.save(var2);
         } catch (IOException var5) {
            var5.printStackTrace();
         }

         var1.sendMessage(Main.PREFIX + "\u00a79Blue \u00a7bVillager set!");
      } else {
         var1.sendMessage(Main.PREFIX + "\u00a7bChoices: &6Gold&b, &5Magenta&b, &aGreen&b, &9Blue&b.");
      }

   }

   public static void checkVillagers(Player var0) {
      String var1 = var0.getLocation().getWorld().getName();
      if (ArenaCfg.getArena(var1).get("Villager.Gold.X") == null && ArenaCfg.getArena(var1).get("Villager.Magenta.X") == null && ArenaCfg.getArena(var1).get("Villager.Green.X") != null && ArenaCfg.getArena(var1).get("Villager.Blue.X") != null) {
         var0.sendMessage(Main.PREFIX + "\u00a7b/sgs setVillager \u00a76\u2588 \u00a75\u2588");
      } else if (ArenaCfg.getArena(var1).get("Villager.Gold.X") != null && ArenaCfg.getArena(var1).get("Villager.Magenta.X") == null && ArenaCfg.getArena(var1).get("Villager.Green.X") == null && ArenaCfg.getArena(var1).get("Villager.Blue.X") != null) {
         var0.sendMessage(Main.PREFIX + "\u00a7b/sgs setVillager \u00a75\u2588 \u00a7a\u2588");
      } else if (ArenaCfg.getArena(var1).get("Villager.Gold.X") != null && ArenaCfg.getArena(var1).get("Villager.Magenta.X") != null && ArenaCfg.getArena(var1).get("Villager.Green.X") == null && ArenaCfg.getArena(var1).get("Villager.Blue.X") == null) {
         var0.sendMessage(Main.PREFIX + "\u00a7b/sgs setVillager \u00a7a\u2588 \u00a79\u2588");
      } else if (ArenaCfg.getArena(var1).get("Villager.Gold.X") == null && ArenaCfg.getArena(var1).get("Villager.Magenta.X") != null && ArenaCfg.getArena(var1).get("Villager.Green.X") != null && ArenaCfg.getArena(var1).get("Villager.Blue.X") == null) {
         var0.sendMessage(Main.PREFIX + "\u00a7b/sgs setVillager \u00a76\u2588 \u00a79\u2588");
      } else if (ArenaCfg.getArena(var1).get("Villager.Gold.X") != null && ArenaCfg.getArena(var1).get("Villager.Magenta.X") == null && ArenaCfg.getArena(var1).get("Villager.Green.X") == null && ArenaCfg.getArena(var1).get("Villager.Blue.X") != null) {
         var0.sendMessage(Main.PREFIX + "\u00a7b/sgs setVillager \u00a75\u2588 \u00a7a\u2588");
      } else if (ArenaCfg.getArena(var1).get("Villager.Gold.X") == null && ArenaCfg.getArena(var1).get("Villager.Magenta.X") != null && ArenaCfg.getArena(var1).get("Villager.Green.X") == null && ArenaCfg.getArena(var1).get("Villager.Blue.X") != null) {
         var0.sendMessage(Main.PREFIX + "\u00a7b/sgs setVillager \u00a76\u2588 \u00a7a\u2588");
      } else if (ArenaCfg.getArena(var1).get("Villager.Gold.X") != null && ArenaCfg.getArena(var1).get("Villager.Magenta.X") == null && ArenaCfg.getArena(var1).get("Villager.Green.X") != null && ArenaCfg.getArena(var1).get("Villager.Blue.X") == null) {
         var0.sendMessage(Main.PREFIX + "\u00a7b/sgs setVillager \u00a75\u2588 \u00a79\u2588");
      } else if (ArenaCfg.getArena(var1).get("Villager.Gold.X") == null && ArenaCfg.getArena(var1).get("Villager.Magenta.X") == null && ArenaCfg.getArena(var1).get("Villager.Green.X") == null && ArenaCfg.getArena(var1).get("Villager.Blue.X") != null) {
         var0.sendMessage(Main.PREFIX + "\u00a7b/sgs setVillager \u00a76\u2588 \u00a75\u2588 \u00a7a\u2588");
      } else if (ArenaCfg.getArena(var1).get("Villager.Gold.X") != null && ArenaCfg.getArena(var1).get("Villager.Magenta.X") == null && ArenaCfg.getArena(var1).get("Villager.Green.X") == null && ArenaCfg.getArena(var1).get("Villager.Blue.X") == null) {
         var0.sendMessage(Main.PREFIX + "\u00a7b/sgs setVillager \u00a75\u2588 \u00a7a\u2588 \u00a79\u2588");
      } else if (ArenaCfg.getArena(var1).get("Villager.Gold.X") == null && ArenaCfg.getArena(var1).get("Villager.Magenta.X") != null && ArenaCfg.getArena(var1).get("Villager.Green.X") == null && ArenaCfg.getArena(var1).get("Villager.Blue.X") == null) {
         var0.sendMessage(Main.PREFIX + "\u00a7b/sgs setVillager \u00a76\u2588 \u00a7a\u2588 \u00a79\u2588");
      } else if (ArenaCfg.getArena(var1).get("Villager.Gold.X") == null && ArenaCfg.getArena(var1).get("Villager.Magenta.X") == null && ArenaCfg.getArena(var1).get("Villager.Green.X") == null && ArenaCfg.getArena(var1).get("Villager.Blue.X") != null) {
         var0.sendMessage(Main.PREFIX + "\u00a7b/sgs setVillager \u00a76\u2588 \u00a7a\u2588 \u00a75\u2588");
      } else if (ArenaCfg.getArena(var1).get("Villager.Gold.X") == null && ArenaCfg.getArena(var1).get("Villager.Magenta.X") != null && ArenaCfg.getArena(var1).get("Villager.Green.X") != null && ArenaCfg.getArena(var1).get("Villager.Blue.X") != null) {
         var0.sendMessage(Main.PREFIX + "\u00a7b/sgs setVillager \u00a76\u2588");
      } else if (ArenaCfg.getArena(var1).get("Villager.Gold.X") != null && ArenaCfg.getArena(var1).get("Villager.Magenta.X") == null && ArenaCfg.getArena(var1).get("Villager.Green.X") != null && ArenaCfg.getArena(var1).get("Villager.Blue.X") != null) {
         var0.sendMessage(Main.PREFIX + "\u00a7b/sgs setVillager \u00a75\u2588");
      } else if (ArenaCfg.getArena(var1).get("Villager.Gold.X") != null && ArenaCfg.getArena(var1).get("Villager.Magenta.X") != null && ArenaCfg.getArena(var1).get("Villager.Green.X") == null && ArenaCfg.getArena(var1).get("Villager.Blue.X") != null) {
         var0.sendMessage(Main.PREFIX + "\u00a7b/sgs setVillager \u00a7a\u2588");
      } else if (ArenaCfg.getArena(var1).get("Villager.Gold.X") != null && ArenaCfg.getArena(var1).get("Villager.Magenta.X") != null && ArenaCfg.getArena(var1).get("Villager.Green.X") != null && ArenaCfg.getArena(var1).get("Villager.Blue.X") == null) {
         var0.sendMessage(Main.PREFIX + "\u00a7b/sgs setVillager \u00a79\u2588");
      } else if (ArenaCfg.getArena(var1).get("Villager.Gold.X") == null && ArenaCfg.getArena(var1).get("Villager.Magenta.X") == null && ArenaCfg.getArena(var1).get("Villager.Green.X") == null && ArenaCfg.getArena(var1).get("Villager.Blue.X") == null) {
         var0.sendMessage(Main.PREFIX + "\u00a7b/sgs setVillager \u00a76\u2588 \u00a75\u2588 \u00a7a\u2588 \u00a79\u2588");
      }

   }

   public static Location getVillager(String var0) {
      return new Location(Bukkit.getWorld(Main.choosenMap), ArenaCfg.getArena(Main.choosenMap).getDouble("Villager." + var0 + ".X"), ArenaCfg.getArena(Main.choosenMap).getDouble("Villager." + var0 + ".Y"), ArenaCfg.getArena(Main.choosenMap).getDouble("Villager." + var0 + ".Z"), (float)ArenaCfg.getArena(Main.choosenMap).getDouble("Villager." + var0 + ".Yaw"), (float)ArenaCfg.getArena(Main.choosenMap).getDouble("Villager." + var0 + ".Pitch"));
   }

   private static Location getVillagerPos1(String var0) {
      return new Location(Bukkit.getWorld(Main.choosenMap), ArenaCfg.getArena(Main.choosenMap).getDouble("Region." + var0 + ".Villager.Pos1.X"), ArenaCfg.getArena(Main.choosenMap).getDouble("Region." + var0 + ".Villager.Pos1.Y"), ArenaCfg.getArena(Main.choosenMap).getDouble("Region." + var0 + ".Villager.Pos1.Z"));
   }

   private static Location getVillagerPos2(String var0) {
      return new Location(Bukkit.getWorld(Main.choosenMap), ArenaCfg.getArena(Main.choosenMap).getDouble("Region." + var0 + ".Villager.Pos2.X"), ArenaCfg.getArena(Main.choosenMap).getDouble("Region." + var0 + ".Villager.Pos2.Y"), ArenaCfg.getArena(Main.choosenMap).getDouble("Region." + var0 + ".Villager.Pos2.Z"));
   }

   public static void loadVillagersRegions() {
      if (ArenaCfg.getArena(Main.choosenMap).get("Region.Gold.Villager.Pos1.X") != null || ArenaCfg.getArena(Main.choosenMap).get("Region.Gold.Villager.Pos2.X") != null) {
         Main.GoldVillagerRegion = new Region(getVillagerPos1("Gold"), getVillagerPos2("Gold"));
      }

      if (ArenaCfg.getArena(Main.choosenMap).get("Region.Magenta.Villager.Pos1.X") != null || ArenaCfg.getArena(Main.choosenMap).get("Region.Magenta.Villager.Pos2.X") != null) {
         Main.MagentaVillagerRegion = new Region(getVillagerPos1("Magenta"), getVillagerPos2("Magenta"));
      }

      if (ArenaCfg.getArena(Main.choosenMap).get("Region.Green.Villager.Pos1.X") != null || ArenaCfg.getArena(Main.choosenMap).get("Region.Green.Villager.Pos2.X") != null) {
         Main.GreenVillagerRegion = new Region(getVillagerPos1("Green"), getVillagerPos2("Green"));
      }

      if (ArenaCfg.getArena(Main.choosenMap).get("Region.Blue.Villager.Pos1.X") != null || ArenaCfg.getArena(Main.choosenMap).get("Region.Blue.Villager.Pos2.X") != null) {
         Main.BlueVillagerRegion = new Region(getVillagerPos1("Blue"), getVillagerPos2("Blue"));
      }

   }
}
