package tonius.zoom;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;
import org.apache.logging.log4j.Logger;

@Mod(modid = Zoom.MODID, version = Zoom.VERSION,
        acceptedMinecraftVersions = "[1.12]")
@Mod.EventBusSubscriber(modid = Zoom.MODID)
public class Zoom {
    
    public static final String MODID = "zoom";
    public static final String PREFIX = MODID + ".";
    public static final String VERSION = "@VERSION@";
    
    @Mod.Instance(MODID)
    public static Zoom instance;
    @SidedProxy(clientSide = "tonius.zoom.client.ClientProxy", serverSide = "tonius.zoom.CommonProxy")
    public static CommonProxy proxy;
    public static Logger logger;
    
    public static ItemBinoculars itemBinoculars;
    
    @Mod.EventHandler
    public static void preInit(FMLPreInitializationEvent evt) {
        logger = evt.getModLog();
        logger.info("Starting Zoom");
        proxy.registerHandlers();
        itemBinoculars = new ItemBinoculars();
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        logger.info("Registering items");
        event.getRegistry().registerAll(itemBinoculars);
    }

    @Mod.EventHandler
    public static void init(FMLInitializationEvent evt) {
        proxy.registerItemModel(itemBinoculars, 0, ItemBinoculars.NAME);
    }
    
    @Mod.EventHandler
    public static void postInit(FMLPostInitializationEvent evt) {
        logger.info("Registering recipes");
        GameRegistry.addRecipe(new ShapedOreRecipe(itemBinoculars,
                "B B",
                "LEL",
                "P P",
                'B', "blockGlassColorless",
                'L', "ingotIron",
                'E', "stickWood",
                'P', "paneGlassColorless"
        ));
    }
    
}
