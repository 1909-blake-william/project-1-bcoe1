package com.revature.servlets;

import java.util.List;

import com.revature.daos.ReimbursementDao;
import com.revature.models.Reimbursement;

public class driver {
	
	
	public static void main(String[] args) {
		ReimbursementDao reimDao = ReimbursementDao.currentImplementation;
		//Reimbursement reim = new Reimbursement(0, 33.9, null, null, "I want to test if submit function works", 1, 0, 0, 1);
		
		//System.out.println(reimDao.update(21, 2, 1));
		
//		System.out.println(reimDao.findByStatus(1));
//		System.out.println(reimDao.findByUser(1));
//		System.out.println(reimDao.findByUserAndStatus(1, 1));
	}
	
	

}
