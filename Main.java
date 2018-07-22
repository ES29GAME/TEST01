package com.andrei1058.skygiants;

import com.andrei1058.skygiants.commands.GiveGold;
import com.andrei1058.skygiants.commands.Leave;
import com.andrei1058.skygiants.commands.Recall;
import com.andrei1058.skygiants.commands.SetupCmds;
import com.andrei1058.skygiants.commands.Vote;
import com.andrei1058.skygiants.configuration.ArenaCfg;
import com.andrei1058.skygiants.configuration.Messages;
import com.andrei1058.skygiants.configuration.Settings;
import com.andrei1058.skygiants.game.GameState;
import com.andrei1058.skygiants.listeners.AsyncPlaterChatListener;
import com.andrei1058.skygiants.listeners.BlockBreakListener;
import com.andrei1058.skygiants.listeners.BlockPlaceListener;
import com.andrei1058.skygiants.listeners.CreatureSpawnListener;
import com.andrei1058.skygiants.listeners.EntityDamageByEntityListener;
import com.andrei1058.skygiants.listeners.EntityDamageListener;
import com.andrei1058.skygiants.listeners.EntityDeathListener;
import com.andrei1058.skygiants.listeners.EntityExplodeListener;
import com.andrei1058.skygiants.listeners.FoodLevelChangeListener;
import com.andrei1058.skygiants.listeners.InventoryClickListener;
import com.andrei1058.skygiants.listeners.PlayerDeathListener;
import com.andrei1058.skygiants.listeners.PlayerDropItemListener;
import com.andrei1058.skygiants.listeners.PlayerInteractEntityListener;
import com.andrei1058.skygiants.listeners.PlayerInteractListener;
import com.andrei1058.skygiants.listeners.PlayerJoinListener;
import com.andrei1058.skygiants.listeners.PlayerLoginListener;
import com.andrei1058.skygiants.listeners.PlayerPickupItemListener;
import com.andrei1058.skygiants.listeners.PlayerQuitListener;
import com.andrei1058.skygiants.listeners.PlayerRespawnListener;
import com.andrei1058.skygiants.listeners.PrepareItemCraftListener;
import com.andrei1058.skygiants.listeners.ServerPingListener;
import com.andrei1058.skygiants.listeners.WeatherChangeListener;
import com.andrei1058.skygiants.locations.Region;
import com.andrei1058.skygiants.protocolLib.PotionEfect;
import com.andrei1058.skygiants.utils.Database;
import com.andrei1058.skygiants.utils.Updater;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import java.util.ArrayList;
import java.util.HashMap;
import net.milkbowl.vault.chat.Chat;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

public class Main extends JavaPlugin {
   public static ProtocolManager protocolLib;
   public static Main plugin;
   public static NMS nmsH;
   public static String Language;
   public static Boolean MAINTENANCE;
   public static Boolean miniSG;
   public static boolean Debug = false;
   public static Boolean DatabaseB = false;
   public static String choosenMap;
   public static String sbtitle;
   public static ArrayList players = new ArrayList();
   public static ArrayList spectators = new ArrayList();
   public static Boolean allowSpectate = false;
   public static ArrayList magentaPlayers = new ArrayList();
   public static ArrayList goldPlayers = new ArrayList();
   public static ArrayList greenPlayers = new ArrayList();
   public static ArrayList bluePlayers = new ArrayList();
   public static ArrayList aliveTeams = new ArrayList();
   public static HashMap money = new HashMap();
   public static HashMap kills = new HashMap();
   public static HashMap beastsSlain = new HashMap();
   public static HashMap deaths = new HashMap();
   public static HashMap goldearnt = new HashMap();
   public static ArrayList gamesplayed = new ArrayList();
   public static ArrayList victories = new ArrayList();
   public static ArrayList lookingAtShop = new ArrayList();
   public static HashMap respawningVoid = new HashMap();
   public static HashMap respawningKill = new HashMap();
   public static ArrayList recall = new ArrayList();
   public static ArrayList respawning = new ArrayList();
   public static HashMap respOwn = new HashMap();
   public static ArrayList hasTeam = new ArrayList();
   public static Region GoldVillagerRegion;
   public static Region MagentaVillagerRegion;
   public static Region GreenVillagerRegion;
   public static Region BlueVillagerRegion;
   public static Region GoldGiantRegion;
   public static Region MagentaGiantRegion;
   public static Region GreenGiantRegion;
   public static Region BlueGiantRegion;
   public static Region BeastRegion;
   public static Region MiddleRegion;
   public static Team SbMagenta;
   public static Team SbGold;
   public static Team SbGreen;
   public static Team SbBlue;
   public static String PREFIX;
   public static int LobbyCountdown = 300;
   public static int GameCountdown = 1800;
   public static long BeastCountDown = 180000L;
   public static int RestartCountdown = 12;
   public static int WarmupCountdown = 90;
   public static int GiantHealth = 850;
   public static int MaxInTeam = 6;
   public static Boolean warmup = true;
   public static GameState STATUS;
   public static Boolean beastSpawned = false;
   public static String beastType = null;
   public static int BeastHealth = 350;
   public static Boolean loaded = false;
   public static ArrayList PlacedBlocks = new ArrayList();
   public static HashMap respBeac = new HashMap();
   public static Boolean goldHit = false;
   public static Boolean magentaHit = false;
   public static Boolean greenHit = false;
   public static Boolean blueHit = false;
   public static ArrayList cannotHitGiant = new ArrayList();
   public static ArrayList voted = new ArrayList();
   public static Boolean doubleDamage = false;
   public static String nmsver;
   public static ScoreboardManager sbmanager;
   public static Scoreboard sb;
   public static Objective obj;
   public static Boolean WINNER = false;
   public static long WarmupMilis = 90000L;
   public static long GameMilis;
   public static Boolean updateAvailable;
   public static String newVersion = null;
   public static Chat chat = null;
   public static Boolean vaultHook = false;
   public static HashMap mapsVoting = new HashMap();

   public void onEnable() {
      loadConfig0();
      nmsver = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];

      try {
         Class var1 = Class.forName("com.andrei1058.skygiants.nms." + nmsver + ".Main");
         if (NMS.class.isAssignableFrom(var1)) {
            nmsH = (NMS)var1.getConstructor().newInstance();
         }
      } catch (Exception var2) {
         this.getLogger().severe("Could not find support for this Spigot version.");
         this.setEnabled(false);
         return;
      }

      this.getLogger().info("Loading support for " + nmsver);
      plugin = this;
      protocolLib = ProtocolLibrary.getProtocolManager();
      Settings.setupConfig();
      Messages.setupMessages();
      Database.setupDatabase();
      ArenaCfg.registerArenas();
      this.getCommand("sgs").setExecutor(new SetupCmds());
      this.getCommand("leave").setExecutor(new Leave());
      this.getCommand("givegold").setExecutor(new GiveGold());
      this.getCommand("recall").setExecutor(new Recall());
      this.getCommand("vote").setExecutor(new Vote());
      this.getCommand("team").setExecutor(new com.andrei1058.skygiants.commands.Team());
      PluginManager var3 = Bukkit.getPluginManager();
      var3.registerEvents(new PlayerJoinListener(), this);
      var3.registerEvents(new PlayerLoginListener(), this);
      var3.registerEvents(new ServerPingListener(), this);
      var3.registerEvents(new PlayerQuitListener(), this);
      var3.registerEvents(new BlockBreakListener(), this);
      var3.registerEvents(new BlockPlaceListener(), this);
      var3.registerEvents(new EntityExplodeListener(), this);
      var3.registerEvents(new EntityDamageListener(), this);
      var3.registerEvents(new EntityDeathListener(), this);
      var3.registerEvents(new EntityDamageByEntityListener(), this);
      var3.registerEvents(new PlayerDeathListener(), this);
      var3.registerEvents(new PlayerRespawnListener(), this);
      var3.registerEvents(new CreatureSpawnListener(), this);
      var3.registerEvents(new FoodLevelChangeListener(), this);
      var3.registerEvents(new AsyncPlaterChatListener(), this);
      var3.registerEvents(new PlayerInteractListener(), this);
      var3.registerEvents(new PlayerInteractEntityListener(), this);
      var3.registerEvents(new InventoryClickListener(), this);
      var3.registerEvents(new PlayerPickupItemListener(), this);
      var3.registerEvents(new PlayerDropItemListener(), this);
      var3.registerEvents(new PrepareItemCraftListener(), this);
      var3.registerEvents(new WeatherChangeListener(), this);
      nmsH.registerGiant();
      nmsH.registerVillager();
      nmsH.registerBeast();
      plugin.getServer().getMessenger().registerOutgoingPluginChannel(plugin, "BungeeCord");
      sbmanager = Bukkit.getScoreboardManager();
      Updater.checkUpdates();
      updateAvailable = false;
      PotionEfect.registerPotionEffects();
      this.setupChat();
   }

   public void onDisable() {
      if (choosenMap != null) {
         Bukkit.unloadWorld(choosenMap, true);
      }

   }

   private boolean setupChat() {
      if (this.getServer().getPluginManager().getPlugin("Vault") == null) {
         return false;
      } else {
         RegisteredServiceProvider var1 = this.getServer().getServicesManager().getRegistration(Chat.class);
         chat = (Chat)var1.getProvider();
         vaultHook = true;
         plugin.getLogger().info("Loaded Vault support!");
         return chat != null;
      }
   }
}
