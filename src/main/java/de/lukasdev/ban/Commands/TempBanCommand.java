package de.lukasdev.ban.Commands;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.util.concurrent.TimeUnit;

public class TempBanCommand extends Command {

    public TempBanCommand() {
        super("tempban", "bungeesystem.ban.tempban");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!sender.hasPermission("bungeesystem.ban.tempban")) {
            sender.sendMessage("Du hast keine Berechtigung, diesen Befehl auszuführen.");
            return;
        }

        if (args.length < 4) {
            sender.sendMessage("Verwendung: /tempban <Spielername> <Zeit> <Einheit> <Grund>");
            return;
        }

        ProxiedPlayer targetPlayer = getTargetPlayer(args[0]);
        if (targetPlayer == null) {
            sender.sendMessage("Spieler nicht gefunden.");
            return;
        }

        long duration;
        try {
            duration = Long.parseLong(args[1]);
        } catch (NumberFormatException e) {
            sender.sendMessage("Ungültige Zeitangabe.");
            return;
        }

        String unit = args[2].toLowerCase();
        TimeUnit timeUnit;

        switch (unit) {
            case "m":
                timeUnit = TimeUnit.MINUTES;
                break;
            case "h":
                timeUnit = TimeUnit.HOURS;
                break;
            case "d":
                timeUnit = TimeUnit.DAYS;
                break;
            default:
                sender.sendMessage("Ungültig, verwende: [m,h,d]");
                return;
        }

        long banDuration = timeUnit.toMillis(duration);
        String reason = args[3];

        targetPlayer.disconnect("Du wurdest zweitweise vom Server gebannt. Grund: " + reason + ". Zeit: " + duration + " " + unit);

        // Optional: Sende eine Benachrichtigung an den Sender
        sender.sendMessage("Der Spieler " + args[0] + " wurde für " + duration + " " + unit + " gebannt. Grund: " + reason);
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