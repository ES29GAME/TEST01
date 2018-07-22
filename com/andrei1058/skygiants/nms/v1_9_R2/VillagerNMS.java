package com.andrei1058.skygiants.nms.v1_9_R2;

import com.andrei1058.skygiants.configuration.Messages;
import java.lang.reflect.Field;
import net.minecraft.server.v1_9_R2.DamageSource;
import net.minecraft.server.v1_9_R2.Entity;
import net.minecraft.server.v1_9_R2.EntityHuman;
import net.minecraft.server.v1_9_R2.EntityVillager;
import net.minecraft.server.v1_9_R2.PathfinderGoalFloat;
import net.minecraft.server.v1_9_R2.PathfinderGoalInteract;
import net.minecraft.server.v1_9_R2.PathfinderGoalLookAtPlayer;
import net.minecraft.server.v1_9_R2.PathfinderGoalMoveTowardsRestriction;
import net.minecraft.server.v1_9_R2.PathfinderGoalRandomStroll;
import net.minecraft.server.v1_9_R2.PathfinderGoalSelector;
import net.minecraft.server.v1_9_R2.World;
import net.minecraft.server.v1_9_R2.WorldServer;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_9_R2.CraftWorld;
import org.bukkit.craftbukkit.v1_9_R2.entity.CraftLivingEntity;
import org.bukkit.craftbukkit.v1_9_R2.util.UnsafeList;
import org.bukkit.entity.Villager;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

public class VillagerNMS extends EntityVillager {
   public VillagerNMS(World var1) {
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
   }

   public void move(double var1, double var3, double var5) {
   }

   public void collide(Entity var1) {
   }

   public boolean damageEntity(DamageSource var1, float var2) {
      return false;
   }

   public void g(double var1, double var3, double var5) {
   }

   public static Villager spawnGoldVillager(Location var0) {
      WorldServer var1 = ((CraftWorld)var0.getWorld()).getHandle();
      VillagerNMS var2 = new VillagerNMS(var1);
      var2.setLocation(var0.getX(), var0.getY(), var0.getZ(), var0.getYaw(), var0.getPitch());
      ((CraftLivingEntity)var2.getBukkitEntity()).setRemoveWhenFarAway(false);
      var2.setCustomName(Messages.getMsg().getString("merchant").replace('&', '\u00a7'));
      var2.setCustomNameVisible(true);
      var1.addEntity(var2, SpawnReason.CUSTOM);
      return (Villager)var2.getBukkitEntity();
   }

   public static Villager spawnMagentaVillager(Location var0) {
      WorldServer var1 = ((CraftWorld)var0.getWorld()).getHandle();
      VillagerNMS var2 = new VillagerNMS(var1);
      var2.setLocation(var0.getX(), var0.getY(), var0.getZ(), var0.getYaw(), var0.getPitch());
      ((CraftLivingEntity)var2.getBukkitEntity()).setRemoveWhenFarAway(false);
      var2.setCustomName(Messages.getMsg().getString("merchant").replace('&', '\u00a7'));
      var2.setCustomNameVisible(true);
      var1.addEntity(var2, SpawnReason.CUSTOM);
      return (Villager)var2.getBukkitEntity();
   }

   public static Villager spawnGreenVillager(Location var0) {
      WorldServer var1 = ((CraftWorld)var0.getWorld()).getHandle();
      VillagerNMS var2 = new VillagerNMS(var1);
      var2.setLocation(var0.getX(), var0.getY(), var0.getZ(), var0.getYaw(), var0.getPitch());
      ((CraftLivingEntity)var2.getBukkitEntity()).setRemoveWhenFarAway(false);
      var2.setCustomName(Messages.getMsg().getString("merchant").replace('&', '\u00a7'));
      var2.setCustomNameVisible(true);
      var1.addEntity(var2, SpawnReason.CUSTOM);
      return (Villager)var2.getBukkitEntity();
   }

   public static Villager spawnBlueVillager(Location var0) {
      WorldServer var1 = ((CraftWorld)var0.getWorld()).getHandle();
      VillagerNMS var2 = new VillagerNMS(var1);
      var2.setLocation(var0.getX(), var0.getY(), var0.getZ(), var0.getYaw(), var0.getPitch());
      ((CraftLivingEntity)var2.getBukkitEntity()).setRemoveWhenFarAway(false);
      var2.setCustomName(Messages.getMsg().getString("merchant").replace('&', '\u00a7'));
      var2.setCustomNameVisible(true);
      var1.addEntity(var2, SpawnReason.CUSTOM);
      return (Villager)var2.getBukkitEntity();
   }
}
