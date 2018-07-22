package com.andrei1058.skygiants.utils;

import java.util.NavigableMap;
import java.util.Random;
import java.util.TreeMap;

public class RandomItem {
   private final NavigableMap map;
   private final Random random;
   private double total;

   public RandomItem() {
      this(new Random());
   }

   public RandomItem(Random var1) {
      this.map = new TreeMap();
      this.total = 0.0D;
      this.random = var1;
   }

   public void add(double var1, Object var3) {
      if (var1 > 0.0D) {
         this.total += var1;
         this.map.put(this.total, var3);
      }

   }

   public Object next() {
      double var1 = this.random.nextDouble() * this.total;
      return this.map.ceilingEntry(var1).getValue();
   }
}
