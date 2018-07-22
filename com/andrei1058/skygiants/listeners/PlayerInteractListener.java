package com.andrei1058.skygiants.listeners;

import com.andrei1058.skygiants.Main;
import com.andrei1058.skygiants.configuration.Messages;
import com.andrei1058.skygiants.configuration.Settings;
import com.andrei1058.skygiants.game.GameState;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class PlayerInteractListener implements Listener {
   public static HashMap chest = new HashMap();

   @EventHandler
   public void interact(PlayerInteractEvent var1) {
      if (var1.getAction() == Action.RIGHT_CLICK_BLOCK && var1.getClickedBlock().getType() == Material.CHEST) {
         var1.setCancelled(true);
         if (Main.spectators.contains(var1.getPlayer())) {
            return;
         }

         if (Main.STATUS == GameState.PLAYING) {
            if (chest.containsKey(var1.getClickedBlock().getLocation())) {
               var1.getPlayer().openInventory((Inventory)chest.get(var1.getClickedBlock().getLocation()));
            } else {
               Random var2 = new Random();
               int var3 = var2.nextInt(6);
               if (var3 < 3) {
                  var3 = 3;
               }

               Inventory var4 = Bukkit.createInventory(var1.getPlayer(), InventoryType.CHEST);
               ArrayList var5 = new ArrayList();
               var5.add(new ItemStack(Material.GOLD_AXE));
               var5.add(new ItemStack(Material.STRING, 2));
               var5.add(new ItemStack(Material.STICK, 4));
               var5.add(new ItemStack(Material.STONE_SPADE));
               var5.add(new ItemStack(Material.IRON_HELMET));
               var5.add(new ItemStack(Material.IRON_SWORD));
               var5.add(new ItemStack(Material.WATER_BUCKET));
               var5.add(new ItemStack(Material.GOLD_BOOTS));
               var5.add(new ItemStack(Material.GOLDEN_APPLE));
               var5.add(new ItemStack(Material.STONE_SWORD));
               var5.add(new ItemStack(Material.CHAINMAIL_HELMET));
               var5.add(new ItemStack(Material.COOKED_BEEF));
               var5.add(new ItemStack(Material.CARROT, 2));
               var5.add(new ItemStack(Material.IRON_PICKAXE));
               var5.add(new ItemStack(Material.IRON_BOOTS));
               var5.add(new ItemStack(Material.GOLD_HELMET));
               var5.add(new ItemStack(Material.IRON_AXE));
               var5.add(new ItemStack(Material.FEATHER, 3));
               var5.add(new ItemStack(Material.RAW_FISH));
               var5.add(new ItemStack(Material.TORCH, 3));
               var5.add(new ItemStack(Material.CHAINMAIL_LEGGINGS));
               var5.add(new ItemStack(Material.COOKED_RABBIT));
               var5.add(new ItemStack(Material.DIAMOND_SPADE));
               var5.add(new ItemStack(Material.IRON_SPADE));
               var5.add(new ItemStack(Material.STONE_AXE));
               var5.add(new ItemStack(Material.GOLD_CHESTPLATE));
               var5.add(new ItemStack(Material.COOKED_BEEF));
               var5.add(new ItemStack(Material.LAVA_BUCKET));
               var5.add(new ItemStack(Material.IRON_CHESTPLATE));
               var5.add(new ItemStack(Material.IRON_LEGGINGS));
               var5.add(new ItemStack(Material.LEATHER_CHESTPLATE));
               var5.add(new ItemStack(Material.WOOD, 12));
               var5.add(new ItemStack(Material.DIAMOND_PICKAXE));
               var5.add(new ItemStack(Material.COOKED_CHICKEN));
               var5.add(new ItemStack(Material.WEB));

               while(var3 != 0) {
                  --var3;
                  Random var6 = new Random();
                  int var7 = var6.nextInt(27);
                  int var8 = var6.nextInt(var5.size());
                  var4.setItem(var7, (ItemStack)var5.get(var8));
               }

               chest.put(var1.getClickedBlock().getLocation(), var4);
               var1.getPlayer().openInventory((Inventory)chest.get(var1.getClickedBlock().getLocation()));
            }
         }
      }

      if (var1.getAction() == Action.RIGHT_CLICK_BLOCK && var1.getClickedBlock().getType() == Material.WORKBENCH && Main.spectators.contains(var1.getPlayer())) {
         var1.setCancelled(true);
      }

      if (var1.getAction() == Action.RIGHT_CLICK_BLOCK && var1.getClickedBlock().getType() == Material.ENCHANTMENT_TABLE && Main.spectators.contains(var1.getPlayer())) {
         var1.setCancelled(true);
      }

      if (var1.getAction() == Action.RIGHT_CLICK_BLOCK && var1.getClickedBlock().getType() == Material.ENDER_CHEST && Main.spectators.contains(var1.getPlayer())) {
         var1.setCancelled(true);
      }

      if (var1.getAction() == Action.RIGHT_CLICK_BLOCK && var1.getClickedBlock().getType() == Material.ANVIL && Main.spectators.contains(var1.getPlayer())) {
         var1.setCancelled(true);
      }

      if (var1.getAction() == Action.LEFT_CLICK_BLOCK && var1.getClickedBlock().getType() == Material.SKULL && Main.respBeac.containsKey(var1.getClickedBlock().getLocation())) {
         var1.getPlayer().sendMessage(Main.PREFIX + Messages.getMsg().getString("beacon-owner").replace("{player}", ((Player)Main.respBeac.get(var1.getClickedBlock().getLocation())).getName()).replace('&', '\u00a7'));
      }

      if (var1.getAction() == Action.RIGHT_CLICK_AIR || var1.getAction() == Action.RIGHT_CLICK_BLOCK) {
         if (Main.nmsH.getItm(var1.getPlayer()).getType() == Material.SLIME_BALL && Main.nmsH.getItm(var1.getPlayer()).getItemMeta().getDisplayName().equalsIgnoreCase(Messages.getMsg().getString("back-to-hub.name").replace('&', '\u00a7'))) {
            ByteArrayDataOutput var9 = ByteStreams.newDataOutput();
            var9.writeUTF("Connect");
            var9.writeUTF(Settings.getCfg().getString("lobby-server"));
            var1.getPlayer().sendPluginMessage(Main.plugin, "BungeeCord", var9.toByteArray());
         }

         if (Main.nmsH.getItm(var1.getPlayer()).getType() == Material.COMPASS && Main.nmsH.getItm(var1.getPlayer()).getItemMeta().getDisplayName().equalsIgnoreCase(Messages.getMsg().getString("spectator-selector.name").replace('&', '\u00a7'))) {
            Inventory var10 = Bukkit.createInventory((InventoryHolder)null, 18, Messages.getMsg().getString("who-to-spectate").replace('&', '\u00a7'));
            Iterator var11 = Main.players.iterator();

            while(var11.hasNext()) {
               Player var12 = (Player)var11.next();
               ItemStack var13 = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
               SkullMeta var14 = (SkullMeta)var13.getItemMeta();
               var14.setOwner(var12.getName());
               var14.setDisplayName(var12.getName());
               var13.setItemMeta(var14);
               var10.addItem(new ItemStack[]{var13});
            }

            var1.getPlayer().openInventory(var10);
         }

         if (Main.nmsH.getItm(var1.getPlayer()).getType() == Material.SKULL_ITEM && Main.nmsH.getItm(var1.getPlayer()).getItemMeta().getDisplayName() != null && Main.nmsH.getItm(var1.getPlayer()).getItemMeta().getDisplayName().equalsIgnoreCase(Messages.getMsg().getString("mini-team").replace('&', '\u00a7'))) {
            if (Main.hasTeam.contains(var1.getPlayer())) {
               var1.getPlayer().sendMessage(Main.PREFIX + Messages.getMsg().getString("already-in-team").replace('&', '\u00a7'));
               return;
            }

            PlayerJoinListener.miniTeams(var1.getPlayer());
         }

         if (Main.nmsH.getItm(var1.getPlayer()).getType() == Material.STAINED_CLAY && (Main.STATUS == GameState.STARTING || Main.STATUS == GameState.LOBBY) && Main.nmsH.getItm(var1.getPlayer()).getItemMeta().getDisplayName().equalsIgnoreCase(Messages.getMsg().getString("normal-team").replace('&', '\u00a7'))) {
            PlayerJoinListener.normalTeams(var1.getPlayer());
         }
      }

   }
}
