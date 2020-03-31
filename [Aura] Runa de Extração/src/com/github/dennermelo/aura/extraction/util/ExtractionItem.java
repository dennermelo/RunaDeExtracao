package com.github.dennermelo.aura.extraction.util;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ExtractionItem {

	public ExtractionItem(Player player, int amount) {
		ItemStack item = new ItemStack(Material.NAME_TAG, amount);
		ItemMeta item_meta = item.getItemMeta();
		item_meta.setDisplayName("�6Runa de Extra��o");
		item_meta.setLore(Arrays.asList("�7Utilize este item para extrair", "�7os encantamentos de determinado",
				"�7item.", "", "�7Encantamentos extra�dos por este item", "�7s�o enviados para seu invent�rio",
				"�7em forma de livro."));
		item_meta.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
		item_meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		item.setItemMeta(item_meta);
		player.getInventory().addItem(item);
	}

}
