package com.andrei1058.skygiants.game;

import com.andrei1058.skygiants.Main;
import com.andrei1058.skygiants.configuration.Messages;
import java.util.ArrayList;
import java.util.Iterator;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Shop {
   public static void buy(Integer var0, Player var1, ItemStack var2) {
      if (((Integer)Main.money.get(var1)).intValue() >= var0.intValue()) {
         Main.money.replace(var1, ((Integer)Main.money.get(var1)).intValue() - var0.intValue());
         var1.sendMessage(Main.PREFIX + Messages.getMsg().getString("purchase").replace('&', '\u00a7'));
         var1.getInventory().addItem(new ItemStack[]{var2});
      } else {
         Iterator var3 = Messages.getMsg().getStringList("insufficient-money").iterator();

         while(var3.hasNext()) {
            String var4 = (String)var3.next();
            var1.sendMessage(Main.PREFIX + var4.replace('&', '\u00a7'));
         }

      }
   }

   public static Inventory mainShop() {
      Inventory var0 = Bukkit.createInventory((InventoryHolder)null, 9, Messages.getMsg().getString("shop.main").replace('&', '\u00a7'));
      var0.addItem(new ItemStack[]{mainItem(Messages.getMsg().getString("shop.gear"), Material.IRON_SWORD)});
      var0.addItem(new ItemStack[]{mainItem(Messages.getMsg().getString("shop.food"), Material.APPLE)});
      var0.addItem(new ItemStack[]{mainItem(Messages.getMsg().getString("shop.potions"), Material.POTION)});
      var0.addItem(new ItemStack[]{mainItem(Messages.getMsg().getString("shop.vanity"), Material.SEA_LANTERN)});
      var0.addItem(new ItemStack[]{mainItem(Messages.getMsg().getString("shop.specials"), Material.RAILS)});
      return var0;
   }

   private static ItemStack mainItem(String var0, Material var1) {
      ItemStack var2 = new ItemStack(var1);
      ItemMeta var3 = var2.getItemMeta();
      var3.setDisplayName(var0.replace('&', '\u00a7'));
      ArrayList var4 = new ArrayList();
      Iterator var5 = Messages.getMsg().getStringList("shop.browse").iterator();

      while(var5.hasNext()) {
         String var6 = (String)var5.next();
         var4.add(var6.replace("{category}", ChatColor.stripColor(var0)).replace('&', '\u00a7'));
      }

      var3.setLore(var4);
      var2.setItemMeta(var3);
      return var2;
   }

   private static ItemStack shopGearItem(String var0, Integer var1, Integer var2, Material var3) {
      ItemStack var4 = new ItemStack(var3, var1.intValue());
      ItemMeta var5 = var4.getItemMeta();
      var5.setDisplayName(Messages.getMsg().getString("shop.itemformat").replace("{item}", Messages.getMsg().getString(var0)).replace("{amount}", String.valueOf(var1)).replace('&', '\u00a7'));
      ArrayList var6 = new ArrayList();
      Iterator var7 = Messages.getMsg().getStringList("shop.itemlore").iterator();

      while(var7.hasNext()) {
         String var8 = (String)var7.next();
         var6.add(var8.replace("{gold}", String.valueOf(var2)).replace('&', '\u00a7'));
      }

      var5.setLore(var6);
      var4.setItemMeta(var5);
      return var4;
   }

   private static ItemStack shoplapis() {
      ItemStack var0 = new ItemStack(Material.INK_SACK, 1, (short)4);
      ItemMeta var1 = var0.getItemMeta();
      var1.setDisplayName(Messages.getMsg().getString("shop.itemformat").replace("{item}", Messages.getMsg().getString("shop.lapis")).replace("{amount}", String.valueOf(1)).replace('&', '\u00a7'));
      ArrayList var2 = new ArrayList();
      Iterator var3 = Messages.getMsg().getStringList("shop.itemlore").iterator();

      while(var3.hasNext()) {
         String var4 = (String)var3.next();
         var2.add(var4.replace("{gold}", String.valueOf(1000)).replace('&', '\u00a7'));
      }

      var1.setLore(var2);
      var0.setItemMeta(var1);
      return var0;
   }

   private static ItemStack shopPotionsItem(String var0, Integer var1) {
      ItemStack var2 = new ItemStack(Material.POTION);
      ItemMeta var3 = var2.getItemMeta();
      var3.setDisplayName(Messages.getMsg().getString("shop.potionformat").replace("{potion}", Messages.getMsg().getString(var0)).replace("{amount}", "1").replace('&', '\u00a7'));
      ArrayList var4 = new ArrayList();
      Iterator var5 = Messages.getMsg().getStringList("shop.itemlore").iterator();

      while(var5.hasNext()) {
         String var6 = (String)var5.next();
         var4.add(var6.replace("{gold}", String.valueOf(var1)).replace('&', '\u00a7'));
      }

      var3.setLore(var4);
      var2.setItemMeta(var3);
      return var2;
   }

   private static ItemStack backItem() {
      ItemStack var0 = new ItemStack(Material.BARRIER);
      ItemMeta var1 = var0.getItemMeta();
      var1.setDisplayName(Messages.getMsg().getString("shop.back").replace('&', '\u00a7'));
      ArrayList var2 = new ArrayList();
      Iterator var3 = Messages.getMsg().getStringList("shop.backlore").iterator();

      while(var3.hasNext()) {
         String var4 = (String)var3.next();
         var2.add(var4.replace('&', '\u00a7'));
      }

      var1.setLore(var2);
      var0.setItemMeta(var1);
      return var0;
   }

   public static Inventory gearShop() {
      Inventory var0 = Bukkit.createInventory((InventoryHolder)null, 27, "\u00a77[M] " + ChatColor.stripColor(Messages.getMsg().getString("shop.gear").replace('&', '\u00a7')));
      var0.addItem(new ItemStack[]{shopGearItem("shop.sword", Integer.valueOf(1), Integer.valueOf(250), Material.DIAMOND_SWORD)});
      var0.addItem(new ItemStack[]{shopGearItem("shop.pickaxe", Integer.valueOf(1), Integer.valueOf(150), Material.DIAMOND_PICKAXE)});
      var0.addItem(new ItemStack[]{shopGearItem("shop.helmet", Integer.valueOf(1), Integer.valueOf(250), Material.DIAMOND_HELMET)});
      var0.addItem(new ItemStack[]{shopGearItem("shop.chestplate", Integer.valueOf(1), Integer.valueOf(350), Material.DIAMOND_CHESTPLATE)});
      var0.addItem(new ItemStack[]{shopGearItem("shop.leggings", Integer.valueOf(1), Integer.valueOf(200), Material.DIAMOND_LEGGINGS)});
      var0.addItem(new ItemStack[]{shopGearItem("shop.boots", Integer.valueOf(1), Integer.valueOf(150), Material.DIAMOND_BOOTS)});
      var0.addItem(new ItemStack[]{shopGearItem("shop.bow", Integer.valueOf(1), Integer.valueOf(250), Material.BOW)});
      var0.addItem(new ItemStack[]{shopGearItem("shop.arrows", Integer.valueOf(16), Integer.valueOf(400), Material.ARROW)});
      var0.setItem(22, backItem());
      return var0;
   }

   public static Inventory foodShop() {
      Inventory var0 = Bukkit.createInventory((InventoryHolder)null, 27, "\u00a77[M] " + ChatColor.stripColor(Messages.getMsg().getString("shop.food").replace('&', '\u00a7')));
      var0.addItem(new ItemStack[]{shopGearItem("shop.melon", Integer.valueOf(15), Integer.valueOf(20), Material.MELON)});
      var0.addItem(new ItemStack[]{shopGearItem("shop.carrot", Integer.valueOf(15), Integer.valueOf(25), Material.CARROT_ITEM)});
      var0.addItem(new ItemStack[]{shopGearItem("shop.apple", Integer.valueOf(15), Integer.valueOf(30), Material.APPLE)});
      var0.addItem(new ItemStack[]{shopGearItem("shop.bread", Integer.valueOf(5), Integer.valueOf(35), Material.BREAD)});
      var0.addItem(new ItemStack[]{shopGearItem("shop.chicken", Integer.valueOf(5), Integer.valueOf(40), Material.COOKED_CHICKEN)});
      var0.addItem(new ItemStack[]{shopGearItem("shop.steak", Integer.valueOf(5), Integer.valueOf(45), Material.COOKED_BEEF)});
      var0.addItem(new ItemStack[]{shopGearItem("shop.cake", Integer.valueOf(3), Integer.valueOf(50), Material.CAKE)});
      var0.addItem(new ItemStack[]{shopGearItem("shop.golden", Integer.valueOf(1), Integer.valueOf(1000), Material.GOLDEN_APPLE)});
      var0.setItem(22, backItem());
      return var0;
   }

   public static Inventory potionShop() {
      Inventory var0 = Bukkit.createInventory((InventoryHolder)null, 27, "\u00a77[M] " + ChatColor.stripColor(Messages.getMsg().getString("shop.potions").replace('&', '\u00a7')));
      var0.addItem(new ItemStack[]{shopPotionsItem("shop.invisibility", Integer.valueOf(750))});
      var0.addItem(new ItemStack[]{shopPotionsItem("shop.regeneration", Integer.valueOf(600))});
      var0.addItem(new ItemStack[]{shopPotionsItem("shop.swiftness", Integer.valueOf(300))});
      var0.addItem(new ItemStack[]{shopPotionsItem("shop.flamer", Integer.valueOf(200))});
      var0.addItem(new ItemStack[]{shopPotionsItem("shop.rabbitp", Integer.valueOf(200))});
      var0.addItem(new ItemStack[]{shopPotionsItem("shop.instanth", Integer.valueOf(250))});
      var0.addItem(new ItemStack[]{shopPotionsItem("shop.hurting", Integer.valueOf(400))});
      var0.addItem(new ItemStack[]{shopPotionsItem("shop.deadlyp", Integer.valueOf(1000))});
      var0.addItem(new ItemStack[]{shopGearItem("shop.milk", Integer.valueOf(1), Integer.valueOf(25), Material.MILK_BUCKET)});
      var0.addItem(new ItemStack[]{shopGearItem("shop.exp", Integer.valueOf(1), Integer.valueOf(2000), Material.EXP_BOTTLE)});
      var0.addItem(new ItemStack[]{shoplapis()});
      var0.setItem(22, backItem());
      return var0;
   }

   public static Inventory vanityShop() {
      Inventory var0 = Bukkit.createInventory((InventoryHolder)null, 45, "\u00a77[M] " + ChatColor.stripColor(Messages.getMsg().getString("shop.vanity").replace('&', '\u00a7')));
      var0.addItem(new ItemStack[]{shopGearItem("shop.prismarine", Integer.valueOf(32), Integer.valueOf(50), Material.PRISMARINE)});
      var0.addItem(new ItemStack[]{shopGearItem("shop.lantern", Integer.valueOf(32), Integer.valueOf(50), Material.SEA_LANTERN)});
      var0.addItem(new ItemStack[]{shopGearItem("shop.glowstone", Integer.valueOf(32), Integer.valueOf(50), Material.GLOWSTONE)});
      var0.addItem(new ItemStack[]{shopItemShort("shop.orangeclay", Integer.valueOf(32), Integer.valueOf(50), Material.STAINED_CLAY, Short.valueOf((short)1))});
      var0.addItem(new ItemStack[]{shopItemShort("shop.lightblueclay", Integer.valueOf(32), Integer.valueOf(50), Material.STAINED_CLAY, Short.valueOf((short)3))});
      var0.addItem(new ItemStack[]{shopItemShort("shop.yellowclay", Integer.valueOf(32), Integer.valueOf(50), Material.STAINED_CLAY, Short.valueOf((short)4))});
      var0.addItem(new ItemStack[]{shopItemShort("shop.limeclay", Integer.valueOf(32), Integer.valueOf(50), Material.STAINED_CLAY, Short.valueOf((short)5))});
      var0.addItem(new ItemStack[]{shopItemShort("shop.pinkclay", Integer.valueOf(32), Integer.valueOf(50), Material.STAINED_CLAY, Short.valueOf((short)6))});
      var0.addItem(new ItemStack[]{shopItemShort("shop.cyanclay", Integer.valueOf(32), Integer.valueOf(50), Material.STAINED_CLAY, Short.valueOf((short)9))});
      var0.addItem(new ItemStack[]{shopItemShort("shop.purpleclay", Integer.valueOf(32), Integer.valueOf(50), Material.STAINED_CLAY, Short.valueOf((short)10))});
      var0.addItem(new ItemStack[]{shopItemShort("shop.blueclay", Integer.valueOf(32), Integer.valueOf(50), Material.STAINED_CLAY, Short.valueOf((short)11))});
      var0.addItem(new ItemStack[]{shopItemShort("shop.redclay", Integer.valueOf(32), Integer.valueOf(50), Material.STAINED_CLAY, Short.valueOf((short)14))});
      var0.addItem(new ItemStack[]{shopItemShort("shop.orangeglass", Integer.valueOf(32), Integer.valueOf(50), Material.STAINED_GLASS, Short.valueOf((short)1))});
      var0.addItem(new ItemStack[]{shopItemShort("shop.magentaglass", Integer.valueOf(32), Integer.valueOf(50), Material.STAINED_GLASS, Short.valueOf((short)2))});
      var0.addItem(new ItemStack[]{shopItemShort("shop.lightblueglass", Integer.valueOf(32), Integer.valueOf(50), Material.STAINED_GLASS, Short.valueOf((short)3))});
      var0.addItem(new ItemStack[]{shopItemShort("shop.yellowglass", Integer.valueOf(32), Integer.valueOf(50), Material.STAINED_GLASS, Short.valueOf((short)4))});
      var0.addItem(new ItemStack[]{shopItemShort("shop.limeglass", Integer.valueOf(32), Integer.valueOf(50), Material.STAINED_GLASS, Short.valueOf((short)5))});
      var0.addItem(new ItemStack[]{shopItemShort("shop.pinkglass", Integer.valueOf(32), Integer.valueOf(50), Material.STAINED_GLASS, Short.valueOf((short)6))});
      var0.addItem(new ItemStack[]{shopItemShort("shop.cyanglass", Integer.valueOf(32), Integer.valueOf(50), Material.STAINED_GLASS, Short.valueOf((short)9))});
      var0.addItem(new ItemStack[]{shopItemShort("shop.purpleglass", Integer.valueOf(32), Integer.valueOf(50), Material.STAINED_GLASS, Short.valueOf((short)10))});
      var0.addItem(new ItemStack[]{shopItemShort("shop.blueglass", Integer.valueOf(32), Integer.valueOf(50), Material.STAINED_GLASS, Short.valueOf((short)11))});
      var0.addItem(new ItemStack[]{shopItemShort("shop.redglass", Integer.valueOf(32), Integer.valueOf(50), Material.STAINED_GLASS, Short.valueOf((short)14))});
      var0.addItem(new ItemStack[]{shopItemShort("shop.redbanner", Integer.valueOf(5), Integer.valueOf(100), Material.BANNER, Short.valueOf((short)1))});
      var0.addItem(new ItemStack[]{shopItemShort("shop.greenbanner", Integer.valueOf(5), Integer.valueOf(100), Material.BANNER, Short.valueOf((short)10))});
      var0.addItem(new ItemStack[]{shopItemShort("shop.yellowbanner", Integer.valueOf(5), Integer.valueOf(100), Material.BANNER, Short.valueOf((short)11))});
      var0.addItem(new ItemStack[]{shopItemShort("shop.bluebanner", Integer.valueOf(5), Integer.valueOf(100), Material.BANNER, Short.valueOf((short)4))});
      var0.setItem(41, backItem());
      return var0;
   }

   private static ItemStack shopItemShort(String var0, Integer var1, Integer var2, Material var3, Short var4) {
      ItemStack var5 = new ItemStack(var3, var1.intValue(), var4.shortValue());
      ItemMeta var6 = var5.getItemMeta();
      var6.setDisplayName(Messages.getMsg().getString("shop.itemformat").replace("{item}", Messages.getMsg().getString(var0)).replace("{amount}", String.valueOf(var1)).replace('&', '\u00a7'));
      ArrayList var7 = new ArrayList();
      Iterator var8 = Messages.getMsg().getStringList("shop.itemlore").iterator();

      while(var8.hasNext()) {
         String var9 = (String)var8.next();
         var7.add(var9.replace("{gold}", String.valueOf(var2)).replace('&', '\u00a7'));
      }

      var6.setLore(var7);
      var5.setItemMeta(var6);
      return var5;
   }
}
