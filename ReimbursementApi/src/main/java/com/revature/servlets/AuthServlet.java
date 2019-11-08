package com.revature.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.daos.UserDao;
import com.revature.models.User;

public class AuthServlet extends HttpServlet {

	private UserDao userDao = UserDao.currentImplementation;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.service(req, resp);
		//System.out.println("To context param: " + req.getServletContext().getInitParameter("To"));
		resp.addHeader("Access-Control-Allow-Origin", "http://localhost:4200");
		resp.addHeader("Access-Control-Allow-Headers",
				"Origin, Methods, Credentials, X-Requested-With, Content-Type, Accept");
		resp.addHeader("Access-Control-Allow-Credentials", "true");
		resp.setContentType("application/json");
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("uri = " + req.getRequestURI());
		if ("/PokemonApi/auth/login".equals(req.getRequestURI())) {
			ObjectMapper om = new ObjectMapper();
			User credentials = (User) om.readValue(req.getReader(), User.class);
			User loggedInUser = userDao.findByUsernameAndPassword(credentials.getUsername(), credentials.getPassword());
			if (loggedInUser == null) {
				resp.setStatus(401); // Unauthorized status code
				return;
			} else {
				resp.setStatus(201);
				req.getSession().setAttribute("user", loggedInUser);
				resp.getWriter().write(om.writeValueAsString(loggedInUser));
				return;
			}
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if ("/PokemonApi/auth/session-user".equals(req.getRequestURI())) {
			ObjectMapper om = new ObjectMapper();
			String json = om.writeValueAsString(req.getSession().getAttribute("user"));
			resp.getWriter().write(json);
		}
	}
}
