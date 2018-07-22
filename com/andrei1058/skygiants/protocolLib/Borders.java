package com.andrei1058.skygiants.protocolLib;

import com.andrei1058.skygiants.Main;
import com.andrei1058.skygiants.configuration.ArenaCfg;
import com.comphenix.protocol.PacketType.Play.Server;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.EnumWrappers.WorldBorderAction;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Borders {
   public static void magentaBorders(Player var0) {
      Main var10000 = Main.plugin;
      PacketContainer var1 = Main.protocolLib.createPacket(Server.WORLD_BORDER);
      var1.getWorldBorderActions().write(0, WorldBorderAction.INITIALIZE);
      var1.getDoubles().write(0, ArenaCfg.getArena(Main.choosenMap).getDouble("Border.Magenta.X")).write(1, ArenaCfg.getArena(Main.choosenMap).getDouble("Border.Magenta.Z")).write(2, ArenaCfg.getArena(Main.choosenMap).getDouble("Border.Magenta.Size")).write(3, ArenaCfg.getArena(Main.choosenMap).getDouble("Border.Magenta.Size"));
      var1.getLongs().write(0, 0L);
      var1.getIntegers().write(0, 29999984).write(1, Integer.valueOf(0)).write(2, Integer.valueOf(0));

      try {
         var10000 = Main.plugin;
         Main.protocolLib.sendServerPacket(var0, var1);
      } catch (InvocationTargetException var3) {
         var3.printStackTrace();
      }

   }

   public static void goldBorders(Player var0) {
      Main var10000 = Main.plugin;
      PacketContainer var1 = Main.protocolLib.createPacket(Server.WORLD_BORDER);
      var1.getWorldBorderActions().write(0, WorldBorderAction.INITIALIZE);
      var1.getDoubles().write(0, ArenaCfg.getArena(Main.choosenMap).getDouble("Border.Gold.X")).write(1, ArenaCfg.getArena(Main.choosenMap).getDouble("Border.Gold.Z")).write(2, ArenaCfg.getArena(Main.choosenMap).getDouble("Border.Gold.Size")).write(3, ArenaCfg.getArena(Main.choosenMap).getDouble("Border.Gold.Size"));
      var1.getLongs().write(0, 0L);
      var1.getIntegers().write(0, 29999984).write(1, Integer.valueOf(0)).write(2, Integer.valueOf(0));

      try {
         var10000 = Main.plugin;
         Main.protocolLib.sendServerPacket(var0, var1);
      } catch (InvocationTargetException var3) {
         var3.printStackTrace();
      }

   }

   public static void greenBorders(Player var0) {
      Main var10000 = Main.plugin;
      PacketContainer var1 = Main.protocolLib.createPacket(Server.WORLD_BORDER);
      var1.getWorldBorderActions().write(0, WorldBorderAction.INITIALIZE);
      var1.getDoubles().write(0, ArenaCfg.getArena(Main.choosenMap).getDouble("Border.Green.X")).write(1, ArenaCfg.getArena(Main.choosenMap).getDouble("Border.Green.Z")).write(2, ArenaCfg.getArena(Main.choosenMap).getDouble("Border.Green.Size")).write(3, ArenaCfg.getArena(Main.choosenMap).getDouble("Border.Green.Size"));
      var1.getLongs().write(0, 0L);
      var1.getIntegers().write(0, 29999984).write(1, Integer.valueOf(0)).write(2, Integer.valueOf(0));

      try {
         var10000 = Main.plugin;
         Main.protocolLib.sendServerPacket(var0, var1);
      } catch (InvocationTargetException var3) {
         var3.printStackTrace();
      }

   }

   public static void blueBorders(Player var0) {
      Main var10000 = Main.plugin;
      PacketContainer var1 = Main.protocolLib.createPacket(Server.WORLD_BORDER);
      var1.getWorldBorderActions().write(0, WorldBorderAction.INITIALIZE);
      var1.getDoubles().write(0, ArenaCfg.getArena(Main.choosenMap).getDouble("Border.Blue.X")).write(1, ArenaCfg.getArena(Main.choosenMap).getDouble("Border.Blue.Z")).write(2, ArenaCfg.getArena(Main.choosenMap).getDouble("Border.Blue.Size")).write(3, ArenaCfg.getArena(Main.choosenMap).getDouble("Border.Bluereload.Size"));
      var1.getLongs().write(0, 0L);
      var1.getIntegers().write(0, 29999984).write(1, Integer.valueOf(0)).write(2, Integer.valueOf(0));

      try {
         var10000 = Main.plugin;
         Main.protocolLib.sendServerPacket(var0, var1);
      } catch (InvocationTargetException var3) {
         var3.printStackTrace();
      }

   }

   public static void removeBorder() {
      Main var10000 = Main.plugin;
      PacketContainer var0 = Main.protocolLib.createPacket(Server.WORLD_BORDER);
      var0.getWorldBorderActions().write(0, WorldBorderAction.INITIALIZE);
      var0.getDoubles().write(0, Main.plugin.getConfig().getDouble("Border.Magenta.Z")).write(1, Main.plugin.getConfig().getDouble("Border.Magenta.Z")).write(2, 3.0E7D).write(3, 3.0E7D);
      var0.getLongs().write(0, 0L);
      var0.getIntegers().write(0, 29999984).write(1, Integer.valueOf(0)).write(2, Integer.valueOf(0));

      try {
         Iterator var1 = Bukkit.getOnlinePlayers().iterator();

         while(var1.hasNext()) {
            Player var2 = (Player)var1.next();
            var10000 = Main.plugin;
            Main.protocolLib.sendServerPacket(var2, var0);
         }
      } catch (InvocationTargetException var3) {
         var3.printStackTrace();
      }

   }
}
