package com.andrei1058.skygiants.utils;

import com.andrei1058.skygiants.Main;
import com.andrei1058.skygiants.configuration.Settings;
import com.andrei1058.skygiants.game.GameState;
import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Database {
   public static String host = Settings.getCfg().getString("Database.host");
   public static int port = Settings.getCfg().getInt("Database.port");
   public static String database = Settings.getCfg().getString("Database.database");
   public static String username = Settings.getCfg().getString("Database.username");
   public static String password = Settings.getCfg().getString("Database.password");
   public static String table = "SkyGiants_stats";

   public static void setupDatabase() {
      Bukkit.getScheduler().runTaskLater(Main.plugin, new Runnable() {
         public void run() {
            if (Main.DatabaseB.booleanValue()) {
               ArrayList var1 = new ArrayList();
               var1.add("ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY");
               var1.add("Player char(36)");
               var1.add("Kills INT");
               var1.add("Deaths INT");
               var1.add("BeastsSlain INT");
               var1.add("GoldEarnt INT");
               var1.add("GamesPlayed INT");
               var1.add("Victories INT");
               var1.add("Shutdowns INT");
               var1.add("Rampages INT");
               var1.add("UUID char(36)");
               MySQL var2 = new MySQL(Database.host, Database.port, Database.database, Database.username, Database.password);
               var2.createTable(Database.table, var1);
               Main.plugin.getLogger().info("Database connected!");
            }

         }
      }, 7L);
   }

   public static void saveStats(Player var0) {
      if (Main.DatabaseB.booleanValue()) {
         MySQL var1 = new MySQL(host, port, database, username, password);
         var1.connect();
         Object var2 = var1.getScore(table, "Kills", var0);
         Object var3 = var1.getScore(table, "Deaths", var0);
         Object var4 = var1.getScore(table, "GoldEarnt", var0);
         Object var5 = var1.getScore(table, "GamesPlayed", var0);
         Object var6 = var1.getScore(table, "Victories", var0);
         Object var7 = var1.getScore(table, "BeastsSlain", var0);
         int var8 = ((Integer)var2).intValue();
         int var9 = ((Integer)var3).intValue();
         int var10 = ((Integer)var4).intValue();
         int var11 = ((Integer)var5).intValue();
         int var12 = ((Integer)var7).intValue();
         if (Main.kills.containsKey(var0)) {
            var8 += ((Integer)Main.kills.get(var0)).intValue();
         }

         if (Main.deaths.containsKey(var0)) {
            var9 += ((Integer)Main.deaths.get(var0)).intValue();
         }

         if (Main.money.containsKey(var0)) {
            var10 += ((Integer)Main.money.get(var0)).intValue() / 1000;
         }

         ++var11;
         if (Main.beastsSlain.containsKey(var0)) {
            var12 += ((Integer)Main.beastsSlain.get(var0)).intValue();
         }

         int var13;
         if (Main.STATUS == GameState.RESTARTING) {
            if (Main.players.contains(var0)) {
               var13 = ((Integer)var6).intValue() + 1;
            } else {
               var13 = ((Integer)var6).intValue();
            }
         } else {
            var13 = ((Integer)var6).intValue();
         }

         ArrayList var14 = new ArrayList();
         var14.add("Player");
         var14.add("Kills");
         var14.add("Deaths");
         var14.add("BeastsSlain");
         var14.add("GoldEarnt");
         var14.add("GamesPlayed");
         var14.add("Victories");
         var14.add("Shutdowns");
         var14.add("Rampages");
         ArrayList var15 = new ArrayList();
         var15.add(var0.getName().toString());
         var15.add(String.valueOf(var8));
         var15.add(String.valueOf(var9));
         var15.add(String.valueOf(var12));
         var15.add(String.valueOf(var10));
         var15.add(String.valueOf(var11));
         var15.add(String.valueOf(var13));
         var15.add("0");
         var15.add("0");
         var1.setData(table, var14, var15, "UUID", "=", var0.getUniqueId().toString());
      }

   }
}
