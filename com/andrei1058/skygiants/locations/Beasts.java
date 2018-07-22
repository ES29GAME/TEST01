package com.andrei1058.skygiants.locations;

import com.andrei1058.skygiants.Main;
import com.andrei1058.skygiants.configuration.ArenaCfg;
import java.io.File;
import java.io.IOException;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class Beasts {
   public static void setBeast(Player var0) {
      File var1 = new File("plugins/SkyGiants1058/Arenas/" + var0.getWorld().getName() + ".yml");
      YamlConfiguration var2 = YamlConfiguration.loadConfiguration(var1);
      var2.set("Beast.X", var0.getLocation().getX());
      var2.set("Beast.Y", var0.getLocation().getY());
      var2.set("Beast.Z", var0.getLocation().getZ());
      var2.set("Region.Beast.Pos1.X", var0.getLocation().getX() - 7.0D);
      var2.set("Region.Beast.Pos1.Y", var0.getLocation().getY() - 10.0D);
      var2.set("Region.Beast.Pos1.Z", var0.getLocation().getZ() - 7.0D);
      var2.set("Region.Beast.Pos2.X", var0.getLocation().getX() + 7.0D);
      var2.set("Region.Beast.Pos2.Y", var0.getLocation().getY() + 10.0D);
      var2.set("Region.Beast.Pos2.Z", var0.getLocation().getZ() + 7.0D);

      try {
         var2.save(var1);
      } catch (IOException var4) {
         var4.printStackTrace();
      }

      var0.sendMessage(Main.PREFIX + "\u00a7bBeast set!");
   }

   public static Location getBeastSpawn() {
      return new Location(Bukkit.getWorld(Main.choosenMap), ArenaCfg.getArena(Main.choosenMap).getDouble("Beast.X"), ArenaCfg.getArena(Main.choosenMap).getDouble("Beast.Y"), ArenaCfg.getArena(Main.choosenMap).getDouble("Beast.Z"));
   }

   private static Location getBeastPos1() {
      return new Location(Bukkit.getWorld(Main.choosenMap), ArenaCfg.getArena(Main.choosenMap).getDouble("Region.Beast.Pos1.X"), ArenaCfg.getArena(Main.choosenMap).getDouble("Region.Beast.Pos1.Y"), ArenaCfg.getArena(Main.choosenMap).getDouble("Region.Beast.Pos1.Z"));
   }

   private static Location getBeastPos2() {
      return new Location(Bukkit.getWorld(Main.choosenMap), ArenaCfg.getArena(Main.choosenMap).getDouble("Region.Beast.Pos2.X"), ArenaCfg.getArena(Main.choosenMap).getDouble("Region.Beast.Pos2.Y"), ArenaCfg.getArena(Main.choosenMap).getDouble("Region.Beast.Pos2.Z"));
   }

   public static void loadBeastRegion() {
      if (ArenaCfg.getArena(Main.choosenMap).get("Region.Beast.Pos1.X") != null || ArenaCfg.getArena(Main.choosenMap).get("Region.Beast.Pos2.X") != null) {
         Main.BeastRegion = new Region(getBeastPos1(), getBeastPos2());
      }

   }
}
