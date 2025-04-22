package blueduck.ilb;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

// An example config class. This is not required, but it's a good idea to have one to keep your config organized.
// Demonstrates how to use Forge's config APIs
@Mod.EventBusSubscriber(modid = IntegratedLuckyBlocks.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config
{
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    private static final ForgeConfigSpec.BooleanValue SHOULD_SPAWN_TEMPLES = BUILDER
            .comment("Enables the Lucky Temple structure spawn in the overworld")
            .define("luckyTemples", true);

    private static final ForgeConfigSpec.BooleanValue SHOULD_CREATIVE_BREAK = BUILDER
            .comment("Whether breaking a Lucky Block in creative mode should activate its effect")
            .define("creativeDestroy", false);


    // a list of strings that are treated as resource locations for items
    private static final ForgeConfigSpec.ConfigValue<List<? extends String>> ITEM_STRINGS = BUILDER
            .comment("A list of loot tables to add Lucky Blocks to")
            .defineListAllowEmpty("loot_tables", List.of("minecraft:chests/end_city_treasure", "minecraft:chests/ancient_city", "minecraft:chests/desert_pyramid", "minecraft:chests/jungle_temple", "minecraft:chests/bastion_treasure", "minecraft:gameplay/fishing/treasure"), Config::validateName);

    private static final ForgeConfigSpec.IntValue LOOT_TABLE_WEIGHT = BUILDER
            .comment("The weight of Lucky Blocks in the loot tables specified above (higher weight = more likely to generate)")
            .defineInRange("lootTableWeight", 3, 0, Integer.MAX_VALUE);

    static final ForgeConfigSpec SPEC = BUILDER.build();

    public static boolean luckyTemplesSpawn;
    public static boolean creativeModeBreaks;
    public static List<? extends String> lootTables;
    public static int weight;

    private static boolean validateName(final Object obj)
    {
        return true;
    }

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event)
    {
        luckyTemplesSpawn = SHOULD_SPAWN_TEMPLES.get();
        creativeModeBreaks = SHOULD_CREATIVE_BREAK.get();

        // convert the list of strings into a set of items
        lootTables = ITEM_STRINGS.get();
        weight = LOOT_TABLE_WEIGHT.get();
    }
}
