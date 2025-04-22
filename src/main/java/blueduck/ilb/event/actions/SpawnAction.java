package blueduck.ilb.event.actions;

import blueduck.ilb.event.EventAction;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;

public class SpawnAction extends EventAction {

      public ResourceLocation entityResLoc;
      public int quantity;
      public String customName = null;

      public SpawnAction(String ent, int quant) {
            entityResLoc = ResourceLocation.parse(ent);
            quantity = quant;
      }
      public SpawnAction(String ent, int quant, String name) {
            entityResLoc = ResourceLocation.parse(ent);
            quantity = quant;
            customName = name;
      }

      public void execute(ServerLevel level, BlockPos pos, Entity entity) {
            super.execute(level, pos, entity);
            EntityType entityFromString = level.registryAccess().registryOrThrow(Registries.ENTITY_TYPE).get(entityResLoc);
            for (int i = 0; i < quantity; i++) {
                  Entity entToSpawn = entityFromString.create(level);
                  entToSpawn.moveTo(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
                  if (customName != null) {
                        entToSpawn.setCustomName(Component.literal(customName));
                  }
                  level.addFreshEntityWithPassengers(entToSpawn);
            }
      }

}
