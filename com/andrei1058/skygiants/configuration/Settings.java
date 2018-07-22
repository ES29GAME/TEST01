package com.andrei1058.skygiants.configuration;

import com.andrei1058.skygiants.Main;
import com.andrei1058.skygiants.game.GameState;
import com.andrei1058.skygiants.game.Scoreboard;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class Settings {
   private static File file = new File("plugins/SkyGiants1058/config.yml");
   private static YamlConfiguration yml;

   public static void setupConfig() {
      if (!file.exists()) {
         file.mkdir();
      }

      yml.options().header("SkyGiants Plugin by andrei1058");
      yml.addDefault("setup", true);
      yml.addDefault("language", "en");
      yml.addDefault("skygiants-mini", true);
      yml.addDefault("allow-spectate", true);
      yml.addDefault("countdowns.lobby.empty", Integer.valueOf(300));
      yml.addDefault("countdowns.lobby.half", Integer.valueOf(60));
      yml.addDefault("countdowns.lobby.full", Integer.valueOf(25));
      yml.addDefault("countdowns.game", Integer.valueOf(1800));
      yml.addDefault("countdowns.warmup", Integer.valueOf(90));
      yml.addDefault("countdowns.restart", Integer.valueOf(12));
      yml.addDefault("gamemode-spectator", false);
      yml.addDefault("lobby-server", "skygiants");
      yml.addDefault("restart-cmd", "restart");
      yml.addDefault("Database.enable", false);
      yml.addDefault("Database.host", "localhost");
      yml.addDefault("Database.port", Integer.valueOf(3306));
      yml.addDefault("Database.database", "SkyGiants");
      yml.addDefault("Database.username", "root");
      yml.addDefault("Database.password", "pass");
      yml.addDefault("Database.scoreboard", true);
      yml.addDefault("reward-command.use", false);
      yml.addDefault("reward-command.cmd", "this command will be executed as console, for each winner, use %player% for payer name");
      yml.options().copyDefaults(true);

      try {
         yml.save(file);
      } catch (IOException var1) {
         var1.printStackTrace();
      }

      Bukkit.getScheduler().runTaskLater(Main.plugin, new Runnable() {
         public void run() {
            File var1 = new File("plugins/SkyGiants1058/messages_" + Settings.getCfg().getString("language") + ".yml");
            if (!var1.exists()) {
               Main.Language = "en";
            } else {
               Main.Language = Settings.getCfg().getString("language");
            }

            if (Settings.getCfg().getBoolean("setup")) {
               Main.MAINTENANCE = true;
            } else {
               Main.MAINTENANCE = false;
               Main.STATUS = GameState.LOBBY;
            }

            if (!Settings.getCfg().getBoolean("skygiants-mini")) {
               if (Messages.getMsg().get("prefix") != null) {
                  Main.PREFIX = Messages.getMsg().getString("prefix");
               } else {
                  Main.PREFIX = "\u00a78\u258e \u00a73\u00a7lSky\u00a7b\u00a7lGiants\u00a78 \u258f ";
               }

               Main.miniSG = false;
               Main.sbtitle = Messages.getMsg().getString("scoreboard.title1").replace('&', '\u00a7');
               Main.BeastHealth = 350;
            } else {
               Main.miniSG = true;
               if (Messages.getMsg().get("mini-prefix") != null) {
                  Main.PREFIX = Messages.getMsg().getString("mini-prefix");
               } else {
                  Main.PREFIX = "\u00a78\u258e \u00a73\u00a7lSky\u00a7b\u00a7lGiants\u00a7a\u00a7l:Mini\u00a78 \u258f ";
               }

               Main.MaxInTeam = 2;
               Main.GiantHealth = 510;
               Main.sbtitle = Messages.getMsg().getString("scoreboard.title2").replace('&', '\u00a7');
               Main.BeastHealth = 210;
            }

            if (Settings.getCfg().get("Database.enable") != null) {
               Main.DatabaseB = Settings.getCfg().getBoolean("Database.enable");
            }

            if (Settings.getCfg().get("MainLobby") != null) {
               Iterator var2 = Bukkit.getWorld(Settings.getCfg().getString("MainLobby.World")).getEntities().iterator();

               while(var2.hasNext()) {
                  Entity var3 = (Entity)var2.next();
                  if (var3.getType() != EntityType.PLAYER) {
                     var3.remove();
                  }
               }
            }

            if (Settings.getCfg().get("allow-spectate") != null) {
               Main.allowSpectate = Settings.getCfg().getBoolean("allow-spectate");
            }

            Scoreboard.goldEntry = Messages.getMsg().getString("scoreboard.gold").replace('&', '\u00a7');
            Scoreboard.magentaEntry = Messages.getMsg().getString("scoreboard.magenta").replace('&', '\u00a7');
            Scoreboard.blueEntry = Messages.getMsg().getString("scoreboard.blue").replace('&', '\u00a7');
            Scoreboard.greenEntry = Messages.getMsg().getString("scoreboard.green").replace('&', '\u00a7');
            Main.LobbyCountdown = Settings.getCfg().getInt("countdowns.lobby.empty");
            Main.GameCountdown = Settings.getCfg().getInt("countdowns.game");
            Main.WarmupCountdown = Settings.getCfg().getInt("countdowns.warmup");
            Main.RestartCountdown = Settings.getCfg().getInt("countdowns.restart");
            Main.loaded = true;
         }
      }, 5L);
   }

   public static FileConfiguration getCfg() {
      YamlConfiguration var0 = YamlConfiguration.loadConfiguration(file);
      return var0;
   }

   public static File getCfgFile() {
      return file;
   }

   public static void addArenaToList(Player var0) {
      if (getCfg().get("Arenas") != null) {
         List var1 = getCfg().getStringList("Arenas");
         var1.add(var0.getLocation().getWorld().getName());
         yml.set("Arenas", var1);

         try {
            yml.save(file);
         } catch (IOException var4) {
            var4.printStackTrace();
         }
      } else {
         ArrayList var5 = new ArrayList();
         var5.add(var0.getLocation().getWorld().getName());
         yml.set("Arenas", var5);

         try {
            yml.save(file);
         } catch (IOException var3) {
            var3.printStackTrace();
         }
      }

   }

   public static void setMainLobby(Player var0) {
      yml.set("MainLobby.World", var0.getLocation().getWorld().getName());
      yml.set("MainLobby.X", var0.getLocation().getX());
      yml.set("MainLobby.Y", var0.getLocation().getY());
      yml.set("MainLobby.Z", var0.getLocation().getZ());
      yml.set("MainLobby.Yaw", var0.getLocation().getYaw());
      yml.set("MainLobby.Pitch", var0.getLocation().getPitch());
      var0.sendMessage(Main.PREFIX + "\u00a7bMain lobby set!");

      try {
         yml.save(file);
      } catch (IOException var2) {
         var2.printStackTrace();
      }

   }

   public static Location getMainLobby() {
      return new Location(Bukkit.getWorld(getCfg().getString("MainLobby.World")), getCfg().getDouble("MainLobby.X"), getCfg().getDouble("MainLobby.Y"), getCfg().getDouble("MainLobby.Z"), (float)getCfg().getDouble("MainLobby.Yaw"), (float)getCfg().getDouble("MainLobby.Pitch"));
   }

   public static void removeArena(String var0) {
      List var1 = yml.getStringList("Arenas");
      var1.remove(var0);
      yml.set("Arenas", var1);

      try {
         yml.save(file);
      } catch (IOException var3) {
         var3.printStackTrace();
      }

      File var2 = new File("plugins/SkyGiants1058/Arenas/" + var0 + ".yml");
      var2.delete();
   }

   public static void toggleSetup(Player var0) {
      if (Main.MAINTENANCE.booleanValue()) {
         Main.MAINTENANCE = false;
         yml.set("setup", false);

         try {
            yml.save(file);
         } catch (IOException var3) {
            var3.printStackTrace();
         }

         var0.sendMessage(Main.PREFIX + "\u00a76Setup done!");
         var0.sendMessage(Main.PREFIX + "\u00a76The server is restarting!");
         Bukkit.getScheduler().runTaskLater(Main.plugin, new Runnable() {
            public void run() {
               Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "restart");
            }
         }, 60L);
      } else {
         Main.MAINTENANCE = true;
         yml.set("setup", true);

         try {
            yml.save(file);
         } catch (IOException var2) {
            var2.printStackTrace();
         }
      }

   }

   static {
      yml = YamlConfiguration.loadConfiguration(file);
   }
}
