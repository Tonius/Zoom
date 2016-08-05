package tonius.zoom;

import net.minecraft.item.Item;

public class CommonProxy {
    
    public void registerHandlers() {
        Zoom.logger.info("Registering handlers");
    }

    public void registerItemModel(Item item, int meta, String name) {}
    
}
