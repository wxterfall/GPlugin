package me.wxterfall.gplugin.utils;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class FreezeUtils {

    public static void applyBlindness(Player player) {
        PotionEffect blindness = new PotionEffect(PotionEffectType.BLINDNESS, Integer.MAX_VALUE, 1, false, false);
        player.addPotionEffect(blindness);
    }

    public static void removeBlindness(Player player) {
        player.removePotionEffect(PotionEffectType.BLINDNESS);
    }

    public static void sendTitle(Player player, String title, String subtitle, int stay) {
        player.sendTitle(title, subtitle, 10, stay, 20); // Timing: fadeIn (10 ticks), stay (70 ticks), fadeOut (20 ticks)
    }


}
