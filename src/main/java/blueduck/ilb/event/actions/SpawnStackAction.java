package blueduck.ilb.event.actions;

import blueduck.ilb.event.EventAction;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;

import java.util.ArrayList;

public class SpawnStackAction extends EventAction {

      public ResourceLocation entityResLoc;
      public int quantity;

      public SpawnStackAction(String ent, int quant) {
            entityResLoc = ResourceLocation.parse(ent);
            quantity = quant;
      }

      public void execute(ServerLevel level, BlockPos pos, Entity entity) {
            super.execute(level, pos, entity);
            EntityType entityFromString = level.registryAccess().registryOrThrow(Registries.ENTITY_TYPE).get(entityResLoc);
            ArrayList<Entity> entList = new ArrayList<Entity>();
            for (int i = 0; i < quantity; i++) {
                  Entity entToSpawn = entityFromString.create(level);
                  entToSpawn.moveTo(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
                  level.addFreshEntityWithPassengers(entToSpawn);
                  entList.add(entToSpawn);
            }
            for (int i = 0; i < entList.size() - 1; i++) {
                  entList.get(i).startRiding(entList.get(i + 1));
            }

      }

}
