package tonius.zoom.client;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelBinoculars extends ModelBase {
    
    ModelRenderer leftCylinder;
    ModelRenderer rightCylinder;
    ModelRenderer middle;
    ModelRenderer frontLeftLens;
    ModelRenderer frontRightLens;
    ModelRenderer leftEnd;
    ModelRenderer rightEnd;
    ModelRenderer leftFrontCylinder;
    ModelRenderer rightFrontCylinder;
    
    public ModelBinoculars() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        
        this.leftCylinder = new ModelRenderer(this, 0, 0);
        this.leftCylinder.addBox(0F, 0F, 0F, 4, 4, 6);
        this.leftCylinder.setRotationPoint(1F, 0F, -2F);
        this.leftCylinder.setTextureSize(64, 32);
        this.leftCylinder.mirror = true;
        this.setRotation(this.leftCylinder, 0F, 0F, 0F);
        
        this.rightCylinder = new ModelRenderer(this, 0, 10);
        this.rightCylinder.addBox(0F, 0F, 0F, 4, 4, 6);
        this.rightCylinder.setRotationPoint(-5F, 0F, -2F);
        this.rightCylinder.setTextureSize(64, 32);
        this.rightCylinder.mirror = true;
        this.setRotation(this.rightCylinder, 0F, 0F, 0F);
        
        this.middle = new ModelRenderer(this, 20, 0);
        this.middle.addBox(0F, 0F, 0F, 2, 2, 4);
        this.middle.setRotationPoint(-1F, -0.5F, -0.5F);
        this.middle.setTextureSize(64, 32);
        this.middle.mirror = true;
        this.setRotation(this.middle, 0F, 0F, 0F);
        
        this.frontLeftLens = new ModelRenderer(this, 20, 6);
        this.frontLeftLens.addBox(0F, 0F, 0F, 3, 3, 3);
        this.frontLeftLens.setRotationPoint(1.5F, 0.5F, 4F);
        this.frontLeftLens.setTextureSize(64, 32);
        this.frontLeftLens.mirror = true;
        this.setRotation(this.frontLeftLens, 0F, 0F, 0F);
        
        this.frontRightLens = new ModelRenderer(this, 20, 12);
        this.frontRightLens.addBox(0F, 0F, 0F, 3, 3, 3);
        this.frontRightLens.setRotationPoint(-4.5F, 0.5F, 4F);
        this.frontRightLens.setTextureSize(64, 32);
        this.frontRightLens.mirror = true;
        this.setRotation(this.frontRightLens, 0F, 0F, 0F);
        
        this.leftEnd = new ModelRenderer(this, 32, 0);
        this.leftEnd.addBox(0F, 0F, 0F, 5, 5, 2);
        this.leftEnd.setRotationPoint(0.5F, -0.5F, -4F);
        this.leftEnd.setTextureSize(64, 32);
        this.leftEnd.mirror = true;
        this.setRotation(this.leftEnd, 0F, 0F, 0F);
        
        this.rightEnd = new ModelRenderer(this, 32, 7);
        this.rightEnd.addBox(0F, 0F, 0F, 5, 5, 2);
        this.rightEnd.setRotationPoint(-5.5F, -0.5F, -4F);
        this.rightEnd.setTextureSize(64, 32);
        this.rightEnd.mirror = true;
        this.setRotation(this.rightEnd, 0F, 0F, 0F);
        
        this.leftFrontCylinder = new ModelRenderer(this, 0, 20);
        this.leftFrontCylinder.addBox(0F, 0F, 0F, 4, 4, 1);
        this.leftFrontCylinder.setRotationPoint(1F, 0F, 7F);
        this.leftFrontCylinder.setTextureSize(64, 32);
        this.leftFrontCylinder.mirror = true;
        this.setRotation(this.leftFrontCylinder, 0F, 0F, 0F);
        
        this.rightFrontCylinder = new ModelRenderer(this, 10, 20);
        this.rightFrontCylinder.addBox(0F, 0F, 0F, 4, 4, 1);
        this.rightFrontCylinder.setRotationPoint(-5F, 0F, 7F);
        this.rightFrontCylinder.setTextureSize(64, 32);
        this.rightFrontCylinder.mirror = true;
        this.setRotation(this.rightFrontCylinder, 0F, 0F, 0F);
    }
    
    public void render(float size) {
        this.leftCylinder.render(size);
        this.rightCylinder.render(size);
        this.middle.render(size);
        this.frontLeftLens.render(size);
        this.frontRightLens.render(size);
        this.leftEnd.render(size);
        this.rightEnd.render(size);
        this.leftFrontCylinder.render(size);
        this.rightFrontCylinder.render(size);
    }
    
    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
    
}