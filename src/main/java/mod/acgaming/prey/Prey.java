package mod.acgaming.prey;

import net.minecraft.potion.Potion;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
@Mod(modid = Prey.MOD_ID, name = Prey.NAME, version = Prey.VERSION, acceptedMinecraftVersions = Prey.ACCEPTED_VERSIONS)
public class Prey
{
    public static final String MOD_ID = "prey";
    public static final String NAME = "Prey";
    public static final String VERSION = "1.0.0";
    public static final String ACCEPTED_VERSIONS = "[1.12.2]";
    public static final Potion PREY_POTION = new PreyPotion().setPotionName("effect." + MOD_ID + "." + MOD_ID).setRegistryName(MOD_ID, MOD_ID);

    @SubscribeEvent
    public static void registerPotion(RegistryEvent.Register<Potion> event)
    {
        event.getRegistry().register(PREY_POTION);
    }
}