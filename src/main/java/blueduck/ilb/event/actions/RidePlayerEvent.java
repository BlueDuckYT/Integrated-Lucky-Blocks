package blueduck.ilb.event.actions;

import blueduck.ilb.event.EventAction;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;

public class RidePlayerEvent extends EventAction {

      public ResourceLocation entityResLoc;
      boolean rideFlag = false;
      public String customName = null;

      public RidePlayerEvent(String ent, boolean ridePlayer) {
            entityResLoc = ResourceLocation.parse(ent);
            rideFlag = ridePlayer;
      }
      public RidePlayerEvent(String ent, boolean ridePlayer, String name) {
            entityResLoc = ResourceLocation.parse(ent);
            rideFlag = ridePlayer;
            customName = name;
      }

      public void execute(ServerLevel level, BlockPos pos, Entity entity) {
            super.execute(level, pos, entity);
            EntityType entityFromString = level.registryAccess().registryOrThrow(Registries.ENTITY_TYPE).get(entityResLoc);

            Entity entToSpawn = entityFromString.create(level);
            entToSpawn.moveTo(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
            if (customName != null) {
                  entToSpawn.setCustomName(Component.literal(customName));
            }
            level.addFreshEntityWithPassengers(entToSpawn);
            if (rideFlag) {
                  entToSpawn.startRiding(entity);
            }
            else {
                  entity.startRiding(entToSpawn);
            }

      }

}
