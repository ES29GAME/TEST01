package com.andrei1058.skygiants.nms.v1_9_R2;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Map;
import net.minecraft.server.v1_9_R2.EntityTypes;

public class RegisterEntity {
   public void registerEntity(String var1, int var2, Class var3, Class var4) {
      try {
         ArrayList var5 = new ArrayList();
         Field[] var6 = EntityTypes.class.getDeclaredFields();
         int var7 = var6.length;

         for(int var8 = 0; var8 < var7; ++var8) {
            Field var9 = var6[var8];
            if (var9.getType().getSimpleName().equals(Map.class.getSimpleName())) {
               var9.setAccessible(true);
               var5.add((Map)var9.get((Object)null));
            }
         }

         if (((Map)var5.get(2)).containsKey(var2)) {
            ((Map)var5.get(0)).remove(var1);
            ((Map)var5.get(2)).remove(var2);
         }

         Method var11 = EntityTypes.class.getDeclaredMethod("a", Class.class, String.class, Integer.TYPE);
         var11.setAccessible(true);
         var11.invoke((Object)null, var4, var1, var2);
      } catch (Exception var10) {
         var10.printStackTrace();
      }

   }
}
