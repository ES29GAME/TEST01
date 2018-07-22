package com.andrei1058.skygiants.commands;

import com.andrei1058.skygiants.Main;
import com.andrei1058.skygiants.configuration.ArenaCfg;
import com.andrei1058.skygiants.configuration.Settings;
import com.andrei1058.skygiants.locations.Beasts;
import com.andrei1058.skygiants.locations.Borders;
import com.andrei1058.skygiants.locations.Giants;
import com.andrei1058.skygiants.locations.Middle;
import com.andrei1058.skygiants.locations.Spawns;
import com.andrei1058.skygiants.locations.Spectate;
import com.andrei1058.skygiants.locations.Villagers;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.WorldCreator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetupCmds implements CommandExecutor {
   private static HashMap createArena = new HashMap();
   private static HashMap setupLocation = new HashMap();
   private static HashMap editArena = new HashMap();

   public boolean onCommand(CommandSender var1, Command var2, String var3, final String[] var4) {
      if (var2.getName().equalsIgnoreCase("sgs")) {
         if (!(var1 instanceof Player)) {
            return true;
         }

         final Player var5 = (Player)var1;
         if (!var5.isOp()) {
            return true;
         }

         if (!Main.MAINTENANCE.booleanValue()) {
            var5.sendMessage(Main.PREFIX + "\u00a7cThis arena in not in setup mode!");
            var5.sendMessage(Main.PREFIX + "\u00a77Go in your config file and set setup-mode = true and restart the server.");
            return true;
         }

         if (var4.length == 0) {
            if (var5.getLocation().getWorld().getName().equalsIgnoreCase((String)createArena.get(var5))) {
               remainingCmds(var5);
               return true;
            }

            if (var5.getWorld().getName().equalsIgnoreCase((String)editArena.get(var5))) {
               listArenaCmds(var5);
               return true;
            }

            listCmds(var5);
         } else {
            if (var4[0].equalsIgnoreCase("addarena")) {
               if (var4.length == 2) {
                  Bukkit.getServer().createWorld(new WorldCreator(var4[1]));
                  var5.sendMessage(Main.PREFIX + "\u00a7cPlease wait!");
                  setupLocation.put(var5, var5.getLocation());
                  ArenaCfg.CreateArenaCfg(var4[1], var5);
                  Bukkit.getScheduler().runTaskLater(Main.plugin, new Runnable() {
                     public void run() {
                        var5.teleport(Bukkit.getWorld(var4[1]).getSpawnLocation());
                        var5.setGameMode(GameMode.CREATIVE);
                        Bukkit.getWorld(var4[1]).setAutoSave(true);
                        SetupCmds.createArena.put(var5, var5.getLocation().getWorld().getName());
                        var5.sendMessage(Main.PREFIX + "\u00a7aYou were teleported to the " + var5.getWorld().getName() + "'s spawn.");
                        if (Settings.getCfg().get("Arenas") != null) {
                           List var1 = Settings.getCfg().getStringList("Arenas");
                           var1.add(var5.getLocation().getWorld().getName());
                           Settings.getCfg().set("Arenas", var1);

                           try {
                              Settings.getCfg().save(Settings.getCfgFile());
                           } catch (IOException var3) {
                              var3.printStackTrace();
                           }
                        } else {
                           Settings.addArenaToList(var5);
                        }

                     }
                  }, 60L);
               } else {
                  var5.sendMessage(Main.PREFIX + "\u00a7cUsage: /sgs addArena <worldName>");
               }

               return true;
            }

            if (var4[0].equalsIgnoreCase("setmainlobby")) {
               if (var4.length == 1) {
                  Settings.setMainLobby(var5);
               } else {
                  var5.sendMessage(Main.PREFIX + "\u00a7cUsage: /sgs setMainLobby");
               }

               return true;
            }

            if (var4[0].equalsIgnoreCase("setspectate")) {
               if (var4.length == 1) {
                  Spectate.setSpect(var5);
                  remainingCmds(var5);
               } else {
                  var5.sendMessage(Main.PREFIX + "\u00a7cUsage: /sgs setSpectate");
               }

               return true;
            }

            if (var4[0].equalsIgnoreCase("setspawn")) {
               if (var4.length == 2) {
                  Spawns.setSpawn(var4[1], var5);
                  remainingCmds(var5);
               } else {
                  var5.sendMessage(Main.PREFIX + "\u00a7cUsage: /sgs setSpawn <team>");
               }

               return true;
            }

            if (var4[0].equalsIgnoreCase("setgiant")) {
               if (var4.length == 2) {
                  Giants.setGiant(var4[1], var5);
                  remainingCmds(var5);
               } else {
                  var5.sendMessage(Main.PREFIX + "\u00a7cUsage: /sgs setGiant <team>");
               }

               return true;
            }

            if (var4[0].equalsIgnoreCase("setvillager")) {
               if (var4.length == 2) {
                  Villagers.setVillager(var4[1], var5);
                  remainingCmds(var5);
               } else {
                  var5.sendMessage(Main.PREFIX + "\u00a7cUsage: /sgs setVillager <team>");
               }

               return true;
            }

            if (var4[0].equalsIgnoreCase("setborder")) {
               if (var4.length == 3) {
                  if (!isInt(var4[2])) {
                     var5.sendMessage(Main.PREFIX + "\u00a7cUsage: /sgs setBorder <team> \u00a74<radius>");
                     return true;
                  }

                  Borders.setBorder(var4[1], var5, Integer.valueOf(var4[2]).intValue() * 2);
                  remainingCmds(var5);
               } else {
                  var5.sendMessage(Main.PREFIX + "\u00a7cUsage: /sgs setBorder <team> <radius>");
               }
            } else if (var4[0].equalsIgnoreCase("setbeast")) {
               if (var4.length == 1) {
                  Beasts.setBeast(var5);
                  remainingCmds(var5);
               } else {
                  var5.sendMessage(Main.PREFIX + "\u00a7cUsage: /sgs setBeast");
               }
            } else if (var4[0].equalsIgnoreCase("setmidpos")) {
               if (var4.length == 2) {
                  if (var4[1].equalsIgnoreCase("1")) {
                     Middle.setMidPos1(var5);
                     remainingCmds(var5);
                  } else if (var4[1].equalsIgnoreCase("2")) {
                     Middle.setMidPos2(var5);
                     remainingCmds(var5);
                  } else {
                     var5.sendMessage(Main.PREFIX + "\u00a7cUsage: /setMidPos 1/2");
                  }
               } else {
                  var5.sendMessage(Main.PREFIX + "\u00a7cUsage: /setMidPos 1/2");
               }
            } else {
               if (var4[0].equalsIgnoreCase("finisharena")) {
                  if (var4.length == 1) {
                     if (Settings.getCfg().getString("MainLobby.World") != null) {
                        var5.teleport(Settings.getMainLobby());
                     } else {
                        var5.teleport((Location)setupLocation.get(var5));
                     }

                     if (createArena.containsKey(var5)) {
                        var5.sendMessage(Main.PREFIX + "\u00a7b" + (String)createArena.get(var5) + " was saved!");
                     } else if (editArena.containsKey(var5)) {
                        var5.sendMessage(Main.PREFIX + "\u00a7b" + (String)editArena.get(var5) + " was saved!");
                     }

                     Bukkit.getScheduler().runTaskLater(Main.plugin, new Runnable() {
                        public void run() {
                           if (SetupCmds.createArena.containsKey(var5)) {
                              Bukkit.getServer().unloadWorld((String)SetupCmds.createArena.get(var5), true);
                           } else if (SetupCmds.editArena.containsKey(var5)) {
                              Bukkit.getServer().unloadWorld((String)SetupCmds.editArena.get(var5), true);
                           }

                        }
                     }, 20L);
                     if (createArena.containsKey(var5)) {
                        createArena.remove(var5);
                     } else if (editArena.containsKey(var5)) {
                        editArena.remove(var5);
                     }
                  } else {
                     var5.sendMessage(Main.PREFIX + "\u00a7cUsage: /sgs finishArena");
                  }

                  return true;
               }

               if (!var4[0].equalsIgnoreCase("arenas")) {
                  if (var4[0].equalsIgnoreCase("deletearena")) {
                     if (var4.length == 2) {
                        if (Settings.getCfg().getString("Arenas").contains(var4[1])) {
                           Settings.removeArena(var4[1]);
                           var5.sendMessage(Main.PREFIX + "\u00a7b" + var4[1] + " was removed from the config!");
                        } else {
                           var5.sendMessage(Main.PREFIX + "\u00a7cUnknown arena!");
                        }
                     } else {
                        var5.sendMessage(Main.PREFIX + "\u00a7cUsage: /sgs deleteArena <worldName>");
                     }
                  } else if (var4[0].equalsIgnoreCase("editarena")) {
                     if (var4.length == 2) {
                        if (Settings.getCfg().getStringList("Arenas").contains(var4[1])) {
                           if (createArena.containsKey(var5)) {
                              var5.sendMessage(Main.PREFIX + "\u00a7cYou can't use this command now because you're creating a new arena!");
                              return true;
                           }

                           setupLocation.put(var5, var5.getLocation());
                           editArena.put(var5, var4[1]);
                           Bukkit.getServer().createWorld(new WorldCreator(var4[1]));
                           var5.sendMessage(Main.PREFIX + "\u00a7cPlease wait!");
                           Bukkit.getScheduler().runTaskLater(Main.plugin, new Runnable() {
                              public void run() {
                                 if (Spectate.getSpect(var4[1]) != null) {
                                    var5.teleport(Spectate.getSpect(var4[1]));
                                 } else {
                                    var5.teleport(Bukkit.getWorld(var4[1]).getSpawnLocation());
                                 }

                              }
                           }, 20L);
                        } else {
                           var5.sendMessage(Main.PREFIX + "\u00a7c" + var4[1] + " doesn't exist!");
                        }
                     } else {
                        var5.sendMessage(Main.PREFIX + "\u00a7cUsage: /sgs editArena <worldName>");
                     }
                  }
               } else if (var4.length != 1) {
                  var5.sendMessage(Main.PREFIX + "\u00a7cUsage: /sgs arenas");
               } else {
                  List var6 = Settings.getCfg().getStringList("Arenas");
                  var5.sendMessage(Main.PREFIX + "\u00a76\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d");
                  var5.sendMessage(Main.PREFIX + "\u00a7e\u00a7lAvailable arenas");
                  Iterator var7 = var6.iterator();

                  while(var7.hasNext()) {
                     Object var8 = var7.next();
                     var5.sendMessage(Main.PREFIX + "\u00a7b" + var8);
                  }

                  var5.sendMessage(Main.PREFIX + "\u00a76\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d");
               }
            }

            if (var4[0].equalsIgnoreCase("togglesetup")) {
               if (var4.length == 1) {
                  Settings.toggleSetup(var5);
               } else {
                  var5.sendMessage(Main.PREFIX + "\u00a7cUsage: /sgs toggleSetup");
               }
            }
         }
      }

      return false;
   }

   private static void listCmds(Player var0) {
      var0.sendMessage(Main.PREFIX + "\u00a76\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d");
      var0.sendMessage(Main.PREFIX + "\u00a7e\u00a7lSetup Commands");
      var0.sendMessage(Main.PREFIX + "\u00a7b/sgs arenas");
      var0.sendMessage(Main.PREFIX + "\u00a7b/sgs setMainLobby");
      var0.sendMessage(Main.PREFIX + "\u00a7b/sgs addArena <worldName>");
      var0.sendMessage(Main.PREFIX + "\u00a7b/sgs deleteArena <worldName>");
      var0.sendMessage(Main.PREFIX + "\u00a7b/sgs editArena <worldName>");
      var0.sendMessage(Main.PREFIX + "\u00a7b/sgs toggleSetup");
      var0.sendMessage(Main.PREFIX + "\u00a76\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d");
   }

   private static void remainingCmds(Player var0) {
      String var1 = var0.getLocation().getWorld().getName();
      var0.sendMessage(Main.PREFIX + "\u00a76\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d");
      var0.sendMessage(Main.PREFIX + "\u00a7e\u00a7l" + var1 + " remaining cmds");
      if (ArenaCfg.getArena(var1).get("Spectate") == null) {
         var0.sendMessage(Main.PREFIX + "\u00a7b/sgs setSpectate");
      }

      if (ArenaCfg.getArena(var1).get("Beast") == null) {
         var0.sendMessage(Main.PREFIX + "\u00a7b/sgs setBeast");
      }

      Spawns.checkSpawns(var0);
      Giants.checkGiants(var0);
      Villagers.checkVillagers(var0);
      Borders.checkBorders(var0);
      if (ArenaCfg.getArena(var0.getWorld().getName()).get("Region.Middle.Pos1.X") == null && ArenaCfg.getArena(var0.getWorld().getName()).get("Region.Middle.Pos2.X") == null) {
         var0.sendMessage(Main.PREFIX + "\u00a7b/sgs setMidPos \u00a791 \u00a722");
      } else if (ArenaCfg.getArena(var0.getWorld().getName()).get("Region.Middle.Pos1.X") == null && ArenaCfg.getArena(var0.getWorld().getName()).get("Region.Middle.Pos2.X") != null) {
         var0.sendMessage(Main.PREFIX + "\u00a7b/sgs setMidPos \u00a791");
      } else if (ArenaCfg.getArena(var0.getWorld().getName()).get("Region.Middle.Pos1.X") != null && ArenaCfg.getArena(var0.getWorld().getName()).get("Region.Middle.Pos2.X") == null) {
         var0.sendMessage(Main.PREFIX + "\u00a7b/sgs setMidPos \u00a722");
      }

      var0.sendMessage(Main.PREFIX + "\u00a77/sgs finishArena");
      var0.sendMessage(Main.PREFIX + "\u00a76\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d");
   }

   private static void listArenaCmds(Player var0) {
      var0.sendMessage(Main.PREFIX + "\u00a76\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d");
      var0.sendMessage(Main.PREFIX + "\u00a7b/sgs setSpectate");
      var0.sendMessage(Main.PREFIX + "\u00a7b/sgs setBeast");
      var0.sendMessage(Main.PREFIX + "\u00a7b/sgs setSpawn <team>");
      var0.sendMessage(Main.PREFIX + "\u00a7b/sgs setGiant <team>");
      var0.sendMessage(Main.PREFIX + "\u00a7b/sgs setVillager <team>");
      var0.sendMessage(Main.PREFIX + "\u00a7b/sgs setBorder <team> <radius>");
      var0.sendMessage(Main.PREFIX + "\u00a7b/sgs setMidPos 1/2");
      var0.sendMessage(Main.PREFIX + "\u00a77/sgs finishArena");
      var0.sendMessage(Main.PREFIX + "\u00a76\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d\u254d");
   }

   private static boolean isInt(String var0) {
      try {
         Integer.parseInt(var0);
         return true;
      } catch (NumberFormatException var2) {
         return false;
      }
   }
}
