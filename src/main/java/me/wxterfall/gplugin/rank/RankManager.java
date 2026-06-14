package me.wxterfall.gplugin.rank;

import me.wxterfall.gplugin.GPlugin;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.UUID;

public final class RankManager {

    private static final RankManager instance = new RankManager();

    private File file;
    private YamlConfiguration config;

    private RankManager() {
    }

    public void load() {
        file = new File(GPlugin.getInstance().getDataFolder(), "ranks.yml");

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        config = YamlConfiguration.loadConfiguration(file);
    }

    public void save() {
        try {
            config.save(file);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Rank getRank(Player player) {
        String rankName = config.getString("players." + player.getUniqueId(), "PLAYER");

        try {
            return Rank.valueOf(rankName.toUpperCase());
        } catch (IllegalArgumentException ex) {
            return Rank.PLAYER;
        }
    }

    public void setRank(UUID uuid, Rank rank) {
        config.set("players." + uuid, rank.name());
        save();
    }

    public void removeRank(UUID uuid) {
        config.set("players." + uuid, null);
        save();
    }

    public static RankManager getInstance() {
        return instance;
    }
}