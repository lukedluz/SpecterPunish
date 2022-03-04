package me.lukedluz.SpecterPunish.Settings;

import me.lukedluz.SpecterPunish.Main;


import net.md_5.bungee.config.*;

public class Settings {

	public String host,user,password,database;
	protected YamlConfig config;
	public static Configuration configuration;
	
    public Settings() {
        this.config = null;
        try {
            (this.config = new YamlConfig("config.yml", Main.getInstance())).saveDefaultConfig();
            this.config.loadConfig();
            configuration = this.config.getConfig();
            loadConfig();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public boolean loadConfig() {
    	if (configuration == null) {
    		return false;
    	}
    	host = configuration.getString("mysql.host");
    	user = configuration.getString("mysql.user");
    	password = configuration.getString("mysql.password");
    	database = configuration.getString("mysql.database");
    	return true;
    }
    
}
