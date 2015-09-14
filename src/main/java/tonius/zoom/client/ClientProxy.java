package tonius.zoom.client;

import net.minecraftforge.client.MinecraftForgeClient;
import tonius.zoom.CommonProxy;
import tonius.zoom.Zoom;
import tonius.zoom.client.model.ModelPlayerCustom;
import api.player.model.ModelPlayerAPI;
import cpw.mods.fml.common.Loader;

public class ClientProxy extends CommonProxy {
    
    @Override
    public void registerHandlers() {
        super.registerHandlers();
        EventHandler.init();
        KeyHandler.init();
        MinecraftForgeClient.registerItemRenderer(Zoom.itemBinoculars, new ItemRenderer());
        
        if (Loader.isModLoaded("RenderPlayerAPI")) {
            ModelPlayerAPI.register(Zoom.MODID, ModelPlayerCustom.class);
        }
    }
    
}
