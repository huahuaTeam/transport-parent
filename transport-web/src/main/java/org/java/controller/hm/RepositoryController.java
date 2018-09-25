package org.java.controller.hm;

import org.java.controller.hm.util.LatitudeUtils;
import org.java.service.hm.RepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class RepositoryController {
    @Autowired
    private RepositoryService service;

    //注入请求
    @Autowired
    private HttpServletRequest request;

    //注入session
    @Autowired
    private HttpSession session;

    /**
     * 加载所有仓库
     * @return
     * @throws Exception
     */
    @RequestMapping("loadRepsitory.do")
    public String loadRepsitory() throws  Exception{
        List<Map<String,Object>> list = service.getAllRepository();
        request.setAttribute("replist",list);
        return "/hm/repository";
    }

    /**
     * 查询
     * @param repname
     * @return
     * @throws Exception
     */
    @RequestMapping("search.do")
    public String searchRepsitory(String repname) throws  Exception{
        List<Map<String,Object>> list = service.findByName(repname);
        request.setAttribute("replist",list);
        if(!repname.equals("") && repname!=null){
            request.setAttribute("repname",repname);
        }
        return "/hm/repository";
    }


    /**
     * 添加c'g
     * @param map
     * @return
     */
    @RequestMapping("addrepository.do")
    public String addRepository(@RequestParam Map<String,String> map) throws  Exception{
        //{rtype=楼房库, address=北京市/北京市/西城区, rarea=199, rname=武汉市, repertoryAddress=北京市北京市西城区}

        String addres =  map.get("address"); //得到地址
        Map<String,String> addmap =LatitudeUtils.getGeocoderLatitude(addres);
        String mapId =addmap.get("lng")+","+addmap.get("lat"); //得到经纬度字符串存入数据库中
        map.put("mapId",mapId); //添加经纬度到map集合中

        String str = LatitudeUtils.spilt(addres); //得到拆分后的字符串
        map.put("address",str); //添加到map集合中

        //在session中取到user
       // Map<String,Object> user = (Map<String, Object>) session.getAttribute("user");
        //String userId = (String) user.get("user_id");
        //System.out.println(userId);

         map.put("userId","19d43d0e-bca4-11e8-905c-52540074defd"); //存入当前用户到map中
        service.addRepository(map);  //添加到数据库
         return "redirect:/loadRepsitory.do";
    }


    /**
     * 修改仓库的状态
     */
    @RequestMapping("updstatus.do")
    public String updStatus(String id,String status) throws  Exception{
        service.updStatus(id,status);
        return "redirect:/loadRepsitory.do";
    }

    /**
     *  进入修改页面 根据id返回信息
     */
    @RequestMapping("updateRep.do")
    @ResponseBody
    public Map<String,Object> updateRep(String id) throws  Exception{
        Map<String,Object> descMap = service.findByidRep(id);
        System.out.println(descMap);
        return descMap;
    }



}
