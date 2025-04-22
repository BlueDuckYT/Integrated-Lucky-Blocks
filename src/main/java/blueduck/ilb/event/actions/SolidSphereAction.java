package blueduck.ilb.event.actions;

import blueduck.ilb.event.EventAction;
import blueduck.ilb.util.ShapeUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.Block;

import java.util.List;

public class SolidSphereAction extends EventAction {

      public ResourceLocation blockResLoc;
      int dist;
      boolean replace;


      public SolidSphereAction(String block, int range, boolean replaceBlocks) {
            blockResLoc = ResourceLocation.parse(block);
            dist = range;
            replace = replaceBlocks;
      }

      public void execute(ServerLevel level, BlockPos pos, Entity entity) {
            super.execute(level, pos, entity);
            Block block = level.registryAccess().registryOrThrow(Registries.BLOCK).get(blockResLoc);
            List<BlockPos> points = ShapeUtil.generateSolidSphere((float) dist);
            for (int i = 0; i <= points.size(); i++) {
                  if (replace || level.getBlockState(entity.blockPosition().offset(points.get(i).getX() - dist/2, points.get(i).getY() - dist/2, points.get(i).getZ() - dist/2)).canBeReplaced()) {
                        level.setBlock(entity.blockPosition().offset(points.get(i)), block.defaultBlockState(), 2);
                  }
            }
      }

}
