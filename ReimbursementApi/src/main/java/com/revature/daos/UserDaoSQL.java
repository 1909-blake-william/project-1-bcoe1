package com.revature.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.revature.models.User;

public class UserDaoSQL implements UserDao{
	
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
		// TODO Auto-generated method stub
		return null;
	}

	public User findById() {
		// TODO Auto-generated method stub
		return null;
	}

	public User findByUsernameAndPassword(String username, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

}
