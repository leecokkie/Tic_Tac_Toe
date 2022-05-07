package de.lecokkie.tic_tac_toe.Listeners;

import de.lecokkie.tic_tac_toe.Commands.DenyDuellCommand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitListener implements Listener {

    @EventHandler
    public void leaveWhileIngame(PlayerQuitEvent e) {
        Player p = e.getPlayer();

        if(ClickNavigationPreGame.ingame.containsValue(p)) {
            Player p2 = DenyDuellCommand.getKey(ClickNavigationPreGame.ingame, p);

            p2.sendMessage("§8[§eTic Tac Toe§8] §c" + p.getName() + " §7hat das Spiel verlassen, und du somit §agewonnen§7!");
            p2.closeInventory();
        }

        if(ClickNavigationPreGame.ingame.containsKey(p)) {
            Player p2 = ClickNavigationPreGame.ingame.get(p);

            p2.sendMessage("§8[§eTic Tac Toe§8] §c" + p.getName() + " §7hat das Spiel verlassen, und du somit §agewonnen§7!");
            p2.closeInventory();
        }
    }
}