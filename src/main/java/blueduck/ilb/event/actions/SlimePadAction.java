package blueduck.ilb.event.actions;

import blueduck.ilb.event.EventAction;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.Blocks;

public class SlimePadAction extends EventAction {

      public SlimePadAction() {

      }

      public void execute(ServerLevel level, BlockPos pos, Entity entity) {
            super.execute(level, pos, entity);
            //level.setBlock(pos.above(), Blocks.SLIME_BLOCK.defaultBlockState(), 2);
            for (int i = -1; i <= 1; i++) {
                  for (int j = -1; j <= 1; j++) {
                        if (level.getBlockState(pos.offset(i, 0, j)).canBeReplaced())
                              level.setBlock(pos.offset(i, 0, j), Blocks.SLIME_BLOCK.defaultBlockState(), 2);
                        }
                  }
            }
      }
