package blueduck.ilb.event.actions;

import blueduck.ilb.event.EventAction;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;

public class ExplodeAction extends EventAction {

      float value;


      public ExplodeAction(float strength) {
            value = strength;
      }

      public void execute(ServerLevel level, BlockPos pos, Entity entity) {
            super.execute(level, pos, entity);
            level.explode(entity, pos.getX(), pos.getY(), pos.getZ(), value, Level.ExplosionInteraction.BLOCK);

      }

}
