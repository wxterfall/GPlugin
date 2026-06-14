package me.wxterfall.gplugin.listener;

import io.papermc.paper.event.player.AsyncChatEvent;
import me.wxterfall.gplugin.rank.Rank;
import me.wxterfall.gplugin.rank.RankManager;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ChatListener implements Listener {

    @EventHandler
    public void onChat(AsyncChatEvent event) {
        Player player = event.getPlayer();
        Rank rank = RankManager.getInstance().getRank(player);

        event.renderer((source, sourceDisplayName, message, viewer) ->
                rank.getPrefixComponent()
                        .append(Component.space())
                        .append(Component.text(source.getName()))
                        .append(Component.text(": "))
                        .append(message)
        );
    }
}