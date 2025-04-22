package blueduck.ilb.event.actions;

import blueduck.ilb.event.EventAction;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;

import java.util.List;

public class FishRainAction extends EventAction {

      public int quant;

      public FishRainAction(int quantity) {
            quant = quantity;
      }

      public void execute(ServerLevel level, BlockPos pos, Entity entity) {
            super.execute(level, pos, entity);
            for (int i = 0; i < quant; i++) {
                  LootParams lootparams = (new LootParams.Builder(level)).withParameter(LootContextParams.ORIGIN, entity.getUpVector(1)).withParameter(LootContextParams.TOOL, entity.getHandSlots().iterator().next()).withParameter(LootContextParams.KILLER_ENTITY, entity).withLuck(((Player)entity).getLuck()).create(LootContextParamSets.FISHING);
                  LootTable loottable = level.getServer().getLootData().getLootTable(BuiltInLootTables.FISHING);
                  List<ItemStack> list = loottable.getRandomItems(lootparams);
                  for (ItemStack itemStack : list) {
                        Entity entToSpawn = new ItemEntity(level, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, itemStack);
                        entToSpawn.moveTo(pos.getX() + 0.5 + level.random.nextDouble() * 8 - 4, pos.getY() + level.random.nextDouble() * 15 + 20, pos.getZ() + 0.5 + level.random.nextDouble() * 8 - 4);
                        level.addFreshEntity(entToSpawn);
                  }
            }
      }

}
