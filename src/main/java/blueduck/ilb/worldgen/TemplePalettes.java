package blueduck.ilb.worldgen;

import blueduck.ilb.event.LuckyEvent;
import net.minecraft.world.level.Level;

import java.util.ArrayList;

public class TemplePalettes {

    public static ArrayList<TemplePalette> paletteList = new ArrayList<TemplePalette>();

    public static void addPalette(TemplePalette palette, int weight) {
        for (int i = 0; i < weight; i++) {
            paletteList.add(palette);
        }
    }

    public static TemplePalette getPalette(Level level) {
        return paletteList.get(level.getRandom().nextInt(paletteList.size()-1));
    }



}
