package tonius.zoom.client;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import tonius.zoom.ItemBinoculars;
import tonius.zoom.Zoom;

public class ItemRenderer implements IItemRenderer {
    
    private static Minecraft mc = Minecraft.getMinecraft();
    public ModelBinoculars binoculars = new ModelBinoculars();
    
    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        return true;
    }
    
    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
        return true;
    }
    
    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
        if (type == ItemRenderType.EQUIPPED || type == ItemRenderType.EQUIPPED_FIRST_PERSON) {
            GL11.glTranslatef(0.5F, 0.5F, 0.5F);
        }
        
        if (item.getItem() instanceof ItemBinoculars) {
            
            mc.renderEngine.bindTexture(new ResourceLocation(Zoom.MODID, "textures/models/binoculars.png"));
            GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
            GL11.glScalef(0.09F, 0.09F, 0.09F);
            GL11.glTranslatef(0.0F, 0.0F, -1.8F);
            
            if (type == ItemRenderType.EQUIPPED_FIRST_PERSON) {
                if (mc.thePlayer.getItemInUse() == item) {
                    return;
                }
                GL11.glRotatef(45F, 0.0F, 1.0F, 0.0F);
                GL11.glTranslatef(3.0F, -7.0F, -0.0F);
            }
            
            this.binoculars.render(1.0F);
        }
    }
    
}
