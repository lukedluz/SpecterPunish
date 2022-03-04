package me.lukedluz.SpecterPunish.Comandos;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import me.lukedluz.SpecterPunish.Main;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.config.Configuration;

public class PunishCommand extends Command {
	
	public PunishCommand() {
		super("punir");
	}
	public static Configuration configuration;

	@SuppressWarnings("deprecation")
	@Override
	public void execute(CommandSender sender, String[] args) {
		if (sender.hasPermission(configuration.getString("Permissoes.Usar"))) {
			if (args.length < 1) {
				sender.sendMessage(configuration.getString("Mensagens.ErroComando").replace("&", "§"));
				return;
			} else if (args.length == 1) {
				ProxiedPlayer p = ProxyServer.getInstance().getPlayer(args[0]);
				sendMotives(sender, p);
				return;
			} else if (args.length >= 2) {
				ProxiedPlayer p = ProxyServer.getInstance().getPlayer(args[0]);
				String message = "";
				for (int i = 0; i < args.length; i++) {
					message = message + args[i] + " ";
				}
				if (!sender.hasPermission(configuration.getString("Permissoes.Staff"))) {
					try {
						int id = Main.getInstance().getMethods().getCurrentId();
						String name = p.getName();
						String punisher = sender.getName();
						String motivo = message.split("http")[0].split(name)[1];
						String prova = message.split("http")[1];
						Main.getInstance().getMethods().punish(name, motivo, punisher, prova);
						ProxyServer.getInstance().broadcast("");
						if (sender instanceof ProxiedPlayer) {
							ProxyServer.getInstance().broadcast(configuration.getString("Mensagens.PunidoPor").replace("@player", "" + name).replace("@staff", "" + punisher).replace("&", "§"));
						} else {
							ProxyServer.getInstance().broadcast(configuration.getString("Mensagens.JogadorPunido").replace("@player", "" + name).replace("&", "§"));
						}
						ProxyServer.getInstance().broadcast(configuration.getString("Mensagens.Motivo").replace("@motivo", ""+motivo).replace("&", "§"));
						
						SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy HH:mm");
						
						p.disconnect(configuration.getString("Mensagens.PunidoDisconnect").replace("@id", "" + id).replace("@data", "" + date.format(System.currentTimeMillis())).replace("&", "§"));
						return;
					} catch (Exception e) {
						sender.sendMessage(configuration.getString("Mensagens.SemProva").replace("&", "§"));
						return;
					}
				} else {
					try {
						int id = Main.getInstance().getMethods().getCurrentId();
						String name = p.getName();
						String punisher = sender.getName();
						String motivo = message.split("http")[0].split(name)[1];
						String prova = message.split("http")[1];
						Main.getInstance().getMethods().punish(name, motivo, punisher, prova);
						ProxyServer.getInstance().broadcast("");
						if (sender instanceof ProxiedPlayer) {
							ProxyServer.getInstance().broadcast(configuration.getString("Mensagens.PunidoPor").replace("@player", "" + name).replace("@staff", "" + punisher).replace("&", "§"));
						} else {
							ProxyServer.getInstance().broadcast(configuration.getString("Mensagens.JogadorPunido").replace("@player", "" + name).replace("&", "§"));
						}
						ProxyServer.getInstance().broadcast(configuration.getString("Mensagens.Motivo").replace("@motivo", ""+motivo).replace("&", "§"));
						
						SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy HH:mm");
						
						p.disconnect(configuration.getString("Mensagens.PunidoDisconnect").replace("@id", "" + id).replace("@data", "" + date.format(System.currentTimeMillis())).replace("&", "§"));
						return;
					} catch (Exception e) {
						int id = Main.getInstance().getMethods().getCurrentId();
						String name = p.getName();
						String punisher = sender.getName();
						Main.getInstance().getMethods().punish(name, "Uso de hack", punisher, "Nenhuma");
						ProxyServer.getInstance().broadcast("");
						if (sender instanceof ProxiedPlayer) {
							ProxyServer.getInstance().broadcast(configuration.getString("Mensagens.PunidoPor").replace("@player", "" + name).replace("@staff", "" + punisher).replace("&", "§"));
						} else {
							ProxyServer.getInstance().broadcast(configuration.getString("Mensagens.JogadorPunido").replace("@player", "" + name).replace("&", "§"));
						}
						ProxyServer.getInstance().broadcast(configuration.getString("Mensagens.Motivo" + "Uso de hack").replace("&", "§"));
						ProxyServer.getInstance().broadcast("");
						
						SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy HH:mm");
						
						p.disconnect(configuration.getString("Mensagens.PunidoDisconnect").replace("@id", "" + id).replace("@data", "" + date.format(System.currentTimeMillis())).replace("&", "§"));
						return;
					}
				}
			}
		} else {
			sender.sendMessage(configuration.getString("Mensagens.SemPermissao").replace("&", "§"));
			return;
		}
	}
	
	public static String generateString(Random rng, String characters, int length) {
		char[] text = new char[length];
		for (int i = 0; i < length; i++) {
			text[i] = characters.charAt(rng.nextInt(characters.length()));
		}
		return new String(text);
	}
	
	@SuppressWarnings("deprecation")
	public void sendMotives(CommandSender sender, ProxiedPlayer p) {
		List<String> motives = new ArrayList<>();
		motives.add("Flood (Inundação).");
		motives.add("Spam.");
		motives.add("Iniciativa de flood.");
		motives.add("Anti-jogo.");
		motives.add("Comércio de contas.");
		motives.add("Divulgação");
		motives.add("Ofensa a jogador.");
		motives.add("Ofensa ao servidor/staff.");
		motives.add("Desinformação.");
		motives.add("Anti-Jogo.");
		motives.add("Divulgação de servidores.");
		motives.add("Atitude de discriminação.");
		motives.add("Uso de Hack.");
		motives.add("Abuso de bugs.");
		motives.add("Estorno de compras.");
		motives.add("Nick impróprio.");
		motives.add("Falsificar provas.");
		sender.sendMessage("");
		sender.sendMessage("&ePunições disponíveis:");
		sender.sendMessage("");
		for (String values : motives) {
			String color = generateString(new Random(), "ef", 0);
			TextComponent text = new TextComponent();
			text.setText("  §" + color + values);
			text.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(configuration.getString("Mensagens.ConfirmarPunicao").replace("&", "§")).create()));
			text.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/punir " + p.getName() + " " + values.substring(0)));
			p.sendMessage(text);
		}
		sender.sendMessage("");
	}
}
