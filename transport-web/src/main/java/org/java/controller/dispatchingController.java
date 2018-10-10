package org.java.controller;


import org.java.service.dispatchingService;
import org.java.service.impl.HttpClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/dispatching")
public class dispatchingController {

    @Autowired
    dispatchingService dservice;

    private static String Uid = "zwz2991722289";
    private static String Key = "d41d8cd98f00b204e980";
    public  String smsMob = "";
    private static String smsText = "您的快件因无人签收，多次联系无效。次日配送！超过三次即可视为拒收。";

//    配送处理类



        //处理类
        public  void cl(){
            List<Map<String,Object>> bidlist=dservice.selectbid();

            for (Map<String,Object> z:bidlist ) {
               String id= z.get("bid").toString();

                List<Map<String,Object>> user=dservice.seleteuser(id);
                List<Map<String,Object>> order=dservice.selectorder(id);

                int i=user.size()-1;

                for (int zz=0;zz<order.size();zz++){
                  String oid=  order.get(zz).get("oid").toString();
                  String uid=  user.get(i).get("uid").toString();
                    System.out.println("oid"+oid);
                    System.out.println("uid"+uid);
                    dservice.addgoods(uid,oid);
                    i=i--;
                    if(i==-1){
                        i=user.size()-1;
                    }
                }
            }
        }


    //  发短信类
    public void cs(){
        HttpClientUtil client = HttpClientUtil.getInstance();
        //UTF发送
        int result = client.sendMsgUtf8(Uid, Key, smsText, smsMob);
        if(result>0){
            System.out.println("UTF8成功发送条数=="+result);
        }else{
            System.out.println(client.getErrorMsg(result));
        }
    }




    //    显示无人签收订单配送详情页面
    @RequestMapping("/deliveryno")
    public String showdeliveryno(HttpSession session,String id,Model model){
        List<Map<String,Object>> list=dservice.getorderparticularsno(id);
        model.addAttribute("orderparticulars",list);
        session.setAttribute("deliveryid",list.get(0).get("id").toString());
        session.setAttribute("tel",list.get(0).get("tel").toString());
        return "/zwz/deliveryno";
    }

//    显示无人签收订单
    @RequestMapping("/showno")
    public String showno(HttpSession session, Model model){
        Map<String,Object> user=(Map<String,Object> )session.getAttribute("user");
        List<Map<String,Object>> list=dservice.getnoorder(user.get("user_id").toString());
        model.addAttribute("dispatchinglist",list);
        return "/zwz/shownodispatching";

    }

//    修改单个订单状态
    @RequestMapping("/updatedelivery")
    public String updatedelivery(HttpSession session,HttpServletRequest request){

        String type=request.getParameter("delivertype");
        String id=(String) session.getAttribute("deliveryid");

        Integer z=Integer.parseInt(type);
        if(z==4){
            dservice.updatedelivery(type,id);
            String tel=(String) session.getAttribute("tel");
            smsMob=tel;
//            cs();
        }else if(z<3){
            dservice.updatedeliveryss(type,id);
            dservice.updateorder("1",id);
        }else if(z==5){
            dservice.delegoods(id);
            dservice.updateorder("3",id);
        }
        return "redirect:/dispatching/show";
    }

//    显示配送详情页面
    @RequestMapping("/delivery")
    public String showdelivery(HttpSession session,String id,Model model){
        List<Map<String,Object>> list=dservice.getorderparticulars(id);
        model.addAttribute("orderparticulars",list);
        session.setAttribute("deliveryid",list.get(0).get("id").toString());
        return "/zwz/delivery";
    }


//    显示待配送页面
    @RequestMapping("/show")
    public String showdispatching(HttpSession session, Model model){
        Map<String,Object> user=(Map<String,Object> )session.getAttribute("user");
        List<Map<String,Object>> list=dservice.getorder(user.get("user_id").toString());
          model.addAttribute("dispatchinglist",list);
//        session.setAttribute("dispatchinglist",list);
        return "/zwz/showdispatching";

    }

}
