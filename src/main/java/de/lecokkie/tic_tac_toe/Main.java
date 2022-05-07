package de.lecokkie.tic_tac_toe;

import de.lecokkie.tic_tac_toe.Commands.AcceptDuellCommand;
import de.lecokkie.tic_tac_toe.Commands.DenyDuellCommand;
import de.lecokkie.tic_tac_toe.Commands.SetTicTacToeCommand;
import de.lecokkie.tic_tac_toe.Commands.TestCommand;
import de.lecokkie.tic_tac_toe.Listeners.*;
import de.lecokkie.tic_tac_toe.util.FileManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        FileManager.loadList();

        getCommand("setTicTacToe").setExecutor(new SetTicTacToeCommand());
        getCommand("test").setExecutor(new TestCommand());
        getCommand("accept").setExecutor(new AcceptDuellCommand());
        getCommand("deny").setExecutor(new DenyDuellCommand());

        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new ClickNavigationPreGame(), this);
        pluginManager.registerEvents(new InvClickBlocker(), this);
        pluginManager.registerEvents(new RequestListener(), this);
        pluginManager.registerEvents(new LeaveGameListener(), this);
        pluginManager.registerEvents(new QuitListener(), this);
        pluginManager.registerEvents(new SetItemListener(), this);
        pluginManager.registerEvents(new JoinListener(), this);
        pluginManager.registerEvents(new ClickNavigationIngame(), this);
    }

    public static Plugin getPlugin() {
        return Main.getPlugin(Main.class);
    }
}