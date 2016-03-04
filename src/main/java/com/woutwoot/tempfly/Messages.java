package com.woutwoot.tempfly;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

/**
 * @author woutwoot
 *         Created by on 6/03/2015 - 21:58.
 */
public class Messages {

    private static FileConfiguration conf;

    public static String get(String key) {
        if (conf.getString(key) == null) {
            return "Message key \"" + key + "\" not found in messages.yml";
        }
        return ChatColor.translateAlternateColorCodes('&', conf.getString(key));
    }

    public static void load() {
        try {
            loadMessages();
        } catch (Exception e) {
            Main.instance.getLogger().warning("Failed to load messages.yml!");
        }
    }

    private static void loadMessages() throws Exception {
        conf = new YamlConfiguration();
        File confFile = new File(Main.instance.getDataFolder(), "messages.yml");
        addDefaults();
        if (confFile.exists()) {
            conf.load(confFile);
        } else {
            conf.options().copyDefaults(true);
            conf.save(confFile);
        }
    }

    private static void addDefaults() {
        d("flight_worn_off", "Your flight ability has worn off.");
        d("can_fly_for_time", "You can now fly for {time}.");
        d("already_have_flight_for_time", "You have {time} of flight left!");
        d("wait_for_time_cooldown", "You have to wait {time} before you can use this command again!");
    }

    private static void d(String s, String t) {
        conf.addDefault(s, t);
    }

}
