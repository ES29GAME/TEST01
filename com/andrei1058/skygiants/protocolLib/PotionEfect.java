package com.andrei1058.skygiants.protocolLib;

import com.andrei1058.skygiants.Main;
import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.PacketType.Play.Server;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;

public class PotionEfect {
   public static void registerPotionEffects() {
      Main var10000 = Main.plugin;
      Main.protocolLib.addPacketListener(new PacketAdapter(Main.plugin, ListenerPriority.NORMAL, new PacketType[]{Server.ENTITY_EFFECT}) {
         public void onPacketSending(PacketEvent var1) {
            if (var1.getPacketType() == Server.ENTITY_EFFECT && Main.spectators.contains(var1.getPlayer())) {
               var1.setCancelled(true);
            }

         }
      });
   }
}
