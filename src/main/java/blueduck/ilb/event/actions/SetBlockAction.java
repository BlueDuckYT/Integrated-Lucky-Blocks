package blueduck.ilb.event.actions;

import blueduck.ilb.event.EventAction;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.Block;

public class SetBlockAction extends EventAction {

    public ResourceLocation blockResLoc;
    int dist;
    boolean atPlayer;


    public SetBlockAction(String block, int height) {
        blockResLoc = ResourceLocation.parse(block);
        dist = height;
        atPlayer = true;
    }
    public SetBlockAction(String block, int height, boolean spawnAtPlayer) {
        blockResLoc = ResourceLocation.parse(block);
        dist = height;
        atPlayer = spawnAtPlayer;
    }

    public void execute(ServerLevel level, BlockPos pos, Entity entity) {
        super.execute(level, pos, entity);
        Block block = level.registryAccess().registryOrThrow(Registries.BLOCK).get(blockResLoc);
        level.setBlock((atPlayer ? entity.blockPosition() : pos).offset(0, dist, 0), block.defaultBlockState(), 2);
    }

}
