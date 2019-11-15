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
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("uri = " + req.getRequestURI());
		ObjectMapper om = new ObjectMapper();
		if ("/ReimbursementApi/auth/login".equals(req.getRequestURI())) {
			User credentials = (User) om.readValue(req.getReader(), User.class);
			User loggedInUser = userDao.findByUsernameAndPassword(credentials.getErsUsername(),
					credentials.getErsPassword());
			if (loggedInUser == null) {
				resp.setStatus(401); // Unauthorized status code
				return;
			} else {
				resp.setStatus(201); // success code
				req.getSession().setAttribute("user", loggedInUser); // set the session info
				//System.out.println(req.getSession().getAttribute("user"));
				resp.getWriter().write(om.writeValueAsString(loggedInUser)); // return the user
				return;
			}
		} else if ("/ReimbursementApi/auth/logout".equals(req.getRequestURI())) {
			System.out.println("logged out");
			resp.setStatus(201); // success code
			req.getSession().invalidate();//setAttribute("user", null); // set the session info
			//resp.getWriter().write(om.writeValueAsString(null)); // return the user
			return;
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if ("/ReimbursementApi/auth/session-user".equals(req.getRequestURI())) {
			ObjectMapper om = new ObjectMapper();
			//System.out.println("hi");
			String json = om.writeValueAsString(req.getSession().getAttribute("user"));
			//System.out.println(json);
			//System.out.println(req.getSession().getAttribute("user"));
			resp.getWriter().write(json);
		}
	}
}
