package tonius.zoom.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.opengl.GL11;
import tonius.zoom.ItemBinoculars;
import tonius.zoom.Zoom;

public class EventHandler {
    
    private static final Minecraft mc = Minecraft.getMinecraft();
    private static final ResourceLocation OVERLAY_TEXTURE = new ResourceLocation(Zoom.MODID, "textures/gui/binoculars.png");
    
    private static final float MIN_ZOOM = 1 / 1.5F;
    private static final float MAX_ZOOM = 1 / 10.0F;
    private static float currentZoom = 1 / 6.0F;
    
    @SubscribeEvent
    public void onFOVUpdate(FOVUpdateEvent evt) {
        if (isUsingBinoculars() && mc.gameSettings.thirdPersonView == 0) {
            evt.setNewfov(currentZoom);
        }
    }
    
    @SubscribeEvent
    public void onMouseScroll(MouseEvent evt) {
        if (isUsingBinoculars() && evt.getDwheel() != 0 && mc.gameSettings.thirdPersonView == 0) {
            currentZoom = 1 / Math.min(Math.max(1 / currentZoom + evt.getDwheel() / 180F, 1 / MIN_ZOOM), 1 / MAX_ZOOM);
            evt.setCanceled(true);
        }
    }
    
    @SubscribeEvent
    public void onRenderTick(TickEvent.RenderTickEvent evt) {
        if (evt.phase != TickEvent.Phase.END) {
            return;
        }

        if (isUsingBinoculars() && mc.gameSettings.thirdPersonView == 0) {
            GL11.glPushMatrix();

            mc.entityRenderer.setupOverlayRendering();
            GlStateManager.enableBlend();
            GlStateManager.disableDepth();
            GlStateManager.depthMask(false);
            GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            GlStateManager.disableAlpha();
            mc.getTextureManager().bindTexture(OVERLAY_TEXTURE);
            
            ScaledResolution res = new ScaledResolution(mc);
            double width = res.getScaledWidth_double();
            double height = res.getScaledHeight_double();

            Tessellator tessellator = Tessellator.getInstance();
            BufferBuilder vertexbuffer = tessellator.getBuffer();
            vertexbuffer.begin(7, DefaultVertexFormats.POSITION_TEX);
            vertexbuffer.pos(0.0D, height, -90.0D).tex(0.0D, 1.0D).endVertex();
            vertexbuffer.pos(width, height, -90.0D).tex(1.0D, 1.0D).endVertex();
            vertexbuffer.pos(width, 0.0D, -90.0D).tex(1.0D, 0.0D).endVertex();
            vertexbuffer.pos(0.0D, 0.0D, -90.0D).tex(0.0D, 0.0D).endVertex();
            tessellator.draw();
            
            GL11.glPopMatrix();
        }
    }
    
    @SubscribeEvent
    public void onRenderHand(RenderHandEvent evt) {
        if (isUsingBinoculars()) {
            evt.setCanceled(true);
        }
    }
    
    private static boolean isUsingBinoculars() {
        if (mc.player == null) {
            return false;
        }

        ItemStack stack = mc.player.getActiveItemStack();
        if (stack != null && stack.getItem() instanceof ItemBinoculars) {
            return true;
        } else if (KeyHandler.keyZoom.isKeyDown()) {
            if (mc.player.getHeldItemOffhand() != null && mc.player.getHeldItemOffhand().getItem() instanceof ItemBinoculars) {
                return true;
            }

            for (ItemStack invStack : mc.player.inventory.mainInventory) {
                if (invStack != null && invStack.getItem() instanceof ItemBinoculars) {
                    return true;
                }
            }
        }
        return false;
    }

}
