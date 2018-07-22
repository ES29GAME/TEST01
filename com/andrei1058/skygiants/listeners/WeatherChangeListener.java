package com.andrei1058.skygiants.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

public class WeatherChangeListener implements Listener {
   @EventHandler
   public void change(WeatherChangeEvent var1) {
      var1.setCancelled(true);
   }
}
