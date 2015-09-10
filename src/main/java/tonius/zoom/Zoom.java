package tonius.zoom;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Zoom.MODID)
public class Zoom {
    
    public static final String MODID = "zoom";
    public static final String PREFIX = MODID + ".";
    public static final String RESOURCE_PREFIX = MODID + ":";
    
    @Instance(MODID)
    public static Zoom instance;
    @SidedProxy(clientSide = "tonius.zoom.client.ClientProxy", serverSide = "tonius.zoom.CommonProxy")
    public static CommonProxy proxy;
    
    @EventHandler
    public static void preInit(FMLPreInitializationEvent evt) {
    }
    
    @EventHandler
    public static void init(FMLInitializationEvent evt) {
        proxy.registerHandlers();
    }
    
    @EventHandler
    public static void postInit(FMLPostInitializationEvent evt) {
    }
    
}
