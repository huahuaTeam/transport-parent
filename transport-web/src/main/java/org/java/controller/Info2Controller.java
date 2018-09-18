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
        System.out.println(login+"     "+pwd);
        Map<String,Object> user =testService.selUser(login,pwd);
        if(user!=null){

            System.out.println(user);
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


    @RequestMapping("/errors")
    public String error() {
        System.out.println("table");
        return "error-404";
    }


    @RequestMapping("/blank")
    public String blank() {
        return "blank";
    }

    @RequestMapping("/index")
    public String index(Model model,HttpSession session) {
        System.out.println("进入了index");
        model.addAttribute("info",1);
    	session.setAttribute("ses","jack");
        System.out.println("" +
                "111"
                +"ashfkjasnfioasjfaslfnasnlfjsdffnsaddasfasfasfasdasdasdasd"
        );
        System.out.println("" +
                "111"
                +"ashfkjasnfioasjfaslfnasnlfjsdffnsaddasfasfasfasdasdasdasd"
        );
        System.out.println("" +
                "111"
                +"ashfkjasnfioasjfaslfnasnlfjsdffnsaddasfasfasfasdasdasdasd"
        );
        System.out.println("" +
                "111"
                +"ashfkjasnfioasj1faslfnasnlfjsdffnsaddasfasfasfasdasdasdasd"
        );
        return "index";
    }
    
    @RequestMapping("/show")
    public String success(Model model) {
    	List<Info2> list = mapper.getList();
        for (Info2 info2 : list) {
            System.out.println(info2);
        }
        System.out.println("" +
                "111"
                +"ashfkjasnfioasjfaslfnasnlfjsdffnsaddasfasfasfasdasdasdasd"
        );
        model.addAttribute("list",list);
    	model.addAttribute("flag",true);
    	System.out.println("进入了success");
        return "success";
    }
    
    @RequestMapping("/paramter/{id}/{name}")
    public String success(@PathVariable("id")Integer id,@PathVariable("name")String name){
    	System.out.println(id+"===="+name);
        System.out.println("" +
                "111"
                +"ashfkjasnfioasjfaslfnasnlfjsdffnsaddasfasfasfasdasdasdasd"
        );
        return "show";
    }
    
	
	@RequestMapping("/add")
	public String add(Info2 info2){
		System.out.println("进入到了add");
		System.out.println("111111111111111111111111");
		info2Service.insertInfo2(info2);
		return "redirect:show.AAA";
	}

    @Override
    public String toString() {
        return super.toString();
    }
	
}
