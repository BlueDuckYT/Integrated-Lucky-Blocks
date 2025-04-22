package blueduck.ilb.event.actions;

import blueduck.ilb.event.EventAction;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.Blocks;

public class HerobrineTotemAction extends EventAction {

      public HerobrineTotemAction() {

      }

      public void execute(ServerLevel level, BlockPos pos, Entity entity) {
            super.execute(level, pos, entity);

            for (int i = 0; i < 3; i++) {
                  for (int j = 0; j < 3; j++) {
                        level.setBlock(pos.offset(i-1,0,j-1), Blocks.GOLD_BLOCK.defaultBlockState(), 2);
                  }
            }
            level.setBlock(pos.above(1), Blocks.NETHERRACK.defaultBlockState(), 2);
            level.setBlock(pos.above(2), Blocks.NETHERRACK.defaultBlockState(), 2);

            level.setBlock(pos.above().east().north(), Blocks.REDSTONE_TORCH.defaultBlockState(), 2);
            level.setBlock(pos.above().north().west(), Blocks.REDSTONE_TORCH.defaultBlockState(), 2);
            level.setBlock(pos.above().west().south(), Blocks.REDSTONE_TORCH.defaultBlockState(), 2);
            level.setBlock(pos.above().south().east(), Blocks.REDSTONE_TORCH.defaultBlockState(), 2);
      }

}
