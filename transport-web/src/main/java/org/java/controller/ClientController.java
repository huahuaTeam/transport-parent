package org.java.controller;

import org.java.mapper.xu.TestMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("client")
public class ClientController{

    @RequestMapping("/")
    public String client(){
        return "/xu/client";
    }


    @Override
    public String toString() {
        return super.toString();
    }
}
