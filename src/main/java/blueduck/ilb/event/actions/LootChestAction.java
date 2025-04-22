package blueduck.ilb.event.actions;

import blueduck.ilb.event.EventAction;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.entity.ChestBlockEntity;

public class LootChestAction extends EventAction {

      public ResourceLocation lootResLoc;
      public boolean trapped;


      public LootChestAction(String loottable) {
            lootResLoc = ResourceLocation.parse(loottable);
            trapped = false;
      }
      public LootChestAction(String loottable, boolean trappedChest) {
            lootResLoc = ResourceLocation.parse(loottable);
            trapped = trappedChest;
      }

      public void execute(ServerLevel level, BlockPos pos, Entity entity) {
            super.execute(level, pos, entity);
            level.setBlock(pos, (trapped ? Blocks.TRAPPED_CHEST : Blocks.CHEST).defaultBlockState(), 2);
            ChestBlockEntity.setLootTable(level, level.random, pos, lootResLoc);
      }

}
