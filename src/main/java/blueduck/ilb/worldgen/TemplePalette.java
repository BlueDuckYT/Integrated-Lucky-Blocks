package blueduck.ilb.worldgen;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

public class TemplePalette {

    public ResourceLocation baseLoc;
    public ResourceLocation pillarLoc;
    public ResourceLocation slabLoc;

    public TemplePalette(String base, String pillar, String slab) {
        baseLoc = ResourceLocation.parse(base);
        pillarLoc = ResourceLocation.parse(pillar);
        slabLoc = ResourceLocation.parse(slab);
    }

    public Block getBase(Level level) {
        return level.registryAccess().registryOrThrow(Registries.BLOCK).get(baseLoc);
    }
    public Block getPillar(Level level) {
        return level.registryAccess().registryOrThrow(Registries.BLOCK).get(pillarLoc);
    }
    public Block getSlab(Level level) {
        return level.registryAccess().registryOrThrow(Registries.BLOCK).get(slabLoc);
    }

}
