package com.github.dennermelo.aura.extraction.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.github.dennermelo.aura.extraction.util.ExtractionItem;

public class GiveExtractionCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lbl, String[] args) {
		if (!(sender instanceof Player)) {
			return true;
		}
		Player player = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("giveruna")) {
			if (player.hasPermission("auraespeciais.runa")) {
				if (args.length == 0) {
					player.sendMessage("§7Para utilizar este comando, digite: §6/giveruna <player> <quantidade>§7.");
					return true;
				}
				if (args.length == 1) {
					Player alvo = Bukkit.getPlayer(args[0]);
					if (alvo != null) {
						// Verifica se o jogador possui 1 slot disponível;
						if (verifySlot(alvo, 1)) {
							new ExtractionItem(alvo, 1);
							player.sendMessage("§7Você enviou 1 Runa de Extração para o jogador " + args[0] + ".");
							return true;
						} else {
							// Caso o jogador não possua 1 slot disponível;
							player.sendMessage("§cEste jogador não possui espaço necessário no inventário.");
							return true;
						}
					} else {
						player.sendMessage("§cO jogador citado é inexistente.");
						return true;
					}
				}
				if (args.length == 2) {
					Player alvo = Bukkit.getPlayer(args[0]);
					int amount;
					try {
						amount = Integer.parseInt(args[1]);
					} catch (NumberFormatException e) {
						player.sendMessage("§cO valor <quantidade> inspecionado não é valido.");
						return true;
					}
					if (alvo != null) {
						if (verifySlot(alvo, amount)) {
							new ExtractionItem(alvo, amount);
							player.sendMessage(
									"§7Você enviou " + amount + " Runa(s) de Extração para o jogador " + args[0] + ".");
							return true;
						} else {
							player.sendMessage("§cEste jogador não possui espaço necessário no inventário.");
							return true;
						}
					} else {
						player.sendMessage("§cO jogador citado não está online.");
						return true;
					}
				}
			}
		}
		return false;
	}

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
