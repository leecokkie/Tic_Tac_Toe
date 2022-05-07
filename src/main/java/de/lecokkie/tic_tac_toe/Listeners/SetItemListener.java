package de.lecokkie.tic_tac_toe.Listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class SetItemListener implements Listener {

    @EventHandler
    public void setItem(InventoryClickEvent e) {
        Player p = (Player) e.getView().getPlayer();
        if(!e.getView().getTitle().startsWith("§9")) {
            return;
        }
        if(e.getView().getItem(1) == null) {
            return;
        }
        ItemStack glasspane = e.getView().getItem(1);
        if(!glasspane.getType().equals(Material.YELLOW_STAINED_GLASS_PANE)) {
            return;
        }
        if(!glasspane.getItemMeta().getDisplayName().equals(" ")) {
            return;
        }

        if(e.getCurrentItem() == null) {

        }
    }
}