package de.lukasdev.ban.Commands;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;


public class KickCommand extends Command {

    public KickCommand() {
        super("kick", "bungeesystem.ban.kick");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender.hasPermission("bungeesystem.ban.kick")) {
            if (args.length >= 1) {
                ProxiedPlayer targetPlayer = getProxy().getPlayer(args[0]);

                if (targetPlayer != null) {
                    String reason = (args.length >= 2) ? args[1] : "Kein Grund angegeben";
                    targetPlayer.disconnect("Du wurdest vom Server gekickt. Grund: " + reason);
                    sender.sendMessage("Der Spieler " + args[0] + " wurde gekickt. Grund: " + reason);
                } else {
                    sender.sendMessage("Dieser Spieler wurde nicht gefunden.");
                }
            } else {
                sender.sendMessage("Bitte Nutze: /kick <Spielername> [Grund]");
            }
        } else {
            sender.sendMessage("Dazu hast du keine Rechte.");
        }
    }

    private ProxyServer getProxy() {
        {

        }
        return null;
    }
}






