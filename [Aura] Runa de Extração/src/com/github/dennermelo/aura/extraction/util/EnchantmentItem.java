package com.github.dennermelo.aura.extraction.util;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;

public class EnchantmentItem {

	public EnchantmentItem(Player player, Enchantment ench, int level) {
		ItemStack book = new ItemStack(Material.ENCHANTED_BOOK);
		EnchantmentStorageMeta bookm = (EnchantmentStorageMeta) book.getItemMeta();
		bookm.addEnchant(ench, level, true);
		bookm.setDisplayName("§eLivro Especial");
		bookm.setLore(Arrays.asList("", "§7Este livro foi extraído através de um", "§7item especial.", "",
				"§7Extraído por: §e" + player.getName()));
		book.setItemMeta(bookm);
		player.getInventory().addItem(book);
	}

}
