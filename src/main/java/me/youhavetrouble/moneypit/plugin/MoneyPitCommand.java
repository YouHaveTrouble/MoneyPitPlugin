package me.youhavetrouble.moneypit.plugin;

import me.youhavetrouble.moneypit.Economy;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.plugin.RegisteredServiceProvider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MoneyPitCommand implements TabExecutor {

    private final MoneyPit plugin;
    private final List<String> subcommands = new ArrayList<>();

    protected MoneyPitCommand(MoneyPit plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sendInfo(sender);
            return true;
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        return subcommands;
    }

    private void sendInfo(CommandSender sender) {
        sender.sendMessage("---------- MoneyPit ----------");
        sender.sendMessage("Plugin version: " + plugin.getDescription().getVersion());
        sender.sendMessage("API version: " + plugin.getApiVersion());
        sender.sendMessage("");
        sender.sendMessage("Registered economy providers:");
        sender.sendMessage(getRegisteredProviders());
        sender.sendMessage("------------------------------");
    }

    private String getRegisteredProviders() {
        Collection<RegisteredServiceProvider<Economy>> registrations = plugin.getServer()
                .getServicesManager()
                .getRegistrations(Economy.class);

        if (registrations.isEmpty()) return "No economy providers registered!";

        List<String> providers = new ArrayList<>(registrations.size());
        for (RegisteredServiceProvider<Economy> provider : registrations) {
            String name = "%s (%s %s)".formatted(
                    provider.getPlugin().getName(),
                    provider.getPlugin().getDescription().getName(),
                    provider.getPlugin().getDescription().getVersion()
            );
            providers.add(name);
        }
        return String.join(", ", providers);
    }
}
