package tonius.zoom.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.common.MinecraftForge;

import org.lwjgl.opengl.GL11;

import tonius.zoom.ItemBinoculars;
import tonius.zoom.Zoom;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;
import cpw.mods.fml.common.gameevent.TickEvent.RenderTickEvent;

public class EventHandler {
    
    private static final Minecraft mc = Minecraft.getMinecraft();
    private static final ResourceLocation OVERLAY_TEXTURE = new ResourceLocation(Zoom.MODID, "textures/gui/binoculars.png");
    
    private static final float MIN_ZOOM = 1 / 1.5F;
    private static final float MAX_ZOOM = 1 / 10.0F;
    private static float currentZoom = 1 / 6.0F;
    
    private static boolean renderPlayerAPILoaded = false;
    
    public static void init() {
        renderPlayerAPILoaded = Loader.isModLoaded("RenderPlayerAPI");
        
        EventHandler handler = new EventHandler();
        FMLCommonHandler.instance().bus().register(handler);
        MinecraftForge.EVENT_BUS.register(handler);
    }
    
    @SubscribeEvent
    public void onFOVUpdate(FOVUpdateEvent evt) {
        if (isUsingBinoculars() && mc.gameSettings.thirdPersonView == 0) {
            evt.newfov = currentZoom;
        }
    }
    
    @SubscribeEvent
    public void onMouseScroll(MouseEvent evt) {
        if (isUsingBinoculars() && evt.dwheel != 0 && mc.gameSettings.thirdPersonView == 0) {
            currentZoom = 1 / Math.min(Math.max(1 / currentZoom + evt.dwheel / 180F, 1 / MIN_ZOOM), 1 / MAX_ZOOM);
            evt.setCanceled(true);
        }
    }
    
    @SubscribeEvent
    public void onRenderTick(RenderTickEvent evt) {
        if (evt.phase != Phase.END) {
            return;
        }
        
        if (isUsingBinoculars() && mc.gameSettings.thirdPersonView == 0) {
            GL11.glPushMatrix();
            mc.entityRenderer.setupOverlayRendering();
            GL11.glEnable(GL11.GL_BLEND);
            OpenGlHelper.glBlendFunc(770, 771, 1, 0);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glDisable(GL11.GL_ALPHA_TEST);
            
            mc.renderEngine.bindTexture(OVERLAY_TEXTURE);
            
            ScaledResolution res = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
            double width = res.getScaledWidth_double();
            double height = res.getScaledHeight_double();
            
            Tessellator tessellator = Tessellator.instance;
            tessellator.startDrawingQuads();
            tessellator.addVertexWithUV(0.0D, height, -90.0D, 0.0D, 1.0D);
            tessellator.addVertexWithUV(width, height, -90.0D, 1.0D, 1.0D);
            tessellator.addVertexWithUV(width, 0.0D, -90.0D, 1.0D, 0.0D);
            tessellator.addVertexWithUV(0.0D, 0.0D, -90.0D, 0.0D, 0.0D);
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
    
    @SubscribeEvent
    public void onRenderHeldItem(RenderPlayerEvent.Specials.Pre evt) {
        if (renderPlayerAPILoaded && isUsingBinoculars(evt.entityPlayer, false)) {
            evt.renderItem = false;
        }
    }
    
    private static boolean isUsingBinoculars(EntityPlayer player, boolean keybind) {
        ItemStack stack = player.getItemInUse();
        if (stack != null && stack.getItem() instanceof ItemBinoculars) {
            return true;
        } else if (keybind && KeyHandler.keyZoom.getIsKeyPressed()) {
            for (ItemStack invStack : player.inventory.mainInventory) {
                if (invStack != null && invStack.getItem() instanceof ItemBinoculars) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private static boolean isUsingBinoculars(boolean keybind) {
        EntityPlayer player = mc.thePlayer;
        if (player == null) {
            return false;
        }
        return isUsingBinoculars(player, keybind);
    }
    
    private static boolean isUsingBinoculars() {
        return isUsingBinoculars(true);
    }
    
}
