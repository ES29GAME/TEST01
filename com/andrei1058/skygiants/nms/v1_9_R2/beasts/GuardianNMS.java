package com.andrei1058.skygiants.nms.v1_9_R2.beasts;

import com.andrei1058.skygiants.Main;
import com.andrei1058.skygiants.configuration.Messages;
import java.lang.reflect.Field;
import net.minecraft.server.v1_9_R2.Entity;
import net.minecraft.server.v1_9_R2.EntityGuardian;
import net.minecraft.server.v1_9_R2.EntityHuman;
import net.minecraft.server.v1_9_R2.GenericAttributes;
import net.minecraft.server.v1_9_R2.PathfinderGoalHurtByTarget;
import net.minecraft.server.v1_9_R2.PathfinderGoalInteract;
import net.minecraft.server.v1_9_R2.PathfinderGoalLookAtPlayer;
import net.minecraft.server.v1_9_R2.PathfinderGoalMeleeAttack;
import net.minecraft.server.v1_9_R2.PathfinderGoalMoveTowardsRestriction;
import net.minecraft.server.v1_9_R2.PathfinderGoalNearestAttackableTarget;
import net.minecraft.server.v1_9_R2.PathfinderGoalRandomStroll;
import net.minecraft.server.v1_9_R2.PathfinderGoalSelector;
import net.minecraft.server.v1_9_R2.World;
import net.minecraft.server.v1_9_R2.WorldServer;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_9_R2.CraftWorld;
import org.bukkit.craftbukkit.v1_9_R2.entity.CraftLivingEntity;
import org.bukkit.craftbukkit.v1_9_R2.util.UnsafeList;
import org.bukkit.entity.Guardian;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

public class GuardianNMS extends EntityGuardian {
   public GuardianNMS(World var1) {
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

      this.goalSelector.a(5, new PathfinderGoalMoveTowardsRestriction(this, 0.6D));
      this.goalSelector.a(9, new PathfinderGoalInteract(this, EntityHuman.class, 3.0F, 1.0F));
      this.goalSelector.a(9, new PathfinderGoalRandomStroll(this, 0.6D));
      this.goalSelector.a(10, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 8.0F));
      this.goalSelector.a(2, new PathfinderGoalHurtByTarget(this, true, new Class[]{EntityHuman.class}));
      this.goalSelector.a(10, new PathfinderGoalMeleeAttack(this, 1.0D, true));
      this.targetSelector.a(5, new PathfinderGoalNearestAttackableTarget(this, EntityHuman.class, true));
   }

   public void move(double var1, double var3, double var5) {
   }

   public void collide(Entity var1) {
   }

   public void g(double var1, double var3, double var5) {
   }

   public static Guardian spawnGuardian(Location var0) {
      WorldServer var1 = ((CraftWorld)var0.getWorld()).getHandle();
      GuardianNMS var2 = new GuardianNMS(var1);
      var2.setElder(true);
      var2.getAttributeInstance(GenericAttributes.maxHealth).setValue(350.0D);
      var2.setLocation(var0.getX(), var0.getY(), var0.getZ(), var0.getYaw(), var0.getPitch());
      ((CraftLivingEntity)var2.getBukkitEntity()).setRemoveWhenFarAway(false);
      var1.addEntity(var2, SpawnReason.CUSTOM);
      var2.setCustomNameVisible(false);
      var2.setCustomName(Messages.getMsg().getString("hydros").replace('&', '\u00a7'));
      var2.setHealth((float)Main.BeastHealth);
      Main.beastSpawned = true;
      Main.beastType = "GUARDIAN";
      return (Guardian)var2.getBukkitEntity();
   }
}
