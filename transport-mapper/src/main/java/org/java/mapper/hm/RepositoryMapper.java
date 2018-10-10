package org.java.mapper.hm;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface RepositoryMapper {

    List<Map<String,Object>> getAllResponse()throws  Exception;

    List<Map<String,Object>> findByName(@Param("rname") String name)throws  Exception;

    void addRepository(Map<String,String> map) throws Exception;

    void updStatus(@Param("rid") String id,@Param("status") String status) throws  Exception;

    Map<String,Object> findByidRep(@Param("rid") String rid) throws  Exception;

    void updateRepertory(Map<String,String> map) throws  Exception;

    List<Map<String,Object>> showEnabledRepsitory() throws  Exception;

    void delRep(@Param("rid") String id) throws Exception;

    List<Map<String,Object>> loadUser()throws  Exception;

    List<Map<String,Object>> loadRepositorySet()throws  Exception;

    List<Map<String,Object>> loadAssembleRep() throws Exception;

    Map<String,Object> findByIdCargo(@Param("id") String id) throws  Exception;

    void addRepList(Map<String,String> map) throws Exception;

    void updInputRepStatus(@Param("status")String statusId, @Param("id") String id) throws  Exception;

    List<Map<String,Object>> getAllOrder(@Param("one") Integer one,@Param("two") Integer two) throws Exception;

    int getCount() throws  Exception;

    List<Map<String,Object>> getOrderByArrs(@Param(value="arrs")String arrs[]) throws Exception;

    void addInputRep(Map<String,Object> map) throws Exception;

    List<Map<String,Object>> getInpRepBai() throws Exception;

    List<Map<String,Object>> getRepBaiHw() throws Exception;

    List<Map<String,Object>> loadregion() throws Exception;

    List<Map<String,Object>> loadposition() throws  Exception;

    void PutinStorage(@Param("listId") String listId,@Param("positonId") String positonId) throws Exception;

    List<Map<String,Object>>  loadRepConfirm() throws Exception;

    void updateOrderStatus(@Param(value="arrs")String arrs[]) throws  Exception;

    void okRepHw(@Param("id") String id) throws  Exception;

    List<Map<String,Object>> getOutRespHwu() throws  Exception;

    List<Map<String,Object>> getCargoDesc(@Param("arrs") String[] arrs) throws  Exception;

    List<Map<String,Object>> loadCars() throws  Exception;

    void addOutRep(Map<String,String> map) throws Exception;

    void updateRepListCount(@Param("relcount") Integer count,@Param("list_id") String listId)throws  Exception;

    List<Map<String,Object>> showRepAssemble()throws  Exception;

    Map<String,Object> getOutNumberDesc(@Param("out_number") String outNumber) throws  Exception;

    List<Map<String,Object>>  findCatIdbyNumberId(@Param("out_number") String outNumber) throws  Exception;

    void addAssRep(Map<String,String> map) throws Exception;

    void updOutRespStatus(@Param("out_number") String number,@Param("status") Integer status);

    List<Map<String,Object>> showOutRepConf() throws Exception;

    void claerCount() throws  Exception;

    void addCatFreight(Map<String,String> map) throws Exception;

    List<Map<String,Object>> showInputDesc(@Param("inpNumber") String number) throws Exception;

    void updateCarStatus(@Param("carId") String carId) throws Exception;

    List<Map<String,Object>> showInventory() throws Exception;

    void updateRepPositionId(@Param("positionId") String positionId,@Param("listId") String listId) throws  Exception;

    void addRepyu(Map<String,String> map) throws Exception;

    List<Map<String,Object>> getRepYuList(@Param("rid") String rid) throws Exception;

    void addRepWei(Map<String,String> map) throws Exception;
}


