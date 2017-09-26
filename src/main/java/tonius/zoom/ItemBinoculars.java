package tonius.zoom;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tonius.zoom.client.KeyHandler;

import javax.annotation.Nullable;
import java.util.List;

public class ItemBinoculars extends Item {

    public static final String NAME = "binoculars";
    
    public ItemBinoculars() {
        this.setMaxStackSize(1);
        this.setUnlocalizedName(Zoom.PREFIX + NAME);
        this.setCreativeTab(CreativeTabs.TOOLS);

        this.setRegistryName(NAME);
        GameRegistry.register(this);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        player.setActiveHand(hand);
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, player.getHeldItem(hand));
    }
    
    @Override
    public int getMaxItemUseDuration(ItemStack itemStack) {
        return Integer.MAX_VALUE;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemStack, @Nullable World world, List<String> list, ITooltipFlag bool) {
        list.add(I18n.format("item.zoom.binoculars.desc.1"));
        if (KeyHandler.keyZoom.getKeyCode() != 0) {
            list.add(I18n.format("item.zoom.binoculars.desc.2", TextFormatting.AQUA + GameSettings.getKeyDisplayString(KeyHandler.keyZoom.getKeyCode()) + TextFormatting.GRAY));
        }
    }
}
