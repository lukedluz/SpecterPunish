package me.lukedluz.SpecterPunish.Settings;

import net.md_5.bungee.api.plugin.*;
import java.nio.file.*;
import java.io.*;
import net.md_5.bungee.config.*;

public class YamlConfig {

    private final File configFile;
    private final String fileName;
    private final Plugin plugin;
    private final File folder;
    private static Configuration config;
    
    public YamlConfig(final String fileName, final Plugin plugin) {
        this.plugin = plugin;
        this.fileName = fileName;
        this.folder = plugin.getDataFolder();
        this.configFile = new File(this.folder, fileName);
    }
    
    public void saveDefaultConfig() {
        if (!this.folder.exists()) {
            this.folder.mkdirs();
        }
        try {
            if (!this.configFile.exists()) {
                Files.copy(this.plugin.getResourceAsStream(this.fileName), this.configFile.toPath(), new CopyOption[0]);
            }
            this.loadConfig();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public void loadConfig() throws IOException {
        YamlConfig.config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(this.configFile);
    }
    
    public void saveConfig() throws IOException {
        ConfigurationProvider.getProvider(YamlConfiguration.class).save(YamlConfig.config, this.configFile);
    }
    
    public Configuration getConfig() {
        return YamlConfig.config;
    }
}
