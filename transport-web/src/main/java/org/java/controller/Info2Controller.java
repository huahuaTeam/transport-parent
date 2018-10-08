package org.java.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.java.entity.Info2;
import org.java.mapper.Info2Mapper;
import org.java.service.Info2Service;
import org.java.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Info2Controller {
	
	@Autowired
	private Info2Service info2Service;

	@Autowired
    private Info2Mapper mapper;

	@Autowired
    private TestService testService;


	@RequestMapping("login")
	public String login(String login,String pwd,HttpSession session,Model model){
        Map<String,Object> user =testService.selUser(login,pwd);
        if(user!=null){
            session.setAttribute("user",user);
            return "index";
        }
        model.addAttribute("err","输入的账号密码有误");
        return  "login";
    }

	@RequestMapping("/")
	public String load(){
	    return "login";
    }

    @RequestMapping("/blank")
    public String blank() {
        return "blank";
    }
}
