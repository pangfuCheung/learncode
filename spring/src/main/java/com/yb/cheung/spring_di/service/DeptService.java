package com.yb.cheung.spring_di.service;


import com.yb.cheung.spring_di.dao.DeptDao;

public class DeptService {
     
	 private DeptDao dao;
	 
	 
	
	/**
	 * @return the dao
	 */
	public DeptDao getDao() {
		return dao;
	}



	/**
	 * @param dao the dao to set
	 */
	public void setDao(DeptDao dao) {
		this.dao = dao;
	}



	public void deptAdd(){
		dao.save();
	}
}
