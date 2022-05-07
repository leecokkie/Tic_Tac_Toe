package de.lecokkie.tic_tac_toe.Listeners;

import de.lecokkie.tic_tac_toe.Main;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class RequestListener implements Listener {

    @EventHandler
    public void blockChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        if(ClickNavigationPreGame.duellchat.contains(p)) {
            ClickNavigationPreGame.duellchat.remove(p);
            e.setCancelled(true);
            String[] message = e.getMessage().split(" ");
            if(message.length != 1) {
                p.sendMessage("§8[§eTic Tac Toe§8] §7Bitte gebe nur §a1 §7Namen ein");
                return;
            }
            if(p.getName().equals(message[0])) {
                p.sendMessage("§8[§eTic Tac Toe§8] §7Du kannst dich nicht selbst herausfordern!");
                return;
            }
            for(Player players : Bukkit.getOnlinePlayers()) {
                if(players.getName().equals(message[0])) {
                    p.sendMessage("§8[§eTic Tac Toe§8] §7Du hast §a" + players.getName() + " §7herausgefordert!");
                    players.sendMessage("§8[§eTic Tac Toe§8] §7Du hast eine Herausforderung von §a" + p.getName() + " §7bekommen!");
                    players.sendMessage(setChallengeText());
                    ClickNavigationPreGame.wantduell.put(p, players);

                    (new BukkitRunnable() {
                        public void run() {
                            ClickNavigationPreGame.wantduell.remove(p, players);
                            p.sendMessage("§8[§eTic Tac Toe§8] §7Die Herausforderung an §a" + players.getName() + " §7ist §cabgelaufen!");
                            players.sendMessage("§8[§eTic Tac Toe§8] §7Die Herausforderung von §a" + p.getName() + " §7ist §cabgelaufen!");
                        }
                    }).runTaskLater(Main.getPlugin(), 20*60);
                    return;
                }
            }
        }
    }


    public TextComponent setChallengeText() {
        TextComponent textComponent = new TextComponent("§8[§eTic Tac Toe§8] §7Klicke um die Herausforderung ");
        TextComponent textComponent2 = new TextComponent(" §7oder ");
        TextComponent textComponent3 = new TextComponent("§7.");

        TextComponent accept = new TextComponent("§8*§aanzunehmen§8*");
        TextComponent deny = new TextComponent("§8*§cabzulehnen§8*");

        accept.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/accept"));
        accept.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText("§aannehmen")));

        deny.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/deny"));
        deny.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText("§cablehnen")));

        textComponent.addExtra(accept);
        textComponent2.addExtra(deny);
        textComponent.addExtra(textComponent2);
        textComponent.addExtra(textComponent3);
        return textComponent;
    }
}