package com.andrei1058.skygiants.configuration;

import com.andrei1058.skygiants.Main;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class ArenaCfg {
   public static void CreateArenaCfg(String var0, Player var1) {
      File var2 = new File("plugins/SkyGiants1058/Arenas/" + var0 + ".yml");
      YamlConfiguration var3 = YamlConfiguration.loadConfiguration(var2);
      if (!var2.exists()) {
         try {
            var2.createNewFile();
         } catch (IOException var5) {
            var5.printStackTrace();
         }
      }

   }

   public static FileConfiguration getArena(String var0) {
      File var1 = new File("plugins/SkyGiants1058/Arenas/" + var0 + ".yml");
      YamlConfiguration var2 = YamlConfiguration.loadConfiguration(var1);
      return var2;
   }

   public static void registerArenas() {
      if (Settings.getCfg().get("Arenas") != null) {
         Iterator var0 = Settings.getCfg().getStringList("Arenas").iterator();

         while(var0.hasNext()) {
            String var1 = (String)var0.next();
            Main.mapsVoting.put(var1, Integer.valueOf(0));
         }

      }
   }

   public static void listArenaVotes(final Player var0) {
      Bukkit.getScheduler().runTaskLater(Main.plugin, new Runnable() {
         public void run() {
            var0.sendMessage(Main.PREFIX + Messages.getMsg().getString("vote-map").replace('&', '\u00a7'));
            var0.sendMessage(Main.PREFIX + Messages.getMsg().getString("vote-map2").replace('&', '\u00a7'));
            int var1 = 0;
            Iterator var2 = Settings.getCfg().getStringList("Arenas").iterator();

            while(var2.hasNext()) {
               String var3 = (String)var2.next();
               Player var10000 = var0;
               StringBuilder var10001 = (new StringBuilder()).append(Main.PREFIX);
               String var10002 = Messages.getMsg().getString("map-choices");
               ++var1;
               var10000.sendMessage(var10001.append(var10002.replace("{number}", String.valueOf(var1)).replace("{map}", var3).replace("{votes}", String.valueOf(Main.mapsVoting.get(var3))).replace('&', '\u00a7')).toString());
            }

            boolean var4 = false;
         }
      }, 2L);
   }
}
