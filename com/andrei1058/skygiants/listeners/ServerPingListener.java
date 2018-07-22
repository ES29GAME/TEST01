package com.andrei1058.skygiants.listeners;

import com.andrei1058.skygiants.Main;
import com.andrei1058.skygiants.configuration.Messages;
import com.andrei1058.skygiants.game.GameState;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class ServerPingListener implements Listener {
   @EventHandler
   public void ping(ServerListPingEvent var1) {
      if (Main.loaded.booleanValue()) {
         if (Main.MAINTENANCE.booleanValue()) {
            var1.setMotd("\u00a74MAINTENANCE");
         }

         if (Main.miniSG.booleanValue()) {
            var1.setMaxPlayers(8);
         } else {
            var1.setMaxPlayers(24);
         }

         if (Main.STATUS != null) {
            if (Main.STATUS == GameState.LOBBY) {
               var1.setMotd(Messages.getMsg().getString("motd.lobby").replace('&', '\u00a7'));
            }

            if (Main.STATUS == GameState.STARTING) {
               var1.setMotd(Messages.getMsg().getString("motd.starting").replace('&', '\u00a7'));
            }

            if (Main.STATUS == GameState.PLAYING) {
               var1.setMotd(Messages.getMsg().getString("motd.playing").replace('&', '\u00a7'));
            }

            if (Main.STATUS == GameState.RESTARTING) {
               var1.setMotd(Messages.getMsg().getString("motd.restarting").replace('&', '\u00a7'));
            }

         }
      }
   }
}
