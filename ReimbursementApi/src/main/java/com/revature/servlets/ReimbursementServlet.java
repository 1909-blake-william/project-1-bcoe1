package com.revature.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.daos.ReimbursementDao;
import com.revature.models.Reimbursement;

public class ReimbursementServlet extends HttpServlet {

	private ReimbursementDao reimDao = ReimbursementDao.currentImplementation;

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
		String userIdStr = req.getParameter("user");
		String statusStr = req.getParameter("status");
		List<Reimbursement> reimbursements = new ArrayList<>();
		if (userIdStr != null && statusStr != null) {
			int userId = Integer.parseInt(userIdStr);
			int status = Integer.parseInt(statusStr);
			reimbursements = reimDao.findByUserAndStatus(userId, status);
		} else if (userIdStr != null) {
			int userId = Integer.parseInt(userIdStr);
			reimbursements = reimDao.findByUser(userId);
		} else if (statusStr != null) {
			int status = Integer.parseInt(statusStr);
			reimbursements = reimDao.findByStatus(status);
		} else {
			reimbursements = reimDao.findAll();

		}
		String json = om.writeValueAsString(reimbursements);
		resp.addHeader("content-type", "application/json");
		resp.getWriter().write(json);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// read the reimbursement from the request body
		ObjectMapper om = new ObjectMapper();
		// System.out.println(req.getReader());
		Reimbursement r = (Reimbursement) om.readValue(req.getReader(), Reimbursement.class);
		// System.out.println(r);

		Reimbursement reim = reimDao.submit(r);
		System.out.println(reim);
		// reim is submitted reimbursement, but now also has an id and timestamp.

		String json = om.writeValueAsString(reim);

		resp.getWriter().write(json);
		resp.setStatus(201); // created status code

	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper om = new ObjectMapper();
		String resolverStr = req.getParameter("resolver");
		String idStr = req.getParameter("id");
		String statusStr = req.getParameter("status");

		int resolver = Integer.parseInt(resolverStr);
		int status = Integer.parseInt(statusStr);
		int id = Integer.parseInt(idStr);
		int check = reimDao.update(resolver, status, id);
		if (check == -1) {
			resp.setStatus(401);
		} else if (check == 0) {
			resp.setStatus(400);
		} else {
			resp.setStatus(204);
		}
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper om = new ObjectMapper();
		// System.out.println(req.getReader());
		System.out.println("in delete");
		int id = (int) om.readValue(req.getReader(), Integer.class);
		reimDao.delete(id);
	}

}
