package me.youhavetrouble;

import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public final class MoneyPit extends JavaPlugin {

    private String apiVersion = "unknown";

    @Override
    public void onEnable() {

        try (InputStream stream = MoneyPit.class.getResourceAsStream("api-version.txt")) {
            if (stream != null) {
                 this.apiVersion = new Scanner(stream, "UTF-8").useDelimiter("\\A").next();
            } else {
                getLogger().warning("Could not read api-version!");
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
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
