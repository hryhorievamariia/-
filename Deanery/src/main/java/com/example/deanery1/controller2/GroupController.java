package com.example.deanery1.controller2;

import com.example.deanery1.model.Group;
import com.example.deanery1.service.GroupService;
import com.example.deanery1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/group")
public class GroupController {
    @Autowired
    private GroupService groupService;

    @Autowired
    UserService userService;



    @GetMapping("/create")
    public String createGroup(){
        return "AddGroupForm";
    }

    @PostMapping("/create")
    public String creategroup(@RequestParam("name") String name, @RequestParam("description") String description, @RequestParam("imageUrl") String imageUrl ){
        Group group = new Group(name, description, imageUrl);
        groupService.save(group);
        return "redirect:/group/admin/list";
    }

    @GetMapping("/list")
    public String getList(Model model, HttpSession session) throws Exception {
        List<Group> groupList = groupService.getAll();
        model.addAttribute("groupList", groupList);
        return "index";

    }

    @GetMapping("/update/{groupId}")
    public String updateGroup(@PathVariable("groupId") int groupId, Model model){
        if(!groupService.findById(groupId)){
            return "Group does not exist";
        }
        Group group = groupService.getById(groupId);
        model.addAttribute("group", group);
        return "UpdateGroupForm";
    }

    @PostMapping("/update/{groupId}")
    public String updateGroup(@RequestParam("name") String name, @RequestParam("description") String description, @RequestParam("imageUrl") String imageUrl, @PathVariable("groupId") int groupId){
        Group group = new Group(groupId, name, description, imageUrl);
        if(!groupService.findById(groupId)){
            return "Group does not exist";
        }
        System.out.println(group);
        groupService.updateById(groupId, group);
        return "redirect:/group/admin/list";
    }

    @GetMapping("admin/list")
    public String getAdminList(Model model, HttpSession session) throws Exception {
        List<Group> groupList = groupService.getAll();
        model.addAttribute("groupList", groupList);
        return "adminGroups";

    }

    @PostMapping("delete/{id}")
    public String deleteGroup(@PathVariable("id") int id){
        groupService.deleteById(id);
        return "redirect:/group/admin/list";
    }
}
