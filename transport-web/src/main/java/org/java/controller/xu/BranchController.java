package org.java.controller.xu;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/branch")
public class BranchController {

    @RequestMapping("/path")
    public String path(){
        return "/xu/branchPath";
    }

    @RequestMapping("/addBranch")
    public String addBranch(){
        return "/xu/branch/branchPath";
    }


    @Override
    public String toString() {
        return super.toString();
    }
}
