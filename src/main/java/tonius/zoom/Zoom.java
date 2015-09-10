package tonius.zoom;

import net.minecraftforge.oredict.ShapedOreRecipe;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = Zoom.MODID)
public class Zoom {
    
    public static final String MODID = "zoom";
    public static final String PREFIX = MODID + ".";
    public static final String RESOURCE_PREFIX = MODID + ":";
    
    @Instance(MODID)
    public static Zoom instance;
    
    @SidedProxy(clientSide = "tonius.zoom.client.ClientProxy", serverSide = "tonius.zoom.CommonProxy")
    public static CommonProxy proxy;
    
    public static ItemBinoculars itemBinoculars;
    
    @EventHandler
    public static void preInit(FMLPreInitializationEvent evt) {
        itemBinoculars = new ItemBinoculars();
    }
    
    @EventHandler
    public static void init(FMLInitializationEvent evt) {
        proxy.registerHandlers();
    }
    
    @EventHandler
    public static void postInit(FMLPostInitializationEvent evt) {
        GameRegistry.addRecipe(new ShapedOreRecipe(itemBinoculars, new Object[] { "B B", "LEL", "P P", 'B', "blockGlassColorless", 'L', "ingotIron", 'E', "stickWood", 'P', "paneGlassColorless" }));
    }
    
}
