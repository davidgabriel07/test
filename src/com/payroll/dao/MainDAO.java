package com.payroll.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.payroll.model.EmployeeBean;
import com.payroll.model.LoginBean;

@Repository
public class MainDAO {

	@Autowired
	SessionFactory factory;
	
	@Transactional
	public Boolean getUser(String uname, String pass) {
		Session session = factory.getCurrentSession();
		List<LoginBean> lb = (List<LoginBean>)session.createQuery("from LoginBean s where s.uname = '" + uname + "'").list();
		if(lb == null){
			return false;
		}
		if(lb.get(0).getUname().equals(uname) && lb.get(0).getPass().equals(pass))
			return true;
		return false;
	}
	
	@Transactional
	public List<EmployeeBean> getEmployees(){
		Session session = factory.getCurrentSession();
		List<EmployeeBean> eb = (List<EmployeeBean>)session.createQuery("from EmployeeBean").list();
		return eb;
	}

	@Transactional
	public void addUser(LoginBean lb) {
		Session session = factory.getCurrentSession();
		session.save(lb);
	}

	@Transactional
	public void delUser(int id) {
		Session session = factory.getCurrentSession();
		EmployeeBean eb = (EmployeeBean)session.get(EmployeeBean.class,id);
		session.delete(eb);
	}
		
}
