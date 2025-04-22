package blueduck.ilb.event.actions;

import blueduck.ilb.event.EventAction;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;

public class TpPlayerUpAction extends EventAction {

      int dist = 0;

      public TpPlayerUpAction(int distance) {
            dist = distance;
      }

      public void execute(ServerLevel level, BlockPos pos, Entity entity) {
            super.execute(level, pos, entity);

            entity.teleportTo(entity.getX(), entity.getY() + dist, entity.getZ());
      }
}
