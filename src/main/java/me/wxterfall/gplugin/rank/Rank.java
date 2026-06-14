package me.wxterfall.gplugin.rank;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Material;

public enum Rank {

    PLAYER("Player", "<gray></gray>", Material.GRAY_WOOL),
    MOD("Mod", "<green><bold>[MOD]</bold></green>", Material.GREEN_WOOL),
    ADMIN("Admin", "<red>[ADMIN]</red>", Material.RED_WOOL),
    OWNER("Owner", "<red>[OWNER]</red>", Material.NETHER_STAR);


    private static final MiniMessage MINI_MESSAGE = MiniMessage.miniMessage();

    private final String displayName;
    private final String prefix;
    private final Material menuItem;

    Rank(String displayName, String prefix, Material menuItem) {
        this.displayName = displayName;
        this.prefix = prefix;
        this.menuItem = menuItem;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getPrefix() {
        return prefix;
    }

    public Component getPrefixComponent() {
        return MINI_MESSAGE.deserialize(prefix);
    }

    public Material getMenuItem() {
        return menuItem;
    }
}
