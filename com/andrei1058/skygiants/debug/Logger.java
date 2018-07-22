package com.andrei1058.skygiants.debug;

import com.andrei1058.skygiants.Main;

public class Logger {
   public static void debugMsg(String var0) {
      if (Main.Debug) {
         Main.plugin.getLogger().info(var0);
      }
   }
}
