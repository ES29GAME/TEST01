package com.andrei1058.skygiants;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionType;

public interface NMS {
   void registerGiant();

   void spawnGiants();

   String gGH(String var1);

   int getGiantHealthInt(String var1);

   void killGiant(String var1);

   void actionMsg(Player var1, String var2);

   void spawnVillagers();

   void registerVillager();

   void spawnBeast();

   void registerBeast();

   Sound catMeow();

   Sound eggPop();

   Sound lvlUp();

   Sound anvil();

   Sound villager();

   Sound splash();

   Sound giantHurt();

   ItemStack getItm(Player var1);

   ItemStack potion(PotionType var1);

   void transparent(Player var1);

   Boolean getboats();
}
