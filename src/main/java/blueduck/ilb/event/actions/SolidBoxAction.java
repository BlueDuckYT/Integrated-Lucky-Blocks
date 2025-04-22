package blueduck.ilb.event.actions;

import blueduck.ilb.event.EventAction;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.Block;

public class SolidBoxAction extends EventAction {

      public ResourceLocation blockResLoc;
      int dist;
      boolean player;


      public SolidBoxAction(String block, int range) {
            blockResLoc = ResourceLocation.parse(block);
            dist = range;
            player = true;
      }
      public SolidBoxAction(String block, int range, boolean playerDependent) {
            blockResLoc = ResourceLocation.parse(block);
            dist = range;
            player = playerDependent;
      }

      public void execute(ServerLevel level, BlockPos pos, Entity entity) {
            super.execute(level, pos, entity);
            Block block = level.registryAccess().registryOrThrow(Registries.BLOCK).get(blockResLoc);
            for (int i = -dist; i <= dist; i++) {
                  for (int j = -dist; j <= dist; j++) {
                        for (int k = -dist; k <= dist; k++) {
                              level.setBlock((player ? entity.blockPosition() : pos).offset(i,j,k), block.defaultBlockState(), 2);

                        }
                  }
            }
      }

}
