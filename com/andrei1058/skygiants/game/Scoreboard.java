package com.andrei1058.skygiants.game;

import com.andrei1058.skygiants.Main;
import com.andrei1058.skygiants.configuration.Messages;
import com.andrei1058.skygiants.configuration.Settings;
import com.andrei1058.skygiants.utils.Database;
import com.andrei1058.skygiants.utils.MySQL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Team;

public class Scoreboard {
   private static SimpleDateFormat style = new SimpleDateFormat("mm:ss");
   private static Date datew;
   private static Date dateg;
   private static Date dateb;
   public static String goldEntry;
   public static String magentaEntry;
   public static String greenEntry;
   public static String blueEntry;

   public static void sendScoreboard() {
      Main.sb = Main.sbmanager.getNewScoreboard();
      Main.obj = Main.sb.registerNewObjective("Test", "Test2");
      Team var0 = Main.sb.registerNewTeam("countdown");
      var0.addEntry(ChatColor.AQUA.toString());
      Team var1 = Main.sb.registerNewTeam("gold_health");
      var1.addEntry(goldEntry);
      Team var2 = Main.sb.registerNewTeam("magenta_health");
      var2.addEntry(magentaEntry);
      Team var3 = Main.sb.registerNewTeam("green_health");
      var3.addEntry(greenEntry);
      Team var4 = Main.sb.registerNewTeam("blue_health");
      var4.addEntry(blueEntry);
      Team var5 = Main.sb.registerNewTeam("gametime");
      var5.addEntry(ChatColor.RED.toString());
      Team var6 = Main.sb.registerNewTeam("beast_countdown");
      var6.addEntry(ChatColor.DARK_AQUA.toString());
      Main.obj.setDisplayName(Main.sbtitle);
      Main.obj.setDisplaySlot(DisplaySlot.SIDEBAR);
      var5.setPrefix(Messages.getMsg().getString("scoreboard.warmup").replace('&', '\u00a7'));
      Main.obj.getScore(ChatColor.RED.toString()).setScore(14);
      var0.setPrefix("03:00");
      Main.obj.getScore(ChatColor.AQUA.toString()).setScore(13);
      Score var7 = Main.obj.getScore("\u00a75               ");
      var7.setScore(12);
      Score var8 = Main.obj.getScore(Messages.getMsg().getString("scoreboard.giants").replace('&', '\u00a7'));
      var8.setScore(11);
      Main.obj.getScore(goldEntry).setScore(10);
      Main.obj.getScore(magentaEntry).setScore(9);
      Main.obj.getScore(greenEntry).setScore(8);
      Main.obj.getScore(blueEntry).setScore(7);
      Score var9 = Main.obj.getScore(ChatColor.RED + "          ");
      var9.setScore(6);
      Score var10 = Main.obj.getScore(Messages.getMsg().getString("scoreboard.beast-spawn").replace('&', '\u00a7'));
      var10.setScore(5);
      var6.setPrefix("03:00");
      Main.obj.getScore(ChatColor.DARK_AQUA.toString()).setScore(4);
      Score var11 = Main.obj.getScore("\u00a75               ");
      var11.setScore(3);
      Score var12 = Main.obj.getScore("\u00a77---------------");
      var12.setScore(2);
      Score var13 = Main.obj.getScore(Messages.getMsg().getString("scoreboard.footer").replace('&', '\u00a7'));
      var13.setScore(1);
      Main.SbGold = Main.sb.registerNewTeam("Gold");
      Main.SbGold.setPrefix("\u00a76");
      Main.SbMagenta = Main.sb.registerNewTeam("Magenta");
      Main.SbMagenta.setPrefix("\u00a7d");
      Main.SbGreen = Main.sb.registerNewTeam("Green");
      Main.SbGreen.setPrefix("\u00a7a");
      Main.SbBlue = Main.sb.registerNewTeam("Blue");
      Main.SbBlue.setPrefix("\u00a79");
      Iterator var14 = Bukkit.getOnlinePlayers().iterator();

      while(var14.hasNext()) {
         Player var15 = (Player)var14.next();
         var15.setScoreboard(Main.sb);
         if (Main.magentaPlayers.contains(var15)) {
            Main.SbMagenta.addEntry(var15.getName());
         } else if (Main.goldPlayers.contains(var15)) {
            Main.SbGold.addEntry(var15.getName());
         } else if (Main.greenPlayers.contains(var15)) {
            Main.SbGreen.addEntry(var15.getName());
         } else if (Main.bluePlayers.contains(var15)) {
            Main.SbBlue.addEntry(var15.getName());
         }
      }

   }

   public static void updateScoreboard() {
      if (Main.warmup.booleanValue()) {
         datew = new Date(Main.WarmupMilis -= 1000L);
      } else {
         dateg = new Date(Main.GameMilis -= 1000L);
         if (Main.BeastCountDown != 0L) {
            dateb = new Date(Main.BeastCountDown -= 1000L);
         }
      }

      Iterator var0 = Bukkit.getOnlinePlayers().iterator();

      while(var0.hasNext()) {
         Player var1 = (Player)var0.next();
         if (var1.getScoreboard() == null) {
            return;
         }

         if (Main.warmup.booleanValue()) {
            var1.getScoreboard().getTeam("countdown").setPrefix(String.valueOf(style.format(datew)).replace(".", ":"));
         } else {
            var1.getScoreboard().getTeam("countdown").setPrefix(String.valueOf(style.format(dateg)).replace(".", ":"));
            var1.getScoreboard().getTeam("gametime").setPrefix(Messages.getMsg().getString("scoreboard.time-left").replace('&', '\u00a7'));
            if (Main.BeastCountDown == 0L) {
               if (!Main.beastSpawned.booleanValue()) {
                  Main.nmsH.spawnBeast();
                  Main.beastSpawned = true;
                  Iterator var2 = Bukkit.getOnlinePlayers().iterator();

                  while(var2.hasNext()) {
                     Player var3 = (Player)var2.next();
                     var3.sendMessage(Main.PREFIX + Messages.getMsg().getString("beast-spawned").replace('&', '\u00a7'));
                  }
               } else {
                  var1.getScoreboard().getTeam("beast_countdown").setPrefix(Messages.getMsg().getString("scoreboard.spawned").replace('&', '\u00a7'));
               }
            } else {
               var1.getScoreboard().getTeam("beast_countdown").setPrefix(String.valueOf(style.format(dateb)).replace(".", ":"));
            }
         }

         var1.getScoreboard().getTeam("gold_health").setSuffix(Main.nmsH.gGH("Gold"));
         var1.getScoreboard().getTeam("magenta_health").setSuffix(Main.nmsH.gGH("Magenta"));
         var1.getScoreboard().getTeam("green_health").setSuffix(Main.nmsH.gGH("Green"));
         var1.getScoreboard().getTeam("blue_health").setSuffix(Main.nmsH.gGH("Blue"));
      }

   }

   public static void sendStats(final Player var0) {
      if (Main.DatabaseB.booleanValue()) {
         if (!Settings.getCfg().getBoolean("Database.scoreboard")) {
            return;
         }

         Bukkit.getScheduler().runTaskLater(Main.plugin, new Runnable() {
            public void run() {
               MySQL var1 = new MySQL(Database.host, Database.port, Database.database, Database.username, Database.password);
               var1.connect();
               Main.sb = Main.sbmanager.getNewScoreboard();
               Main.obj = Main.sb.registerNewObjective("Test", "Test2");
               Main.obj.setDisplayName(Messages.getMsg().getString("stats").replace('&', '\u00a7'));
               Main.obj.setDisplaySlot(DisplaySlot.SIDEBAR);
               Score var2 = Main.obj.getScore(Messages.getMsg().getString("deaths").replace('&', '\u00a7'));
               Score var3 = Main.obj.getScore(Messages.getMsg().getString("gold-earnt").replace('&', '\u00a7'));
               Score var4 = Main.obj.getScore(Messages.getMsg().getString("games-played").replace('&', '\u00a7'));
               Score var5 = Main.obj.getScore(Messages.getMsg().getString("kills").replace('&', '\u00a7'));
               Score var6 = Main.obj.getScore(Messages.getMsg().getString("beasts-slain").replace('&', '\u00a7'));
               Score var7 = Main.obj.getScore(Messages.getMsg().getString("rampages").replace('&', '\u00a7'));
               Score var8 = Main.obj.getScore(Messages.getMsg().getString("shutdowns").replace('&', '\u00a7'));
               Score var9 = Main.obj.getScore(Messages.getMsg().getString("victories").replace('&', '\u00a7'));
               var2.setScore(((Integer)var1.getScore(Database.table, "Deaths", var0)).intValue());
               var3.setScore(((Integer)var1.getScore(Database.table, "GoldEarnt", var0)).intValue());
               var4.setScore(((Integer)var1.getScore(Database.table, "GamesPlayed", var0)).intValue());
               var5.setScore(((Integer)var1.getScore(Database.table, "Kills", var0)).intValue());
               var6.setScore(((Integer)var1.getScore(Database.table, "BeastsSlain", var0)).intValue());
               var7.setScore(((Integer)var1.getScore(Database.table, "Rampages", var0)).intValue());
               var8.setScore(((Integer)var1.getScore(Database.table, "Shutdowns", var0)).intValue());
               var9.setScore(((Integer)var1.getScore(Database.table, "Victories", var0)).intValue());
               var0.setScoreboard(Main.sb);
               if (!var1.isDataExists(Database.table, "UUID", var0.getUniqueId().toString())) {
                  ArrayList var10 = new ArrayList();
                  var10.add("ID");
                  var10.add("Player");
                  var10.add("Kills");
                  var10.add("Deaths");
                  var10.add("BeastsSlain");
                  var10.add("GoldEarnt");
                  var10.add("GamesPlayed");
                  var10.add("Victories");
                  var10.add("Shutdowns");
                  var10.add("Rampages");
                  var10.add("UUID");
                  ArrayList var11 = new ArrayList();
                  var11.add("0");
                  var11.add(var0.getName().toString());
                  var11.add("0");
                  var11.add("0");
                  var11.add("0");
                  var11.add("0");
                  var11.add("0");
                  var11.add("0");
                  var11.add("0");
                  var11.add("0");
                  var11.add(var0.getUniqueId().toString());
                  var1.createDate(Database.table, var10, var11);
               }

               var1.close();
            }
         }, 10L);
      }

   }

   public static void restartSb() {
      Main.sb.clearSlot(DisplaySlot.SIDEBAR);
   }
}
