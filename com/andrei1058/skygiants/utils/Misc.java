package com.andrei1058.skygiants.utils;

public class Misc {
   public static boolean isInt(String var0) {
      try {
         Integer.parseInt(var0);
         return true;
      } catch (NumberFormatException var2) {
         return false;
      }
   }
}
