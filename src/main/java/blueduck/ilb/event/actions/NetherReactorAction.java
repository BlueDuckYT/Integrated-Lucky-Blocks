package blueduck.ilb.event.actions;

import blueduck.ilb.event.EventAction;
import blueduck.ilb.registry.BlockRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class NetherReactorAction extends EventAction {

      public NetherReactorAction() {

      }

      public void execute(ServerLevel level, BlockPos pos, Entity entity) {
            super.execute(level, pos, entity);

            for (int k = 0; k < 2; k++) {
                  level.setBlock(pos.above(k*2), Blocks.COBBLESTONE.defaultBlockState(), 2);
                  level.setBlock(pos.east().above(k*2), Blocks.COBBLESTONE.defaultBlockState(), 2);
                  level.setBlock(pos.west().above(k*2), Blocks.COBBLESTONE.defaultBlockState(), 2);
                  level.setBlock(pos.north().above(k*2), Blocks.COBBLESTONE.defaultBlockState(), 2);
                  level.setBlock(pos.south().above(k*2), Blocks.COBBLESTONE.defaultBlockState(), 2);
            }
            level.setBlock(pos.east().north(), Blocks.GOLD_BLOCK.defaultBlockState(), 2);
            level.setBlock(pos.west().south(), Blocks.GOLD_BLOCK.defaultBlockState(), 2);
            level.setBlock(pos.north().west(), Blocks.GOLD_BLOCK.defaultBlockState(), 2);
            level.setBlock(pos.south().east(), Blocks.GOLD_BLOCK.defaultBlockState(), 2);

            level.setBlock(pos.east().north().above(), Blocks.COBBLESTONE.defaultBlockState(), 2);
            level.setBlock(pos.west().south().above(), Blocks.COBBLESTONE.defaultBlockState(), 2);
            level.setBlock(pos.north().west().above(), Blocks.COBBLESTONE.defaultBlockState(), 2);
            level.setBlock(pos.south().east().above(), Blocks.COBBLESTONE.defaultBlockState(), 2);

            level.setBlock(pos.above(), BlockRegistry.LUCKY_BLOCK.get().defaultBlockState(), 2);
      }

}
