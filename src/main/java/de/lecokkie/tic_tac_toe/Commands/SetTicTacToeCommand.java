package de.lecokkie.tic_tac_toe.Commands;

import de.lecokkie.tic_tac_toe.util.FileManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SetTicTacToeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(!(sender instanceof Player)) {
            return false;
        }
        Player p = (Player) sender;

        if(!(args.length == 1)) {
            p.sendMessage("§8[§eTic Tac Toe§8] §7Bitte benutze §a/setTicTacToe §7<§aname§7>");
            return false;
        }
        if(!p.hasPermission("*")) {
            p.sendMessage("§8[§eTic Tac Toe§8] §7Dazu hast du nicht genügend Rechte!");
            return false;
        }

        String name = args[0];
        FileManager.createTicTacToeBlock(name, p);

        return false;
    }
}