package de.lecokkie.tic_tac_toe.Listeners;

import de.lecokkie.tic_tac_toe.Commands.DenyDuellCommand;
import de.lecokkie.tic_tac_toe.Main;
import de.lecokkie.tic_tac_toe.util.FileManager;
import net.kyori.adventure.text.Component;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

public class ClickNavigationIngame implements Listener {

    public static ArrayList<Player> amzug = new ArrayList<>();

    @EventHandler
    public void ingameClick(InventoryClickEvent e) {
        Player p = (Player) e.getView().getPlayer();
        if(!e.getView().getTitle().startsWith("§9")) {
            return;
        }
        if(e.getView().getItem(1) == null) {
            return;
        }
        ItemStack itemStack = e.getView().getItem(1);
        if(!itemStack.getItemMeta().getDisplayName().equals(" ")) {
            return;
        }

        if(e.getCurrentItem() != null) {
            return;
        }
        setItem(p, e.getRawSlot());
    }

    public void setItem(Player p, int i) {
        if(amzug.contains(p)) {
            if(i > 53) {
                return;
            }
            amzug.remove(p);
            Player p2 = null;

            if(ClickNavigationPreGame.ingame.containsKey(p)) {
                p2 = ClickNavigationPreGame.ingame.get(p);
            }
            if(ClickNavigationPreGame.ingame.containsValue(p)) {
                p2 = DenyDuellCommand.getKey(ClickNavigationPreGame.ingame, p);
            }
            amzug.add(p2);

            File loc = new File("plugins//Tic_Tac_Toe", "PlayerBlocks");
            File userfile = new File(loc, p.getUniqueId() + ".yml");

            if(!userfile.exists()) {
                userfile.mkdirs();
                FileManager.createPlayer(p);
            }
            YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(userfile);

            Material material = Material.valueOf(yamlConfiguration.getString("Material"));

            ItemStack itemStack = new ItemStack(material);
            ItemMeta itemMeta = itemStack.getItemMeta();

            if(ClickNavigationPreGame.ingame.containsKey(p)) {
                itemMeta.setDisplayName("§9" + p.getName());
            }
            if(ClickNavigationPreGame.ingame.containsValue(p)) {
                itemMeta.setDisplayName("§c" + p.getName());
            }
            itemStack.setItemMeta(itemMeta);
            p.getOpenInventory().setItem(i, itemStack);
            checkWin(p, p2);
        }
    }

    public void checkWin(Player p, Player p2) {
        File loc = new File("plugins//Tic_Tac_Toe", "PlayerBlocks");
        File userfile = new File(loc, p.getUniqueId() + ".yml");
        File userfile2 = new File(loc, p2.getUniqueId() + ".yml");

        if(!userfile.exists()) {
            userfile.mkdirs();
            FileManager.createPlayer(p);
        }
        if(!userfile2.exists()) {
            userfile2.mkdirs();
            FileManager.createPlayer(p2);
        }

        YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(userfile);
        YamlConfiguration yamlConfiguration2 = YamlConfiguration.loadConfiguration(userfile2);

        Material material = Material.valueOf(yamlConfiguration.getString("Material"));
        Material material2 = Material.valueOf(yamlConfiguration2.getString("Material"));

        ItemStack itemStack = new ItemStack(material);
        ItemStack itemStack2 = new ItemStack(material2);

        ItemMeta itemMeta = itemStack.getItemMeta();
        ItemMeta itemMeta2 = itemStack2.getItemMeta();

        itemMeta.setDisplayName("§9" + p.getName());
        itemMeta2.setDisplayName("§c" + p2.getName());

        itemStack.setItemMeta(itemMeta);
        itemStack2.setItemMeta(itemMeta2);

        if (checkWinning(Objects.requireNonNull(p.getPlayer()), itemStack)) {
            p.sendMessage("You have Won the Game.");
        }
        if (checkWinning(Objects.requireNonNull(p2.getPlayer()), itemStack2)) {
            p2.sendMessage("You have Won the Game.");
        }
    }

    public void win(Player p) {
        p.sendMessage("win");
    }

    public Boolean checkWinning(Player player, ItemStack itemStack) {
                Inventory inventory = player.getOpenInventory().getTopInventory();
                ItemStack[] itemStacks = inventory.getContents();
                int slotnumber = 0;
                for (ItemStack items : itemStacks) {
                    if (items != null && items.getType() != Material.AIR) {
                        if (itemStacks[21].isSimilar(itemStack) && itemStacks[22].isSimilar(itemStack) && itemStacks[23].isSimilar(itemStack)) {
                            win(player);
                            return true;
                        }
                    }
                    if (slotnumber == 21 ||slotnumber == 22 ||slotnumber == 23 ||slotnumber == 30 ||slotnumber == 31 ||slotnumber == 32 ||slotnumber == 39 ||slotnumber == 40 ||slotnumber == 41) {
                        if (inventory.getItem(slotnumber) != null || Objects.requireNonNull(inventory.getItem(slotnumber)).getType() != Material.AIR) {
                            Main.getPlugin().getLogger().info("Slot: " +slotnumber+ ", Item: " + Objects.requireNonNull(inventory.getItem(slotnumber)).getType());
                        } else {
                            Main.getPlugin().getLogger().info("Slot: " + slotnumber + ", Item: Null");
                        }
                    }
                    slotnumber++;
                }
                return false;
            }
    }