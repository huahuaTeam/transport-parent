package org.java.controller.hm;

import com.alibaba.fastjson.JSONArray;
import org.apache.logging.log4j.core.util.UuidUtil;
import org.java.controller.hm.util.LatitudeUtils;
import org.java.service.hm.RepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import sun.applet.Main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

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
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("loadRepsitory.do")
    public String loadRepsitory() throws Exception {
        List<Map<String, Object>> list = service.getAllRepository();
        request.setAttribute("replist", list);
        return "/hm/repository";
    }

    /**
     * 查询
     *
     * @param repname
     * @return
     * @throws Exception
     */
    @RequestMapping("search.do")
    public String searchRepsitory(String repname) throws Exception {
        List<Map<String, Object>> list = service.findByName(repname);
        request.setAttribute("replist", list);
        if (!repname.equals("") && repname != null) {
            request.setAttribute("repname", repname);
        }
        return "/hm/repository";
    }


    /**
     * 添加仓库
     *
     * @param map
     * @return
     */
    @RequestMapping("addrepository.do")
    public String addRepository(@RequestParam Map<String, String> map) throws Exception {
        //{rtype=楼房库, address=北京市/北京市/西城区, rarea=199, rname=武汉市, repertoryAddress=北京市北京市西城区}

        String addres = map.get("address"); //得到地址
        Map<String, String> addmap = LatitudeUtils.getGeocoderLatitude(addres);
        String mapId = addmap.get("lng") + "," + addmap.get("lat"); //得到经纬度字符串存入数据库中
        map.put("mapId", mapId); //添加经纬度到map集合中

        //String str = LatitudeUtils.spilt(addres); //得到拆分后的字符串
        map.put("address", addres); //添加到map集合中

        //在session中取到user
        Map<String, Object> user = (Map<String, Object>) session.getAttribute("user");
        String userId = (String) user.get("user_id");
        System.out.println(userId);

        map.put("userId", userId); //存入当前用户到map中
        service.addRepository(map);  //添加到数据库
        return "redirect:/loadRepsitory.do";
    }


    /**
     * 修改仓库的状态
     */
    @RequestMapping("updstatus.do")
    public String updStatus(String id, String status) throws Exception {
        service.updStatus(id, status);
        if(status.equals("1")){
            return "redirect:/loadRepsitory.do";
        }else {
            return "redirect:/enabledRepsitory.do";
        }
    }

    /**
     * 进入修改页面 根据id返回信息
     */
    @RequestMapping("updateRep.do")
    @ResponseBody
    public Map<String, Object> updateRep(String id) throws Exception {
        Map<String, Object> descMap = service.findByidRep(id);
        System.out.println(descMap);
        return descMap;
    }

    /**
     * 修改仓库
     */
    @RequestMapping("updaterepository.do")
    public String updaterRpository(@RequestParam Map<String, String> map) throws Exception {
        String addres = map.get("uaddress"); //得到地址
        Map<String, String> addmap = LatitudeUtils.getGeocoderLatitude(addres);
        String mapId = addmap.get("lng") + "," + addmap.get("lat"); //得到经纬度字符串存入数据库中
        map.put("mid", mapId); //存入map中
        service.updateRepertory(map);
        return "redirect:/loadRepsitory.do";
    }

    //查看仓库
    @RequestMapping("showRep.do")
    @ResponseBody
    public Map<String, Object> showRep(String id) throws Exception {
        Map<String, Object> descMap = service.findByidRep(id);
        System.out.println(descMap);
        return descMap;
    }

    //进入地图页
    @RequestMapping("goshowMap.do")
    public String goshowMap(String id) {
        return "/hm/repositoryMap";
    }


    //加载地图
    @RequestMapping("storeManagementMap.do")
    @ResponseBody
    public Map<String, Object> storeManagementMap() throws Exception {
        System.out.println("****************************");
        Map<String, Object> mp = new HashMap<String, Object>();
        List<Map<String, Object>> ls = service.getAllRepository();

       List<ArrayList<String>> list  = new ArrayList<>(); //用于保存显示的信息和经纬度
        for (Map<String, Object> map : ls) {
            Object mapId = map.get("mapId");
            if(mapId!=null){
                String[] log  = mapId.toString().split(",");
                ArrayList<String> al = new ArrayList<>();
                al.add(log[0]);
                al.add(log[1]);
                al.add(map.get("r_name").toString() + "</br>" + map.get("r_address"));
                list.add(al);
            }
        }
        mp.put("list", list);
        System.out.println(mp);
        return mp;
    }


    /**
     * 禁用启用
     */
    @RequestMapping("enabledRepsitory.do")
    public String enabledRepsitory() throws Exception {
        List<Map<String,Object>> list = service.showEnabledRepsitory();
        request.setAttribute("enlist",list);
        return "/hm/Enrepository";
    }

    //删除仓库
    @RequestMapping("delRep.do")
    public String delRep(String id) throws Exception{
        service.delRep(id);
        return "redirect:/enabledRepsitory.do";
    }


    //進入入庫頁面
    @RequestMapping("goPutaway.do")
    public String goPutaway() throws Exception{
        String businessKey = UuidUtil.getTimeBasedUuid().toString();
        request.setAttribute("Ruuid",businessKey);
        return "/hm/demo";
    }

    //加载所有用户
    @RequestMapping("loadUser.do")
    @ResponseBody
    public  List<Map<String,Object>> loadUser() throws  Exception{
        List<Map<String,Object>> list = service.loadUser();
        return list;
    }

    //加载所有仓库
    @RequestMapping("loadRepositorySet.do")
    @ResponseBody
    public List<Map<String,Object>> loadRepositorySet() throws  Exception{
        List<Map<String,Object>> list = service.loadRepositorySet();
        System.out.println(list);
        return list;
    }


    //得到用户选中过的货物
    @RequestMapping("getHwu.do")
    @ResponseBody
    public List<Map<String,Object>> getHwu(String arrs[])throws Exception{
        System.out.println("***************************进入");
        System.out.println(arrs.length);
        for (int i = 0; i < arrs.length; i++) {
            System.out.println(arrs[i]);
        }
        List<Map<String,Object>> orderlist = service.getOrderByArrs(arrs);
        session.setAttribute("getwuList",orderlist);
        session.setAttribute("orderArrs",arrs);

        return orderlist;
    }

    /**
     * 加载所有可以入库的订单
     * @return
     */
    @RequestMapping("loadOrder.do")
    @ResponseBody
    public List<Map<String,Object>> loadOrder()throws  Exception {
        Integer one = 1;
        Integer two = 5;
        Integer count =service.getCount();
        List<Map<String,Object>> list  = service.getAllOrder(one,two);
        Map<String,Object> pageMap = new HashMap<>();
        pageMap.put("index",one);
        pageMap.put("size",two);
        pageMap.put("count",count);
        session.setAttribute("pageMap",pageMap);
        list.add(0,pageMap);
        return list;
    }

    /*分页*/
    @RequestMapping("change.do")
    @ResponseBody
    public List<Map<String,Object>> change(Integer index)throws  Exception {
        System.out.println("进入分页*************");
        Map<String,Object> pageMap = (Map<String, Object>) session.getAttribute("pageMap");
        pageMap.put("index",index);
        List<Map<String,Object>> list  = service.getAllOrder(index,5);
        session.setAttribute("pageMap",pageMap);
        list.add(0,pageMap);
        return list;
    }


    /**
     * 提交入库单
     * 1,往入库单中添加数据
     * @throws Exception
     */
    @RequestMapping("subRuKu.do")
    public String subRuKu(@RequestParam("mydesc") String mydesc,  @RequestParam("Importance")String Importance,
                          @RequestParam("Importance2") String Importance2,
                          HttpServletRequest request, HttpServletResponse response) throws  Exception{

        List<Map<String,Object>> list = (List<Map<String, Object>>) session.getAttribute("getwuList");
        String[] arrs = (String[]) session.getAttribute("orderArrs");
        String number = UuidUtil.getTimeBasedUuid().toString();
        for (Map<String,Object> map: list) {
            map.put("desc",mydesc); //添加备注的内容
            map.put("userId",Importance); //推送的用户id
            map.put("repId",Importance2); //仓库id
            map.put("number",number);
            //添加入库信息
            System.out.println(map);
            service.addInputRep(map);
        }
        System.out.println("进入请求**********************************");
        //修改之前订单表中的状态
        System.out.println(arrs[0]);
        service.updateOrderStatus(arrs);
        //清空session中的元素
        session.removeAttribute("getwuList");
        session.removeAttribute("orderArrs");
        return "redirect:/goassembleRep.do";
    }

    //进入入库装卸页面

    @RequestMapping("goassembleRep.do")
    public String goassembleRep() throws Exception{
        //在session中取到user
        Map<String, Object> user = (Map<String, Object>) session.getAttribute("user");
        String userId = (String) user.get("user_id");
        List<Map<String,Object>> assList = service.loadAssembleRep(userId);
        request.setAttribute("assList",assList);
        return "/hm/assembleRep";
    }


    //进入处理入库单详情页面
    @RequestMapping("goRepEdi.do")
    public String goRepEdi(String id) throws  Exception{
        Map<String,Object> assMap = service.findByIdCargo(id);
        System.out.println(assMap);
        request.setAttribute("assMap",assMap);
        return "/hm/assembleEdi";
    }


    //添加到仓库物品表中    （处理入库单）
    @RequestMapping("addAssRep.do")
    public String addAssRep(@RequestParam Map<String,String> map) throws  Exception{

        System.out.println(map);
        //新增
        service.addRepList(map);
        //修改原先入库单的状态 为1
        String id = map.get("order_id");
        service.updInputRepStatus("1",id);
        return "redirect:/goassembleRep.do";
    }


    //进入货物摆放页面
    @RequestMapping("getPlaceGood.do")
    @ResponseBody
    public String getPlaceGood() throws Exception{
        List<Map<String,Object>> inRepList = service.getInpRepBai(); //得到所有的入库单编号和需要处理的数量
        List<Map<String,Object>> inRepHwuList = service.getRepBaiHw(); //得到所有入库单下面需要处理的国务
        inRepList.addAll(inRepHwuList);
        String str = "{\"rows\":"+ JSONArray.toJSONString(inRepList)+"}";
        return str;
    }

    //加载ztree插件
    @RequestMapping("loadRepBai.do")
    @ResponseBody
    public String loadRepBai() throws  Exception{
        List<Map<String,Object>> list1 = service.loadResp();
        List<Map<String,Object>> list2 = service.loadregion();
        List<Map<String,Object>> list3 = service.loadposition();
        list1.addAll(list2);
        list1.addAll(list3);
        String str =  JSONArray.toJSONString(list1);
        return str;
    }


    //进入货物摆放页面
    @RequestMapping("goPlaceGood.do")
    public String goPlaceGood() throws Exception{
        return "/hm/PlaceGood";
    }

    //用于保存入库传入的id


    @RequestMapping("savelistId.do")
    public void saveListId(String listId) {
        System.out.println(listId);
        session.setAttribute("listId",listId);
    }

    //入库摆货
    @RequestMapping("PutinStorage.do")
    @ResponseBody
    public String PutinStorage(String id) throws  Exception {
        System.out.println(id);
        String listId = session.getAttribute("listId").toString();
        service.PutinStorage(listId,id); //入库
        session.removeAttribute("listId");
        return "1";
    }

    /*进入入库确认页面*/
    @RequestMapping("goRepConfirm.do")
    public String goRepConfirm() throws Exception{
        System.out.println("**********************************进入");
        List<Map<String,Object>> list = service.loadRepConfirm(); //得到所有可以确认入库的入库单
        System.out.println(list);
        request.setAttribute("confList",list);
        return "/hm/repConfirm";
    }

    /*确认入库*/
    @RequestMapping("okRepHwu.do")
    public String okRepHwu(String id) throws Exception{
        service.okRepHw(id);
        return "redirect:/goRepConfirm.do";
    }




    /************************************************出库********************************************************/
    /*查询出所有可以出库的货物*/
    @RequestMapping("goOutRespHwu.do")
    public String goOutRespHwu() throws  Exception{
        List<Map<String,Object>> outRhList = service.getOutRespHwu(); //得到所有可以出库的货物
        request.setAttribute("outRhList",outRhList);
        return "/hm/OutRespHwu";
    }

    /*保存需要出库的货物的id*/
    @RequestMapping("saveOutListId.do")
    @ResponseBody
    public String saveOutListId(String[] arrs)throws Exception{
        System.out.println("进入**********************");
        System.out.println(arrs.length);
        for (int i = 0; i < arrs.length; i++) {
            System.out.println(arrs[i]);
        }
        session.setAttribute("outListIdarrs",arrs);
        return "";
    }

    /*进入创建货物单页面*/
    @RequestMapping("goa.do")
    public String goA() throws  Exception{

        //在session中取到user
        Map<String, Object> user = (Map<String, Object>) session.getAttribute("user");
        String userId = (String) user.get("user_id");

        //取得之前存入session中的listId数组
        String[] arrs = (String[]) session.getAttribute("outListIdarrs");
        System.out.println(arrs.length);
        if(arrs!=null){
            List<Map<String,Object>> carGoDesclist = service.getCargoDesc(arrs);
            request.setAttribute("carGoDesclist",carGoDesclist);
        }
        //日期
        request.setAttribute("outRepTime",new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        //用户
        request.setAttribute("outUser",user.get("user_name").toString());

        String uuid = UuidUtil.getTimeBasedUuid().toString();
        System.out.println("生成的uuid:"+uuid);
        String number = uuid.substring(0,8).toUpperCase();
        System.out.println(number);
        //出库单号
        request.setAttribute("outNumber",number);
        request.setAttribute("outRelNumber",uuid);

        return "/hm/a";
    }

    /*绑定下拉框     加载所有车辆*/
    @RequestMapping("loadCards.do")
    @ResponseBody
    public List<Map<String,Object>> loadCars() throws  Exception{
        List<Map<String,Object>> list = service.loadCars();
        System.out.println(list);
        return list;
    }


    /*添加出库单*/
    @RequestMapping("subOutGarGo.do")
    public String subOutGarGo(@RequestParam("outRelNumber") String relNumber,@RequestParam("Importance2") String carId) throws Exception{

        System.out.println("***********************************进入!!!!!!!!!!!!!!!");

        //在session中取到user
        Map<String, Object> user = (Map<String, Object>) session.getAttribute("user");
        String userId = (String) user.get("user_id");

        //取得之前存入session中的listId数组
        String[] arrs = (String[]) session.getAttribute("outListIdarrs");

        String[] relcount = new String[arrs.length]; //该数组用于保存页面中取得的真实出货数量
        String[] typeId = new String[arrs.length];  //改数组用于保存货物类型id
        String[] widths=  new String[arrs.length]; //改数组用于保存货物的重量
        for (int i = 0; i < arrs.length; i++) {
            relcount[i] =request.getParameter("relcount"+i);
            typeId[i] =request.getParameter("typeId"+i);
            widths[i] =request.getParameter("width"+i);
        }

        List<Map<String,String>> updList = new ArrayList<>();

        //添加 -----------------------------------------------------
        for (int i = 0; i < arrs.length; i++) {
            Map<String,String> map = new HashMap<>();
            map.put("listId",arrs[i]); //   仓库订单id
            map.put("relCount",relcount[i]);    //真实出库数量
            map.put("typeId",typeId[i]); //货物类型id
            map.put("width",widths[i]); //货物重量
            map.put("outRelNumber",relNumber);  //真实出库编号
            map.put("userId",userId); //出库负责人
            map.put("carId",carId); //出库车辆编号
            System.out.println(map);
            //添加导出库表中
            service.addOutRep(map);
            updList.add(map);
        }

        //修改出库的数量---------------------
        for (Map<String,String> map: updList) {
                Integer count =Integer.parseInt(map.get("relCount"));
                String listId = map.get("listId");
                service.updateRepListCount(count,listId);
        }
        //session.removeAttribute("outListIdarrs");
        return "redirect:/goOutRespHwu.do";
    }


    //进入出库装卸页面
    @RequestMapping("gorepAssemble.do")
    public String gorepAssemble() throws Exception{
        List<Map<String,Object>> assList = service.showRepAssemble(); //得到所有可以出库装卸的出库单
        request.setAttribute("assList",assList);
        return "/hm/repAssemble";
    }


    //进入添加装卸单页面
    @RequestMapping("findAssId.do")
    public String findAssId(String id) throws Exception{
        Map<String,Object> outdescMap = service.getOutNumberDesc(id);
        List<Map<String,Object>> CarIdlist = service.findCatIdbyNumberId(id);
        String carId = CarIdlist.get(0).get("car_id").toString();
        outdescMap.put("outNumberId",id);
        outdescMap.put("car_id",carId);
        request.setAttribute("outdescMap",outdescMap);
        return "/hm/addAssRep";
    }

    //添加入库装卸单
    @RequestMapping("subAssFrm.do")
    public  String subAssFrm(@RequestParam Map<String,String> map) throws  Exception{

        //在session中取到user
        Map<String, Object> user = (Map<String, Object>) session.getAttribute("user");
        String userId = (String) user.get("user_id");
        map.put("userId",userId);
        System.out.println(map);

        service.addAssRep(map); //添加出库装卸单

        //修改出库单状态
        String outNumberId = map.get("outNumberId");
        service.updOutRespStatus(outNumberId,1);

        //往车辆配送表中新增一条数据,转交给下一流程
        service.addCatFreight(map);

        //修改车辆表中的状态
        String carId = map.get("carId");
        service.updateCarStatus(carId);

        return "redirect:/gorepAssemble.do";
    }


    //进入出库确认页面
    @RequestMapping("goOutRepConf.do")
    public String goOutRepConf() throws  Exception{
        List<Map<String,Object>> comfList = service.showOutRepConf(); //得到所有可以出库确认的出库单号
        request.setAttribute("comfList",comfList);
        return "/hm/outRepConf";
    }

    //出库确认
    @RequestMapping("comfRep.do")
    public String comfRep(String id) throws Exception{

        //修改状态位为出库
        service.updOutRespStatus(id,2);

        //清空仓库中数量为0的仓位
        service.claerCount();

        return "redirect:/goOutRepConf.do";
    }


    /*查看入库单*/
    @RequestMapping("goShowInputRep.do")
    public String goShowInputRep(String id) throws Exception{

        List<Map<String,Object>> inpuDescList = service.showInputDesc(id);
        String inputNumber = inpuDescList.get(0).get("innumber").toString();
        String inputTime = inpuDescList.get(0).get("input_date").toString();
        request.setAttribute("inpuDescList",inpuDescList);
        request.setAttribute("inputNumber",inputNumber);
        request.setAttribute("inputTime",inputTime);

        return "/hm/showInputDesc";
    }


    /*库存查询*/
    @RequestMapping("showInventory.do")
    public String showInventory() throws Exception{
        List<Map<String,Object>> invlist = service.showInventory();
        request.setAttribute("invlist",invlist);
        return "/hm/selectRepHwu";
    }


    /*更换仓位  */
    @RequestMapping("updateRepCw.do")
    @ResponseBody
    public String updateRepCw(String id) throws Exception{
        String listId = session.getAttribute("listId").toString();
        service.updateRepPositionId(id,listId);
        session.removeAttribute("listId");
        return "1";
    }


    /*添加仓域*/
    @RequestMapping("addRepYu.do")
    public String addRepYu(@RequestParam Map<String,String> map) throws  Exception{
        System.out.println(map);
        service.addRepyu(map);
        return "redirect:/loadRepsitory.do";
    }

    /*打开添加仓位页面读取所有仓域*/
    @RequestMapping("addRepWei.do")
    @ResponseBody
    public List<Map<String,Object>> addRepWei(String id) throws  Exception{
        List<Map<String,Object>> yuList = service.getRepYuList(id);
        return yuList;
    }

    /*添加仓位*/
    @RequestMapping("subRepWei.do")
    public String subRepWei(@RequestParam Map<String,String> map) throws Exception{
        System.out.println(map);
        service.addRepWei(map);
        return "redirect:/loadRepsitory.do";
    }

}
