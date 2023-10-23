package me.youhavetrouble.moneypit.plugin;

import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public final class MoneyPit extends JavaPlugin {

    private String apiVersion = "unknown";

    @Override
    public void onEnable() {

        try (InputStream stream = MoneyPit.class.getResourceAsStream("/api-version.txt")) {
            if (stream != null) {
                 this.apiVersion = new Scanner(stream, StandardCharsets.UTF_8).next();
            } else {
                getLogger().warning("Could not read api-version! (failed to read file)");
            }
        } catch (IOException e) {
            getLogger().warning("Could not read api-version! (IOException)");
        }

        PluginCommand command = getCommand("moneypit");
        if (command != null) {
            command.setExecutor(new MoneyPitCommand(this));
        } else {
            getLogger().warning("Could not register /moneypit command!");
        }
    }

    public String getApiVersion() {
        return this.apiVersion;
    }

}
