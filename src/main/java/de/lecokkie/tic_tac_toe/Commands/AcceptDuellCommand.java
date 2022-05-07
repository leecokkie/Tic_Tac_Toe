package de.lecokkie.tic_tac_toe.Commands;

import de.lecokkie.tic_tac_toe.Listeners.ClickNavigationPreGame;
import de.lecokkie.tic_tac_toe.util.TicTacToeInv;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class AcceptDuellCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        Player p = (Player) sender;

        if(!ClickNavigationPreGame.wantduell.containsValue(p)) {
            return false;
        }

        Player p2 = DenyDuellCommand.getKey(ClickNavigationPreGame.wantduell, p);

        ClickNavigationPreGame.wantduell.remove(p2, p);
        ClickNavigationPreGame.ingame.put(p2, p);

        p.sendMessage("§8[§eTic Tac Toe§8] §7Das Spiel gegen §a" + p2.getName() + " §7wurde gestartet!");
        p2.sendMessage("§8[§eTic Tac Toe§8] §7Das Spiel gegen §a" + p.getName() + " §7wurde gestartet!");

        ClickNavigationPreGame.removePlayerFromLists(p);
        ClickNavigationPreGame.removePlayerFromLists(p2);

        TicTacToeInv.openPlayInventory(p, p2);

        return false;
    }
}