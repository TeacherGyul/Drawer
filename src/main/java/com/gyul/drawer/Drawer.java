package com.gyul.drawer;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Drawer extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getPluginManager().registerEvents(new Dr_Handler(),this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
