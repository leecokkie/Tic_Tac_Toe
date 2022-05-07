package de.lecokkie.tic_tac_toe.Commands;

import de.lecokkie.tic_tac_toe.Listeners.ClickNavigationPreGame;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class DenyDuellCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        Player p = (Player) sender;
        if(ClickNavigationPreGame.wantduell.containsValue(p)) {
            Player p2 = getKey(ClickNavigationPreGame.wantduell, p);
            ClickNavigationPreGame.wantduell.remove(p2, p);
            p.sendMessage("§8[§eTic Tac Toe§8] §7Du hast die Herausforderung von §c" + p2.getName() + " §7abgelehnt!");
            p2.sendMessage("§8[§eTic Tac Toe§8] §7Deine Herausforderung wurde von §c" + p.getName() + " §7abgelehnt!");
        }
        return false;
    }

    public static <K, V> K getKey(Map<K, V> map, V value) {
        for(Map.Entry<K, V> entry : map.entrySet()) {
            if(value.equals(entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }
}
