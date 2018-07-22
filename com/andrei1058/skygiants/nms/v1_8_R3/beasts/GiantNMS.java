package com.andrei1058.skygiants.nms.v1_8_R3.beasts;

import com.andrei1058.skygiants.Main;
import java.lang.reflect.Field;
import net.minecraft.server.v1_8_R3.Entity;
import net.minecraft.server.v1_8_R3.EntityGiantZombie;
import net.minecraft.server.v1_8_R3.EntityHuman;
import net.minecraft.server.v1_8_R3.EntityVillager;
import net.minecraft.server.v1_8_R3.GenericAttributes;
import net.minecraft.server.v1_8_R3.PathfinderGoalFloat;
import net.minecraft.server.v1_8_R3.PathfinderGoalHurtByTarget;
import net.minecraft.server.v1_8_R3.PathfinderGoalInteract;
import net.minecraft.server.v1_8_R3.PathfinderGoalLookAtPlayer;
import net.minecraft.server.v1_8_R3.PathfinderGoalMoveTowardsRestriction;
import net.minecraft.server.v1_8_R3.PathfinderGoalRandomStroll;
import net.minecraft.server.v1_8_R3.PathfinderGoalSelector;
import net.minecraft.server.v1_8_R3.World;
import net.minecraft.server.v1_8_R3.WorldServer;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftLivingEntity;
import org.bukkit.craftbukkit.v1_8_R3.util.UnsafeList;
import org.bukkit.entity.Giant;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

public class GiantNMS extends EntityGiantZombie {
   public GiantNMS(World var1) {
      super(var1);

      try {
         Field var2 = PathfinderGoalSelector.class.getDeclaredField("b");
         var2.setAccessible(true);
         Field var3 = PathfinderGoalSelector.class.getDeclaredField("c");
         var3.setAccessible(true);
         var2.set(this.goalSelector, new UnsafeList());
         var2.set(this.targetSelector, new UnsafeList());
         var3.set(this.goalSelector, new UnsafeList());
         var3.set(this.targetSelector, new UnsafeList());
      } catch (Exception var4) {
         ;
      }

      this.goalSelector.a(0, new PathfinderGoalFloat(this));
      this.goalSelector.a(5, new PathfinderGoalMoveTowardsRestriction(this, 0.6D));
      this.goalSelector.a(9, new PathfinderGoalInteract(this, EntityHuman.class, 3.0F, 1.0F));
      this.goalSelector.a(9, new PathfinderGoalInteract(this, EntityVillager.class, 5.0F, 0.02F));
      this.goalSelector.a(9, new PathfinderGoalRandomStroll(this, 0.6D));
      this.goalSelector.a(10, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 8.0F));
      this.goalSelector.a(2, new PathfinderGoalHurtByTarget(this, true, new Class[]{EntityHuman.class}));
   }

   public void move(double var1, double var3, double var5) {
   }

   public void collide(Entity var1) {
   }

   public void g(double var1, double var3, double var5) {
   }

   public static Giant spawnMagentaGiant(Location var0) {
      WorldServer var1 = ((CraftWorld)var0.getWorld()).getHandle();
      GiantNMS var2 = new GiantNMS(var1);
      var2.setLocation(var0.getX(), var0.getY(), var0.getZ(), var0.getYaw(), var0.getPitch());
      ((CraftLivingEntity)var2.getBukkitEntity()).setRemoveWhenFarAway(false);
      var1.addEntity(var2, SpawnReason.CUSTOM);
      var2.getAttributeInstance(GenericAttributes.maxHealth).setValue(850.0D);
      if (!Main.miniSG.booleanValue()) {
         var2.setHealth(850.0F);
      } else {
         var2.setHealth(510.0F);
      }

      com.andrei1058.skygiants.nms.v1_8_R3.Main.MagentaGiant = var2;
      Main.aliveTeams.add("MAGENTA");
      return (Giant)var2.getBukkitEntity();
   }

   public static Giant spawnOrangeGiant(Location var0) {
      WorldServer var1 = ((CraftWorld)var0.getWorld()).getHandle();
      GiantNMS var2 = new GiantNMS(var1);
      var2.setLocation(var0.getX(), var0.getY(), var0.getZ(), var0.getYaw(), var0.getPitch());
      ((CraftLivingEntity)var2.getBukkitEntity()).setRemoveWhenFarAway(false);
      var1.addEntity(var2, SpawnReason.CUSTOM);
      var2.getAttributeInstance(GenericAttributes.maxHealth).setValue(850.0D);
      if (!Main.miniSG.booleanValue()) {
         var2.setHealth(850.0F);
      } else {
         var2.setHealth(510.0F);
      }

      com.andrei1058.skygiants.nms.v1_8_R3.Main.GoldGiant = var2;
      Main.aliveTeams.add("GOLD");
      return (Giant)var2.getBukkitEntity();
   }

   public static Giant spawnBlueGiant(Location var0) {
      WorldServer var1 = ((CraftWorld)var0.getWorld()).getHandle();
      GiantNMS var2 = new GiantNMS(var1);
      var2.setLocation(var0.getX(), var0.getY(), var0.getZ(), var0.getYaw(), var0.getPitch());
      ((CraftLivingEntity)var2.getBukkitEntity()).setRemoveWhenFarAway(false);
      var1.addEntity(var2, SpawnReason.CUSTOM);
      var2.getAttributeInstance(GenericAttributes.maxHealth).setValue(850.0D);
      if (!Main.miniSG.booleanValue()) {
         var2.setHealth(850.0F);
      } else {
         var2.setHealth(510.0F);
      }

      com.andrei1058.skygiants.nms.v1_8_R3.Main.BlueGiant = var2;
      Main.aliveTeams.add("BLUE");
      return (Giant)var2.getBukkitEntity();
   }

   public static Giant spawnGreenGiant(Location var0) {
      WorldServer var1 = ((CraftWorld)var0.getWorld()).getHandle();
      GiantNMS var2 = new GiantNMS(var1);
      var2.setLocation(var0.getX(), var0.getY(), var0.getZ(), var0.getYaw(), var0.getPitch());
      ((CraftLivingEntity)var2.getBukkitEntity()).setRemoveWhenFarAway(false);
      var1.addEntity(var2, SpawnReason.CUSTOM);
      var2.getAttributeInstance(GenericAttributes.maxHealth).setValue(850.0D);
      if (!Main.miniSG.booleanValue()) {
         var2.setHealth(850.0F);
      } else {
         var2.setHealth(510.0F);
      }

      com.andrei1058.skygiants.nms.v1_8_R3.Main.GreenGiant = var2;
      Main.aliveTeams.add("GREEN");
      return (Giant)var2.getBukkitEntity();
   }
}
