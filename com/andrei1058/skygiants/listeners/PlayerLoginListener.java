package com.andrei1058.skygiants.listeners;

import com.andrei1058.skygiants.Main;
import com.andrei1058.skygiants.game.GameState;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;

public class PlayerLoginListener implements Listener {
   @EventHandler
   public void login(PlayerLoginEvent var1) {
      if (!Main.loaded.booleanValue()) {
         var1.disallow(Result.KICK_OTHER, "\u00a76Loading. . .");
      } else {
         if (var1.getResult() == Result.KICK_FULL) {
            var1.allow();
         }

         if (Main.miniSG.booleanValue()) {
            if (Bukkit.getOnlinePlayers().size() >= 8) {
               if (Main.STATUS != GameState.LOBBY && Main.STATUS != GameState.STARTING) {
                  if (!Main.allowSpectate.booleanValue()) {
                     var1.disallow(Result.KICK_OTHER, "Full");
                  }
               } else {
                  var1.disallow(Result.KICK_OTHER, "Full");
               }
            }
         } else if (Bukkit.getOnlinePlayers().size() >= 16) {
            if (Main.STATUS != GameState.LOBBY && Main.STATUS != GameState.STARTING) {
               if (!Main.allowSpectate.booleanValue()) {
                  var1.disallow(Result.KICK_OTHER, "Full");
               }
            } else {
               var1.disallow(Result.KICK_OTHER, "Full");
            }
         }

      }
   }
}
