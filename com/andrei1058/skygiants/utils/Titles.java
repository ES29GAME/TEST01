package com.andrei1058.skygiants.utils;

import com.andrei1058.skygiants.Main;
import com.andrei1058.skygiants.configuration.Messages;
import java.lang.reflect.Constructor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Titles {
   /** @deprecated */
   @Deprecated
   public static void sendTitle(Player var0, Integer var1, Integer var2, Integer var3, String var4) {
      sendTitle(var0, var1, var2, var3, var4, (String)null);
   }

   public static void sendSubtitle(Player var0, Integer var1, Integer var2, Integer var3, String var4) {
      sendTitle(var0, var1, var2, var3, (String)null, var4);
   }

   public static void sendFullTitle(Player var0, Integer var1, Integer var2, Integer var3, String var4, String var5) {
      sendTitle(var0, var1, var2, var3, var4, var5);
   }

   /** @deprecated */
   @Deprecated
   public static Integer getPlayerProtocol(Player var0) {
      return Integer.valueOf(47);
   }

   public static void sendPacket(Player var0, Object var1) {
      try {
         Object var2 = var0.getClass().getMethod("getHandle").invoke(var0);
         Object var3 = var2.getClass().getField("playerConnection").get(var2);
         var3.getClass().getMethod("sendPacket", getNMSClass("Packet")).invoke(var3, var1);
      } catch (Exception var4) {
         var4.printStackTrace();
      }

   }

   public static Class getNMSClass(String var0) {
      String var1 = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];

      try {
         return Class.forName("net.minecraft.server." + var1 + "." + var0);
      } catch (ClassNotFoundException var3) {
         var3.printStackTrace();
         return null;
      }
   }

   public static void sendTitle(Player var0, Integer var1, Integer var2, Integer var3, String var4, String var5) {
      try {
         Object var6;
         Object var7;
         Constructor var8;
         Object var9;
         if (var4 != null) {
            var6 = getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("TITLE").get((Object)null);
            var7 = getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", String.class).invoke((Object)null, "{\"text\":\"" + var4 + "\"}");
            var8 = getNMSClass("PacketPlayOutTitle").getConstructor(getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0], getNMSClass("IChatBaseComponent"), Integer.TYPE, Integer.TYPE, Integer.TYPE);
            var9 = var8.newInstance(var6, var7, var1, var2, var3);
            sendPacket(var0, var9);
         }

         if (var5 != null) {
            var6 = getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("SUBTITLE").get((Object)null);
            var7 = getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", String.class).invoke((Object)null, "{\"text\":\"" + var5 + "\"}");
            var8 = getNMSClass("PacketPlayOutTitle").getConstructor(getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0], getNMSClass("IChatBaseComponent"), Integer.TYPE, Integer.TYPE, Integer.TYPE);
            var9 = var8.newInstance(var6, var7, var1, var2, var3);
            sendPacket(var0, var9);
         }
      } catch (Exception var10) {
         var10.printStackTrace();
      }

   }

   public static void fight(Player var0, Integer var1, Integer var2, Integer var3, String var4, String var5) {
      try {
         Object var6;
         Object var7;
         Constructor var8;
         Object var9;
         if (var4 != null) {
            var6 = getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("TITLE").get((Object)null);
            var7 = getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", String.class).invoke((Object)null, "{\"text\":\"" + var4 + "\"}");
            var8 = getNMSClass("PacketPlayOutTitle").getConstructor(getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0], getNMSClass("IChatBaseComponent"), Integer.TYPE, Integer.TYPE, Integer.TYPE);
            var9 = var8.newInstance(var6, var7, var1, var2, var3);
            sendPacket(var0, var9);
         }

         if (var5 != null) {
            var6 = getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("SUBTITLE").get((Object)null);
            var7 = getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", String.class).invoke((Object)null, "{\"text\":\"" + var5 + "\"}");
            var8 = getNMSClass("PacketPlayOutTitle").getConstructor(getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0], getNMSClass("IChatBaseComponent"), Integer.TYPE, Integer.TYPE, Integer.TYPE);
            var9 = var8.newInstance(var6, var7, var1, var2, var3);
            sendPacket(var0, var9);
         }
      } catch (Exception var10) {
         var10.printStackTrace();
      }

   }

   public static void fightTitle(final Player var0) {
      (new BukkitRunnable() {
         int countdown = Messages.getMsg().getStringList("fight").size();
         int string = 0;

         public void run() {
            if (this.countdown != 0) {
               --this.countdown;
            }

            Titles.sendTitle(var0, Integer.valueOf(1), Integer.valueOf(5), Integer.valueOf(1), ((String)Messages.getMsg().getStringList("fight").get(this.string)).replace('&', '\u00a7'), Messages.getMsg().getString("good-luck").replace("{player}", var0.getName()).replace('&', '\u00a7'));
            ++this.string;
            if (this.countdown == 0) {
               this.cancel();
            }

         }
      }).runTaskTimer(Main.plugin, 0L, 5L);
   }
}
