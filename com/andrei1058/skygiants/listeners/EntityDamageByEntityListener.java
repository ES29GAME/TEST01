package com.andrei1058.skygiants.listeners;

import com.andrei1058.skygiants.Main;
import com.andrei1058.skygiants.configuration.Messages;
import com.andrei1058.skygiants.game.GameState;
import com.andrei1058.skygiants.utils.Titles;
import java.util.Iterator;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Giant;
import org.bukkit.entity.Guardian;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.util.Vector;

public class EntityDamageByEntityListener implements Listener {
   @EventHandler
   public void damage(final EntityDamageByEntityEvent var1) {
      if (!Main.MAINTENANCE.booleanValue()) {
         if (Main.STATUS != GameState.PLAYING) {
            var1.setCancelled(true);
         }

         if (Main.respawning.contains(var1.getDamager())) {
            var1.setCancelled(true);
         } else {
            if (var1.getDamager() instanceof Player && var1.getEntity() instanceof Player) {
               if (Main.goldPlayers.contains(var1.getEntity()) && Main.goldPlayers.contains(var1.getDamager())) {
                  var1.setCancelled(true);
               } else if (Main.magentaPlayers.contains(var1.getDamager()) && Main.magentaPlayers.contains(var1.getEntity())) {
                  var1.setCancelled(true);
               } else if (Main.greenPlayers.contains(var1.getDamager()) && Main.greenPlayers.contains(var1.getEntity())) {
                  var1.setCancelled(true);
               } else if (Main.bluePlayers.contains(var1.getDamager()) && Main.bluePlayers.contains(var1.getEntity())) {
                  var1.setCancelled(true);
               } else if (Main.spectators.contains(var1.getDamager()) && Main.players.contains(var1.getEntity())) {
                  var1.setCancelled(true);
               } else if (Main.players.contains(var1.getDamager()) && Main.spectators.contains(var1.getEntity())) {
                  var1.setCancelled(true);
               }
            } else if (var1.getDamager() instanceof Projectile && var1.getEntity() instanceof Player) {
               Projectile var10 = (Projectile)var1.getDamager();
               if (Main.goldPlayers.contains(var1.getEntity()) && Main.goldPlayers.contains(var10.getShooter())) {
                  var1.setCancelled(true);
               } else if (Main.magentaPlayers.contains(var10.getShooter()) && Main.magentaPlayers.contains(var1.getEntity())) {
                  var1.setCancelled(true);
               } else if (Main.greenPlayers.contains(var10.getShooter()) && Main.greenPlayers.contains(var1.getEntity())) {
                  var1.setCancelled(true);
               } else if (Main.bluePlayers.contains(var10.getShooter()) && Main.bluePlayers.contains(var1.getEntity())) {
                  var1.setCancelled(true);
               } else if (Main.spectators.contains(var10.getShooter()) && Main.players.contains(var1.getEntity())) {
                  var1.setCancelled(true);
               } else if (Main.players.contains(var1.getDamager()) && Main.spectators.contains(var1.getEntity())) {
                  var1.setCancelled(true);
               }

               if (Main.respawning.contains(var10.getShooter())) {
                  var1.setCancelled(true);
               }
            } else if (var1.getEntity() instanceof Giant) {
               final Player var2;
               if (var1.getDamager() instanceof Player) {
                  var2 = (Player)var1.getDamager();
               } else {
                  if (!(var1.getDamager() instanceof Projectile)) {
                     return;
                  }

                  Projectile var3 = (Projectile)var1.getDamager();
                  var2 = (Player)var3.getShooter();
               }

               if (Main.spectators.contains(var2)) {
                  var1.setCancelled(true);
                  return;
               }

               if (Main.respawning.contains(var2)) {
                  var1.setCancelled(true);
                  return;
               }

               if (Main.doubleDamage.booleanValue()) {
                  ((Giant)var1.getEntity()).damage(var1.getDamage() * 2.0D);
               }

               Player var4;
               Iterator var5;
               String var6;
               Iterator var9;
               if (Main.GoldGiantRegion.isInRegion(var1.getEntity().getLocation())) {
                  if (Main.goldPlayers.contains(var2)) {
                     var1.setCancelled(true);
                     if (!Main.cannotHitGiant.contains(var2)) {
                        var2.sendMessage(Main.PREFIX + Messages.getMsg().getString("cant-damage-giant").replace('&', '\u00a7'));
                        Main.cannotHitGiant.add(var2);
                        Bukkit.getScheduler().runTaskLater(Main.plugin, new Runnable() {
                           public void run() {
                              Main.cannotHitGiant.remove(var2);
                           }
                        }, 100L);
                     }

                     return;
                  }

                  if (!Main.goldHit.booleanValue()) {
                     Main.goldHit = true;
                     var1.getEntity().teleport(new Location(var1.getEntity().getWorld(), var1.getEntity().getLocation().getX(), var1.getEntity().getLocation().getY() + 3.0D, var1.getEntity().getLocation().getZ()));
                     Bukkit.getScheduler().runTaskLater(Main.plugin, new Runnable() {
                        public void run() {
                           var1.getEntity().teleport(new Location(var1.getEntity().getWorld(), var1.getEntity().getLocation().getX(), var1.getEntity().getLocation().getY() + 3.0D, var1.getEntity().getLocation().getZ()));
                           Bukkit.getScheduler().runTaskLater(Main.plugin, new Runnable() {
                              public void run() {
                                 var1.getEntity().teleport(new Location(var1.getEntity().getWorld(), var1.getEntity().getLocation().getX(), var1.getEntity().getLocation().getY() + 3.0D, var1.getEntity().getLocation().getZ()));
                                 Bukkit.getScheduler().runTaskLater(Main.plugin, new Runnable() {
                                    public void run() {
                                       var1.getEntity().teleport(new Location(var1.getEntity().getWorld(), var1.getEntity().getLocation().getX(), var1.getEntity().getLocation().getY() - 3.0D, var1.getEntity().getLocation().getZ()));
                                       Bukkit.getScheduler().runTaskLater(Main.plugin, new Runnable() {
                                          public void run() {
                                             var1.getEntity().teleport(new Location(var1.getEntity().getWorld(), var1.getEntity().getLocation().getX(), var1.getEntity().getLocation().getY() - 3.0D, var1.getEntity().getLocation().getZ()));
                                             Bukkit.getScheduler().runTaskLater(Main.plugin, new Runnable() {
                                                public void run() {
                                                   var1.getEntity().teleport(new Location(var1.getEntity().getWorld(), var1.getEntity().getLocation().getX(), var1.getEntity().getLocation().getY() - 3.0D, var1.getEntity().getLocation().getZ()));
                                                   Location var1x = var1.getEntity().getLocation();

                                                   for(int var2 = 0; var2 < 360; var2 += 3) {
                                                      var1x.setZ(var1x.getZ() + Math.cos((double)var2) * 3.0D);
                                                      var1x.setX(var1x.getX() + Math.cos((double)var2) * 3.0D);
                                                      var1x.getWorld().playEffect(var1x, Effect.CLOUD, 60);
                                                      var1x.getWorld().playEffect(var1x, Effect.CLOUD, 60);
                                                      var1x.getWorld().playEffect(var1x, Effect.CLOUD, 60);
                                                   }

                                                   Iterator var4 = var1.getEntity().getNearbyEntities(4.0D, 4.0D, 4.0D).iterator();

                                                   while(var4.hasNext()) {
                                                      Entity var3 = (Entity)var4.next();
                                                      if (var3 instanceof Player && !Main.goldPlayers.contains(var3)) {
                                                         if (Main.spectators.contains(var3)) {
                                                            return;
                                                         }

                                                         ((Player)var3).damage(1.0D);
                                                         var3.setVelocity(new Vector(1.0D, 0.5D, 1.0D));
                                                         ((Player)var3).playSound(var3.getLocation(), Main.nmsH.giantHurt(), 1.0F, 1.0F);
                                                      }
                                                   }

                                                }
                                             }, 4L);
                                          }
                                       }, 4L);
                                    }
                                 }, 4L);
                              }
                           }, 4L);
                        }
                     }, 4L);
                     var9 = Main.goldPlayers.iterator();

                     while(true) {
                        if (!var9.hasNext()) {
                           Bukkit.getScheduler().runTaskLater(Main.plugin, new Runnable() {
                              public void run() {
                                 Main.goldHit = false;
                              }
                           }, 150L);
                           break;
                        }

                        var4 = (Player)var9.next();
                        var5 = Messages.getMsg().getStringList("giant-attacked").iterator();

                        while(var5.hasNext()) {
                           var6 = (String)var5.next();
                           var4.sendMessage(Main.PREFIX + var6.replace('&', '\u00a7'));
                           var4.playSound(var4.getLocation(), Main.nmsH.giantHurt(), 1.0F, 1.0F);
                        }
                     }
                  }
               } else if (Main.MagentaGiantRegion.isInRegion(var1.getEntity().getLocation())) {
                  if (Main.magentaPlayers.contains(var2)) {
                     var1.setCancelled(true);
                     if (!Main.cannotHitGiant.contains(var2)) {
                        var2.sendMessage(Main.PREFIX + Messages.getMsg().getString("cant-damage-giant").replace('&', '\u00a7'));
                        Main.cannotHitGiant.add(var2);
                        Bukkit.getScheduler().runTaskLater(Main.plugin, new Runnable() {
                           public void run() {
                              Main.cannotHitGiant.remove(var2);
                           }
                        }, 100L);
                     }

                     return;
                  }

                  if (!Main.magentaHit.booleanValue()) {
                     Main.magentaHit = true;
                     var1.getEntity().teleport(new Location(var1.getEntity().getWorld(), var1.getEntity().getLocation().getX(), var1.getEntity().getLocation().getY() + 3.0D, var1.getEntity().getLocation().getZ()));
                     Bukkit.getScheduler().runTaskLater(Main.plugin, new Runnable() {
                        public void run() {
                           var1.getEntity().teleport(new Location(var1.getEntity().getWorld(), var1.getEntity().getLocation().getX(), var1.getEntity().getLocation().getY() + 3.0D, var1.getEntity().getLocation().getZ()));
                           Bukkit.getScheduler().runTaskLater(Main.plugin, new Runnable() {
                              public void run() {
                                 var1.getEntity().teleport(new Location(var1.getEntity().getWorld(), var1.getEntity().getLocation().getX(), var1.getEntity().getLocation().getY() + 3.0D, var1.getEntity().getLocation().getZ()));
                                 Bukkit.getScheduler().runTaskLater(Main.plugin, new Runnable() {
                                    public void run() {
                                       var1.getEntity().teleport(new Location(var1.getEntity().getWorld(), var1.getEntity().getLocation().getX(), var1.getEntity().getLocation().getY() - 3.0D, var1.getEntity().getLocation().getZ()));
                                       Bukkit.getScheduler().runTaskLater(Main.plugin, new Runnable() {
                                          public void run() {
                                             var1.getEntity().teleport(new Location(var1.getEntity().getWorld(), var1.getEntity().getLocation().getX(), var1.getEntity().getLocation().getY() - 3.0D, var1.getEntity().getLocation().getZ()));
                                             Bukkit.getScheduler().runTaskLater(Main.plugin, new Runnable() {
                                                public void run() {
                                                   var1.getEntity().teleport(new Location(var1.getEntity().getWorld(), var1.getEntity().getLocation().getX(), var1.getEntity().getLocation().getY() - 3.0D, var1.getEntity().getLocation().getZ()));
                                                   Location var1x = var1.getEntity().getLocation();

                                                   for(int var2 = 0; var2 < 360; var2 += 3) {
                                                      var1x.setZ(var1x.getZ() + Math.cos((double)var2) * 3.0D);
                                                      var1x.setX(var1x.getX() + Math.cos((double)var2) * 3.0D);
                                                      var1x.getWorld().playEffect(var1x, Effect.CLOUD, 60);
                                                      var1x.getWorld().playEffect(var1x, Effect.CLOUD, 60);
                                                      var1x.getWorld().playEffect(var1x, Effect.CLOUD, 60);
                                                   }

                                                   Iterator var4 = var1.getEntity().getNearbyEntities(4.0D, 4.0D, 4.0D).iterator();

                                                   while(var4.hasNext()) {
                                                      Entity var3 = (Entity)var4.next();
                                                      if (var3 instanceof Player && !Main.magentaPlayers.contains(var3)) {
                                                         if (Main.spectators.contains(var3)) {
                                                            return;
                                                         }

                                                         ((Player)var3).damage(1.0D);
                                                         var3.setVelocity(new Vector(1.0D, 0.5D, 1.0D));
                                                         ((Player)var3).playSound(var3.getLocation(), Main.nmsH.giantHurt(), 1.0F, 1.0F);
                                                      }
                                                   }

                                                }
                                             }, 4L);
                                          }
                                       }, 4L);
                                    }
                                 }, 4L);
                              }
                           }, 4L);
                        }
                     }, 4L);
                     var9 = Main.magentaPlayers.iterator();

                     while(true) {
                        if (!var9.hasNext()) {
                           Bukkit.getScheduler().runTaskLater(Main.plugin, new Runnable() {
                              public void run() {
                                 Main.magentaHit = false;
                              }
                           }, 150L);
                           break;
                        }

                        var4 = (Player)var9.next();
                        var5 = Messages.getMsg().getStringList("giant-attacked").iterator();

                        while(var5.hasNext()) {
                           var6 = (String)var5.next();
                           var4.sendMessage(Main.PREFIX + var6.replace('&', '\u00a7'));
                           var4.playSound(var4.getLocation(), Main.nmsH.giantHurt(), 1.0F, 1.0F);
                        }
                     }
                  }
               } else if (Main.GreenGiantRegion.isInRegion(var1.getEntity().getLocation())) {
                  if (Main.greenPlayers.contains(var2)) {
                     var1.setCancelled(true);
                     if (!Main.cannotHitGiant.contains(var2)) {
                        var2.sendMessage(Main.PREFIX + Messages.getMsg().getString("cant-damage-giant").replace('&', '\u00a7'));
                        Main.cannotHitGiant.add(var2);
                        Bukkit.getScheduler().runTaskLater(Main.plugin, new Runnable() {
                           public void run() {
                              Main.cannotHitGiant.remove(var2);
                           }
                        }, 100L);
                     }

                     return;
                  }

                  if (!Main.greenHit.booleanValue()) {
                     Main.greenHit = true;
                     var1.getEntity().teleport(new Location(var1.getEntity().getWorld(), var1.getEntity().getLocation().getX(), var1.getEntity().getLocation().getY() + 3.0D, var1.getEntity().getLocation().getZ()));
                     Bukkit.getScheduler().runTaskLater(Main.plugin, new Runnable() {
                        public void run() {
                           var1.getEntity().teleport(new Location(var1.getEntity().getWorld(), var1.getEntity().getLocation().getX(), var1.getEntity().getLocation().getY() + 3.0D, var1.getEntity().getLocation().getZ()));
                           Bukkit.getScheduler().runTaskLater(Main.plugin, new Runnable() {
                              public void run() {
                                 var1.getEntity().teleport(new Location(var1.getEntity().getWorld(), var1.getEntity().getLocation().getX(), var1.getEntity().getLocation().getY() + 3.0D, var1.getEntity().getLocation().getZ()));
                                 Bukkit.getScheduler().runTaskLater(Main.plugin, new Runnable() {
                                    public void run() {
                                       var1.getEntity().teleport(new Location(var1.getEntity().getWorld(), var1.getEntity().getLocation().getX(), var1.getEntity().getLocation().getY() - 3.0D, var1.getEntity().getLocation().getZ()));
                                       Bukkit.getScheduler().runTaskLater(Main.plugin, new Runnable() {
                                          public void run() {
                                             var1.getEntity().teleport(new Location(var1.getEntity().getWorld(), var1.getEntity().getLocation().getX(), var1.getEntity().getLocation().getY() - 3.0D, var1.getEntity().getLocation().getZ()));
                                             Bukkit.getScheduler().runTaskLater(Main.plugin, new Runnable() {
                                                public void run() {
                                                   var1.getEntity().teleport(new Location(var1.getEntity().getWorld(), var1.getEntity().getLocation().getX(), var1.getEntity().getLocation().getY() - 3.0D, var1.getEntity().getLocation().getZ()));
                                                   Location var1x = var1.getEntity().getLocation();

                                                   for(int var2 = 0; var2 < 360; var2 += 3) {
                                                      var1x.setZ(var1x.getZ() + Math.cos((double)var2) * 3.0D);
                                                      var1x.setX(var1x.getX() + Math.cos((double)var2) * 3.0D);
                                                      var1x.getWorld().playEffect(var1x, Effect.CLOUD, 60);
                                                      var1x.getWorld().playEffect(var1x, Effect.CLOUD, 60);
                                                      var1x.getWorld().playEffect(var1x, Effect.CLOUD, 60);
                                                   }

                                                   Iterator var4 = var1.getEntity().getNearbyEntities(4.0D, 4.0D, 4.0D).iterator();

                                                   while(var4.hasNext()) {
                                                      Entity var3 = (Entity)var4.next();
                                                      if (var3 instanceof Player && !Main.greenPlayers.contains(var3)) {
                                                         if (Main.spectators.contains(var3)) {
                                                            return;
                                                         }

                                                         ((Player)var3).damage(1.0D);
                                                         var3.setVelocity(new Vector(1.0D, 0.5D, 1.0D));
                                                         ((Player)var3).playSound(var3.getLocation(), Main.nmsH.giantHurt(), 1.0F, 1.0F);
                                                      }
                                                   }

                                                }
                                             }, 4L);
                                          }
                                       }, 4L);
                                    }
                                 }, 4L);
                              }
                           }, 4L);
                        }
                     }, 4L);
                     var9 = Main.greenPlayers.iterator();

                     while(true) {
                        if (!var9.hasNext()) {
                           Bukkit.getScheduler().runTaskLater(Main.plugin, new Runnable() {
                              public void run() {
                                 Main.greenHit = false;
                              }
                           }, 150L);
                           break;
                        }

                        var4 = (Player)var9.next();
                        var5 = Messages.getMsg().getStringList("giant-attacked").iterator();

                        while(var5.hasNext()) {
                           var6 = (String)var5.next();
                           var4.sendMessage(Main.PREFIX + var6.replace('&', '\u00a7'));
                           var4.playSound(var4.getLocation(), Main.nmsH.giantHurt(), 1.0F, 1.0F);
                        }
                     }
                  }
               } else if (Main.BlueGiantRegion.isInRegion(var1.getEntity().getLocation())) {
                  if (Main.bluePlayers.contains(var2)) {
                     var1.setCancelled(true);
                     if (!Main.cannotHitGiant.contains(var2)) {
                        var2.sendMessage(Main.PREFIX + Messages.getMsg().getString("cant-damage-giant").replace('&', '\u00a7'));
                        Main.cannotHitGiant.add(var2);
                        Bukkit.getScheduler().runTaskLater(Main.plugin, new Runnable() {
                           public void run() {
                              Main.cannotHitGiant.remove(var2);
                           }
                        }, 100L);
                     }

                     return;
                  }

                  if (!Main.blueHit.booleanValue()) {
                     Main.blueHit = true;
                     var1.getEntity().teleport(new Location(var1.getEntity().getWorld(), var1.getEntity().getLocation().getX(), var1.getEntity().getLocation().getY() + 3.0D, var1.getEntity().getLocation().getZ()));
                     Bukkit.getScheduler().runTaskLater(Main.plugin, new Runnable() {
                        public void run() {
                           var1.getEntity().teleport(new Location(var1.getEntity().getWorld(), var1.getEntity().getLocation().getX(), var1.getEntity().getLocation().getY() + 3.0D, var1.getEntity().getLocation().getZ()));
                           Bukkit.getScheduler().runTaskLater(Main.plugin, new Runnable() {
                              public void run() {
                                 var1.getEntity().teleport(new Location(var1.getEntity().getWorld(), var1.getEntity().getLocation().getX(), var1.getEntity().getLocation().getY() + 3.0D, var1.getEntity().getLocation().getZ()));
                                 Bukkit.getScheduler().runTaskLater(Main.plugin, new Runnable() {
                                    public void run() {
                                       var1.getEntity().teleport(new Location(var1.getEntity().getWorld(), var1.getEntity().getLocation().getX(), var1.getEntity().getLocation().getY() - 3.0D, var1.getEntity().getLocation().getZ()));
                                       Bukkit.getScheduler().runTaskLater(Main.plugin, new Runnable() {
                                          public void run() {
                                             var1.getEntity().teleport(new Location(var1.getEntity().getWorld(), var1.getEntity().getLocation().getX(), var1.getEntity().getLocation().getY() - 3.0D, var1.getEntity().getLocation().getZ()));
                                             Bukkit.getScheduler().runTaskLater(Main.plugin, new Runnable() {
                                                public void run() {
                                                   var1.getEntity().teleport(new Location(var1.getEntity().getWorld(), var1.getEntity().getLocation().getX(), var1.getEntity().getLocation().getY() - 3.0D, var1.getEntity().getLocation().getZ()));
                                                   Location var1x = var1.getEntity().getLocation();

                                                   for(int var2 = 0; var2 < 360; var2 += 3) {
                                                      var1x.setZ(var1x.getZ() + Math.cos((double)var2) * 3.0D);
                                                      var1x.setX(var1x.getX() + Math.cos((double)var2) * 3.0D);
                                                      var1x.getWorld().playEffect(var1x, Effect.CLOUD, 60);
                                                      var1x.getWorld().playEffect(var1x, Effect.CLOUD, 60);
                                                      var1x.getWorld().playEffect(var1x, Effect.CLOUD, 60);
                                                   }

                                                   Iterator var4 = var1.getEntity().getNearbyEntities(4.0D, 4.0D, 4.0D).iterator();

                                                   while(var4.hasNext()) {
                                                      Entity var3 = (Entity)var4.next();
                                                      if (var3 instanceof Player && !Main.bluePlayers.contains(var3)) {
                                                         if (Main.spectators.contains(var3)) {
                                                            return;
                                                         }

                                                         ((Player)var3).damage(1.0D);
                                                         var3.setVelocity(new Vector(1.0D, 0.5D, 1.0D));
                                                         ((Player)var3).playSound(var3.getLocation(), Main.nmsH.giantHurt(), 1.0F, 1.0F);
                                                      }
                                                   }

                                                }
                                             }, 4L);
                                          }
                                       }, 4L);
                                    }
                                 }, 4L);
                              }
                           }, 4L);
                        }
                     }, 4L);
                     var9 = Main.bluePlayers.iterator();

                     while(true) {
                        if (!var9.hasNext()) {
                           Bukkit.getScheduler().runTaskLater(Main.plugin, new Runnable() {
                              public void run() {
                                 Main.blueHit = false;
                              }
                           }, 150L);
                           break;
                        }

                        var4 = (Player)var9.next();
                        var5 = Messages.getMsg().getStringList("giant-attacked").iterator();

                        while(var5.hasNext()) {
                           var6 = (String)var5.next();
                           var4.sendMessage(Main.PREFIX + var6.replace('&', '\u00a7'));
                           var4.playSound(var4.getLocation(), Main.nmsH.giantHurt(), 1.0F, 1.0F);
                        }
                     }
                  }
               }

               Giant var11 = (Giant)var1.getEntity();
               if (var11.getHealth() == (double)Main.GiantHealth) {
                  Titles.sendTitle(var2, Integer.valueOf(0), Integer.valueOf(10), Integer.valueOf(0), " ", "\u00a77" + (int)var11.getHealth() + "\u00a7a \u25cf\u25cf\u25cf\u25cf\u25cf\u25cf\u25cf\u25cf\u25cf\u25cf\u00a77 " + (int)var11.getHealth());
               }

               if ((double)(Main.GiantHealth * 90 / 100) < var11.getHealth() && var11.getHealth() < (double)Main.GiantHealth) {
                  Titles.sendTitle(var2, Integer.valueOf(0), Integer.valueOf(10), Integer.valueOf(0), " ", "\u00a77" + (int)var11.getHealth() + "\u00a7a \u25cf\u25cf\u25cf\u25cf\u25cf\u25cf\u25cf\u25cf\u25cf\u00a78\u25cb\u00a77 " + (int)var11.getHealth());
               }

               if ((double)(Main.GiantHealth * 80 / 100) < var11.getHealth() && var11.getHealth() < (double)(Main.GiantHealth * 90 / 100)) {
                  Titles.sendTitle(var2, Integer.valueOf(0), Integer.valueOf(10), Integer.valueOf(0), " ", "\u00a77" + (int)var11.getHealth() + "\u00a7a \u25cf\u25cf\u25cf\u25cf\u25cf\u25cf\u25cf\u25cf\u00a78\u25cb\u25cb\u00a77 " + (int)var11.getHealth());
               }

               if ((double)(Main.GiantHealth * 70 / 100) < var11.getHealth() && var11.getHealth() < (double)(Main.GiantHealth * 80 / 100)) {
                  Titles.sendTitle(var2, Integer.valueOf(0), Integer.valueOf(10), Integer.valueOf(0), " ", "\u00a77" + (int)var11.getHealth() + "\u00a7a \u25cf\u25cf\u25cf\u25cf\u25cf\u25cf\u25cf\u00a78\u25cb\u25cb\u25cb\u00a77 " + (int)var11.getHealth());
               }

               if ((double)(Main.GiantHealth * 60 / 100) < var11.getHealth() && var11.getHealth() < (double)(Main.GiantHealth * 70 / 100)) {
                  Titles.sendTitle(var2, Integer.valueOf(0), Integer.valueOf(10), Integer.valueOf(0), " ", "\u00a77" + (int)var11.getHealth() + "\u00a7a \u25cf\u25cf\u25cf\u25cf\u25cf\u25cf\u00a78\u25cb\u25cb\u25cb\u25cb\u00a77 " + (int)var11.getHealth());
               }

               if ((double)(Main.GiantHealth * 50 / 100) < var11.getHealth() && var11.getHealth() < (double)(Main.GiantHealth * 60 / 100)) {
                  Titles.sendTitle(var2, Integer.valueOf(0), Integer.valueOf(10), Integer.valueOf(0), " ", "\u00a77" + (int)var11.getHealth() + "\u00a7e \u25cf\u25cf\u25cf\u25cf\u25cf\u00a78\u25cb\u25cb\u25cb\u25cb\u25cb\u00a77 " + (int)var11.getHealth());
               }

               if ((double)(Main.GiantHealth * 40 / 100) < var11.getHealth() && var11.getHealth() < (double)(Main.GiantHealth * 50 / 100)) {
                  Titles.sendTitle(var2, Integer.valueOf(0), Integer.valueOf(10), Integer.valueOf(0), " ", "\u00a77" + (int)var11.getHealth() + "\u00a7e \u25cf\u25cf\u25cf\u25cf\u00a78\u25cb\u25cb\u25cb\u25cb\u25cb\u25cb\u00a77 " + (int)var11.getHealth());
               }

               if ((double)(Main.GiantHealth * 30 / 100) < var11.getHealth() && var11.getHealth() < (double)(Main.GiantHealth * 40 / 100)) {
                  Titles.sendTitle(var2, Integer.valueOf(0), Integer.valueOf(10), Integer.valueOf(0), " ", "\u00a77" + (int)var11.getHealth() + "\u00a7c \u25cf\u25cf\u25cf\u00a78\u25cb\u25cb\u25cb\u25cb\u25cb\u25cb\u25cb \u00a77" + (int)var11.getHealth());
               }

               if ((double)(Main.GiantHealth * 20 / 100) < var11.getHealth() && var11.getHealth() < (double)(Main.GiantHealth * 30 / 100)) {
                  Titles.sendTitle(var2, Integer.valueOf(0), Integer.valueOf(10), Integer.valueOf(0), " ", "\u00a77" + (int)var11.getHealth() + "\u00a7c \u25cf\u25cf\u00a78\u25cb\u25cb\u25cb\u25cb\u25cb\u25cb\u25cb\u25cb\u00a77 " + (int)var11.getHealth());
               }

               if ((double)(Main.GiantHealth * 10 / 100) < var11.getHealth() && var11.getHealth() < (double)(Main.GiantHealth * 20 / 100)) {
                  Titles.sendTitle(var2, Integer.valueOf(0), Integer.valueOf(10), Integer.valueOf(0), " ", "\u00a77" + (int)var11.getHealth() + "\u00a7c \u25cf\u00a78\u25cb\u25cb\u25cb\u25cb\u25cb\u25cb\u25cb\u25cb\u25cb\u00a77 " + (int)var11.getHealth());
               }

               if (0.0D < var11.getHealth() && var11.getHealth() < (double)(Main.GiantHealth * 10 / 100)) {
                  Titles.sendTitle(var2, Integer.valueOf(0), Integer.valueOf(10), Integer.valueOf(0), " ", "\u00a77" + (int)var11.getHealth() + "\u00a78 \u25cb\u25cb\u25cb\u25cb\u25cb\u25cb\u25cb\u25cb\u25cb\u25cb\u00a77 " + (int)var11.getHealth());
               }
            } else {
               Player var12;
               Projectile var13;
               if (var1.getEntity() instanceof Guardian) {
                  Guardian var7 = (Guardian)var1.getEntity();
                  if (var1.getDamager() instanceof Projectile) {
                     var13 = (Projectile)var1.getDamager();
                     var12 = (Player)var13.getShooter();
                  } else {
                     if (!(var1.getDamager() instanceof Player)) {
                        var1.setCancelled(true);
                        return;
                     }

                     var12 = (Player)var1.getDamager();
                  }

                  if (Main.spectators.contains(var12)) {
                     var1.setCancelled(true);
                  }

                  if (var7.getHealth() == (double)Main.BeastHealth) {
                     Titles.sendTitle(var12, Integer.valueOf(0), Integer.valueOf(10), Integer.valueOf(0), " ", "\u00a77" + (int)var7.getHealth() + "\u00a7a \u25cf\u25cf\u25cf\u25cf\u25cf\u25cf\u25cf\u25cf\u25cf\u25cf\u00a77 " + (int)var7.getHealth());
                  }

                  if ((double)(Main.BeastHealth * 90 / 100) < var7.getHealth() && var7.getHealth() < (double)Main.BeastHealth) {
                     Titles.sendTitle(var12, Integer.valueOf(0), Integer.valueOf(10), Integer.valueOf(0), " ", "\u00a77" + (int)var7.getHealth() + "\u00a7a \u25cf\u25cf\u25cf\u25cf\u25cf\u25cf\u25cf\u25cf\u25cf\u00a78\u25cb\u00a77 " + (int)var7.getHealth());
                  }

                  if ((double)(Main.BeastHealth * 80 / 100) < var7.getHealth() && var7.getHealth() < (double)(Main.BeastHealth * 90 / 100)) {
                     Titles.sendTitle(var12, Integer.valueOf(0), Integer.valueOf(10), Integer.valueOf(0), " ", "\u00a77" + (int)var7.getHealth() + "\u00a7a \u25cf\u25cf\u25cf\u25cf\u25cf\u25cf\u25cf\u25cf\u00a78\u25cb\u25cb\u00a77 " + (int)var7.getHealth());
                  }

                  if ((double)(Main.BeastHealth * 70 / 100) < var7.getHealth() && var7.getHealth() < (double)(Main.BeastHealth * 80 / 100)) {
                     Titles.sendTitle(var12, Integer.valueOf(0), Integer.valueOf(10), Integer.valueOf(0), " ", "\u00a77" + (int)var7.getHealth() + "\u00a7a \u25cf\u25cf\u25cf\u25cf\u25cf\u25cf\u25cf\u00a78\u25cb\u25cb\u25cb\u00a77 " + (int)var7.getHealth());
                  }

                  if ((double)(Main.BeastHealth * 60 / 100) < var7.getHealth() && var7.getHealth() < (double)(Main.BeastHealth * 70 / 100)) {
                     Titles.sendTitle(var12, Integer.valueOf(0), Integer.valueOf(10), Integer.valueOf(0), " ", "\u00a77" + (int)var7.getHealth() + "\u00a7a \u25cf\u25cf\u25cf\u25cf\u25cf\u25cf\u00a78\u25cb\u25cb\u25cb\u25cb\u00a77 " + (int)var7.getHealth());
                  }

                  if ((double)(Main.BeastHealth * 50 / 100) < var7.getHealth() && var7.getHealth() < (double)(Main.BeastHealth * 60 / 100)) {
                     Titles.sendTitle(var12, Integer.valueOf(0), Integer.valueOf(10), Integer.valueOf(0), " ", "\u00a77" + (int)var7.getHealth() + "\u00a7e \u25cf\u25cf\u25cf\u25cf\u25cf\u00a78\u25cb\u25cb\u25cb\u25cb\u25cb\u00a77 " + (int)var7.getHealth());
                  }

                  if ((double)(Main.BeastHealth * 40 / 100) < var7.getHealth() && var7.getHealth() < (double)(Main.BeastHealth * 50 / 100)) {
                     Titles.sendTitle(var12, Integer.valueOf(0), Integer.valueOf(10), Integer.valueOf(0), " ", "\u00a77" + (int)var7.getHealth() + "\u00a7e \u25cf\u25cf\u25cf\u25cf\u00a78\u25cb\u25cb\u25cb\u25cb\u25cb\u25cb\u00a77 " + (int)var7.getHealth());
                  }

                  if ((double)(Main.BeastHealth * 30 / 100) < var7.getHealth() && var7.getHealth() < (double)(Main.BeastHealth * 40 / 100)) {
                     Titles.sendTitle(var12, Integer.valueOf(0), Integer.valueOf(10), Integer.valueOf(0), " ", "\u00a77" + (int)var7.getHealth() + "\u00a7c \u25cf\u25cf\u25cf\u00a78\u25cb\u25cb\u25cb\u25cb\u25cb\u25cb\u25cb \u00a77" + (int)var7.getHealth());
                  }

                  if ((double)(Main.BeastHealth * 20 / 100) < var7.getHealth() && var7.getHealth() < (double)(Main.BeastHealth * 30 / 100)) {
                     Titles.sendTitle(var12, Integer.valueOf(0), Integer.valueOf(10), Integer.valueOf(0), " ", "\u00a77" + (int)var7.getHealth() + "\u00a7c \u25cf\u25cf\u00a78\u25cb\u25cb\u25cb\u25cb\u25cb\u25cb\u25cb\u25cb\u00a77 " + (int)var7.getHealth());
                  }

                  if ((double)(Main.BeastHealth * 10 / 100) < var7.getHealth() && var7.getHealth() < (double)(Main.BeastHealth * 20 / 100)) {
                     Titles.sendTitle(var12, Integer.valueOf(0), Integer.valueOf(10), Integer.valueOf(0), " ", "\u00a77" + (int)var7.getHealth() + "\u00a7c \u25cf\u00a78\u25cb\u25cb\u25cb\u25cb\u25cb\u25cb\u25cb\u25cb\u25cb\u00a77 " + (int)var7.getHealth());
                  }

                  if (0.0D < var7.getHealth() && var7.getHealth() < (double)(Main.BeastHealth * 10 / 100)) {
                     Titles.sendTitle(var12, Integer.valueOf(0), Integer.valueOf(10), Integer.valueOf(0), " ", "\u00a77" + (int)var7.getHealth() + "\u00a78 \u25cb\u25cb\u25cb\u25cb\u25cb\u25cb\u25cb\u25cb\u25cb\u25cb\u00a77 " + (int)var7.getHealth());
                  }
               } else if (var1.getEntity() instanceof Horse) {
                  Horse var8 = (Horse)var1.getEntity();
                  if (var1.getDamager() instanceof Projectile) {
                     var13 = (Projectile)var1.getDamager();
                     var12 = (Player)var13.getShooter();
                  } else {
                     if (!(var1.getDamager() instanceof Player)) {
                        var1.setCancelled(true);
                        return;
                     }

                     var12 = (Player)var1.getDamager();
                  }

                  if (Main.spectators.contains(var12)) {
                     var1.setCancelled(true);
                  }

                  if (var8.getHealth() == (double)Main.BeastHealth) {
                     Titles.sendTitle(var12, Integer.valueOf(0), Integer.valueOf(10), Integer.valueOf(0), " ", "\u00a77" + (int)var8.getHealth() + "\u00a7a \u25cf\u25cf\u25cf\u25cf\u25cf\u25cf\u25cf\u25cf\u25cf\u25cf\u00a77 " + (int)var8.getHealth());
                  }

                  if ((double)(Main.BeastHealth * 90 / 100) < var8.getHealth() && var8.getHealth() < (double)Main.BeastHealth) {
                     Titles.sendTitle(var12, Integer.valueOf(0), Integer.valueOf(10), Integer.valueOf(0), " ", "\u00a77" + (int)var8.getHealth() + "\u00a7a \u25cf\u25cf\u25cf\u25cf\u25cf\u25cf\u25cf\u25cf\u25cf\u00a78\u25cb\u00a77 " + (int)var8.getHealth());
                  }

                  if ((double)(Main.BeastHealth * 80 / 100) < var8.getHealth() && var8.getHealth() < (double)(Main.BeastHealth * 90 / 100)) {
                     Titles.sendTitle(var12, Integer.valueOf(0), Integer.valueOf(10), Integer.valueOf(0), " ", "\u00a77" + (int)var8.getHealth() + "\u00a7a \u25cf\u25cf\u25cf\u25cf\u25cf\u25cf\u25cf\u25cf\u00a78\u25cb\u25cb\u00a77 " + (int)var8.getHealth());
                  }

                  if ((double)(Main.BeastHealth * 70 / 100) < var8.getHealth() && var8.getHealth() < (double)(Main.BeastHealth * 80 / 100)) {
                     Titles.sendTitle(var12, Integer.valueOf(0), Integer.valueOf(10), Integer.valueOf(0), " ", "\u00a77" + (int)var8.getHealth() + "\u00a7a \u25cf\u25cf\u25cf\u25cf\u25cf\u25cf\u25cf\u00a78\u25cb\u25cb\u25cb\u00a77 " + (int)var8.getHealth());
                  }

                  if ((double)(Main.BeastHealth * 60 / 100) < var8.getHealth() && var8.getHealth() < (double)(Main.BeastHealth * 70 / 100)) {
                     Titles.sendTitle(var12, Integer.valueOf(0), Integer.valueOf(10), Integer.valueOf(0), " ", "\u00a77" + (int)var8.getHealth() + "\u00a7a \u25cf\u25cf\u25cf\u25cf\u25cf\u25cf\u00a78\u25cb\u25cb\u25cb\u25cb\u00a77 " + (int)var8.getHealth());
                  }

                  if ((double)(Main.BeastHealth * 50 / 100) < var8.getHealth() && var8.getHealth() < (double)(Main.BeastHealth * 60 / 100)) {
                     Titles.sendTitle(var12, Integer.valueOf(0), Integer.valueOf(10), Integer.valueOf(0), " ", "\u00a77" + (int)var8.getHealth() + "\u00a7e \u25cf\u25cf\u25cf\u25cf\u25cf\u00a78\u25cb\u25cb\u25cb\u25cb\u25cb\u00a77 " + (int)var8.getHealth());
                  }

                  if ((double)(Main.BeastHealth * 40 / 100) < var8.getHealth() && var8.getHealth() < (double)(Main.BeastHealth * 50 / 100)) {
                     Titles.sendTitle(var12, Integer.valueOf(0), Integer.valueOf(10), Integer.valueOf(0), " ", "\u00a77" + (int)var8.getHealth() + "\u00a7e \u25cf\u25cf\u25cf\u25cf\u00a78\u25cb\u25cb\u25cb\u25cb\u25cb\u25cb\u00a77 " + (int)var8.getHealth());
                  }

                  if ((double)(Main.BeastHealth * 30 / 100) < var8.getHealth() && var8.getHealth() < (double)(Main.BeastHealth * 40 / 100)) {
                     Titles.sendTitle(var12, Integer.valueOf(0), Integer.valueOf(10), Integer.valueOf(0), " ", "\u00a77" + (int)var8.getHealth() + "\u00a7c \u25cf\u25cf\u25cf\u00a78\u25cb\u25cb\u25cb\u25cb\u25cb\u25cb\u25cb \u00a77" + (int)var8.getHealth());
                  }

                  if ((double)(Main.BeastHealth * 20 / 100) < var8.getHealth() && var8.getHealth() < (double)(Main.BeastHealth * 30 / 100)) {
                     Titles.sendTitle(var12, Integer.valueOf(0), Integer.valueOf(10), Integer.valueOf(0), " ", "\u00a77" + (int)var8.getHealth() + "\u00a7c \u25cf\u25cf\u00a78\u25cb\u25cb\u25cb\u25cb\u25cb\u25cb\u25cb\u25cb\u00a77 " + (int)var8.getHealth());
                  }

                  if ((double)(Main.BeastHealth * 10 / 100) < var8.getHealth() && var8.getHealth() < (double)(Main.BeastHealth * 20 / 100)) {
                     Titles.sendTitle(var12, Integer.valueOf(0), Integer.valueOf(10), Integer.valueOf(0), " ", "\u00a77" + (int)var8.getHealth() + "\u00a7c \u25cf\u00a78\u25cb\u25cb\u25cb\u25cb\u25cb\u25cb\u25cb\u25cb\u25cb\u00a77 " + (int)var8.getHealth());
                  }

                  if (0.0D < var8.getHealth() && var8.getHealth() < (double)(Main.BeastHealth * 10 / 100)) {
                     Titles.sendTitle(var12, Integer.valueOf(0), Integer.valueOf(10), Integer.valueOf(0), " ", "\u00a77" + (int)var8.getHealth() + "\u00a78 \u25cb\u25cb\u25cb\u25cb\u25cb\u25cb\u25cb\u25cb\u25cb\u25cb\u00a77 " + (int)var8.getHealth());
                  }
               }
            }

         }
      }
   }
}
