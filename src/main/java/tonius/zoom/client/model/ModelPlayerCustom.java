package tonius.zoom.client.model;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import tonius.zoom.ItemBinoculars;
import tonius.zoom.Zoom;
import api.player.model.ModelPlayerAPI;
import api.player.model.ModelPlayerBase;

public class ModelPlayerCustom extends ModelPlayerBase {
    
    private static Minecraft mc = Minecraft.getMinecraft();
    
    private final ModelBinoculars modelBinoculars = new ModelBinoculars();
    
    public ModelPlayerCustom(ModelPlayerAPI modelPlayerAPI) {
        super(modelPlayerAPI);
    }
    
    @Override
    public void afterRender(Entity entity, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6) {
        if (!isUsingBinoculars(entity)) {
            return;
        }
        
        mc.renderEngine.bindTexture(new ResourceLocation(Zoom.MODID, "textures/models/binoculars.png"));
        GL11.glPushMatrix();
        GL11.glRotatef(this.modelPlayer.bipedHead.rotateAngleY * 60F, 0F, 1F, 0F);
        GL11.glRotatef(this.modelPlayer.bipedHead.rotateAngleX * 60F, 1F, 0F, 0F);
        GL11.glTranslatef(0.0F, -0.3F, -0.68F);
        this.modelBinoculars.render(0.05F);
        GL11.glPopMatrix();
    }
    
    @Override
    public void afterSetRotationAngles(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, Entity entity) {
        if (!isUsingBinoculars(entity)) {
            return;
        }
        
        this.modelPlayer.bipedLeftArm.rotateAngleX = this.modelPlayer.bipedRightArm.rotateAngleX = this.modelPlayer.bipedHead.rotateAngleX - 2.0F;
        this.modelPlayer.bipedLeftArm.rotateAngleY = this.modelPlayer.bipedRightArm.rotateAngleY = this.modelPlayer.bipedHead.rotateAngleY;
    }
    
    private static boolean isUsingBinoculars(Entity entity) {
        if (!(entity instanceof EntityPlayer)) {
            return false;
        }
        EntityPlayer player = (EntityPlayer) entity;
        
        ItemStack stack = player.getItemInUse();
        return stack != null && stack.getItem() instanceof ItemBinoculars;
    }
    
}
