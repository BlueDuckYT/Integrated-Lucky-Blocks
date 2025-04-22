package blueduck.ilb.event.actions;

import blueduck.ilb.event.EventAction;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;

public class ItemAction extends EventAction {

      public ResourceLocation itemResLoc;
      public int quantity;
      public String customName = null;

      public ItemAction(String ent, int quant) {
            itemResLoc = ResourceLocation.parse(ent);
            quantity = quant;
      }


      public void execute(ServerLevel level, BlockPos pos, Entity entity) {
            super.execute(level, pos, entity);
            ItemStack stack = new ItemStack(level.registryAccess().registryOrThrow(Registries.ITEM).get(itemResLoc), quantity);
            Entity entToSpawn = new ItemEntity(level, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, stack);
            entToSpawn.moveTo(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5);
            entToSpawn.setDeltaMovement(0.0D, 0.3D, 0.0D);
            level.addFreshEntityWithPassengers(entToSpawn);
      }

}
