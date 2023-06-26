package me.autobot.AutoMendingRepair.Events;

import me.autobot.AutoMendingRepair.Config.ConfigManager;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;


public class ItemDamage implements Listener {
    @EventHandler
    public void Deterioration(PlayerItemDamageEvent event) {
        Player player = event.getPlayer();

        if (ConfigManager.get().getInt(player.getUniqueId().toString()) != 1) {return;}

        if (player.getLevel() == 0 && getPlayerPoints(player) < 1) {return;}

        ItemStack item = event.getItem();

        Damageable tool = null;
        if (item.getItemMeta() instanceof Damageable) {
            tool = (Damageable) item.getItemMeta();
        }
        if (tool == null) {return;}

        boolean containMending = tool.hasEnchant(Enchantment.MENDING);
        if (!containMending) {return;}

        if (!tool.hasDamage() || tool.getDamage() < 2) {return;} //1 EXP per 2 Durability

        if (getPlayerPoints(player) >= 1) {
            player.setExp((getPlayerPoints(player)-1)/player.getExpToLevel());
        } else {
            float exp = player.getExp();
            player.setLevel(player.getLevel()-1);
            player.setExp((player.getExpToLevel()-1+exp)/player.getExpToLevel());
        }
        tool.setDamage(Math.max(0, tool.getDamage()-2));
        item.setItemMeta(tool);

    }

    public float getPlayerPoints(Player player) {
        return player.getExpToLevel() * player.getExp();
    }
}
