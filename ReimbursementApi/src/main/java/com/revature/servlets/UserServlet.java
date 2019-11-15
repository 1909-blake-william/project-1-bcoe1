package com.revature.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.daos.UserDao;
import com.revature.models.User;

public class UserServlet extends HttpServlet {

	private UserDao userDao = UserDao.currentImplementation;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println(req.getRequestURL());
		resp.addHeader("Access-Control-Allow-Origin", "http://localhost:5500");
		resp.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
		resp.addHeader("Access-Control-Allow-Headers",
				"Origin, Methods, Credentials, X-Requested-With, Content-Type, Accept");
		resp.addHeader("Access-Control-Allow-Credentials", "true");
		resp.setContentType("application/json");
		// TODO Auto-generated method stub
		super.service(req, resp);

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		ObjectMapper om = new ObjectMapper();

		String username = req.getParameter("username");
		String json;
		
		if (username == null) {
			 List<User> users = userDao.findAll();
			json = om.writeValueAsString(users);
		} else {
			User user = userDao.findByUsername(username);
			json = om.writeValueAsString(user);
		}

		resp.addHeader("content-type", "application/json");
		resp.getWriter().write(json);
	}
}
