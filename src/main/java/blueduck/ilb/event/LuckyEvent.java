package blueduck.ilb.event;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.Arrays;

public class LuckyEvent {

    public ArrayList<EventAction> actionList = new ArrayList<EventAction>();

    public LuckyEvent(EventAction... actions) {
        actionList.addAll(Arrays.asList(actions));
    }

    public void execute(ServerLevel level, BlockPos pos, Entity entity) {
        for (int i = 0; i < actionList.size(); i++) {
            actionList.get(i).execute(level, pos, entity);
        }
    }
}
