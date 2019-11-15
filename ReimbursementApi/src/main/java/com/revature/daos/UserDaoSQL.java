package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.User;
import com.revature.util.ConnectionUtil;

public class UserDaoSQL implements UserDao {

	User extractUser(ResultSet rs) throws SQLException {
		int id = rs.getInt("ers_users_id");
		String rsUsername = rs.getString("ers_username");
		String rsPassword = rs.getString("ers_password");
		String firstName = rs.getString("user_first_name");
		String lastName = rs.getString("user_last_name");
		String email = rs.getString("user_email");
		int role = rs.getInt("user_role_id");
		return new User(id, rsUsername, rsPassword, firstName, lastName, email, role);
	}

	public int save(User u) {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<User> findAll() {
		try (Connection c = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM ers_users ";

			PreparedStatement ps = c.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			List<User> users = new ArrayList<User>();

			while (rs.next()) {
				users.add(extractUser(rs));
			}
			return users;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public User findById() {
		// TODO Auto-generated method stub
		return null;
	}

	public User findByUsernameAndPassword(String username, String password) {
		try (Connection c = ConnectionUtil.getConnection()) {

			String sql = "SELECT * FROM ers_users " + "WHERE ers_username = ? AND ers_password = ?";

			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return extractUser(rs);
			} else {
				return null;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public User findByUsername(String username) {
		try (Connection c = ConnectionUtil.getConnection()) {

			String sql = "SELECT * FROM ers_users " + "WHERE ers_username = ?";

			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, username);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return extractUser(rs);
			} else {
				return null;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}
