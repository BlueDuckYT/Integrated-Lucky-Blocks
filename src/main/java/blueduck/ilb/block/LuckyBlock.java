package blueduck.ilb.block;

import blueduck.ilb.Config;
import blueduck.ilb.registry.EventList;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;

public class LuckyBlock extends Block {
    public LuckyBlock(Properties p_49795_) {
        super(p_49795_);
    }

    @Override
    public boolean onDestroyedByPlayer(BlockState state, Level level, BlockPos pos, Player player, boolean willHarvest, FluidState fluid) {
        boolean flag = super.onDestroyedByPlayer(state, level, pos, player, willHarvest, fluid);
        //EventList.getEvent(level).execute(level, pos, player);
        if (!level.isClientSide() && player.getMainHandItem().getEnchantmentLevel(Enchantments.SILK_TOUCH) == 0 && (Config.creativeModeBreaks || !player.getAbilities().invulnerable || !player.getAbilities().mayfly || !player.getAbilities().instabuild)) {
            EventList.getEvent(level).execute((ServerLevel) level, pos, player);
        }
        return flag;
    }
}
