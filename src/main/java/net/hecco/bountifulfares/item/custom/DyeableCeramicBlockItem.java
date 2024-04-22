package net.hecco.bountifulfares.item.custom;

import net.hecco.bountifulfares.BountifulFares;
import net.hecco.bountifulfares.block.entity.DyeableCeramicBlockEntity;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.BlockItem;
import net.minecraft.item.DyeableItem;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DyeableCeramicBlockItem extends BlockItem implements DyeableItem {
    public int DefColor = DyeableCeramicBlockEntity.DEFAULT_COLOR;
    public DyeableCeramicBlockItem(Block block, Settings settings) {
        super(block, settings);
    }

    @Override
    public int getColor(ItemStack stack) {
        NbtCompound nbtCompound = stack.getSubNbt(DISPLAY_KEY);
        if (nbtCompound != null && nbtCompound.contains(COLOR_KEY, NbtElement.NUMBER_TYPE)) {
            return nbtCompound.getInt(COLOR_KEY);
        }
        return DefColor;
    }

    @Override
    public ActionResult place(ItemPlacementContext context) {
        ActionResult result = super.place(context);
        BlockEntity blockEntity = context.getWorld().getBlockEntity(context.getBlockPos());
        if(blockEntity instanceof DyeableCeramicBlockEntity ceramicTilesBlockEntity){
            ceramicTilesBlockEntity.color = getColor(context.getStack());
        }
        return result;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
//        NbtCompound nbtCompound = stack.getSubNbt(DISPLAY_KEY);
//        if (nbtCompound != null && nbtCompound.contains(COLOR_KEY)) {
        tooltip.add(Text.translatable("tooltip." + BountifulFares.MOD_ID + ".dyeable"));
//        }
        super.appendTooltip(stack, world, tooltip, context);
    }
}
