package me.wxterfall.gplugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public final class Fly implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String [] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players may run this command.");
            return true;
        }
        Player player = (Player) sender;
        if (player.getAllowFlight()){
            player.setAllowFlight(false);
            player.sendMessage("Flight disabled");
        } else {
            player.setAllowFlight(true);
            player.sendMessage("Flight enabled");
        }

        return true;
    }
}
