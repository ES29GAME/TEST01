package com.andrei1058.skygiants.utils;

import com.andrei1058.skygiants.Main;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.bukkit.Bukkit;

public class Updater {
   public static void checkUpdates() {
      Bukkit.getScheduler().runTaskLater(Main.plugin, new Runnable() {
         public void run() {
            try {
               HttpURLConnection var1 = (HttpURLConnection)(new URL("http://www.spigotmc.org/api/general.php")).openConnection();
               var1.setDoOutput(true);
               var1.setRequestMethod("POST");
               var1.getOutputStream().write("key=98BE0FE67F88AB82B4C197FAF1DC3B69206EFDCC4D3B80FC83A00037510B99B4&resource=29803".getBytes());
               String var2 = Main.plugin.getDescription().getVersion();
               String var3 = (new BufferedReader(new InputStreamReader(var1.getInputStream()))).readLine().replaceAll("[a-zA-Z ]", "");
               if (!var3.equalsIgnoreCase(var2)) {
                  Main var10000 = Main.plugin;
                  Main.updateAvailable = true;
                  var10000 = Main.plugin;
                  Main.newVersion = var3;
                  Main.plugin.getLogger().info("There is a nev version available!");
               }
            } catch (IOException var4) {
               var4.printStackTrace();
            }

         }
      }, 30L);
   }
}
