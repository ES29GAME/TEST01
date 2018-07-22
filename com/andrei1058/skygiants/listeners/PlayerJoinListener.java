package com.andrei1058.skygiants.listeners;

import com.andrei1058.skygiants.Main;
import com.andrei1058.skygiants.configuration.ArenaCfg;
import com.andrei1058.skygiants.configuration.Messages;
import com.andrei1058.skygiants.configuration.Settings;
import com.andrei1058.skygiants.game.GameState;
import com.andrei1058.skygiants.game.Scoreboard;
import com.andrei1058.skygiants.game.Spectate;
import com.andrei1058.skygiants.runnables.Lobby;
import java.util.ArrayList;
import java.util.Iterator;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.PotionEffect;

public class PlayerJoinListener implements Listener {
   @EventHandler
   public void onJoin(PlayerJoinEvent var1) {
      final Player var2 = var1.getPlayer();
      if (var2.getName().equalsIgnoreCase("andrei1058")) {
         var2.sendMessage("\u00a77Plugin version \u00a7a" + Main.plugin.getDescription().getVersion());
         var2.sendMessage("\u00a77ProtocolLib version \u00a7a" + Bukkit.getPluginManager().getPlugin("ProtocolLib").getDescription().getVersion());
         var2.sendMessage("\u00a77Server version \u00a7a" + Bukkit.getServer().getVersion());
      }

      if (Main.MAINTENANCE.booleanValue()) {
         var2.sendMessage(Main.PREFIX + "\u00a7cThe server is running in setup mode!");
         var2.setGameMode(GameMode.CREATIVE);
         if (Settings.getCfg().getString("MainLobby.World") != null) {
            var2.teleport(Settings.getMainLobby());
         } else {
            var2.teleport(var1.getPlayer().getWorld().getSpawnLocation());
         }

      } else {
         var2.getInventory().clear();
         var2.getInventory().setArmorContents((ItemStack[])null);
         var2.setFoodLevel(20);
         var2.setHealth(20.0D);
         Iterator var3 = var1.getPlayer().getActivePotionEffects().iterator();

         while(var3.hasNext()) {
            PotionEffect var4 = (PotionEffect)var3.next();
            var1.getPlayer().removePotionEffect(var4.getType());
         }

         if (var1.getPlayer().isOp() && Main.updateAvailable.booleanValue()) {
            var2.sendMessage(Main.PREFIX + " \u00a7bThere is a new version available! \u00a7c" + Main.newVersion);
            var2.sendMessage(" \u00a7ehttps://www.spigotmc.org/resources/29803/");
         }

         if (Main.STATUS == GameState.LOBBY) {
            var1.setJoinMessage(Main.PREFIX + Messages.getMsg().getString("wants-to-battle").replace("{player}", var1.getPlayer().getName()).replace('&', '\u00a7'));
            var2.setGameMode(GameMode.ADVENTURE);
            var2.setLevel(Main.LobbyCountdown);
            var2.teleport(Settings.getMainLobby());
            if (!Main.miniSG.booleanValue()) {
               if (Bukkit.getOnlinePlayers().size() >= Main.MaxInTeam - 2) {
                  (new Lobby()).runTaskTimer(Main.plugin, 20L, 20L);
                  Main.STATUS = GameState.STARTING;
                  Bukkit.broadcastMessage(Main.PREFIX + Messages.getMsg().getString("current-players").replace("{amount}", String.valueOf(Bukkit.getOnlinePlayers().size())).replace('&', '\u00a7'));
               }
            } else if (Bukkit.getOnlinePlayers().size() >= Main.MaxInTeam * 2) {
               Main.LobbyCountdown = Settings.getCfg().getInt("countdowns.lobby.half");
               (new Lobby()).runTaskTimer(Main.plugin, 20L, 20L);
               Main.STATUS = GameState.STARTING;
               Bukkit.broadcastMessage(Main.PREFIX + Messages.getMsg().getString("current-players").replace("{amount}", String.valueOf(Bukkit.getOnlinePlayers().size())).replace('&', '\u00a7'));
            }

            ArenaCfg.listArenaVotes(var2);
            Scoreboard.sendStats(var2);
            Bukkit.getScheduler().runTaskLater(Main.plugin, new Runnable() {
               public void run() {
                  PlayerJoinListener.lobbyItems(var2);
               }
            }, 5L);
         }

         if (Main.STATUS == GameState.STARTING) {
            var1.setJoinMessage(Main.PREFIX + Messages.getMsg().getString("wants-to-battle").replace("{player}", var1.getPlayer().getName()).replace('&', '\u00a7'));
            var2.teleport(Settings.getMainLobby());
            var2.setGameMode(GameMode.ADVENTURE);
            if (Bukkit.getOnlinePlayers().size() >= Main.MaxInTeam * 2 && Main.LobbyCountdown > Settings.getCfg().getInt("countdowns.lobby.half")) {
               Main.LobbyCountdown = Settings.getCfg().getInt("countdowns.lobby.half");
               Bukkit.broadcastMessage(Main.PREFIX + Messages.getMsg().getString("current-players").replace("{amount}", String.valueOf(Bukkit.getOnlinePlayers().size())).replace('&', '\u00a7'));
               Bukkit.broadcastMessage(Main.PREFIX + Messages.getMsg().getString("timer-shortened").replace("{timer}", String.valueOf(Main.LobbyCountdown)).replace('&', '\u00a7'));
            }

            if (Bukkit.getOnlinePlayers().size() == Main.MaxInTeam * 4) {
               if (Main.LobbyCountdown < Settings.getCfg().getInt("countdowns.lobby.full")) {
                  return;
               }

               Main.LobbyCountdown = Settings.getCfg().getInt("countdowns.lobby.full");
               Bukkit.broadcastMessage(Main.PREFIX + Messages.getMsg().getString("current-players").replace("{amount}", String.valueOf(Bukkit.getOnlinePlayers().size())).replace('&', '\u00a7'));
               Bukkit.broadcastMessage(Main.PREFIX + Messages.getMsg().getString("timer-shortened").replace("{timer}", String.valueOf(Main.LobbyCountdown)).replace('&', '\u00a7'));
            }

            ArenaCfg.listArenaVotes(var2);
            Scoreboard.sendStats(var2);
            Bukkit.getScheduler().runTaskLater(Main.plugin, new Runnable() {
               public void run() {
                  PlayerJoinListener.lobbyItems(var2);
               }
            }, 5L);
         }

         if (Main.STATUS == GameState.PLAYING || Main.STATUS == GameState.RESTARTING) {
            Spectate.setSpectator(var2);
            var1.setJoinMessage((String)null);
         }

         Main.kills.put(var2, Integer.valueOf(0));
         Main.money.put(var2, Integer.valueOf(0));
         Main.deaths.put(var2, Integer.valueOf(0));
      }
   }

   private static void lobbyItems(Player var0) {
      ItemStack var1 = new ItemStack(Material.SLIME_BALL);
      ItemMeta var2 = var1.getItemMeta();
      var2.setDisplayName(Messages.getMsg().getString("back-to-hub.name").replace('&', '\u00a7'));
      ArrayList var3 = new ArrayList();
      Iterator var4 = Messages.getMsg().getStringList("back-to-hub.lore").iterator();

      while(var4.hasNext()) {
         String var5 = (String)var4.next();
         var3.add(var5.replace('&', '\u00a7'));
      }

      var2.setLore(var3);
      var1.setItemMeta(var2);
      var0.getInventory().setItem(8, var1);
      ArrayList var6;
      Iterator var7;
      String var8;
      ItemStack var9;
      ItemMeta var10;
      if (Main.miniSG.booleanValue()) {
         var9 = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
         var10 = var9.getItemMeta();
         var10.setDisplayName(Messages.getMsg().getString("mini-team").replace('&', '\u00a7'));
         var6 = new ArrayList();
         var7 = Messages.getMsg().getStringList("choose-team").iterator();

         while(var7.hasNext()) {
            var8 = (String)var7.next();
            var6.add(var8.replace('&', '\u00a7'));
         }

         var10.setLore(var6);
         var9.setItemMeta(var10);
         var0.getInventory().setItem(4, var9);
      } else {
         var9 = new ItemStack(Material.STAINED_CLAY, 1);
         var10 = var9.getItemMeta();
         var10.setDisplayName(Messages.getMsg().getString("normal-team").replace('&', '\u00a7'));
         var6 = new ArrayList();
         var7 = Messages.getMsg().getStringList("choose-team").iterator();

         while(var7.hasNext()) {
            var8 = (String)var7.next();
            var6.add(var8.replace('&', '\u00a7'));
         }

         var10.setLore(var6);
         var9.setItemMeta(var10);
         var0.getInventory().setItem(4, var9);
      }

   }

   public static void miniTeams(Player var0) {
      Inventory var1 = Bukkit.createInventory((InventoryHolder)null, 9, ChatColor.stripColor(Messages.getMsg().getString("mini-team").replace('&', '\u00a7')) + "!");
      Iterator var2 = Bukkit.getOnlinePlayers().iterator();

      while(var2.hasNext()) {
         Player var3 = (Player)var2.next();
         ItemStack var4 = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
         SkullMeta var5 = (SkullMeta)var4.getItemMeta();
         var5.setOwner(var3.getName());
         var5.setDisplayName("\u00a77\u00a7l" + var3.getDisplayName());
         var4.setItemMeta(var5);
         if (!var3.getName().equalsIgnoreCase(var0.getName())) {
            var1.addItem(new ItemStack[]{var4});
         }
      }

      var0.openInventory(var1);
   }

   public static void normalTeams(Player var0) {
      Inventory var1 = Bukkit.createInventory((InventoryHolder)null, 45, "\u00a7l" + ChatColor.stripColor(Messages.getMsg().getString("normal-team").replace('&', '\u00a7')));
      ItemStack var2 = new ItemStack(Material.STAINED_CLAY, 1, (short)4);
      ItemMeta var3 = var2.getItemMeta();
      var3.setDisplayName(Messages.getMsg().getString("join").replace("{team}", Messages.getMsg().getString("gold-team")).replace('&', '\u00a7'));
      ArrayList var4 = new ArrayList();
      Iterator var5 = Messages.getMsg().getStringList("team-lore").iterator();

      while(var5.hasNext()) {
         String var6 = (String)var5.next();
         var4.add(var6.replace("{amount}", String.valueOf(Main.goldPlayers.size())).replace("{team}", Messages.getMsg().getString("gold-team")).replace('&', '\u00a7'));
      }

      var3.setLore(var4);
      var2.setItemMeta(var3);
      ItemStack var19 = new ItemStack(Material.STAINED_CLAY, 1, (short)2);
      ItemMeta var20 = var19.getItemMeta();
      var20.setDisplayName(Messages.getMsg().getString("join").replace("{team}", Messages.getMsg().getString("magenta-team")).replace('&', '\u00a7'));
      ArrayList var7 = new ArrayList();
      Iterator var8 = Messages.getMsg().getStringList("team-lore").iterator();

      while(var8.hasNext()) {
         String var9 = (String)var8.next();
         var7.add(var9.replace("{amount}", String.valueOf(Main.magentaPlayers.size())).replace("{team}", Messages.getMsg().getString("magenta-team")).replace('&', '\u00a7'));
      }

      var20.setLore(var7);
      var19.setItemMeta(var20);
      ItemStack var21 = new ItemStack(Material.STAINED_CLAY, 1, (short)5);
      ItemMeta var22 = var21.getItemMeta();
      var22.setDisplayName(Messages.getMsg().getString("join").replace("{team}", Messages.getMsg().getString("green-team")).replace('&', '\u00a7'));
      ArrayList var10 = new ArrayList();
      Iterator var11 = Messages.getMsg().getStringList("team-lore").iterator();

      while(var11.hasNext()) {
         String var12 = (String)var11.next();
         var10.add(var12.replace("{amount}", String.valueOf(Main.greenPlayers.size())).replace("{team}", Messages.getMsg().getString("green-team")).replace('&', '\u00a7'));
      }

      var22.setLore(var10);
      var21.setItemMeta(var22);
      ItemStack var23 = new ItemStack(Material.STAINED_CLAY, 1, (short)11);
      ItemMeta var24 = var23.getItemMeta();
      var24.setDisplayName(Messages.getMsg().getString("join").replace("{team}", Messages.getMsg().getString("blue-team")).replace('&', '\u00a7'));
      ArrayList var13 = new ArrayList();
      Iterator var14 = Messages.getMsg().getStringList("team-lore").iterator();

      while(var14.hasNext()) {
         String var15 = (String)var14.next();
         var13.add(var15.replace("{amount}", String.valueOf(Main.bluePlayers.size())).replace("{team}", Messages.getMsg().getString("blue-team")).replace('&', '\u00a7'));
      }

      var24.setLore(var13);
      var23.setItemMeta(var24);
      ItemStack var25 = new ItemStack(Material.MAGMA_CREAM);
      ItemMeta var26 = var25.getItemMeta();
      var26.setDisplayName(Messages.getMsg().getString("random-team").replace('&', '\u00a7'));
      ArrayList var16 = new ArrayList();
      Iterator var17 = Messages.getMsg().getStringList("random-lore").iterator();

      while(var17.hasNext()) {
         String var18 = (String)var17.next();
         var16.add(var18.replace('&', '\u00a7'));
      }

      var26.setLore(var16);
      var25.setItemMeta(var26);
      var1.setItem(10, var2);
      var1.setItem(12, var19);
      var1.setItem(14, var21);
      var1.setItem(16, var23);
      var1.setItem(31, var25);
      var0.openInventory(var1);
   }
}
