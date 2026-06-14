package me.wxterfall.gplugin.commands;

import net.md_5.bungee.api.ChatColor;
import org.bukkit. command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;

public final class Discord implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String [] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players may run this command.");
            return true;
        }
        Player player = (Player) sender;

        // Create the clickable message
        TextComponent message = new TextComponent("Click this to join our Discord!");
        message.setColor(ChatColor.DARK_AQUA);
        message.setBold(true);

        // Set the clickable event to open the URL
        message.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://discord.gg/x"));

        // Send the clickable message to the player
        player.spigot().sendMessage(message);

        return true;
    }
}

