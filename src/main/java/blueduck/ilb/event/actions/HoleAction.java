package blueduck.ilb.event.actions;

import blueduck.ilb.event.EventAction;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.Blocks;

public class HoleAction extends EventAction {

      int dist;
      int deep;


      public HoleAction(int range, int depth) {
            dist = range;
            deep = depth;
      }

      public void execute(ServerLevel level, BlockPos pos, Entity entity) {
            super.execute(level, pos, entity);
            for (int i = -dist; i <= dist; i++) {
                  for (int j = 2; j >= -deep; j--) {
                        for (int k = -dist; k <= dist; k++) {
                              level.setBlock(entity.blockPosition().offset(i,j,k), Blocks.AIR.defaultBlockState(), 2);

                        }
                  }
            }
      }

}
