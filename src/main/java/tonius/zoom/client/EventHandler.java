package tonius.zoom.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.common.MinecraftForge;
import tonius.zoom.ItemBinoculars;
import tonius.zoom.Zoom;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;
import cpw.mods.fml.common.gameevent.TickEvent.RenderTickEvent;

public class EventHandler {
    
    private static Minecraft mc = Minecraft.getMinecraft();
    private static float currentZoom = 1 / 6.0F;
    private static final float MIN_ZOOM = 1 / 1.5F;
    private static final float MAX_ZOOM = 1 / 10.0F;
    
    public static void init() {
        EventHandler handler = new EventHandler();
        FMLCommonHandler.instance().bus().register(handler);
        MinecraftForge.EVENT_BUS.register(handler);
    }
    
    @SubscribeEvent
    public void onFOVUpdate(FOVUpdateEvent evt) {
        ItemStack binoculars = this.getBinoculars(evt.entity);
        if (binoculars != null && mc.gameSettings.thirdPersonView == 0) {
            evt.newfov = currentZoom;
        }
    }
    
    @SubscribeEvent
    public void onMouseScroll(MouseEvent evt) {
        if (mc.thePlayer != null) {
            if (evt.dwheel != 0) {
                ItemStack binoculars = this.getBinoculars(mc.thePlayer);
                if (binoculars != null && mc.gameSettings.thirdPersonView == 0) {
                    currentZoom = 1 / Math.min(Math.max(1 / currentZoom + evt.dwheel / 180F, 1 / MIN_ZOOM), 1 / MAX_ZOOM);
                    evt.setCanceled(true);
                }
            }
        }
    }
    
    private ItemStack getBinoculars(EntityPlayerSP player) {
        if (player.getItemInUse() != null && player.getItemInUse().getItem() instanceof ItemBinoculars) {
            return player.getItemInUse();
        }
        return null;
    }
    
    @SubscribeEvent
    public void onRenderTick(RenderTickEvent evt) {
        if (evt.phase == Phase.END) {
            EntityPlayer player = mc.thePlayer;
            if (player != null) {
                if (player.getItemInUse() != null && player.getItemInUse().getItem() instanceof ItemBinoculars && mc.gameSettings.thirdPersonView == 0) {
                    mc.entityRenderer.setupOverlayRendering();
                    ScaledResolution res = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
                    renderBinocularsBlur(res.getScaledWidth_double(), res.getScaledHeight_double());
                }
            }
        }
    }
    
    private static void renderBinocularsBlur(double width, double height) {
        mc.renderEngine.bindTexture(new ResourceLocation(Zoom.MODID, "textures/gui/binoculars.png"));
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(0.0D, height, -90.0D, 0.0D, 1.0D);
        tessellator.addVertexWithUV(width, height, -90.0D, 1.0D, 1.0D);
        tessellator.addVertexWithUV(width, 0.0D, -90.0D, 1.0D, 0.0D);
        tessellator.addVertexWithUV(0.0D, 0.0D, -90.0D, 0.0D, 0.0D);
        tessellator.draw();
    }
    
}
