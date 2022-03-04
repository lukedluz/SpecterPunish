package me.lukedluz.SpecterPunish;

import me.lukedluz.SpecterPunish.Database.Methods;
import me.lukedluz.SpecterPunish.Database.MySQL;
import me.lukedluz.SpecterPunish.Utils.CommandManager;
import me.lukedluz.SpecterPunish.Utils.ListenerManager;
import me.lukedluz.SpecterPunish.Settings.Settings;
import net.md_5.bungee.api.plugin.Plugin;

public class Main extends Plugin {

    private static Main i;
    private String host,user,password,database;
    public MySQL sql;
    public Settings settings;
    public Methods methods;

    public Main() {
        Main.i = this;
    }

    public static Main getInstance() {
        return i;
    }

    @Override
    public void onEnable() {
        settings = new Settings();
        this.host = settings.host;
        this.user = settings.user;
        this.database = settings.database;
        this.password = settings.password;
        sql = new MySQL(host, user, password, database);
        sql.startConnection();
        methods = new Methods();

        new ListenerManager(this);
        new CommandManager(this);
    }

    @Override
    public void onDisable() {
        sql.stopConnection();
    }

    public MySQL getSql() {
        return sql;
    }

    public Methods getMethods() {
        return methods;
    }
}
