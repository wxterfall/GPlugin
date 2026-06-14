package me.wxterfall.gplugin.rank;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public final class RankMenu {

    public static final String MENU_TITLE = "Rank Manager";

    private RankMenu() {
    }

    public static void open(Player staff, Player target) {
        Inventory inventory = Bukkit.createInventory(null, 27, MENU_TITLE + ": " + target.getName());

        int slot = 10;

        for (Rank rank : Rank.values()) {
            ItemStack item = new ItemStack(rank.getMenuItem());
            ItemMeta meta = item.getItemMeta();

            meta.displayName(Component.text("Set rank: " + rank.getDisplayName()));
            item.setItemMeta(meta);

            inventory.setItem(slot, item);
            slot++;
        }

        ItemStack remove = new ItemStack(Material.BARRIER);
        ItemMeta removeMeta = remove.getItemMeta();
        removeMeta.displayName(Component.text("Remove rank"));
        remove.setItemMeta(removeMeta);

        inventory.setItem(16, remove);

        staff.openInventory(inventory);
    }
}