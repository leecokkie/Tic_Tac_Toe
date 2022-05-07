package de.lecokkie.tic_tac_toe.util;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class FileManager {

    public static ArrayList<Location> locationList = new ArrayList<>();
    public static void loadList() {
        File loc = new File("plugins//Tic_Tac_Toe", "Blocks");
        File[] directoryListing = loc.listFiles();

        if(directoryListing != null) {
            for(File child : directoryListing) {

                YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(child);

                String sworld = yamlConfiguration.getString("world");
                World world = Bukkit.getWorld(sworld);
                String xs = yamlConfiguration.getString("x");
                float x = Float.parseFloat(xs);
                String ys = yamlConfiguration.getString("y");
                float y = Float.parseFloat(ys);
                String zs = yamlConfiguration.getString("z");
                float z = Float.parseFloat(zs);

                Location location = new Location(world, x, y, z);

                locationList.add(location);
            }
        }
    }

    public static void createTicTacToeBlock(String name, Player p) {

        File loc = new File("plugins//Tic_Tac_Toe", "Blocks");
        File userfile = new File(loc, name + ".yml");

        if(!userfile.exists()) {
            if(p.getTargetBlock(5).isEmpty()) {
                p.sendMessage("§8[§eTic Tac Toe§8] §7Du musst auf einen Block gucken, der max. §a3 §7Blöcke entfernt ist!");
                return;
            }
            loc.mkdirs();
            try {
                userfile.createNewFile();
                String world = p.getWorld().getName();
                Block targetBlock = p.getTargetBlock(3);
                float targetBlockX = targetBlock.getX();
                float targetBlockY = targetBlock.getY();
                float targetBlockZ = targetBlock.getZ();

                PrintWriter pw = new PrintWriter(userfile);
                pw.println("name: " + name);
                pw.println("world: " + world);
                pw.println("x: " + targetBlockX);
                pw.println("y: " + targetBlockY);
                pw.println("z: " + targetBlockZ);
                pw.close();

                Location location = FileManager.getBlock(name, p);
                int x = location.getBlockX();
                int y = location.getBlockY();
                int z = location.getBlockZ();

                loadList();
                p.sendMessage("§8[§eTic Tac Toe§8] §7Du hast das Tic Tac Toe §a" + name + " §7bei den Koordinaten: x: §a" + x + "§7, y: §a" + y + "§7, z: §a" + z + " §7gesetzt!");
            } catch(Exception e) {
                e.printStackTrace();
            }
        } else if(userfile.exists()) {
            p.sendMessage("§8[§eTic Tac Toe§8] §7Das Tic Tac Toe §a" + name + " §7existiert bereits");
        }
    }

    public static Location getBlock(String name, Player p) {
        File loc = new File("plugins//Tic_Tac_Toe", "Blocks");
        File userfile = new File(loc, name + ".yml");

        if(!userfile.exists()) {
            createTicTacToeBlock(name, p);
            getBlock(name, p);
        } else {
            YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(userfile);

            String sworld = yamlConfiguration.getString("world");
            World world = Bukkit.getWorld(sworld);
            String xs = yamlConfiguration.getString("x");
            float x = Float.parseFloat(xs);
            String ys = yamlConfiguration.getString("y");
            float y = Float.parseFloat(ys);
            String zs = yamlConfiguration.getString("z");
            float z = Float.parseFloat(zs);

            Location location = new Location(world, x, y, z);
            return location;
        }
        return null;
    }

    public static void createPlayer(Player p) {
        File loc = new File("plugins//Tic_Tac_Toe", "PlayerBlocks");
        File userfile = new File(loc, p.getUniqueId() + ".yml");

        if(!userfile.exists()) {
            loc.mkdirs();
            try {
                userfile.createNewFile();

                String key = "Material: ";
                Material material = Material.NETHER_STAR;

                PrintWriter pw = new PrintWriter(userfile);
                pw.println(key + material);
                pw.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void setPlayerMaterial(Player p, Material material) {
        File loc = new File("plugins//Tic_Tac_Toe", "PlayerBlocks");
        File userfile = new File(loc, p.getUniqueId() + ".yml");

        if(!userfile.exists()) {
            loc.mkdirs();
            createPlayer(p);
        } else {
            YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(userfile);

            yamlConfiguration.set("Material", material.toString());

            try {
                yamlConfiguration.save(userfile);
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
    }
}