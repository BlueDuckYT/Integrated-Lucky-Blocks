package blueduck.ilb.worldgen;

import blueduck.ilb.Config;
import blueduck.ilb.registry.BlockRegistry;
import com.mojang.serialization.Codec;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class LuckyTempleFeature extends Feature<NoneFeatureConfiguration> {

    public LuckyTempleFeature(Codec<NoneFeatureConfiguration> p_65786_) {
        super(p_65786_);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        boolean flag = true;
        if (!Config.luckyTemplesSpawn) {
            return false;
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (context.level().getBlockState(context.origin().offset(i-1, -1, j-1)).canBeReplaced()) {
                    flag = false;
                }
            }
        }
        if (flag) {
            TemplePalette palette = TemplePalettes.getPalette(context.level().getLevel());

            Block baseBlock = palette.getBase(context.level().getLevel());
            Block pillarBlock = palette.getPillar(context.level().getLevel());
            Block slabBlock = palette.getSlab(context.level().getLevel());


            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    for (int k = 0; k < 4; k++) {
                        if (!context.level().getBlockState(context.origin().offset(i - 2, k - 1, j - 2)).is(Blocks.WATER)) {
                            context.level().setBlock(context.origin().offset(i - 2, k - 1, j - 2), Blocks.AIR.defaultBlockState(), 2);
                        }

                    }
                }
            }
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    context.level().setBlock(context.origin().offset(i - 2, -1, j - 2), baseBlock.defaultBlockState(), 2);
                    //context.level().setBlock(context.origin().offset(i - 2, 3, j - 2), Blocks.QUARTZ_BLOCK.defaultBlockState(), 2);
                    if ((i == 0 || i == 4) && (j == 0 || j == 4)) {
                        context.level().setBlock(context.origin().offset(i - 2, 0, j - 2), pillarBlock.defaultBlockState(), 2);
                        context.level().setBlock(context.origin().offset(i - 2, 1, j - 2), pillarBlock.defaultBlockState(), 2);
                        context.level().setBlock(context.origin().offset(i - 2, 2, j - 2), slabBlock.defaultBlockState(), 2);


                    }
                }
            }
            context.level().setBlock(context.origin(), pillarBlock.defaultBlockState(), 2);
            context.level().setBlock(context.origin().above(1), pillarBlock.defaultBlockState(), 2);
            if (context.level().getRandom().nextDouble() < 0.5) {
                context.level().setBlock(context.origin().above(2), pillarBlock.defaultBlockState(), 2);
                context.level().setBlock(context.origin().above(3), BlockRegistry.LUCKY_BLOCK.get().defaultBlockState(), 2);
            }
            else {
                context.level().setBlock(context.origin().above(2), BlockRegistry.LUCKY_BLOCK.get().defaultBlockState(), 2);
            }
            context.level().setBlock(context.origin().east(), slabBlock.defaultBlockState(), 2);
            context.level().setBlock(context.origin().north(), slabBlock.defaultBlockState(), 2);
            context.level().setBlock(context.origin().west(), slabBlock.defaultBlockState(), 2);
            context.level().setBlock(context.origin().south(), slabBlock.defaultBlockState(), 2);

        }

        return false;
    }

}
