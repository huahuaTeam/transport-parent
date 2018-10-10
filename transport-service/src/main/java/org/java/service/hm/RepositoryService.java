package org.java.service.hm;


import java.util.List;
import java.util.Map;

public interface RepositoryService {


    List<Map<String,Object>> getAllRepository() throws  Exception;

    List<Map<String,Object>> findByName(String name)throws  Exception;

    void addRepository(Map<String,String> map) throws Exception;

    void updStatus(String id,String status) throws  Exception;

    Map<String,Object> findByidRep(String rid) throws  Exception;

    void updateRepertory(Map<String,String> map) throws  Exception;

    List<Map<String,Object>> showEnabledRepsitory() throws  Exception;

    void delRep(String id) throws Exception;

    List<Map<String,Object>> loadUser()throws  Exception;

    List<Map<String,Object>> loadRepositorySet()throws  Exception;

    List<Map<String,Object>> loadAssembleRep(String userId) throws Exception;

    Map<String,Object> findByIdCargo(String id) throws  Exception;

    void addRepList(Map<String,String> m) throws Exception;

    void updInputRepStatus(String statusId,String id) throws  Exception;

    List<Map<String,Object>> getAllOrder(Integer one,Integer two) throws Exception;

    int getCount() throws  Exception;

    List<Map<String,Object>> getOrderByArrs(String arrs[]) throws Exception;

    void addInputRep(Map<String,Object> map) throws Exception;

    List<Map<String,Object>> getInpRepBai() throws Exception;

    List<Map<String,Object>> getRepBaiHw() throws Exception;

    List<Map<String,Object>> loadregion() throws Exception;

    List<Map<String,Object>> loadposition() throws  Exception;

    List<Map<String,Object>> loadResp() throws  Exception;

    void PutinStorage(String listId,String positonId) throws Exception ;

    List<Map<String,Object>>  loadRepConfirm() throws Exception;

    void updateOrderStatus(String arrs[]) throws  Exception;

    void okRepHw(String id) throws  Exception;


    /*出库*********************************************************************/
    List<Map<String,Object>> getOutRespHwu() throws  Exception;

    List<Map<String,Object>> getCargoDesc( String[] arrs) throws  Exception;

    List<Map<String,Object>> loadCars() throws  Exception;

    void addOutRep(Map<String,String> map) throws Exception;

    void updateRepListCount(Integer count,String listId)throws  Exception;

    List<Map<String,Object>> showRepAssemble()throws  Exception;

    Map<String,Object> getOutNumberDesc(String outNumber) throws  Exception;

    void addAssRep(Map<String,String> map) throws Exception;

    void updOutRespStatus(String number,Integer status);

    List<Map<String,Object>> showOutRepConf() throws Exception;

    void claerCount() throws  Exception;

    void addCatFreight(Map<String,String> map) throws Exception;

    List<Map<String,Object>>  findCatIdbyNumberId(String outNumber) throws  Exception;

    List<Map<String,Object>> showInputDesc(String number) throws Exception;

    void updateCarStatus(String carId) throws Exception;

    List<Map<String,Object>> showInventory() throws Exception;

    void updateRepPositionId(String positionId,String listId) throws  Exception;

    void addRepyu(Map<String,String> map) throws Exception;

    List<Map<String,Object>> getRepYuList(String rid) throws Exception;

    void addRepWei(Map<String,String> map) throws Exception;
}
