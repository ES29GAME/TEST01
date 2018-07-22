package com.andrei1058.skygiants.game;

import com.andrei1058.skygiants.Main;
import org.bukkit.entity.Player;

public class RandomTeam {
   public static void randomTeam(Player var0) {
      Main.players.add(var0);
      if (!Main.magentaPlayers.contains(var0) && !Main.goldPlayers.contains(var0) && !Main.greenPlayers.contains(var0) && !Main.bluePlayers.contains(var0)) {
         if (Main.miniSG.booleanValue()) {
            if (Main.magentaPlayers.isEmpty()) {
               Main.magentaPlayers.add(var0);
               return;
            }

            if (Main.goldPlayers.isEmpty()) {
               Main.goldPlayers.add(var0);
               return;
            }

            if (Main.greenPlayers.isEmpty()) {
               Main.greenPlayers.add(var0);
               return;
            }

            if (Main.bluePlayers.isEmpty()) {
               Main.bluePlayers.add(var0);
               return;
            }

            if (Main.magentaPlayers.size() == 1) {
               Main.magentaPlayers.add(var0);
               return;
            }

            if (Main.goldPlayers.size() == 1) {
               Main.goldPlayers.add(var0);
               return;
            }

            if (Main.greenPlayers.size() == 1) {
               Main.greenPlayers.add(var0);
               return;
            }

            if (Main.bluePlayers.size() == 1) {
               Main.bluePlayers.add(var0);
               return;
            }

            Spectate.setSpectator(var0);
         } else {
            if (Main.magentaPlayers.isEmpty()) {
               Main.magentaPlayers.add(var0);
               return;
            }

            if (Main.goldPlayers.isEmpty()) {
               Main.goldPlayers.add(var0);
               return;
            }

            if (Main.greenPlayers.isEmpty()) {
               Main.greenPlayers.add(var0);
               return;
            }

            if (Main.bluePlayers.isEmpty()) {
               Main.bluePlayers.add(var0);
               return;
            }

            if (Main.magentaPlayers.size() == 1) {
               Main.magentaPlayers.add(var0);
               return;
            }

            if (Main.goldPlayers.size() == 1) {
               Main.goldPlayers.add(var0);
               return;
            }

            if (Main.greenPlayers.size() == 1) {
               Main.greenPlayers.add(var0);
               return;
            }

            if (Main.bluePlayers.size() == 1) {
               Main.bluePlayers.add(var0);
               return;
            }

            if (Main.magentaPlayers.size() == 2) {
               Main.magentaPlayers.add(var0);
               return;
            }

            if (Main.goldPlayers.size() == 2) {
               Main.goldPlayers.add(var0);
               return;
            }

            if (Main.greenPlayers.size() == 2) {
               Main.greenPlayers.add(var0);
               return;
            }

            if (Main.bluePlayers.size() == 2) {
               Main.bluePlayers.add(var0);
               return;
            }

            if (Main.magentaPlayers.size() == 3) {
               Main.magentaPlayers.add(var0);
               return;
            }

            if (Main.goldPlayers.size() == 3) {
               Main.goldPlayers.add(var0);
               return;
            }

            if (Main.greenPlayers.size() == 3) {
               Main.greenPlayers.add(var0);
               return;
            }

            if (Main.bluePlayers.size() == 3) {
               Main.bluePlayers.add(var0);
               return;
            }

            if (Main.magentaPlayers.size() == 4) {
               Main.magentaPlayers.add(var0);
               return;
            }

            if (Main.goldPlayers.size() == 4) {
               Main.goldPlayers.add(var0);
               return;
            }

            if (Main.greenPlayers.size() == 4) {
               Main.greenPlayers.add(var0);
               return;
            }

            if (Main.bluePlayers.size() == 4) {
               Main.bluePlayers.add(var0);
               return;
            }

            if (Main.magentaPlayers.size() == 5) {
               Main.magentaPlayers.add(var0);
               return;
            }

            if (Main.goldPlayers.size() == 5) {
               Main.goldPlayers.add(var0);
               return;
            }

            if (Main.greenPlayers.size() == 5) {
               Main.greenPlayers.add(var0);
               return;
            }

            if (Main.bluePlayers.size() == 5) {
               Main.bluePlayers.add(var0);
               return;
            }

            Spectate.setSpectator(var0);
         }

      }
   }
}
