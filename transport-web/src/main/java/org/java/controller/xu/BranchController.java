package org.java.controller.xu;

import org.java.entity.TranBranch;
import org.java.entity.TranOrder;
import org.java.service.xu.BranchService;
import org.java.service.xu.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/branch")
public class BranchController {

    @Autowired
    private BranchService branchService;

    @Autowired
    private OrderService orderService;

    @RequestMapping("/selBranchPeople")
    public String selBranchPeople(String bId,Model model){
        TranBranch branch = branchService.oneBranch(bId);
        List<Map<String, Object>> people = branchService.branchPeopleById(bId);
        int count = branchService.selBranchPeopleCount(bId);
        model.addAttribute("branch",branch);
        model.addAttribute("people",people);
        model.addAttribute("count",count);
        return "/xu/branch/selBranchPeople";
    }

    //将人员进行分配
    @RequestMapping("/allocationPeople2")
    public String allocationPeople(String userId,String branchId){
        branchService.updateBid(branchId,userId);
        return "redirect:/branch/allocationPeople.do";
    }

    //分配网点员工页面
    @RequestMapping("/allocationPeople")
    public String allocationPeople(Model model){
        List<Map<String, Object>> list = branchService.branchPeople();
        List<Map<String, Object>> notList = branchService.branchNotPeople();
        Integer peopleCount = branchService.allocationPeopleCount();
        List<Map<String, Object>> people = branchService.allocationPeople();
        List<TranBranch> branch = branchService.selALL();
        model.addAttribute("peopleCount",peopleCount);
        model.addAttribute("list",list);
        model.addAttribute("notList",notList);
        model.addAttribute("people",people);
        model.addAttribute("branch",branch);
        return "/xu/branch/branchPeople";
    }

    @RequestMapping("/updateOrder")
    public String updateOrder(String id){
        TranOrder order =new TranOrder();
        order.setOrderId(id);
        order.setOrderStatus(5);
        orderService.updateByPrimaryKeySelective(order);
        return "/xu/branch/collectPack";
    }

    //根据id得到商品的信息
    @RequestMapping("/getOrder")
    @ResponseBody
    public TranOrder getOrder(@RequestBody String id){
        System.out.println("进入到了订单");
        return  orderService.selectByPrimaryKey(id);
    }

    //跳转到揽件页面
    @RequestMapping("/getCollectPack")
    public String getCollectPack(){
        return "/xu/branch/collectPack";
    }

    //查看网点的详情
    @RequestMapping("/detailBranch")
    public String detailBranch(String id,Model model){
        TranBranch branch = branchService.oneBranch(id);
        Map<String, Object> map = branchService.branchDetailById(id);
        model.addAttribute("branch",branch);
        model.addAttribute("map",map);
        return "/xu/branch/detailBranch";
    }

    @RequestMapping("/dealBranchOrder")
    public String dealBranchOrder(Model model){
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        List<Map<String, Object>> list = branchService.branchDetail();
        System.out.println(list);
        model.addAttribute("list",list);
        return "/xu/branch/dealBranchOrder";
    }

    @ResponseBody
    @RequestMapping("/likeBranch")
    public List<TranBranch> likeBranch(@RequestBody String place){
        List<TranBranch> list = branchService.likeBranch(place);
        System.out.println(list);
        return list;
    }

    @RequestMapping("/mapBranch")
    public String mapBranch(Model model){
        List<TranBranch> list = branchService.selALL();
        System.out.println(list);
        model.addAttribute("list",list);
        return "/xu/branch/mapBranch";
    }

    @RequestMapping("/addBranchs")
    public String addBranch(TranBranch tranBranch,Model model){
        tranBranch.setbId(UUID.randomUUID().toString());
        branchService.addBranch(tranBranch);
        model.addAttribute("info","添加成功");
        return "/xu/branch/addBranch";
    }

    @RequestMapping("/selBranch")
    public String selBranch(Model model){
        List<TranBranch> list = branchService.selALL();
        model.addAttribute("list",list);
        System.out.println(list);
        return "/xu/branch/selBranch";
    }

    @RequestMapping("/path")
    public String path(){
        return "/xu/branchPath";
    }

    @RequestMapping("/addBranch")
    public String addBranch(){
        return "/xu/branch/addBranch";
    }


    @Override
    public String toString() {
        return super.toString();
    }
}
