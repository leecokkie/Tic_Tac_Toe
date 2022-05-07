package de.lecokkie.tic_tac_toe.Listeners;

import de.lecokkie.tic_tac_toe.Commands.DenyDuellCommand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class LeaveGameListener implements Listener {

    @EventHandler
    public void closeInvWhileIngame(InventoryCloseEvent e) {
        Player p = (Player) e.getPlayer();
        if(ClickNavigationPreGame.ingame.containsKey(p)) {
            Player p2 = ClickNavigationPreGame.ingame.get(p);

            ClickNavigationPreGame.ingame.remove(p, p2);

            p.sendMessage("§8[§eTic Tac Toe§8] §7Du hast das Spiel verlassen, und somit §cverloren§7!");
            p2.sendMessage("§8[§eTic Tac Toe§8] §c" + p.getName() + " §7hat das Spiel verlassen, und du hast somit §agewonnen§7!");

            p2.closeInventory();
            return;
        }

        if(ClickNavigationPreGame.ingame.containsValue(p)) {
            Player p2 = DenyDuellCommand.getKey(ClickNavigationPreGame.ingame, p);

            ClickNavigationPreGame.ingame.remove(p2, p);

            p.sendMessage("§8[§eTic Tac Toe§8] §7Du hast das Spiel verlassen, und somit §cverloren§7!");
            p2.sendMessage("§8[§eTic Tac Toe§8] §c" + p.getName() + " §7hat das Spiel verlassen, und du hast somit §agewonnen§7!");

            p2.closeInventory();
        }
    }
}