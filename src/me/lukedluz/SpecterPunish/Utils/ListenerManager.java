package me.lukedluz.SpecterPunish.Utils;

import me.lukedluz.SpecterPunish.Main;
import me.lukedluz.SpecterPunish.Eventos.PostLoginListener;

public class ListenerManager {

	private PostLoginListener login = new PostLoginListener();
	
	public ListenerManager(Main m) {
		m.getProxy().getPluginManager().registerListener(m, login);
	}
	
}
