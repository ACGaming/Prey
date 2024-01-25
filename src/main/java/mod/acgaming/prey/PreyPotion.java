package mod.acgaming.prey;

import java.util.Collections;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.EnumDifficulty;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PreyPotion extends Potion
{
    public static final ResourceLocation PREY_ICON = new ResourceLocation(Prey.MOD_ID, "textures/gui/prey_icon.png");

    public PreyPotion()
    {
        super(true, 16711680);
    }

    @Override
    public void performEffect(EntityLivingBase entity, int amplifier)
    {
        if (entity.world.getDifficulty() == EnumDifficulty.PEACEFUL || entity.ticksExisted % PreyConfig.tickingRate != 0) return;

        int increasedFollowRange;

        switch (amplifier)
        {
            case 0:
                increasedFollowRange = PreyConfig.preyDistanceI;
                break;
            case 1:
                increasedFollowRange = PreyConfig.preyDistanceII;
                break;
            case 2:
                increasedFollowRange = PreyConfig.preyDistanceIII;
                break;
            case 3:
                increasedFollowRange = PreyConfig.preyDistanceIV;
                break;
            default:
                increasedFollowRange = PreyConfig.preyDistanceV;
                break;
        }

        entity.world.getEntitiesWithinAABB(EntityMob.class, entity.getEntityBoundingBox().grow(32.0D + increasedFollowRange)).forEach(mob -> {
            if (!mob.isEntityAlive() || mob.isAIDisabled()) return;

            IAttributeInstance followRange = mob.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE);

            if (followRange.getAttributeValue() < followRange.getBaseValue() + increasedFollowRange) followRange.setBaseValue(followRange.getBaseValue() + increasedFollowRange);
        });
    }

    @Override
    public final boolean isReady(int duration, int amplifier)
    {
        return duration > 0;
    }

    @Override
    public boolean shouldRender(PotionEffect effect)
    {
        return PreyConfig.shouldRenderInv;
    }

    @Override
    public boolean shouldRenderHUD(PotionEffect effect)
    {
        return PreyConfig.shouldRenderHUD;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void renderInventoryEffect(PotionEffect effect, Gui gui, int x, int y, float z)
    {
        Minecraft.getMinecraft().renderEngine.bindTexture(PREY_ICON);
        Gui.drawModalRectWithCustomSizedTexture(x + 6, y + 7, 0, 0, 18, 18, 18, 18);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void renderHUDEffect(PotionEffect effect, Gui gui, int x, int y, float z, float alpha)
    {
        Minecraft.getMinecraft().renderEngine.bindTexture(PREY_ICON);
        Gui.drawModalRectWithCustomSizedTexture(x + 3, y + 3, 0, 0, 18, 18, 18, 18);
    }

    @Override
    public List<ItemStack> getCurativeItems()
    {
        return PreyConfig.isCurable ? super.getCurativeItems() : Collections.emptyList();
    }
}