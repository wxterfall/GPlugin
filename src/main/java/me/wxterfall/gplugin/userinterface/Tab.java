package me.wxterfall.gplugin.userinterface;

import me.wxterfall.gplugin.setting.Settings;
import org.bukkit.Bukkit;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.entity.Player;
import me.wxterfall.gplugin.rank.Rank;
import me.wxterfall.gplugin.rank.RankManager;
import net.kyori.adventure.text.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Tab implements Runnable
{
    private static final Tab instance = new Tab();

    private final Map<UUID, Integer> headerPositions = new HashMap<>();
    private final Map<UUID, Integer> footerPositions = new HashMap<>();

    private final MiniMessage miniMessage = MiniMessage.miniMessage();

    private Tab() {
    }

    @Override
    public void run() {
        List<String> headerLines = Settings.getInstance().getHeaderLines();
        List<String> footerLines = Settings.getInstance().getFooterLines();
        boolean animated = Settings.getInstance().isTabAnimated();

        if (headerLines == null || headerLines.isEmpty()) {
            Bukkit.getLogger().warning("[GPlugin] tab.header is missing or empty in config.yml");
            return;
        }

        if (footerLines == null || footerLines.isEmpty()) {
            Bukkit.getLogger().warning("[GPlugin] tab.footer is missing or empty in config.yml");
            return;
        }


        for (Player player : Bukkit.getOnlinePlayers()) {
            String header;
            String footer;

            if (animated) {
                int headerPosition = headerPositions.getOrDefault(player.getUniqueId(), 0);
                int footerPosition = footerPositions.getOrDefault(player.getUniqueId(), 0);

                if (headerPosition >= headerLines.size()) {
                    headerPosition = 0;
                }

                if (footerPosition >= footerLines.size()) {
                    footerPosition = 0;
                }

                header = headerLines.get(headerPosition);
                footer = footerLines.get(footerPosition);

                headerPositions.put(player.getUniqueId(), headerPosition + 1);
                footerPositions.put(player.getUniqueId(), footerPosition + 1);
            } else {
                header = String.join("\n", headerLines);
                footer = String.join("\n", footerLines);
            }

            Rank rank = RankManager.getInstance().getRank(player);

            player.playerListName(
                    rank.getPrefixComponent()
                            .append(Component.space())
                            .append(Component.text(player.getName()))
            );

            player.sendPlayerListHeaderAndFooter(
                    miniMessage.deserialize(replaceVariables(player, header)),
                    miniMessage.deserialize(replaceVariables(player, footer))
            );
        }
    }

    private String replaceVariables(Player player, String message) {
        return message
                .replace("{player}", player.getName())
                .replace("{online}", String.valueOf(Bukkit.getOnlinePlayers().size()));
    }

    public static Tab getInstance() {
        return instance;
    }
}
