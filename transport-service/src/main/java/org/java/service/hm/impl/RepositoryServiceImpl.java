package org.java.service.hm.impl;

import org.java.mapper.hm.RepositoryMapper;
import org.java.service.hm.RepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RepositoryServiceImpl implements RepositoryService{
    @Autowired
    private RepositoryMapper mapper;

    @Override
    public List<Map<String, Object>> getAllRepository() throws Exception {
        return mapper.getAllResponse();
    }

    @Override
    public List<Map<String, Object>> findByName(String name) throws Exception {
        return mapper.findByName(name);
    }

    @Override
    public void addRepository(Map<String, String> map) throws Exception {
        mapper.addRepository(map);
    }

    @Override
    public void updStatus(String id, String status) throws Exception {
        mapper.updStatus(id,status);
    }

    @Override
    public Map<String, Object> findByidRep(String rid) throws Exception {
        return mapper.findByidRep(rid);
    }

    @Override
    public void updateRepertory(Map<String, String> map) throws Exception {
        mapper.updateRepertory(map);
    }

    @Override
    public List<Map<String, Object>> showEnabledRepsitory() throws Exception {
        return mapper.showEnabledRepsitory();
    }

    @Override
    public void delRep(String id) throws Exception {
        mapper.delRep(id);
    }

    @Override
    public List<Map<String, Object>> loadUser() throws Exception {
        List<Map<String,Object>> userList = mapper.loadUser(); //得到原先user集合
        List<Map<String,Object>> list = new ArrayList<>();
        //转换成select2需要的格式数据
        for (Map<String,Object> m: userList) {
            Map<String,Object> map = new HashMap<>();
            map.put("id",m.get("user_id"));
            map.put("text",m.get("user_name"));
            list.add(map);
        }
        return list;
    }

    @Override
    public List<Map<String, Object>> loadRepositorySet() throws Exception {
        List<Map<String,Object>> userList = mapper.loadRepositorySet(); //得到原先user集合
        List<Map<String,Object>> list = new ArrayList<>();
        //转换成select2需要的格式数据
        for (Map<String,Object> m: userList) {
            Map<String,Object> map = new HashMap<>();
            map.put("id",m.get("r_id"));
            map.put("text",m.get("r_name"));
            list.add(map);
        }
        return list;
    }

    @Override
    public List<Map<String, Object>> loadCars() throws Exception {
        List<Map<String,Object>> userList = mapper.loadCars(); //得到原先user集合
        List<Map<String,Object>> list = new ArrayList<>();
        //转换成select2需要的格式数据
        for (Map<String,Object> m: userList) {
            Map<String,Object> map = new HashMap<>();
            map.put("id",m.get("Tra_car_id"));
            map.put("text",m.get("Tra_car_conding"));
            list.add(map);
        }
        return list;
    }

    public List<Map<String, Object>> loadAssembleRep(String userId) throws Exception {
        //用于保存该用你能够查询到的数据
        List<Map<String,Object>> assList = new ArrayList<>();
        List<Map<String,Object>> list = mapper.loadAssembleRep();
        for (Map<String, Object> map : list) {
            if(map.get("user_id").toString().indexOf(userId)!=-1){
                System.out.println("ok");
                assList.add(map);
            }else{
                System.out.println("no");
            }
        }
        return assList;
    }

   //根据id返回单个货物信息
    public Map<String, Object> findByIdCargo(String id) throws Exception {
        return mapper.findByIdCargo(id);
    }

    @Override
    public void addRepList(Map<String, String> m) throws Exception {
        mapper.addRepList(m);
    }

    @Override
    public void updInputRepStatus(String statusId, String id)  throws  Exception{
        mapper.updInputRepStatus(statusId,id);
    }

    @Override
    public List<Map<String, Object>> getAllOrder(Integer one,Integer two) throws Exception {
        Integer a = (one-1)*two;
        return mapper.getAllOrder(a,two);
    }

    @Override
    public int getCount() throws Exception {
        return mapper.getCount();
    }

    @Override
    public List<Map<String, Object>> getOrderByArrs(String[] arrs) throws Exception {
        return mapper.getOrderByArrs(arrs);
    }

    @Override
    public void addInputRep(Map<String, Object> map) throws Exception {
        mapper.addInputRep(map);
    }


    /**
     *  <th data-options="field:'name',width:180">货物名称</th>
        <th data-options="field:'reptype',width:60,align:'right'">货物类型</th>
        <th data-options="field:'repcount',width:80">货物数量</th>
        <th data-options="field:'status',width:80">状态</th>
        <th data-options="field:'cz',width:80">操作</th>
     * @return
     * @throws Exception
     */
    @Override
    public List<Map<String, Object>> getInpRepBai() throws Exception {
        List<Map<String,Object>> list = mapper.getInpRepBai();
        List<Map<String,Object>> newList = new ArrayList<>();
        for (Map<String,Object> m :list) {
            Map<String,Object> map = new HashMap<>();
            if(Integer.parseInt(m.get("number").toString())!=0){
                map.put("id",m.get("input_number").toString());
                map.put("name","<span style=\"color: red;font-weight: bold\">"+m.get("innumber").toString()+"</span>,<span style=\"color: grey;font-size: 12px\">代处理货物<span style=\"color: limegreen;font-weight: bold\">"+m.get("number")+"</span>件</span>");
                newList.add(map);
            }
        }
        return newList;
    }

    @Override
    public List<Map<String, Object>> getRepBaiHw() throws Exception {
        List<Map<String,Object>> list = mapper.getRepBaiHw();
        List<Map<String,Object>> newList = new ArrayList<>();
        for (Map<String,Object> m :list) {
            Map<String,Object> map = new HashMap<>();
            map.put("name",m.get("list_name").toString());
            map.put("reptype",m.get("type_name").toString());
            map.put("repcount",m.get("list_count").toString());
            map.put("status","<span style=\"color: #0ec228\">入库中</span>");
            map.put("id",m.get("list_id").toString());
            map.put("_parentId",m.get("inp_number").toString());
            map.put("state","closed");
            map.put("cz","<a href=\"#\" style=\"font-size: 14px;font-weight: bold\"  onclick=\"openDiv('"+m.get("list_id").toString()+"')\">入库</a>");
            newList.add(map);
        }
        return newList;
    }

    //加载所有的仓域
    @Override
    public List<Map<String, Object>> loadregion() throws Exception {
        List<Map<String,Object>> newList = new ArrayList<>();
        List<Map<String,Object>> list =  mapper.loadregion();
        for (Map<String,Object> m :list) {
            Map<String,Object> map = new HashMap<>();
            map.put("name",m.get("region_name").toString());
            map.put("id",m.get("region_id").toString());
            map.put("pid",m.get("r_id").toString());
            newList.add(map);
        }
        return newList;
    }

    /*加载所有仓位*/
    @Override
    public List<Map<String, Object>> loadposition() throws Exception {
        List<Map<String,Object>> newList = new ArrayList<>();
        List<Map<String,Object>> list =  mapper.loadposition();
        for (Map<String,Object> m :list) {
            Map<String,Object> map = new HashMap<>();
            map.put("name",m.get("position_name").toString());
            map.put("id",m.get("position_id").toString());
            map.put("pid",m.get("region_id").toString());
            newList.add(map);
        }
        return newList;
    }

    /*加载所有仓库*/
    @Override
    public List<Map<String, Object>> loadResp() throws Exception {
        List<Map<String,Object>> newList = new ArrayList<>();
        List<Map<String,Object>> list =  mapper.loadRepositorySet();
        for (Map<String,Object> m :list) {
            if(Integer.parseInt(m.get("status").toString())==0){
                Map<String,Object> map = new HashMap<>();
                map.put("name",m.get("r_name").toString());
                map.put("id",m.get("r_id").toString());
                map.put("pid","0");
                newList.add(map);
            }
        }
        return newList;
    }

    /*入库*/

    @Override
    public void PutinStorage(String listId, String positonId) throws Exception {
        mapper.PutinStorage(listId,positonId);
    }

    @Override
    public List<Map<String, Object>> loadRepConfirm() throws Exception {
         List<Map<String,Object>> newlist = new ArrayList<>();
         List<Map<String,Object>> list = mapper.loadRepConfirm();
        for (Map<String,Object> m: list) {
            if(Integer.parseInt(m.get("number").toString())==0){
                newlist.add(m);
            }
        }
         return newlist;
    }


    @Override
    public void updateOrderStatus(String[] arrs) throws Exception {
        mapper.updateOrderStatus(arrs);
    }

    @Override
    public void okRepHw(String id) throws Exception {
        mapper.okRepHw(id);
    }



    /*出库*******************************************/

    @Override
    public List<Map<String, Object>> getOutRespHwu() throws Exception {
        return mapper.getOutRespHwu();
    }

    @Override
    public List<Map<String, Object>> getCargoDesc(String[] arrs) throws Exception {
        return mapper.getCargoDesc(arrs);
    }

    @Override
    public void addOutRep(Map<String, String> map) throws Exception {
        mapper.addOutRep(map);
    }

    @Override
    public void updateRepListCount(Integer count, String listId) throws Exception {
        mapper.updateRepListCount(count,listId);
    }

    @Override
    public List<Map<String, Object>> showRepAssemble() throws Exception {
        return mapper.showRepAssemble();
    }

    @Override
    public Map<String, Object> getOutNumberDesc(String outNumber) throws Exception {
        return mapper.getOutNumberDesc(outNumber);
    }

    @Override
    public void addAssRep(Map<String, String> map) throws Exception {
        mapper.addAssRep(map);
    }

    @Override
    public void updOutRespStatus(String number,Integer status) {
        mapper.updOutRespStatus(number,status);
    }

    @Override
    public List<Map<String, Object>> showOutRepConf() throws Exception {
        return mapper.showOutRepConf();
    }

    @Override
    public void claerCount() throws Exception {
        mapper.claerCount();
    }

    @Override
    public void addCatFreight(Map<String, String> map) throws Exception {
        mapper.addCatFreight(map);
    }

    @Override
    public  List<Map<String,Object>>  findCatIdbyNumberId(String outNumber) throws Exception {
        return mapper.findCatIdbyNumberId(outNumber);
    }

    @Override
    public List<Map<String, Object>> showInputDesc(String number) throws Exception {
        return mapper.showInputDesc(number);
    }

    @Override
    public void updateCarStatus(String carId) throws Exception {
        mapper.updateCarStatus(carId);
    }

    @Override
    public List<Map<String, Object>> showInventory() throws Exception {
        return mapper.showInventory();
    }

    @Override
    public void updateRepPositionId(String positionId, String listId) throws Exception {
        mapper.updateRepPositionId(positionId,listId);
    }

    @Override
    public void addRepyu(Map<String, String> map) throws Exception {
        mapper.addRepyu(map);
    }

    @Override
    public List<Map<String, Object>> getRepYuList(String rid) throws Exception {
        return mapper.getRepYuList(rid);
    }

    @Override
    public void addRepWei(Map<String, String> map) throws Exception {
        mapper.addRepWei(map);
    }
}
