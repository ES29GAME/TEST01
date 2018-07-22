package com.andrei1058.skygiants.listeners;

import com.andrei1058.skygiants.Main;
import com.andrei1058.skygiants.configuration.Messages;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMoveListener implements Listener {
   public void move(PlayerMoveEvent var1) {
      if (Main.recall.contains(var1.getPlayer())) {
         Main.recall.remove(var1.getPlayer());
         var1.getPlayer().sendMessage(Main.PREFIX + Messages.getMsg().getString("recall-cancelled").replace('&', '\u00a7'));
      }

   }
}
