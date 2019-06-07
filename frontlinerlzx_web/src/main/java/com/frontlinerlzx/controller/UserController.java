package com.frontlinerlzx.controller;

import com.frontlinerlzx.domain.Role;
import com.frontlinerlzx.domain.UserInfo;
import com.frontlinerlzx.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    /**
     * 用户添加
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/save.do")
    @PreAuthorize("authentication.principal.username == 'admin'")
    public String save(UserInfo userInfo) throws Exception {
        userService.save(userInfo);
        System.out.println(userInfo);
        return "redirect:findAll.do";
    }

    @RequestMapping("/findAll.do")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView findAll() throws Exception {


        ModelAndView mv = new ModelAndView();
        List<UserInfo> userInfos = userService.findAll();
        mv.addObject("userList", userInfos);
        mv.setViewName("user-list");
        return mv;

    }

    /**
     * 查询指定id的用户
     */
    @RequestMapping("/findById.do")
    public ModelAndView findById(String id) throws Exception {
        ModelAndView mv = new ModelAndView();
        UserInfo userInfo = userService.findById(id);

        mv.addObject("user", userInfo);
        mv.setViewName("user-show1");

        return mv;
    }


    /**
     * 查询用户以及用户可以添加的角色
     */
    @RequestMapping("/findUserByIdAndAllRole.do")
    public ModelAndView findUserByIdAndAllRole(@RequestParam(name = "id", required = true) String userid) throws Exception {

        ModelAndView mv = new ModelAndView();
        //根据用户id查询用户

        UserInfo userInfo = userService.findById(userid);
        //根据用户id查询可以添加的角色

        List<Role> otherRoles = userService.findOtherRoles(userid);
        mv.addObject("user", userInfo);
        mv.addObject("roleList", otherRoles);
        mv.setViewName("user-role-add");
        return mv;

    }

    /**
     * 给指定用户分配角色
     */

    @RequestMapping("/addRoleToUser.do")
    public String addRoleToUser(@RequestParam(name = "userId", required = true) String userId, @RequestParam(name = "ids", required = true) String[] roleids) throws Exception {
        userService.addRoleToUser(userId, roleids);
        return "redirect:findAll.do";
    }

}
