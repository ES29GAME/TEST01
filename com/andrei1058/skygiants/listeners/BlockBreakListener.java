package com.andrei1058.skygiants.listeners;

import com.andrei1058.skygiants.Main;
import com.andrei1058.skygiants.configuration.Messages;
import com.andrei1058.skygiants.game.GameState;
import com.andrei1058.skygiants.utils.RandomItem;
import java.util.Random;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class BlockBreakListener implements Listener {
   @EventHandler
   public void ores(BlockBreakEvent var1) {
      if (!Main.MAINTENANCE.booleanValue()) {
         if (Main.STATUS != GameState.PLAYING) {
            if (!var1.getPlayer().isOp()) {
               var1.setCancelled(true);
            }

         } else if (Main.respawning.contains(var1.getPlayer())) {
            var1.setCancelled(true);
         } else {
            if (Main.GoldGiantRegion.isInRegion(var1.getBlock().getLocation())) {
               if (!Main.PlacedBlocks.contains(var1.getBlock().getLocation())) {
                  var1.setCancelled(true);
                  var1.getPlayer().sendMessage(Main.PREFIX + Messages.getMsg().getString("protected-region").replace('&', '\u00a7'));
               }
            } else if (Main.MagentaGiantRegion.isInRegion(var1.getBlock().getLocation())) {
               if (!Main.PlacedBlocks.contains(var1.getBlock().getLocation())) {
                  var1.setCancelled(true);
                  var1.getPlayer().sendMessage(Main.PREFIX + Messages.getMsg().getString("protected-region").replace('&', '\u00a7'));
               }
            } else if (Main.GreenGiantRegion.isInRegion(var1.getBlock().getLocation())) {
               if (!Main.PlacedBlocks.contains(var1.getBlock().getLocation())) {
                  var1.setCancelled(true);
                  var1.getPlayer().sendMessage(Main.PREFIX + Messages.getMsg().getString("protected-region").replace('&', '\u00a7'));
               }
            } else if (Main.BlueGiantRegion.isInRegion(var1.getBlock().getLocation())) {
               if (!Main.PlacedBlocks.contains(var1.getBlock().getLocation())) {
                  var1.setCancelled(true);
                  var1.getPlayer().sendMessage(Main.PREFIX + Messages.getMsg().getString("protected-region").replace('&', '\u00a7'));
               }
            } else if (Main.BeastRegion.isInRegion(var1.getBlock().getLocation())) {
               var1.setCancelled(true);
               var1.getPlayer().sendMessage(Main.PREFIX + Messages.getMsg().getString("scared-region").replace('&', '\u00a7'));
            } else if (Main.MiddleRegion.isInRegion(var1.getBlock().getLocation())) {
               if (Main.PlacedBlocks.contains(var1.getBlock().getLocation())) {
                  var1.setCancelled(false);
               } else if (var1.getBlock().getType() != Material.DIAMOND_ORE || var1.getBlock().getType() != Material.GOLD_BLOCK || var1.getBlock().getType() != Material.GOLD_ORE || var1.getBlock().getType() != Material.IRON_ORE || var1.getBlock().getType() != Material.LAPIS_ORE || var1.getBlock().getType() != Material.REDSTONE_ORE || var1.getBlock().getType() != Material.GLOWING_REDSTONE_ORE || var1.getBlock().getType() != Material.EMERALD_ORE) {
                  var1.setCancelled(true);
               }
            } else if (Main.BlueVillagerRegion.isInRegion(var1.getBlock().getLocation())) {
               var1.setCancelled(true);
               var1.getPlayer().sendMessage(Main.PREFIX + Messages.getMsg().getString("protected-region").replace('&', '\u00a7'));
            } else if (Main.GoldVillagerRegion.isInRegion(var1.getBlock().getLocation())) {
               var1.setCancelled(true);
               var1.getPlayer().sendMessage(Main.PREFIX + Messages.getMsg().getString("protected-region").replace('&', '\u00a7'));
            } else if (Main.MagentaVillagerRegion.isInRegion(var1.getBlock().getLocation())) {
               var1.setCancelled(true);
               var1.getPlayer().sendMessage(Main.PREFIX + Messages.getMsg().getString("protected-region").replace('&', '\u00a7'));
            } else if (Main.GreenVillagerRegion.isInRegion(var1.getBlock().getLocation())) {
               var1.setCancelled(true);
               var1.getPlayer().sendMessage(Main.PREFIX + Messages.getMsg().getString("protected-region").replace('&', '\u00a7'));
            }

            Random var2;
            int var3;
            if (var1.getBlock().getType() == Material.GOLD_BLOCK) {
               if (Main.MiddleRegion.isInRegion(var1.getBlock().getLocation())) {
                  var1.setCancelled(true);
                  var1.getBlock().setType(Material.STONE);
               } else {
                  var1.getBlock().breakNaturally(new ItemStack(Material.STICK));
               }

               var2 = new Random();
               var3 = var2.nextInt(121) + 80;
               Main.money.replace(var1.getPlayer(), ((Integer)Main.money.get(var1.getPlayer())).intValue() + var3);
               var1.getPlayer().sendMessage(Main.PREFIX + Messages.getMsg().getString("gold-gained").replace("{amount}", String.valueOf(var3)).replace('&', '\u00a7'));
               var1.getPlayer().playSound(var1.getPlayer().getLocation(), Main.nmsH.catMeow(), 1.0F, 1.0F);
               var1.getPlayer().getWorld().playEffect(var1.getBlock().getLocation().add(0.2D, 0.2D, 0.2D), Effect.FLAME, 1);
               var1.getPlayer().getWorld().playEffect(var1.getBlock().getLocation().add(0.6D, 0.6D, 0.6D), Effect.FLAME, 1);
               var1.getPlayer().getWorld().playEffect(var1.getBlock().getLocation().add(0.9D, 0.9D, 0.9D), Effect.FLAME, 1);
            } else {
               RandomItem var8;
               ItemStack var9;
               if (var1.getBlock().getType() == Material.DIAMOND_ORE) {
                  if (Main.MiddleRegion.isInRegion(var1.getBlock().getLocation())) {
                     var1.setCancelled(true);
                     var1.getBlock().setType(Material.STONE, true);
                  } else {
                     var1.getBlock().breakNaturally(new ItemStack(Material.STICK));
                  }

                  var8 = new RandomItem();
                  var8.add(16.0D, new ItemStack(Material.DIAMOND_HELMET));
                  var8.add(16.0D, new ItemStack(Material.DIAMOND_CHESTPLATE));
                  var8.add(16.0D, new ItemStack(Material.DIAMOND_LEGGINGS));
                  var8.add(16.0D, new ItemStack(Material.DIAMOND_BOOTS));
                  var8.add(16.0D, new ItemStack(Material.DIAMOND_SWORD));
                  var8.add(16.0D, new ItemStack(Material.DIAMOND_PICKAXE));
                  var9 = (ItemStack)var8.next();
                  var1.getPlayer().getWorld().dropItemNaturally(var1.getBlock().getLocation(), var9);
                  var1.getPlayer().getWorld().playEffect(var1.getBlock().getLocation().add(0.2D, 0.2D, 0.2D), Effect.ENDER_SIGNAL, 3);
                  var1.getPlayer().getWorld().playEffect(var1.getBlock().getLocation().add(0.6D, 0.6D, 0.6D), Effect.ENDER_SIGNAL, 3);
                  var1.getPlayer().getWorld().playEffect(var1.getBlock().getLocation().add(0.9D, 0.9D, 0.9D), Effect.ENDER_SIGNAL, 3);
                  var1.getPlayer().getWorld().playSound(var1.getPlayer().getLocation(), Main.nmsH.eggPop(), 1.0F, 1.0F);
               } else {
                  ItemStack var5;
                  if (var1.getBlock().getType() == Material.GOLD_ORE) {
                     if (Main.MiddleRegion.isInRegion(var1.getBlock().getLocation())) {
                        var1.setCancelled(true);
                        var1.getBlock().setType(Material.STONE, true);
                     } else {
                        var1.getBlock().breakNaturally(new ItemStack(Material.STICK));
                     }

                     var8 = new RandomItem();
                     var8.add(8.0D, new ItemStack(Material.FLINT_AND_STEEL));
                     var8.add(8.0D, new ItemStack(Material.COOKED_CHICKEN));
                     var8.add(8.0D, new ItemStack(Material.DIAMOND_HELMET));
                     var8.add(8.0D, new ItemStack(Material.LEATHER_BOOTS));
                     var8.add(8.0D, new ItemStack(Material.TNT));
                     var8.add(8.0D, new ItemStack(Material.IRON_HOE));
                     var8.add(8.0D, new ItemStack(Material.TORCH, 2));
                     var8.add(8.0D, new ItemStack(Material.DIAMOND_SWORD));
                     var8.add(8.0D, new ItemStack(Material.IRON_AXE));
                     var8.add(8.0D, new ItemStack(Material.LEATHER_LEGGINGS));
                     var8.add(8.0D, new ItemStack(Material.STONE, 12));
                     var8.add(8.0D, new ItemStack(Material.IRON_PICKAXE));
                     var8.add(8.0D, new ItemStack(Material.WATER_BUCKET));
                     var8.add(8.0D, new ItemStack(Material.WOOD, 12));
                     var8.add(8.0D, new ItemStack(Material.YELLOW_FLOWER));
                     var8.add(8.0D, new ItemStack(Material.LEATHER_CHESTPLATE));
                     var8.add(8.0D, new ItemStack(Material.DIAMOND_PICKAXE));
                     var8.add(8.0D, new ItemStack(Material.GOLD_HELMET));
                     var9 = (ItemStack)var8.next();
                     ItemStack var4 = (ItemStack)var8.next();
                     var5 = (ItemStack)var8.next();
                     Creeper var6;
                     if (var9.getType() == Material.YELLOW_FLOWER) {
                        var6 = (Creeper)var1.getPlayer().getWorld().spawnEntity(var1.getBlock().getLocation(), EntityType.CREEPER);
                        var6.setPowered(true);
                        var1.getPlayer().getWorld().playEffect(var1.getBlock().getLocation().add(0.2D, 0.2D, 0.2D), Effect.VILLAGER_THUNDERCLOUD, 5);
                        var1.getPlayer().getWorld().playEffect(var1.getBlock().getLocation().add(0.2D, 1.0D, 0.2D), Effect.VILLAGER_THUNDERCLOUD, 5);
                        var1.getPlayer().getWorld().playEffect(var1.getBlock().getLocation().add(0.3D, 1.2D, 0.3D), Effect.VILLAGER_THUNDERCLOUD, 5);
                        var1.getPlayer().getWorld().playEffect(var1.getBlock().getLocation(), Effect.VILLAGER_THUNDERCLOUD, 5);
                        var1.getPlayer().sendMessage(Main.PREFIX + Messages.getMsg().getString("creeper-spawn").replace('&', '\u00a7'));
                     }

                     if (var4.getType() == Material.YELLOW_FLOWER) {
                        var6 = (Creeper)var1.getPlayer().getWorld().spawnEntity(var1.getBlock().getLocation(), EntityType.CREEPER);
                        var6.setPowered(true);
                        var1.getPlayer().getWorld().playEffect(var1.getBlock().getLocation().add(0.2D, 0.2D, 0.2D), Effect.VILLAGER_THUNDERCLOUD, 5);
                        var1.getPlayer().getWorld().playEffect(var1.getBlock().getLocation().add(0.2D, 1.0D, 0.2D), Effect.VILLAGER_THUNDERCLOUD, 5);
                        var1.getPlayer().getWorld().playEffect(var1.getBlock().getLocation().add(0.3D, 1.2D, 0.3D), Effect.VILLAGER_THUNDERCLOUD, 5);
                        var1.getPlayer().getWorld().playEffect(var1.getBlock().getLocation(), Effect.VILLAGER_THUNDERCLOUD, 5);
                        var1.getPlayer().sendMessage(Main.PREFIX + Messages.getMsg().getString("creeper-spawn").replace('&', '\u00a7'));
                     }

                     if (var5.getType() == Material.YELLOW_FLOWER) {
                        var6 = (Creeper)var1.getPlayer().getWorld().spawnEntity(var1.getBlock().getLocation(), EntityType.CREEPER);
                        var6.setPowered(true);
                        var1.getPlayer().getWorld().playEffect(var1.getBlock().getLocation().add(0.2D, 0.2D, 0.2D), Effect.VILLAGER_THUNDERCLOUD, 5);
                        var1.getPlayer().getWorld().playEffect(var1.getBlock().getLocation().add(0.2D, 1.0D, 0.2D), Effect.VILLAGER_THUNDERCLOUD, 5);
                        var1.getPlayer().getWorld().playEffect(var1.getBlock().getLocation().add(0.3D, 1.2D, 0.3D), Effect.VILLAGER_THUNDERCLOUD, 5);
                        var1.getPlayer().getWorld().playEffect(var1.getBlock().getLocation(), Effect.VILLAGER_THUNDERCLOUD, 5);
                        var1.getPlayer().sendMessage(Main.PREFIX + Messages.getMsg().getString("creeper-spawn").replace('&', '\u00a7'));
                     }

                     var1.getPlayer().getWorld().dropItemNaturally(var1.getBlock().getLocation(), var9);
                     var1.getPlayer().getWorld().dropItemNaturally(var1.getBlock().getLocation(), var4);
                     var1.getPlayer().getWorld().dropItemNaturally(var1.getBlock().getLocation(), var5);
                     var1.getPlayer().getWorld().playEffect(var1.getBlock().getLocation().add(0.20000000298023224D, 0.20000000298023224D, 0.20000000298023224D), Effect.FLAME, 5);
                     var1.getPlayer().getWorld().playEffect(var1.getBlock().getLocation().add(0.5D, 0.5D, 0.5D), Effect.FLAME, 5);
                     var1.getPlayer().getWorld().playSound(var1.getPlayer().getLocation(), Main.nmsH.lvlUp(), 1.0F, 1.0F);
                  } else if (var1.getBlock().getType() == Material.IRON_ORE) {
                     if (Main.MiddleRegion.isInRegion(var1.getBlock().getLocation())) {
                        var1.setCancelled(true);
                        var1.getBlock().setType(Material.STONE, true);
                     } else {
                        var1.getBlock().breakNaturally(new ItemStack(Material.STICK));
                     }

                     var2 = new Random();
                     var3 = var2.nextInt(3) + 1;
                     RandomItem var10 = new RandomItem();
                     var10.add(10.0D, new ItemStack(Material.COAL, 2));
                     var10.add(10.0D, new ItemStack(Material.STICK, 2));
                     var10.add(10.0D, new ItemStack(Material.FLINT));
                     var10.add(10.0D, new ItemStack(Material.DIAMOND));
                     var10.add(10.0D, new ItemStack(Material.IRON_INGOT));
                     var10.add(10.0D, new ItemStack(Material.IRON_INGOT, 2));
                     var10.add(10.0D, new ItemStack(Material.STRING, 2));
                     var10.add(10.0D, new ItemStack(Material.ARROW, 2));
                     var5 = (ItemStack)var10.next();
                     ItemStack var12 = (ItemStack)var10.next();
                     ItemStack var7 = (ItemStack)var10.next();
                     switch(var3) {
                     case 1:
                        var1.getPlayer().getWorld().dropItemNaturally(var1.getBlock().getLocation(), var5);
                        break;
                     case 2:
                        var1.getPlayer().getWorld().dropItemNaturally(var1.getBlock().getLocation(), var5);
                        var1.getPlayer().getWorld().dropItemNaturally(var1.getBlock().getLocation(), var12);
                        break;
                     case 3:
                        var1.getPlayer().getWorld().dropItemNaturally(var1.getBlock().getLocation(), var5);
                        var1.getPlayer().getWorld().dropItemNaturally(var1.getBlock().getLocation(), var12);
                        var1.getPlayer().getWorld().dropItemNaturally(var1.getBlock().getLocation(), var7);
                     }

                     var1.getPlayer().getWorld().playEffect(var1.getBlock().getLocation().add(0.2D, 0.5D, 0.2D), Effect.SLIME, 5);
                     var1.getPlayer().getWorld().playEffect(var1.getBlock().getLocation().add(0.2D, 0.4D, 0.2D), Effect.SLIME, 5);
                     var1.getPlayer().getWorld().playEffect(var1.getBlock().getLocation().add(0.5D, 0.6D, 0.5D), Effect.SLIME, 5);
                     var1.getPlayer().getWorld().playSound(var1.getPlayer().getLocation(), Main.nmsH.anvil(), 1.0F, 1.0F);
                  } else if (var1.getBlock().getType() != Material.REDSTONE_ORE && var1.getBlock().getType() != Material.GLOWING_REDSTONE_ORE) {
                     if (var1.getBlock().getType() == Material.LAPIS_ORE) {
                        if (Main.MiddleRegion.isInRegion(var1.getBlock().getLocation())) {
                           var1.setCancelled(true);
                           var1.getBlock().setType(Material.STONE, true);
                        } else {
                           var1.getBlock().breakNaturally(new ItemStack(Material.STICK));
                        }

                        var2 = new Random();
                        var3 = var2.nextInt(8) + 1;
                        int var11 = var2.nextInt(50) + 1;
                        switch(var3) {
                        case 1:
                           var1.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, var11 * 20, 1));
                           var1.getPlayer().sendMessage(Main.PREFIX + Messages.getMsg().getString("potion-added").replace("{type}", Messages.getMsg().getString("potions.absorption")).replace("{time}", String.valueOf(var11)).replace('&', '\u00a7'));
                           break;
                        case 2:
                           var1.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, var11 * 20, 1));
                           var1.getPlayer().sendMessage(Main.PREFIX + Messages.getMsg().getString("potion-added").replace("{type}", Messages.getMsg().getString("potions.fire-resistence")).replace("{time}", String.valueOf(var11)).replace('&', '\u00a7'));
                           break;
                        case 3:
                           var1.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, var11 * 20, 1));
                           var1.getPlayer().sendMessage(Main.PREFIX + Messages.getMsg().getString("potion-added").replace("{type}", Messages.getMsg().getString("potions.regeneration")).replace("{time}", String.valueOf(var11)).replace('&', '\u00a7'));
                           break;
                        case 4:
                           var1.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOW, var11 * 20, 1));
                           var1.getPlayer().sendMessage(Main.PREFIX + Messages.getMsg().getString("potion-added").replace("{type}", Messages.getMsg().getString("potions.slow")).replace("{time}", String.valueOf(var11)).replace('&', '\u00a7'));
                           break;
                        case 5:
                           var1.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, var11 * 20, 1));
                           var1.getPlayer().sendMessage(Main.PREFIX + Messages.getMsg().getString("potion-added").replace("{type}", Messages.getMsg().getString("potions.fast-digging")).replace("{time}", String.valueOf(var11)).replace('&', '\u00a7'));
                           break;
                        case 6:
                           var1.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, var11 * 20, 1));
                           var1.getPlayer().sendMessage(Main.PREFIX + Messages.getMsg().getString("potion-added").replace("{type}", Messages.getMsg().getString("potions.blindness")).replace("{time}", String.valueOf(var11)).replace('&', '\u00a7'));
                           break;
                        case 7:
                           var1.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, var11 * 20, 1));
                           var1.getPlayer().sendMessage(Main.PREFIX + Messages.getMsg().getString("potion-added").replace("{type}", Messages.getMsg().getString("potions.confusion")).replace("{time}", String.valueOf(var11)).replace('&', '\u00a7'));
                           break;
                        case 8:
                           var1.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, var11 * 20, 1));
                           var1.getPlayer().sendMessage(Main.PREFIX + Messages.getMsg().getString("potion-added").replace("{type}", Messages.getMsg().getString("potions.damage-resistance")).replace("{time}", String.valueOf(var11)).replace('&', '\u00a7'));
                        }

                        var1.getPlayer().getWorld().playEffect(var1.getBlock().getLocation().add(0.2D, 0.2D, 0.2D), Effect.SPLASH, 5);
                        var1.getPlayer().getWorld().playEffect(var1.getBlock().getLocation().add(0.5D, 0.5D, 0.5D), Effect.SPLASH, 5);
                        var1.getPlayer().getWorld().playEffect(var1.getBlock().getLocation().add(0.9D, 0.9D, 0.9D), Effect.SPLASH, 5);
                        var1.getPlayer().getWorld().playSound(var1.getPlayer().getLocation(), Main.nmsH.splash(), 1.0F, 1.0F);
                     } else if (var1.getBlock().getType() == Material.EMERALD_ORE) {
                        if (Main.MiddleRegion.isInRegion(var1.getBlock().getLocation())) {
                           var1.setCancelled(true);
                           var1.getBlock().setType(Material.STONE);
                           var1.getBlock().getWorld().dropItemNaturally(var1.getBlock().getLocation(), new ItemStack(Material.INK_SACK, 2, (short)4));
                           var1.getPlayer().setLevel(var1.getPlayer().getLevel() + 1);
                           var1.getBlock().getWorld().playEffect(var1.getBlock().getLocation().add(0.20000000298023224D, 0.20000000298023224D, 0.20000000298023224D), Effect.HAPPY_VILLAGER, 5);
                           var1.getBlock().getWorld().playEffect(var1.getBlock().getLocation().add(0.5D, 0.5D, 0.5D), Effect.HAPPY_VILLAGER, 5);
                           var1.getBlock().getWorld().playEffect(var1.getBlock().getLocation().add(0.8999999761581421D, 0.8999999761581421D, 0.8999999761581421D), Effect.HAPPY_VILLAGER, 5);
                           var1.getPlayer().getWorld().playSound(var1.getPlayer().getLocation(), Main.nmsH.lvlUp(), 1.0F, 1.0F);
                        } else {
                           var1.getBlock().breakNaturally(new ItemStack(Material.STICK));
                           var1.getBlock().getWorld().dropItemNaturally(var1.getBlock().getLocation(), new ItemStack(Material.INK_SACK, 2, (short)4));
                           var1.getPlayer().setLevel(var1.getPlayer().getLevel() + 1);
                           var1.getBlock().getWorld().playEffect(var1.getBlock().getLocation().add(0.20000000298023224D, 0.20000000298023224D, 0.20000000298023224D), Effect.HAPPY_VILLAGER, 5);
                           var1.getBlock().getWorld().playEffect(var1.getBlock().getLocation().add(0.5D, 0.5D, 0.5D), Effect.HAPPY_VILLAGER, 5);
                           var1.getBlock().getWorld().playEffect(var1.getBlock().getLocation().add(0.8999999761581421D, 0.8999999761581421D, 0.8999999761581421D), Effect.HAPPY_VILLAGER, 5);
                           var1.getPlayer().getWorld().playSound(var1.getPlayer().getLocation(), Main.nmsH.lvlUp(), 1.0F, 1.0F);
                        }
                     } else if (var1.getBlock().getType() == Material.SKULL && Main.respBeac.containsValue(var1.getBlock().getLocation())) {
                        ((Player)Main.respBeac.get(var1.getBlock().getLocation())).sendMessage(Main.PREFIX + Messages.getMsg().getString("beacon-removed").replace('&', '\u00a7'));
                        Main.respBeac.remove(var1.getBlock().getLocation());
                        Main.respOwn.remove(var1.getPlayer());
                        var1.setCancelled(true);
                        var1.getBlock().setType(Material.AIR);
                     }
                  } else {
                     if (Main.MiddleRegion.isInRegion(var1.getBlock().getLocation())) {
                        var1.setCancelled(true);
                        var1.getBlock().setType(Material.STONE, true);
                     } else {
                        var1.getBlock().breakNaturally(new ItemStack(Material.STICK));
                     }

                     var2 = new Random();
                     var3 = var2.nextInt(9) + 16;
                     var1.getPlayer().setHealthScale((double)var3);
                     var1.getPlayer().getWorld().playSound(var1.getPlayer().getLocation(), Main.nmsH.villager(), 1.0F, 1.0F);
                     var1.getPlayer().getWorld().playEffect(var1.getBlock().getLocation().add(0.2D, 0.2D, 0.2D), Effect.HEART, 5);
                     var1.getPlayer().getWorld().playEffect(var1.getBlock().getLocation().add(0.5D, 0.5D, 0.5D), Effect.HEART, 5);
                     var1.getPlayer().getWorld().playEffect(var1.getBlock().getLocation().add(0.8D, 0.8D, 0.8D), Effect.HEART, 5);
                  }
               }
            }

         }
      }
   }
}
