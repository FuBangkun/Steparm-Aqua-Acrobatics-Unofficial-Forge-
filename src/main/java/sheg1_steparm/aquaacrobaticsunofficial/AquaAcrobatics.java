package sheg1_steparm.aquaacrobaticsunofficial;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLModIdMappingEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sheg1_steparm.aquaacrobaticsunofficial.proxy.CommonProxy;

@Mod(
        modid = Tags.MOD_ID,
        name = Tags.MOD_NAME,
        version = Tags.VERSION,
        dependencies = "required-after:mixinbooter"
)
public class AquaAcrobatics {
    public static final Logger LOGGER = LogManager.getLogger(Tags.MOD_NAME);

    @SidedProxy(clientSide = "sheg1_steparm.aquaacrobaticsunofficial.proxy.ClientProxy", serverSide = "sheg1_steparm.aquaacrobaticsunofficial.proxy.CommonProxy")
    private static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(final FMLPreInitializationEvent evt) {
        proxy.onPreInit(evt);
    }

    @Mod.EventHandler
    public void init(final FMLInitializationEvent evt) {
        proxy.onInit();
    }

    @Mod.EventHandler
    public void postInit(final FMLPostInitializationEvent evt) {
        proxy.onPostInit();
    }

    @Mod.EventHandler
    public void onMappings(FMLModIdMappingEvent evt) {
        proxy.onMappings();
    }
}