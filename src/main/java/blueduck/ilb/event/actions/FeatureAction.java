package blueduck.ilb.event.actions;

import blueduck.ilb.event.EventAction;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContextBuilder;
import net.minecraft.commands.Commands;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class FeatureAction extends EventAction {

      public ResourceLocation featureToPlace;
      public int height;


      public FeatureAction(String feature, int distUp) {
            featureToPlace = ResourceLocation.parse(feature);
            height = distUp;
      }

      public void execute(ServerLevel level, BlockPos pos, Entity entity) {
            super.execute(level, pos, entity);
            placeFeature(level, Holder.direct(level.registryAccess().registryOrThrow(Registries.CONFIGURED_FEATURE).get(featureToPlace)), pos.offset(0,height,0));

      }

      public static void placeFeature(ServerLevel serverLevel, Holder<ConfiguredFeature<?,?>> holder, BlockPos blockPos) {
            ConfiguredFeature<?,?> configuredFeature = holder.value();
            configuredFeature.place(serverLevel, serverLevel.getChunkSource().getGenerator(), serverLevel.getRandom(), blockPos);
      }

}
