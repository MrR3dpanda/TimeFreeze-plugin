package studio.redpanda.timeFreeze;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class TimeFreezeListener implements Listener {
    private final TimeFreeze plugin;
    private boolean isFrozen = false;

    public TimeFreezeListener(TimeFreeze plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();

        if (item == null || !item.getType().equals(plugin.getFreezeItem())) {
            return;
        }

        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (player.hasPermission("timefreeze.use")) {
                toggleTimeFreeze(player);
                event.setCancelled(true);
            } else {
                player.sendMessage("You don't have permission to use this item!");
            }
        }
    }

    private void toggleTimeFreeze(Player player) {
        if (isFrozen) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "tick unfreeze");
            player.sendMessage("Time has been unfrozen!");
            isFrozen = false;
        } else {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "tick freeze");
            player.sendMessage("Time has been frozen!");
            isFrozen = true;
        }
    }
}