package tonius.zoom.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import tonius.zoom.CommonProxy;
import tonius.zoom.Zoom;

public class ClientProxy extends CommonProxy {
    
    @Override
    public void registerHandlers() {
        super.registerHandlers();
        MinecraftForge.EVENT_BUS.register(new EventHandler());
        KeyHandler.init();
    }

    @Override
    public void registerItemModel(Item item, int meta, String name) {
        RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
        renderItem.getItemModelMesher().register(item, meta, new ModelResourceLocation(Zoom.MODID + ":" + name, "inventory"));
    }
    
}
