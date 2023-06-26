package me.autobot.AutoMendingRepair.Config;

import me.autobot.AutoMendingRepair.AutoMendingRepair;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigManager {

    private static File file;
    private static FileConfiguration playerPref;

    public static void setup() {
        if (!AutoMendingRepair.getPlugin().getDataFolder().exists()) {
            AutoMendingRepair.getPlugin().getDataFolder().mkdir();
        }
        file = new File(AutoMendingRepair.getPlugin().getDataFolder(), "playerpref.yml");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        playerPref = YamlConfiguration.loadConfiguration(file);

    }

    public static FileConfiguration get() {
        return playerPref;
    }

    public static void save() {
        try {
            playerPref.save(file);

        } catch (IOException e) {
            System.out.println("Could not save file");
            throw new RuntimeException(e);
        }
    }

    public static void reload() {
        playerPref = YamlConfiguration.loadConfiguration(file);
    }
}
