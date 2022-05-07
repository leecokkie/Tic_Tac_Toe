package de.lecokkie.tic_tac_toe.Listeners;

import de.lecokkie.tic_tac_toe.Commands.DenyDuellCommand;
import de.lecokkie.tic_tac_toe.Main;
import de.lecokkie.tic_tac_toe.util.FileManager;
import de.lecokkie.tic_tac_toe.util.TicTacToeInv;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;

public class ClickNavigationPreGame implements Listener {

    public static ArrayList<Player> warteliste = new ArrayList<>();
    public static HashMap<Player, Player> ingame = new HashMap<>();
    public static ArrayList<Player> duellchat = new ArrayList<>();
    public static HashMap<Player, Player> wantduell = new HashMap<>();

    //Opening the menu for choosing between waitinglist or 1v1
    @EventHandler
    public void clickBlock(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        Block targetBlock = p.getTargetBlock(6);
        if(FileManager.locationList.contains(targetBlock.getLocation())) {
            if(e.getAction().isRightClick()) {
                if(!(e.getHand() == EquipmentSlot.OFF_HAND)) {

                    TicTacToeInv.openAuswahlInventory(p);

                }
            }
        }
    }

    //Doing the things depending on the click (waitinglist or 1v1)
    @EventHandler
    public void clickAuswahl(InventoryClickEvent e) {
        Player p = (Player) e.getView().getPlayer();
        if(!e.getView().getTitle().equals("§eTic Tac Toe")) {
            return;
        }
        if(e.getCurrentItem() != null && e.getCurrentItem().hasItemMeta()) {
            if(e.getCurrentItem().getType().equals(Material.BARRIER) && e.getCurrentItem().getItemMeta().getDisplayName().equals("§cSchließen")) {
                p.closeInventory();
            }
            if(e.getCurrentItem().getType().equals(Material.LIME_TERRACOTTA) && e.getCurrentItem().getItemMeta().getDisplayName().equals("§2Warteschlange")) {
                if(warteliste.contains(p)) {
                    p.sendMessage("§8[§eTic Tac Toe§8] §7Du stehst bereits auf der Warteliste!");
                    p.closeInventory();
                    return;
                }
                if(duellchat.contains(p)) {
                    duellchat.remove(p);
                }
                if(ingame.containsKey(p)) {
                    return;
                }
                if(ingame.containsValue(p)) {
                    return;
                }
                if(warteliste.isEmpty()) {
                    warteliste.add(p);
                    p.sendMessage("§8[§eTic Tac Toe§8] §7Du wurdest in die Warteliste §aeingetragen§7!");
                    p.closeInventory();
                }
                if(!warteliste.isEmpty()) {
                    Player p2 = warteliste.get(0);
                    if(p != p2) {
                        warteliste.remove(p2);

                        p.sendMessage("§8[§eTic Tac Toe§8] §7Du wurdest in die Warteliste §aeingetragen§7!");
                        p.sendMessage("§8[§eTic Tac Toe§8] §7Das Spiel gegen §a" + p2.getName() + " §7wurde gestartet!");
                        p2.sendMessage("§8[§eTic Tac Toe§8] §7Das Spiel gegen §a" + p.getName() + " §7wurde gestartet!");

                        removePlayerFromLists(p);
                        removePlayerFromLists(p2);

                        TicTacToeInv.openPlayInventory(p, p2);

                        ingame.put(p, p2);
                    }
                }
            }
            if(e.getCurrentItem().getType().equals(Material.OAK_SIGN) && e.getCurrentItem().getItemMeta().getDisplayName().equals("§eGegnersuche")) {
                p.closeInventory();
                p.sendMessage("§8[§eTic Tac Toe§8] §7Schreibe den Namen des Spielers in den Chat, den du herausfordern möchtest:");
                duellchat.add(p);

                (new BukkitRunnable() {
                    public void run() {
                        duellchat.remove(p);
                    }
                }).runTaskLater(Main.getPlugin(), 20*60);
            }
            if(e.getCurrentItem().getType().equals(Material.NETHER_STAR) && e.getCurrentItem().getItemMeta().getDisplayName().equals("§fSpielstein-Wahl")) {
                TicTacToeInv.openItemChoose(p);
            }
        }
    }

    @EventHandler
    public void clickItemChoose(InventoryClickEvent e) {
        Player p = (Player) e.getView().getPlayer();
        if(!e.getView().getTitle().equals("§fSpielstein Auswahl")) {
            return;
        }
        if(e.getCurrentItem() != null) {
            if(e.getCurrentItem().getType().equals(Material.BARRIER) && e.getCurrentItem().getItemMeta().getDisplayName().equals("§cSchließen")) {
                p.closeInventory();
            }
            if(e.getCurrentItem().getType().equals(Material.ARROW) && e.getCurrentItem().getItemMeta().getDisplayName().equals("§7Zurück")) {
                TicTacToeInv.openAuswahlInventory(p);
            }
            if(e.getCurrentItem().getType().equals(Material.ARROW) && e.getCurrentItem().getItemMeta().getDisplayName().equals("§enächste Seite")) {
                TicTacToeInv.openItemChoose2(p);
            }
            if(e.getCurrentItem().getType().equals(Material.ARROW) && e.getCurrentItem().getItemMeta().getDisplayName().equals("§evorherige Seite")) {
                TicTacToeInv.openItemChoose(p);
            }
            if(!e.getCurrentItem().hasItemMeta()) {
                if(e.getClickedInventory().getHolder() == null) {
                    Material material = e.getCurrentItem().getType();
                    FileManager.setPlayerMaterial(p, material);
                    p.closeInventory();
                    p.sendMessage("§8[§eTic Tac Toe§8] §7Dein neuer Spielstein ist: §a" + material.toString().toLowerCase());
                }
            }
        }
    }

    public static void removePlayerFromLists(Player p) {
        //warteliste
        if(ClickNavigationPreGame.warteliste.contains(p)) {
            ClickNavigationPreGame.warteliste.remove(p);
        }

        //wantduell
        if(ClickNavigationPreGame.wantduell.containsKey(p)) {
            ClickNavigationPreGame.wantduell.remove(p, ClickNavigationPreGame.wantduell.get(p));
        }
        if(ClickNavigationPreGame.wantduell.containsValue(p)) {
            Player key = DenyDuellCommand.getKey(ClickNavigationPreGame.wantduell, p);
            ClickNavigationPreGame.wantduell.remove(key, p);
        }

        //duellchat
        if(ClickNavigationPreGame.duellchat.contains(p)) {
            ClickNavigationPreGame.duellchat.remove(p);
        }

        //amzug
        if(ClickNavigationIngame.amzug.contains(p)) {
            ClickNavigationIngame.amzug.remove(p);
        }
    }
}