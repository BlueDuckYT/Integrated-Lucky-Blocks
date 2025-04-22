package blueduck.ilb.event.actions;

import blueduck.ilb.event.EventAction;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.Block;

public class EncaseInBoxAction extends EventAction {

      public ResourceLocation blockResLoc;
      int dist;


      public EncaseInBoxAction(String block, int range) {
            blockResLoc = ResourceLocation.parse(block);
            dist = range;
      }

      public void execute(ServerLevel level, BlockPos pos, Entity entity) {
            super.execute(level, pos, entity);
            Block block = level.registryAccess().registryOrThrow(Registries.BLOCK).get(blockResLoc);
            for (int i = -dist; i <= dist; i++) {
                  for (int j = -dist; j <= dist; j++) {
                        for (int k = -dist; k <= dist; k++) {
                              if ((i == -dist || i == dist) || ((k == -dist || k == dist) || (j == -dist || j == dist))) {
                                    level.setBlock(entity.blockPosition().offset(i,j,k), block.defaultBlockState(), 2);
                              }
                        }
                  }
            }
      }

}
