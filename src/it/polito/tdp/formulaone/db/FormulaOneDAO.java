package it.polito.tdp.formulaone.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.formulaone.model.GarePiloti;
import it.polito.tdp.formulaone.model.Race;
import it.polito.tdp.formulaone.model.Season;

public class FormulaOneDAO {

	public List<Season> getAllSeasons() {
		String sql = "SELECT year, url FROM seasons ORDER BY year DESC";
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			List<Season> list = new ArrayList<>();
			while (rs.next()) {
				list.add(new Season(rs.getInt("year"), rs.getString("url")));
			}
			conn.close();
			return list;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Race> getRacesByYear(Integer year, Map<Integer, Race> idMapRaces){
		
		String sql = "SELECT * " + 
				"FROM races r " + 
				"WHERE r.YEAR = ?";
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, year);
			ResultSet rs = st.executeQuery();
			List<Race> list = new ArrayList<>();
			while (rs.next()) {
				
				Race r = new Race(rs.getInt("raceId"), 
						Year.of(rs.getInt("year")), 
						rs.getInt("round"), 
						rs.getInt("circuitId"), 
						rs.getString("name"), 
						rs.getTimestamp("date").toLocalDateTime().toLocalDate(), 
						/*rs.getTimestamp("time").toLocalDateTime().toLocalTime()*/null, 
						rs.getString("url"));
				
				list.add(r);
				idMapRaces.put(rs.getInt("raceId"), r);
			}
			conn.close();
			return list;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public List<GarePiloti> getArchi(Integer year){
		
		String sql = "SELECT r1.raceId, r2.raceId, COUNT(r1.driverId)as cnt " + 
				"FROM results r1, results r2, races r " + 
				"WHERE r1.driverId = r2.driverId " + 
				"AND r.raceId = r1.raceId " + 
				"AND r.YEAR = ? " + 
				"AND r1.statusId = 1 " + 
				"AND r2.statusId = 1 " + 
				"AND r1.raceId > r2.raceId " + 
				"GROUP BY r1.raceId, r2.raceId";
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, year);
			ResultSet rs = st.executeQuery();
			List<GarePiloti> list = new ArrayList<>();
			while (rs.next()) {
				list.add(new GarePiloti(rs.getInt("r1.raceId"), 
						rs.getInt("r2.raceId"), 
						rs.getInt("cnt")));
			}
			conn.close();
			return list;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		
	}

}


