package com.draco18s.ores.enchantments;

import java.util.Set;

import com.draco18s.ores.OresBase;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.init.Enchantments;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;

public class EnchantmentShatter extends Enchantment {

	public EnchantmentShatter(EntityEquipmentSlot[] slots) {
		super(Enchantment.Rarity.UNCOMMON, EnumEnchantmentType.DIGGER, slots);
	}
	
	@Override
	public int getMinEnchantability(int par1) {
		return 1 + 10 * par1;
	}
	
	@Override
	public int getMaxEnchantability(int par1) {
		return getMinEnchantability(par1) + 15;
	}
	
	@Override
	public int getMaxLevel() {
		return 4;
	}
	
	public boolean canApplyTogether(Enchantment other) {
		boolean ret = super.canApplyTogether(other);
		ret &= other != Enchantments.EFFICIENCY;
		ret &= other != Enchantments.SILK_TOUCH;
		ret &= other != OresBase.enchCracker;
		return ret;
	}
	
	@Override
	public boolean canApply(ItemStack stack) {
		Item i = stack.getItem();
		if(i instanceof ItemTool) {
			ItemTool tool = (ItemTool)i;
			Set<String> classes = tool.getToolClasses(stack);
			for(String cl : classes) {
				if(cl.equals("pickaxe")) {
					return true;
				}
			}
		}
		return false;
	}
	
	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack) {
		return canApply(stack);
	}
}
