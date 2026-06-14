package me.wxterfall.gplugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class StaffChat implements CommandExecutor{

    @Override
    public boolean onCommand( CommandSender sender,  Command command,  String label,  String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players can use this command.");
            return true;
        }

        Player player = (Player) sender;

        String message = String.join(" ", args);
        if (message.isEmpty()){
            player.sendMessage(ChatColor.RED + "You cannot send an empty staff chat message!");
            return true;
        }

        for (Player staff : sender.getServer().getOnlinePlayers()){
            if (staff.hasPermission("staff.staffchat")){
                staff.sendMessage(ChatColor.GRAY + "[" +ChatColor.RED + "" + ChatColor.BOLD + "S"+ ChatColor.GRAY + "] " + ChatColor.DARK_RED + player.getName() + ChatColor.GRAY + ": " + message);
                return true;
            }
        }
        return false;
    }
}

