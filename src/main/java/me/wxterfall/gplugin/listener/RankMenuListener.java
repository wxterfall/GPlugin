package me.wxterfall.gplugin.listener;

import me.wxterfall.gplugin.rank.Rank;
import me.wxterfall.gplugin.rank.RankManager;
import me.wxterfall.gplugin.rank.RankMenu;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class RankMenuListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player)) {
            return;
        }

        Player staff = (Player) event.getWhoClicked();

        String title = event.getView().getTitle();

        if (!title.startsWith(RankMenu.MENU_TITLE + ": ")) {
            return;
        }

        event.setCancelled(true);

        if (event.getCurrentItem() == null || event.getCurrentItem().getType() == Material.AIR) {
            return;
        }

        String targetName = title.replace(RankMenu.MENU_TITLE + ": ", "");
        Player target = Bukkit.getPlayer(targetName);

        if (target == null) {
            staff.sendMessage("That player is no longer online.");
            staff.closeInventory();
            return;
        }

        Material clicked = event.getCurrentItem().getType();

        if (clicked == Material.BARRIER) {
            RankManager.getInstance().removeRank(target.getUniqueId());
            staff.sendMessage("Removed " + target.getName() + "'s rank.");
            staff.closeInventory();
            return;
        }

        for (Rank rank : Rank.values()) {
            if (rank.getMenuItem() == clicked) {
                RankManager.getInstance().setRank(target.getUniqueId(), rank);
                staff.sendMessage("Set " + target.getName() + "'s rank to " + rank.getDisplayName() + ".");
                staff.closeInventory();
                return;
            }
        }
    }
}