package me.wxterfall.gplugin.userinterface;

import me.wxterfall.gplugin.GPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scoreboard.*;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;


public class Board implements Runnable
{
    private final static Board instance = new Board();

    private Board()
    {
    }

    @Override
    public void run()
    {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.getScoreboard() != null && player.getScoreboard().getObjective(GPlugin.getInstance().getName()) != null)
                updateScoreboard(player);
            else
                createNewScoreboard(player);
        }
    }
    private void createNewScoreboard(Player player) {
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective(GPlugin.getInstance().getName(), "dummy");

        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName(ChatColor.DARK_PURPLE + "Test" + ChatColor.LIGHT_PURPLE + "Server " + ChatColor.DARK_PURPLE + "| " + ChatColor.YELLOW + ChatColor.BOLD.toString() + "S1");

        int playerCount = Bukkit.getOnlinePlayers().size();
        int maxPlayers = Bukkit.getMaxPlayers();
        double TPS = Bukkit.getServer().getTPS()[0];

        objective.getScore(ChatColor.DARK_AQUA + "Hello, " + ChatColor.WHITE + player.getName() + ChatColor.DARK_AQUA + "!").setScore(8);
        objective.getScore(ChatColor.GRAY + "  ").setScore(7);
        objective.getScore(ChatColor.GRAY + "| " + ChatColor.DARK_AQUA + "Deaths: " + ChatColor.WHITE).setScore(6);
        objective.getScore(ChatColor.GRAY + "| " + ChatColor.DARK_AQUA + "KDR: " + ChatColor.WHITE).setScore(5);
        objective.getScore(ChatColor.GRAY + "| " + ChatColor.DARK_AQUA + "TPS: " + ChatColor.WHITE + String.format("%.2f", TPS)).setScore(4);
        objective.getScore(ChatColor.GRAY + "| " + ChatColor.DARK_AQUA + "Ping: " + ChatColor.WHITE + player.getPing() + "ms").setScore(3);
        objective.getScore(ChatColor.GRAY + "| " + ChatColor.DARK_AQUA + "Online: " + ChatColor.WHITE + playerCount + "/" + ChatColor.WHITE + maxPlayers).setScore(2);
        objective.getScore(ChatColor.GRAY + " " ).setScore(1);
        objective.getScore(ChatColor.GRAY + "Server.minehut.gg").setScore(0);

        player.setScoreboard(scoreboard);
    }

    private void updateScoreboard(Player player) {

    }

    public static Board getInstance(){
        return instance;
    }
}
