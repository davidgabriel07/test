package com.payroll.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.payroll.dao.MainDAO;
import com.payroll.model.LoginBean;

@Controller
public class MainController {
	
	@Autowired
	HttpSession session;
	
	@Autowired
	MainDAO maindao;
	
	@Autowired
	HttpServletRequest request;
	
	@RequestMapping("/")
	public String returnIndex(){
		return "index";
	}
	
	@RequestMapping("/add")
	public String returnAdd(){
		return "home";
	}
	
	@RequestMapping("/error")
	public String returnError(){
		return "error";
	}
	
	@RequestMapping("/create")
	public String returnCreate(){
		return "create";
	}
	
	@RequestMapping("/view")
	public String returnView(){
		session.setAttribute("employees",maindao.getEmployees());
		return "view";
	}
	
	@RequestMapping("/login")
	public String returnHome(){
		String uname = request.getParameter("uname");
		String pass = request.getParameter("pass");
		Boolean log = maindao.getUser(uname,pass);
		if(log){
			return "redirect:/add";
		}
		return "redirect:/error";
	}
	
	@RequestMapping("/createUser")
	public String createUser(){
		String uname = request.getParameter("uname");
		String pass = request.getParameter("pass");
		String cpass = request.getParameter("cpass");
		if(cpass.equals(pass)){
			LoginBean lb = new LoginBean();
			lb.setPass(pass);
			lb.setUname(uname);
			maindao.addUser(lb);
			return "redirect:/index";
		}
		else{
			return "redirect:/error";
		}
	}
	
	@RequestMapping("/delUser")
	public String delUser(){
		maindao.delUser(Integer.parseInt(request.getParameter("empid")));
		return "redirect:/view";
	}
	
}
