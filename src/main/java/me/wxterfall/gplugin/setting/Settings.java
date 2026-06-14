package me.wxterfall.gplugin.setting;

import java.io.File;
import java.util.List;
import java.util.Set;

import me.wxterfall.gplugin.GPlugin;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.Bukkit;


public final class Settings {

    private final static Settings instance = new Settings();

    private File file;
    private YamlConfiguration config;

    private List<String> headerLines;
    private List<String> footerLines;
    private boolean tabAnimated;

    private Settings() {
    }

    public void load() {
        file = new File(GPlugin.getInstance().getDataFolder(), "config.yml");

        if (!file.exists())
            GPlugin.getInstance().saveResource("config.yml", false);

        config = new YamlConfiguration();

        try {
            config.options().parseComments(true);
        } catch (Throwable ignored) {
        }

        try {
            config.load(file);

        } catch (final Exception ex) {
            ex.printStackTrace();
        }

        tabAnimated = config.getBoolean("tab.animated", false);
        headerLines = config.getStringList("tab.header");
        footerLines = config.getStringList("tab.footer");

        Bukkit.getLogger().info("[GPlugin] tab.animated = " + tabAnimated);
    }

    public void save() {
        try {
            config.save(file);

        } catch (final Exception ex) {
            ex.printStackTrace();
        }
    }

    public void set(String path, Object value) {
        config.set(path, value);
        save();
    }

    public List<String> getHeaderLines() {
        return headerLines;
    }

    public List<String> getFooterLines() {
        return footerLines;
    }

    public static Settings getInstance() {
        return instance;
    }

    public boolean isTabAnimated(){
        return tabAnimated;
    }
}