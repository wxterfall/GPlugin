package me.wxterfall.gplugin;

import me.wxterfall.gplugin.commands.*;
import me.wxterfall.gplugin.listener.FreezeListener;
import org.bukkit.plugin.java.JavaPlugin;
import me.wxterfall.gplugin.userinterface.Board;
import me.wxterfall.gplugin.userinterface.Tab;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.NotNull;
import me.wxterfall.gplugin.utils.Scheduler;
import me.wxterfall.gplugin.setting.Settings;
import me.wxterfall.gplugin.commands.Ranks;
import me.wxterfall.gplugin.listener.ChatListener;
import me.wxterfall.gplugin.listener.RankMenuListener;
import me.wxterfall.gplugin.rank.RankManager;


public final class GPlugin extends JavaPlugin {
    final String version = getDescription().getVersion();
    private @NotNull BukkitTask boardTask;
    private Scheduler.Task tabTask;

    @Override
    public void onEnable()
    {
        // Plugin startup logic
        getLogger().info("GPlugin loading!");

        Settings.getInstance().load();
        RankManager.getInstance().load();

        getCommand("fly").setExecutor(new Fly());
        getCommand("discord").setExecutor(new Discord());
        getCommand("vanish").setExecutor(new Vanish());
        getCommand("staffchat").setExecutor(new StaffChat());
        getCommand("freeze").setExecutor(new Freeze());
        getCommand("ranks").setExecutor(new Ranks());

        getServer().getPluginManager().registerEvents(new FreezeListener(), this);
        getServer().getPluginManager().registerEvents(new RankMenuListener(), this);
        getServer().getPluginManager().registerEvents(new ChatListener(), this);

        boardTask = getServer().getScheduler().runTaskTimer(this, Board.getInstance(), 0, 20 /* update time, higher == less lag */); // will be used for updating scoreboard

        tabTask = Scheduler.runTimer(Tab.getInstance(), 0, 20);

        getLogger().info("GPlugin loaded!");
        getLogger().info("===================================");
        getLogger().info("GPlugin has been enabled!");
        getLogger().info("Version " + version);
        getLogger().info("Developed with <3 by Oscar");
        getLogger().info("===================================");
    }

    @Override
    public void onDisable()
    {
        // Plugin shutdown logic
        getLogger().info("GPlugin disabling!");

        if (boardTask != null)
        {
            boardTask.cancel();
        }
        if (tabTask != null)
        {
            tabTask.cancel();
        }

        getLogger().info("===================================");
        getLogger().info("GPlugin has been disabled!");
        getLogger().info("===================================");
    }

    public static GPlugin getInstance()
    {
        return getPlugin(GPlugin.class);
    }

}
