package org.java.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/zwzpath")
public class zwzpathContrller {

//    导航处理类
    @RequestMapping("showpath")
    public String show(String place, HttpSession session){
        session.setAttribute("place",place);
        return "/zwz/path/projectpath";
    }
}
