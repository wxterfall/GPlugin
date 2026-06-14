package me.wxterfall.gplugin.commands;

import me.wxterfall.gplugin.rank.RankMenu;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Ranks implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command.");
            return true;
        }

        Player staff = (Player) sender;

        if (!staff.hasPermission("gplugin.ranks")) {
            staff.sendMessage("You do not have permission to use this.");
            return true;
        }

        if (args.length != 1) {
            staff.sendMessage("Usage: /ranks <player>");
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);

        if (target == null) {
            staff.sendMessage("That player is not online.");
            return true;
        }

        RankMenu.open(staff, target);
        return true;
    }
}