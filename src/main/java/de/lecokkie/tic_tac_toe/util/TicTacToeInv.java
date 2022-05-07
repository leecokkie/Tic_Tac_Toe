package de.lecokkie.tic_tac_toe.util;

import de.lecokkie.tic_tac_toe.Listeners.ClickNavigationIngame;
import de.lecokkie.tic_tac_toe.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TicTacToeInv {

    public static void openAuswahlInventory(Player p) {
        //BlockAuswahl
        ItemStack itemchoose = new ItemStack(Material.NETHER_STAR);
        ItemMeta itemchoosemeta = itemchoose.getItemMeta();
        itemchoosemeta.setDisplayName("§fSpielstein-Wahl");
        itemchoose.setItemMeta(itemchoosemeta);

        //Barrier
        ItemStack close = new ItemStack(Material.BARRIER);
        ItemMeta closemeta = close.getItemMeta();
        closemeta.setDisplayName("§cSchließen");
        close.setItemMeta(closemeta);

        //Schild
        ItemStack sign = new ItemStack(Material.OAK_SIGN);
        ItemMeta signmeta = sign.getItemMeta();
        signmeta.setDisplayName("§eGegnersuche");
        sign.setItemMeta(signmeta);

        //Terracotta
        ItemStack waitinglist = new ItemStack(Material.LIME_TERRACOTTA);
        ItemMeta waitinglistmeta = waitinglist.getItemMeta();
        waitinglistmeta.setDisplayName("§2Warteschlange");
        waitinglist.setItemMeta(waitinglistmeta);

        //Black glass pane
        ItemStack black = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta blackmeta = black.getItemMeta();
        blackmeta.setDisplayName(" ");
        black.setItemMeta(blackmeta);

        //Yellow glass pane
        ItemStack yellow = new ItemStack(Material.YELLOW_STAINED_GLASS_PANE);
        ItemMeta yellowmeta = yellow.getItemMeta();
        yellowmeta.setDisplayName(" ");
        yellow.setItemMeta(yellowmeta);

        //Book and Quill
        ItemStack info = new ItemStack(Material.WRITABLE_BOOK);

        List<String> list = new ArrayList<>();
        list.add("§7----------");
        list.add("§7Klicke auf das Schild,");
        list.add("§7um nach einem bestimmten");
        list.add("§7Gegner zu suchen");
        list.add("§7----------");
        list.add("§7Klicke auf den Ton,");
        list.add("§7um in die Warteschlange");
        list.add("§7eingetragen zu werden");

        ItemMeta infometa = info.getItemMeta();
        infometa.setDisplayName("§8< §aInformation §8>");
        infometa.setLore(list);
        info.setItemMeta(infometa);

        Inventory inventory = Bukkit.createInventory(null, 9*3, "§eTic Tac Toe");

        inventory.setItem(0, yellow);
        inventory.setItem(1, yellow);
        inventory.setItem(2, black);
        inventory.setItem(3, black);
        inventory.setItem(4, info);
        inventory.setItem(5, black);
        inventory.setItem(6, black);
        inventory.setItem(7, yellow);
        inventory.setItem(8, yellow);
        inventory.setItem(9, yellow);
        inventory.setItem(10, black);
        inventory.setItem(11, sign);
        inventory.setItem(12, black);
        inventory.setItem(13, black);
        inventory.setItem(14, yellow);
        inventory.setItem(15, waitinglist);
        inventory.setItem(16, black);
        inventory.setItem(17, black);
        inventory.setItem(18, yellow);
        inventory.setItem(19, itemchoose);
        inventory.setItem(20, black);
        inventory.setItem(21, yellow);
        inventory.setItem(22, yellow);
        inventory.setItem(23, black);
        inventory.setItem(24, black);
        inventory.setItem(25, black);
        inventory.setItem(26, close);


        p.openInventory(inventory);
    }

    public static void openPlayInventory(Player p, Player opponent) {
        Inventory inventory = Bukkit.createInventory(p, 9*6, "§9" + p.getName() + " §7vs §c" + opponent.getName());

        //Yellow glass pane
        ItemStack yellow = new ItemStack(Material.YELLOW_STAINED_GLASS_PANE);
        ItemMeta yellowmeta = yellow.getItemMeta();
        yellowmeta.setDisplayName(" ");
        yellow.setItemMeta(yellowmeta);

        //Black glass pane
        ItemStack black = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta blackmeta = black.getItemMeta();
        blackmeta.setDisplayName(" ");
        black.setItemMeta(blackmeta);

        //Informationsbuch
        List<String> list = new ArrayList<>();

        ItemStack info = new ItemStack(Material.WRITABLE_BOOK);
        ItemMeta infometa = info.getItemMeta();
        infometa.setDisplayName("§8< §aInformationen §8>");

        list.add("§7----------");
        list.add("§7Klicke auf das Feld,");
        list.add("§7auf das du dein");
        list.add("§7Item setzen möchtest");

        infometa.setLore(list);
        info.setItemMeta(infometa);

        //Player 1 sign
        ItemStack sign1 = new ItemStack(Material.NAME_TAG);
        ItemMeta sign1meta = sign1.getItemMeta();
        sign1meta.setDisplayName("§9" + p.getName());
        sign1.setItemMeta(sign1meta);

        //Player 2 sign
        ItemStack sign2 = new ItemStack(Material.NAME_TAG);
        ItemMeta sign2meta = sign2.getItemMeta();
        sign2meta.setDisplayName("§c" + opponent.getName());
        sign2.setItemMeta(sign2meta);

        inventory.setItem(1, yellow);
        inventory.setItem(5, yellow);
        inventory.setItem(11, yellow);
        inventory.setItem(12, yellow);
        inventory.setItem(24, yellow);
        inventory.setItem(37, yellow);
        inventory.setItem(43, yellow);
        inventory.setItem(46, yellow);
        inventory.setItem(47, yellow);
        inventory.setItem(51, yellow);
        inventory.setItem(52, yellow);

        inventory.setItem(3, black);
        inventory.setItem(7, black);
        inventory.setItem(10, black);
        inventory.setItem(13, black);
        inventory.setItem(14, black);
        inventory.setItem(15, black);
        inventory.setItem(16, black);
        inventory.setItem(19, black);
        inventory.setItem(20, black);
        inventory.setItem(25, black);
        inventory.setItem(28, black);
        inventory.setItem(29, black);
        inventory.setItem(33, black);
        inventory.setItem(34, black);
        inventory.setItem(38, black);
        inventory.setItem(42, black);
        inventory.setItem(48, black);
        inventory.setItem(49, black);
        inventory.setItem(50, black);

        inventory.setItem(4, info);
        inventory.setItem(2, sign1);
        inventory.setItem(6, sign2);


        File loc = new File("plugins//Tic_Tac_Toe", "PlayerBlocks");
        File userfile = new File(loc, p.getUniqueId() + ".yml");
        File userfile2 = new File(loc, opponent.getUniqueId() + ".yml");

        YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(userfile);
        YamlConfiguration yamlConfiguration2 = YamlConfiguration.loadConfiguration(userfile2);

        //Player items
        try{
            Material material = Material.valueOf(yamlConfiguration.getString("Material"));
            Material material2 = Material.valueOf(yamlConfiguration2.getString("Material"));

            if(material.equals(material2)) {
                if(material.equals(Material.NETHER_STAR)) {
                    material2 = Material.ENDER_EYE;
                }
                if(!material.equals(Material.NETHER_STAR)) {
                    material2 = Material.NETHER_STAR;
                }
            }

            ItemStack playerblock = new ItemStack(material);
            ItemMeta playerblockmeta = playerblock.getItemMeta();
            playerblockmeta.setDisplayName("§9" + p.getName());
            playerblock.setItemMeta(playerblockmeta);

            for(int i = 0; i <= 45; i = i + 9) {
                inventory.setItem(i, playerblock);
            }

            ItemStack playerblock2 = new ItemStack(material2);
            ItemMeta playerblockmeta2 = playerblock2.getItemMeta();
            playerblockmeta2.setDisplayName("§c" + opponent.getName());
            playerblock2.setItemMeta(playerblockmeta2);

            for(int i = 8; i <= 53; i = i + 9) {
                inventory.setItem(i, playerblock2);
            }
        } catch(Exception e) {
            p.sendMessage("§8[§eTic Tac Toe§8] §7Bitte melde dich umgehend bei einem Administrator!");
            opponent.sendMessage("§8[§eTic Tac Toe§8] §7Bitte melde dich umgehend bei einem Administrator!");
            (new BukkitRunnable() {
                public void run() {
                    p.closeInventory();
                    opponent.closeInventory();
                }
            }).runTaskLater(Main.getPlugin(), 1);
        }

        ClickNavigationIngame.amzug.add(p);

        p.openInventory(inventory);
        opponent.openInventory(p.getOpenInventory().getTopInventory());
    }

    public static void openItemChoose(Player p) {
        File loc = new File("plugins//Tic_Tac_Toe", "PlayerBlocks");
        File userfile = new File(loc, p.getUniqueId() + ".yml");

        if(!userfile.exists()) {
            loc.mkdirs();
            FileManager.createPlayer(p);
        }
        YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(userfile);

        Inventory inventory = Bukkit.createInventory(null, 9*5, "§fSpielstein Auswahl");

        //Seite vor
        ItemStack next = new ItemStack(Material.ARROW);
        ItemMeta nextmeta = next.getItemMeta();
        nextmeta.setDisplayName("§enächste Seite");
        next.setItemMeta(nextmeta);

        //Arrow
        ItemStack arrow = new ItemStack(Material.ARROW);
        ItemMeta arrowmeta = arrow.getItemMeta();
        arrowmeta.setDisplayName("§7Zurück");
        arrow.setItemMeta(arrowmeta);

        //Barrier
        ItemStack close = new ItemStack(Material.BARRIER);
        ItemMeta closemeta = close.getItemMeta();
        closemeta.setDisplayName("§cSchließen");
        close.setItemMeta(closemeta);

        //Black glass pane
        ItemStack black = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta blackmeta = black.getItemMeta();
        blackmeta.setDisplayName(" ");
        black.setItemMeta(blackmeta);

        //Yellow glass pane
        ItemStack yellow = new ItemStack(Material.YELLOW_STAINED_GLASS_PANE);
        ItemMeta yellowmeta = yellow.getItemMeta();
        yellowmeta.setDisplayName(" ");
        yellow.setItemMeta(yellowmeta);

        //Book and Quill
        ItemStack info = new ItemStack(Material.WRITABLE_BOOK);

        List<String> list = new ArrayList<>();
        list.add("§7----------");
        list.add("§7Wähle ein Item als");
        list.add("§7deinen Spielstein aus!");

        ItemMeta infometa = info.getItemMeta();
        infometa.setDisplayName("§8< §aInformation §8> Seite 1");
        infometa.setLore(list);
        info.setItemMeta(infometa);


        ItemStack nether_star = new ItemStack(Material.NETHER_STAR);
        ItemStack ender_eye = new ItemStack(Material.ENDER_EYE);
        ItemStack golden_apple = new ItemStack(Material.GOLDEN_APPLE);
        ItemStack amethyst = new ItemStack(Material.AMETHYST_SHARD);
        ItemStack scute = new ItemStack(Material.SCUTE);
        ItemStack diamond = new ItemStack(Material.DIAMOND);
        ItemStack beacon = new ItemStack(Material.BEACON);
        ItemStack snowball = new ItemStack(Material.SNOWBALL);
        ItemStack clay = new ItemStack(Material.CLAY_BALL);
        ItemStack slime = new ItemStack(Material.SLIME_BALL);
        ItemStack bone = new ItemStack(Material.BONE);
        ItemStack ender_pearl = new ItemStack(Material.ENDER_PEARL);
        ItemStack rocket = new ItemStack(Material.FIREWORK_ROCKET);
        ItemStack shulker = new ItemStack(Material.SHULKER_SHELL);
        ItemStack prismarine = new ItemStack(Material.PRISMARINE_SHARD);
        ItemStack cookie = new ItemStack(Material.COOKIE);
        ItemStack brown_mushroom = new ItemStack(Material.BROWN_MUSHROOM);
        ItemStack red_mushroom = new ItemStack(Material.RED_MUSHROOM);
        ItemStack wither_rose = new ItemStack(Material.WITHER_ROSE);
        ItemStack crimson_fungus = new ItemStack(Material.CRIMSON_FUNGUS);
        ItemStack warped_fungus = new ItemStack(Material.WARPED_FUNGUS);


        inventory.setItem(0, black);
        inventory.setItem(1, black);
        inventory.setItem(2, yellow);
        inventory.setItem(3, yellow);
        inventory.setItem(4, info);
        inventory.setItem(5, black);
        inventory.setItem(6, yellow);
        inventory.setItem(7, yellow);
        inventory.setItem(8, yellow);
        inventory.setItem(9, black);
        inventory.setItem(17, yellow);
        inventory.setItem(18, black);
        inventory.setItem(26, black);
        inventory.setItem(27, yellow);
        inventory.setItem(35, black);
        inventory.setItem(36, arrow);
        inventory.setItem(37, black);
        inventory.setItem(38, black);
        inventory.setItem(39, yellow);
        inventory.setItem(41, next);
        inventory.setItem(42, yellow);
        inventory.setItem(43, black);
        inventory.setItem(44, close);

        try{
            Material material = Material.valueOf(yamlConfiguration.getString("Material"));
            ItemStack playerblock = new ItemStack(material);
            ItemMeta playerblockmeta = playerblock.getItemMeta();
            playerblockmeta.addEnchant(Enchantment.ARROW_DAMAGE, 1, false);
            playerblockmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            List<String> playerblocklore = new ArrayList<>();
            playerblocklore.add("§6Aktueller Spielstein");
            playerblockmeta.setLore(playerblocklore);
            playerblock.setItemMeta(playerblockmeta);
            inventory.setItem(40, playerblock);
        } catch(Exception e) {
            inventory.setItem(40, yellow);
        }

        inventory.setItem(10, nether_star);
        inventory.setItem(11, ender_eye);
        inventory.setItem(12, golden_apple);
        inventory.setItem(13, amethyst);
        inventory.setItem(14, scute);
        inventory.setItem(15, diamond);
        inventory.setItem(16, beacon);
        inventory.setItem(19, snowball);
        inventory.setItem(20, clay);
        inventory.setItem(21, slime);
        inventory.setItem(22, bone);
        inventory.setItem(23, ender_pearl);
        inventory.setItem(24, rocket);
        inventory.setItem(25, shulker);
        inventory.setItem(28, prismarine);
        inventory.setItem(29, cookie);
        inventory.setItem(30, brown_mushroom);
        inventory.setItem(31, red_mushroom);
        inventory.setItem(32, wither_rose);
        inventory.setItem(33, crimson_fungus);
        inventory.setItem(34, warped_fungus);

        p.openInventory(inventory);
    }

    public static void openItemChoose2(Player p) {
        File loc = new File("plugins//Tic_Tac_Toe", "PlayerBlocks");
        File userfile = new File(loc, p.getUniqueId() + ".yml");

        if(!userfile.exists()) {
            loc.mkdirs();
            FileManager.createPlayer(p);
        }
        YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(userfile);

        Inventory inventory = Bukkit.createInventory(null, 9*5, "§fSpielstein Auswahl");


        //Seite zurück
        ItemStack previous = new ItemStack(Material.ARROW);
        ItemMeta previousmeta = previous.getItemMeta();
        previousmeta.setDisplayName("§evorherige Seite");
        previous.setItemMeta(previousmeta);

        //Arrow
        ItemStack arrow = new ItemStack(Material.ARROW);
        ItemMeta arrowmeta = arrow.getItemMeta();
        arrowmeta.setDisplayName("§7Zurück");
        arrow.setItemMeta(arrowmeta);

        //Barrier
        ItemStack close = new ItemStack(Material.BARRIER);
        ItemMeta closemeta = close.getItemMeta();
        closemeta.setDisplayName("§cSchließen");
        close.setItemMeta(closemeta);

        //Black glass pane
        ItemStack black = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta blackmeta = black.getItemMeta();
        blackmeta.setDisplayName(" ");
        black.setItemMeta(blackmeta);

        //Yellow glass pane
        ItemStack yellow = new ItemStack(Material.YELLOW_STAINED_GLASS_PANE);
        ItemMeta yellowmeta = yellow.getItemMeta();
        yellowmeta.setDisplayName(" ");
        yellow.setItemMeta(yellowmeta);

        //Book and Quill
        ItemStack info = new ItemStack(Material.WRITABLE_BOOK);

        List<String> list = new ArrayList<>();
        list.add("§7----------");
        list.add("§7Wähle ein Item als");
        list.add("§7deinen Spielstein aus!");

        ItemMeta infometa = info.getItemMeta();
        infometa.setDisplayName("§8< §aInformation §8> Seite 2");
        infometa.setLore(list);
        info.setItemMeta(infometa);

        try{
            Material material = Material.valueOf(yamlConfiguration.getString("Material"));
            ItemStack playerblock = new ItemStack(material);
            ItemMeta playerblockmeta = playerblock.getItemMeta();
            playerblockmeta.addEnchant(Enchantment.ARROW_DAMAGE, 1, false);
            playerblockmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            List<String> playerblocklore = new ArrayList<>();
            playerblocklore.add("§6Aktueller Spielstein");
            playerblockmeta.setLore(playerblocklore);
            playerblock.setItemMeta(playerblockmeta);
            inventory.setItem(40, playerblock);
        } catch(Exception e) {
            inventory.setItem(40, yellow);
        }

        ItemStack end_rod = new ItemStack(Material.END_ROD);
        ItemStack lily_pad = new ItemStack(Material.LILY_PAD);
        ItemStack sunflower = new ItemStack(Material.SUNFLOWER);
        ItemStack firecoral = new ItemStack(Material.FIRE_CORAL);
        ItemStack horncoral = new ItemStack(Material.HORN_CORAL);
        ItemStack flowerpot = new ItemStack(Material.FLOWER_POT);
        ItemStack dragon_head = new ItemStack(Material.DRAGON_HEAD);
        ItemStack redstone = new ItemStack(Material.REDSTONE);
        ItemStack repeater = new ItemStack(Material.REPEATER);
        ItemStack saddle = new ItemStack(Material.SADDLE);
        ItemStack nametag = new ItemStack(Material.NAME_TAG);
        ItemStack clock = new ItemStack(Material.CLOCK);
        ItemStack totem = new ItemStack(Material.TOTEM_OF_UNDYING);
        ItemStack ghast_tear = new ItemStack(Material.GHAST_TEAR);
        ItemStack fermented_spider_eye = new ItemStack(Material.FERMENTED_SPIDER_EYE);
        ItemStack blaze_powder = new ItemStack(Material.BLAZE_POWDER);
        ItemStack magma_cream = new ItemStack(Material.MAGMA_CREAM);
        ItemStack rabbit_foot = new ItemStack(Material.RABBIT_FOOT);
        ItemStack experience_bottle = new ItemStack(Material.EXPERIENCE_BOTTLE);
        ItemStack trident = new ItemStack(Material.TRIDENT);
        ItemStack netherite = new ItemStack(Material.NETHERITE_INGOT);

        inventory.setItem(0, black);
        inventory.setItem(1, black);
        inventory.setItem(2, yellow);
        inventory.setItem(3, yellow);
        inventory.setItem(4, info);
        inventory.setItem(5, black);
        inventory.setItem(6, yellow);
        inventory.setItem(7, yellow);
        inventory.setItem(8, yellow);
        inventory.setItem(9, black);
        inventory.setItem(17, yellow);
        inventory.setItem(18, black);
        inventory.setItem(26, black);
        inventory.setItem(27, yellow);
        inventory.setItem(35, black);
        inventory.setItem(36, arrow);
        inventory.setItem(37, black);
        inventory.setItem(38, black);
        inventory.setItem(39, previous);
        inventory.setItem(41, black);
        inventory.setItem(42, yellow);
        inventory.setItem(43, black);
        inventory.setItem(44, close);

        inventory.setItem(10, end_rod);
        inventory.setItem(11, lily_pad);
        inventory.setItem(12, sunflower);
        inventory.setItem(13, firecoral);
        inventory.setItem(14, horncoral);
        inventory.setItem(15, flowerpot);
        inventory.setItem(16, dragon_head);
        inventory.setItem(19, redstone);
        inventory.setItem(20, repeater);
        inventory.setItem(21, saddle);
        inventory.setItem(22, nametag);
        inventory.setItem(23, clock);
        inventory.setItem(24, totem);
        inventory.setItem(25, ghast_tear);
        inventory.setItem(28, fermented_spider_eye);
        inventory.setItem(29, blaze_powder);
        inventory.setItem(30, magma_cream);
        inventory.setItem(31, rabbit_foot);
        inventory.setItem(32, experience_bottle);
        inventory.setItem(33, trident);
        inventory.setItem(34, netherite);

        p.openInventory(inventory);
    }
}