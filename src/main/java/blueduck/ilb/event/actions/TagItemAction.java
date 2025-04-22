package blueduck.ilb.event.actions;

import blueduck.ilb.event.EventAction;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.Optional;

public class TagItemAction extends EventAction {

      public ResourceLocation itemResLoc;
      public int quantity;
      public int attempts;
      public boolean spread;

      public TagItemAction(String ent, int quant, int tries) {
            itemResLoc = ResourceLocation.parse(ent);
            quantity = quant;
            attempts = tries;
            spread = tries > 1;

      }

      public TagItemAction(String ent, int quant, int tries, boolean shouldSpread) {
            itemResLoc = ResourceLocation.parse(ent);
            quantity = quant;
            attempts = tries;
            spread = shouldSpread;
      }


      public void execute(ServerLevel level, BlockPos pos, Entity entity) {
            super.execute(level, pos, entity);
            for (int i = 0; i < attempts; i++) {
                  Optional<Item> optional = BuiltInRegistries.ITEM.getTag(TagKey.create(Registries.ITEM, itemResLoc)).flatMap((p_224980_) -> {
                        return p_224980_.getRandomElement(level.random);
                  }).map(Holder::value);
                  ItemStack stack = new ItemStack(optional.get(), quantity);
                  Entity entToSpawn = new ItemEntity(level, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, stack);
                  entToSpawn.moveTo(pos.getX() + 0.5 + (spread ? level.random.nextDouble() - 0.5 : 0), pos.getY() + 0.5, pos.getZ() + 0.5 + (spread ? level.random.nextDouble() - 0.5 : 0));
                  entToSpawn.setDeltaMovement(0.0D, 0.3D, 0.0D);
                  level.addFreshEntityWithPassengers(entToSpawn);
            }
      }

}
