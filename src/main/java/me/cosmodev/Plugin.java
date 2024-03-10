package me.cosmodev;

import org.bukkit.plugin.java.JavaPlugin;

public final class Plugin extends JavaPlugin {
    private static Plugin instance;

    @Override
    public void onEnable() {
        instance = this;

        saveDefaultConfig();
        getCommand("test").setExecutor(new ClockCommand());
        getCommand("test").setTabCompleter(new ClockCommand());

    }

    public static Plugin getInstance(){
        return instance;
    }
}
