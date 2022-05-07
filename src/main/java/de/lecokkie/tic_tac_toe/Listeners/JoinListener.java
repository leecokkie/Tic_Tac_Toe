package de.lecokkie.tic_tac_toe.Listeners;

import de.lecokkie.tic_tac_toe.util.FileManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        FileManager.createPlayer(p);
    }

}
