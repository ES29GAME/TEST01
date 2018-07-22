package com.andrei1058.skygiants.nms.v1_10_R1.beasts;

import com.andrei1058.skygiants.Main;
import com.andrei1058.skygiants.configuration.Messages;
import java.lang.reflect.Field;
import java.util.HashSet;
import net.minecraft.server.v1_10_R1.Entity;
import net.minecraft.server.v1_10_R1.EntityHuman;
import net.minecraft.server.v1_10_R1.EntityIronGolem;
import net.minecraft.server.v1_10_R1.GenericAttributes;
import net.minecraft.server.v1_10_R1.PathfinderGoalHurtByTarget;
import net.minecraft.server.v1_10_R1.PathfinderGoalInteract;
import net.minecraft.server.v1_10_R1.PathfinderGoalLookAtPlayer;
import net.minecraft.server.v1_10_R1.PathfinderGoalMeleeAttack;
import net.minecraft.server.v1_10_R1.PathfinderGoalMoveTowardsRestriction;
import net.minecraft.server.v1_10_R1.PathfinderGoalRandomStroll;
import net.minecraft.server.v1_10_R1.PathfinderGoalSelector;
import net.minecraft.server.v1_10_R1.World;
import net.minecraft.server.v1_10_R1.WorldServer;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_10_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_10_R1.entity.CraftLivingEntity;
import org.bukkit.entity.IronGolem;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

public class GolemNMS extends EntityIronGolem {
   public GolemNMS(World var1) {
      super(var1);

      try {
         Field var2 = PathfinderGoalSelector.class.getDeclaredField("b");
         var2.setAccessible(true);
         Field var3 = PathfinderGoalSelector.class.getDeclaredField("c");
         var3.setAccessible(true);
         var2.set(this.goalSelector, new HashSet());
         var2.set(this.targetSelector, new HashSet());
         var3.set(this.goalSelector, new HashSet());
         var3.set(this.targetSelector, new HashSet());
      } catch (Exception var4) {
         var4.printStackTrace();
      }

      this.goalSelector.a(5, new PathfinderGoalMoveTowardsRestriction(this, 0.6D));
      this.goalSelector.a(9, new PathfinderGoalInteract(this, EntityHuman.class, 3.0F, 1.0F));
      this.goalSelector.a(9, new PathfinderGoalRandomStroll(this, 0.6D));
      this.goalSelector.a(10, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 8.0F));
      this.goalSelector.a(2, new PathfinderGoalHurtByTarget(this, true, new Class[]{EntityHuman.class}));
      this.goalSelector.a(10, new PathfinderGoalMeleeAttack(this, 2.0D, true));
   }

   public void move(double var1, double var3, double var5) {
   }

   public void collide(Entity var1) {
   }

   public void g(double var1, double var3, double var5) {
   }

   public void initAttributes() {
      super.initAttributes();
      this.getAttributeInstance(GenericAttributes.maxHealth).setValue((double)Main.BeastHealth);
   }

   public static IronGolem spawnGolem(Location var0) {
      WorldServer var1 = ((CraftWorld)var0.getWorld()).getHandle();
      GolemNMS var2 = new GolemNMS(var1);
      var2.setLocation(var0.getX(), var0.getY(), var0.getZ(), var0.getYaw(), var0.getPitch());
      ((CraftLivingEntity)var2.getBukkitEntity()).setRemoveWhenFarAway(false);
      var1.addEntity(var2, SpawnReason.CUSTOM);
      var2.setCustomName(Messages.getMsg().getString("talos").replace('&', '\u00a7'));
      var2.setCustomNameVisible(false);
      var2.setPlayerCreated(false);
      var2.setHealth((float)Main.BeastHealth);
      Main.beastSpawned = true;
      Main.beastType = "IRON_GOLEM";
      return (IronGolem)var2.getBukkitEntity();
   }
}
