package com.frontlinerlzx.controller;

import com.frontlinerlzx.domain.Permission;
import com.frontlinerlzx.domain.Role;
import com.frontlinerlzx.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {


    @Autowired
    IRoleService roleService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception {
        ModelAndView mv = new ModelAndView();

        List<Role> roles = roleService.findAll();


        mv.addObject("roleList", roles);
        mv.setViewName("role-list");
        return mv;
    }


    @RequestMapping("/save.do")
    public String save(Role role) throws Exception {

        roleService.save(role);

        return "redirect:findAll.do";
    }

    /**
     * 根据roleid查询role，并查询出可以添加的权限
     *
     * @return
     */
    @RequestMapping("/findRoleByIdAndParmission")
    public ModelAndView findRoleByIdAndParmission(@RequestParam(name = "id",required = true) String roleId) throws Exception {
        ModelAndView mv = new ModelAndView();

        //根据role查询role

        Role role = roleService.findByRoleId(roleId);


        //根据role查询可以添加的权限
        List<Permission> permissions = roleService.findOtherPermissions(roleId);

        mv.addObject("role", role);
        mv.addObject("permissionList", permissions);
        mv.setViewName("role-permission-add");
        return mv;
    }

    @RequestMapping("addPermissionToRole.do")
    public String addPermissionToRole(@RequestParam(name="roleId",required = true) String roleId,@RequestParam(name="ids",required = true) String[] permissionIds ) throws Exception {

        roleService.addPermissionToRole(roleId,permissionIds);


        return "redirect:findAll.do";
    }


    /**
     * 根据id删除指定角色
     */

    @RequestMapping("/deleteById.do")
    public String deleteById( String id ) throws Exception {
        roleService.deleteById(id);
        return "redirect:findAll.do";
    }
}
