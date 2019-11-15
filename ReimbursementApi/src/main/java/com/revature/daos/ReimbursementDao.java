package com.revature.daos;

import java.util.List;

import com.revature.models.Reimbursement;
import com.revature.models.User;

public interface ReimbursementDao {
	
	ReimbursementDao currentImplementation = new ReimbursementDaoSql();
	
	Reimbursement submit(Reimbursement reimb);
	
	int update(int resolver, int status, int id);
	
	List<Reimbursement> findAll();
	
	List<Reimbursement> findByStatus(int status);
	
	List<Reimbursement> findByUser(int userId);
	
	List<Reimbursement> findByUserAndStatus(int userId, int status);
	
	int delete(int id);

}
