package com.andrei1058.skygiants.nms.v1_8_R3;

import com.andrei1058.skygiants.NMS;
import com.andrei1058.skygiants.configuration.Messages;
import com.andrei1058.skygiants.locations.Beasts;
import com.andrei1058.skygiants.locations.Giants;
import com.andrei1058.skygiants.locations.Villagers;
import com.andrei1058.skygiants.nms.v1_8_R3.beasts.GiantNMS;
import com.andrei1058.skygiants.nms.v1_8_R3.beasts.GolemNMS;
import com.andrei1058.skygiants.nms.v1_8_R3.beasts.GuardianNMS;
import com.andrei1058.skygiants.nms.v1_8_R3.beasts.HorseNMS;
import java.util.Iterator;
import java.util.Random;
import net.minecraft.server.v1_8_R3.EntityGiantZombie;
import net.minecraft.server.v1_8_R3.EntityGuardian;
import net.minecraft.server.v1_8_R3.EntityHorse;
import net.minecraft.server.v1_8_R3.EntityIronGolem;
import net.minecraft.server.v1_8_R3.EntityVillager;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Giant;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionType;

public class Main implements NMS {
   public static GiantNMS GoldGiant;
   public static GiantNMS MagentaGiant;
   public static GiantNMS GreenGiant;
   public static GiantNMS BlueGiant;

   public void registerGiant() {
      RegisterEntity var1 = new RegisterEntity();
      var1.registerEntity("Giant", 53, EntityGiantZombie.class, GiantNMS.class);
   }

   public void spawnGiants() {
      if (!com.andrei1058.skygiants.Main.magentaPlayers.isEmpty()) {
         GiantNMS.spawnMagentaGiant(Giants.getGiant("Magenta"));
      }

      if (!com.andrei1058.skygiants.Main.goldPlayers.isEmpty()) {
         GiantNMS.spawnOrangeGiant(Giants.getGiant("Gold"));
      }

      if (!com.andrei1058.skygiants.Main.greenPlayers.isEmpty()) {
         GiantNMS.spawnGreenGiant(Giants.getGiant("Green"));
      }

      if (!com.andrei1058.skygiants.Main.bluePlayers.isEmpty()) {
         GiantNMS.spawnBlueGiant(Giants.getGiant("Blue"));
      }

   }

   public String gGH(String var1) {
      if (var1.equalsIgnoreCase("Gold")) {
         return !com.andrei1058.skygiants.Main.aliveTeams.contains("GOLD") ? Messages.getMsg().getString("scoreboard.dead").replace('&', '\u00a7') : getGiantHealth(GoldGiant);
      } else if (var1.equalsIgnoreCase("Magenta")) {
         return !com.andrei1058.skygiants.Main.aliveTeams.contains("MAGENTA") ? Messages.getMsg().getString("scoreboard.dead").replace('&', '\u00a7') : getGiantHealth(MagentaGiant);
      } else if (var1.equalsIgnoreCase("Green")) {
         return !com.andrei1058.skygiants.Main.aliveTeams.contains("GREEN") ? Messages.getMsg().getString("scoreboard.dead").replace('&', '\u00a7') : getGiantHealth(GreenGiant);
      } else if (var1.equalsIgnoreCase("Blue")) {
         return !com.andrei1058.skygiants.Main.aliveTeams.contains("BLUE") ? Messages.getMsg().getString("scoreboard.dead").replace('&', '\u00a7') : getGiantHealth(BlueGiant);
      } else {
         return Messages.getMsg().getString("scoreboard.dead").replace('&', '\u00a7');
      }
   }

   private static String getGiantHealth(GiantNMS var0) {
      double var1 = 0.0D;
      var1 = (double)var0.getHealth();
      double var3 = var1 * 100.0D / (double)com.andrei1058.skygiants.Main.GiantHealth;
      return String.valueOf((int)var3 + "%");
   }

   public int getGiantHealthInt(String var1) {
      if (var1.equalsIgnoreCase("Gold")) {
         return (int)GoldGiant.getHealth();
      } else if (var1.equalsIgnoreCase("Magenta")) {
         return (int)MagentaGiant.getHealth();
      } else if (var1.equalsIgnoreCase("Green")) {
         return (int)GreenGiant.getHealth();
      } else {
         return var1.equalsIgnoreCase("Blue") ? (int)BlueGiant.getHealth() : 0;
      }
   }

   public void killGiant(String var1) {
      Iterator var2;
      Entity var3;
      if (var1.equalsIgnoreCase("Gold")) {
         var2 = Bukkit.getWorld(com.andrei1058.skygiants.Main.choosenMap).getEntities().iterator();

         while(var2.hasNext()) {
            var3 = (Entity)var2.next();
            if (var3 instanceof Giant && com.andrei1058.skygiants.Main.GoldGiantRegion.isInRegion(var3.getLocation())) {
               ((Giant)var3).setHealth(0.0D);
            }
         }

         com.andrei1058.skygiants.Main.aliveTeams.remove("GOLD");
      } else if (var1.equalsIgnoreCase("Magenta")) {
         var2 = Bukkit.getWorld(com.andrei1058.skygiants.Main.choosenMap).getEntities().iterator();

         while(var2.hasNext()) {
            var3 = (Entity)var2.next();
            if (var3 instanceof Giant && com.andrei1058.skygiants.Main.MagentaGiantRegion.isInRegion(var3.getLocation())) {
               ((Giant)var3).setHealth(0.0D);
            }
         }

         com.andrei1058.skygiants.Main.aliveTeams.remove("MAGENTA");
      } else if (var1.equalsIgnoreCase("Green")) {
         var2 = Bukkit.getWorld(com.andrei1058.skygiants.Main.choosenMap).getEntities().iterator();

         while(var2.hasNext()) {
            var3 = (Entity)var2.next();
            if (var3 instanceof Giant && com.andrei1058.skygiants.Main.GreenGiantRegion.isInRegion(var3.getLocation())) {
               ((Giant)var3).setHealth(0.0D);
            }
         }

         com.andrei1058.skygiants.Main.aliveTeams.remove("GREEN");
      } else if (var1.equalsIgnoreCase("Blue")) {
         var2 = Bukkit.getWorld(com.andrei1058.skygiants.Main.choosenMap).getEntities().iterator();

         while(var2.hasNext()) {
            var3 = (Entity)var2.next();
            if (var3 instanceof Giant && com.andrei1058.skygiants.Main.BlueGiantRegion.isInRegion(var3.getLocation())) {
               ((Giant)var3).setHealth(0.0D);
            }
         }

         com.andrei1058.skygiants.Main.aliveTeams.remove("BLUE");
      }

   }

   public void actionMsg(Player var1, String var2) {
      CraftPlayer var3 = (CraftPlayer)var1;
      String var4 = ChatColor.translateAlternateColorCodes('&', var2);
      IChatBaseComponent var5 = ChatSerializer.a("{\"text\": \"" + var4 + "\"}");
      PacketPlayOutChat var6 = new PacketPlayOutChat(var5, (byte)2);
      var3.getHandle().playerConnection.sendPacket(var6);
   }

   public void spawnVillagers() {
      if (!com.andrei1058.skygiants.Main.magentaPlayers.isEmpty()) {
         VillagerNMS.spawnMagentaVillager(Villagers.getVillager("Magenta"));
      }

      if (!com.andrei1058.skygiants.Main.goldPlayers.isEmpty()) {
         VillagerNMS.spawnGoldVillager(Villagers.getVillager("Gold"));
      }

      if (!com.andrei1058.skygiants.Main.greenPlayers.isEmpty()) {
         VillagerNMS.spawnGreenVillager(Villagers.getVillager("Green"));
      }

      if (!com.andrei1058.skygiants.Main.bluePlayers.isEmpty()) {
         VillagerNMS.spawnBlueVillager(Villagers.getVillager("Blue"));
      }

   }

   public void registerVillager() {
      RegisterEntity var1 = new RegisterEntity();
      var1.registerEntity("Villager", 120, EntityVillager.class, VillagerNMS.class);
   }

   public void spawnBeast() {
      Random var1 = new Random();
      int var2 = var1.nextInt(2) + 1;
      switch(var2) {
      case 1:
         GuardianNMS.spawnGuardian(Beasts.getBeastSpawn());
         break;
      case 2:
         HorseNMS.spawnHorse(Beasts.getBeastSpawn());
      }

   }

   public void registerBeast() {
      RegisterEntity var1 = new RegisterEntity();
      var1.registerEntity("Guardian", 68, EntityGuardian.class, GuardianNMS.class);
      var1.registerEntity("IronGolem", 99, EntityIronGolem.class, GolemNMS.class);
      var1.registerEntity("Horse", 100, EntityHorse.class, HorseNMS.class);
   }

   public Sound catMeow() {
      return Sound.valueOf("CAT_MEOW");
   }

   public Sound eggPop() {
      return Sound.valueOf("CHICKEN_EGG_POP");
   }

   public Sound lvlUp() {
      return Sound.valueOf("LEVEL_UP");
   }

   public Sound anvil() {
      return Sound.valueOf("ANVIL_LAND");
   }

   public Sound villager() {
      return Sound.valueOf("VILLAGER_HAGGLE");
   }

   public Sound splash() {
      return Sound.valueOf("SPLASH2");
   }

   public Sound giantHurt() {
      return Sound.valueOf("ZOMBIE_PIG_HURT");
   }

   public ItemStack getItm(Player var1) {
      return var1.getItemInHand();
   }

   public ItemStack potion(PotionType var1) {
      return (new Potion(var1, 1)).toItemStack(1);
   }

   public void transparent(Player var1) {
      var1.spigot().setCollidesWithEntities(false);
   }

   public Boolean getboats() {
      return false;
   }
}
