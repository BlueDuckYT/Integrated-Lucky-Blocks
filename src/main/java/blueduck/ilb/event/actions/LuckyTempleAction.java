package blueduck.ilb.event.actions;

import blueduck.ilb.event.EventAction;
import blueduck.ilb.registry.BlockRegistry;
import blueduck.ilb.worldgen.TemplePalette;
import blueduck.ilb.worldgen.TemplePalettes;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class LuckyTempleAction extends EventAction {

    public boolean middle;
    public boolean corner;

    public LuckyTempleAction(boolean middleBlock, boolean cornerBlocks) {
        middle = middleBlock;
        corner = cornerBlocks;
    }

    public void execute(ServerLevel level, BlockPos pos, Entity entity) {
        TemplePalette palette = TemplePalettes.getPalette(level);

        Block baseBlock = palette.getBase(level);
        Block pillarBlock = palette.getPillar(level);
        Block slabBlock = palette.getSlab(level);


        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < 4; k++) {
                    if (!level.getBlockState(pos.offset(i - 2, k - 1, j - 2)).is(Blocks.WATER)) {
                        level.setBlock(pos.offset(i - 2, k - 1, j - 2), Blocks.AIR.defaultBlockState(), 2);
                    }

                }
            }
        }
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                level.setBlock(pos.offset(i - 2, -1, j - 2), baseBlock.defaultBlockState(), 2);
                //level.setBlock(pos.offset(i - 2, 3, j - 2), Blocks.QUARTZ_BLOCK.defaultBlockState(), 2);
                if ((i == 0 || i == 4) && (j == 0 || j == 4)) {
                    level.setBlock(pos.offset(i - 2, 0, j - 2), pillarBlock.defaultBlockState(), 2);
                    level.setBlock(pos.offset(i - 2, 1, j - 2), pillarBlock.defaultBlockState(), 2);
                    level.setBlock(pos.offset(i - 2, 2, j - 2), corner ? BlockRegistry.LUCKY_BLOCK.get().defaultBlockState() : slabBlock.defaultBlockState(), 2);


                }
            }
        }
        level.setBlock(pos, pillarBlock.defaultBlockState(), 2);
        level.setBlock(pos.above(1), pillarBlock.defaultBlockState(), 2);
        if (level.getRandom().nextDouble() < 0.5) {
            level.setBlock(pos.above(2), pillarBlock.defaultBlockState(), 2);
            level.setBlock(pos.above(3), middle ? BlockRegistry.LUCKY_BLOCK.get().defaultBlockState() : slabBlock.defaultBlockState(), 2);
        }
        else {
            level.setBlock(pos.above(2), middle ? BlockRegistry.LUCKY_BLOCK.get().defaultBlockState() : slabBlock.defaultBlockState(), 2);
        }
        level.setBlock(pos.east(), slabBlock.defaultBlockState(), 2);
        level.setBlock(pos.north(), slabBlock.defaultBlockState(), 2);
        level.setBlock(pos.west(), slabBlock.defaultBlockState(), 2);
        level.setBlock(pos.south(), slabBlock.defaultBlockState(), 2);

    }

}
