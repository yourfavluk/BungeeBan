package de.lukasdev.ban.Commands;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class BanCommand extends Command {

    public BanCommand() {
        super("ban", "bungeesystem.ban.ban");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!sender.hasPermission("bungeesystem.ban.ban")) {
            sender.sendMessage("Dazu hast du keine Rechte!");
            return;
        }

        if (args.length < 1) {
            sender.sendMessage("Nutze: /ban <Spielername> [Grund]");
            return;
        }

        ProxiedPlayer targetPlayer = getTargetPlayer(args[0]);
        if (targetPlayer == null) {
            sender.sendMessage("Dieser Spieler wurde nicht gefunden.");
            return;
        }

        String reason = (args.length >= 2) ? args[1] : "es wurde kein Grund angegeben";

        sender.sendMessage("Spieler " + args[0] + " wurde gebannt. Grund: " + reason);
    }

    private ProxiedPlayer getTargetPlayer(String playerName) {
        for (ProxiedPlayer player : getProxy().getPlayers()) {
            if (player.getName().equalsIgnoreCase(playerName)) {
                return player;
            }
        }
        return null;
    }

    private ProxyServer getProxy() {

        return null;
    }
}





