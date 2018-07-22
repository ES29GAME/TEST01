package com.andrei1058.skygiants.listeners;

import com.andrei1058.skygiants.Main;
import com.andrei1058.skygiants.configuration.Messages;
import com.andrei1058.skygiants.game.Spectate;
import com.andrei1058.skygiants.locations.Spawns;
import com.andrei1058.skygiants.protocolLib.Borders;
import com.andrei1058.skygiants.utils.Titles;
import java.util.Iterator;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerDeathListener implements Listener {
   @EventHandler
   public void death(final PlayerDeathEvent var1) {
      if (!Main.MAINTENANCE.booleanValue()) {
         if (Main.spectators.contains(var1.getEntity())) {
            var1.getEntity().spigot().respawn();
         } else {
            var1.getDrops().clear();
            var1.setDeathMessage((String)null);
            if (!Main.deaths.containsKey(var1.getEntity())) {
               Main.deaths.put(var1.getEntity(), Integer.valueOf(1));
            } else {
               Main.deaths.replace(var1.getEntity(), ((Integer)Main.deaths.get(var1.getEntity())).intValue() + 1);
            }

            Main.respawning.add(var1.getEntity());
            Iterator var2;
            Player var3;
            if (var1.getEntity().getKiller() instanceof Player) {
               var1.getEntity().getKiller().sendMessage(Main.PREFIX + Messages.getMsg().getString("gold-for-killing").replace("{player}", var1.getEntity().getName()).replace('&', '\u00a7'));
               if (Main.money.containsKey(var1.getEntity())) {
                  Main.money.replace(var1.getEntity(), ((Integer)Main.money.get(var1.getEntity())).intValue() + 250);
               }

               if (Main.kills.containsKey(var1.getEntity())) {
                  Main.kills.replace(var1.getEntity(), ((Integer)Main.kills.get(var1.getEntity())).intValue() + 1);
               }

               var1.getEntity().spigot().respawn();
               if (!Main.respawningKill.containsKey(var1.getEntity())) {
                  Main.respawningKill.put(var1.getEntity(), Integer.valueOf(3));
               } else if (((Integer)Main.respawningKill.get(var1.getEntity())).intValue() < 15) {
                  Main.respawningKill.replace(var1.getEntity(), ((Integer)Main.respawningKill.get(var1.getEntity())).intValue() + 4);
               }

               var2 = Main.players.iterator();

               while(var2.hasNext()) {
                  var3 = (Player)var2.next();
                  var3.hidePlayer(var1.getEntity());
               }

               (new BukkitRunnable() {
                  int i;

                  {
                     this.i = ((Integer)Main.respawningKill.get(var1.getEntity())).intValue();
                  }

                  public void run() {
                     if (this.i != 0) {
                        --this.i;
                     }

                     if (!Main.respawning.contains(var1.getEntity())) {
                        this.cancel();
                        Spectate.setSpectator(var1.getEntity());
                     }

                     if (this.i == 15 || this.i == 14 || this.i == 13 || this.i == 12 || this.i == 11 || this.i == 10 || this.i == 9 || this.i == 8 || this.i == 7 || this.i == 6 || this.i == 5 || this.i == 4) {
                        Titles.sendTitle(var1.getEntity(), Integer.valueOf(0), Integer.valueOf(14), Integer.valueOf(0), " ", Messages.getMsg().getString("respawning-in").replace("{time}".replace("&l", ""), String.valueOf(this.i)).replace('&', '\u00a7'));
                     }

                     if (this.i == 3) {
                        Titles.sendTitle(var1.getEntity(), Integer.valueOf(0), Integer.valueOf(14), Integer.valueOf(0), " ", Messages.getMsg().getString("respawning-in").replace("{time}".replace("&l", ""), "\u278c").replace('&', '\u00a7'));
                     }

                     if (this.i == 2) {
                        Titles.sendTitle(var1.getEntity(), Integer.valueOf(0), Integer.valueOf(14), Integer.valueOf(0), " ", Messages.getMsg().getString("respawning-in").replace("{time}".replace("&l", ""), "\u278b").replace('&', '\u00a7'));
                     }

                     if (this.i == 1) {
                        Titles.sendTitle(var1.getEntity(), Integer.valueOf(0), Integer.valueOf(14), Integer.valueOf(0), " ", Messages.getMsg().getString("respawning-in").replace("{time}".replace("&l", ""), "\u278a").replace('&', '\u00a7'));
                     }

                     if (this.i == 0) {
                        this.cancel();
                        Titles.fightTitle(var1.getEntity());
                        Main.respawning.remove(var1.getEntity());
                        if (Main.respBeac.containsValue(var1.getEntity())) {
                           var1.getEntity().teleport((Location)Main.respOwn.get(var1.getEntity().getName()));
                        } else if (Main.goldPlayers.contains(var1.getEntity())) {
                           var1.getEntity().teleport(Spawns.getSpawn("Gold"));
                        } else if (Main.magentaPlayers.contains(var1.getEntity())) {
                           var1.getEntity().teleport(Spawns.getSpawn("Magenta"));
                        } else if (Main.greenPlayers.contains(var1.getEntity())) {
                           var1.getEntity().teleport(Spawns.getSpawn("Green"));
                        } else if (Main.bluePlayers.contains(var1.getEntity())) {
                           var1.getEntity().teleport(Spawns.getSpawn("Blue"));
                        }

                        Iterator var1x = Bukkit.getOnlinePlayers().iterator();

                        while(var1x.hasNext()) {
                           Player var2 = (Player)var1x.next();
                           var2.showPlayer(var1.getEntity());
                        }

                        var1.getEntity().setAllowFlight(false);
                     }

                  }
               }).runTaskTimer(Main.plugin, 0L, 20L);
            } else {
               if (var1.getEntity().getLastDamageCause().getCause() == DamageCause.VOID) {
                  var1.getEntity().spigot().respawn();
                  if (!Main.respawningVoid.containsKey(var1.getEntity())) {
                     Main.respawningVoid.put(var1.getEntity(), Integer.valueOf(3));
                  } else if (((Integer)Main.respawningVoid.get(var1.getEntity())).intValue() < 15) {
                     Main.respawningVoid.replace(var1.getEntity(), ((Integer)Main.respawningVoid.get(var1.getEntity())).intValue() + 2);
                  }

                  var2 = Main.players.iterator();

                  while(var2.hasNext()) {
                     var3 = (Player)var2.next();
                     var3.hidePlayer(var1.getEntity());
                  }

                  (new BukkitRunnable() {
                     int i;

                     {
                        this.i = ((Integer)Main.respawningVoid.get(var1.getEntity())).intValue();
                     }

                     public void run() {
                        if (this.i != 0) {
                           --this.i;
                        }

                        if (!Main.respawning.contains(var1.getEntity())) {
                           this.cancel();
                           Spectate.setSpectator(var1.getEntity());
                        }

                        if (this.i == 15 || this.i == 14 || this.i == 13 || this.i == 12 || this.i == 11 || this.i == 10 || this.i == 9 || this.i == 8 || this.i == 7 || this.i == 6 || this.i == 5 || this.i == 4) {
                           Titles.sendTitle(var1.getEntity(), Integer.valueOf(0), Integer.valueOf(14), Integer.valueOf(0), " ", Messages.getMsg().getString("respawning-in").replace("{time}", String.valueOf(this.i)).replace('&', '\u00a7'));
                        }

                        if (this.i == 3) {
                           Titles.sendTitle(var1.getEntity(), Integer.valueOf(0), Integer.valueOf(14), Integer.valueOf(0), " ", Messages.getMsg().getString("respawning-in").replace("{time}", "\u278c").replace('&', '\u00a7'));
                        }

                        if (this.i == 2) {
                           Titles.sendTitle(var1.getEntity(), Integer.valueOf(0), Integer.valueOf(14), Integer.valueOf(0), " ", Messages.getMsg().getString("respawning-in").replace("{time}", "\u278b").replace('&', '\u00a7'));
                        }

                        if (this.i == 1) {
                           Titles.sendTitle(var1.getEntity(), Integer.valueOf(0), Integer.valueOf(14), Integer.valueOf(0), " ", Messages.getMsg().getString("respawning-in").replace("{time}", "\u278a").replace('&', '\u00a7'));
                        }

                        if (this.i == 0) {
                           this.cancel();
                           Titles.fightTitle(var1.getEntity());
                           Main.respawning.remove(var1.getEntity());
                           if (Main.respBeac.containsValue(var1.getEntity())) {
                              var1.getEntity().teleport((Location)Main.respOwn.get(var1.getEntity().getName()));
                           } else if (Main.goldPlayers.contains(var1.getEntity())) {
                              var1.getEntity().teleport(Spawns.getSpawn("Gold"));
                              if (Main.warmup.booleanValue()) {
                                 Borders.goldBorders(var1.getEntity());
                              }
                           } else if (Main.magentaPlayers.contains(var1.getEntity())) {
                              var1.getEntity().teleport(Spawns.getSpawn("Magenta"));
                              if (Main.warmup.booleanValue()) {
                                 Borders.magentaBorders(var1.getEntity());
                              }
                           } else if (Main.greenPlayers.contains(var1.getEntity())) {
                              var1.getEntity().teleport(Spawns.getSpawn("Green"));
                              if (Main.warmup.booleanValue()) {
                                 Borders.greenBorders(var1.getEntity());
                              }
                           } else if (Main.bluePlayers.contains(var1.getEntity())) {
                              var1.getEntity().teleport(Spawns.getSpawn("Blue"));
                              if (Main.warmup.booleanValue()) {
                                 Borders.blueBorders(var1.getEntity());
                              }
                           }

                           Iterator var1x = Bukkit.getOnlinePlayers().iterator();

                           while(var1x.hasNext()) {
                              Player var2 = (Player)var1x.next();
                              var2.showPlayer(var1.getEntity());
                           }

                           var1.getEntity().setAllowFlight(false);
                        }

                     }
                  }).runTaskTimer(Main.plugin, 0L, 20L);
               } else {
                  var1.getEntity().spigot().respawn();
                  if (!Main.respawningKill.containsKey(var1.getEntity())) {
                     Main.respawningKill.put(var1.getEntity(), Integer.valueOf(3));
                  } else if (((Integer)Main.respawningKill.get(var1.getEntity())).intValue() < 15) {
                     Main.respawningKill.replace(var1.getEntity(), ((Integer)Main.respawningKill.get(var1.getEntity())).intValue() + 4);
                  }

                  var2 = Main.players.iterator();

                  while(var2.hasNext()) {
                     var3 = (Player)var2.next();
                     var3.hidePlayer(var1.getEntity());
                  }

                  (new BukkitRunnable() {
                     int i;

                     {
                        this.i = ((Integer)Main.respawningKill.get(var1.getEntity())).intValue();
                     }

                     public void run() {
                        if (this.i != 0) {
                           --this.i;
                        }

                        if (!Main.respawning.contains(var1.getEntity())) {
                           this.cancel();
                           Spectate.setSpectator(var1.getEntity());
                        }

                        if (this.i == 15 || this.i == 14 || this.i == 13 || this.i == 12 || this.i == 11 || this.i == 10 || this.i == 9 || this.i == 8 || this.i == 7 || this.i == 6 || this.i == 5 || this.i == 4) {
                           Titles.sendTitle(var1.getEntity(), Integer.valueOf(0), Integer.valueOf(14), Integer.valueOf(0), " ", Messages.getMsg().getString("respawning-in").replace("{time}".replace("&l", ""), String.valueOf(this.i)).replace('&', '\u00a7'));
                        }

                        if (this.i == 3) {
                           Titles.sendTitle(var1.getEntity(), Integer.valueOf(0), Integer.valueOf(14), Integer.valueOf(0), " ", Messages.getMsg().getString("respawning-in").replace("{time}".replace("&l", ""), "\u278c").replace('&', '\u00a7'));
                        }

                        if (this.i == 2) {
                           Titles.sendTitle(var1.getEntity(), Integer.valueOf(0), Integer.valueOf(14), Integer.valueOf(0), " ", Messages.getMsg().getString("respawning-in").replace("{time}".replace("&l", ""), "\u278b").replace('&', '\u00a7'));
                        }

                        if (this.i == 1) {
                           Titles.sendTitle(var1.getEntity(), Integer.valueOf(0), Integer.valueOf(14), Integer.valueOf(0), " ", Messages.getMsg().getString("respawning-in").replace("{time}".replace("&l", ""), "\u278a").replace('&', '\u00a7'));
                        }

                        if (this.i == 0) {
                           this.cancel();
                           Titles.fightTitle(var1.getEntity());
                           Main.respawning.remove(var1.getEntity());
                           if (Main.respBeac.containsValue(var1.getEntity())) {
                              var1.getEntity().teleport((Location)Main.respOwn.get(var1.getEntity().getName()));
                           } else if (Main.goldPlayers.contains(var1.getEntity())) {
                              var1.getEntity().teleport(Spawns.getSpawn("Gold"));
                           } else if (Main.magentaPlayers.contains(var1.getEntity())) {
                              var1.getEntity().teleport(Spawns.getSpawn("Magenta"));
                           } else if (Main.greenPlayers.contains(var1.getEntity())) {
                              var1.getEntity().teleport(Spawns.getSpawn("Green"));
                           } else if (Main.bluePlayers.contains(var1.getEntity())) {
                              var1.getEntity().teleport(Spawns.getSpawn("Blue"));
                           }

                           Iterator var1x = Bukkit.getOnlinePlayers().iterator();

                           while(var1x.hasNext()) {
                              Player var2 = (Player)var1x.next();
                              var2.showPlayer(var1.getEntity());
                           }

                           var1.getEntity().setAllowFlight(false);
                        }

                     }
                  }).runTaskTimer(Main.plugin, 0L, 20L);
               }

            }
         }
      }
   }
}
