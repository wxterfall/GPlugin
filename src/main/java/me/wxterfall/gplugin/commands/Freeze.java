package me.wxterfall.gplugin.commands;

import me.wxterfall.gplugin.GPlugin;
import me.wxterfall.gplugin.utils.FreezeUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;
import java.util.HashSet;
import java.lang.Thread;
import java.util.UUID;

public class Freeze implements CommandExecutor{

    public static HashSet<UUID> frozen = new HashSet<>();


    public boolean onCommand(CommandSender sender, Command command, String s, String[] args){

        if (!command.getName().equalsIgnoreCase("freeze")){
            return false;
        }

        if (!sender.hasPermission("staff.freeze")){
            sender.sendMessage(ChatColor.RED + "You do not have permission to use this command.");
            return true;
        }

        if (args.length != 1){
            sender.sendMessage(ChatColor.RED + "Usage: /freeze <player>");
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);

        if (target == null){
            sender.sendMessage(ChatColor.RED + "Player is not online.");
            return true;
        }

        if (sender.equals(target)) {
            sender.sendMessage(ChatColor.RED + "You cannot freeze yourself.");
            return true;
        }

        if (target.isOp() || target.hasPermission("staff.freeze")) {
            sender.sendMessage(ChatColor.RED + "You cannot freeze " + ChatColor.RED + target.getDisplayName() + ChatColor.RED + " because they are an operator or have the staff.freeze permission.");
            return true;
        }


        Player p = (Player) sender;
        UUID targetUUID = target.getUniqueId();

        if (frozen.contains(targetUUID)){
            frozen.remove(targetUUID);
            FreezeUtils.removeBlindness(target);
            FreezeUtils.sendTitle(target, ChatColor.GREEN + "" + ChatColor.BOLD + "You have been unfrozen", ChatColor.YELLOW + "by " + p.getDisplayName(), 70);
            sender.sendMessage(ChatColor.GREEN + "You have unfrozen " + ChatColor.RESET + target.getDisplayName());
            target.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "You have been unfrozen!");
        } else {
            frozen.add(targetUUID);
            FreezeUtils.applyBlindness(target);
            FreezeUtils.sendTitle(target, ChatColor.RED + "" + ChatColor.BOLD + "You have been frozen", ChatColor.YELLOW + "by " + p.getDisplayName(), 144000);
            sender.sendMessage(ChatColor.RED + "You have frozen " + ChatColor.RESET + target.getDisplayName());
            target.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "You have been frozen and must join the 'Waiting screenshare' voice call on our discord!");
        }


        return true;
    }



}
