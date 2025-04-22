package blueduck.ilb.event.actions;

import blueduck.ilb.event.EventAction;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class CageAction extends EventAction {

      public ResourceLocation baseBlockResLoc;
      public ResourceLocation sideBlockResLoc;
      public ResourceLocation roofBlockResLoc;

      boolean shouldHaveRoof;


      public CageAction(boolean roof) {
            baseBlockResLoc = ResourceLocation.parse("minecraft:polished_deepslate");
            sideBlockResLoc = ResourceLocation.parse("minecraft:iron_bars");
            roofBlockResLoc = ResourceLocation.parse("minecraft:deepslate_tile_slab");
            shouldHaveRoof = roof;
      }

      public CageAction(String baseBlock, String sideBlock, String roofBlock, boolean roof) {
            baseBlockResLoc = ResourceLocation.parse(baseBlock);
            sideBlockResLoc = ResourceLocation.parse(sideBlock);
            roofBlockResLoc = ResourceLocation.parse(roofBlock);
            shouldHaveRoof = roof;
      }

      public void execute(ServerLevel level, BlockPos pos, Entity entity) {
            super.execute(level, pos, entity);
            Block baseblock = level.registryAccess().registryOrThrow(Registries.BLOCK).get(baseBlockResLoc);
            Block sideblock = level.registryAccess().registryOrThrow(Registries.BLOCK).get(sideBlockResLoc);
            Block roofblock = level.registryAccess().registryOrThrow(Registries.BLOCK).get(roofBlockResLoc);
            for (int i = -1; i <= 1; i++) {
                  for (int j = -1; j <= 1; j++) {
                        level.setBlock(entity.blockPosition().offset(i,-1, j), baseblock.defaultBlockState(), 2);
                  }
            }
            for (int i = -1; i <= 1; i++) {
                  for (int j = 0; j <= 3; j++) {
                        for (int k = -1; k <= 1; k++) {
                              if (((i == -1 || i == 1) || (k == -1 || k == 1)) || (shouldHaveRoof && j == 3)) {
                                    level.setBlock(entity.blockPosition().offset(i,j,k), j == 3 ? roofblock.defaultBlockState() : sideblock.defaultBlockState(), 2);
                              }
                        }
                  }
            }
            for (int j = 0; j <= 2; j++) {
                  level.setBlock(entity.blockPosition().offset(0,j,0), Blocks.AIR.defaultBlockState(), 2);
            }
      }

}
