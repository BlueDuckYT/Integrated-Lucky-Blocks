package blueduck.ilb.registry;

import blueduck.ilb.IntegratedLuckyBlocks;
import blueduck.ilb.worldgen.LuckyTempleFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class FeatureRegistry {

    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, IntegratedLuckyBlocks.MODID);

    public static final RegistryObject<Feature<NoneFeatureConfiguration>> LUCKY_TEMPLE_FEATURE = FEATURES.register("lucky_temple_feature", () -> new LuckyTempleFeature(NoneFeatureConfiguration.CODEC));

}
