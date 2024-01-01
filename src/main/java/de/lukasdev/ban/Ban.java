package de.lukasdev.ban;

import de.lukasdev.ban.Commands.BanCommand;
import de.lukasdev.ban.Commands.KickCommand;
import de.lukasdev.ban.Commands.TempBanCommand;
import net.md_5.bungee.api.plugin.Plugin;

public final class Ban extends Plugin {

    @Override
    public void onEnable() {
        getProxy().getPluginManager().registerCommand(this, new KickCommand());
        getProxy().getPluginManager().registerCommand(this, new BanCommand());
        getProxy().getPluginManager().registerCommand(this, new TempBanCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}