package com.github.dennermelo.aura.extraction;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.dennermelo.aura.extraction.command.GiveExtractionCommand;
import com.github.dennermelo.aura.extraction.listener.InventoryListener;

public class Main extends JavaPlugin {

	private PluginManager pm = Bukkit.getPluginManager();

	@Override
	public void onEnable() {
		init();
	}

	private void init() {
		Bukkit.getConsoleSender().sendMessage("§6Aura-Runa: §7Inicializando plugin...");
		pm.registerEvents(new InventoryListener(), this);
		getCommand("giveruna").setExecutor(new GiveExtractionCommand());
	}

	@Override
	public void onDisable() {
		Bukkit.getConsoleSender().sendMessage("§6Aura-Runa: §cDesabilitando plugin...");
	}

}
