package studio.redpanda.timeFreeze;


import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class TimeFreeze extends JavaPlugin {
    private Material freezeItem;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        loadConfig();

        getServer().getPluginManager().registerEvents(new TimeFreezeListener(this), this);
        getCommand("timefreeze").setExecutor(new TimeFreezeCommand(this));

        getLogger().info("TimeFreeze plugin has been enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("TimeFreeze plugin has been disabled!");
    }

    public void loadConfig() {
        reloadConfig();
        FileConfiguration config = getConfig();
        String itemName = config.getString("freeze_item", "CLOCK");
        try {
            freezeItem = Material.valueOf(itemName.toUpperCase());
        } catch (IllegalArgumentException e) {
            getLogger().warning("Invalid freeze_item in config.yml. Using default: CLOCK");
            freezeItem = Material.CLOCK;
        }
    }

    public Material getFreezeItem() {
        return freezeItem;
    }
}