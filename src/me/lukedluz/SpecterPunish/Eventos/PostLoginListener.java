package me.lukedluz.SpecterPunish.Eventos;

import java.text.SimpleDateFormat;

import me.lukedluz.SpecterPunish.Main;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.event.PreLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class PostLoginListener implements Listener {
	
//	@SuppressWarnings("deprecation")
//	@EventHandler
//	public void onPostlogin(PostLoginEvent e) {
//		ProxiedPlayer p = e.getPlayer();
//		if (Main.getInstance().getMethods().isBanned(p.getName()) || Main.getInstance().getMethods().isTempBanned(p.getName())) {
//			int id = Main.getInstance().getMethods().getActiveBannedId(p.getName());
//
//			SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy HH:mm");
//			
//			long data = Main.getInstance().getMethods().getFirstBannedTime(p.getName());
//			p.disconnect("�c�lSNOCK MC\n�r�f\n�cVoc� foi punido do servidor!\n�r�cPara recorrer a sua puni��o visite nosso team speak!\n�cID da puni��o: �f#" + id + "\n�cData da puni��o: �f" + date.format(System.currentTimeMillis() - data) + "\n�r \n�cAcesse: �fhttp://snockmc.com\n�r ");
//		}
//	}
	
	@EventHandler
	public void prelogin(PreLoginEvent e) {
		String name = e.getConnection().getName();
		if (Main.getInstance().getMethods().isBanned(name) || Main.getInstance().getMethods().isTempBanned(name)) {
			int id = Main.getInstance().getMethods().getActiveBannedId(name);

			SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy HH:mm");
			
			long data = Main.getInstance().getMethods().getFirstBannedTime(name);
			e.setCancelled(true);
			e.setCancelReason("�c�lSNOCK MC\n�r�f\n�cVoc� foi punido do servidor!\n�r�cPara recorrer a sua puni��o visite nosso team speak!\n�cID da puni��o: �f#" + id + "\n�cData da puni��o: �f" + date.format(System.currentTimeMillis() - data) + "\n�r \n�cAcesse: �fhttp://snockmc.com\n�r ");
		}
	}
}
