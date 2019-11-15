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
		int id = rs.getInt(1);
		Double amount = rs.getDouble(2);
		Timestamp submit = rs.getTimestamp(3);
		Timestamp resolve = rs.getTimestamp(4);
		String descrip = rs.getString(5);
		int author = rs.getInt(6);
		int resolver = rs.getInt(7);
		int status = rs.getInt(8);
		int type = rs.getInt(9);
		String name = rs.getString(11);
		String nameRes = rs.getString(22);
		String aStatus = rs.getString(18);
		String aType = rs.getString(20);
		return new Reimbursement(id, amount, submit, resolve, descrip, author, resolver, status, type, name, nameRes,
				aStatus, aType);
	}

	public Reimbursement submit(Reimbursement reimb) {
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

//			String sql2 = "SELECT * FROM ers_reimbursement r"
//					+ "LEFT JOIN ers_users u ON (r.reimb_author = u.ers_users_id) "
//					+ "LEFT JOIN ers_reimbursement_status s ON (r.reimb_status_id = s.reimb_status_id) "
//					+ "LEFT JOIN ers_reimbursement_type t ON (r.reimb_type_id = t.reimb_type_id) "
//					+ "LEFT JOIN ers_users uu ON (r.reimb_resolver = uu.ers_users_id)";
			String sql2 = "SELECT * FROM (ers_reimbursement r "
					+ "LEFT JOIN ers_users u ON (r.reimb_author = u.ers_users_id)) "
					+ "LEFT JOIN ers_reimbursement_status s ON (r.reimb_status_id = s.reimb_status_id) "
					+ "LEFT JOIN ers_reimbursement_type t ON (r.reimb_type_id = t.reimb_type_id) "
					+ "LEFT JOIN ers_users uu ON (r.reimb_resolver = uu.ers_users_id)";

//			r.reimb_id as id, r.reimb_amount amount, r.reimb_submitted submitted, "
//					+ "r.reimb_resolved resolved, r.reimb_description descrip, r.reimb_author authorId, "
//					+ "r.reimb_resolver resolverId, r.reimb_status_id statusId, r.reimb_type_id typeId, "
//					+ "u.ers_username author, s.reimb_status status, t.reimb_type type, uu.ers_username resolver "
			PreparedStatement ps2 = c.prepareStatement(sql2);
			ResultSet rs = ps2.executeQuery();
			Reimbursement reimbursement = new Reimbursement();
			List<Reimbursement> reimbursements = new ArrayList<>();
			while (rs.next()) {
				reimbursements.add(extractReimbursement(rs));
			}
			int id = reimbursements.get(0).getReimbId();
			for (int i = 1; i < reimbursements.size(); i++) {
				if (id < reimbursements.get(i).getReimbId()) {
					id = reimbursements.get(i).getReimbId();
					reimbursement = reimbursements.get(i);
				}
			}

			return reimbursement;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public List<Reimbursement> findAll() {

		try (Connection c = ConnectionUtil.getConnection()) {

			String sql = "SELECT * FROM (ers_reimbursement r "
					+ "LEFT JOIN ers_users u ON (r.reimb_author = u.ers_users_id)) "
					+ "LEFT JOIN ers_reimbursement_status s ON (r.reimb_status_id = s.reimb_status_id) "
					+ "LEFT JOIN ers_reimbursement_type t ON (r.reimb_type_id = t.reimb_type_id) "
					+ "LEFT JOIN ers_users uu ON (r.reimb_resolver = uu.ers_users_id)";

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
//			String sql = "SELECT * FROM ers_reimbursement r "
//					+ "LEFT JOIN ers_users u ON (r.reimb_author = u.ers_users_id) "
//					+ "LEFT JOIN ers_reimbursement_status s USING (reimb_status_id) "
//					+ "LEFT JOIN ers_reimbursement_type t USING (reimb_type_id) "
//					+ "LEFT JOIN ers_users uu ON (r.reimb_resolver = uu.ers_users_id) "
//					+ "WHERE reimb_status_id = ?";

			String sql = "SELECT * FROM (ers_reimbursement r "
					+ "LEFT JOIN ers_users u ON (r.reimb_author = u.ers_users_id)) "
					+ "LEFT JOIN ers_reimbursement_status s ON (r.reimb_status_id = s.reimb_status_id) "
					+ "LEFT JOIN ers_reimbursement_type t ON (r.reimb_type_id = t.reimb_type_id) "
					+ "LEFT JOIN ers_users uu ON (r.reimb_resolver = uu.ers_users_id) " + "WHERE r.reimb_status_id = ?";
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
//			String sql = "SELECT * FROM ers_reimbursement r "
//					+ "LEFT JOIN ers_users u ON (r.reimb_author = u.ers_users_id) "
//					+ "LEFT JOIN ers_reimbursement_status s USING (reimb_status_id) "
//					+ "LEFT JOIN ers_reimbursement_type t USING (reimb_type_id) "
//					+ "LEFT JOIN ers_users uu ON (r.reimb_resolver = uu.ers_users_id) "
//					+ "WHERE r.reimb_author = ?";

			String sql = "SELECT * FROM (ers_reimbursement r "
					+ "LEFT JOIN ers_users u ON (r.reimb_author = u.ers_users_id)) "
					+ "LEFT JOIN ers_reimbursement_status s ON (r.reimb_status_id = s.reimb_status_id) "
					+ "LEFT JOIN ers_reimbursement_type t ON (r.reimb_type_id = t.reimb_type_id) "
					+ "LEFT JOIN ers_users uu ON (r.reimb_resolver = uu.ers_users_id) " + "WHERE r.reimb_author = ?";
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
//			String sql = "SELECT * FROM ers_reimbursement r "
//					+ "LEFT JOIN ers_users u ON (r.reimb_author = u.ers_users_id) "
//					+ "LEFT JOIN ers_reimbursement_status s USING (reimb_status_id) "
//					+ "LEFT JOIN ers_reimbursement_type t USING (reimb_type_id) "
//					+ "LEFT JOIN ers_users uu ON (r.reimb_resolver = uu.ers_users_id) "
//					+ "WHERE r.reimb_status_id = ? AND r.reimb_author = ?";

			String sql = "SELECT * FROM (ers_reimbursement r "
					+ "LEFT JOIN ers_users u ON (r.reimb_author = u.ers_users_id)) "
					+ "LEFT JOIN ers_reimbursement_status s ON (r.reimb_status_id = s.reimb_status_id) "
					+ "LEFT JOIN ers_reimbursement_type t ON (r.reimb_type_id = t.reimb_type_id) "
					+ "LEFT JOIN ers_users uu ON (r.reimb_resolver = uu.ers_users_id) "
					+ "WHERE r.reimb_status_id = ? AND r.reimb_author = ?";
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
			String sqlCheck = "SELECT * FROM ers_reimbursement WHERE reimb_id = ?";
			PreparedStatement psCheck = c.prepareStatement(sqlCheck);
			psCheck.setInt(1, id);
			ResultSet rs = psCheck.executeQuery();
			rs.next();
			int author = rs.getInt("reimb_author");

			if (author == resolver) {
				return -1;
			}

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

	@Override
	public int delete(int id) {

		try (Connection c = ConnectionUtil.getConnection()) {
			String sql = "DELETE FROM ers_reimbursement WHERE reimb_id = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();

			return id;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}

}
