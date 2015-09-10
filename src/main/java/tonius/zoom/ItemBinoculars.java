package tonius.zoom;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBinoculars extends Item {
    
    public ItemBinoculars() {
        this.setMaxStackSize(1);
        this.setUnlocalizedName(Zoom.PREFIX + "binoculars");
        this.setTextureName(Zoom.RESOURCE_PREFIX + "binoculars");
        this.setCreativeTab(CreativeTabs.tabTools);
        
        GameRegistry.registerItem(this, "binoculars");
    }
    
    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
        player.setItemInUse(itemStack, this.getMaxItemUseDuration(itemStack));
        return itemStack;
    }
    
    @Override
    public int getMaxItemUseDuration(ItemStack itemStack) {
        return Integer.MAX_VALUE;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean bool) {
        list.add(StatCollector.translateToLocal("item.zoom.binoculars.desc.1"));
        list.add(StatCollector.translateToLocal("item.zoom.binoculars.desc.2"));
    }
}
