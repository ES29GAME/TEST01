package com.andrei1058.skygiants.listeners;

import com.andrei1058.skygiants.Main;
import com.andrei1058.skygiants.commands.Team;
import com.andrei1058.skygiants.configuration.Messages;
import com.andrei1058.skygiants.game.GameState;
import com.andrei1058.skygiants.game.Shop;
import java.util.ArrayList;
import java.util.Iterator;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.ClickEvent.Action;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionType;

public class InventoryClickListener implements Listener {
   @EventHandler
   public void inv(InventoryClickEvent var1) {
      if (var1.getCurrentItem() != null) {
         if (var1.getCurrentItem().getType() != Material.AIR) {
            if (var1.getCurrentItem().getItemMeta().getDisplayName() != null) {
               if (var1.getCurrentItem().getType() == Material.SKULL_ITEM) {
                  Player var2;
                  if ((Main.STATUS == GameState.PLAYING || Main.STATUS == GameState.RESTARTING) && var1.getCurrentItem().getItemMeta().getDisplayName() != null) {
                     if (var1.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("Beacon")) {
                        var1.setCancelled(true);
                     } else {
                        var2 = Bukkit.getPlayer(var1.getCurrentItem().getItemMeta().getDisplayName());
                        if (Main.players.contains(var2)) {
                           var1.getWhoClicked().teleport(Bukkit.getPlayer(var2.getName()));
                           var1.setCancelled(true);
                        } else {
                           var1.setCancelled(true);
                           var1.getWhoClicked().closeInventory();
                        }
                     }
                  }

                  if (Main.STATUS == GameState.LOBBY || Main.STATUS == GameState.STARTING) {
                     var1.setCancelled(true);
                     if (Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(ChatColor.stripColor(var1.getCurrentItem().getItemMeta().getDisplayName())))) {
                        var2 = Bukkit.getPlayer(ChatColor.stripColor(var1.getCurrentItem().getItemMeta().getDisplayName()));
                        if (Main.hasTeam.contains(var2)) {
                           var1.getWhoClicked().sendMessage(Main.PREFIX + Messages.getMsg().getString("has-team").replace('&', '\u00a7'));
                           return;
                        }

                        if (Team.hasInvited.containsKey(var2)) {
                           if (var1.getWhoClicked().getUniqueId().toString() == ((Player)Team.hasInvited.get(var2)).getUniqueId().toString()) {
                              var2.sendMessage(Main.PREFIX + Messages.getMsg().getString("teamed-up").replace("{name}", var1.getWhoClicked().getName()).replace('&', '\u00a7'));
                              ((Player)Team.hasInvited.get(var2)).sendMessage(Main.PREFIX + Messages.getMsg().getString("teamed-up").replace("{name}", var2.getName()).replace('&', '\u00a7'));
                              Main.hasTeam.add(var2);
                              Main.hasTeam.add((Player)var1.getWhoClicked());
                              if (Main.goldPlayers.isEmpty()) {
                                 Main.goldPlayers.add(var2);
                                 Main.goldPlayers.add(Team.hasInvited.get(var2));
                              } else if (Main.magentaPlayers.isEmpty()) {
                                 Main.magentaPlayers.add(var2);
                                 Main.magentaPlayers.add(Team.hasInvited.get(var2));
                              } else if (Main.greenPlayers.isEmpty()) {
                                 Main.greenPlayers.add(var2);
                                 Main.greenPlayers.add(Team.hasInvited.get(var2));
                              } else if (Main.bluePlayers.isEmpty()) {
                                 Main.bluePlayers.add(var2);
                                 Main.bluePlayers.add(Team.hasInvited.get(var2));
                              }

                              var1.setCancelled(true);
                              var1.getWhoClicked().closeInventory();
                              return;
                           }

                           var1.setCancelled(true);
                        }

                        TextComponent var3 = new TextComponent(Main.PREFIX + Messages.getMsg().getString("invite-click").replace('&', '\u00a7'));
                        var3.setClickEvent(new ClickEvent(Action.RUN_COMMAND, "/team " + var1.getWhoClicked().getName()));
                        var2.sendMessage(Main.PREFIX + Messages.getMsg().getString("invite-received").replace("{name}", var1.getWhoClicked().getName()).replace('&', '\u00a7'));
                        var2.spigot().sendMessage(var3);
                        var1.getWhoClicked().sendMessage(Main.PREFIX + Messages.getMsg().getString("invite-sent").replace("{name}", var2.getName()).replace('&', '\u00a7'));
                        if (Team.hasInvited.containsKey(var1.getWhoClicked())) {
                           ((Player)Team.hasInvited.get(var1.getWhoClicked())).sendMessage(Main.PREFIX + Messages.getMsg().getString("invite-revoked").replace("{player}", var1.getWhoClicked().getName()).replace('&', '\u00a7'));
                        }

                        Team.isInvited.put(var2, (Player)var1.getWhoClicked());
                        Team.hasInvited.put((Player)var1.getWhoClicked(), var2);
                        var1.setCancelled(true);
                        var1.getWhoClicked().closeInventory();
                     } else {
                        var1.setCancelled(true);
                        var1.getWhoClicked().closeInventory();
                     }
                  }
               }

               if (var1.getCurrentItem().getType() == Material.IRON_SWORD) {
                  if (var1.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(Messages.getMsg().getString("shop.gear").replace('&', '\u00a7'))) {
                     var1.getWhoClicked().openInventory(Shop.gearShop());
                     var1.setCancelled(true);
                  }
               } else if (var1.getCurrentItem().getType() == Material.APPLE) {
                  if (var1.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(Messages.getMsg().getString("shop.food").replace('&', '\u00a7'))) {
                     var1.getWhoClicked().openInventory(Shop.foodShop());
                     var1.setCancelled(true);
                  }

                  if (var1.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(itemName("shop.apple", Integer.valueOf(15)))) {
                     Shop.buy(Integer.valueOf(25), (Player)var1.getWhoClicked(), new ItemStack(Material.APPLE, 15));
                     var1.setCancelled(true);
                  }
               } else if (var1.getCurrentItem().getType() == Material.POTION) {
                  if (var1.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(Messages.getMsg().getString("shop.potions").replace('&', '\u00a7'))) {
                     var1.getWhoClicked().openInventory(Shop.potionShop());
                     var1.setCancelled(true);
                  } else if (var1.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(potionName("shop.invisibility"))) {
                     Shop.buy(Integer.valueOf(750), (Player)var1.getWhoClicked(), new ItemStack(Main.nmsH.potion(PotionType.INVISIBILITY)));
                     var1.setCancelled(true);
                  } else if (var1.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(potionName("shop.regeneration"))) {
                     Shop.buy(Integer.valueOf(600), (Player)var1.getWhoClicked(), new ItemStack(Main.nmsH.potion(PotionType.REGEN)));
                     var1.setCancelled(true);
                  } else if (var1.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(potionName("shop.swiftness"))) {
                     Shop.buy(Integer.valueOf(350), (Player)var1.getWhoClicked(), new ItemStack(Main.nmsH.potion(PotionType.SPEED)));
                     var1.setCancelled(true);
                  } else if (var1.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(potionName("shop.flamer"))) {
                     Shop.buy(Integer.valueOf(200), (Player)var1.getWhoClicked(), new ItemStack(Main.nmsH.potion(PotionType.FIRE_RESISTANCE)));
                     var1.setCancelled(true);
                  } else if (var1.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(potionName("shop.rabbitp"))) {
                     Shop.buy(Integer.valueOf(200), (Player)var1.getWhoClicked(), new ItemStack(Main.nmsH.potion(PotionType.JUMP)));
                     var1.setCancelled(true);
                  } else if (var1.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(potionName("shop.instanth"))) {
                     Shop.buy(Integer.valueOf(250), (Player)var1.getWhoClicked(), new ItemStack(Main.nmsH.potion(PotionType.INSTANT_HEAL)));
                     var1.setCancelled(true);
                  } else if (var1.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(potionName("shop.hurting"))) {
                     Shop.buy(Integer.valueOf(400), (Player)var1.getWhoClicked(), new ItemStack(Main.nmsH.potion(PotionType.INSTANT_DAMAGE)));
                     var1.setCancelled(true);
                  } else if (var1.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(potionName("shop.deadlyp"))) {
                     Shop.buy(Integer.valueOf(1000), (Player)var1.getWhoClicked(), new ItemStack(Main.nmsH.potion(PotionType.POISON)));
                     var1.setCancelled(true);
                  }
               } else if (var1.getCurrentItem().getType() == Material.SEA_LANTERN) {
                  if (var1.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(Messages.getMsg().getString("shop.vanity").replace('&', '\u00a7'))) {
                     var1.getWhoClicked().openInventory(Shop.vanityShop());
                     var1.setCancelled(true);
                  } else if (var1.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(itemName("shop.lantern", Integer.valueOf(32)))) {
                     Shop.buy(Integer.valueOf(50), (Player)var1.getWhoClicked(), new ItemStack(Material.SEA_LANTERN, 32));
                     var1.setCancelled(true);
                  }
               } else if (var1.getCurrentItem().getType() == Material.RAILS) {
                  if (var1.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(Messages.getMsg().getString("shop.specials").replace('&', '\u00a7'))) {
                     var1.setCancelled(true);
                  }
               } else if (var1.getCurrentItem().getType() == Material.DIAMOND_SWORD) {
                  if (var1.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(itemName("shop.sword", Integer.valueOf(1)))) {
                     Shop.buy(Integer.valueOf(250), (Player)var1.getWhoClicked(), new ItemStack(Material.DIAMOND_SWORD, 1));
                     var1.setCancelled(true);
                  }
               } else if (var1.getCurrentItem().getType() == Material.DIAMOND_PICKAXE) {
                  if (var1.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(itemName("shop.pickaxe", Integer.valueOf(1)))) {
                     Shop.buy(Integer.valueOf(150), (Player)var1.getWhoClicked(), new ItemStack(Material.DIAMOND_PICKAXE, 1));
                     var1.setCancelled(true);
                  }
               } else if (var1.getCurrentItem().getType() == Material.DIAMOND_HELMET) {
                  if (var1.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(itemName("shop.helmet", Integer.valueOf(1)))) {
                     Shop.buy(Integer.valueOf(250), (Player)var1.getWhoClicked(), new ItemStack(Material.DIAMOND_HELMET, 1));
                     var1.setCancelled(true);
                  }
               } else if (var1.getCurrentItem().getType() == Material.DIAMOND_CHESTPLATE) {
                  if (var1.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(itemName("shop.chestplate", Integer.valueOf(1)))) {
                     Shop.buy(Integer.valueOf(350), (Player)var1.getWhoClicked(), new ItemStack(Material.DIAMOND_CHESTPLATE, 1));
                     var1.setCancelled(true);
                  }
               } else if (var1.getCurrentItem().getType() == Material.DIAMOND_LEGGINGS) {
                  if (var1.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(itemName("shop.leggings", Integer.valueOf(1)))) {
                     Shop.buy(Integer.valueOf(200), (Player)var1.getWhoClicked(), new ItemStack(Material.DIAMOND_LEGGINGS, 1));
                     var1.setCancelled(true);
                  }
               } else if (var1.getCurrentItem().getType() == Material.DIAMOND_BOOTS) {
                  if (var1.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(itemName("shop.boots", Integer.valueOf(1)))) {
                     Shop.buy(Integer.valueOf(150), (Player)var1.getWhoClicked(), new ItemStack(Material.DIAMOND_BOOTS, 1));
                     var1.setCancelled(true);
                  }
               } else if (var1.getCurrentItem().getType() == Material.BOW) {
                  if (var1.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(itemName("shop.bow", Integer.valueOf(1)))) {
                     Shop.buy(Integer.valueOf(250), (Player)var1.getWhoClicked(), new ItemStack(Material.BOW, 1));
                     var1.setCancelled(true);
                  }
               } else if (var1.getCurrentItem().getType() == Material.ARROW) {
                  if (var1.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(itemName("shop.arrows", Integer.valueOf(16)))) {
                     Shop.buy(Integer.valueOf(400), (Player)var1.getWhoClicked(), new ItemStack(Material.ARROW, 16));
                     var1.setCancelled(true);
                  }
               } else if (var1.getCurrentItem().getType() == Material.MELON) {
                  if (var1.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(itemName("shop.melon", Integer.valueOf(15)))) {
                     Shop.buy(Integer.valueOf(20), (Player)var1.getWhoClicked(), new ItemStack(Material.MELON, 15));
                     var1.setCancelled(true);
                  }
               } else if (var1.getCurrentItem().getType() == Material.CARROT_ITEM) {
                  if (var1.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(itemName("shop.carrot", Integer.valueOf(15)))) {
                     Shop.buy(Integer.valueOf(25), (Player)var1.getWhoClicked(), new ItemStack(Material.CARROT_ITEM, 15));
                     var1.setCancelled(true);
                  }
               } else if (var1.getCurrentItem().getType() == Material.BREAD) {
                  if (var1.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(itemName("shop.bread", Integer.valueOf(5)))) {
                     Shop.buy(Integer.valueOf(35), (Player)var1.getWhoClicked(), new ItemStack(Material.BREAD, 5));
                     var1.setCancelled(true);
                  }
               } else if (var1.getCurrentItem().getType() == Material.COOKED_CHICKEN) {
                  if (var1.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(itemName("shop.chicken", Integer.valueOf(5)))) {
                     Shop.buy(Integer.valueOf(40), (Player)var1.getWhoClicked(), new ItemStack(Material.COOKED_CHICKEN, 5));
                     var1.setCancelled(true);
                  }
               } else if (var1.getCurrentItem().getType() == Material.COOKED_BEEF) {
                  if (var1.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(itemName("shop.steak", Integer.valueOf(5)))) {
                     Shop.buy(Integer.valueOf(45), (Player)var1.getWhoClicked(), new ItemStack(Material.COOKED_BEEF, 5));
                     var1.setCancelled(true);
                  }
               } else if (var1.getCurrentItem().getType() == Material.CAKE) {
                  if (var1.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(itemName("shop.cake", Integer.valueOf(3)))) {
                     Shop.buy(Integer.valueOf(50), (Player)var1.getWhoClicked(), new ItemStack(Material.CAKE, 3));
                     var1.setCancelled(true);
                  }
               } else if (var1.getCurrentItem().getType() == Material.GOLDEN_APPLE) {
                  if (var1.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(itemName("shop.golden", Integer.valueOf(1)))) {
                     Shop.buy(Integer.valueOf(1000), (Player)var1.getWhoClicked(), new ItemStack(Material.GOLDEN_APPLE));
                     var1.setCancelled(true);
                  }
               } else if (var1.getCurrentItem().getType() == Material.MILK_BUCKET) {
                  if (var1.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(itemName("shop.milk", Integer.valueOf(1)))) {
                     Shop.buy(Integer.valueOf(25), (Player)var1.getWhoClicked(), new ItemStack(Material.MILK_BUCKET));
                     var1.setCancelled(true);
                  }
               } else if (var1.getCurrentItem().getType() == Material.EXP_BOTTLE) {
                  if (var1.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(itemName("shop.exp", Integer.valueOf(1)))) {
                     Shop.buy(Integer.valueOf(2000), (Player)var1.getWhoClicked(), new ItemStack(Material.EXP_BOTTLE));
                     var1.setCancelled(true);
                  }
               } else if (var1.getCurrentItem().getType() == Material.PRISMARINE) {
                  if (var1.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(itemName("shop.prismarine", Integer.valueOf(32)))) {
                     Shop.buy(Integer.valueOf(50), (Player)var1.getWhoClicked(), new ItemStack(Material.PRISMARINE, 32));
                     var1.setCancelled(true);
                  }
               } else if (var1.getCurrentItem().getType() == Material.GLOWSTONE) {
                  if (var1.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(itemName("shop.glowstone", Integer.valueOf(32)))) {
                     Shop.buy(Integer.valueOf(50), (Player)var1.getWhoClicked(), new ItemStack(Material.GLOWSTONE, 32));
                     var1.setCancelled(true);
                  }
               } else if (var1.getCurrentItem().getType() == Material.STAINED_CLAY) {
                  ArrayList var4;
                  Iterator var5;
                  String var6;
                  ItemStack var7;
                  ItemMeta var8;
                  if (var1.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(Messages.getMsg().getString("join").replace("{team}", Messages.getMsg().getString("gold-team")).replace('&', '\u00a7'))) {
                     if (Main.goldPlayers.size() > Main.magentaPlayers.size() || Main.goldPlayers.size() > Main.greenPlayers.size() || Main.goldPlayers.size() > Main.bluePlayers.size()) {
                        var1.getWhoClicked().sendMessage(Main.PREFIX + Messages.getMsg().getString("cannot-join-team").replace('&', '\u00a7'));
                        var1.setCancelled(true);
                        var1.getWhoClicked().closeInventory();
                        return;
                     }

                     Main.goldPlayers.remove(var1.getWhoClicked());
                     Main.bluePlayers.remove(var1.getWhoClicked());
                     Main.magentaPlayers.remove(var1.getWhoClicked());
                     Main.greenPlayers.remove(var1.getWhoClicked());
                     Main.goldPlayers.add((Player)var1.getWhoClicked());
                     var1.getWhoClicked().sendMessage(Main.PREFIX + Messages.getMsg().getString("team-queued").replace("{team}", Messages.getMsg().getString("gold-team")).replace('&', '\u00a7'));
                     var1.setCancelled(true);
                     var7 = new ItemStack(Material.STAINED_CLAY, 1, (short)4);
                     var8 = var7.getItemMeta();
                     var8.setDisplayName(Messages.getMsg().getString("normal-team").replace('&', '\u00a7'));
                     var4 = new ArrayList();
                     var5 = Messages.getMsg().getStringList("choose-team").iterator();

                     while(var5.hasNext()) {
                        var6 = (String)var5.next();
                        var4.add(var6.replace('&', '\u00a7'));
                     }

                     var8.setLore(var4);
                     var7.setItemMeta(var8);
                     var1.getWhoClicked().closeInventory();
                     var1.getWhoClicked().getInventory().setItem(4, var7);
                  } else if (var1.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(Messages.getMsg().getString("join").replace("{team}", Messages.getMsg().getString("magenta-team")).replace('&', '\u00a7'))) {
                     if (Main.magentaPlayers.size() > Main.goldPlayers.size() || Main.magentaPlayers.size() > Main.greenPlayers.size() || Main.magentaPlayers.size() > Main.bluePlayers.size()) {
                        var1.getWhoClicked().sendMessage(Main.PREFIX + Messages.getMsg().getString("cannot-join-team").replace('&', '\u00a7'));
                        var1.setCancelled(true);
                        return;
                     }

                     Main.goldPlayers.remove(var1.getWhoClicked());
                     Main.bluePlayers.remove(var1.getWhoClicked());
                     Main.magentaPlayers.remove(var1.getWhoClicked());
                     Main.greenPlayers.remove(var1.getWhoClicked());
                     Main.magentaPlayers.add((Player)var1.getWhoClicked());
                     var1.getWhoClicked().sendMessage(Main.PREFIX + Messages.getMsg().getString("team-queued").replace("{team}", Messages.getMsg().getString("magenta-team")).replace('&', '\u00a7'));
                     var7 = new ItemStack(Material.STAINED_CLAY, 1, (short)2);
                     var8 = var7.getItemMeta();
                     var8.setDisplayName(Messages.getMsg().getString("normal-team").replace('&', '\u00a7'));
                     var4 = new ArrayList();
                     var5 = Messages.getMsg().getStringList("choose-team").iterator();

                     while(var5.hasNext()) {
                        var6 = (String)var5.next();
                        var4.add(var6.replace('&', '\u00a7'));
                     }

                     var8.setLore(var4);
                     var7.setItemMeta(var8);
                     var1.getWhoClicked().getInventory().setItem(4, var7);
                     var1.setCancelled(true);
                     var1.getWhoClicked().closeInventory();
                  } else if (var1.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(Messages.getMsg().getString("join").replace("{team}", Messages.getMsg().getString("green-team")).replace('&', '\u00a7'))) {
                     if (Main.greenPlayers.size() > Main.goldPlayers.size() || Main.greenPlayers.size() > Main.magentaPlayers.size() || Main.greenPlayers.size() > Main.bluePlayers.size()) {
                        var1.getWhoClicked().sendMessage(Main.PREFIX + Messages.getMsg().getString("cannot-join-team").replace('&', '\u00a7'));
                        var1.setCancelled(true);
                        return;
                     }

                     Main.goldPlayers.remove(var1.getWhoClicked());
                     Main.bluePlayers.remove(var1.getWhoClicked());
                     Main.magentaPlayers.remove(var1.getWhoClicked());
                     Main.greenPlayers.remove(var1.getWhoClicked());
                     Main.greenPlayers.add((Player)var1.getWhoClicked());
                     var1.getWhoClicked().sendMessage(Main.PREFIX + Messages.getMsg().getString("team-queued").replace("{team}", Messages.getMsg().getString("green-team")).replace('&', '\u00a7'));
                     var7 = new ItemStack(Material.STAINED_CLAY, 1, (short)5);
                     var8 = var7.getItemMeta();
                     var8.setDisplayName(Messages.getMsg().getString("normal-team").replace('&', '\u00a7'));
                     var4 = new ArrayList();
                     var5 = Messages.getMsg().getStringList("choose-team").iterator();

                     while(var5.hasNext()) {
                        var6 = (String)var5.next();
                        var4.add(var6.replace('&', '\u00a7'));
                     }

                     var8.setLore(var4);
                     var7.setItemMeta(var8);
                     var1.getWhoClicked().getInventory().setItem(4, var7);
                     var1.setCancelled(true);
                     var1.getWhoClicked().closeInventory();
                  } else if (var1.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(Messages.getMsg().getString("join").replace("{team}", Messages.getMsg().getString("blue-team")).replace('&', '\u00a7'))) {
                     if (Main.bluePlayers.size() > Main.goldPlayers.size() || Main.bluePlayers.size() > Main.magentaPlayers.size() || Main.bluePlayers.size() > Main.greenPlayers.size()) {
                        var1.getWhoClicked().sendMessage(Main.PREFIX + Messages.getMsg().getString("cannot-join-team").replace('&', '\u00a7'));
                        var1.setCancelled(true);
                        return;
                     }

                     Main.goldPlayers.remove(var1.getWhoClicked());
                     Main.bluePlayers.remove(var1.getWhoClicked());
                     Main.magentaPlayers.remove(var1.getWhoClicked());
                     Main.greenPlayers.remove(var1.getWhoClicked());
                     Main.bluePlayers.add((Player)var1.getWhoClicked());
                     var1.getWhoClicked().sendMessage(Main.PREFIX + Messages.getMsg().getString("team-queued").replace("{team}", Messages.getMsg().getString("blue-team")).replace('&', '\u00a7'));
                     var7 = new ItemStack(Material.STAINED_CLAY, 1, (short)11);
                     var8 = var7.getItemMeta();
                     var8.setDisplayName(Messages.getMsg().getString("normal-team").replace('&', '\u00a7'));
                     var4 = new ArrayList();
                     var5 = Messages.getMsg().getStringList("choose-team").iterator();

                     while(var5.hasNext()) {
                        var6 = (String)var5.next();
                        var4.add(var6.replace('&', '\u00a7'));
                     }

                     var8.setLore(var4);
                     var7.setItemMeta(var8);
                     var1.getWhoClicked().getInventory().setItem(4, var7);
                     var1.setCancelled(true);
                     var1.getWhoClicked().closeInventory();
                  } else if (var1.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(itemName("shop.orangeclay", Integer.valueOf(32)))) {
                     Shop.buy(Integer.valueOf(50), (Player)var1.getWhoClicked(), new ItemStack(Material.STAINED_CLAY, 32, (short)1));
                     var1.setCancelled(true);
                  } else if (var1.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(itemName("shop.magentaclay", Integer.valueOf(32)))) {
                     Shop.buy(Integer.valueOf(50), (Player)var1.getWhoClicked(), new ItemStack(Material.STAINED_CLAY, 32, (short)2));
                     var1.setCancelled(true);
                  } else if (var1.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(itemName("shop.lightblueclay", Integer.valueOf(32)))) {
                     Shop.buy(Integer.valueOf(50), (Player)var1.getWhoClicked(), new ItemStack(Material.STAINED_CLAY, 32, (short)3));
                     var1.setCancelled(true);
                  } else if (var1.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(itemName("shop.yellowclay", Integer.valueOf(32)))) {
                     Shop.buy(Integer.valueOf(50), (Player)var1.getWhoClicked(), new ItemStack(Material.STAINED_CLAY, 32, (short)4));
                     var1.setCancelled(true);
                  } else if (var1.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(itemName("shop.limeclay", Integer.valueOf(32)))) {
                     Shop.buy(Integer.valueOf(50), (Player)var1.getWhoClicked(), new ItemStack(Material.STAINED_CLAY, 32, (short)5));
                     var1.setCancelled(true);
                  } else if (var1.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(itemName("shop.pinkclay", Integer.valueOf(32)))) {
                     Shop.buy(Integer.valueOf(50), (Player)var1.getWhoClicked(), new ItemStack(Material.STAINED_CLAY, 32, (short)6));
                     var1.setCancelled(true);
                  } else if (var1.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(itemName("shop.cyanclay", Integer.valueOf(32)))) {
                     Shop.buy(Integer.valueOf(50), (Player)var1.getWhoClicked(), new ItemStack(Material.STAINED_CLAY, 32, (short)9));
                     var1.setCancelled(true);
                  } else if (var1.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(itemName("shop.purpleclay", Integer.valueOf(32)))) {
                     Shop.buy(Integer.valueOf(50), (Player)var1.getWhoClicked(), new ItemStack(Material.STAINED_CLAY, 32, (short)10));
                     var1.setCancelled(true);
                  } else if (var1.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(itemName("shop.blueclay", Integer.valueOf(32)))) {
                     Shop.buy(Integer.valueOf(50), (Player)var1.getWhoClicked(), new ItemStack(Material.STAINED_CLAY, 32, (short)11));
                     var1.setCancelled(true);
                  } else if (var1.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(itemName("shop.redclay", Integer.valueOf(32)))) {
                     Shop.buy(Integer.valueOf(50), (Player)var1.getWhoClicked(), new ItemStack(Material.STAINED_CLAY, 32, (short)14));
                     var1.setCancelled(true);
                  } else if (var1.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(itemName("shop.yellowclay", Integer.valueOf(32)))) {
                     Shop.buy(Integer.valueOf(50), (Player)var1.getWhoClicked(), new ItemStack(Material.STAINED_CLAY, 32, (short)4));
                     var1.setCancelled(true);
                  }
               } else if (var1.getCurrentItem().getType() == Material.STAINED_GLASS) {
                  if (var1.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(itemName("shop.orangeglass", Integer.valueOf(32)))) {
                     Shop.buy(Integer.valueOf(50), (Player)var1.getWhoClicked(), new ItemStack(Material.STAINED_GLASS, 32, (short)1));
                     var1.setCancelled(true);
                  } else if (var1.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(itemName("shop.magentaglass", Integer.valueOf(32)))) {
                     Shop.buy(Integer.valueOf(50), (Player)var1.getWhoClicked(), new ItemStack(Material.STAINED_GLASS, 32, (short)2));
                     var1.setCancelled(true);
                  } else if (var1.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(itemName("shop.lightblueglass", Integer.valueOf(32)))) {
                     Shop.buy(Integer.valueOf(50), (Player)var1.getWhoClicked(), new ItemStack(Material.STAINED_GLASS, 32, (short)3));
                     var1.setCancelled(true);
                  } else if (var1.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(itemName("shop.yellowglass", Integer.valueOf(32)))) {
                     Shop.buy(Integer.valueOf(50), (Player)var1.getWhoClicked(), new ItemStack(Material.STAINED_GLASS, 32, (short)4));
                     var1.setCancelled(true);
                  } else if (var1.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(itemName("shop.limeglass", Integer.valueOf(32)))) {
                     Shop.buy(Integer.valueOf(50), (Player)var1.getWhoClicked(), new ItemStack(Material.STAINED_GLASS, 32, (short)5));
                     var1.setCancelled(true);
                  } else if (var1.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(itemName("shop.pinkglass", Integer.valueOf(32)))) {
                     Shop.buy(Integer.valueOf(50), (Player)var1.getWhoClicked(), new ItemStack(Material.STAINED_GLASS, 32, (short)6));
                     var1.setCancelled(true);
                  } else if (var1.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(itemName("shop.cyanglass", Integer.valueOf(32)))) {
                     Shop.buy(Integer.valueOf(50), (Player)var1.getWhoClicked(), new ItemStack(Material.STAINED_GLASS, 32, (short)9));
                     var1.setCancelled(true);
                  } else if (var1.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(itemName("shop.purpleglass", Integer.valueOf(32)))) {
                     Shop.buy(Integer.valueOf(50), (Player)var1.getWhoClicked(), new ItemStack(Material.STAINED_GLASS, 32, (short)10));
                     var1.setCancelled(true);
                  } else if (var1.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(itemName("shop.blueglass", Integer.valueOf(32)))) {
                     Shop.buy(Integer.valueOf(50), (Player)var1.getWhoClicked(), new ItemStack(Material.STAINED_GLASS, 32, (short)11));
                     var1.setCancelled(true);
                  } else if (var1.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(itemName("shop.redglass", Integer.valueOf(32)))) {
                     Shop.buy(Integer.valueOf(50), (Player)var1.getWhoClicked(), new ItemStack(Material.STAINED_GLASS, 32, (short)14));
                     var1.setCancelled(true);
                  } else if (var1.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(itemName("shop.yellowglass", Integer.valueOf(32)))) {
                     Shop.buy(Integer.valueOf(50), (Player)var1.getWhoClicked(), new ItemStack(Material.STAINED_GLASS, 32, (short)4));
                     var1.setCancelled(true);
                  }
               } else if (var1.getCurrentItem().getType() == Material.BANNER) {
                  if (var1.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(itemName("shop.redbanner", Integer.valueOf(5)))) {
                     Shop.buy(Integer.valueOf(100), (Player)var1.getWhoClicked(), new ItemStack(Material.BANNER, 5, (short)1));
                     var1.setCancelled(true);
                  } else if (var1.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(itemName("shop.greenbanner", Integer.valueOf(5)))) {
                     Shop.buy(Integer.valueOf(100), (Player)var1.getWhoClicked(), new ItemStack(Material.BANNER, 5, (short)10));
                     var1.setCancelled(true);
                  } else if (var1.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(itemName("shop.yellowbanner", Integer.valueOf(5)))) {
                     Shop.buy(Integer.valueOf(100), (Player)var1.getWhoClicked(), new ItemStack(Material.BANNER, 5, (short)11));
                     var1.setCancelled(true);
                  } else if (var1.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(itemName("shop.bluebanner", Integer.valueOf(5)))) {
                     Shop.buy(Integer.valueOf(100), (Player)var1.getWhoClicked(), new ItemStack(Material.BANNER, 5, (short)4));
                     var1.setCancelled(true);
                  }
               } else if (var1.getCurrentItem().getType() == Material.MAGMA_CREAM) {
                  if (var1.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(Messages.getMsg().getString("random-team").replace('&', '\u00a7'))) {
                     Main.goldPlayers.remove(var1.getWhoClicked());
                     Main.magentaPlayers.remove(var1.getWhoClicked());
                     Main.greenPlayers.remove(var1.getWhoClicked());
                     Main.bluePlayers.remove(var1.getWhoClicked());
                     if (Main.goldPlayers.size() < Main.magentaPlayers.size() && Main.goldPlayers.size() < Main.bluePlayers.size() && Main.goldPlayers.size() < Main.greenPlayers.size()) {
                        Main.goldPlayers.add((Player)var1.getWhoClicked());
                        var1.getWhoClicked().sendMessage(Main.PREFIX + Messages.getMsg().getString("team-queued").replace("{team}", Messages.getMsg().getString("gold-team")).replace('&', '\u00a7'));
                     }

                     if (Main.magentaPlayers.size() < Main.greenPlayers.size() && Main.magentaPlayers.size() < Main.goldPlayers.size() && Main.magentaPlayers.size() < Main.bluePlayers.size()) {
                        Main.magentaPlayers.add((Player)var1.getWhoClicked());
                        var1.getWhoClicked().sendMessage(Main.PREFIX + Messages.getMsg().getString("team-queued").replace("{team}", Messages.getMsg().getString("magenta-team")).replace('&', '\u00a7'));
                     }

                     if (Main.greenPlayers.size() < Main.magentaPlayers.size() && Main.greenPlayers.size() < Main.goldPlayers.size() && Main.greenPlayers.size() < Main.bluePlayers.size()) {
                        Main.greenPlayers.add((Player)var1.getWhoClicked());
                        var1.getWhoClicked().sendMessage(Main.PREFIX + Messages.getMsg().getString("team-queued").replace("{team}", Messages.getMsg().getString("green-team")).replace('&', '\u00a7'));
                     }

                     if (Main.bluePlayers.size() < Main.magentaPlayers.size() && Main.bluePlayers.size() < Main.goldPlayers.size() && Main.bluePlayers.size() < Main.greenPlayers.size()) {
                        Main.bluePlayers.add((Player)var1.getWhoClicked());
                        var1.getWhoClicked().sendMessage(Main.PREFIX + Messages.getMsg().getString("team-queued").replace("{team}", Messages.getMsg().getString("blue-team")).replace('&', '\u00a7'));
                     }

                     var1.setCancelled(true);
                     var1.getWhoClicked().closeInventory();
                  }
               } else if (var1.getCurrentItem().getType() == Material.BARRIER) {
                  if (var1.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(Messages.getMsg().getString("shop.back").replace('&', '\u00a7'))) {
                     var1.getWhoClicked().openInventory(Shop.mainShop());
                     var1.setCancelled(true);
                  }
               } else if (var1.getCurrentItem().getType() == Material.INK_SACK && var1.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(itemName("shop.lapis", Integer.valueOf(1)))) {
                  Shop.buy(Integer.valueOf(1000), (Player)var1.getWhoClicked(), new ItemStack(Material.INK_SACK, 1, (short)4));
                  var1.setCancelled(true);
               }

            }
         }
      }
   }

   private static String itemName(String var0, Integer var1) {
      String var2 = Messages.getMsg().getString("shop.itemformat");
      var2 = var2.replace("{item}", Messages.getMsg().getString(var0)).replace("{amount}", String.valueOf(var1)).replace('&', '\u00a7');
      return var2;
   }

   private static String potionName(String var0) {
      String var1 = Messages.getMsg().getString("shop.potionformat");
      var1 = var1.replace("{potion}", Messages.getMsg().getString(var0)).replace("{amount}", String.valueOf(1)).replace('&', '\u00a7');
      return var1;
   }
}
