package tonius.zoom.client;

import net.minecraftforge.client.MinecraftForgeClient;
import tonius.zoom.CommonProxy;
import tonius.zoom.Zoom;

public class ClientProxy extends CommonProxy {
    
    @Override
    public void registerHandlers() {
        super.registerHandlers();
        EventHandler.init();
        KeyHandler.init();
        MinecraftForgeClient.registerItemRenderer(Zoom.itemBinoculars, new ItemRenderer());
    }
    
}
