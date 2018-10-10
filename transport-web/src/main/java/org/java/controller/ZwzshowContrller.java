package org.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.java.service.zwzshowService;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/zwzshow")
public class ZwzshowContrller {

    @Autowired
    zwzshowService service;

//    查询数据类

//    采购单申请查询
    @RequestMapping("/forpurchase")
    public String forpurchase(HttpSession session){
        List<Map<String,Object>> list=service.selectforpurchase();
        session.setAttribute("forpurchaselist",list);
        return "/zwz/show/forpurchase";
    }
    //    采购订单查询
    @RequestMapping("/purchaseorder")
    public String purchaseorder(HttpSession session){
        List<Map<String,Object>> list=service.selectpurchaseorder();
        session.setAttribute("purchaseorderlist",list);
        return "/zwz/show/purchaseorder";
    }

    //    入库作业单查询
    @RequestMapping("/enterstorage")
    public String enterstorage(HttpSession session){

        List<Map<String,Object>> list=service.selectenterstorage();
        session.setAttribute("enterstoragelist",list);
        return "/zwz/show/enterstorage";
    }
    //    出库作业单查询
    @RequestMapping("/outstorage")
    public String outstorage(HttpSession session){

        List<Map<String,Object>> list=service.selectoutstorage();
        session.setAttribute("outstoragelist",list);
        return "/zwz/show/outstorage";
    }
    //    配送任务单查询
    @RequestMapping("/dispatching")
    public String dispatching(HttpSession session){

        List<Map<String,Object>> list=service.selectdispatching();
        session.setAttribute("dispatchinglist",list);
        return "/zwz/show/dispatching";
    }
    //    客户订单查询
    @RequestMapping("/clientorder")
    public String clientorder(Model model)
    {
        List<Map<String,Object>> list=service.selectclientorder();
        System.out.println(list);
        model.addAttribute("list",list);

        return "/zwz/show/clientorder";
    }

}
