package me.autobot.AutoMendingRepair;

import me.autobot.AutoMendingRepair.Config.ConfigManager;
import org.bukkit.plugin.java.JavaPlugin;
import me.autobot.AutoMendingRepair.Command.CommandManager;
import me.autobot.AutoMendingRepair.Events.ItemDamage;

public final class AutoMendingRepair extends JavaPlugin {
    private static AutoMendingRepair plugin;
    @Override
    public void onEnable() {
        // Plugin startup logic

        plugin = this;

        ConfigManager.setup();

        getCommand("automending").setExecutor(new CommandManager());
        getServer().getPluginManager().registerEvents(new ItemDamage(),this);
    }

    public static AutoMendingRepair getPlugin() {
        return plugin;
    }
}
