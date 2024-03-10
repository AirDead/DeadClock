package me.cosmodev;

import me.cosmodev.Commands.ClockCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class Plugin extends JavaPlugin {
    private static Plugin instance;

    @Override
    public void onEnable() {
        instance = this;

        saveDefaultConfig();
        getCommand("test").setExecutor(new ClockCommand());

    }

    public static Plugin getInstance(){
        return instance;
    }
}
