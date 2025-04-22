package blueduck.ilb.event.actions;

import blueduck.ilb.event.EventAction;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class ArenaAction extends EventAction {

      public ResourceLocation baseBlockResLoc;
      public ResourceLocation sideBlockResLoc;
      public ResourceLocation roofBlockResLoc;

      public ArenaAction() {
            baseBlockResLoc = ResourceLocation.parse("minecraft:polished_deepslate");
            sideBlockResLoc = ResourceLocation.parse("minecraft:iron_bars");
            roofBlockResLoc = ResourceLocation.parse("minecraft:deepslate_tile_slab");
      }

      public ArenaAction(String baseBlock, String sideBlock, String roofBlock) {
            baseBlockResLoc = ResourceLocation.parse(baseBlock);
            sideBlockResLoc = ResourceLocation.parse(sideBlock);
            roofBlockResLoc = ResourceLocation.parse(roofBlock);
      }

      public void execute(ServerLevel level, BlockPos pos, Entity entity) {
            super.execute(level, pos, entity);
            Block baseblock = level.registryAccess().registryOrThrow(Registries.BLOCK).get(baseBlockResLoc);
            Block sideblock = level.registryAccess().registryOrThrow(Registries.BLOCK).get(sideBlockResLoc);
            Block roofblock = level.registryAccess().registryOrThrow(Registries.BLOCK).get(roofBlockResLoc);
            for (int i = -5; i <= 5; i++) {
                  for (int j = -5; j <= 5; j++) {
                        level.setBlock(entity.blockPosition().offset(i,-1, j), baseblock.defaultBlockState(), 2);
                  }
            }
            for (int i = -5; i <= 5; i++) {
                  for (int j = 0; j <= 4; j++) {
                        for (int k = -5; k <= 5; k++) {
                              if ((i == -5 || i == 5) || (k == -5 || k == 5)) {
                                    level.setBlock(entity.blockPosition().offset(i,j,k), j == 4 ? roofblock.defaultBlockState() : sideblock.defaultBlockState(), 2);
                              }
                        }
                  }
            }
            for (int i = -4; i <= 4; i++) {
                  for (int j = 0; j <= 4; j++) {
                        for (int k = -4; k <= 4; k++) {
                              level.setBlock(entity.blockPosition().offset(i,j,k), Blocks.AIR.defaultBlockState(), 2);

                        }
                  }
            }
      }

}
