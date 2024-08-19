package studio.redpanda.timeFreeze;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TimeFreezeCommand implements CommandExecutor {
    private final TimeFreeze plugin;

    public TimeFreezeCommand(TimeFreeze plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
            if (sender instanceof Player && !sender.hasPermission("timefreeze.reload")) {
                sender.sendMessage("You don't have permission to use this command!");
                return true;
            }

            plugin.loadConfig();
            sender.sendMessage("TimeFreeze configuration reloaded!");
            return true;
        }

        sender.sendMessage("Usage: /timefreeze reload");
        return true;
    }
}