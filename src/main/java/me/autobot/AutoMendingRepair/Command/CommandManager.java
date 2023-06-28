package me.autobot.AutoMendingRepair.Command;

import me.autobot.AutoMendingRepair.Config.ConfigManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.UUID;

public class CommandManager implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Player p = (Player) commandSender;
            UUID uuid = p.getUniqueId();

            FileConfiguration playerPref = ConfigManager.get();

            switch (playerPref.getInt(uuid.toString())) {
                case (1):
                    playerPref.set(uuid.toString(),0);
                    p.sendMessage(ChatColor.RED + "Auto Mending Repair: OFF");
                    break;
                case (0):
                    playerPref.set(uuid.toString(),1);
                    p.sendMessage(ChatColor.GREEN + "Auto Mending Repair: ON");
                    break;
                default:
                    playerPref.set(uuid.toString(),1);
                    p.sendMessage(ChatColor.GREEN + "Auto Mending Repair: ON");
                    break;
            }

            ConfigManager.get().options().copyDefaults(true);
            ConfigManager.save();


        }
        return true;
    }
}
