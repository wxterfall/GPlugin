package me.wxterfall.gplugin.listener;

import me.wxterfall.gplugin.GPlugin;
import me.wxterfall.gplugin.commands.Freeze;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import org.bukkit.Bukkit;
import me.wxterfall.gplugin.utils.FreezeUtils;
import org.bukkit.util.ChatPaginator;

import java.util.UUID;

public class FreezeListener implements Listener {

    @EventHandler
    public static void onMove(PlayerMoveEvent e){
        Player p = e.getPlayer();
        UUID playerUUID = p.getUniqueId();

        if (Freeze.frozen.contains(playerUUID)){
            e.setCancelled(true);
        }
    }

    @EventHandler
    public static void onCommand(PlayerCommandPreprocessEvent e){
        Player p = e.getPlayer();
        UUID playerUUID = p.getUniqueId();
        String command = e.getMessage().toLowerCase();

        if (Freeze.frozen.contains(playerUUID) && !command.startsWith("/discord")) {
            e.setCancelled(true);
            p.sendMessage(ChatColor.RED + "You can only execute the /discord command while frozen!");
        }
    }

    @EventHandler
    public static void onDamage(EntityDamageByEntityEvent e){
        if (e.getDamager() instanceof Player && e.getEntity() instanceof Player) {
            Player damager = (Player) e.getDamager(); // The player who is attacking
            Player target = (Player) e.getEntity();   // The player being attacked
            UUID damagerUUID = damager.getUniqueId();
            boolean damagerFrozen = Freeze.frozen.contains(damagerUUID);
            UUID targetUUID = target.getUniqueId();
            boolean targetFrozen = Freeze.frozen.contains(targetUUID);

            // Prevent damage if one is frozen and the other is not
            if (damagerFrozen && !targetFrozen) {
                e.setCancelled(true);
                damager.sendMessage(ChatColor.RED + "You cannot harm players while you are frozen.");
            } else if (!damagerFrozen && targetFrozen) {
                e.setCancelled(true);
                damager.sendMessage(ChatColor.RED + "This player is currently frozen, you cannot harm them.");
            } else if (damagerFrozen && targetFrozen) {
                e.setCancelled(true);
                damager.sendMessage(ChatColor.RED + "You cannot harm players while you are frozen.");
            }
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        UUID playerUUID = player.getUniqueId();

        // Check if the player was frozen before logging out
        if (Freeze.frozen.contains(playerUUID)) {
            FreezeUtils.applyBlindness(player);  // Reapply blindness when player rejoins
            player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "You have been frozen and must join the 'Waiting screenshare' voice call on our discord!");
            FreezeUtils.sendTitle(player, ChatColor.RED + "" + ChatColor.BOLD + "You are still frozen", ChatColor.YELLOW + "Rejoining does not change that.", 144000);


            for (Player player1 : Bukkit.getOnlinePlayers()) {
                if (player1.hasPermission("staff.freeze") || player1.isOp()) {
                    player1.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + player.getDisplayName() + " has rejoined and is still frozen.");
                }
            }
        }
    }


    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player frozenPlayer = event.getPlayer();
        UUID playerUUID = frozenPlayer.getUniqueId();

        if (Freeze.frozen.contains(playerUUID)) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (player.hasPermission("staff.freeze") || player.isOp()) {
                    player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + frozenPlayer.getDisplayName() + " logged out while frozen.");
                }
            }
        }
    }



}
