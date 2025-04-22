package blueduck.ilb.registry;

import blueduck.ilb.event.LuckyEvent;
import net.minecraft.world.level.Level;

import java.util.ArrayList;

public class EventList {

    public static ArrayList<LuckyEvent> eventList = new ArrayList<LuckyEvent>();

    public static LuckyEvent getEvent(Level level) {
        return eventList.get(level.getRandom().nextInt(eventList.size()-1));
    }

    public static void addEvent(LuckyEvent event, int weight) {
        for (int i = 0; i < weight; i++) {
            eventList.add(event);
        }
    }

}
