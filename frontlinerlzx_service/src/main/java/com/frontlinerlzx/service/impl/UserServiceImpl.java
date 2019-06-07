package com.frontlinerlzx.service.impl;

import com.frontlinerlzx.dao.IUserDao;
import com.frontlinerlzx.domain.Role;
import com.frontlinerlzx.domain.UserInfo;
import com.frontlinerlzx.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao userDao;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserDetails loadUserByUsername(String username) {
        UserInfo userInfo = null;
        try {
            userInfo = userDao.findByUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //处理自己的用户对象封装成UserDetails
//        User user = new User(userInfo.getUsername(),"{noop}"+userInfo.getPassword(),getAuthority(userInfo.getRoles()));
        User user = new User(userInfo.getUsername(),userInfo.getPassword(),userInfo.getStatus() == 1 ? true : false,true,true,true,getAuthority(userInfo.getRoles()));
        return user;
    }


    /**
     * 作用放回一个List集合，集合中装入的角色描述
     * @return
     */

    public List<SimpleGrantedAuthority> getAuthority(List<Role> roles){
        List<SimpleGrantedAuthority> list = new ArrayList<SimpleGrantedAuthority>();
        for (Role role :  roles)
        list.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));

        return list;
    }

    public List<UserInfo> findAll() throws Exception{
        return userDao.findAll();
    }

    public void save(UserInfo userInfo) throws Exception {
        //对密码进行加密处理
        userInfo.setPassword(bCryptPasswordEncoder.encode(userInfo.getPassword()));

        userDao.save(userInfo);


    }

    public UserInfo findById(String id) throws Exception {

        return  userDao.findById(id);
    }

    public List<Role> findOtherRoles(String userid) throws Exception {
        return userDao.findOtherRoles(userid);
    }

    public void addRoleToUser(String userId, String[] roleids) throws Exception {


        for (String roleId : roleids){
            userDao.addRoleToUser(userId,roleId);
        }

    }
}
