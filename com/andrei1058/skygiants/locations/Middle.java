package com.andrei1058.skygiants.locations;

import com.andrei1058.skygiants.Main;
import com.andrei1058.skygiants.configuration.ArenaCfg;
import java.io.File;
import java.io.IOException;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class Middle {
   public static void setMidPos1(Player var0) {
      File var1 = new File("plugins/SkyGiants1058/Arenas/" + var0.getWorld().getName() + ".yml");
      YamlConfiguration var2 = YamlConfiguration.loadConfiguration(var1);
      var2.set("Region.Middle.Pos1.X", var0.getLocation().getX());
      var2.set("Region.Middle.Pos1.Y", var0.getLocation().getY());
      var2.set("Region.Middle.Pos1.Z", var0.getLocation().getZ());

      try {
         var2.save(var1);
      } catch (IOException var4) {
         var4.printStackTrace();
      }

      var0.sendMessage(Main.PREFIX + "\u00a7bPos1 set!");
   }

   public static void setMidPos2(Player var0) {
      File var1 = new File("plugins/SkyGiants1058/Arenas/" + var0.getWorld().getName() + ".yml");
      YamlConfiguration var2 = YamlConfiguration.loadConfiguration(var1);
      var2.set("Region.Middle.Pos2.X", var0.getLocation().getX());
      var2.set("Region.Middle.Pos2.Y", var0.getLocation().getY());
      var2.set("Region.Middle.Pos2.Z", var0.getLocation().getZ());

      try {
         var2.save(var1);
      } catch (IOException var4) {
         var4.printStackTrace();
      }

      var0.sendMessage(Main.PREFIX + "\u00a7bPos2 set!");
   }

   private static Location getMidPos1() {
      return new Location(Bukkit.getWorld(Main.choosenMap), ArenaCfg.getArena(Main.choosenMap).getDouble("Region.Middle.Pos1.X"), ArenaCfg.getArena(Main.choosenMap).getDouble("Region.Middle.Pos1.Y"), ArenaCfg.getArena(Main.choosenMap).getDouble("Region.Middle.Pos1.Z"));
   }

   private static Location getMidPos2() {
      return new Location(Bukkit.getWorld(Main.choosenMap), ArenaCfg.getArena(Main.choosenMap).getDouble("Region.Middle.Pos2.X"), ArenaCfg.getArena(Main.choosenMap).getDouble("Region.Middle.Pos2.Y"), ArenaCfg.getArena(Main.choosenMap).getDouble("Region.Middle.Pos2.Z"));
   }

   public static void loadMidRegion() {
      if (ArenaCfg.getArena(Main.choosenMap).get("Region.Middle.Pos1.X") != null || ArenaCfg.getArena(Main.choosenMap).get("Region.Middle.Pos2.X") != null) {
         Main.MiddleRegion = new Region(getMidPos1(), getMidPos2());
      }

   }
}
