package blueduck.ilb.event.actions;

import blueduck.ilb.event.EventAction;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;

public class SpawnJockeyAction extends EventAction {

      public ResourceLocation entityResLoc;
      public ResourceLocation riderResLoc;
      public int quantity;
      public String customName = null;
      public String customName2 = null;

      public SpawnJockeyAction(String ent, String ent2, int quant) {
            entityResLoc = ResourceLocation.parse(ent);
            riderResLoc = ResourceLocation.parse(ent2);
            quantity = quant;
      }
      public SpawnJockeyAction(String ent, String ent2, int quant, String name, String name2) {
            entityResLoc = ResourceLocation.parse(ent);
            riderResLoc = ResourceLocation.parse(ent2);
            quantity = quant;
            customName = name;
            customName2 = name2;
      }

      public void execute(ServerLevel level, BlockPos pos, Entity entity) {
            super.execute(level, pos, entity);
            EntityType entityFromString = level.registryAccess().registryOrThrow(Registries.ENTITY_TYPE).get(entityResLoc);
            EntityType entity2FromString = level.registryAccess().registryOrThrow(Registries.ENTITY_TYPE).get(riderResLoc);
            for (int i = 0; i < quantity; i++) {
                  Entity entToSpawn = entityFromString.create(level);
                  entToSpawn.moveTo(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
                  if (customName != null) {
                        entToSpawn.setCustomName(Component.literal(customName));
                  }
                  level.addFreshEntityWithPassengers(entToSpawn);
                  //Rider
                  Entity entToSpawn2 = entity2FromString.create(level);
                  entToSpawn2.moveTo(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
                  if (customName2 != null) {
                        entToSpawn2.setCustomName(Component.literal(customName2));
                  }
                  level.addFreshEntityWithPassengers(entToSpawn2);
                  entToSpawn2.startRiding(entToSpawn);
            }
      }

}
