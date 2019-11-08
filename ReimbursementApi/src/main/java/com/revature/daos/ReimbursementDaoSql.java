package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.util.ConnectionUtil;

public class ReimbursementDaoSql implements ReimbursementDao {
	Reimbursement extractReimbursement(ResultSet rs) throws SQLException {
		int id = rs.getInt("reimb_id");
		Double amount = rs.getDouble("reimb_amount");
		Timestamp submit = rs.getTimestamp("reimb_submitted");
		Timestamp resolve = rs.getTimestamp("reimb_resolved");
		String descrip = rs.getString("reimb_description");
		int author = rs.getInt("reimb_author");
		int resolver = rs.getInt("reimb_resolver");
		int status = rs.getInt("reimb_status_id");
		int type = rs.getInt("reimb_type_id");
		return new Reimbursement(id, amount, submit, resolve, descrip, author, resolver, status, type);
	}

	public int submit(Reimbursement reimb) {
		try (Connection c = ConnectionUtil.getConnection()) {
			double amount = reimb.getReimbAmount();
			String descrip = reimb.getReimbDescription();
			int author = reimb.getReimbAuthor();
			int type = reimb.getReimbTypeId();
			String sql = "INSERT INTO ers_reimbursement "
					+ "(reimb_amount, reimb_description, reimb_author, reimb_type_id) " + "VALUES(?, ?, ?, ?)";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setDouble(1, amount);
			ps.setString(2, descrip);
			ps.setInt(3, author);
			ps.setInt(4, type);
			ps.executeUpdate();
			String sql2 = "SELECT * FROM ers_reimbursement";
			PreparedStatement ps2 = c.prepareStatement(sql2);
			ResultSet rs = ps2.executeQuery();
			List<Reimbursement> reimbursements = new ArrayList<>();
			while (rs.next()) {
				reimbursements.add(extractReimbursement(rs));
			}
			int id = reimbursements.get(0).getReimbId();
			for (int i = 1; i < reimbursements.size(); i++) {
				if (id < reimbursements.get(i).getReimbId()) {
					id = reimbursements.get(i).getReimbId();
				}
			}
			return id;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}

	public List<Reimbursement> findAll() {

		try (Connection c = ConnectionUtil.getConnection()) {

			String sql = "SELECT * FROM ers_reimbursement";

			PreparedStatement ps = c.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			List<Reimbursement> reimbursements = new ArrayList<>();
			while (rs.next()) {
				reimbursements.add(extractReimbursement(rs));
			}

			return reimbursements;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	public List<Reimbursement> findByStatus(int status) {
		try (Connection c = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM ers_reimbursement WHERE reimb_status_id = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, status);
			ResultSet rs = ps.executeQuery();
			List<Reimbursement> reimbursements = new ArrayList<>();
			while (rs.next()) {
				reimbursements.add(extractReimbursement(rs));
			}

			return reimbursements;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public List<Reimbursement> findByUser(int userId) {
		try (Connection c = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM ers_reimbursement WHERE reimb_author = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();
			List<Reimbursement> reimbursements = new ArrayList<>();
			while (rs.next()) {
				reimbursements.add(extractReimbursement(rs));
			}

			return reimbursements;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public List<Reimbursement> findByUserAndStatus(int userId, int status) {
		try (Connection c = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM ers_reimbursement WHERE reimb_status_id = ? AND reimb_author = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, status);
			ps.setInt(2, userId);
			ResultSet rs = ps.executeQuery();
			List<Reimbursement> reimbursements = new ArrayList<>();
			while (rs.next()) {
				reimbursements.add(extractReimbursement(rs));
			}

			return reimbursements;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int update(int resolver, int status, int id) {
		try (Connection c = ConnectionUtil.getConnection()) {
			String sql = "UPDATE ers_reimbursement " + "SET reimb_resolver = ?, reimb_status_id = ? "
					+ "WHERE reimb_id = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, resolver);
			ps.setInt(2, status);
			ps.setInt(3, id);
			ps.executeUpdate();

			return id;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}

}
