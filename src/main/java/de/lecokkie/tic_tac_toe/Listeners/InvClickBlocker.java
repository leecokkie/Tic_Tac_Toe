package de.lecokkie.tic_tac_toe.Listeners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.ItemStack;

public class InvClickBlocker implements Listener {

    @EventHandler
    public void onInvClick(InventoryClickEvent e) {
        String inventoryname = e.getView().getTitle();

        if(inventoryname.equals("§eTic Tac Toe")) {
            blockClick(e);
        }
        if(inventoryname.startsWith("§9")) {
            if(e.getView().getItem(1) != null) {
                ItemStack glasspane = e.getView().getItem(1);
                if(glasspane.getItemMeta().getDisplayName().equals(" ")) {
                    blockClick(e);
                }
            }
        }
        if(inventoryname.equals("§fSpielstein Auswahl")) {
            blockClick(e);
        }
    }

    public void blockClick(InventoryClickEvent e) {
        if(e.getRawSlot() <= e.getInventory().getSize() - 1) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void shiftClick(InventoryClickEvent e) {
        if(e.getView().getTitle().startsWith("§9")) {
            if(e.getView().getItem(1) != null) {
                ItemStack itemStack = e.getView().getItem(1);
                if(itemStack.getItemMeta().getDisplayName().equals(" ")) {
                    if(e.isShiftClick()) {
                        e.setCancelled(true);
                    }
                }
            }
        }
    }

    @EventHandler
    public void swapItems(PlayerSwapHandItemsEvent e) {
        if(!e.getPlayer().getOpenInventory().getTitle().startsWith("§9")) {
            return;
        }
        if(e.getPlayer().getOpenInventory().getItem(1) == null) {
            return;
        }
        if(!e.getPlayer().getOpenInventory().getItem(1).equals(Material.YELLOW_STAINED_GLASS_PANE)) {
            return;
        }
        if(!e.getPlayer().getOpenInventory().getItem(1).getItemMeta().getDisplayName().equals(" ")) {
            return;
        }

        e.setCancelled(true);
    }
}