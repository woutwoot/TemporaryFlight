package com.woutwoot.tempfly;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.*;
/**
 * Created by WoutP on 3/03/2016.
 */
public class Main extends JavaPlugin {

    public static Main instance;

    private Map<UUID, Flyer> flyers = new HashMap<>();

    @Override
    public void onEnable(){
        instance = this;
        loadConfig();
        Messages.load();
        this.getCommand("tfly").setExecutor(new CommandHandler());
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new FlightTask(), 20L, 20L);
    }

    @Override
    public void onDisable(){
        for(Flyer f : flyers.values()){
            f.stop();
        }
    }

    public Map<UUID, Flyer> getFlyers() {
        return flyers;
    }

    public void loadConfig(){
        this.getDataFolder().mkdirs();
        try {
            this.getConfig().load(new File(this.getDataFolder(), "config.yml"));
        } catch (IOException | InvalidConfigurationException e) {
            this.getLogger().warning("Failed to load config. Generating a new config file...");
            this.getConfig().addDefault("FlightDuration", 20);
            this.getConfig().addDefault("FlightCooldown", 600);
            this.getConfig().addDefault("PluginTag", Vars.tag);
            this.getConfig().options().copyDefaults(true);
            this.getConfig().options().header("test");
            try {
                this.getConfig().save(new File(this.getDataFolder(), "config.yml"));
            } catch (IOException ignored) {}
        }
        Vars.tag = ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("PluginTag"));
    }

    public void handleFlightCommand(Player p) {
        if(flyers.containsKey(p.getUniqueId())){
            flyers.get(p.getUniqueId()).handleCommand();
        }else {
            flyers.put(p.getUniqueId(), new Flyer(p.getUniqueId()));
        }
    }
}
