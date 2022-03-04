package me.lukedluz.SpecterPunish.Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import me.lukedluz.SpecterPunish.Main;

public class Methods {

	public int getCurrentId() {
		try {
			PreparedStatement ps = Main.getInstance().getSql().connection.prepareStatement("SELECT * FROM punish_geral");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				return rs.getInt("id_atual");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int getaAllPunishments() {
		try {
			PreparedStatement ps = Main.getInstance().getSql().connection.prepareStatement("SELECT * FROM punish_geral");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				return rs.getInt("punicoes");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public boolean isBanned(String name) {
		try {
			PreparedStatement ps = Main.getInstance().getSql().connection.prepareStatement("SELECT * FROM punish_punishments WHERE nome='" + name + "';");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				return rs.getBoolean("status");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	

	public boolean isTempBanned(String name) {
		try {
			PreparedStatement ps = Main.getInstance().getSql().connection.prepareStatement("SELECT * FROM punish_temporary WHERE nome='" + name + "';");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				return rs.getBoolean("status");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public int getActiveBannedId(String name) {
		try {
			PreparedStatement ps = Main.getInstance().getSql().connection.prepareStatement("SELECT * FROM punish_temporary WHERE nome='" + name + "';");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				if (rs.getBoolean("status")) {
					return rs.getInt("id");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public long getFirstBannedTime(String name) {
		try {
			PreparedStatement ps = Main.getInstance().getSql().connection.prepareStatement("SELECT * FROM punish_temporary WHERE nome='" + name + "';");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				if (rs.getBoolean("status")) {
					return rs.getLong("start");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public String getPunisherName(String name) {
		try {
			PreparedStatement ps = Main.getInstance().getSql().connection.prepareStatement("SELECT * FROM punish_temporary WHERE nome='" + name + "';");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				if (rs.getBoolean("status")) {
					return rs.getString("punisher");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Ningu�m";
	}
	
	public void punish(String name, String motivo, String punisher, String prova) {
		try {
			int id = getCurrentId()+1;
			long data = System.currentTimeMillis();
			PreparedStatement ps = Main.getInstance().getSql().connection.prepareStatement("INSERT INTO punish_punishments (nome,id,motivo,punisher,status,start,prova) VALUES (?,?,?,?,?,?,?)");
			ps.setString(1, name);
			ps.setInt(2, id);
			ps.setString(3, motivo);
			ps.setString(4, punisher);
			ps.setBoolean(5, true);
			ps.setLong(6, data);
			ps.setString(7, prova);
			ps.executeUpdate();
			updatePunishsAmount();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void unpunish(String name) {
		try {
			PreparedStatement ps = Main.getInstance().getSql().connection.prepareStatement("UPDATE punish_punishments SET status=? WHERE nome='" + name + "';");
			ps.setBoolean(1, false);
			ps.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updatePunishsAmount() {
		try {
			int id = getCurrentId()+1;
			PreparedStatement ps = Main.getInstance().getSql().connection.prepareStatement("UPDATE punish_geral SET id_atual='" + id + "';");
			ps.executeUpdate();
			PreparedStatement ps1 = Main.getInstance().getSql().connection.prepareStatement("UPDATE punish_geral SET punicoes='0';");
			ps1.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void temppunish(String name, String motivo, String punisher, String prova, Long end) {
		try {
			int id = getCurrentId()+1;
			long inicio = System.currentTimeMillis();
			long fim = end;
			PreparedStatement ps = Main.getInstance().getSql().connection.prepareStatement("INSERT into punish_temporary (nome,id,motivo,punisher,status,start,end,prova) VALUES (?,?,?,?,?,?,?,?)");
			ps.setString(1, name);
			ps.setInt(2, id);
			ps.setString(3, motivo);
			ps.setString(4, punisher);
			ps.setBoolean(5, true);
			ps.setLong(6, inicio);
			ps.setLong(7, fim);
			ps.setString(8, prova);
			ps.executeUpdate();
			updatePunishsAmount();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void untemppunish(String name) {
		try {
			PreparedStatement ps = Main.getInstance().getSql().connection.prepareStatement("UPDATE punish_temporary SET status=? WHERE nome='" + name + "';");
			ps.setBoolean(1, false);
			ps.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
