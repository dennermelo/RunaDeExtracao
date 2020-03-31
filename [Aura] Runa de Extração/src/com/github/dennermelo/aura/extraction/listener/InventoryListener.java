package com.github.dennermelo.aura.extraction.listener;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import com.github.dennermelo.aura.extraction.util.EnchantmentItem;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

public class InventoryListener implements Listener {

	private static final ImmutableSet<Material> itens_permitidos = Sets.immutableEnumSet(Material.DIAMOND_AXE,
			Material.GOLD_AXE, Material.IRON_AXE, Material.WOOD_AXE, Material.STONE_AXE, Material.DIAMOND_SWORD,
			Material.GOLD_SWORD, Material.IRON_SWORD, Material.WOOD_SWORD, Material.STONE_SWORD,
			Material.DIAMOND_PICKAXE, Material.GOLD_PICKAXE, Material.IRON_PICKAXE, Material.WOOD_PICKAXE,
			Material.STONE_PICKAXE, Material.DIAMOND_SPADE, Material.GOLD_SPADE, Material.IRON_SPADE,
			Material.WOOD_SPADE, Material.STONE_SPADE, Material.DIAMOND_BOOTS, Material.GOLD_BOOTS, Material.IRON_BOOTS,
			Material.LEATHER_BOOTS, Material.CHAINMAIL_BOOTS, Material.DIAMOND_CHESTPLATE, Material.GOLD_CHESTPLATE,
			Material.IRON_CHESTPLATE, Material.LEATHER_CHESTPLATE, Material.CHAINMAIL_CHESTPLATE,
			Material.DIAMOND_LEGGINGS, Material.GOLD_LEGGINGS, Material.IRON_LEGGINGS, Material.LEATHER_LEGGINGS,
			Material.CHAINMAIL_LEGGINGS, Material.DIAMOND_HELMET, Material.GOLD_HELMET, Material.IRON_HELMET,
			Material.LEATHER_HELMET, Material.CHAINMAIL_HELMET);

	@SuppressWarnings("deprecation")
	@EventHandler
	public void event(final InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();
		ItemStack clicked = event.getCurrentItem();
		ItemStack tool = event.getCursor();

		if (event.getAction() == InventoryAction.SWAP_WITH_CURSOR
				&& tool.getItemMeta().getDisplayName().equals("§6Runa de Extração")) {
			if (itens_permitidos.contains(clicked.getType())) {
				if (clicked.getItemMeta().hasEnchants()) {
					if (verifySlot(player, clicked.getEnchantments().size())) {

						for (Enchantment ench : clicked.getEnchantments().keySet()) {
							// Loop por todos encantamentos de um item;
							new EnchantmentItem(player, ench, clicked.getEnchantmentLevel(ench));
							clicked.removeEnchantment(ench);
						}
						if (tool.getAmount() > 1) {
							tool.setAmount(tool.getAmount() - 1);
						} else {
							event.setCursor(new ItemStack(Material.AIR));
						}
						player.playSound(player.getLocation(), Sound.HORSE_SADDLE, 1.0F, 1.0F);
						event.setCancelled(true);
						return;
					} else {
						// Caso o jogador não possua espaço no inventário;
						player.sendMessage("§cParece que seu inventário não possui espaço para a extração.");
						event.setCancelled(true);
						return;
					}
				} else {
					// Caso o item selecionado não possua encantamentos;
					player.sendMessage(
							"§cParece que o item selecionado não possui encantamentos para serem extraídos.");
					event.setCancelled(true);
					return;
				}

			} else {
				// Caso o item selecionado não pertença ao ImutableSet(itens_permitidos);
				player.sendMessage("§cEste item não permite extração de encantamentos.");
				event.setCancelled(true);
				return;
			}
		}
	}

	// Pequeno sistema para verificar se há slots disponíveis;
	public boolean verifySlot(Player player, int required) {
		int i = 0;
		for (ItemStack item : player.getInventory()) {
			if (item == null) {
				i++;
			}
		}
		if (i >= required) {
			return true;
		} else {
			return false;
		}
	}

}
