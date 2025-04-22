package blueduck.ilb.event.actions;

import blueduck.ilb.event.EventAction;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.AABB;

import java.util.List;
import java.util.Objects;

public class EffectAction extends EventAction {

      public ResourceLocation effectResLoc;
      public int amp;
      public int dur;
      public boolean showParticles;
      public double effectRange;
      public String mobToEffect;

      public EffectAction(String effect, int amplifier, int duration, boolean show, double range) {
            effectResLoc = ResourceLocation.parse(effect);
            amp = amplifier;
            dur = duration;
            showParticles = show;
            effectRange = range;
            mobToEffect = "all";
      }
      public EffectAction(String effect, int amplifier, int duration, boolean show, double range, String mob) {
            effectResLoc = ResourceLocation.parse(effect);
            amp = amplifier;
            dur = duration;
            showParticles = show;
            effectRange = range;
            mobToEffect = mob;
      }


      public void execute(ServerLevel level, BlockPos pos, Entity entity) {
            super.execute(level, pos, entity);
            MobEffectInstance effect = new MobEffectInstance(level.registryAccess().registryOrThrow(Registries.MOB_EFFECT).get(effectResLoc), dur, amp, showParticles, showParticles);
            AABB aabb = (new AABB(pos)).inflate(effectRange);
            List<LivingEntity> nearbyEntities = level.getEntitiesOfClass(LivingEntity.class, aabb);
            for (LivingEntity ent : nearbyEntities) {
                  if (Objects.equals(mobToEffect, "all") || level.registryAccess().registryOrThrow(Registries.ENTITY_TYPE).get(ResourceLocation.parse(mobToEffect)).equals(ent.getType())) {
                        ent.addEffect(effect);
                  }
            }
      }

}
