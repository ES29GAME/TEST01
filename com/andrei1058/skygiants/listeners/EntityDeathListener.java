package com.andrei1058.skygiants.listeners;

import com.andrei1058.skygiants.Main;
import com.andrei1058.skygiants.configuration.Messages;
import com.andrei1058.skygiants.game.Spectate;
import com.andrei1058.skygiants.game.Winner;
import com.andrei1058.skygiants.utils.Titles;
import java.util.Iterator;
import java.util.Random;
import org.bukkit.Bukkit;
import org.bukkit.entity.Giant;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class EntityDeathListener implements Listener {
   @EventHandler
   public void death(EntityDeathEvent var1) {
      if (var1.getEntity() instanceof Giant) {
         Iterator var2;
         Player var3;
         if (Main.GoldGiantRegion.isInRegion(var1.getEntity().getLocation())) {
            if (Main.aliveTeams.contains("GOLD")) {
               Main.aliveTeams.remove("GOLD");
            }

            var2 = Bukkit.getOnlinePlayers().iterator();

            while(var2.hasNext()) {
               var3 = (Player)var2.next();
               Titles.sendTitle(var3, Integer.valueOf(0), Integer.valueOf(20), Integer.valueOf(0), "\u00a76\u2716", Messages.getMsg().getString("team-eliminated").replace("{team}", Messages.getMsg().getString("gold-team")).replace('&', '\u00a7'));
               if (Main.goldPlayers.contains(var3)) {
                  Spectate.setSpectator(var3);
                  Main.respawning.remove(var3);
               }
            }
         } else if (Main.MagentaGiantRegion.isInRegion(var1.getEntity().getLocation())) {
            if (Main.aliveTeams.contains("MAGENTA")) {
               Main.aliveTeams.remove("MAGENTA");
            }

            var2 = Bukkit.getOnlinePlayers().iterator();

            while(var2.hasNext()) {
               var3 = (Player)var2.next();
               Titles.sendTitle(var3, Integer.valueOf(0), Integer.valueOf(20), Integer.valueOf(0), "\u00a7d\u2716", Messages.getMsg().getString("team-eliminated").replace("{team}", Messages.getMsg().getString("magenta-team")).replace('&', '\u00a7'));
               if (Main.magentaPlayers.contains(var3)) {
                  Spectate.setSpectator(var3);
                  Main.respawning.remove(var3);
               }
            }
         } else if (Main.GreenGiantRegion.isInRegion(var1.getEntity().getLocation())) {
            if (Main.aliveTeams.contains("GREEN")) {
               Main.aliveTeams.remove("GREEN");
            }

            var2 = Bukkit.getOnlinePlayers().iterator();

            while(var2.hasNext()) {
               var3 = (Player)var2.next();
               Titles.sendTitle(var3, Integer.valueOf(0), Integer.valueOf(20), Integer.valueOf(0), "\u00a7a\u2716", Messages.getMsg().getString("team-eliminated").replace("{team}", Messages.getMsg().getString("green-team")).replace('&', '\u00a7'));
               if (Main.greenPlayers.contains(var3)) {
                  Spectate.setSpectator(var3);
                  Main.respawning.remove(var3);
               }
            }
         } else if (Main.BlueGiantRegion.isInRegion(var1.getEntity().getLocation())) {
            if (Main.aliveTeams.contains("BLUE")) {
               Main.aliveTeams.remove("BLUE");
            }

            var2 = Bukkit.getOnlinePlayers().iterator();

            while(var2.hasNext()) {
               var3 = (Player)var2.next();
               Titles.sendTitle(var3, Integer.valueOf(0), Integer.valueOf(20), Integer.valueOf(0), "\u00a79\u2716", Messages.getMsg().getString("team-eliminated").replace("{team}", Messages.getMsg().getString("blue-team")).replace('&', '\u00a7'));
               if (Main.bluePlayers.contains(var3)) {
                  Spectate.setSpectator(var3);
                  Main.respawning.remove(var3);
               }
            }
         }

         Winner.getWinner();
         var1.getDrops().clear();
         if (Main.goldPlayers.contains(var1.getEntity().getKiller())) {
            var2 = Main.goldPlayers.iterator();

            while(var2.hasNext()) {
               var3 = (Player)var2.next();
               var3.sendMessage(Main.PREFIX + Messages.getMsg().getString("giant-slain").replace('&', '\u00a7'));
               Main.money.replace(var3, ((Integer)Main.money.get(var3)).intValue() + 1000);
            }
         } else if (Main.magentaPlayers.contains(var1.getEntity().getKiller())) {
            var2 = Main.magentaPlayers.iterator();

            while(var2.hasNext()) {
               var3 = (Player)var2.next();
               var3.sendMessage(Main.PREFIX + Messages.getMsg().getString("giant-slain").replace('&', '\u00a7'));
               Main.money.replace(var3, ((Integer)Main.money.get(var3)).intValue() + 1000);
            }
         } else if (Main.greenPlayers.contains(var1.getEntity().getKiller())) {
            var2 = Main.greenPlayers.iterator();

            while(var2.hasNext()) {
               var3 = (Player)var2.next();
               var3.sendMessage(Main.PREFIX + Messages.getMsg().getString("giant-slain").replace('&', '\u00a7'));
               Main.money.replace(var3, ((Integer)Main.money.get(var3)).intValue() + 1000);
            }
         } else if (Main.bluePlayers.contains(var1.getEntity().getKiller())) {
            var2 = Main.bluePlayers.iterator();

            while(var2.hasNext()) {
               var3 = (Player)var2.next();
               var3.sendMessage(Main.PREFIX + Messages.getMsg().getString("giant-slain").replace('&', '\u00a7'));
               Main.money.replace(var3, ((Integer)Main.money.get(var3)).intValue() + 1000);
            }
         }

         if (!Main.beastsSlain.containsKey(var1.getEntity().getKiller())) {
            ;
         }
      }

      if (var1.getEntity().getCustomName() != null && (var1.getEntity().getCustomName().equalsIgnoreCase(Messages.getMsg().getString("hydros").replace('&', '\u00a7')) || var1.getEntity().getCustomName().equalsIgnoreCase(Messages.getMsg().getString("centaur").replace('&', '\u00a7')))) {
         var1.getDrops().clear();
         Main.beastSpawned = false;
         Main.BeastCountDown = 180000L;
         Random var6 = new Random();
         int var7 = var6.nextInt(3) + 1;
         if (Main.beastsSlain.containsKey(var1.getEntity().getKiller())) {
            Main.beastsSlain.replace(var1.getEntity().getKiller(), Main.beastsSlain.get(var1.getEntity().getKiller()));
         } else {
            Main.beastsSlain.put(var1.getEntity().getKiller(), Integer.valueOf(1));
         }

         Iterator var4;
         Player var5;
         if (Main.goldPlayers.contains(var1.getEntity().getKiller())) {
            var4 = Main.goldPlayers.iterator();

            while(var4.hasNext()) {
               var5 = (Player)var4.next();
               var5.sendMessage(Main.PREFIX + Messages.getMsg().getString("beast-slain").replace('&', '\u00a7'));
               Main.money.replace(var5, ((Integer)Main.money.get(var5)).intValue() + 1000);
               switch(var7) {
               case 1:
                  var5.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 900, 2));
                  var5.sendMessage(Main.PREFIX + Messages.getMsg().getString("team-potion.main").replace("{potion}", Messages.getMsg().getString("team-potion.speed")).replace('&', '\u00a7'));
                  break;
               case 2:
                  var5.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 900, 2));
                  var5.sendMessage(Main.PREFIX + Messages.getMsg().getString("team-potion.main").replace("{potion}", Messages.getMsg().getString("team-potion.damage")).replace('&', '\u00a7'));
                  break;
               case 3:
                  var5.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 900, 2));
                  var5.sendMessage(Main.PREFIX + Messages.getMsg().getString("team-potion.main").replace("{potion}", Messages.getMsg().getString("team-potion.jump")).replace('&', '\u00a7'));
                  break;
               case 4:
                  var5.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 900, 2));
                  var5.sendMessage(Main.PREFIX + Messages.getMsg().getString("team-potion.main").replace("{potion}", Messages.getMsg().getString("team-potion.jump")).replace('&', '\u00a7'));
               }
            }
         } else if (Main.magentaPlayers.contains(var1.getEntity().getKiller())) {
            var4 = Main.magentaPlayers.iterator();

            while(var4.hasNext()) {
               var5 = (Player)var4.next();
               var5.sendMessage(Main.PREFIX + Messages.getMsg().getString("beast-slain").replace('&', '\u00a7'));
               Main.money.replace(var5, ((Integer)Main.money.get(var5)).intValue() + 1000);
               switch(var7) {
               case 1:
                  var5.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 900, 2));
                  var5.sendMessage(Main.PREFIX + Messages.getMsg().getString("team-potion.main").replace("{potion}", Messages.getMsg().getString("team-potion.speed")).replace('&', '\u00a7'));
                  break;
               case 2:
                  var5.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 900, 2));
                  var5.sendMessage(Main.PREFIX + Messages.getMsg().getString("team-potion.main").replace("{potion}", Messages.getMsg().getString("team-potion.damage")).replace('&', '\u00a7'));
                  break;
               case 3:
                  var5.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 900, 2));
                  var5.sendMessage(Main.PREFIX + Messages.getMsg().getString("team-potion.main").replace("{potion}", Messages.getMsg().getString("team-potion.jump")).replace('&', '\u00a7'));
                  break;
               case 4:
                  var5.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 900, 2));
                  var5.sendMessage(Main.PREFIX + Messages.getMsg().getString("team-potion.main").replace("{potion}", Messages.getMsg().getString("team-potion.jump")).replace('&', '\u00a7'));
               }
            }
         } else if (Main.bluePlayers.contains(var1.getEntity().getKiller())) {
            var4 = Main.bluePlayers.iterator();

            while(var4.hasNext()) {
               var5 = (Player)var4.next();
               var5.sendMessage(Main.PREFIX + Messages.getMsg().getString("beast-slain").replace('&', '\u00a7'));
               Main.money.replace(var5, ((Integer)Main.money.get(var5)).intValue() + 1000);
               switch(var7) {
               case 1:
                  var5.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 900, 2));
                  var5.sendMessage(Main.PREFIX + Messages.getMsg().getString("team-potion.main").replace("{potion}", Messages.getMsg().getString("team-potion.speed")).replace('&', '\u00a7'));
                  break;
               case 2:
                  var5.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 900, 2));
                  var5.sendMessage(Main.PREFIX + Messages.getMsg().getString("team-potion.main").replace("{potion}", Messages.getMsg().getString("team-potion.damage")).replace('&', '\u00a7'));
                  break;
               case 3:
                  var5.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 900, 2));
                  var5.sendMessage(Main.PREFIX + Messages.getMsg().getString("team-potion.main").replace("{potion}", Messages.getMsg().getString("team-potion.jump")).replace('&', '\u00a7'));
                  break;
               case 4:
                  var5.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 900, 2));
                  var5.sendMessage(Main.PREFIX + Messages.getMsg().getString("team-potion.main").replace("{potion}", Messages.getMsg().getString("team-potion.jump")).replace('&', '\u00a7'));
               }
            }
         } else if (Main.greenPlayers.contains(var1.getEntity().getKiller())) {
            var4 = Main.greenPlayers.iterator();

            while(var4.hasNext()) {
               var5 = (Player)var4.next();
               var5.sendMessage(Main.PREFIX + Messages.getMsg().getString("beast-slain").replace('&', '\u00a7'));
               Main.money.replace(var5, ((Integer)Main.money.get(var5)).intValue() + 1000);
               switch(var7) {
               case 1:
                  var5.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 900, 2));
                  var5.sendMessage(Main.PREFIX + Messages.getMsg().getString("team-potion.main").replace("{potion}", Messages.getMsg().getString("team-potion.speed")).replace('&', '\u00a7'));
                  break;
               case 2:
                  var5.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 900, 2));
                  var5.sendMessage(Main.PREFIX + Messages.getMsg().getString("team-potion.main").replace("{potion}", Messages.getMsg().getString("team-potion.damage")).replace('&', '\u00a7'));
                  break;
               case 3:
                  var5.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 900, 2));
                  var5.sendMessage(Main.PREFIX + Messages.getMsg().getString("team-potion.main").replace("{potion}", Messages.getMsg().getString("team-potion.jump")).replace('&', '\u00a7'));
                  break;
               case 4:
                  var5.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 900, 2));
                  var5.sendMessage(Main.PREFIX + Messages.getMsg().getString("team-potion.main").replace("{potion}", Messages.getMsg().getString("team-potion.jump")).replace('&', '\u00a7'));
               }
            }
         }
      }

   }
}
