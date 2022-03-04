package me.lukedluz.SpecterPunish.Utils;

import me.lukedluz.SpecterPunish.Main;
import me.lukedluz.SpecterPunish.Comandos.PunishCommand;

public class CommandManager {

	private PunishCommand punish = new PunishCommand();
	
	public CommandManager(Main m) {
		m.getProxy().getPluginManager().registerCommand(m, punish);
	}
	
}
