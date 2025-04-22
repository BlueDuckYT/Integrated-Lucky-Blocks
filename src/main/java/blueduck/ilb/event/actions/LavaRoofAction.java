package blueduck.ilb.event.actions;

import blueduck.ilb.event.EventAction;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.Blocks;

public class LavaRoofAction extends EventAction {

      int dist = 0;
      int up = 0;
      boolean placeFlag;
      ResourceLocation fluidLoc;

      public LavaRoofAction(int range, int height, boolean playerDependent) {
            dist = range;
            up = height;
            placeFlag = playerDependent;
            fluidLoc = null;
      }

      public LavaRoofAction(int range, int height, boolean playerDependent, String fluid) {
            dist = range;
            up = height;
            placeFlag = playerDependent;
            fluidLoc = ResourceLocation.parse(fluid);
      }

      public void execute(ServerLevel level, BlockPos pos, Entity entity) {
            super.execute(level, pos, entity);
            if (placeFlag) {
                  pos = entity.getOnPos().offset(0, 1, 0);
            }
            for (int i = -dist; i <= dist; i++) {
                  for (int j = -dist; j <= dist; j++) {
                        BlockPos newPos = pos.offset(i, up, j);
                        if (level.getBlockState(newPos).canBeReplaced())
                              level.setBlock(newPos, (fluidLoc == null ? Blocks.LAVA : level.registryAccess().registryOrThrow(Registries.BLOCK).get(fluidLoc)).defaultBlockState(), 2);
                        }
                  }
            }
      }
