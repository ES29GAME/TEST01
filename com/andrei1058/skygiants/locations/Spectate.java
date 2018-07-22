package com.andrei1058.skygiants.locations;

import com.andrei1058.skygiants.Main;
import java.io.File;
import java.io.IOException;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class Spectate {
   public static void setSpect(Player var0) {
      File var1 = new File("plugins/SkyGiants1058/Arenas/" + var0.getLocation().getWorld().getName() + ".yml");
      YamlConfiguration var2 = YamlConfiguration.loadConfiguration(var1);
      var2.set("Spectate.X", var0.getLocation().getX());
      var2.set("Spectate.Y", var0.getLocation().getY());
      var2.set("Spectate.Z", var0.getLocation().getZ());
      var2.set("Spectate.Pitch", var0.getLocation().getPitch());
      var2.set("Spectate.Yaw", var0.getLocation().getYaw());

      try {
         var2.save(var1);
         var0.sendMessage(Main.PREFIX + "\u00a7aArena's spectate location set!");
      } catch (IOException var4) {
         var4.printStackTrace();
      }

   }

   public static Location getSpect(String var0) {
      File var1 = new File("plugins/SkyGiants1058/Arenas/" + var0 + ".yml");
      YamlConfiguration var2 = YamlConfiguration.loadConfiguration(var1);
      return new Location(Bukkit.getWorld(var0), var2.getDouble("Spectate.X"), var2.getDouble("Spectate.Y"), var2.getDouble("Spectate.Z"), (float)var2.getDouble("Spectate.Yaw"), (float)var2.getDouble("Spectate.Pitch"));
   }
}
