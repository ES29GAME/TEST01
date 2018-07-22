package com.andrei1058.skygiants.nms.v1_10_R1.beasts;

import com.andrei1058.skygiants.Main;
import com.andrei1058.skygiants.configuration.Messages;
import java.lang.reflect.Field;
import net.minecraft.server.v1_10_R1.Entity;
import net.minecraft.server.v1_10_R1.EntityHorse;
import net.minecraft.server.v1_10_R1.EntityHuman;
import net.minecraft.server.v1_10_R1.EnumHorseType;
import net.minecraft.server.v1_10_R1.GenericAttributes;
import net.minecraft.server.v1_10_R1.PathfinderGoalHurtByTarget;
import net.minecraft.server.v1_10_R1.PathfinderGoalInteract;
import net.minecraft.server.v1_10_R1.PathfinderGoalLookAtPlayer;
import net.minecraft.server.v1_10_R1.PathfinderGoalMoveTowardsRestriction;
import net.minecraft.server.v1_10_R1.PathfinderGoalRandomStroll;
import net.minecraft.server.v1_10_R1.PathfinderGoalSelector;
import net.minecraft.server.v1_10_R1.World;
import net.minecraft.server.v1_10_R1.WorldServer;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_10_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_10_R1.entity.CraftLivingEntity;
import org.bukkit.craftbukkit.v1_10_R1.util.UnsafeList;
import org.bukkit.entity.Horse;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

public class HorseNMS extends EntityHorse {
   public HorseNMS(World var1) {
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
   }

   public void move(double var1, double var3, double var5) {
   }

   public void collide(Entity var1) {
   }

   public void g(double var1, double var3, double var5) {
   }

   public static Horse spawnHorse(Location var0) {
      WorldServer var1 = ((CraftWorld)var0.getWorld()).getHandle();
      HorseNMS var2 = new HorseNMS(var1);
      var2.setType(EnumHorseType.HORSE);
      var2.setTame(false);
      var2.getAttributeInstance(GenericAttributes.maxHealth).setValue((double)Main.BeastHealth);
      var2.setLocation(var0.getX(), var0.getY(), var0.getZ(), var0.getYaw(), var0.getPitch());
      ((CraftLivingEntity)var2.getBukkitEntity()).setRemoveWhenFarAway(false);
      var1.addEntity(var2, SpawnReason.CUSTOM);
      var2.setCustomName(Messages.getMsg().getString("centaur").replace('&', '\u00a7'));
      Main.beastType = "CENTAUR";
      var2.setHealth((float)Main.BeastHealth);
      return (Horse)var2.getBukkitEntity();
   }
}
