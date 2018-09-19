package org.java.controller.xu;

import org.java.mapper.xu.TestMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("client")
public class ClientController{

    @RequestMapping("/")
    public String client(){
        return "/xu/client";
    }

    @RequestMapping("add")
    public String add(){
        System.out.println("");
        return "/xu/addClient";
    }

    @RequestMapping("addClient")
    public String addClient(Map<String ,String> client){

        return "/xu/addClient";
    }


    @Override
    public String toString() {
        return super.toString();
    }
}
