package mod.acgaming.prey;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = Prey.MOD_ID, name = Prey.NAME)
public class PreyConfig
{
    @Config.Name("Prey I Distance")
    @Config.Comment("Increased follow range distance in blocks")
    @Config.RangeInt(min = 0, max = 2048)
    public static int preyDistanceI = 8;

    @Config.Name("Prey II Distance")
    @Config.Comment("Increased follow range distance in blocks")
    @Config.RangeInt(min = 0, max = 2048)
    public static int preyDistanceII = 16;

    @Config.Name("Prey III Distance")
    @Config.Comment("Increased follow range distance in blocks")
    @Config.RangeInt(min = 0, max = 2048)
    public static int preyDistanceIII = 24;

    @Config.Name("Prey IV Distance")
    @Config.Comment("Increased follow range distance in blocks")
    @Config.RangeInt(min = 0, max = 2048)
    public static int preyDistanceIV = 32;

    @Config.Name("Prey V+ Distance")
    @Config.Comment("Increased follow range distance in blocks")
    @Config.RangeInt(min = 0, max = 2048)
    public static int preyDistanceV = 40;

    @Config.Name("Ticking Rate")
    @Config.Comment("Update rate in ticks, tweak this for performance reasons")
    public static int tickingRate = 40;

    @Config.Name("Curable")
    @Config.Comment("If the potion effect should be curable by milk")
    public static boolean isCurable = false;

    @Config.Name("Render In HUD")
    @Config.Comment("If the potion effect should be displayed in the player's ingame HUD")
    public static boolean shouldRenderHUD = true;

    @Config.Name("Render In Inventory")
    @Config.Comment("If the potion effect should be displayed in the player's inventory")
    public static boolean shouldRenderInv = true;

    @Mod.EventBusSubscriber(modid = Prey.MOD_ID)
    public static class EventHandler
    {
        @SubscribeEvent
        public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event)
        {
            if (event.getModID().equals(Prey.MOD_ID)) ConfigManager.sync(Prey.MOD_ID, Config.Type.INSTANCE);
        }
    }
}