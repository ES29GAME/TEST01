package com.andrei1058.skygiants.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import org.bukkit.entity.Player;

public class MySQL {
   private String host;
   private int port;
   private String database;
   private String user;
   private String password;
   private Connection conn;

   private String getHost() {
      return this.host;
   }

   private int getPort() {
      return this.port;
   }

   private String getDataBase() {
      return this.database;
   }

   private String getUser() {
      return this.user;
   }

   private String getPassword() {
      return this.password;
   }

   private Connection getConnection() {
      return this.conn;
   }

   private void setHost(String var1) {
      this.host = var1;
   }

   private void setPort(int var1) {
      this.port = var1;
   }

   private void setDataBase(String var1) {
      this.database = var1;
   }

   private void setUser(String var1) {
      this.user = var1;
   }

   private void setPassword(String var1) {
      this.password = var1;
   }

   private void setConnection(Connection var1) {
      this.conn = var1;
   }

   public MySQL(String var1, int var2, String var3, String var4, String var5) {
      this.setHost(var1);
      this.setPort(var2);
      this.setDataBase(var3);
      this.setUser(var4);
      this.setPassword(var5);
   }

   public boolean createTable(String var1, ArrayList var2) {
      this.openConnection();
      if (this.isConnected()) {
         try {
            boolean var3 = true;
            String var4 = "";
            Iterator var5 = var2.iterator();

            while(var5.hasNext()) {
               String var6 = (String)var5.next();
               if (var2.size() == 1) {
                  var4 = var4 + var6;
               } else if (var3) {
                  var4 = var4 + var6;
                  var3 = false;
               } else {
                  var4 = var4 + ", " + var6;
               }
            }

            this.getConnection().createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS `" + var1 + "` (" + var4 + ");");
            return true;
         } catch (SQLException var7) {
            this.printError(var7.getMessage());
         }
      }

      this.closeConnection();
      return false;
   }

   public Object getScore(String var1, String var2, Player var3) {
      if (!this.isConnected()) {
         this.connect();
      }

      if (this.isConnected()) {
         ResultSet var4;
         try {
            var4 = this.getConnection().createStatement().executeQuery("SELECT * FROM `" + var1 + "` WHERE `" + "UUID" + "`='" + var3.getUniqueId().toString() + "';");
            if (var4.next()) {
               return var4.getObject(var2);
            }
         } catch (SQLException var6) {
            ;
         }

         try {
            var4 = this.getConnection().createStatement().executeQuery("SELECT * FROM `" + var1 + "` WHERE `" + "Player" + "`='" + var3.getName() + "';");
            return var4.next() ? var4.getObject(var2) : Integer.valueOf(0);
         } catch (SQLException var5) {
            return Integer.valueOf(0);
         }
      } else {
         return Integer.valueOf(0);
      }
   }

   public boolean deleteData(String var1, String var2, String var3, String var4) {
      this.openConnection();
      if (this.isConnected()) {
         try {
            this.getConnection().createStatement().executeUpdate("DELETE FROM `" + var1 + "` WHERE `" + var2 + "`" + var3 + "'" + var4 + "';");
            return true;
         } catch (SQLException var6) {
            this.printError(var6.getMessage());
         }
      }

      this.closeConnection();
      return false;
   }

   public boolean isDataExists(String var1, String var2, String var3) {
      this.openConnection();
      if (this.isConnected()) {
         try {
            ResultSet var4 = this.getConnection().createStatement().executeQuery("SELECT * FROM `" + var1 + "` WHERE `" + var2 + "`='" + var3 + "';");
            if (var4.next()) {
               return true;
            }
         } catch (SQLException var5) {
            this.printError(var5.getMessage());
         }
      }

      this.closeConnection();
      return false;
   }

   public void connect() {
      this.openConnection();
   }

   public void close() {
      this.closeConnection();
   }

   public boolean setData(String var1, ArrayList var2, ArrayList var3) {
      this.openConnection();
      if (this.isConnected() && var2.size() == var3.size()) {
         try {
            boolean var4 = true;
            int var5 = 0;
            String var6 = "";

            for(Iterator var7 = var2.iterator(); var7.hasNext(); ++var5) {
               String var8 = (String)var7.next();
               if (var4) {
                  var6 = var6 + "`" + var8 + "`='" + (String)var3.get(var5) + "'";
                  var4 = false;
               } else {
                  var6 = var6 + ", `" + var8 + "`='" + (String)var3.get(var5) + "'";
               }
            }

            this.getConnection().createStatement().executeUpdate("UPDATE `" + var1 + "` SET " + var6 + ";");
            return true;
         } catch (SQLException var9) {
            this.printError(var9.getMessage());
         }
      }

      this.closeConnection();
      return false;
   }

   public boolean createDate(String var1, ArrayList var2, ArrayList var3) {
      this.openConnection();
      if (this.isConnected()) {
         try {
            boolean var4 = true;
            boolean var5 = true;
            String var6 = "";
            String var7 = "";
            Iterator var8 = var2.iterator();

            String var9;
            while(var8.hasNext()) {
               var9 = (String)var8.next();
               if (var2.size() == 1) {
                  var7 = var7 + "`" + var9 + "`";
               } else if (var4) {
                  var7 = var7 + "`" + var9 + "`";
                  var4 = false;
               } else {
                  var7 = var7 + ", `" + var9 + "`";
               }
            }

            var8 = var3.iterator();

            while(var8.hasNext()) {
               var9 = (String)var8.next();
               if (var3.size() == 1) {
                  var6 = var6 + "'" + var9 + "'";
               } else if (var5) {
                  var6 = var6 + "'" + var9 + "'";
                  var5 = false;
               } else {
                  var6 = var6 + ", '" + var9 + "'";
               }
            }

            this.getConnection().createStatement().executeUpdate("INSERT INTO `" + var1 + "` (" + var7 + ") VALUES (" + var6 + ");");
            return true;
         } catch (SQLException var10) {
            this.printError(var10.getMessage());
         }
      }

      this.closeConnection();
      return false;
   }

   public boolean setData(String var1, ArrayList var2, ArrayList var3, String var4, String var5, String var6) {
      this.openConnection();
      if (this.isConnected() && var2.size() == var3.size()) {
         try {
            boolean var7 = true;
            int var8 = 0;
            String var9 = "";

            for(Iterator var10 = var2.iterator(); var10.hasNext(); ++var8) {
               String var11 = (String)var10.next();
               if (var7) {
                  var9 = var9 + "`" + var11 + "`='" + (String)var3.get(var8) + "'";
                  var7 = false;
               } else {
                  var9 = var9 + ", `" + var11 + "`='" + (String)var3.get(var8) + "'";
               }
            }

            this.getConnection().createStatement().executeUpdate("UPDATE `" + var1 + "` SET " + var9 + " WHERE `" + var4 + "`" + var5 + "'" + var6 + "';");
            return true;
         } catch (SQLException var12) {
            this.printError(var12.getMessage());
         }
      }

      this.closeConnection();
      return false;
   }

   public boolean execute(String var1) {
      this.openConnection();
      if (this.isConnected()) {
         try {
            this.getConnection().createStatement().executeUpdate(var1);
            return true;
         } catch (SQLException var3) {
            this.printError(var3.getMessage());
         }
      }

      this.closeConnection();
      return false;
   }

   private void printError(String var1) {
      System.out.println(var1);
   }

   private boolean openConnection() {
      if (!this.isConnected()) {
         try {
            this.setConnection(DriverManager.getConnection("jdbc:mysql://" + this.getHost() + ":" + this.getPort() + "/" + this.getDataBase() + "?user=" + this.getUser() + "&password=" + this.getPassword()));
         } catch (SQLException var2) {
            this.printError(var2.getMessage());
            return false;
         }
      }

      return true;
   }

   private boolean closeConnection() {
      if (this.isConnected()) {
         try {
            this.getConnection().close();
         } catch (SQLException var2) {
            this.printError(var2.getMessage());
            return false;
         }
      }

      return true;
   }

   public boolean isConnected() {
      try {
         if (this.getConnection() == null) {
            return false;
         }

         if (this.getConnection().isClosed()) {
            return false;
         }
      } catch (SQLException var2) {
         this.printError(var2.getMessage());
      }

      return true;
   }
}
