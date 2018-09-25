package org.java.controller.xu;

import org.java.entity.TranClient;
import org.java.mapper.xu.TestMapper;
import org.java.service.xu.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("client")
public class ClientController{

    @Autowired
    private ClientService service;

    @RequestMapping("/")
    public String client(Model model){
        List<Map<String, Object>> list = service.selAllClient();
        System.out.println(list);
        model.addAttribute("list",list);
        return "/xu/client";
    }

    @RequestMapping("add")
    public String add(){
        System.out.println("");
        return "/xu/addClient";
    }

    @RequestMapping("/addClient")
    public String addClient(TranClient client){
        client.setClientId(UUID.randomUUID().toString());
        System.out.println(client);
        service.addClient(client);
        return "redirect:/client/";
    }

    @RequestMapping("/addClientOrder")
    public String addClientOrder(){
        return "/xu/ClientOrder";
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
